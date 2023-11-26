package tech.zolhungaj.amqapi.clientcommands;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE})
public @interface CommandGroup {
    String value() default "";
}
