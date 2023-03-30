/**
 * This is a custom annotation that can be used to mark methods, fields, parameters,
 * local variables, and type usages as not nullable.
 *
 * @Retention(RetentionPolicy.CLASS): Specifies that the retention policy for this annotation
 * is CLASS. This means that the annotation will be retained by the Java compiler and will
 * be available at runtime via reflection.
 *
 * @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER,
 * ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE}): Specifies the element types that
 * this annotation can be applied to. In this case, the annotation can be applied to methods,
 * fields, parameters, local variables, and type usages.
 *
 * @interface NotNull: Defines the name of the custom annotation.
 *
 * String value() default "": Defines an optional attribute for the annotation that can be used
 * to provide additional information about the annotation. In this case, the default value
 * is an empty string.
 *
 * Class<? extends Exception> exception() default Exception.class: Defines another optional
 * attribute for the annotation that can be used to specify an exception that should be thrown
 * if the annotated element is null. In this case, the default value is the `Exception` class.
 */
package main.utils.parameters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
public @interface NotNull {
    String value() default "";

    Class<? extends Exception> exception() default Exception.class;
}
