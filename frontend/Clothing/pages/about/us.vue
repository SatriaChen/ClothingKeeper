<template>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">关于我们</block>
		</cu-custom>
		
	<view class="cu-bar bg-white margin-top">
		<view class="action sub-title">
			<text class="text-xl text-bold text-blue">关于我们</text>
			<text class="text-ABC text-blue">About</text>
			<!-- last-child选择器-->
		</view>
	</view>
		<!-- <view class="margin-top">
			<text>我们是一个来自四川大学计算机学院与软件学院的团队，我们以学校“双创”计划为契机，开发了这款软硬结合的智能衣柜管理器，希望通过我们的努力，可以让您的生活幸福感得到一点点的提高，感受智能家居的独特魅力</text>
		</view> -->
		<view class="cu-card dynamic">
			<view class="cu-item shadow">
				
				<view class="grid flex-sub padding-lr">
					<image src="../../static/uni.jpg"
					 mode="widthFix"></image>
				</view>
		
				<view class="cu-list menu-avatar comment solids-top">
					<view class="cu-item">
						
						<view class="content justify-between">
							
							<view class="bg-grey padding-sm radius margin-top-sm  text-sm">
								<view class="flex">
									<view class="flex-sub">我们是一个来自四川大学计算机学院与软件学院的团队，我们以学校“双创”计划为契机，开发了这款软硬结合的智能衣柜管理器，希望通过我们的努力，可以让您的生活幸福感得到一点点的提高，感受智能家居的独特魅力。</view>
								</view>
							</view>
							<view class="margin-top-sm flex justify-end">
								<view class="text-gray text-df">2020年2月28日</view>
								
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		</view>
</template>

<script>
</script>

<style>
</style>