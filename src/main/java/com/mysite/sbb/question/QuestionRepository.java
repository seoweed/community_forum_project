package com.mysite.sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// repository 로 만들기 위해 JpaRepository 인터페이스를 상속받는다.
// JpaRepository 를 상속할 때는 제네릭스 타입으로 <Question, Integer> 처럼 리포지터리의 대상이 되는
// 엔티티의 타입(Question)과 해당 엔티티의 PK의 속성 타입(Integer)을 지정해야 한다. 이것은 JpaRepository 를 생성하기 위한 규칙이다.
    public interface QuestionRepository extends JpaRepository<Question, Integer> {
//        이제 QuestionRepository, AnswerRepository 를 이용하여 question, answer 테이블에 데이터를 저장하거나 조회할 수 있다.

    // 물론 기본적으로 제공하는 crud 를 제외하고 다른 메서드를 추가 생성하는것이 가능하다.
    // Question 엔티티의 subject 값으로 데이터를 조회하는 메서드를 추가함
    //  "인터페이스에 findBySubject 라는 메서드를 선언만 하고 구현은 하지 않았는데 도대체 어떻게 실행이 되는 거지?"
    //이러한 마법은 JpaRepository를 상속한 QuestionRepository 객체가 생성될때 벌어진다. (DI에 의해 스프링이 자동으로 QuestionRepository 객체를 생성한다.
    // 이 때 프록시 패턴이 사용된다고 한다.) 리포지터리 객체의 메서드가 실행될때 JPA가 해당 메서드명을 분석하여 쿼리를 만들고 실행한다.
    //즉, findBy + 엔티티의 속성명(예:findBySubject)과 같은 리포지터리 메서드를 작성하면 해당 속성의 값으로 데이터를 조회할수 있다.
        Question[] findBySubject(String subject);

        // 제목과 내용을 함께 조회하는 메서드
    // 리포지터리의 메서드명은 데이터를 조회하는 쿼리문의 where 조건을 결정하는 역할을 한다.
    // 리포지토리 명을 막쓰는것이 아니라 정해진 이름들로 사용하는 것이 올바른 사용법이다.
    // 퀴리 생성큐칙에 대한 공식 문서를 참고해도 된다.
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
        Question[] findBySubjectAndContent(String subject, String content);

        // 제목에 특정 문자열이 포함되어 있는 데이터를 조회
    // 응답 결과가 여러건인 경우에는 리포지터리 메서드의 리턴 타입을 Question이 아닌 List<Question> 으로 해야 한다.
        List<Question> findBySubjectLike(String subject);

        // 페이징 작업을 위한 데이터 조회
        Page<Question> findAll(Pageable pageable);


}
