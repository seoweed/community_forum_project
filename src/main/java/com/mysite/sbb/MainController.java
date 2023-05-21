package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/sbb")
    @ResponseBody //  @ResponseBody 는 URL 요청에 대한 응답으로 문자열을 리턴하라는 의미이다.
    public String index() {
        return "안녕하세요 sbb에 오신것을 환영합니다.";
    }

    // root page 생성
    // redirect:<URL> - URL로 리다이렉트 (리다이렉트는 완전히 새로운 URL로 요청이 된다.)
    //forward:<URL> - URL로 포워드 (포워드는 기존 요청 값들이 유지된 상태로 URL이 전환된다.)
    @GetMapping("/")
    public String root() {
        return "redirect:/question/list"; // /question/list URL로 페이지를 리다이렉트 하라는 명령어
    }


}
