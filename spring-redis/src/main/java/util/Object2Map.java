package util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Object2Map {

    public static Map<String, Object> object2Map(Object object) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
//            Class<?> type = field.getType();
//            if(type.isPrimitive()){
//               // map.put(name,)
//                System.out.println(field.get(object));
//            }

//            System.out.println(type.isPrimitive());
//            System.out.println(name + ":" + type);
            map.put(name, field.get(object));
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

}
