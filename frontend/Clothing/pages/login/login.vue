<template>
	<view>
			<view class="UCenter-bg"  :style="{background: 'url(../../static/BasicsBg.png)'}">
				
				<view class="flex-sub text-center padding margin-top">
					<view class="cu-avatar xl round margin-left" style="background-image:url(https://ossweb-img.qq.com/images/lol/web201310/skin/big99008.jpg);"></view>
					<view class="text-lg margin-top">Clothing Keeper</view>
					<view class="margin-top">用户登录</view>
					<!-- <image src="https://image.weilanwl.com/gif/rhomb-black.gif" mode="aspectFit" class="gif-black response" style="height:240upx"></image> -->
				</view>
			</view>
		
			<view>
			<form>
			<view class="cu-form-group margin-top">
				<view class="title">用户名</view>
				<input placeholder="请输入用户名" name="username" v-model="username"></input>
			</view>
			<view class="cu-form-group">
				<view class="title">密码</view>
				<input placeholder="请输入密码" name="pwd" v-model="pwd" type="password"></input>
			</view>
			</form>
			<view class="box">
			  <view class="cu-bar btn-group">
				<navigator url="../register/register" class="cu-btn text-green line-green shadow text-center" style="width: 330rpx;">立即注册
					<!-- <button class="cu-bar text-green line-green shadow text-center"></button> -->
					</navigator>
<!-- 				<button class="cu-btn text-green line-green shadow" @click="reg" id="cli">立即注册</button> -->	
			  	<button class="cu-btn bg-green shadow-blur" type="primary" @tap="loginBtn">登录</button>
			  </view>
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
					username:'',
					pwd:'',
				};
			},
			// onLoad() {
			// 	self=this;
			// 	uni.getStorage({
			// 		key:'userinfo',
			// 		success:function(res){
			// 			const listdata=res.data;
			// 			self.list=listdata;
			// 			console.log("数据是："+self.list.UserID);
			// 		}
			// 	})
			// },
			
			methods: {
				loginBtn(){
					if(this.username.length<=0){
						uni.showToast({
							icon:'none',
							title:'请输入用户名'
						});
						return;
					}
					if(this.pwd.length<=0){
						uni.showToast({
							icon:'none',
							title:'请输入密码'
						});
						return;
					}
					uni.request({
						url:'http://clothingkeeper.cn:8082/api/login',
						data:{
							uName:this.username, 
							uPassword:this.pwd
						},
						header : {  
						'content-type': 'application/x-www-form-urlencoded;charset=utf-8'  
						},
						success:(res)=>{
							let list=JSON.stringify(res.data);
							console.log("返回数据:"+list);
							if(list=="400"){
								uni.showToast({
									title: '密码错误',
									icon:'none'
								});
								return;
							}
							else if(list=="404"){
								uni.showToast({
									title: '用户不存在',
									icon:'none'
								});
								return;
								}
							else{
							uni.navigateTo({
								url:'../index/index',
							});
							uni.showToast({
									title: '登录成功'
								});
							// uni.setStorage({
							// 		key:'userid',
							// 		data:res.data.UserID,
							// 		success:function(){
							// 		    uni.getStorage({
							// 		    	key:'userid',
							// 				success:function(res){
							// 					console.log(res.data);	
							// 				}
							// 		    });	
							// 		}
							// 	});
							//存储token
							uni.setStorage({
								key:'token',
								data:res.data.token,
								success:function(){
								    uni.getStorage({
								    	key:'token',
										success:function(res){
											console.log(res.data);	
										}
								    });	
								}
							});
								}
						},fail:()=>{
							uni.showToast({
								title: '网络异常'
							});
						},
						
					})
					
				},
				
			}
		}
</script>

<style>
</style>
