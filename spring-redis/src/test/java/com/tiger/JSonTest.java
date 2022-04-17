package com.tiger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Zenghu
 * @Date 2022/3/11
 * @Description
 * @Version: 1.0
 **/
public class JSonTest {

    public static void main(String[] args) {
        Employee employee = new Employee(23);

        //String jsonString = "{\"enterDate\":\"2022-03-11\",\"id\":0}";

        String jsonString = "{}";
        String s = JSON.toJSONString(employee);

        System.out.println(s);


        Employee employee1 = JSON.parseObject(jsonString, Employee.class);
        System.out.println(employee1);

        ParserConfig config = new ParserConfig();




    }

}

@JSONType(parseFeatures = {Feature.IgnoreNotMatch}, serialzeFeatures = {SerializerFeature.NotWriteDefaultValue})
class Employee implements Serializable {

    private int id =12;
    @JSONField(format = "yyyyMMdd")
    private Date enterDate;
    private String job;
    private Person person = new Person();

    private Map<String, String> map = new HashMap<>();

    public Employee(int id) {
        //enterDate = Calendar.getInstance().getTime();
        this.id = id;
        System.out.println("init");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}

class Person {

    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
