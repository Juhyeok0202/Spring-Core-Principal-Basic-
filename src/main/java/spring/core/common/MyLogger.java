package spring.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 로그을 출력하기 위한 클래스
 *
 * @Scope(value = "request")을 사용해서 requset 스코프로 저정함.
 * 이제 이 빈은 HHTP 요청 당 하나씩 생성(Spring Container 요청하는 시점)되고, HTTP 요청이 끝나는 시점에 소멸된다.
 */

/*
[proxyMode]
대상이 Class       => TARGET_CLASS
대상이 Interface   => INTERFACE

CGLIB라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어서 주입

 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL; // 이 빈이 생성되는 시점에 알 수 없으므로, 외부 setter로 입력 받음
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        /*
        uuid는 HTTP 요청 당 하나만 생성되므로, 고유한 값이 보장된다.
        즉, 다른 요청과 구분이 가능하다.
         */
        uuid = UUID.randomUUID().toString(); // 전세계적으로 유일한 아이디 하나가 생성됨
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
