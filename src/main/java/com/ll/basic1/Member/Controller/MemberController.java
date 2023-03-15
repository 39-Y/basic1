package com.ll.basic1.Member.Controller;

import com.ll.basic1.Domain.RsCode;
import com.ll.basic1.Member.Service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MemberController {
    MemberService service = new MemberService();
    @GetMapping("/member/login")
    public RsCode login(String id, String pw) {
        return service.tryLogin(id, pw);
    }
}
