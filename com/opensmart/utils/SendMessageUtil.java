package com.opensmart.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SendMessageUtil {

	//产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static final String accessKeyId = "LTAI7gTYuEQTIFwV";
    private static final String accessKeySecret = "85wMUPhZNb1iArp31sUSBf9wTCeTyG";
    
    private static IAcsClient getMessageData(String phoneNum){
    	//可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        try {
            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			return acsClient;
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public static String sendIdentifyCode(String phoneNum) throws ClientException {
    	IAcsClient acsClient=getMessageData(phoneNum);
    	//组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNum);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("开睿科技os");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_129470195");
        //生成六位验证码
        String charValue = "";
		for (int i = 0; i < 6; i++) {
			charValue = charValue + (int) (Math.random() * 9);
		}
		
		request.setTemplateParam("{\"code\":\""+charValue+"\"}");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(charValue);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")){
			System.out.println("请求成功");
		}
		return charValue;
	}
    
    public static String sendOnceUnlockCode(String phoneNum) throws ClientException{
    	IAcsClient acsClient=getMessageData(phoneNum);
    	//组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNum);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("开睿科技os");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_130911151");
        //生成六位验证码
        String charValue = "";
		for (int i = 0; i < 6; i++) {
			charValue = charValue + (int) (Math.random() * 9);
		}
		
		request.setTemplateParam("{\"code\":\""+charValue+"\"}");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(charValue);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")){
			System.out.println("请求成功");
		}
        return charValue;
    }
    
    
    public static String sendCoutomUnlockCode(String phoneNum,String startTime, String endTime) throws ClientException{
    	IAcsClient acsClient=getMessageData(phoneNum);
    	//组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNum);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("开睿科技os");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_130911151");
        //生成六位验证码
        String charValue = "";
		for (int i = 0; i < 6; i++) {
			charValue = charValue + (int) (Math.random() * 9);
		}
		request.setTemplateParam("{\"code\":\""+charValue+"\"}");
		request.setTemplateParam("{\"time\":\""+endTime+"\"}");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(charValue);
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")){
			System.out.println("请求成功");
		}
        return charValue;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
