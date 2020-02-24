<template>
	<view>
		<!-- 组件内部事件传递给父级组件
		 当被点击时，调用 函数tap1-->
		<h1 @tap='tap1'>新闻标题:{{title}}</h1>
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
		},
		methods: {
			// 声明组件对应事件的函数 
			tap1 : function(){
				 console.log(1);
				 // 使用emit函数将组件的时间传递给调用者，第一个参数是事件名称，第二个参数是事件携带的变量 
				 // 在父级组件上使用@事件名称的方式，监听子组件的tap1事件
				 this.$emit('tap1', this.title)
			}
		}
	}
</script>

<style>
</style>
