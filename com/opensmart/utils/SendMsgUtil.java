package com.opensmart.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SendMsgUtil {

	//产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static final String accessKeyId = "LTAI7gTYuEQTIFwV";
    private static final String accessKeySecret = "85wMUPhZNb1iArp31sUSBf9wTCeTyG";
	
    public static String getMessageData(String phoneNum,String msg){
    	//可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        try {
            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			SendSmsResponse response= sendMessage(phoneNum,acsClient);;
			/*if(msg==null){
				response=sendMessage(phoneNum,acsClient);
			}else{
				response=sendMessage(phoneNum,acsClient,msg);
			}*/
			if(response.getCode() != null && response.getCode().equals("OK")){
				System.out.println("请求成功");
	            /*QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(phoneNum,response.getBizId(),acsClient);
	            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs()){
	            	String identifyCode=smsSendDetailDTO.getOutId();
	            	return identifyCode;
	            }*/
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
    }

    /**
     * 发送细节
     * @param bizId
     * @param acsClient 
     * @return
     * @throws ClientException 
     * @throws  
     */
	public static QuerySendDetailsResponse querySendDetails(String phoneNum, String bizId, IAcsClient acsClient) throws ClientException {
		//组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(phoneNum);
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
	}

	/**
	 * 发送短信
	 * @param phoneNum
	 * @param acsClient
	 * @return
	 * @throws ServerException
	 * @throws ClientException
	 */
	private static SendSmsResponse sendMessage(String phoneNum,IAcsClient acsClient) throws ClientException {
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
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"256985\"}");
		
		request.setTemplateParam("{\"code\":\""+charValue+"\"}");
        
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(charValue);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
	}
	
	/**
	 * 发送短信
	 * @param phoneNum
	 * @param acsClient
	 * @return
	 * @throws ServerException
	 * @throws ClientException
	 */
	public static SendSmsResponse sendMessage(String phoneNum,IAcsClient acsClient,String password) throws ClientException {
		//组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNum);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("开睿科技os");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_129470195");
        
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"256985\"}");
		
		request.setTemplateParam("{\"code\":\""+password+"\"}");
        
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(password);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
	}
	
	
}
