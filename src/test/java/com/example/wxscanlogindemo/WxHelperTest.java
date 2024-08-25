package com.example.wxscanlogindemo;

import com.example.wxscanlogindemo.util.WxHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WxHelperTest {

    @Autowired
    private WxHelper wxHelper;

    @Test
    public void testGetTicket() throws Exception {
        assert wxHelper.getQRCodeTicket(123) != null;
    }
}
