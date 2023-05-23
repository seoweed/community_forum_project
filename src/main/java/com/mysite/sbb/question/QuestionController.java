package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

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
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }
    // 질문 상세 페이지 컨트롤러
    // 변하는 id 값을 얻을 때에는 @PathVariable 애너테이션을 사용
    // 이때 @GetMapping(value = "/question/detail/{id}") 에서 사용한 id와 @PathVariable("id")의 매개변수 이름이 동일해야 한다.
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    // 질문 등록 템플릿에서 필드 항목으로 사용했던 subject, content의 이름과 동일하게 해야 함에 주의하자.
    // 폼 클래스 생성 후 (@RequestParam String subject, @RequestParam String content)를 아래 처럼 바꿔준다.
    // @Valid 애너테이션을 적용하면 QuestionForm의 @NotEmpty, @Size 등으로 설정한 검증 기능이 동작한다.
    //  이어지는 BindingResult 매개변수는 @Valid 애너테이션으로 인해 검증이 수행된 결과를 의미하는 객체이다.
    //
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        // 오류가 있는 경우에는 다시 폼을 작성하는 화면을 렌더링
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장 후 질문 목록으로 이동함
    }





}
