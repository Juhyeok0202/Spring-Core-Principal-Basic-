package spring.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

//        // ThreadA: A사용자 10,000원 주문
//        statefulService1.order("userA", 10000);
//        // ThreadB: A사용자 20,000원 주문
//        statefulService2.order("userB", 20000);

        //🌟 ThreadA: A사용자 10,000원 주문(이렇게 지역변수를 통해 공유 변수 문제 해결)
        int userAPrice = statefulService1.order("userA", 10000);
        //🌟 ThreadB: A사용자 20,000원 주문(이렇게 지역변수를 통해 공유 변수 문제 해결)
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        // expected : 10000
        // actual   : 20000 (cuz: 같은 인스턴스 사용)
//        int price = statefulService1.getPrice();
        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}