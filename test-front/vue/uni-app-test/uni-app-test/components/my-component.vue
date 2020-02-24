<template>
	<view>
		<h1>新闻标题:{{title}}</h1>
		<div>新闻内容:{{content}}</div>
	</view>
</template>

<script>
	export default {
		// 声明组件属性
		props: {
			title : {
				type : String,
				default : ""
			},
			content  : {
				type : String,
				default : ""
			}
		},
		data() {
			return {
				
			};
		}
	}
</script>

<style>
</style>
