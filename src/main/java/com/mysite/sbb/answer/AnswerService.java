package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    // 답변 생성 후 저장
    public void create(Question question, String content) {
        Answer answer = new Answer(); // answer 객체 생성
        answer.setContent(content); // 내용 set
        answer.setQuestion(question); // 질문 set
        answer.setCreateDate(LocalDateTime.now()); // 시간 set
        this.answerRepository.save(answer);
    }
}
