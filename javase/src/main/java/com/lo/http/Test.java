package com.lo.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试http post并发请求时线程死循环问题。
 * 公司的代码调用tsdb使用post请求时，发现再并发访问时，后访问的线程回出现socket超时，重试后正常返回。
 * 但回家用mac测试自己springboot暴露的post方法却没有什么问题。
 * 前后的区别是请求必须是json，请求时需要在header上设置httpPost.setHeader("Content-type", "application/json");
 * 但请求tsdb的post请求不需要。
 * 并且自己sprintboot接口不关闭response，或者使用默认的httpClient也多不会超时。
 * 周一回公司试一下设置header，不行再用get请求，实在不行可能是tsdb设置了并发量？
 */
@Slf4j
public class Test {

    public static void main(String[] args) throws InterruptedException {
        new Test().test();
    }
    public void test() throws InterruptedException {
        long start = System.currentTimeMillis();
        log.info("---------###  start {}", start);
        for(int i=0;i<100;i++){
            int finalI = i;
            this.invoke(true,true, i);
            //Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        log.info("---------###  spent {} s", (end-start)/1000);
        Thread.sleep(60000);
        System.out.println("ooooooooooooooooover");
    }


    private void doGet(){
        String putUrl = "http://www.baidu.com";
        //String opentsdbDataPointListJsonString = JSONArray.toJSONString(opentsdbQuery);
        String ret = HttpClientUtil.doGet(putUrl,null, "utf-8");
        //String ret = HttpClientUtil.doPostWithJson(putUrl,  postString, null);
        System.out.println(ret);
    }
    private void doPost(){
        String putUrl = "http://localhost:8080/post";
        final MyRequestBody myRequestBody = new MyRequestBody();myRequestBody.bodyParam="test";
        String myRequestBodyListJsonString = JSONArray.toJSONString(myRequestBody);
        //String ret = HttpClientUtil.doPost(putUrl,null, "[{\"bodyParam\":\"1\"}]","utf-8");
        String ret = HttpClientUtil.doPost(putUrl,null, JSON.toJSONString(myRequestBody),"utf-8");
        //String ret = HttpClientUtil.doPostWithJson(putUrl,  postString, null);
        log.info("ret:{}",ret);
    }

    @Data
    private static class MyRequestBody{
        String bodyParam;
    }

    private <T> void invoke(boolean invokePost, boolean async,T tag){
        if(async){

            Thread thread = new Thread(()->{
                this.doGetOrPost(invokePost);
                log.info("@@@@@@@@@{}", tag);
            });
            thread.start();
        }else {
            this.doGetOrPost(invokePost);
        }
    }

    private void doGetOrPost(boolean invokePost){
        if(invokePost){
            this.doPost();
        }else {
            this.doGet();
        }
    }


}