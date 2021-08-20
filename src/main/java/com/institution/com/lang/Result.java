package com.institution.com.lang;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenguo
 */
@Data
public class Result implements Serializable {
    /**
     * code 200 正常操作
     * 非200异常操作
     * */
    private int code;
    private String msg;
    private Object data;

    public static Result success(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result success(Object data){
        return success(200, "success", data);
    }

    public static Result failed(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result failed(String msg){
        return failed(400, msg, null);
    }

}
