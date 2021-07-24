package com.ioc.test.test02;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author daxue0929
 * @date 2021/07/19
 **/
@Slf4j
public class FactoryMethodDemo {
    public static void main(String[] args){
        Creator creator = new ConcreateCreator();

        //工厂方法模式,实例化了一个电子产品类
        Product electronicProduct = creator.factory(ConcreateProduct.class);
        electronicProduct.make();
    }
}

/**
 * 工厂方法模式的优点:
 *  良好的封装性,代码结构清晰.
 *  优秀的可扩展性.
 *  屏蔽产品类
 *  工厂方法模式是典型的解耦框架.
 *
 * 工厂方法模式的使用场景:
 *  工厂方法模式在项目中使用额非常频繁,在很多框架的代码中都可以发现工厂方法模式的应用,典型场景如下:
 *
 *  1.工厂方法是new一个对象的替代品,因此在所以需要生成对象的地方都可以使用,但是需要慎重考虑是否需要增加一个工厂类进行管理,增加代码的复杂度.
 *    需要灵活的可扩展的框架时,可以考虑采用工厂方法模式.
 *
 *  2.工厂方法模式可以用在异构项目中,例如,通过WebService与一个非Java项目交互,虽然WebService号称可以做到异构系统的同构化,
 *    但是在实际开发中还是会遇见各种问题,如类型问题,WSDL文件的支持问题等.从WSDL产生的对象都认为是一个产品,
 *    然后由一个具体的工厂类进行管理,减少与外围的耦合.
 *
 *  3.工厂方法模式可以使用在测试驱动开发的框架下.例如:测试一个类A,就需要将与类A关联的类B也同时产生出来,
 *  使用工厂方法可以将类B虚拟出来,避免类A与类B的耦合.
 *
 *
 *
*
 *
 *
 */
