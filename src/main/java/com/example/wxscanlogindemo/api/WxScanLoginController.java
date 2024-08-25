package com.example.wxscanlogindemo.api;

import com.example.wxscanlogindemo.po.Result;
import com.example.wxscanlogindemo.service.WxScanLoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxScanLoginController {
    private final WxScanLoginService wxScanLoginService;

    public WxScanLoginController(WxScanLoginService wxScanLoginService) {
        this.wxScanLoginService = wxScanLoginService;
    }

    @GetMapping("generateQRCodeTicket")
    public Result<String> generateQCCodeTicket() throws Exception {
        return Result.ok(wxScanLoginService.generateQRCodeTicket());
    }

    @GetMapping("checkQRCodeState")
    public Result<String> checkQRCodeState(@RequestParam String ticket) {
        return Result.ok(wxScanLoginService.checkQRCodeResult(ticket));
    }
}
