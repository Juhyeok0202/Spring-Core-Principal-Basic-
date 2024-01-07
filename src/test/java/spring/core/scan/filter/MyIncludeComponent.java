package spring.core.scan.filter;


import java.lang.annotation.*;

@Target(ElementType.TYPE) //Class level
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
