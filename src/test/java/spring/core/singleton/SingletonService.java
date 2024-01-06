package spring.core.singleton;

public class SingletonService {

    // 자기 자신을 static level로 선언 => 딱 하나만 존재
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        // 조회는 getInstance를 통해서'만' 조회 가능
        // 항상 동일 객체 인스턴스만 반환 가능
        return instance;
    }

    private SingletonService() {
        // 오직 Java가 실행될 때, 단 하나 생성됨
        // 기본 생성자를 private로 외부에서 (추가)생성하지 못하게 막아버림
    }

    public void login() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
