package com.example.wxscanlogindemo;

import com.example.wxscanlogindemo.dao.TicketPool;
import com.example.wxscanlogindemo.dao.impl.TicketPoolGuavaImpl;
import com.example.wxscanlogindemo.po.TicketState;
import org.junit.jupiter.api.Test;

public class TicketPoolTest {
    @Test
    public void test() {
        TicketPool ticketPool = new TicketPoolGuavaImpl();
        String testTicket = "111";
        assert ticketPool.getTicketState(testTicket) == TicketState.NOT_EXIST;

        assert ticketPool.addTicket(testTicket);
        assert ticketPool.getTicketState(testTicket) == TicketState.CREATED;

        assert ticketPool.changeTicketState(testTicket, TicketState.NOT_EXIST);
        assert ticketPool.getTicketState(testTicket) == TicketState.NOT_EXIST;

        assert ticketPool.changeTicketState(testTicket, TicketState.SCANNED);
        assert ticketPool.getTicketState(testTicket) == TicketState.SCANNED;
    }
}
