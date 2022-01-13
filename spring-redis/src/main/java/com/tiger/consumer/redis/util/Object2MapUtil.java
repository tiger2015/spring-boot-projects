package com.tiger.consumer.redis.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class Object2MapUtil {


    /**
     * 通过反射
     * 需要解决：复杂对象
     * POJO对象属性只包含基本的数据类型以及包装类型、String
     *
     * @param object
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> object2Map(Object object) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = object.getClass();
        // 包含所有访问权限的
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            String name = field.getName();
            Object o = field.get(object);
            map.put(name, o);
        }
        return map;
    }


    public static <T> T map2Object(Map<Object, Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        if (map == null) {
            throw new NullPointerException();
        }
        T instance = clazz.newInstance();
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            if (map.get(name) != null) {
                field.set(instance, map.get(name));
            }
        }
        return instance;
    }

    /**
     * 通过 fastjson
     *
     * @param map
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T mapToObjectByJson(Map<Object, Object> map, Class<T> cls) {
        String s = JSONObject.toJSONString(map);
        return JSONObject.parseObject(s, cls);
    }

    public static <T> Map<String, Object> objectToMapByJson(T t, Class<T> cls) {
        String s = JSONObject.toJSONString(t, SerializerFeature.NotWriteDefaultValue);
        return JSONObject.parseObject(s);
    }

}
