package com.lim.utils;

import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;

import static com.aliyun.teautil.Common.toJSONString;

@Component
public class AliSMSUtils {
    //https://help.aliyun.com/zh/sms/developer-reference/api-dysmsapi-2017-05-25-endpoint?spm=a2c4g.11186623.0.0.4a5d5f9fn3wPix
    public static void sendSM(String phone, String code) throws Exception {
        Config config = new Config()
                // 请确保代码运行环境配置了相应环境变量,可以与OSS的一致即可
                .setAccessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
                .setAccessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));

        // 配置 Endpoint，注意这个endpoint不同于OSS的endpoint
        //https://help.aliyun.com/zh/sms/developer-reference/api-dysmsapi-2017-05-25-endpoint?spm=a2c4g.11186623.0.0.4a5d5f9fn3wPix
        config.endpoint = "dysmsapi.aliyuncs.com";
        // 初始化请求客户端
        Client client = new Client(config);

        // 构造请求对象，请替换请求参数值
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("reggie外卖")
                .setTemplateCode("SMS_479830291")
                .setTemplateParam("{\"code\":\"" + code + "\"}"); // 替换为动态验证码，TemplateParam为序列化后的JSON字符串

        // 获取响应对象
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);

        // 响应包含服务端响应的 body 和 headers
        System.out.println(toJSONString(sendSmsResponse));
    }
}

