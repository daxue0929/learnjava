package com.daxue.applet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface RabbitExcel {


    /**
     * 列名
     */
    String columnName() default "";

    /**
     * json格式字典对应项，比如有时用户身份用0和1表示普通和会员，
     * 那么最终提现到excel里应该是普通或会员而不是0或1，此时，用这个注解。
     */
    String dict() default "";


}
