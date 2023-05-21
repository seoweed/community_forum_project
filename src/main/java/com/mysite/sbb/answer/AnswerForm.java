package com.mysite.sbb.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import javax.swing.text.StringContent;
@Setter @Getter
public class AnswerForm {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
