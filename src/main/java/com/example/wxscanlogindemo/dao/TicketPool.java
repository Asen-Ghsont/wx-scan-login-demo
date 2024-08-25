package com.example.wxscanlogindemo.dao;

import com.example.wxscanlogindemo.po.TicketState;

public interface TicketPool {
    boolean addTicket(String ticket);
    boolean changeTicketState(String ticket, TicketState state);
    TicketState getTicketState(String ticket);
}
