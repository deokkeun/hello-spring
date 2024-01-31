package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // @Controller, @Service, @Repository -> 컴포넌트 스캔(@Component)과 자동 의존관계 설정
public class MemberController {
    // @Controller
    // spring container에서 spring bean(MemberController객체)이 관리된다.

    //    @Autowired (DI - 필드 주입)
    private final MemberService memberService;

//    @Autowired // (DI - setter 주입)
//    public void setMemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // @Autowired
    // memberService를 spring이 spring container에 있는 memberService를 가져다 연결해준다
    // spring container에 등록 되있는 것만 @Autowired가 가능하다
    @Autowired // (DI - 생성자 주입) - 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute(members);
        return "members/memberList";
    }
}
