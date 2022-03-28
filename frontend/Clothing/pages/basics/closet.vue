<template name="closet">
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">我的衣橱</block>
		</cu-custom>
	
	<scroll-view scroll-x class="bg-white nav  shadow margin-top">
		<view class="flex text-center">
			<view class="cu-item flex-sub" :class="0==TabCur?'text-orange cur':''" @tap="tabSelect" :data-id="0">
				上装
			</view>
			<view class="cu-item flex-sub" :class="1==TabCur?'text-orange cur':''" @tap="tabSelect" :data-id="1">
				下装
			</view>
			<view class="cu-item flex-sub" :class="2==TabCur?'text-orange cur':''" @tap="tabSelect" :data-id="2">
				套装
			</view>
		  <view class="cu-item flex-sub" :class="3==TabCur?'text-orange cur':''"  @tap="tabSelect" :data-id="3">
			  其他
		</view>
		<view>
			<navigator url="../closet/add">
			<view class="cu-item flex-sub">新增</view>
			</navigator>
		</view>
		</view>
	</scroll-view>
	
	<upper v-if="TabCur==0"></upper>
	<bottoms v-if="TabCur==1"></bottoms>
	<suit v-if="TabCur==2"></suit>
	<shoes v-if="TabCur==3"></shoes>
	</view>
</template>

<script>
	import request from "../../common/request.js"
	export default {
		data() {
			return {
				TabCur: 0,
				scrollLeft: 0,
				cRfid:"",
				clothesdata:[],
				client:null,
				is_open_socket: false
			};
		},
		created(){
			var self = this
		    var mqtt = require('mqtt')
			// #ifdef H5
			self.client = mqtt.connect('ws://47.103.18.170:8080')
			// #endif
			// #ifdef MP-WEIXIN||APP-PLUS
			self.client = mqtt.connect('wx://47.103.18.170:8080')
			// #endif
					self.client.on('connect', function() {
						//self.logs.push('on connect')
						self.client.subscribe('/home/rfid', function(err) {
							self.cRfid="";
						}).on('message', function(topic, message) {
							console.log(message);
							self.cRfid=message.toString();
							console.log(self.cRfid);
							
							uni.setStorage({
								key:'RFID',
								data:self.cRfid,
								success:function(){
								    uni.getStorage({
								    	key:'RFID',
										success:function(res){
											console.log(res.data);	
										}
								    });	
								}
							});
							self.getClothes(); 
						//self.logs.push(message.toString())
						
					})
					})
			            
		},
		beforeDestroy() {
		
					this.closeSocket();
		
				},
		methods: {
			getClothes(){
				var that=this;
				request({
						url:'cloth/countByrfid',
						data:{
							cRfid:this.cRfid
						},
						header : {  
						'content-type': 'application/x-www-form-urlencoded;charset=utf-8'  
						},
						success: function(res) {
							// uni.showToast({
							// 	title:"查询成功"
							// })
							//console.log(res.data);
							if(res.data==0)
							{uni.navigateTo({
					            url:'../closet/add',
				                            });
				             }else{
								 uni.navigateTo({
								     url:'../closet/cloth',
								     });
							 }
							// console.log(res.data);
							// const tempdata=res.data;
							// that.clothesdata=tempdata.data;
							// console.log(that.clothesdata);
						},
						fail: function() {
							console.log("连接失败");
						}
				
				});
			},
			tabSelect(e) {
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60
			},
			closeSocket() {
				var self=this;
				self.client.close({
					success(res) {
						this.is_open_socket = false;
					    console.log("关闭成功", res)					
						},					
						fail(err) {
							console.log("关闭失败", err)					
							}				
							})			
				}
		}
	}
</script>

<style>
</style>
