package com.example.wxscanlogindemo.service;

import com.example.wxscanlogindemo.po.WxEvent;
import com.example.wxscanlogindemo.po.WxMessage;
import com.example.wxscanlogindemo.util.SpringUtil;
import org.springframework.stereotype.Service;

@Service
public class WxService {
    private static final String token = "lalala";

    public String checkSignature(String signature, String timestamp, String nonce, String echostr) {
        // 为简化代码，此处不做校验
        System.out.println("收到微信校验请求，echostr为：" + echostr);
        return echostr;
    }

    public void receiveWxMessage(WxMessage wxMessage) {
        SpringUtil.publishEvent(new WxEvent(this, wxMessage));
    }
}
