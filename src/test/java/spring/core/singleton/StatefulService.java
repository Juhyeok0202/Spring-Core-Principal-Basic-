package spring.core.singleton;
/*
🌟🌟🌟🌟🌟
스프링 빈은 항상 무상태(Stateless)로 설계해야함.
스프링 빈은 싱글톤이니까. 하나의 객체 인스턴스를 공유하기 때문에 발생하는 문제 방지
 */
public class StatefulService {

//    private int price; //상태를 유지하는 필드  10000 -> 20000

//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; //여기가 문제!
//    }

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
     }

//    public int getPrice() {
//        return price;
//    }
}
