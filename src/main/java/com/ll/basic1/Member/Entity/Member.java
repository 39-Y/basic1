package com.ll.basic1.Member.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {
    private static long ID=0;
    @Getter
    private long id;
    @Getter
    private String userName;
    @Getter
    private String pw;

    public Member(String userName, String pw){
        this(++ID, userName, pw) ;
    }
}
