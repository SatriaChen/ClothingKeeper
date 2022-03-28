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
				<button class="cu-btn bg-green shadow" @tap="getRecommend" data-target="menuModal">获取</button>
			</view>
		</view>
		<!-- 
		<view class="cu-form-group">
			
			<view class="region">
				<text>我的位置</text>
				<text>
					{{province}} {{city}} {{district}}
				</text> -->
				<!--class="popup-btn"-->
				<!-- <text class="cuIcon-right" @click="popup_bottom()"></text>
				<linkAddress
					ref="linkAddress"
					:height="height"
					@confirmCallback="confirmCallback()"
				>
				</linkAddress>
			</view>
				
		</view> -->
	<!-- 	<view class="bg-white">
			<button @click="getRecommend()">获取推荐</button>
		</view> -->
			<view class="solids-bottom padding-xs flex align-center margin-top" v-if="centigrade!=null">
				<view class="flex-sub text-center">
					<view class="solid-bottom text-sl padding">
						<text class="text-blue">{{centigrade.low}}~{{centigrade.high}}℃</text>
					</view>
				</view>
			</view>
	</view>
			
			
			<view class="cu-bar justify-center bg-white" v-if="clothesResponse.upper!=null">
				<view class="action sub-title">
					<text class="text-xl text-bold text-green">上装</text>
					<text class="bg-green" style="width:2rem"></text>
					<!-- last-child选择器-->
				</view>
			</view>
			<view class="cu-list menu-avatar">
					<view class="cu-item " v-for="(item,index) in clothesResponse.upper" :key="index">
						<view class="cu-avatar radius lg">
							<image class="cu-avatar radius lg" :src="item.cClothimg"></image>
						</view>
						<view class="content">
							<view class="text-brown">
								<view class="text-cut">{{item.cTag}}</view>
							</view>
							<view class="cu-tag bg-red light sm round">{{item.cSeason}}</view>
						</view>
						<view class="action">
							<view class="cu-tag bg-green sm round">{{item.cState}}</view>
						</view>
					</view>
			</view>
			
			<view class="cu-bar justify-center bg-white" v-if="clothesResponse.bottom!=null">
				<view class="action sub-title">
					<text class="text-xl text-bold text-green">下装</text>
					<text class="bg-green" style="width:2rem"></text>
					<!-- last-child选择器-->
				</view>
			</view>
			<view class="cu-list menu-avatar">
					<view class="cu-item " v-for="(item,index) in clothesResponse.bottom" :key="index">
						<!--<view class="cu-avatar radius lg" style="background-image:url(../../static/upper.jpg);"></view>-->
						<view class="cu-avatar radius lg">
							<image class="cu-avatar radius lg" :src="item.cClothimg"></image>
						</view>
						<view class="content">
							<view class="text-brown">
								<view class="text-cut">{{item.cTag}}</view>
							</view>
							<view class="cu-tag bg-red light sm round">{{item.cSeason}}</view>
						</view>
						<view class="action">
							<view class="cu-tag bg-green sm round">{{item.cState}}</view>
						</view>
					</view>
			</view>			
			
			
			<view class="cu-bar justify-center bg-white" v-if="clothesResponse.suit!=null">
				<view class="action sub-title">
					<text class="text-xl text-bold text-green">套装</text>
					<text class="bg-green" style="width:2rem"></text>
					<!-- last-child选择器-->
				</view>
			</view>
			<view class="cu-list menu-avatar">
					<view class="cu-item " v-for="(item,index) in clothesResponse.suit" :key="index">
						<!-- <view class="cu-avatar radius lg" style="background-image:url(../../static/upper.jpg);"></view> -->
						<view class="cu-avatar radius lg">
							<image class="cu-avatar radius lg" :src="item.cClothimg"></image>
						</view>
						<view class="content">
							<view class="text-brown">
								<view class="text-cut">{{item.cTag}}</view>
							</view>
							<view class="cu-tag bg-red light sm round">{{item.cSeason}}</view>
						</view>
						<view class="action">
							<view class="cu-tag bg-green sm round">{{item.cState}}</view>
						</view>
					</view>
			</view>
			
			<view class="cu-bar justify-center bg-white" v-if="clothesResponse.elseCloth!=null">
				<view class="action sub-title">
					<text class="text-xl text-bold text-green">其他</text>
					<text class="bg-green" style="width:2rem"></text>
					<!-- last-child选择器-->
				</view>
			</view>
			<view class="cu-list menu-avatar">
					<view class="cu-item " v-for="(item,index) in clothesResponse.elseCloth" :key="index">
						<!--<view class="cu-avatar radius lg" style="background-image:url(../../static/upper.jpg);"></view>-->
						<view class="cu-avatar radius lg">
							<image class="cu-avatar radius lg" :src="item.cClothimg"></image>
						</view>
						<view class="content">
							<view class="text-brown">
								<view class="text-cut">{{item.cTag}}</view>
							</view>
							<view class="cu-tag bg-red light sm round">{{item.cSeason}}</view>
						</view>
						<view class="action">
							<view class="cu-tag bg-green sm round">{{item.cState}}</view>
						</view>
					</view>
			</view>
		</view>

</template>


<script>
	import linkAddress from '../../components/xuan-linkAddress/xuan-linkAddress.vue'
	import request from "../../common/request.js"
	
	export default {
		components:{
			linkAddress
		},
		data() {
			let centid=this.$store.state.centigrade;
			//console.log(centid);
			let ads=this.$store.state.user_address;
			//console.log(ads);
			let cloreps = this.$store.state.clothesResponse;
			//console.log(cloreps);
			return {
				height: '500rpx',
				province: ads.province,
				city: ads.city,
				district: ads.district,
				centigrade:centid,
				clothesResponse: cloreps
				
			}
		},
		
		created() {
			console.log("页面加载");
			this.province = "四川省";
			this.city = "成都市"
			this.district = "双流县"
			this.centigrade.high ="25";
			this.centigrade.low ="15";
		},
		/*onShow() {
						console.log("页面展示");
						this.province="安徽省";
						this.city="合肥市";
						this.district="瑶海区";
						this.gettemperature();
					},*/
		methods: {
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
			//传递cityname，从后台获取该城市的气温
			//debugger	
			
			getRecommend(){
				var that = this;
				request({
					
					url:'api/recommend',
					data:{
						city: this.city
					},
					header : {  
					'content-type': 'application/x-www-form-urlencoded;charset=utf-8'  
					},
					success: function(res) {
						console.log(res);
						that.clothesResponse = null;
						that.centigrade = null;
						that.clothesResponse = res.data.clothesResponse;
						that.centigrade = res.data.centigrade;
						that.$store.commit('get_clothesResponse',res.data.clothesResponse);
						that.$store.commit('get_centigrade',res.data.centigrade);
						
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
