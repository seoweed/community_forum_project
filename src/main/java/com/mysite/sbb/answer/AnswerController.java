package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    // 변하는 id 값을 얻을 때에는 @PathVariable 애너테이션을 사용해야 한다
    // @RequestParam 은 템플릿에서 답변으로 작성한 content 를 가져오는 역할을 한다.
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @RequestParam String content) {
        Question question = this.questionService.getQuestion(id); // 해당 질문을 id로 조회해서 가져옴
        answerService.create(question, content); // 답변 생성 후 저장 메소드 사용
        return String.format("redirect:/question/detail/%s", id);
    }
}
