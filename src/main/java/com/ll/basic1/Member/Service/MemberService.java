package com.ll.basic1.Member.Service;

import com.ll.basic1.Domain.RsCode;

public class MemberService {
    public RsCode tryLogin(String id, String pw){
        if(!"user1".equals(id))
            return new RsCode("F-2", id+"는 존재하지 않습니다.");
        else if(! "1234".equals(pw))
            return new RsCode("F-1", "비밀번호가 일치하지 않습니다.");
        return new RsCode("S-1", "user1 님 환영합니다.");
    }
}
