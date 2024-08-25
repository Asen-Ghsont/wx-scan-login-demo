package com.example.wxscanlogindemo.po;

import lombok.Data;

@Data
public class Result<E> {
    private E data;
    private int code;
    private String msg;

    private Result() {
    }

    public static <E> Result<E> ok(E data, String msg) {
        Result<E> ans = new Result<>();
        ans.setData(data);
        ans.setCode(200);
        ans.setMsg(msg);
        return ans;
    }

    public static <E> Result<E> ok(E data) {
        return ok(data, "请求成功");
    }

    public static <E> Result<E> fail() {
        Result<E> ans = new Result<>();
        ans.setCode(500);
        ans.setMsg("请求失败");
        return ans;
    }
}
