package com.itheima.web.controller.loginAction;

import com.alibaba.fastjson.JSON;
import com.itheima.domain.wechat.WeChatInfo;
import com.itheima.web.base.BaseController;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
public class loginActionController extends BaseController {

    @RequestMapping(value = "/WxLogin", method = RequestMethod.GET)
    public void CodeLogin() throws IOException {
        String code = request.getParameter("code");
        try {
            String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
                    "wx3bdb1192c22883f3" + "&secret=" + "db9d6b88821df403e5ff11742e799105" + "&code=" + code + "&grant_type=authorization_code";
            //创建HttpClient对象
            CloseableHttpClient client = HttpClients.createDefault();
            //创建get请求
            HttpGet get = new HttpGet(tokenUrl);
            //发送get请求
            CloseableHttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity, "utf-8");
            System.out.println(json);
            //封装
            WeChatInfo weChatInfo = JSON.parseObject(json, WeChatInfo.class);
            System.out.println(weChatInfo);
            //自己的业务逻辑

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("微信扫码登录异常");
        }
    }
}
