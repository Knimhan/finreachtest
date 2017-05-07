package de.finreach.business.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputDTO
{
    private Map<String, String> romanEquivalent;

    private List<GoodsExpressionSentence> goodsExpressionSentences;

    private List<QuestionSentence> questionSentences;
    
    private Map<String, Double> goods;
}
