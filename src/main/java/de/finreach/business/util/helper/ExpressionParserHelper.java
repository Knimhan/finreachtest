package de.finreach.business.util.helper;

import org.springframework.stereotype.Component;

import de.finreach.business.model.InputDTO;

@Component
public class ExpressionParserHelper
{
    public String concatRomenNumeralTokens(String... romanTokens)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (String romanToken : romanTokens)
            stringBuilder = stringBuilder.append(romanToken);
        return stringBuilder.toString();
    }

    public String getRomanValue(InputDTO inputDTO, String romanEquivalentKey)
    {
        return inputDTO.getRomanEquivalent().get(romanEquivalentKey);
    }

    public Double getGoodsValue(InputDTO inputDTO, String[] words)
    {
        return inputDTO.getGoods().get(words[Constants.INDEX_OF_GOODS_IN_CREDIT_QUESTION]);
    }

    public String[] splitSentence(String sentence)
    {
        return sentence.split(" ");
    }

}
