package com.carryless.utils.parse;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author  carryLess
 * @date    2018-04-08
 * @version 1.0
 * @desc    解析Properties文件
 */
public class ParseProperties {

    private ParseProperties(){}

    /**
     * 根据File(properties文件)，返回Map<Object,Object> map
     * @param file(properties文件)
     * @return 返回Map<Object,Object>类型的Map
     */
    public static Map<Object,Object> getMapByProperties(File file) throws IOException {
        Set<Map.Entry<Object, Object>> entries = getPropertiesByFile(file).entrySet();
        Map<Object,Object> resultMap = entries.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return resultMap;
    }

    /**
     * 根据Key获取properties文件中的value
     * @param file  properties文件
     * @param key   Key值
     * @return  String类型的value值，没有key时返回null
     */
    public static String getValueByKey(File file,String key) throws IOException {
        String result = null;
        result =  getPropertiesByFile(file).getProperty(key);
        return result;
    }

    /**
     * 根据File 获取Properties对象
     * @param file  properties文件
     * @return Properties
     * @throws IOException
     */
    public static Properties getPropertiesByFile(File file) throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }
}
