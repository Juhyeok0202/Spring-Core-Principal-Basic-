package spring.core.member;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Primary // @Qualifier보다는 @Primary가 실무에서 자주 사용된다.
public class MemoryMemberRepository implements MemberRepository{

    // 동시성 해시맵을 써야 좋긴 함.
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
