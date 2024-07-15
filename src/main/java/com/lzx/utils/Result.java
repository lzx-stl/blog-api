package com.lzx.utils;

import java.util.HashMap;

public class Result extends HashMap<String, Object> {

    @Override
    public Result put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
