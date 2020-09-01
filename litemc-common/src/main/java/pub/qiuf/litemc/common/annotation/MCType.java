package pub.qiuf.litemc.common.annotation;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MCType {
    String value();
}