package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // 동시성 문제 때문에 HashMap 보다는 ConcurrentHashMap을 사용하는 것이 좋다
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null 이 반환될 가능성이 있으면 Optional로 감싸준다

        // Optional.of
        // value가 null인 경우 NPE 예외를 던집니다.
        // 반드시 값이 있어야 하는 객체인 경우 해당 메서드를 사용한다

        // Optional.ofNullable
        // value가 null인 경우 비어있는 Optional을 반환합니다.
        // 값이 null 일수도 있는 것은 해당 메서드를 사용한다
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
