<template>
	<view>
		<view class="bg-white">
	<view class="cu-bar bg-white solid-bottom margin-top">
	<view class="cu-item arrow">
		<view class="region">
			<!--class="popup-btn"-->
			<text class="cuIcon-locationfill" style="margin-left:10rpx ;" @tap="popup_bottom"></text>
			<linkAddress
				ref="linkAddress"
				:height="height"
				@confirmCallback="confirmCallback()"
			>
			</linkAddress>
			
			<text class="text-grey">
				{{province}} {{city}} {{district}}
			</text>
		</view>
	</view>
		<view class="action">
			<button class="cu-btn bg-green shadow" @tap="getTravelRecommend" data-target="menuModal">获取</button>
		</view>
	</view>
			<view class="cu-form-group">
				<view class="title">出发日期</view>
				<picker id="start" mode="date" :value="startdate" v-bind:start="start" v-bind:end="end" @change="StartDateChange">
					<view class="picker">
						{{startdate}}
					</view>
				</picker>
			</view>
			<view class="cu-form-group">
				<view class="title">返回日期</view>
				<picker id="end" mode="date" :value="enddate" v-bind:start="endStart" v-bind:end="end" @change="EndDateChange">
					<view class="picker">
						{{enddate}}
					</view>
				</picker>
			</view>
	<!-- 		<view class="solids-bottom padding-xs flex align-center margin-top">
				<view class="flex-sub text-center">
					<view class="solid-bottom text-sl padding">
						<text class="text-blue text-sm">{{ganmao}}</text>
					</view>
				</view>
			</view> -->
			</view>
			
			<view v-for="(dayrecommend, i) in travelRecommend" :key = "i">
				<view>
					<view>第{{i+1}}天</view>
					<view class="cu-bar justify-center bg-white" v-if="dayrecommend.upper!=null">
						<view class="action sub-title">
							<text class="text-xl text-bold text-green">上装</text>
							<text class="bg-green" style="width:2rem"></text>
							<!-- last-child选择器-->
						</view>
					</view>
					<view class="cu-list menu-avatar">
							<view class="cu-item " v-for="(item,index) in dayrecommend.upper" :key="index">
								<!--<view class="cu-avatar radius lg" style="background-image:url(../../static/upper.jpg);"></view>-->
								<view class="cu-avatar radius lg">
									<image class="cu-avatar radius lg" :src="item.cClothimg"></image>
								</view>
								<view class="content">
									<view class="text-brown">
										<view class="text-cut">{{item.cStyle}}</view>
									</view>
									<view class="cu-tag bg-red light sm round">{{item.cSeason}}</view>
								</view>
								<view class="action">
									<view class="cu-tag bg-green sm round">{{item.cState}}</view>
								</view>
							</view>
					</view>
					<view class="cu-bar justify-center bg-white" v-if="dayrecommend.bottom!=null">
						<view class="action sub-title">
							<text class="text-xl text-bold text-green">下装</text>
							<text class="bg-green" style="width:2rem"></text>
							<!-- last-child选择器-->
						</view>
					</view>
					<view class="cu-list menu-avatar">
							<view class="cu-item " v-for="(item,index) in dayrecommend.bottom" :key="index">
								<!--<view class="cu-avatar radius lg" style="background-image:url(../../static/upper.jpg);"></view>-->
								<view class="cu-avatar radius lg">
									<image class="cu-avatar radius lg" :src="item.cClothimg"></image>
								</view>
								<view class="content">
									<view class="text-brown">
										<view class="text-cut">{{item.cStyle}}</view>
									</view>
									<view class="cu-tag bg-red light sm round">{{item.cSeason}}</view>
								</view>
								<view class="action">
									<view class="cu-tag bg-green sm round">{{item.cState}}</view>
								</view>
							</view>
					</view>			
					
					
					<view class="cu-bar justify-center bg-white" v-if="dayrecommend.suit!=null">
						<view class="action sub-title">
							<text class="text-xl text-bold text-green">套装</text>
							<text class="bg-green" style="width:2rem"></text>
							<!-- last-child选择器-->
						</view>
					</view>
					<view class="cu-list menu-avatar">
							<view class="cu-item " v-for="(item,index) in dayrecommend.suit" :key="index">
								<!--<view class="cu-avatar radius lg" style="background-image:url(../../static/upper.jpg);"></view>-->
								<view class="cu-avatar radius lg">
									<image class="cu-avatar radius lg" :src="item.cClothimg"></image>
								</view>
								<view class="content">
									<view class="text-brown">
										<view class="text-cut">{{item.cStyle}}</view>
									</view>
									<view class="cu-tag bg-red light sm round">{{item.cSeason}}</view>
								</view>
								<view class="action">
									<view class="cu-tag bg-green sm round">{{item.cState}}</view>
								</view>
							</view>
					</view>
					
					<view class="cu-bar justify-center bg-white" v-if="dayrecommend.elseCloth!=null">
						<view class="action sub-title">
							<text class="text-xl text-bold text-green">其他</text>
							<text class="bg-green" style="width:2rem"></text>
							<!-- last-child选择器-->
						</view>
					</view>
					<view class="cu-list menu-avatar">
							<view class="cu-item " v-for="(item,index) in dayrecommend.elseCloth" :key="index">
								<!--<view class="cu-avatar radius lg" style="background-image:url(../../static/upper.jpg);"></view>-->
								<view class="cu-avatar radius lg">
									<image class="cu-avatar radius lg" :src="item.cClothimg"></image>
								</view>
								<view class="content">
									<view class="text-brown">
										<view class="text-cut">{{item.cStyle}}</view>
									</view>
									<view class="cu-tag bg-red light sm round">{{item.cSeason}}</view>
								</view>
								<view class="action">
									<view class="cu-tag bg-green sm round">{{item.cState}}</view>
								</view>
							</view>
					</view>
				</view>
			</view>			
	</view>
