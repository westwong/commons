package com.k2futrue.commons.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author west
 * @date 2018-5-17
 * jsonUtil
 */
public class JsonUtil {
    private static final String ERROR_CODE = "errorCode";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";

    public static final int SUCCESS = 200;//操作成功
    public static final int ERROR = 500;//运行时错误
    public static final int NO_LOGIN = 401;//没有登录

    /**
     * 当传入异常时
     */
    public static JSONObject setJson(Integer errorCode, Throwable t) {
        JSONObject json = new JSONObject();
        json.put(ERROR_CODE, errorCode);
        json.put(MESSAGE, t.getMessage());
        json.put(DATA, null);
        return json;
    }

    /**
     * 当传入数据时
     */
    public static JSONObject setJson(Object variables) {
        JSONObject json = new JSONObject();
        json.put(ERROR_CODE, SUCCESS);
        json.put(MESSAGE, "");
        json.put(DATA, variables);
        return json;
    }

    /**
     * 自定义json
     */
    public static JSONObject setJson(int errorCode, String message, Object data) {
        JSONObject json = new JSONObject();
        json.put(ERROR_CODE, errorCode);
        json.put(MESSAGE, message);
        json.put(DATA, data);
        return json;

    }

    /**
     * 传入错误码
     */
    public static JSONObject setJson(int errorCode) {
        JSONObject json = new JSONObject();
        json.put(ERROR_CODE, errorCode);
        json.put(MESSAGE, "");
        json.put(DATA, null);
        return json;

    }

    /**
     * 传入错误码和信息
     */
    public static JSONObject setJson(int errorCode, String message) {
        JSONObject json = new JSONObject();
        json.put(ERROR_CODE, errorCode);
        json.put(MESSAGE, message);
        json.put(DATA, null);
        return json;

    }

    public static JSONObject setJson() {
        JSONObject json = new JSONObject();
        json.put(ERROR_CODE, SUCCESS);
        json.put(MESSAGE, "success");
        json.put(DATA, null);
        return json;

    }
}