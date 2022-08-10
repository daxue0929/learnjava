//package com.daxue.applet;
//
//import com.alibaba.fastjson.JSONObject;
//import com.oracle.javafx.jmx.json.JSONException;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//public class ExcelUtils {
//
//
//    /**
//     * 方法说明：根据filePath获取文件，根据传入的开始行和结束行，获取文件中的数据存到传入的T类型的list中返回。
//     * 注意：
//     * 1.T的字段名称必须和Excel中的列按照顺序一一对应才能正确获取到数据。
//     * 2.该方法只操作Excel中的第一个sheet。
//     * 3.只能读取文本，图片什么的没考虑。
//     * 4.取出的数据包含头（startRow）不包含尾(endRow)。
//     *
//     * @param filePath
//     *            文件路径
//     * @param startRow
//     *            数据开始行
//     * @param endRow
//     *            数据结束行
//     * @param clazz
//     *            包装返回数据用的Class对象。
//     * @return
//     * @throws Exception
//     */
//    public static <T> List<T> getBeanFromExcel(String filePath, int startRow, int endRow, Class<T> clazz) {
//        Sheet sheet = getSheet(filePath);
//        Method[] methods = getMethods(clazz);
//        int lastRowNum = sheet.getLastRowNum();
//        startRow = startRow < 1 ? 1 : startRow;
//        endRow = endRow > lastRowNum ? lastRowNum : endRow;
//        int coloumNums = sheet.getRow(0).getPhysicalNumberOfCells();
//
//        return getResult(startRow, endRow, coloumNums, sheet, clazz, methods);
//    }
//
//    /**
//     * 导出excel 2019-9-3 09:53:00
//     * @param entityList 要导出的数据列表，
//     * 如果想要指定表中每个列的名字，就在实体类的字段上增加注解，
//     * 类似：@RabbitExcel(columnName="第一列")即可，不加注解则列名为字段名。
//     * 有时用户身份用0和1表示普通和会员，那么最终体现到excel里应该是普通或会员而不是0或1，
//     * 此时，用注解中的dict指定，示例：@RabbitExcel(columnName="用户状态",dict="{\"1\":\"正常\",\"0\":\"冻结\"}")
//     * @param path 文件保存的路径
//     * @param fileName 指定文件名，但是最终得到的文件会是 文件名+时间戳
//     */
//    public static <T> void exportExcel(List<T> entityList, String path, String fileName){
//        if(entityList == null || entityList.size() == 0){
//            return;
//        }
//
//        //获取实体类字段名
//        Class clazz = entityList.get(0).getClass();
//        Field[] fields = clazz.getDeclaredFields();
//
//        //创建一个webbook，对应一个Excel文件
//        HSSFWorkbook wb = new HSSFWorkbook();
//
//        //在webbook中添加一个sheet,对应Excel文件中的sheet
//        HSSFSheet sheet = wb.createSheet("sheet1");
//
//        //在sheet中添加表头第0行
//        HSSFRow row = sheet.createRow((int) 0);
//
//        //根据字段名设置表头
//        row.createCell(0).setCellValue("序号");
//        for(int i = 1;i<=fields.length;i++){
//            Field field = fields[i-1];
//            String columnName = field.getName();
//
//            RabbitExcel annotation = field.getAnnotation(RabbitExcel.class);
//            if(annotation != null && !"".equals(annotation.columnName())){
//                columnName = annotation.columnName();
//            }
//
//            row.createCell(i).setCellValue(columnName);
//        }
//
//        //获取字段的get方法
//        Method[] getMethods = getgetMethods(clazz);
//        HSSFCell cell = null;
//
//        //写入数据
//        for(int i = 0;i<entityList.size();i++){
//            row = sheet.createRow((int) i + 1);
//            T t = entityList.get(i);
//            cell = row.createCell(0);
//            cell.setCellValue(i+1);
//            for(int j = 1;j<=fields.length;j++){
//                try {
//                    cell = row.createCell(j);
//                    Object cellObj = getMethods[j-1].invoke(t, null);
//                    String cellValue = String.valueOf(cellObj);
//
//                    //如果是日期类型，那么就转换成常用格式
//                    if(cellObj instanceof Date){
//                        cellValue = getStrFromDateWithSecond((Date) cellObj);
//                    }
//
//                    //如果有dict注解，则将字段值，替换为字典对应字段
//                    Field field = fields[j-1];
//                    RabbitExcel annotation = field.getAnnotation(RabbitExcel.class);
//                    if(annotation != null){
//                        String dictStr = annotation.dict();
//                        if(!"".equals(dictStr)){
//                            JSONObject dictJsonObj;
//                            try {
//                                dictJsonObj = JSONObject.fromObject(dictStr);
//                                String dictValue = dictJsonObj.get(cellValue) == null ? null : dictJsonObj.getString(cellValue);
//                                cellValue = dictValue == null ? cellValue : dictValue;
//                            } catch (JSONException e) {
//                                System.out.println("警告：dict注解未生效，dict的值，要传json格式字符串，当前为："+dictStr);
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//                    cell.setCellValue(cellValue);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        //写到硬盘
//        try {
//            FileOutputStream fout = new FileOutputStream(path+fileName+System.currentTimeMillis()+".xls");
//            wb.write(fout);
//            fout.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String getStrFromDateWithSecond(Date date){
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return dft.format(c.getTime());
//    }
//
//    public static void main(String[] args) {
//        List<ExcelTestEntity> entityList = new ArrayList<ExcelTestEntity>();
//        ExcelTestEntity e1 = new ExcelTestEntity();
//        e1.setAge(10);
//        e1.setCreateTime(new Date());
//        e1.setName("rabbit兔子");
//        e1.setSalary(new BigDecimal("100"));
//        e1.setStatus(1);
//
//        ExcelTestEntity e2 = new ExcelTestEntity();
//        e2.setAge(10);
//        e2.setCreateTime(new Date());
//        e2.setName("rabbit兔子");
//        e2.setSalary(new BigDecimal("100"));
//        e2.setStatus(0);
//
//        entityList.add(e1);
//        entityList.add(e2);
//
//        String path = "C:\\temp\\";
//        String fileName = "Excel表格导出测试";
//
//        exportExcel(entityList, path, fileName);
//    }
//
//    /**
//     * 获取第一个工作簿
//     */
//    private static Sheet getSheet(String filePath) {
//        return getWorkBood(filePath).getSheetAt(0);
//    }
//
//
//    private static <T> List<T> getResult(int startRow, int endRow, int coloumNums, Sheet sheet, Class<T> clazz, Method[] methods) {
//        List<T> list = new ArrayList<T>();
//        for (int rowNum = startRow; rowNum < endRow; rowNum++) {
//            list.add((T) castDataToBean(sheet.getRow(rowNum), coloumNums, clazz, methods));
//        }
//        return list;
//    }
//
//    /**
//     * 获取所有字段的set方法
//     */
//    private static <T> Method[] getMethods(Class<T> clazz) {
//        return getGetOrSetMethods(clazz,"set");
//    }
//
//    /**
//     * 获取所有字段的get方法
//     */
//    private static <T> Method[] getgetMethods(Class<T> clazz){
//        return getGetOrSetMethods(clazz,"get");
//    }
//
//    /**
//     * 获取所有字段的get或set方法
//     */
//    private static <T> Method[] getGetOrSetMethods(Class<T> clazz,String getOrSet){
//        //获取clazz的属性数量
//        Field[] fields = clazz.getDeclaredFields();
//        Method[] methods = new Method[fields.length];
//        String fieldName;
//        try {
//            for (int i = 0; i < fields.length; i++) {
//                fieldName = fields[i].getName();
//                fieldName = new StringBuilder(getOrSet).append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
//                if("set".equals(getOrSet)){
//                    methods[i] = clazz.getMethod(fieldName, fields[i].getType());
//                }else if("get".equals(getOrSet)){
//                    methods[i] = clazz.getMethod(fieldName);
//                }
//            }
//        } catch (NoSuchMethodException | SecurityException e) {
//            e.printStackTrace();
//        }
//        return methods;
//    }
//
//    private static <T> Object castDataToBean(Row row, int coloumNums, Class<T> clazz, Method[] methods) {
//        Object bean = null;
//        try {
//            bean = clazz.newInstance();
//            for (int i = 0; (i < coloumNums)&&(i < methods.length); i++) {
//                methods[i].invoke(bean, formatValue(methods[i].getParameterTypes()[0], getCellValue(row, i)));
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//        return bean;
//    }
//
//    private static Object formatValue(Class<?> clazz, String data) {
//        if(clazz == int.class || clazz == Integer.class){
//            return Integer.valueOf(data.replaceAll("\\.0", ""));
//        }else if(clazz == float.class || clazz == Float.class){
//            return Float.valueOf(data);
//        }
//        return data;
//    }
//
//    private static String getCellValue(Row row, int coloum) {
//        Cell cell = row.getCell(coloum);
//        cell.setCellType(Cell.CELL_TYPE_STRING);//数字日期什么的，都当作String取出来。
//        return cell.getStringCellValue();
//    }
//
//    private static Workbook getWorkBood(String filePath) {
//        Workbook wb = null;
//        InputStream stream = null;
//        try {
//            stream = new FileInputStream(filePath);
//            //初始化workbook
//            //获取文件类型
//            String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
//            return fileType.equals("xls") ? new HSSFWorkbook(stream) : fileType.equals("xlsx") ? new XSSFWorkbook(stream) : null;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                stream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return wb;
//
//    }
//
//
//}
