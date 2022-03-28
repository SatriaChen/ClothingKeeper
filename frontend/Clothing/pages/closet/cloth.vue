<template>
	<view>
			<cu-custom bgColor="bg-gradual-blue" :isBack="true">
					<block slot="backText">返回</block>
					<block slot="content">衣物详情</block>
				</cu-custom>
			 
	<!-- 			<image class="UCenter-bg"  :src="clothesdata[0].cClothImgUrl"> -->
					
					</image>
					<view class="cu-card case">
						<view class="cu-item shadow">
							<view class="image">
								<image :src="clothesdata[0].cClothImgUrl"
								 mode="widthFix"></image>
							</view>
						</view>
					</view>
				    <!-- </view> -->
					<view class="cu-list menu card-menu margin-top sm-border">
					    <view class="cu-item">
					        <text class="cuIcon-tagfill text-blue">详细描述</text>
					        <text class="text-grey">{{clothesdata[0].cTag}}</text>
					    </view>
						<view class="cu-item">
						    <text class="cuIcon-evaluate text-blue">适宜季节</text>
						    <text class="text-grey">{{clothesdata[0].cSeason}}</text>
						</view>
					    <view class="cu-item">
					        <text class="cuIcon-clothesfill text-blue">服装风格</text>
					        <text class="text-grey">{{clothesdata[0].cStyle}}</text>

					    </view>
					<view class="cu-item">
					    <text class="cuIcon-magic text-blue">服装状态</text>
					    <text class="text-grey">{{clothesdata[0].cState}}</text>
					</view>
					</view>
					
					
					<view class="box">
					  <view class="cu-bar btn-group">
					<button class="cu-btn bg-green shadow-blur" @tap="deleteclothes(clothesdata[0].clothId)">删除数据</button>
					<button class="cu-btn text-green line-green shadow" @tap="changeState(clothesdata[0].clothId)">更改状态</button>
					  </view>
					</view>
					
					  <view class="cu-tabbar-height"></view>
	</view>
</template>

<script>
	import request from "../../common/request.js"
	export default {
		data() {
			return {
				InputBottom: 0,
				clothesdata:[],
				modalName: null,
				cRfid:"",
			};
		},
		created(){
			this.getClothes()
		},
		methods: {
			getClothes(){
				
				var that=this;
				uni.getStorage({
					key:'RFID',
					success:function(res){
						that.cRfid=res.data;
						console.log(res.data);	
						}
					});
				request({
						url:'cloth/rfid',
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
							console.log(res.data);
							const tempdata=res.data;
							that.clothesdata=tempdata.data;
							console.log(that.clothesdata);
						},
						fail: function() {
							console.log("连接失败");
						}
				
				});
			},
			
			deleteclothes(e){
				var that=this;
				request({
						url:'cloth/clothdelete',
						data:{
							clothId:e
						},
						header : {  
						'content-type': 'application/x-www-form-urlencoded;charset=utf-8'  
						},
						success: function(res) {
							uni.showToast({
								title:"删除成功"
							})
						    that.getClothes();
							console.log(res.data);
						
						},
						fail: function() {
							console.log("连接失败");
						}
				
				});
	
			},
			changeState(e){
				var that=this;
				request({
						url:'cloth/changeState',
						data:{
							clothId:e
						},
						header : {  
						'content-type': 'application/x-www-form-urlencoded;charset=utf-8'  
						},
						success: function(res) {
							uni.showToast({
								title:"修改成功"
							})
						    that.getClothes();
							console.log(res.data);
						
						},
						fail: function() {
							console.log("连接失败");
						}
				
				});
			}
		}
	}
</script>

<style>
</style>
