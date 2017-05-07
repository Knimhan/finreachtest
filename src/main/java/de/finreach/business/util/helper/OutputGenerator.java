package de.finreach.business.util.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.finreach.business.model.InputDTO;

@Component
public class OutputGenerator
{
    @Autowired
    private RomanToDecimalConverter romanToDecimalConverter;

    @Autowired
    private ExpressionParserHelper expressionParserHelper;

    public String createRomanEquivalentOutput(InputDTO inputDTO, String[] words)
    {
        String romanTokenOne = expressionParserHelper.getRomanValue(inputDTO, words[Constants.INDEX_OF_ROMAN_NUMERAL_ONE_IN_ROMAN_EVALUATION_QUESTION]);
        String romanTokenTwo = expressionParserHelper.getRomanValue(inputDTO, words[Constants.INDEX_OF_ROMAN_NUMERAL_TWO_IN_ROMAN_EVALUATION_QUESTION]);
        String romanTokenThree = expressionParserHelper.getRomanValue(inputDTO, words[Constants.INDEX_OF_ROMAN_NUMERAL_THREE_IN_ROMAN_EVALUATION_QUESTION]);
        String romanTokenFour = expressionParserHelper.getRomanValue(inputDTO, words[Constants.INDEX_OF_ROMAN_NUMERAL_FOUR_IN_ROMAN_EVALUATION_QUESTION]);
        String concatRomanNumeral = expressionParserHelper.concatRomenNumeralTokens(romanTokenOne, romanTokenTwo, romanTokenThree, romanTokenFour);

        Integer value = romanToDecimalConverter.convert(concatRomanNumeral);

        return generateRomansEvaluationOutputString(words, value);
    }

    public String createCreditOutput(InputDTO inputDTO, String[] words)
    {
        String romanTokenOne = expressionParserHelper.getRomanValue(inputDTO, words[Constants.INDEX_OF_ROMAN_NUMERAL_ONE_IN_CREDIT_QUESTION]);
        String romanTokenTwo = expressionParserHelper.getRomanValue(inputDTO, words[Constants.INDEX_OF_ROMAN_NUMERAL_TWO_IN_CREDIT_QUESTION]);

        Integer quantityInDecimal = romanToDecimalConverter.convert(expressionParserHelper.concatRomenNumeralTokens(romanTokenOne, romanTokenTwo));

        Double credits = calculateCredits(quantityInDecimal, expressionParserHelper.getGoodsValue(inputDTO, words));

        return generateCreditsOutputString(words, credits);
    }

    private Double calculateCredits(Integer quantityInDecimal, Double goodsValue)
    {
        return quantityInDecimal * goodsValue;
    }

    private String generateRomansEvaluationOutputString(String[] words, Integer value)
    {
        return words[Constants.INDEX_OF_ROMAN_NUMERAL_ONE_IN_ROMAN_EVALUATION_QUESTION] + " " + words[Constants.INDEX_OF_ROMAN_NUMERAL_TWO_IN_ROMAN_EVALUATION_QUESTION] + " "
                + words[Constants.INDEX_OF_ROMAN_NUMERAL_THREE_IN_ROMAN_EVALUATION_QUESTION] + " " + words[Constants.INDEX_OF_ROMAN_NUMERAL_FOUR_IN_ROMAN_EVALUATION_QUESTION] + " is " + value;
    }

    private String generateCreditsOutputString(String[] words, Double credits)
    {
        return words[Constants.INDEX_OF_ROMAN_NUMERAL_ONE_IN_CREDIT_QUESTION] + " " + words[Constants.INDEX_OF_ROMAN_NUMERAL_TWO_IN_CREDIT_QUESTION] + " "
                + words[Constants.INDEX_OF_GOODS_IN_CREDIT_QUESTION] + " is " + credits + " Credits";
    }
}
