<template>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">季度榜单</block>
		</cu-custom>
	
		<view class="cu-bar bg-white margin-top">
			<view class="action sub-title">
				<text class="text-xl text-bold text-blue">季度单品榜单</text>
				<text class="text-ABC text-blue">Favor</text>
			</view>
		</view>
	<view  v-for="i in htmlJSON" :key="i.clothesID">
		<view class="cu-list menu-avatar">
			<view class="cu-item ">
				<image class="cu-avatar radius lg" :src="i.ImageUrl"></image>
				<view class="content">
					<view class="text-brown">
						<view class="text-cut">{{i.tag}}</view>
					</view>
					<view class="cu-tag bg-red light sm round">{{i.season}}</view>
					<view class="cu-tag bg-blue light sm round">{{i.style}}</view>
				</view>
				<view class="action">
					<view class="cu-tag round bg-orange sm light">{{i.frequence}}次</view>
				</view>
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
	            	htmlJSON:[],
	            };
	        },
			onLoad() {
				this.productsList();
			},
	        methods: {
				productsList() {
						request({
							url: "api/favor",
							method:'get',
							success: (res) => {
								let table = res.data;
								this.htmlJSON = table;
								console.log(table);
							}
						});
						
					}
	        },
	    }

</script>
.box {
		margin: 20upx 0;
	}

	.box view.cu-bar {
		margin-top: 20upx;
	}

			 .1{
				 background:#ffffff url(../../static/upper.jpg) no-repeat;
			 }
			 .2{
				 background-image:url(../../static/bottom.jpg)
	     }
		 .3{
			 background-image:url(../../static/suit.jpg)
		 }
		 .4{
			 background-image:url(../../static/shoes.jpg)
		 }
<style>
</style>
