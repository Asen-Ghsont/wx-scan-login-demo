package com.example.wxscanlogindemo.service;

import com.example.wxscanlogindemo.dao.TicketPool;
import com.example.wxscanlogindemo.po.TicketState;
import com.example.wxscanlogindemo.po.WxEvent;
import com.example.wxscanlogindemo.po.WxMessage;
import com.example.wxscanlogindemo.util.WxHelper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WxScanLoginService {
    private static final int SCENE_ID = 123;
    private final WxHelper wxHelper;
    private final TicketPool ticketPool;

    public WxScanLoginService(TicketPool ticketPool, WxHelper wxHelper) {
        this.ticketPool = ticketPool;
        this.wxHelper = wxHelper;
    }

    public String generateQRCodeTicket() throws Exception {
        String ticket = wxHelper.getQRCodeTicket(SCENE_ID);
        ticketPool.addTicket(ticket);
        return ticket;
    }

    public String checkQRCodeResult(String ticket) {
        return ticketPool.getTicketState(ticket).toString();
    }

    @EventListener(WxEvent.class)
    public void listenWxEvent(WxEvent event) throws Exception {
        WxMessage wxMessage = event.getWxMessage();
        if (wxMessage == null) throw new Exception("wxMessage is null");
        if (!Objects.equals(wxMessage.getEvent(), "SCAN") && !Objects.equals(wxMessage.getEvent(), "subscribe")) return;
        String ticket = wxMessage.getTicket();
        if (ticket == null) throw new Exception("ticket from wxMessage is null");
        ticketPool.changeTicketState(ticket, TicketState.SCANNED);
    }
}
