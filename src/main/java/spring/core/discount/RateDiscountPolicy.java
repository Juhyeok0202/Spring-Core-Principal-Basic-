package spring.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import spring.core.annotation.MainDiscountPolicy;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.order.OrderServiceImpl;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private final int discountPercent = 10; // 정률 할인 10%

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
