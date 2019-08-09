package com.erkuai.commonarchitecture.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Administrator on 2019/8/9.
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLife {
    String value() default "Application";
}
