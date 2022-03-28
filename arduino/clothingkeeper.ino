#include <PubSubClient.h>
#include <DHT.h>
#include <DHT_U.h>
#include <ESP8266WiFi.h>

#include <SPI.h>
#include <MFRC522.h>

#define SS_PIN 10
#define RST_PIN 9
#define DHTPIN D4
#define DHTTYPE DHT11
DHT dht(DHTPIN,DHTTYPE);

MFRC522 rfid(SS_PIN, RST_PIN); // Instance of the class

MFRC522::MIFARE_Key key; 

// Init array that will store new NUID 
byte nuidPICC[4];

int count=0;
bool WIFI_Status=true;

const char* mqtt_server_IP="47.103.18.170";
const int port=1883;
//const char* mqtt_username="mqttuser";
//const char* mqtt_password="12345678";

const char* topic_name="/home/monitor";
const char* topic_name_r="/home/rfid";
const char* client_id="wemos001";
unsigned long lastSend;
WiFiClient espclient;
PubSubClient client(espclient);


//一键联网
void smartConfig()
{
  WiFi.mode(WIFI_STA);
  Serial.println("\r\nWait for Smartconfig等待连接");
  delay(2000);
  // 等待配网
  WiFi.beginSmartConfig();
  
while (1)
  {
    Serial.print(".");
    digitalWrite(BUILTIN_LED,HIGH);
    delay(1000);
    digitalWrite(BUILTIN_LED,LOW);
    delay(1000);
    if (WiFi.smartConfigDone())
    {
      Serial.println("SmartConfig Success");
      Serial.printf("SSID:%s\r\n", WiFi.SSID().c_str());
      Serial.printf("PSW:%s\r\n", WiFi.psk().c_str());
      break;
    }
  }
}

//获取温湿度数据
void gettemp()
{
float h=dht.readHumidity();//湿度
float t=dht.readTemperature();//摄氏温度
float f=dht.readTemperature(true);
if(isnan(h)||isnan(t)||isnan(f))
{
  Serial.println("Fail");
  return;
  }
Serial.print("Humidity:");
Serial.println(h);
Serial.print("Temperature:");
Serial.println(t);
String temperature=String(t);
String humidity=String(h);
//String payload="{";
//payload+="\"temperature\":";
//payload+=temperature;
//payload+=",";
//payload+="\"humidity\":";
//payload+=humidity;
//payload+="}";
String payload=humidity;

char attributes[100];
payload.toCharArray(attributes,100);
client.publish(topic_name,attributes);
  }

  //获取rfid信息
 void getRfid(){
   // Reset the loop if no new card present on the sensor/reader. This saves the entire process when idle.
  if ( ! rfid.PICC_IsNewCardPresent())
    return;

  // Verify if the NUID has been readed
  if ( ! rfid.PICC_ReadCardSerial())
    return;

  Serial.print(F("PICC type: "));
  MFRC522::PICC_Type piccType = rfid.PICC_GetType(rfid.uid.sak);
  Serial.println(rfid.PICC_GetTypeName(piccType));

  // Check is the PICC of Classic MIFARE type
  if (piccType != MFRC522::PICC_TYPE_MIFARE_MINI &&  
    piccType != MFRC522::PICC_TYPE_MIFARE_1K &&
    piccType != MFRC522::PICC_TYPE_MIFARE_4K) {
    Serial.println(F("Your tag is not of type MIFARE Classic."));
    return;
  }

  if (rfid.uid.uidByte[0] != nuidPICC[0] || 
    rfid.uid.uidByte[1] != nuidPICC[1] || 
    rfid.uid.uidByte[2] != nuidPICC[2] || 
    rfid.uid.uidByte[3] != nuidPICC[3] ) {
    Serial.println(F("A new card has been detected."));

    // Store NUID into nuidPICC array
    for (byte i = 0; i < 4; i++) {
      nuidPICC[i] = rfid.uid.uidByte[i];
    }
   
    Serial.println(F("The NUID tag is:"));
    Serial.print(F("In hex: "));
    printHex(rfid.uid.uidByte, rfid.uid.size);
    Serial.println();
    Serial.print(F("In dec: "));
    printDec(rfid.uid.uidByte, rfid.uid.size);
    Serial.println();
  }
  else Serial.println(F("Card read previously."));

  // Halt PICC
  rfid.PICC_HaltA();

  // Stop encryption on PCD
  rfid.PCD_StopCrypto1();
  }


  //连接mqtt服务器
  void reconnect(){
    while(!client.connected()){
      if(client.connect(client_id))
      {
        Serial.println("Connect success");
        }
        else
        {
           Serial.print("failed,rc=");
           Serial.print(client.state());
           Serial.print("try 5 seconds later");
           delay(5000);
          }
      
      }
    }
void setup()
{
  Serial.begin(115200);
  dht.begin();
  SPI.begin(); // Init SPI bus
  rfid.PCD_Init(); // Init MFRC522 
  delay(10);
  pinMode(BUILTIN_LED,OUTPUT);


  
  Serial.println("\r\n正在连接");
  while(WiFi.status()!=WL_CONNECTED)
  {
    if(WIFI_Status)
    {
    Serial.print(".");
    digitalWrite(BUILTIN_LED,HIGH);
    delay(1000);
    digitalWrite(BUILTIN_LED,LOW);
    delay(1000);
    count++;
    if(count>=5){
      WIFI_Status=false;
      Serial.println("请用手机配置");
      }
      }
      else{
        smartConfig();
        }
    }
  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  
  for (byte i = 0; i < 6; i++) {
    key.keyByte[i] = 0xFF;
  }

  Serial.println(F("This code scan the MIFARE Classsic NUID."));
  Serial.print(F("Using the following key:"));
  printHex(key.keyByte, MFRC522::MF_KEY_SIZE);
  
  client.setServer(mqtt_server_IP,port);
  lastSend=0;
}
  
void loop()
{

  reconnect();
  if(millis()-lastSend>10000){
    gettemp();
    lastSend=millis();
    }
    getRfid();
    client.loop();
}

/**
 * Helper routine to dump a byte array as hex values to Serial. 
 */
void printHex(byte *buffer, byte bufferSize) {
  for (byte i = 0; i < bufferSize; i++) {
    Serial.print(buffer[i] < 0x10 ? " 0" : " ");
    Serial.print(buffer[i], HEX);
  }
}

/**
 * Helper routine to dump a byte array as dec values to Serial.
 */
void printDec(byte *buffer, byte bufferSize) {
  for (byte i = 0; i < bufferSize; i++) {
    Serial.print(buffer[i] < 0x10 ? " 0" : " ");
    Serial.print(buffer[i], DEC);
  }
  String DecId="";
   for (byte i = 0; i < bufferSize; i++) {
    DecId=DecId+String(buffer[i] < 0x10 ? " 0" : " ");
    DecId=DecId+String(buffer[i], DEC);
  }
  String payload=DecId;

char attributes[100];
payload.toCharArray(attributes,100);
client.publish(topic_name_r,attributes);
}
