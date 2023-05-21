package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller // 자신이 controller 라는것을 스프링부트에게 알려주기 위해 붙임
@RequiredArgsConstructor // 방법 3의 객체 생성과 생성자 주입을 해주기 위해 생성자를 생성해주는 lombok 어노테이션이다.
// URL의 프리픽스가 모두 /question으로 시작한다는 것을 알수 있다. 이런 경우 클래스명 위에
// @RequestMapping("/question") 애너테이션을 추가하고 메서드 단위에서는 /question 를 생략한 그 뒷 부분만을 적으면 된다.
@RequestMapping("/question")
public class QuestionController {
    // 매핑 방법 1
    //    @GetMapping("/question/list") // get 방식으로 매핑해줌
//    @ResponseBody // 문자열을 웹페이지에 바로 출력하는 방식
//    public String list() {
//        return "question list";
//    }

    // 매핑 방법 2
    // resources/templates/question_list.html 경로에 있는 파일을 찾아서 웹페이지에 출력해줌
    //    @GetMapping("/question/list") // get 방식으로 매핑해줌
//    public String list() {
//        return "question_list";
//    }
    // 매핑 방법 3
    // 이번에는 question class 에 질문 목록을 출력해 봄
//    private final QuestionRepository questionRepository; // 생성자 자동 의존 관계 주입과 함께 객체를 생성해준다.
//
//    @GetMapping("/question/list") // get 방식으로 매핑해줌
//    public String list(Model model) {
//        List<Question> questionList = questionRepository.findAll(); // findAll 로 질문 목록 데이터를 가져왔음
//        model.addAttribute("questionList", questionList); // Model 객체에 "questionList" 라는 이름으로 값을 저장
//        return "question_list";
//    }

    // service class 를 만든 후 방법 3을 리팩토링 함
    private final QuestionService questionService; // 자동 주입과 컨트롤러 대신 중간다리 역할인 서비스 객체를 생성
    // 질문 목록 페이지 컨트롤러
    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = questionService.getList(); // QuestionService 에 만들어두었던 getList 메서드를 사용
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
    // 질문 상세 페이지 컨트롤러
    // 변하는 id 값을 얻을 때에는 @PathVariable 애너테이션을 사용
    // 이때 @GetMapping(value = "/question/detail/{id}") 에서 사용한 id와 @PathVariable("id")의 매개변수 이름이 동일해야 한다.
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }





}
