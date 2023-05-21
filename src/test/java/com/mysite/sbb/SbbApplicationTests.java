package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	// @@ 데이터 저장 테스트 @@
//	// DI(Dependency Injection) - 스프링이 객체를 대신 생성하여 주입한다.
//	@Autowired // 스프링의 DI 기능으로 questionRepository 객체를 스프링이 자동으로 생성해 준다.
//	// 객체를 주입하는 방식에는 @Autowired 외에 Setter 또는 생성자를 사용하는 방식이 있다
//	//  순환참조 문제와 같은 이유로 @Autowired 보다는 생성자를 통한 객체 주입방식이 권장된다.
//	//  하지만 테스트 코드의 경우에는 생성자를 통한 객체의 주입이 불가능하므로 테스트 코드 작성시에만 @Autowired를 사용하고
//	//  실제 코드 작성시에는 생성자를 통한 객체 주입방식을 사용하겠다.
//	private QuestionRepository questionRepository;
//
//	@Test // testJpa 메서드 위의 @Test 애너테이션은 testJpa 메서드가 테스트 메서드임을 나타낸다. 위 클래스를 JUnit으로 실행하면 @Test 애너테이션이 붙은 메서드가 실행된다.
//	void testJap() {
//		Question q1 = new Question(); // 질문 객체 생성
//		q1.setSubject("sbb가 무엇인가요?"); // 질문 객체에 제목 설정
//		q1.setContent("sbb에 대해서 알고싶습니다. 알려주세요."); // 질문 객체에 내용 설정
//		q1.setCreateDate(LocalDateTime.now()); // 작성 시간 설정
//		this.questionRepository.save(q1); // 첫번째 질문(q1 객체) 데이터베이스에 저장
//
//		// q1 객체와 동일하게 생성
//		Question q2 = new Question();
//		q2.setSubject("스프링부트 모델 질문입니다.");
//		q2.setContent("id는 자동으로 생성되나요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2); // 두번째 질문(q2 객체) 데이터 베이스에 저장
//	}
	// @@ 데이터 검색 테스트 @@
//	@Autowired
//	private QuestionRepository questionRepository;
//
//	@Test
//	void testJap() {
//		List<Question> all = this.questionRepository.findAll();
//		Assertions.assertThat(all.size()).isEqualTo(4); // assertj.core.api 사용해서 같은지 확인
//
//		Question q = all.get(0);
//		org.junit.jupiter.api.Assertions.assertEquals(q.getSubject(), "sbb가 무엇인가요?");
//
//	}

	// @@ 데이터 id로 검색 테스트 @@
//	@Autowired
//	private QuestionRepository questionRepository;
//
//	@Test
//	void testJap() {
//
//		Optional<Question> oq = this.questionRepository.findById(1);
//		if (oq.isPresent()) {
//			Question question_form.html = oq.get();
//			Assertions.assertThat(question_form.html.getSubject()).isEqualTo("sbb가 무엇인가요?");
//		}
//	}

	// Question 엔티티의 subject 값으로 데이터를 조회
	// 아쉽게도 Question 리포지터리는 findBySubject와 같은 메서드를 기본적으로 제공하지는 않는다.
	// findBySubject 메서드를 사용하려면 QuestionRepository 인터페이스를 변경해야 한다.

//	@Test
//	void testJpa() {
//		Question[] q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
//		Assertions.assertEquals(1, q[0].getId());
//	}
	// 제목과 내용을 함께 조회 findBySubjectAndContent
//	@Test
//	void testJpa() {
//		Question[] bySubjectAndContent = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고싶습니다. 알려주세요.");
//		Assertions.assertEquals(1, bySubjectAndContent[0].getId());
//
//	}
	// 제목에 특정 문자열이 포함되어 있는 데이터를 조회 findBySubjectLike
//	@Test
//	void testJpa() {
//		List<Question> ssb = questionRepository.findBySubjectLike("sbb%");
//		Assertions.assertEquals(1, ssb.get(0).getId());
//	}
	// 질문 데이터 수정
	// setter 를 이용해서 검색된 데이터의 내용을 바꾸고 save 메서드를 사용하여 저장한다.
//	@Test
//	void testJap() {
//		Optional<Question> oq = this.questionRepository.findById(1);
//		Assertions.assertTrue(oq.isPresent());
//		Question q = oq.get();
//		q.setSubject("수정된 제목");
//		this.questionRepository.save(q);
//	}

	// 데이터 삭제
//	@Test
//	void testJpa() {
//		Assertions.assertEquals(4, this.questionRepository.count());
//		Optional<Question> oq = this.questionRepository.findById(4);
//		Question q = oq.get();
//		this.questionRepository.delete(q);
//		Assertions.assertEquals(3, this.questionRepository.count());
//	}

	// answerRepository 사용
	// 답변 데이터 생성 후 저장하기
//	@Test
//	void testJpa() {
//		Optional<Question> oq = this.questionRepository.findById(2);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//
//		Answer a1 = new Answer();
//		a1.setQuestion(q);
//		a1.setContent("네 자동으로 생성됩니다.");
//		a1.setCreateDate(LocalDateTime.now());
//		this.answerRepository.save(a1);
//	}
	// 답변 조회하기
//	@Test
//	void testJpa() {
//		Optional<Answer> oa = this.answerRepository.findById(1);
//		Answer a = oa.get();
//		assertEquals(2, a.getQuestion().getId());
//	}

	// 질문에서 답변 찾기
	@Transactional // 메서드가 종료될 때 까지 db 세션 유지
	@Test
	void testJap() {
		Optional<Question> oq = this.questionRepository.findById(2);
		Question q = oq.get();
		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
		// 이렇게만 하면 LazyInitializationException 오류가 발생한다.
		// 왜냐하면 Question 리포지터리가 findById를 호출하여 Question 객체를 조회하고 나면 DB세션이 끊어지기 때문이다.
		// 그 이후에 실행되는 q.getAnswerList() 메서드는 세션이 종료되어 오류가 발생한다.
		// 답변 데이터 리스트는 q 객체를 조회할때 가져오지 않고 q.getAnswerList() 메서드를 호출하는 시점에 가져오기 때문이다.
		// 이 문제는 테스트 코드에서만 발생한다. 실제 서버에서 JPA 프로그램들을 실행할 때는 DB 세션이 종료되지 않기 때문에 위와 같은 오류가 발생하지 않는다.
		// 테스트 코드를 수행할 때 위와 같은 오류를 방지할 수 있는 가장 간단한 방법은 다음처럼 @Transactional 애너테이션을 사용하는 것이다.
		// @Transactional 애너테이션을 사용하면 메서드가 종료될 때까지 DB 세션이 유지된다
	}
}
