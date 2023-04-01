package com.jjerome.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SocketMapping{

    String resPath() default "";

    String reqPath();
}