</template>


<script>
	import request from "../../common/request.js"
	import cDate from "../../common/date.js"
	import linkAddress from '../../components/xuan-linkAddress/xuan-linkAddress.vue'
	export default {
		components:{
			linkAddress
		},
		data() {
			let ads = this.$store.state.user_address;
			let travel = this.$store.state.travelRecommend;
			let ganmao = this.$store.state.ganmao;
			return {
				height: '500rpx',
				province: ads.province,
				city: ads.city,
				district: ads.district,
				//region: ['四川省', '成都市', '武侯区'],
				startdate: cDate.get_now_formatDate(),
				enddate: cDate.get_now_formatDate(),
				start: cDate.get_now_formatDate(),
				end: cDate.get_end_date(),
				endStart: cDate.get_now_formatDate(),
				travelRecommend: travel,
				ganmao: ganmao
				
			};
		},
		
		methods: {
			StartDateChange(e) {
				this.startdate = e.detail.value
				this.endStart = e.detail.value
			},
			EndDateChange(e) {
				this.enddate = e.detail.value
			},
			//点击弹出弹窗
			popup_bottom: function() {
				this.height= '550rpx';
				//显示
				this.show_popup();
			},
			//显示弹窗
			show_popup: function() {
				this.$refs.linkAddress.show();
			},
			//回调
			confirmCallback: function() {
				let ads=this.$store.state.user_address;
				this.province = ads.province;
				this.city = ads.city;
				this.district = ads.district;
			},
			getTravelRecommend(){
				var that = this;
				request({
					url:'api/travel',
					data:{
						city: this.city,
						startdate: this.startdate,
						enddate: this.enddate
					},
					header : {  
					'content-type': 'application/x-www-form-urlencoded;charset=utf-8'  
					},
					success: function(res) {
						console.log(res);
						that.travelRecommend = null;
						that.ganmao = null;
						that.ganmao = res.data.ganmao;
						uni.showToast({
							title: res.data.ganmao,
							icon:'none'
						});
						that.travelRecommend = res.data.travelRecommend;
						that.$store.commit('get_travelRecommend',res.data.travelRecommend);
						that.$store.commit('get_ganmao',res.data.ganmao);
						
						
					},
					fail: function() {
						console.log("连接失败");
					}
			
			});
			}
	},
}
	
</script>

<style>
	.popup-btn {
		height: 2rem;
		width: 2rem;
		
	}
</style>
