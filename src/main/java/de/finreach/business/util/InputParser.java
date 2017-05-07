package de.finreach.business.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.finreach.business.model.GoodsExpressionSentence;
import de.finreach.business.model.InputDTO;
import de.finreach.business.model.QuestionSentence;
import de.finreach.business.util.helper.Constants;
import de.finreach.business.util.helper.ExpressionParserHelper;

@Component
public class InputParser
{
    @Autowired
    private ExpressionParserHelper expressionParserHelper;

    public InputDTO parse(List<String> readSentences)
    {
        Map<String, String> romanEquivalent = new HashMap<>();
        List<GoodsExpressionSentence> goodsExpressionSentences = new ArrayList<>();
        List<QuestionSentence> questionSentences = new ArrayList<>();

        for (String currentLine : readSentences)
        {
            String words[] = expressionParserHelper.splitSentence(currentLine);
            if (isRomanEquivalentSentence(words))
            {
                romanEquivalent.put(words[0], words[2]);
            }
            else if (isGoodsExpressionSentence(words))
            {
                goodsExpressionSentences.add(createGoodsExpression(words));
            }
            else if (isQuestionSentence(words))
            {
                questionSentences.add(new QuestionSentence(currentLine, Constants.VALID_QUESTION));
            }
            else
            {
                questionSentences.add(new QuestionSentence(currentLine, Constants.INVALID_QUESTION));
            }
        }

        return new InputDTO(romanEquivalent, goodsExpressionSentences, questionSentences, null);
    }

    private GoodsExpressionSentence createGoodsExpression(String[] words)
    {
        return new GoodsExpressionSentence(words[Constants.INDEX_OF_ROMAN_NUMERAL_ONE_IN_GOODS_EXPRESSION], words[Constants.INDEX_OF_ROMAN_NUMERAL_TWO_IN_GOODS_EXPRESSION],
                words[Constants.INDEX_OF_GOODS_IN_GOODS_EXPRESSION], new Double(words[Constants.INDEX_OF_AMOUNT_IN_GOODS_EXPRESSION]));
    }

    private boolean isGoodsExpressionSentence(String[] words)
    {
        return Constants.GOODS_EXPRESSION_TOKEN_LENGTH == words.length && "IS".equalsIgnoreCase(words[Constants.INDEX_OF_IS_IN_GOODS_EXPRESSION])
                && "CREDITS".equalsIgnoreCase(words[Constants.INDEX_OF_CREDITS_IN_GOODS_EXPRESSION]);
    }

    private boolean isRomanEquivalentSentence(String[] words)
    {
        return Constants.ROMAN_EQUIVALENT_LENGTH == words.length && "IS".equalsIgnoreCase(words[Constants.INDEX_OF_IS_IN_ROMAN_EQUIVALENT]);
    }

    private boolean isQuestionSentence(String[] words)
    {
        return Constants.QUESTION_SENTENCE_LENGTH == words.length && "HOW".equalsIgnoreCase(words[Constants.INDEX_OF_HOW_IN_QUESTION_SENTENCE]) && "?".endsWith(words[Constants.INDEX_OF_QUESTIONMARK]);
    }

}
