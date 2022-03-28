<template>
<view>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">穿衣推荐</block>
		</cu-custom>
	</view>
	
	<scroll-view scroll-x class="bg-white nav  shadow margin-top">
		<view class="flex text-center">
			<view class="cu-item flex-sub" :class="0==TabCur?'text-orange cur':''" @tap="tabSelect" :data-id="0">
				今日穿衣
			</view>
			<view class="cu-item flex-sub" :class="1==TabCur?'text-orange cur':''" @tap="tabSelect" :data-id="1">
				旅行推荐
			</view>
		</view>
	</scroll-view>
	
	<dayrecommend v-if="TabCur==0"></dayrecommend>
	<travelrecommend v-if="TabCur==1"></travelrecommend>
	
	</view>
</template>

<script>
	export default {
		data() {
			return {
				TabCur: 0,
				scrollLeft: 0
			};
		},
		methods: {
			tabSelect(e) {
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60
			}
		}
	}
</script>

<style>
</style>
