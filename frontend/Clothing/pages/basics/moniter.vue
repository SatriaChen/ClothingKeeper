<template name="moniter">
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">实时监测</block>
		</cu-custom>
		
		<view class="UCenter-bg"  :style="{background: 'url(../../static/BasicsBg.png)'}">
			<!-- <view class="padding">
			<view class="cu-avatar xl round margin-left bg-gradual-green">68%</view>
			<view class="cu-tag badge" :class="cuIcon-infofill text-red" ></view> -->
			<view class="flex-sub text-center padding margin-top">
				<view class="solid-bottom text-xsl light margin-top">
					<text>{{humidity}}%</text>
				</view>
			<!-- 	<view class="content">
					<button @click="connect" type="primary">connect</button>
					<view class="log">
						<view v-for="(log,index) in logs" class="" :key="index">
							{{log}}
						</view>
					</view>
				</view> -->
				
				<view class="text-lg margin-top">衣柜内当前湿度</view>
				<view class="margin-top">适宜范围:40~50%</view>
				<!-- <image src="https://image.weilanwl.com/gif/rhomb-black.gif" mode="aspectFit" class="gif-black response" style="height:240upx"></image> -->
			</view>
		    <!-- </view> -->
			</view>
			<view class="cu-card article">
				<view class="cu-item shadow">
					<view class="title"><view class="text-cut">防潮防湿</view></view>
					<view class="content">
						<image src="../../static/msg.jpg"
						 mode="aspectFill"></image>
						<view class="desc">
							<view class="text-content"> 房内过潮会引起很多麻烦，衣物就是最大的受害者之一。而衣柜湿度要不能控制得当，对衣服的影响就大得多了。其实衣柜防潮纸需要做到以下细节，就可以保你任何天气都有干爽的衣服。</view>
						</view>
					</view>
				</view>
			</view>
			<view class="cu-card article">
				<view class="cu-item shadow">
					<view class="title"><view class="text-cut">如何除湿</view></view>
					<view class="content">
						<image src="../../static/closet.jpg"
						 mode="aspectFill"></image>
						<view class="desc">
							<view class="text-content">除湿机除湿、除湿包去湿、石灰吸潮法</view>
						</view>
					</view>
				</view>
			</view>
		
		
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				humidity:'48',
				socketTask: null,
				client:null,
				// 确保websocket是打开状态
				is_open_socket: false
			}
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
						self.client.subscribe('/home/monitor', function(err) {
							
						}).on('message', function(topic, message) {
							console.log(message);
							self.humidity=message.toString()
						// self.logs.push(message.toString())
					})
					})
			            
		},
		beforeDestroy() {
		
					this.closeSocket();
		
				},
		methods: {
				// 关闭websocket【离开这个页面的时候执行关闭】			
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
					},
			
},
}
</script>

<style>
	.UCenter-bg {
	 /* background:linear-gradient(45deg, #0081ff, #1cbbb4); */
	  background-size: cover;
	  height: 500rpx;
	  display: flex;
	  justify-content: center;
	  padding-top: 40rpx;
	  overflow: hidden;
	  position: relative;
	  flex-direction: column;
	  align-items: center;
	  color: #fff;
	  font-weight: 300;
	  text-shadow: 0 0 3px rgba(0, 0, 0, 0.3);
	}
	
</style>
