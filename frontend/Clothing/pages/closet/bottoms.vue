<template>
	<view>
<!-- 	<view class="box">
		<view class="cu-bar search bg-white">
			<view class="search-form round">
				<text class="cuIcon-search"></text>
				<input @focus="InputFocus" @blur="InputBlur" :adjust-position="false" type="text" placeholder="搜索关键词" confirm-type="search"></input>
			</view>
			<view class="action">
				<button class="cu-btn bg-green shadow-blur round">搜索</button>
				</view>
				<view class="action">
					<navigator url="../closet/add">
				<button class="cu-btn bg-green shadow-blur round">新增</button>
				</navigator>
			</view>
		</view>
	</view> -->		
	<view class="cu-list menu-avatar">
		<view class="cu-item" :class="modalName=='move-box-'+ index?'move-cur':''" v-for="(item,index) in clothesdata" :key="index"
		 @touchstart="ListTouchStart" @touchmove="ListTouchMove" @touchend="ListTouchEnd" :data-target="'move-box-' + index">
			<image class="cu-avatar radius lg" :src="item.cClothImgUrl"></image>
			<view class="content">
				<view class="text-brown">
					<view class="text-cut">{{item.cTag}}</view>
				</view>
				<view class="cu-tag bg-red light sm round">{{item.cSeason}}</view>
				<view class="cu-tag bg-red light sm round">{{item.cStyle}}</view>
			</view>
			<view class="action">
				<view class="cu-tag bg-green sm round">{{item.cState}}</view>
			</view>
			<view class="move">
				<view class="bg-grey" @tap="deleteclothes(item.clothId)">删除</view>
				<view class="bg-red" @tap="changeState(item.clothId)">切换状态</view>
			</view>
		</view>
	</view>
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
				listTouchStart: 0,
				listTouchDirection: null,
			};
		},
		created(){
			this.getClothes()
		},
		methods: {
			// ListTouch触摸开始
			ListTouchStart(e) {
				this.listTouchStart = e.touches[0].pageX
			},
			
			// ListTouch计算方向
			ListTouchMove(e) {
				this.listTouchDirection = e.touches[0].pageX - this.listTouchStart > 0 ? 'right' : 'left'
			},
			
			// ListTouch计算滚动
			ListTouchEnd(e) {
				if (this.listTouchDirection == 'left') {
					this.modalName = e.currentTarget.dataset.target
				} else {
					this.modalName = null
				}
				this.listTouchDirection = null
			},
			
			InputFocus(e) {
				this.InputBottom = e.detail.height
			},
			InputBlur(e) {
				this.InputBottom = 0
			},
			getClothes(){
				var that=this;
				request({
						url:'cloth/clothType',
						data:{
							clothType:"下装"
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
	.cu-form-group .title {
		min-width: calc(4em + 15px);
	}
	.box {
		margin: 20upx 0;
	}
	
	.box view.cu-bar {
		margin-top: 20upx;
	}
</style>
