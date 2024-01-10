package spring.core.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.core.common.MyLogger;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;

    // MyLogger를 주입 받는 것이 아니라,
    // MyLogger를 찾을 수 있는 Dependency Lookup할 수 있는 얘가 주입이 됨.(대리자)
    // getObject() 하는 시점에 생성됨.
    // 즉, request scope인 스프링 빈이 스프링 컨테이너한테 요청하는 시점을 '지연'하는 효과
//    private final ObjectProvider<MyLogger> myLoggerProvider; // SCOPE: request

    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody // view 없이 문자를 바로 반환하기 위함
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        /*스프링 인터셉터로 구현하는 편이 더 깔끔하긴 하다.*/

//        MyLogger myLogger = myLoggerProvider.getObject(); //정말 필요한 시점에 가져올 수 있음
        // 고객이 어떤 URL로 요청햇는지 알 수 있음
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000); //1초 sleep
        logDemoService.logic("testId");
        return "Ok";
    }
}
