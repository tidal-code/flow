package com.tidal.flow.data;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExceptionData {
    private ExceptionData() {
    }

    private static final ThreadLocal<Map<String, Object>> EXCEPTION_DATA = ThreadLocal.withInitial(LinkedHashMap::new);

    public static void addExceptionData(String key, Object value){
        EXCEPTION_DATA.get().put(key, value);
    }

    public static Object getExceptionData(String key){
        return EXCEPTION_DATA.get().get(key);
    }

    public static Map<String, Object> getExceptionDataMap(){
        return EXCEPTION_DATA.get();
    }

    public static void removeExceptionData(){
        EXCEPTION_DATA.remove();
    }
}
