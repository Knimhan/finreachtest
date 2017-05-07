package de.finreach.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodsExpressionSentence
{
    private String romanEquivalentTokenOne;

    private String romanEquivalentTokenTwo;

    private String goodsName;
    
    private Double amount;
}
