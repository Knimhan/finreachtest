package de.finreach.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionSentence
{
    private String question;

    private Boolean validQuestion;
}
