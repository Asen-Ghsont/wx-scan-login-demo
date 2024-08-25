package com.example.wxscanlogindemo.dao.impl;

import com.example.wxscanlogindemo.dao.TicketPool;
import com.example.wxscanlogindemo.po.TicketState;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class TicketPoolGuavaImpl implements TicketPool {
    private static final int MAX_SIZE = 200;
    private static final long EXPIRE_TIME = 60;
    LoadingCache<String, TicketState> ticketStateMap = CacheBuilder.newBuilder()
            .maximumSize(MAX_SIZE)
            .expireAfterWrite(EXPIRE_TIME, TimeUnit.SECONDS)
            .build(new CacheLoader<>() {
                @Override
                public TicketState load(@NonNull String s) {
                    return TicketState.NOT_EXIST;
                }
            });

    /**
     * 添加ticket，并给出默认状态CREATED
     * @param ticket ticket
     * @return 假如添加失败，返回false
     */
    @Override
    public boolean addTicket(String ticket) {
        try {
            TicketState state = ticketStateMap.get(ticket);
            if (state != TicketState.NOT_EXIST) return false;
            ticketStateMap.put(ticket, TicketState.CREATED);
            return true;
        } catch (ExecutionException e) {
            return false;
        }
    }

    /**
     * 修改ticket状态
     * @param ticket ticket
     * @param state 需要转移的状态
     * @return 永远返回true
     */
    @Override
    public boolean changeTicketState(String ticket, TicketState state) {
        ticketStateMap.put(ticket, state);
        return true;
    }

    /**
     * 获取ticket状态
     * @param ticket ticket
     * @return 假如获取失败，返回null值
     */
    @Override
    public TicketState getTicketState(String ticket) {
        try {
            return ticketStateMap.get(ticket);
        } catch (ExecutionException e) {
            return null;
        }
    }
}
