package com.ll.basic1.Member.Repository;

import com.ll.basic1.Member.Entity.Member;

import java.util.List;
import java.util.Map;

public class MemberRepository {
    List<Member> members = List.of(
           new Member("user1" , "1234"),
            new Member("abc" , "12345"),
            new Member("test" , "12346"),
            new Member("love" , "12347"),
            new Member("like" , "12348"),
            new Member("giving" , "12349"),
            new Member("thanks" , "123410"),
            new Member("hello" , "123411"),
            new Member("good", "123412"),
            new Member("peace" , "123413")
    );

    public Member findByUserName(String userName){
        return members.stream()
                .filter(m->userName.equals(m.getUserName()))
                .findFirst()
                .orElse(null);
    }


}
