package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
// 화면에서 전달되는 입력 값을 검증하기 위해서 폼 클래스를 작성하고
// 값을 검증한다. 폼 클래스를 만든 후에는 컨트롤러에서 사용할수 있도록
// 컨트롤러를 수정한다.
@Getter @Setter
public class QuestionForm {
    @NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max = 200)
    private String subject;
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
