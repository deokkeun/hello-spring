package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//@Service
public class MemberService { // 서비스는 비즈니스에 의존적
    // command + shift + t (Create New Test)

    private final MemberRepository memberRepository;

    // DI(Dependency Injection) - 필드 주입, setter 주입, 생성자 주입(권장)
    // 외부에서 객체 간의 관계(의존성)을 결정해 주는데 즉,
    // 객체를 직접 생성하는 것이 아니라 외부에서 생성후 주입시켜 주는 방식
    // 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X(임시)
        // command + option + v (return 생성)
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // control + t (Refactor This)
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // ifPresent() 값이 있으면 (Optional 때문에 사용 가능)
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
