package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

public class WebUtils {
    /**
     * 把Map中的值注入到JavaBean中
     * @param value
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBean(Map value , T bean ){
        try {
            System.out.println("注入之前：" + bean);
            /**
             * 把所有请求的参数都注入到bean对象中
             */
            DateConverter converter = new DateConverter();
            converter.setPattern(new String("yyyy-MM-dd HH:mm:ss.S"));
            ConvertUtils.register(converter, Date.class);
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换成为 int 类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
