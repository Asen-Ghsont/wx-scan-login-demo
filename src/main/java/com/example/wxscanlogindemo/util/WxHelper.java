package com.example.wxscanlogindemo.util;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WxHelper {
    @Data
    static class TokenInfo {
        public String accessToken;
        public Long expireTime;
    }

    private static TokenInfo tokenInfo;
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String APPID = "wx27ce155b28b8ecc9";
    private static final String APP_SECRET = "f769d4fa1dc414769dd2162548363590";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APP_SECRET;
    private static final String QRCODE_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    @Data
    static class AccessTokenRes {
        @JsonAlias("access_token")
        public String accessToken;
        @JsonAlias("expires_in")
        public Long expireIn;
    }

    private String getAccessToken() throws Exception {
        long now = System.currentTimeMillis();
        boolean needRefresh = (tokenInfo == null || now > tokenInfo.expireTime);
        if (needRefresh) {
            AccessTokenRes result = restTemplate.getForObject(ACCESS_TOKEN_URL, AccessTokenRes.class);
            if (result == null) throw new Exception("request access token error");
            System.out.println("request access token: " + result.accessToken);
            tokenInfo = new TokenInfo();
            tokenInfo.accessToken = result.accessToken;
            tokenInfo.expireTime = result.expireIn * 1000 + now;
        }
        return tokenInfo.accessToken;
    }

    @Data
    static class QRCodeRes {
        @JsonAlias("ticket")
        public String ticket;
        @JsonAlias("expire_seconds")
        public Long expireTime;
        @JsonAlias("url")
        public String url;
    }

    public String getQRCodeTicket(int sceneId) throws Exception {
        String url = QRCODE_TICKET_URL + getAccessToken();
        String requestBody = "{\"expire_seconds\": 60, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + sceneId + "}}}";
        QRCodeRes res = restTemplate.postForObject(url, requestBody, QRCodeRes.class);
        if (res == null) throw new Exception("request QRCode ticket error");
        System.out.println("request QRCode ticket: " + res.ticket);
        return res.ticket;
    }
}
