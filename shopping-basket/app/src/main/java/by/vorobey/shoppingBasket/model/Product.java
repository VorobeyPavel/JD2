package by.vorobey.shoppingBasket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Integer id;
    private String name;
    private Double price;
    private Integer count;
    private Double totalPriceItem;

}
