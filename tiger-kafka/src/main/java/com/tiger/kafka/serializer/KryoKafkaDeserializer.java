package com.tiger.kafka.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.SerializerFactory;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author Zenghu
 * @Date 2022年04月05日 17:05
 * @Description
 * @Version: 1.0
 **/
@Slf4j
public class KryoKafkaDeserializer<T> implements Deserializer<T> {

    private static final Logger LOG = LoggerFactory.getLogger(KryoKafkaDeserializer.class);
    public static final String DESERIALIZER_KEY_TYPE = "key-type";

    public static final String DESERIALIZER_VALUE_TYPE = "value-type";

    private static final ThreadLocal<Kryo> kryoThreadLocal = new ThreadLocal<Kryo>() {

        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.setReferences(false);
            kryo.setRegistrationRequired(false);
            kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
            SerializerFactory.TaggedFieldSerializerFactory defaultSerializerFactory = new SerializerFactory.TaggedFieldSerializerFactory();
            defaultSerializerFactory.getConfig().setReadUnknownTagData(true);
            kryo.setDefaultSerializer(defaultSerializerFactory);
            return kryo;
        }
    };

    private static final ThreadLocal<Input> inputThreadLocal = new ThreadLocal<Input>() {
        @Override
        protected Input initialValue() {
            Input input = new Input(4096);
            return input;
        }
    };

    private Class<?> cls;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Object objType = isKey ? configs.get(DESERIALIZER_KEY_TYPE) : configs.get(DESERIALIZER_VALUE_TYPE);
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
    public T deserialize(String topic, byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        try {
            Kryo kryo = kryoThreadLocal.get();
            Input input = inputThreadLocal.get();
            input.setBuffer(data);
            Object o = kryo.readObjectOrNull(input, cls);
            return (T) o;
        } catch (Exception e) {
            LOG.error("deserialize fail", e);
        }
        return null;
    }
}
