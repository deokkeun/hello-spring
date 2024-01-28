package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    // control + r (이전에 실행한것을 그대로 실행)

    MemberService memberService;
    MemoryMemberRepository memberRepository;

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
//    같은 인스턴스를 사용하지 않는 문제가 생김
//    memberService 내부에 같은 MemoryMemberRepository 인스턴스를 사용되도록 변경함 (DI)

    @BeforeEach // 메서드 실행이 동작하기 전에 실행
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // Memory DB Clear
    @AfterEach // 메서드 실행이 끝날때 마다 동작(callback method)
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() { // 회원가입
        // given - 주어졌을때
        Member member = new Member();
        member.setName("spring");

        // when - 실행하면
        Long saveId = memberService.join(member);

        // then - 결과가 이렇게 나와야 한다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void validateDuplicateMemberTest() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
        }
*/
        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}