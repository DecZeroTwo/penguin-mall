package com.wnhz.older.authority.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BmsRole {
    String value() default "";
}
