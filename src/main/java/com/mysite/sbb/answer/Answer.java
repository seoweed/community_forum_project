package com.mysite.sbb.answer;
// Question class 와 동일한 엔티티 클래스로 동일하게 만들어주면 된다.

import com.mysite.sbb.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;
    // entity 끼리 서로 참조하고 있는 상태에서는 속성만 추가하면 되는것이 아니고
    // 질문 엔티티와 연결된 속성이라는 것을 명시적으로 표시해야한다.
    // 다대일 관계이므로(질문 하나에 여러개의 답변이 달릴수 있기 때문) ManyToOne 어노테이션을 사용해서 명시해준다.
    // 답변은 Many(많은 것)가 되고 질문은 One(하나)이 된다. 따라서 @ManyToOne은 N:1 관계라고 할 수 있다
    // @ManyToOne 애너테이션을 설정하면 Answer 엔티티의 question 속성과 Question 엔티티가 서로 연결된다.
    // (실제 데이터베이스에서는 ForeignKey 관계가 생성된다.)
    @ManyToOne // 여기서 설정을 해주었으니 반대편 속성에도 설정을 해줘야 한다. Question 으로 가서 속성을 추가해준다.
    private Question question;
}
