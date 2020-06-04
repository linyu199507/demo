package com.chinaweal.sdcs.crcs.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 请求数据处理
 * Created by chents on 2019/8/21.
 */
public class RequestUtil {

    /**
     * @param c   传入Entity类型
     * @param obj obj需要有get(Object key)方法. 常见类型Map / JSONObject
     *            key的值要和model的字段名一致.value的值要和model的字段类型一致
     * @return
     */

    public static Object mapToEntity(Class c, Object obj) {
        Class<?> aClass = obj.getClass();
        if ("java.util.HashMap".equals(aClass.getName())) {
            aClass = aClass.getSuperclass();
        }
        try {
            Method getMethod = aClass.getDeclaredMethod("get", Object.class);
            Object o = c.newInstance();
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                if (!Modifier.isFinal(field.getModifiers())) {
                    String fieldName = field.getName();
                    if (fieldName.equalsIgnoreCase("lauptime")) continue;
                    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method setMethod = c.getDeclaredMethod(methodName, field.getType());
                    Object value = getMethod.invoke(obj, fieldName);
                    if (value != null) {
                        String type = field.getType().getName();
                        if ("java.util.HashMap".equals(type)) {
                            value = mapToEntity(field.getType(), value);
                            setMethod.invoke(o, value);
                        } else if ("java.lang.Integer".equals(type)) {
                            setMethod.invoke(o, Integer.valueOf(StringUtils.isNotEmpty(value.toString()) ? value.toString() : "0"));
                        } else if ("java.math.BigDecimal".equals(type)) {
                            setMethod.invoke(o, new BigDecimal(StringUtils.isNotEmpty(value.toString()) ? value.toString() : "0"));
                        } else if ("java.sql.Date".equals(type) || "java.util.Date".equals(type)) {
                            if (value instanceof String) {
                                if (StringUtils.isNotEmpty((String) value)) {
                                    setMethod.invoke(o, Date.valueOf(value.toString()));
                                }
                            } else if (value instanceof Long) {
                                setMethod.invoke(o, new Date(((Long) value).longValue()));
                            }
                        } else {
                            setMethod.invoke(o, value);
                        }
                    }
                }
            }
            return o;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object mapToEntityTwo(Class c, Object obj) {
        Class<?> aClass = obj.getClass();
        if ("java.util.HashMap".equals(aClass.getName())) {
            aClass = aClass.getSuperclass();
        }
        try {
            Method getMethod = aClass.getDeclaredMethod("get", Object.class);
            Object o = c.newInstance();
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                if (!Modifier.isFinal(field.getModifiers())) {
                    String fieldName = field.getName();
                    if (fieldName.equalsIgnoreCase("lauptime")) continue;
                    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method setMethod = c.getDeclaredMethod(methodName, field.getType());
                    Object value = getMethod.invoke(obj, fieldName);
                    if (value != null) {
                        String type = field.getType().getName();
                        if ("java.util.HashMap".equals(type)) {
                            value = mapToEntity(field.getType(), value);
                            setMethod.invoke(o, value);
                        } else if ("java.lang.Integer".equals(type)) {
                            setMethod.invoke(o, Integer.valueOf(StringUtils.isNotEmpty(value.toString()) ? value.toString() : "0"));
                        } else if ("java.math.BigDecimal".equals(type)) {
                            setMethod.invoke(o, new BigDecimal(StringUtils.isNotEmpty(value.toString()) ? value.toString() : "0"));
                        } else if ("java.sql.Date".equals(type)) {
                            if (value instanceof String) {
                                if (StringUtils.isNotEmpty((String) value)) {
                                    setMethod.invoke(o, Date.valueOf(value.toString()));
                                }
                            } else if (value instanceof Long) {
                                setMethod.invoke(o, new Date(((Long) value).longValue()));
                            }
                        } else if ("java.util.Date".equals(type)) {
                            if (value instanceof String) {
                                if (StringUtils.isNotEmpty((String) value)) {
                                    java.util.Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) value);
                                    setMethod.invoke(o,parse );
                                }
                            } else if (value instanceof Long) {
                                setMethod.invoke(o, new java.util.Date(((Long) value).longValue()));
                            }
                        }else {
                            setMethod.invoke(o, value);
                        }
                    }
                }
            }
            return o;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
