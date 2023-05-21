package com.mysite.sbb.question;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.answer.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// controller 와 repository 의 중간다리 역할 수행
@Service // 스프링부트가 서비스로 인식할 수 있도록 어노테이션을 붙여준다.
@RequiredArgsConstructor // 생성자 lombok
public class QuestionService {
    private final QuestionRepository questionRepository; // questionRepository 객체는 생성자 방식으로 DI 규칙에 의해 주입된다.
    private final AnswerRepository answerRepository;

    public List<Question> getList() {
        return questionRepository.findAll();
    }
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        }else {
            throw new DataNotFoundException("question not found");
        }
    }
    // 질문 저장하기
    public void create(String subject, String content) {
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

}
