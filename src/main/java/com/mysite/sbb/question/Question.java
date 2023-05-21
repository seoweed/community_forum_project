package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

// 가장 첫 단계로 엔티티 (모델, 도메인 모델) 구성 및 작성을 해야한다.
// 엔티티는 데이터베이스 테이블과 매핑되는 자바 클래스를 말한다
// 따라서 SBB 에는 질문과 답변에 해당하는 엔티티가 있어야 한다.
@Getter @Setter // getter, setter 생성
@Entity // entity 이므로 @Entity 어노테이션을 붙여준다. @Entity 애너테이션을 적용해야 JPA가 엔티티로 인식한다
public class Question {
    // 각 변수? 들에 그에 맞는 어노테이션을 붙여준다.

    @Id // id 속성을 기본 키로 지정한다. 기본 키로 지정하면 이제 id 속성의 값은 데이터베이스에 저장할 때 동일한 값으로 저장할 수 없다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터를 저장할 때 해당 속성에 값을 따로 세팅하지 않아도 1씩 자동으로 증가하여 저장된다
    private Integer id;
    // @Column 은 추가 설정이 없으면 생략이 가능하다
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;
    // Answer 엔티티에서 Question 엔티티를 참조했기 때문에 마찬가지로 설정을 해줘야 한다. mappedBy를 사용해 매핑 당한것을 표시해줌
    // mappedBy는 참조 엔티티의 속성명을 의미한다. 즉, Answer 엔티티에서 Question 엔티티를 참조한 속성명 question을 mappedBy에 전달해야 한다.
    // 질문 하나에는 여러개의 답변이 작성될 수 있다. 이때 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해서 @OneToMany의 속성으로 cascade = CascadeType.REMOVE를 사용했다.
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
