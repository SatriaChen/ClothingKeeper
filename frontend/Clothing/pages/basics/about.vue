<template name="about">
	<view>
			<cu-custom bgColor="bg-gradual-blue" :isBack="true">
					<block slot="backText">返回</block>
					<block slot="content">您与我们</block>
				</cu-custom>
				
				<view class="UCenter-bg"  :style="{background: 'url(../../static/BasicsBg.png)'}">
					
					<view class="flex-sub text-center padding margin-top">
						<view class="cu-avatar xl round margin-left" style="background-image:url(https://ossweb-img.qq.com/images/lol/web201310/skin/big99008.jpg);"></view>
						<view class="text-lg margin-top">Clothing Keeper</view>
						<view class="margin-top">您与我们</view>
						<!-- <image src="https://image.weilanwl.com/gif/rhomb-black.gif" mode="aspectFit" class="gif-black response" style="height:240upx"></image> -->
					</view>
					</view>
				    <!-- </view> -->
					<view class="cu-list menu card-menu margin-top sm-border">
					    <view class="cu-item arrow">
					      <navigator class="content" url="/pages/about/favor" hover-class="none">
					        <text class="cuIcon-favorfill text-blue"></text>
					        <text class="text-grey">喜好榜单</text>
					      </navigator>
					    </view>
						<view class="cu-item arrow">
						  <navigator class="content" url="/pages/about/addmonitor" hover-class="none">
						    <text class="cuIcon-wifi text-blue"></text>
						    <text class="text-grey">设备配网</text>
						  </navigator>
						</view>
					    <view class="cu-item arrow">
					      <navigator class="content" url="/pages/about/introduce" hover-class="none">
					        <text class="cuIcon-edit text-blue"></text>
					        <text class="text-grey">软件介绍</text>
					      </navigator>
					    </view>
					<view class="cu-item arrow">
					  <navigator class="content" url="/pages/about/us" hover-class="none">
					    <text class="cuIcon-peoplefill text-blue"></text>
					    <text class="text-grey">关于我们</text>
					  </navigator>
					</view>
					</view>
					
					
					<view class="box">
					  <view class="cu-bar btn-group">
					<button class="cu-btn bg-green shadow-blur" @tap="deleteID">注销账户</button>
					<button class="cu-btn text-green line-green shadow" @tap="loginOut">退出登录</button>
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
					
				};
			},
			methods: {
				loginOut:function(){
									uni.reLaunch({
									    url: '../login/login',
									});
									 uni.setStorage({
									 	key:'token',
									    data:null
									 }); 
								
							},
							deleteID:function(){
								uni.showModal({
								    title: '提示',
								    content: '是否确认注销账户',
								    success: function (res) {
								        if (res.confirm) {
											request({
											          url:"api/deleteID",
											          method:'get',
											          success: function (res) {
											                console.log(res.data);
											                uni.showToast({
											                		title: '账户已注销'
											                	});
																uni.reLaunch({
																    url: '../login/login',
																});
											                }
											            }) 
								        } else if (res.cancel) {
								            console.log('用户点击取消');
								        }
								    }
								});   
							}
						},
		}

</script>

<style>
</style>
