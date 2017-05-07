package de.finreach.business.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.finreach.business.model.InputDTO;
import de.finreach.business.model.QuestionSentence;
import de.finreach.business.util.helper.Constants;
import de.finreach.business.util.helper.ExpressionParserHelper;
import de.finreach.business.util.helper.OutputGenerator;

@Component
public class QuestionParser
{
    @Autowired
    private OutputGenerator outputGenerator;

    @Autowired
    private ExpressionParserHelper expressionParserHelper;

    public List<String> parse(InputDTO inputDTO)
    {
        List<String> outputs = new ArrayList<>();
        String output;
        for (QuestionSentence questionSentence : inputDTO.getQuestionSentences())
        {
            if (questionSentence.getValidQuestion())
            {
                String words[] = expressionParserHelper.splitSentence(questionSentence.getQuestion());
                if (isQuestionTypeCredit(words))
                {
                    output = outputGenerator.createCreditOutput(inputDTO, words);
                }
                else if (isQuestionTypeRomanEvaluation(words))
                {
                    output = outputGenerator.createRomanEquivalentOutput(inputDTO, words);
                }
                else
                {
                    output = Constants.ERROR_MESSAGE;
                }
            }
            else
            {
                output = Constants.ERROR_MESSAGE;
            }
            outputs.add(output);
        }
        return outputs;
    }

    private boolean isQuestionTypeCredit(String[] words)
    {
        return "CREDITS".equalsIgnoreCase(words[Constants.INDEX_OF_CREDITS_IN_QUESTION_SENTENCE]);
    }

    private boolean isQuestionTypeRomanEvaluation(String[] words)
    {
        return "IS".equalsIgnoreCase(words[Constants.INDEX_OF_IS_IN_ROMAN_EQUIVALENT_QUESTION_SENTENCE]);
    }

}
