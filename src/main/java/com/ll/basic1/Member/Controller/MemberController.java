package com.ll.basic1.Member.Controller;

import com.ll.basic1.Domain.RQ;
import com.ll.basic1.Domain.RsCode;
import com.ll.basic1.Member.Service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class MemberController {
    MemberService service;
    RQ rrqq;

    //@ResponseBody
    @GetMapping("/member/login")
    public String login() {
//        if(rrqq.isLogin()){
//            return "<h1>이미 로그인 되었습니다.</h1>";
//
//        }
        return "usr/member/login";
    }

    @ResponseBody
    @PostMapping("/member/doLogin")
    public RsCode doLogin(String id, String pw) {
        RsCode rc= service.tryLogin(id, pw);

        if(rc.isSuccess()){
            //rrqq.setCookie("logOn", id);
            rrqq.setSession("logOn", id);
        }
        return rc;
    }

    @ResponseBody
    @GetMapping("/member/me")
    public RsCode checkMe() {
        //String userName = rrqq.getCookie("logOn","none");
        String userName = rrqq.getSession("logOn", "none");
        return service.checkLogin(userName);
    }
    @ResponseBody
    @GetMapping("/member/logout")
    public RsCode logOut() {
        //rrqq.removeCookie("logOn");
        rrqq.removeSession("logOn");
        return RsCode.of("S-2", "로그아웃 되었습니다.");
    }

}
