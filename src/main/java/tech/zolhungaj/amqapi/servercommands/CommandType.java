package tech.zolhungaj.amqapi.servercommands;

import java.lang.annotation.*;
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CommandType.List.class)
@Target({ElementType.TYPE_USE, ElementType.TYPE})
public @interface CommandType {
    String value() default "";
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE_USE, ElementType.TYPE})
    @Documented
    @interface List {
        CommandType[] value();
    }
}
