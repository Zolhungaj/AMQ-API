package tech.zolhungaj.amqapi.clientcommands;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE})
public @interface CommandName {
    String value() default "";
}
