package spring.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.core.discount.FixDiscountPolicy;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {

        //생성자 주입을 해야 이렇게 유연성있는 테스트 만들 수 있음
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}