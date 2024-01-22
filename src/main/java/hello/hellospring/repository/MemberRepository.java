package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    // Optional<T>
    // "결과 없음"을 표시해야 하는 명확한 필요성이 있고 null을 사용하면 오류가 발생할 가능성이 있는 경우
    // 메서드 반환 유형으로 사용하기 위한 것
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
