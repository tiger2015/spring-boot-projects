package com.tiger.springboot.mybatis;

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer;

import java.time.LocalDate;

/**
 * @Author Zenghu
 * @Date 2022年04月06日 21:26
 * @Description
 * @Version: 1.0
 **/
public class MyList {

    //@TaggedFieldSerializer.Tag(value = 1)
    //private Double d = 0D;

    @TaggedFieldSerializer.Tag(value = 2)
    private int[] g;

    @TaggedFieldSerializer.Tag(value = 3)
    private String add = "add";

    @TaggedFieldSerializer.Tag(value = 4)
    private LocalDate date = LocalDate.now();


    public MyList(int size) {
        g = new int[size];
       // d = 2D;
    }

}
