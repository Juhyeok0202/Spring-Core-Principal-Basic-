package spring.core.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.core.annotation.MainDiscountPolicy;
import spring.core.discount.DiscountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;

@Component //Register Component to Spring Container
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 보통 생략하고들 사용함
    // 생성자 의존성 주입은 '불변'
    @Autowired // Automatic Dependency Injection //🌟생성자 단 1개이면, @Autowired 생략 가능(OrderServiceImpl 등록 할 때, 선택 여지가 1개이니.)
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        // 오직 인터페이스에만 의존함(생성자 주입)
        // 외부에서 의존성을 주입함 -> DI(Dependency Injection)(의존관계 주입)
        // 관심사를 분리하여 실행에만 집중
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인에 대한것은 Discount에게 모두 위임하고, 결과만 받음(SRP 잘 지킴)

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
