package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(`viewResolver`)가 화면을 찾아서 처리한다.
    // 스프링 부트 템플릿엔진 기본 viewName 매핑
    // `resources:templates/`+{ViewName}+`.html`

    // (Thymeleaf 템플릿 엔진이 렌더링하고 변환을 한 HTML을 반환)

    // * Build and Start *
    // ./gradlew clean build
    // cd build/libs
    // java -jar hello-spring-0.0.1-SNAPSHOT.jar

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    // @ResponseBody
    // HTTP 통신 프로토콜의 Header, Body가 있는데 Body 부분에 데이터를 직접 넣어주겠다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
    }


    // XML 방식 "<HTML></HTML>"
    // JSON 방식 {"name":"spring!!"}

    // @ResponseBody 사용 원리
    // HTTP의 BODY에 문자 내용을 직접 반환
    // `viewResolver` 대신에 `HttpMessageConverter` 가 동작
    // 기본 문자처리: `StringHttpMessageConverter`
    // 기본 객체처리: `MappingJackson2HttpMessageConverter` (객체 -> JSON)
    // byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음

    // 참고: 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해서
    // `HttpMessageConverter`가 선택된다.
    
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        // command + shift + enter (객체 자동완성)
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
