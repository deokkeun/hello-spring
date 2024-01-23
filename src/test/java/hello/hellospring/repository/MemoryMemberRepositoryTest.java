package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// 테스트 주도 개발 TDD 방식
// 테스트 케이스를 먼저 만들고 구현 클래스를 만들어서 돌려본다
public class MemoryMemberRepositoryTest {
    // 테스트는 순서에 의존 관계 없이 설계 되어야 한다

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메서드 실행이 끝날때 마다 동작(callback method)
    public void afterEach() {
        repository.clearStore();
    }

    // command + shift + enter 작성 완료되면 다음줄로 이동
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // .get() Optional에서 값을 꺼낼때 좋은 방법은 아니다
        assertThat(result).isEqualTo(member);
                // Actual            Expected
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift + F6 (변수명 한번에 변경)
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift + F6 (변수명 한번에 변경)
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
