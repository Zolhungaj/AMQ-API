package tech.zolhungaj.amqapi.clientcommands;

import java.lang.annotation.*;

/**
 * Signifies that this object has a field/method which will be directly inserted as the data field of the command.
 * <br>
 * By default, that field will be named "data", but this can be changed with the value parameter.
 * <br>
 * The order of precedence for the data field in case of a conflict is:
 * <ol>
 *     <li>Record component</li>
 *     <li>JavaBeans style getter for field name</li>
 *     <li>Method name</li>
 *     <li>Field name</li>
 * </ol>
 */
@Inherited
@Documented
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DirectDataCommand {
    String value() default "data";
}
