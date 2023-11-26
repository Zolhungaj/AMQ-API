package tech.zolhungaj.amqapi.clientcommands;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmptyClientCommand {
}
