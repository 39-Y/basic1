package com.ll.basic1.Member.Service;

import com.ll.basic1.Domain.RsCode;
import com.ll.basic1.Member.Entity.Member;
import com.ll.basic1.Member.Repository.MemberRepository;

public class MemberService {
    MemberRepository m = new MemberRepository();

    public RsCode tryLogin(String id, String pw){
        Member member = m.findByUserName(id);
        if(member==null)
            return new RsCode("F-2", id+"는 존재하지 않습니다.");
        else if(!member.getPw().equals(pw))
            return new RsCode("F-1", "비밀번호가 일치하지 않습니다.");
        return new RsCode("S-1", id+" 님 환영합니다.");
    }
}
