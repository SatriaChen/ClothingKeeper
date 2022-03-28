<template>
	<view>
		
		<mainpage v-if="PageCur=='main'"></mainpage>
		<about v-if="PageCur=='about'"></about>
		<closet v-if="PageCur=='closet'"></closet>
		<moniter v-if="PageCur=='moniter'"></moniter>
		<recommend v-if="PageCur=='recommend'"></recommend>
	
		<!-- <view class="cu-bar tabbar bg-white shadow foot">
			<view class="action" @click="NavChange" data-cur="main">
				<view class='cuIcon-cu-image'>
					<image :src="'/static/tabbar/basics' + [PageCur=='main'?'_cur':''] + '.png'"></image>
				</view>
				<view :class="PageCur=='main'?'text-green':'text-gray'">首页</view>
			</view>
			
			
			<view class="action" @click="NavChange" data-cur="about">
				<view class='cuIcon-cu-image'>
					<image :src="'/static/tabbar/component' + [PageCur == 'about'?'_cur':''] + '.png'"></image>
				</view>
				<view :class="PageCur=='about'?'text-green':'text-gray'">关于</view>
			</view>
			
			
			
			<view class="action" @click="NavChange" data-cur="closet">
				<view class='cuIcon-cu-image'>
					<image :src="'/static/tabbar/plugin' + [PageCur == 'closet'?'_cur':''] + '.png'"></image>
				</view>
				<view :class="PageCur=='closet'?'text-green':'text-gray'">衣橱</view>
			</view>
		</view> -->
		
		<view class="cu-bar tabbar bg-white shadow foot">
			
			<view class="action" @click="NavChange" data-cur="closet">
				<view class="cuIcon-clothesfill"></view> 
				<view :class="PageCur=='closet'?'text-green':'text-gray'">衣橱</view>
			</view>
			
			<view class="action" @click="NavChange" data-cur="recommend">
				<view class="cuIcon-appreciatefill"></view> 
				<view :class="PageCur=='recommend'?'text-green':'text-gray'">推荐</view>
			</view>
			
			<view class="action text-gray add-action" @click="NavChange" data-cur="main">
				<button class="cu-btn cuIcon-homefill bg-green shadow"></button>
				首页
			</view>
			
			<view class="action" @click="NavChange" data-cur="moniter">
				<view class="cuIcon-attentionfill"></view> 
				<view :class="PageCur=='moniter'?'text-green':'text-gray'">监测</view>
			</view>
			
			<view class="action" @click="NavChange" data-cur="about">
				<view class="cuIcon-peoplefill"></view> 
				<view :class="PageCur=='about'?'text-green':'text-gray'">我的</view>
			</view>
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
		return {
				PageCur: 'main'
			}
		},
		methods: {
			NavChange: function(e) {
				this.PageCur = e.currentTarget.dataset.cur
			}
		}
	}
</script>

<style>

</style>
