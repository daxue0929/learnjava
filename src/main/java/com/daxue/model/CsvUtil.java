package com.daxue.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author liujufu
 * @date 2018/8/27 13:56
 */
public class CsvUtil {

    /** 日志对象 **/
    private static final Logger logger = LoggerFactory.getLogger(CsvUtil.class);


    /**
     * 生成CSV文件
     * @param filePath 文件保存路径，例如：D:/temp/test.csv
     * @param beans 实体对象集合
     */
    public static <T> void createFile(String filePath, List<T> beans, Class<?> entity) {
        CsvWriter writer = null;
        try {
            // 创建文件对象
            File file = createFile(filePath);
            // 生成文件
//            writer =
            writer = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
            // 获取内容
//            List<String[]> contents = getStringArrayFromBeanNoTitle(beans, MaCsvModel.class);
            List<String[]> contents = getStringArrayFromBeanNoTitle(beans,entity);
            // 写入内容
            for (String[] each : contents) {
                writer.writeRecord(each);
            }
        } catch (Exception e) {
            logger.error("生成CSV文件失败", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static <T> void appendCsvFile(String filePath, List<String[]> contents) {
        CsvWriter writer = null;
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,true),"utf-8"),1024);
            writer = new CsvWriter(bufferedWriter, ',');
            for (String[] each : contents) {
                writer.writeRecord(each);
            }
        } catch (Exception e) {
            logger.error("生成CSV文件失败", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 读取CSV文件内容
     *
     * @param filePath 文件存放的路径，如：D:/csv/xxx.csv
     * @param bean 类类型
     * @return List<T>
     */
    public static <T> List<T> readFile(String filePath, Class<T> bean) {
        List<String[]> dataList = new ArrayList<String[]>();
        CsvReader reader = null;
        try {
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
//            reader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
            if (reader != null) {
                // 跳过表头，如果需要表头的话，这句可以忽略
                //reader.readHeaders();
                // 逐行读入除表头的数据
                while (reader.readRecord()) {
                    dataList.add(reader.getValues());
                }
                if (!dataList.isEmpty()) {
                    // 数组转对象
                    return getBeanFromStringArray(dataList, bean);
                }
            }
        } catch (Exception e) {
            logger.error("读取CSV文件失败", e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return Collections.emptyList();
    }

    public static List<String[]> readFile(String filePath) {
        List<String[]> dataList = new ArrayList<String[]>();
        CsvReader reader = null;
        try {
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
//            reader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
            if (reader != null) {
                // 跳过表头，如果需要表头的话，这句可以忽略
                //reader.readHeaders();
                // 逐行读入除表头的数据
                while (reader.readRecord()) {
                    dataList.add(reader.getValues());
                }
            }
        } catch (Exception e) {
            logger.error("读取CSV文件失败", e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return dataList;
    }

    public static List<String[]> readFile(File file) {
        List<String[]> dataList = new ArrayList<String[]>();
        CsvReader reader = null;
        try {
            reader = new CsvReader(new InputStreamReader(new FileInputStream(file),"utf-8"), ',');
            if (reader != null) {
                while (reader.readRecord()) {
                    dataList.add(reader.getValues());
                }
            }
        } catch (Exception e) {
            logger.error("读取CSV文件失败", e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return dataList;
    }

    /**
     * 删除该目录下所有文件
     * @param filePath 文件目录路径，如：d:/test
     */
    public static boolean deleteFiles(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    if (f.isFile() && f.delete()) {
                        logger.info("删除" + f.getName() + "文件成功");
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 删除单个文件
     * @param filePath 文件目录路径，如：d:/test
     * @param fileName 文件名称，如：110.csv
     */
    public static boolean deleteFile(String filePath, String fileName) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    if (f.isFile() && f.getName().equals(fileName)) {
                        return f.delete();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 泛型实体转换为数组
     * @param beans
     * @return List<String[]>
     */
    public static <T> List<String[]> getStringArrayFromBean(List<T> beans,Class<?> pojoClass) {
        List<String[]> result = new ArrayList<String[]>();
        Field[] declaredFields = PoiPublicUtil.getClassFields(pojoClass);
        List<Field> annoFields = new ArrayList<Field>();
        // 筛选出标有注解的字段
        for (Field field : declaredFields) {
            Excel anno = field.getAnnotation(Excel.class);
            if (anno != null) {
                annoFields.add(field);
            }
        }
        // 获取注解的值，即内容标题
        String[] title = new String[annoFields.size()];
        for (int i = 0; i < annoFields.size(); i++) {
            title[i] = annoFields.get(i).getAnnotation(Excel.class).name();
        }
        result.add(title);
        try {
            // 获取内容
            for (T t : beans) {
                String[] item = new String[annoFields.size()];
                int index = 0;
                for (Field field : annoFields) {
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = ReflectionUtils.findMethod(t.getClass(), methodName);
                    if (method != null) {
                        Object value = ReflectionUtils.invokeMethod(method, t);
                        if (value == null) {
                            item[index] = "";
                        } else {
                            item[index] = value.toString();
                        }
                    }
                    index ++;
                }
                result.add(item);
            }
        } catch (Exception e) {
            logger.info("实体对象转数组失败", e);
        }
        return result;
    }


    /**
     * 泛型实体转换为数组
     * 不要表头
     * @param beans
     * @param pojoClass
     * @param <T>
     * @return
     */
    public static <T> List<String[]> getStringArrayFromBeanNoTitle(List<T> beans,Class<?> pojoClass) {
        List<String[]> result = new ArrayList<String[]>();
        Field[] declaredFields = PoiPublicUtil.getClassFields(pojoClass);
        List<Field> annoFields = new ArrayList<Field>();
        // 筛选出标有注解的字段
        for (Field field : declaredFields) {
            Excel anno = field.getAnnotation(Excel.class);
            if (anno != null) {
                annoFields.add(field);
            }
        }

        try {
            // 获取内容
            for (T t : beans) {
                String[] item = new String[annoFields.size()];
                int index = 0;
                for (Field field : annoFields) {
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = ReflectionUtils.findMethod(t.getClass(), methodName);
                    if (method != null) {
                        Object value = ReflectionUtils.invokeMethod(method, t);
                        if (value == null) {
                            item[index] = "";
                        } else {
                            item[index] = value.toString();
                        }
                    }
                    index ++;
                }
                result.add(item);
            }
        } catch (Exception e) {
            logger.info("实体对象转数组失败", e);
        }
        return result;
    }

    public static String[] getHeaderFromBean(List beans,Class<?> pojoClass) {
        List<String[]> result = new ArrayList<String[]>();
        Field[] declaredFields = PoiPublicUtil.getClassFields(pojoClass);
        List<Field> annoFields = new ArrayList<Field>();
        // 筛选出标有注解的字段
        for (Field field : declaredFields) {
            Excel anno = field.getAnnotation(Excel.class);
            if (anno != null) {
                annoFields.add(field);
            }
        }
        // 获取注解的值，即内容标题
        String[] title = new String[annoFields.size()];
        for (int i = 0; i < annoFields.size(); i++) {
            title[i] = annoFields.get(i).getAnnotation(Excel.class).name();
        }
        return title;
    }

    public static <T> List<String[]> getStringListFromBean(List<T> beans,Class<?> pojoClass) {
        List<String[]> result = new ArrayList<String[]>();
        Field[] declaredFields = PoiPublicUtil.getClassFields(pojoClass);
        List<Field> annoFields = new ArrayList<Field>();
        // 筛选出标有注解的字段
        for (Field field : declaredFields) {
            Excel anno = field.getAnnotation(Excel.class);
            if (anno != null) {
                annoFields.add(field);
            }
        }
        try {
            // 获取内容
            for (T t : beans) {
                String[] item = new String[annoFields.size()];
                int index = 0;
                for (Field field : annoFields) {
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = ReflectionUtils.findMethod(t.getClass(), methodName);
                    if (method != null) {
                        Object value = ReflectionUtils.invokeMethod(method, t);
                        if (value == null) {
                            item[index] = "";
                        } else {
                            item[index] = value.toString();
                        }
                    }
                    index ++;
                }
                result.add(item);
            }
        } catch (Exception e) {
            logger.info("实体对象转数组失败", e);
        }
        return result;
    }


    /**
     * 数组转为对象集合
     * @param dataList
     * @param bean
     * @return List<T>
     */
    private static <T> List<T> getBeanFromStringArray(List<String[]> dataList, Class<T> bean) {
        List<T> list = new ArrayList<>();
        List<Map<String, Object>> titles = getTitles2(dataList);
        Map<String, Field> fields = getFields(bean);
        try {
            for (Map<String, Object> map : titles) {
                T t = bean.newInstance();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (fields.containsKey(entry.getKey())) {
                        Field field = fields.get(entry.getKey());
                        Class<?> valType = field.getType();
                        String fieldName = field.getName();
                        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Method method = ReflectionUtils.findMethod(bean, methodName, valType);
                        if (method != null) {
                            ReflectionUtils.invokeMethod(method, t, entry.getValue());
                        }
                    }
                }
                list.add(t);
            }
        } catch (Exception e) {
            logger.error("创建实体失败", e);
        }
        return list;
    }

    /**
     * 数组标题与值的对应关系
     * @param dataList
     * @return
     */
    private static <T> List<Map<String, String>> getTitles(List<String[]> dataList) {
        List<Map<String, String>> list = new ArrayList<>();
        String[] titles = dataList.get(0);
        dataList.remove(0);
        for (String[] values : dataList) {
            Map<String, String> titleMap = new HashMap<>();
            for (int i = 0; i < values.length; i++) {
                titleMap.put(titles[i], values[i]);
            }
            list.add(titleMap);
        }
        return list;
    }
    private static <T> List<Map<String, Object>> getTitles2(List<String[]> dataList) {
        List<Map<String, Object>> list = new ArrayList<>();
        String[] titles = dataList.get(0);
        dataList.remove(0);
        for (String[] values : dataList) {
            Map<String, Object> titleMap = new HashMap<>();
            for (int i = 0; i < values.length; i++) {
                titleMap.put(titles[i], (Object)values[i]);
            }
            list.add(titleMap);
        }
        return list;
    }

    /**
     * 注解名称与字段属性的对应关系
     *
     * @param clazz 实体对象类类型
     * @param <T> 泛型类型
     * @return Map<String,Field>
     */
    private static <T> Map<String, Field> getFields(Class<T> clazz) {
        Map<String, Field> annoMap = new HashMap<>();
        Field[] fileds = clazz.getDeclaredFields();
        for (Field filed : fileds) {
            Excel anno = filed.getAnnotation(Excel.class);
            if (anno != null) {
                // 获取name属性值
                if (StringUtils.isNotBlank(anno.name())) {
                    annoMap.put(anno.name(), filed);
                }
            }
        }
        return annoMap;
    }

    /**
     * 创建文件对象
     * @param filePath 文件路径，例如：temp/test.csv
     * @return File
     */
    private static File createFile(String filePath) {
        File file = null;
        try {
            // 创建文件目录
            file = new File(filePath.substring(0, filePath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 创建文件对象
            file = new File(filePath);
            if (!file.exists() && file.createNewFile()) {
                logger.info("创建文件对象成功");
            }
        } catch (IOException e) {
            logger.error("创建文件对象失败", e);
        }
        return file;
    }

}

