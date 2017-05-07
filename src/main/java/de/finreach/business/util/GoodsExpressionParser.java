package de.finreach.business.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.finreach.business.model.GoodsExpressionSentence;
import de.finreach.business.model.InputDTO;
import de.finreach.business.util.helper.ExpressionParserHelper;
import de.finreach.business.util.helper.RomanToDecimalConverter;

@Component
public class GoodsExpressionParser
{
    @Autowired
    private RomanToDecimalConverter romanToDecimalConverter;

    @Autowired
    private ExpressionParserHelper expressionParserHelper;

    public InputDTO parse(InputDTO inputDTO)
    {
        Map<String, Double> goods = new HashMap<>();

        for (GoodsExpressionSentence goodsExpressionSentence : inputDTO.getGoodsExpressionSentences())
        {
            String romanTokenOne = expressionParserHelper.getRomanValue(inputDTO, goodsExpressionSentence.getRomanEquivalentTokenOne());
            String romanTokenTwo = expressionParserHelper.getRomanValue(inputDTO, goodsExpressionSentence.getRomanEquivalentTokenTwo());
            Integer quantityInDecimal = romanToDecimalConverter.convert(expressionParserHelper.concatRomenNumeralTokens(romanTokenOne, romanTokenTwo));
            goods.put(goodsExpressionSentence.getGoodsName(), evalutateValueOfGood(quantityInDecimal, goodsExpressionSentence.getAmount()));
        }

        inputDTO.setGoods(goods);
        return inputDTO;
    }

    private Double evalutateValueOfGood(Integer quantityInDecimal, Double amount)
    {
        return amount / quantityInDecimal;
    }

}
