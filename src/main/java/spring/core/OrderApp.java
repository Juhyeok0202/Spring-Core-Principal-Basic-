package spring.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.order.Order;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
//        MemberServiceImpl memberService = new MemberServiceImpl();
////        OrderServiceImpl orderService = new OrderServiceImpl();
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "ItemA", 20000);
        System.out.println("order = " + order);
    }
}
