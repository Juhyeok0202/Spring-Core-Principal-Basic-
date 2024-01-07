package spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //@Component가 붙어있음 얘도
@ComponentScan( //Default-Packages : 해당 클래스의 패키지 부터 하위 패키지까지
        // 모든 Java코드를 다 뒤짐 Libs도 => 오래 걸림. 타겟설정!
        basePackages = "spring.core.member",
        basePackageClasses = AutoAppConfig.class,
        // AppConfig 등의 @Configuration 충돌 회피(기존 예제 코드 안지우고 남겨두기 위함)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // @Component 붙은 클래스 찾아서 자동으로 스프링빈으로 등록해줌.
public class AutoAppConfig {

}
