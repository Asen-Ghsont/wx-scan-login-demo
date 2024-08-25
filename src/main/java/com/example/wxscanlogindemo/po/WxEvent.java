package com.example.wxscanlogindemo.po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class WxEvent extends ApplicationEvent {
    private WxMessage wxMessage;

    public WxEvent(Object source, WxMessage wxMessage) {
        super(source);
        this.wxMessage = wxMessage;
    }
}
