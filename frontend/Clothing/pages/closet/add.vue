<template>
	<view>
<view>
<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">新增衣物</block>
		</cu-custom>
	</view>

	<view class="cu-form-group margin-top flex solid-bottom padding justify-center">
		<view class="grid col-4 grid-square flex-sub">
			<view class="bg-img" v-for="(item,index) in imgList" :key="index" @tap="ViewImage" :data-url="imgList[index]">
			 <image :src="imgList[index]" mode="aspectFill"></image>
				<view class="cu-tag bg-red" @tap.stop="DelImg" :data-index="index">
					<text class='cuIcon-close'></text>
				</view>
			</view>
			<view class="solids" @tap="ChooseImage" v-if="imgList.length<2">
				<text class='cuIcon-cameraadd'></text>
			</view>
		</view>
	
	</view>
	

	
	
	<view class="cu-form-group margin-top">
		<view class="title">衣物类型</view>
		<picker @change="PickerTypeChange" :value="typeindex" :range="pickertype">
			<view class="picker">
				{{pickertype[typeindex]}}
			</view>
		</picker>
	</view>
	
	<view class="cu-form-group margin-top">
		<view class="title">适宜季节</view>
		<picker @change="PickerTimeChange" :value="timeindex" :range="pickertime">
			<view class="picker">
				{{pickertime[timeindex]}}
			</view>
		</picker>
	</view>
	
	<view class="cu-form-group margin-top">
		<view class="title">衣物风格</view>
		<picker @change="PickerStyleChange" :value="styleindex" :range="pickerstyle">
			<view class="picker">
				{{pickerstyle[styleindex]}}
			</view>
		</picker>
	</view>
	
	<view class="cu-form-group margin-top">
		<textarea maxlength="-1" :disabled="modalName!=null" @input="textareaAInput" placeholder="其他标签"></textarea>
	</view>
   <view class="padding">
	<button class="cu-btn block bg-blue margin-tb-sm lg" type="primary" @tap="confirmadd">确认添加</button>		
	</view>
	</view>
</template>

<script>
	import request from "../../common/request.js"
	export default{
		data(){
			return{
				typeindex:0,
				timeindex:0,
				styleindex:0,
			    //tagindex:0,
				pickertype: ['上装', '下装', '套装','其他'],
				pickertime:['春秋', '夏季','冬季'],
				pickerstyle:['正式', '休闲','运动'],
                
		
				imgList: [],
				modalName: null,
				textareaAValue: '',
				textareaBValue: '',
				cClothimg:'123',
				cRfid:'',
			};
		},
		methods:{
			ChooseImage() {
				var that=this;
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: (res) => {
							this.imgList = res.tempFilePaths
							const tempFilePaths=res.tempFilePaths
							uni.uploadFile({
							    url: 'http://192.168.1.103:8081/cloth/picture', //仅为示例，非真实的接口地址
							    filePath: tempFilePaths[0],
							    name: 'file',
							    formData: {
							          'user': 'test'
							    },
							 success: function (uploadFileRes) {
							     console.log(uploadFileRes.data);
								 const imagedata=JSON.parse(uploadFileRes.data);
								 that.cClothimg=imagedata.data;
								 console.log(that.cClothimg);
							}
							});
					}
				});
			},
			ViewImage(e) {
				uni.previewImage({
					urls: this.imgList,
					current: e.currentTarget.dataset.url
				});
			},
			DelImg(e) {
				uni.showModal({
					title: '提示',
					content: '确定要删除吗？',
					cancelText: '再看看',
					confirmText: '再见',
					success: res => {
						if (res.confirm) {
							this.imgList.splice(e.currentTarget.dataset.index, 1)
						}
					}
				})
			},
			PickerTypeChange(e) {
				this.typeindex = e.detail.value
			},
			PickerTimeChange(e) {
				this.timeindex = e.detail.value
			},
			PickerStyleChange(e) {
				this.styleindex = e.detail.value
			},
			textareaAInput(e) {
				this.textareaAValue = e.detail.value
			},
	        confirmadd(){
				var that=this;
				uni.getStorage({
					key:'RFID',
					success:function(res){
						that.cRfid=res.data;
						console.log(res.data);	
						}
					});
				request({
						url:'cloth/new',
						data:{
							cClothimg: this.cClothimg,
							cRfid: this.cRfid,
							cSeason: this.timeindex,
							cStyle:this.styleindex,
							cTag:this.textareaAValue,
							cTypeid:this.pickertype[this.typeindex],
						},
						header : {  
						'content-type': 'application/x-www-form-urlencoded;charset=utf-8'  
						},
						success: function(res) {
							uni.showToast({
								title:"添加成功"
							})
							console.log(res);
							uni.navigateTo({
							    url:'../basics/closet',
							    });
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
