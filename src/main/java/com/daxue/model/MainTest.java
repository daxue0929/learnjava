package com.daxue.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author daxue0929
 * @date 2020/03/11
 **/
public class MainTest {

    public static List<Student> students = new ArrayList<>();
    public static List<Caurse> caurses = new ArrayList<>();
    public static String stuPath = "E:/test/student";  //文件保存路径
    public static String cauPath = "E:/test/caurse"; //文件保存路径

    public static void main(String[] args) {
        initFileHeader();
        initFromFile();
        PleaseChoosePattern();
    }
    private static void initFileHeader() {
//        students.add(new Student("id", "name", "sex", "cla"));
//        caurses.add(new Caurse("id", "name", "credit"));
    }

    private static void initFromFile() {
        students.addAll(CsvUtil.readFile(stuPath, Student.class));
        caurses.addAll(CsvUtil.readFile(cauPath, Caurse.class));
    }

    private static void PleaseChoosePattern() {

        Scanner cin = new Scanner(System.in);

        while (true) {
            System.out.println("请选择系统模式: 1录入模式, 2查询模式");
            String next = cin.next();
            switch (next) {
                case "1":
                    scannerConsole();
                    break;
                case "2":
                    selectConsole();
            }
        }
    }

    private static void selectConsole() {
        boolean isSelect = true;
        while (isSelect) {
            System.out.println("当前是信息查询模式: ");
            System.out.println("请输入1查询学生信息,2查询课程信息, 4退出当前模式");
            Scanner cin = new Scanner(System.in);
            String next = cin.next();
            switch (next) {
                case "1" :
                    selectStudent();
                    break;
                case "2" :
                    selectCause();
                    break;
                case "4" :
                    isSelect = false;
                    System.out.println("退出当前模式");
                    break;
            }
        }
    }

    public static void scannerConsole() {
        boolean isScan = true;
        while (isScan) {
            System.out.println("当前是信息录入模式: ");
            System.out.println("请输入1,2,3,4进入相关录入选项,0保存文件,1录入学生信息,2录入课程信息,3录入选课信息,4退出当前模式");
            Scanner cin = new Scanner(System.in);
            String next = cin.next();
            switch (next) {
                case "1":
                    System.out.println("请根据提示输入相应信息：");
                    scannerStudent();
                    break;
                case "2":
                    System.out.println("请根据提示输入相应信息：");
                    scannerCaurse();
                    break;
                case "3":
                    System.out.println("请根据提示输入相应信息：");
                    break;
                case "0":
                    writeFile();
                    System.out.println("保存三对象文件信息");
                case "4" :
                    isScan = false;
                    System.out.println("退出当前模式");
                    break;
            }
        }
    }

    private static void selectCause() {
        String caurseId = scan();
        caurses.forEach(item -> {
            if (item.getId() == caurseId) {
                System.out.println("当前课程信息为:" + item.toString());
            }
        });
    }

    private static void selectStudent() {
        System.out.print("请输入学生的学号:");
        String stuID = scan();
        students.forEach(item -> {
            if (item.getId().equals(stuID)) {
                System.out.println("当前学生信息为:" + item.toString());
            }
        });
    }

    public static void scannerStudent() {
        Student student = new Student();
        System.out.print("请输入学生学号: ");
        student.setId(scan());

        System.out.print("请输入学生姓名: ");
        student.setName(scan());

        System.out.print("请输入学生性别: ");
        student.setSex(scan());

        System.out.print("请输入学生班级: ");
        student.setCla(scan());
        System.out.println("---------本次学生信息录取完毕!--------");

        students.add(student);
        System.out.println("请输入1,2,3进入一级菜单,0保存文件");

    }
    public static void scannerCaurse() {
        Caurse caurse = new Caurse();
        System.out.print("请输入课程编号:");
        caurse.setId(scan());

        System.out.print("请输入课程名称:");
        caurse.setName(scan());

        System.out.print("请输入课程学分:");
        caurse.setCredit(scan());

        System.out.println("---------本次课程信息录取完毕!--------");
        caurses.add(caurse);

        System.out.println("请输入1,2,3进入一级菜单,0保存文件");
    }
    public static void writeFile() {
        CsvUtil.createFile(stuPath,students, Student.class);
        CsvUtil.createFile(cauPath, caurses, Caurse.class);
    }
    public static String scan() {
        Scanner cin = new Scanner(System.in);
        String next = cin.next();
        return next;
    }
}
