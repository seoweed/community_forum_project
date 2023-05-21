package com.mysite.sbb.answer;

import org.springframework.data.jpa.repository.JpaRepository;
// QuestionRepository 와 마찬가지로 상속받고 제네릭 타입을 넣어주면 된다.
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
//    이제 QuestionRepository, AnswerRepository 를 이용하여 question, answer 테이블에 데이터를 저장하거나 조회할 수 있다.

}
