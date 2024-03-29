package com.ll.basic1.Member.Service;

import com.ll.basic1.Domain.RsCode;
import com.ll.basic1.Member.Entity.Member;
import com.ll.basic1.Member.Repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MemberService {
    MemberRepository m = new MemberRepository();

    public RsCode tryLogin(String id, String pw){
        Member member = m.findByUserName(id);
        if(member==null)
            return RsCode.of("F-2", id+"는 존재하지 않습니다.");
        else if(!member.getPw().equals(pw))
            return RsCode.of("F-1", "비밀번호가 일치하지 않습니다.");
        else
            return RsCode.of("S-1", id+" 님 환영합니다.", member);
    }

    public RsCode checkLogin(String logOnValue) {
        if(logOnValue.equals("none"))
            return RsCode.of("F-1", "로그인이 필요합니다.");
        return RsCode.of("S-1", "당신의 UserName은 "+logOnValue+" 입니다.");
    }
}
