package com.k2futrue.commons.util;

import java.util.Map;

/**
 * @author West
 * @date create in 2019/10/24
 */
public class Message {

    public String error;

    public int status;

    public Object message;

    public Message (Map map){
        this.error = (String) map.get("error");
        this.status = (Integer) map.get("status");
        this.message = map.get("message");
    }
    public Message(){}

    public boolean succ(){
        return  RespBuilder.SUCCESS == status;
    }
}