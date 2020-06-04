package com.chinaweal.sdcs.crcs.statistical.common.util;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: NaJim
 * @Date: 2019/9/27
 */
public class GetPropertiesValue {

    public static String getByKey(String propertiesFile ,String key){
    Properties props = new Properties();
    InputStream in = null;
		try {
        in = Resources.getResourceAsStream("properties/"+propertiesFile);
        props.load(in);
        return props.getProperty(key);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

		return " ";
}

}
