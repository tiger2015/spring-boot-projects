package com.tiger.kafka.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.SerializerFactory;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.apache.kafka.common.serialization.Serializer;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author Zenghu
 * @Date 2022年04月05日 16:53
 * @Description
 * @Version: 1.0
 **/
public class KryoKafkaSerializer<T> implements Serializer<T> {
    private static Logger LOG = LoggerFactory.getLogger(KryoKafkaSerializer.class);
    public static final String SERIALIZER_KEY_TYPE = "key-type";

    public static final String SERIALIZER_VALUE_TYPE = "value-type";

    private static final ThreadLocal<Kryo> kryoThreadLocal = new ThreadLocal<Kryo>() {

        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.setReferences(false);
            kryo.setRegistrationRequired(false);
            // 设置此选项会导致ArrayList出现NullPointException
            //kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            // 如果没有默认的构造器，会自动生成构造器
            kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
            SerializerFactory.TaggedFieldSerializerFactory defaultSerializerFactory = new SerializerFactory.TaggedFieldSerializerFactory();
            defaultSerializerFactory.getConfig().setReadUnknownTagData(true);
            kryo.setDefaultSerializer(defaultSerializerFactory);
            return kryo;
        }
    };

    private static final ThreadLocal<Output> outPutThreadLocal = new ThreadLocal<Output>() {
        @Override
        protected Output initialValue() {
            Output output = new Output(4096, -1);
            return output;
        }
    };

    private Class cls;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Object objType = isKey ? configs.get(SERIALIZER_KEY_TYPE) : configs.get(SERIALIZER_VALUE_TYPE);
        if (objType instanceof String) {
            String clsName = (String) objType;
            try {
                cls = Class.forName(clsName);
                Kryo kryo = kryoThreadLocal.get();
                kryo.register(cls);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null) {
            return null;
        }
        Kryo kryo = kryoThreadLocal.get();
        Output output = outPutThreadLocal.get();
        // 如果此处使用writeObjectOrNull, 则在反序列化时必须使用readObjectOrNull
        // 否则反序列化报错
        try {
            kryo.writeObjectOrNull(output, data, cls);
            byte[] buffer = output.toBytes();
            output.flush();
            return buffer;
        } catch (Exception e) {
            LOG.error("serialize fail", e);
        } finally {
            output.reset();
        }
        return null;
    }
}
