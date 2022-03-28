<template>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">设备配网</block>
		</cu-custom>
		<view class="UCenter-bg"  :style="{background: 'url(../../static/BasicsBg.png)'}">
			
			<view class="flex-sub text-center padding margin-top">
				<view class="cu-avatar xl round margin-left" style="background-image:url(https://ossweb-img.qq.com/images/lol/web201310/skin/big99008.jpg);"></view>
				<view class="text-lg margin-top">Clothing Keeper</view>
				<view class="margin-top">为您的设备配置无线网络</view>
				<!-- <image src="https://image.weilanwl.com/gif/rhomb-black.gif" mode="aspectFit" class="gif-black response" style="height:240upx"></image> -->
			</view>
		</view>
				
		<view>
		<form>
		<view class="cu-form-group margin-top">
			<view class="title">SSID:</view>
			<text class="showMsg" v-model="ssid">{{ssid}}</text>
		</view>
		<view class="cu-form-group">
			<view class="title">BSSID:</view>
			<text class="showMsg" v-model="bssid">{{bssid}}</text>
		</view>
		<view class="cu-form-group margin-top">
			<view class="title">密码</view>
			<input placeholder="请输入密码" type="text" v-model="password"></input>
		</view>
		<view class="cu-form-group">
			<view class="title">设备数量</view>
			<input placeholder="请输入密码" type="number" v-model="deviceCount"></input>
		</view>
		</form>
		
		
		
		<view class="box">
		  <view class="cu-bar btn-group">
			<button class="cu-btn text-green line-green shadow" type="primary" plain="true" @click="getmessage()">配网需知</button>
		  	<button class="cu-btn bg-green shadow-blur" type="primary" plain="true" @click="getWifiInfo()">获取Wi-Fi</button>
		</view>
			<view class="cu-bar btn-group">
			<button class="cu-btn bg-green shadow-blur" type="primary" plain="true" @click="smartConfig()">一键连接Wi-Fi</button>
			<button class="cu-btn bg-green shadow-blur" type="primary" plain="true" @click="cancel()">取消联网</button>
		  </view>
		</view>
		</view>
		  <view class="cu-tabbar-height"></view>
	</view>

</template>

<script>
	const modal = uni.requireNativePlugin('modal');
	const EspTouch = uni.requireNativePlugin('SZW-EspTouch');
	export default {
		data() {
			return {
				title: '',
				bssid: '',
				ssid: '',
				password: '',
				deviceCount: '1',
				broadcast: true,
			}
		},
		onLoad() {},
		created() {
			this.getWifiInfo()
		},
		methods: {
			getmessage(){
				
				uni.showModal({
				
				    title: '配网方式',
				
				    content: '首次使用该产品时需要为其配置WIFI网络，操作如下：1.打开wifi，并将手机连接上wifi；2.点击“获取Wi-Fi按钮”，系统将自动获取连接的网络；3.输入该网络的密码，点击“一键连接Wi-Fi”按钮；4.设备蓝灯闪烁表示设备正在等在配网，蓝灯长亮即配网成功',
				
				    success: function (res) {
				
				        if (res.confirm) {
				
				            console.log('用户点击确定');
				
				        } else if (res.cancel) {
				
				            console.log('用户点击取消');
				
				        }
				
				    }
				
				});

			},
			radioChange: function(event) {
				this.broadcast = Number(event.target.value) === 1;
			},
			getWifiInfo() {
				let info = EspTouch.getWifiInfo({}, result => {
				        const msg = JSON.stringify(result);
						console.log(result.SSID);
						this.ssid=result.SSID;
						this.bssid=result.BSSID;
				       // modal.toast({message: msg, duration: 1.5});
				    });
			},
			smartConfig() {
				let info = EspTouch.smartConfig(
				        {
				            bssid: this.bssid,
				            ssid: this.ssid,
				            password: this.password,
				            deviceCount: this.deviceCount,
				            broadcast: 'true'
				        }, 
				        result => {
				            const msg = JSON.stringify(result);
				
				            if(result.type == 'begin') {
				                modal.toast({message: "配置开始", duration: 1.5});
				            } 
				            else if(result.type == 'stepOk') {
				                modal.toast({message: "发现并配置了一个设备:"+msg, duration: 1.5});
				            } 
				            else if(result.type == 'stepFailed') {
				                modal.toast({message: "配置失败："+msg, duration: 1.5});
				            } 
				            else if(result.type == 'end') {
				                //modal.toast({message: "配置结束", duration: 1.5});
				            } 
				        }
				    );

			},
			cancel() {
				EspTouch.cancel();
			},
			
			// 			nvueclick() {
			// 				uni.navigateTo({
			// 					url: '/pages/nvue/index2'
			// 				});
			// 			}
		}
	}
</script>

<style>
</style>
