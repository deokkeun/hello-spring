package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(`viewResolver`)가 화면을 찾아서 처리한다.
    // 스프링 부트 템플릿엔진 기본 viewName 매핑
    // `resources:templates/`+{ViewName}+`.html`

    // * Build and Start *
    // ./gradlew clean build
    // cd build/libs
    // java -jar hello-spring-0.0.1-SNAPSHOT.jar

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";
    }
}
