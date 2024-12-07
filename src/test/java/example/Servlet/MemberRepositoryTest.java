package example.Servlet;

import example.Servlet.Object.Member;
import example.Servlet.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void AfterEach(){
        memberRepository.clear();
    }


    @Test
    void save(){
        Member member = new Member("Spring", 10);

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        Member member1 = new Member("Spring1",10);
        Member member2 = new Member("Spring2",20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> list = memberRepository.findAll();
        Assertions.assertThat(list.size()).isEqualTo(2);
    }


}
