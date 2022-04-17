package com.tiger.springboot.mybatis;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.SerializerFactory;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.util.Arrays;

/**
 * @Author Zenghu
 * @Date 2022年04月05日 22:28
 * @Description
 * @Version: 1.0
 **/
public class Test2 {

    public static void main(String[] args) {
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        //
        kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
        SerializerFactory.TaggedFieldSerializerFactory defaultFactory = new SerializerFactory.TaggedFieldSerializerFactory();
        defaultFactory.getConfig().setReadUnknownTagData(true);
        kryo.setDefaultSerializer(defaultFactory);

        Output output = new Output(1024,-1);
        MyList myList = new MyList(2);

        kryo.writeObjectOrNull(output, myList, MyList.class);
        byte[] bytes = output.toBytes();
        System.out.println(Arrays.toString(bytes));

        byte[] buffer = new byte[]{1, 4, 3, 3, 1, 97, 100, -28, 1, 10, 0, 0, 0, 0, 0, 0, 0, 64, 2, 1, 0, 91, -55, 1, 3, 0, 0};
        Input input = new Input();
         input.setBuffer(buffer);
       //input.setBuffer(new byte[1]);
        //input.setLimit(bytes.length);
        //input.setTotal(bytes.length);
       //input.setInputStream(new ByteArrayInputStream(bytes));

        //input.readVarLong(true);

        //Long aLong = kryo.readObject(input, Long.class);


        MyList myList1 = kryo.readObjectOrNull(input, MyList.class);

        System.out.println(myList1);
    }

}
