package com.tiger.springboot.mybatis;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Zenghu
 * @Date 2022/2/17
 * @Description
 * @Version: 1.0
 **/
public class Test {


    static{
        String logPath = System.getProperty("JM.LOG.PATH");
        System.out.println("log path:" + logPath);
    }

    public static void main(String[] args) {

        String configPath = "C:\\Users\\ZengHu\\Desktop\\config.yml";

        Constructor constructor = new Constructor(Config.class);

        TypeDescription typeDescription = new TypeDescription(Config.class);
       // typeDescription.putMapPropertyType("mapList",  String.class, ArrayList.class);
        List<String> list = new ArrayList<>();
       typeDescription.addPropertyParameters("mapList", String.class, list.getClass());
        constructor.addTypeDescription(typeDescription);

        Yaml yaml = new Yaml(constructor);

        try {
            Config config = yaml.load(new FileInputStream(configPath));
            System.out.println(config.list);
            // 泛型擦除
            List<String> testLs = new ArrayList<>();
            testLs.add("1234");
            Class<? extends List> lsClass = testLs.getClass();
            Method add = lsClass.getDeclaredMethod("add", Object.class);
            add.invoke(testLs, 12);
            System.out.println(testLs);
        } catch (FileNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }


    protected static class Config {

        private Map<String, List<String>> mapList;

        private List<String> list;

        public void setList(List<String> list) {
            this.list = list;
        }

        public void setMapList(Map<String, List<String>> mapList) {
            this.mapList = mapList;
        }
    }

}
