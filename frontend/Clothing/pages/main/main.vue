<template name="mainpage">
			
			<view>
				<cu-custom bgColor="bg-gradual-blue" :isBack="true">
					<block slot="backText">返回</block>
					<block slot="content">首页</block>
				</cu-custom>
				
				<view class="cu-card case">
					<view class="cu-item shadow">
						<view class="image">
							<image src="../../static/logobg.jpg"
							 mode="widthFix"></image>
						</view>
					</view>
				</view>
				
				
			<scroll-view scroll-y class="page">
			<view class="nav-list margin-top-xl">
				<navigator hover-class="none" :url="'/pages/basics/' + item.name" class="nav-li" navigateTo :class="'bg-'+item.color"
				 :style="[{animation: 'show ' + ((index+1)*0.2+1) + 's 1'}]" v-for="(item,index) in elements" :key="index">
					<view class="nav-title">{{item.title}}</view>
					<view class="nav-name">{{item.name}}</view>
					<text :class="'cuIcon-' + item.cuIcon"></text>
				</navigator>
				
			</view>
			
			<!-- <view class="cu-tabbar-height"></view> -->
		</scroll-view>
		
	</view>
</template>

<script>
export default {
		name: "mainpage",
		data() {
			return {
				elements: [{
						title: '我的衣橱',
						name: 'closet',
						color: 'cyan',
						cuIcon: 'clothesfill'
					},
					{
						title: '穿衣推荐',
						name: 'recommend',
						color: 'blue',
						cuIcon: 'appreciatefill'
					},
					{
						title: '实时监测',
						name: 'moniter',
						color: 'purple',
						cuIcon: 'attentionfill'
					},
					{
						title: '关于我们 ',
						name: 'about',
						color: 'mauve',
						cuIcon: 'myfill'
					},
				],
			
			
			cardCur: 0,
			swiperList: [{
					id: 0,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big84000.jpg'
				}, {
					id: 1,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big37006.jpg',
				}, {
					id: 2,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big39000.jpg'
				}, {
					id: 3,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big10001.jpg'
				}, {
					id: 4,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big25011.jpg'
				}, {
					id: 5,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big21016.jpg'
				}, {
					id: 6,
					type: 'image',
					url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big99008.jpg'
				}],
				dotStyle: false,
				towerStart: 0,
				direction: ''
			};
		},
		onShow() {
			console.log("success")
		},
		onload(){
			this.TowerSwiper('swiperList')
		},
		methods: {
				DotStyle(e) {
					this.dotStyle = e.detail.value
				},
				// cardSwiper
				cardSwiper(e) {
					this.cardCur = e.detail.current
				},
				// towerSwiper
				// 初始化towerSwiper
				TowerSwiper(name) {
					let list = this[name];
					for (let i = 0; i < list.length; i++) {
						list[i].zIndex = parseInt(list.length / 2) + 1 - Math.abs(i - parseInt(list.length / 2))
						list[i].mLeft = i - parseInt(list.length / 2)
					}
					this.swiperList = list
				},
		
				// towerSwiper触摸开始
				TowerStart(e) {
					this.towerStart = e.touches[0].pageX
				},
		
				// towerSwiper计算方向
				TowerMove(e) {
					this.direction = e.touches[0].pageX - this.towerStart > 0 ? 'right' : 'left'
				},
		
				// towerSwiper计算滚动
				TowerEnd(e) {
					let direction = this.direction;
					let list = this.swiperList;
					if (direction == 'right') {
						let mLeft = list[0].mLeft;
						let zIndex = list[0].zIndex;
						for (let i = 1; i < this.swiperList.length; i++) {
							this.swiperList[i - 1].mLeft = this.swiperList[i].mLeft
							this.swiperList[i - 1].zIndex = this.swiperList[i].zIndex
						}
						this.swiperList[list.length - 1].mLeft = mLeft;
						this.swiperList[list.length - 1].zIndex = zIndex;
					} else {
						let mLeft = list[list.length - 1].mLeft;
						let zIndex = list[list.length - 1].zIndex;
						for (let i = this.swiperList.length - 1; i > 0; i--) {
							this.swiperList[i].mLeft = this.swiperList[i - 1].mLeft
							this.swiperList[i].zIndex = this.swiperList[i - 1].zIndex
						}
						this.swiperList[0].mLeft = mLeft;
						this.swiperList[0].zIndex = zIndex;
					}
					this.direction = ""
					this.swiperList = this.swiperList
				},
			}
	}
</script>

<style>
	.tower-swiper .tower-item {
		transform: scale(calc(0.5 + var(--index) / 10));
		margin-left: calc(var(--left) * 100upx - 150upx);
		z-index: var(--index);
	}
</style>
