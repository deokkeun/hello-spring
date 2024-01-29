package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 아직 데이터 저장소가 선정되지 않았다는 가상의 시나리오 떄문에 인터페이스와 구현체를 따로 만듦
    Member save(Member member);

    // Optional<T>
    // "결과 없음"을 표시해야 하는 명확한 필요성이 있고 null을 사용하면 오류가 발생할 가능성이 있는 경우
    // 메서드 반환 유형으로 사용하기 위한 것
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
