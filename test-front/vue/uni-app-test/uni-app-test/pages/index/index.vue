<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
		<div class='box'>
			hi ...
		</div>
		<!-- switch组件 -->
		<view>
			<switch checked="true" @change=""></switch>
		</view>
		<!-- 使用数字输入框组件 -->
		<yp-number-box></yp-number-box>
		<yp-number-box :min="0" :max="9"></yp-number-box>
		<yp-number-box @change="bindChange"></yp-number-box>
		<yp-number-box @change="change" :index="index" />
		
		<!-- 使用自定义组件 -->
		<mycomponent title="title" content="content ..."></mycomponent>
	</view>
	
</template>

<script>
	import ypNumberBox from "@/components/yp-number-box/yp-number-box.vue"
	import mycomponent from "@/components/my-component.vue"
	
	export default {
		data() {
			return {
				title: 'Hello---'
			}
		},
		onLoad() {

		},
		methods: {

		},
		components: {
			ypNumberBox,
			mycomponent
		}
	}
</script>

<style lang="less">
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
@import '../../less.less';
</style>
