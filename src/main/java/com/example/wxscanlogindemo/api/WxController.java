package com.example.wxscanlogindemo.api;

import com.example.wxscanlogindemo.po.WxMessage;
import com.example.wxscanlogindemo.service.WxService;
import org.springframework.web.bind.annotation.*;

@RestController
public class WxController {
    final WxService wxService;

    public WxController(WxService wxService) {
        this.wxService = wxService;
    }

    /**
     * 暴露接口，用于连接微信服务器，校验防止被盗刷
     *
     * @param signature 根据“timestamp+nonce+微信平台配置的token”生成的签名
     * @param timestamp 时间戳
     * @param nonce     微信服务器提供的随机生成的字符串
     * @param echostr   微信服务器随机字符串
     * @return 正常返回echostr响应微信，其他情况不响应
     */
    @GetMapping("lalala")
    public String checkIsAvailable(
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("echostr") String echostr
    ) {
        return wxService.checkSignature(signature, timestamp, nonce, echostr);
    }

    /**
     * 暴露接口，用于接受微信服务器的事件通知，该接口可以加密，不加密存在被盗刷的风险
     * @param wxMessage 事件信息
     */
    @PostMapping("lalala")
    public void receiveWxMessage(@RequestBody WxMessage wxMessage) {
        wxService.receiveWxMessage(wxMessage);
    }
}
