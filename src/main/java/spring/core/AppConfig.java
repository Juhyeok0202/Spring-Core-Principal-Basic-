package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

@Configuration // CGLIB기술을 사용하여 (의존 관계 주입이 필요해서 직접 호출할 때도)싱글톤을 가능케 함.(빈 등록은 됨)
public class AppConfig { /*연극 기획자(DI Container)*/
    /*
    * 관심사 분리를 위한 공연 기획자(외부 의존 관계 주입 도우미)
    * 사용 영역과 구성 영역을 완전히 분리(구성 영역만 고치기 가능)
    *
    * Method 명을 보는 순간 역할이 잘 드러나게 리팩토링
    * */
    @Bean //(Register to Spring Container)
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //configurationTest에서 1번만 호출됨. 싱글톤 보장해줌.
        System.out.println("call AppConfig.memberRepository");
        // 구성의 역할을 분리
        // DB로 바꿀 때 아래 코드만 바꾸면 됨.
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        // 할인 정책 바꿀 때, 아래 코드만 변경하면 됨.
        return new RateDiscountPolicy();
    }
}
