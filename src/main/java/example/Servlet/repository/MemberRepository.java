package example.Servlet.repository;

import example.Servlet.Object.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberRepository {
    private static HashMap<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    private static final MemberRepository memberRepository = new MemberRepository();

    // 싱글톤 사용
    public static MemberRepository getInstance(){
        return memberRepository;
    }

    // 생성자 막기
    private MemberRepository(){
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clear(){
       store.clear();
    }



}
