package by.vorobey.shoppingBasket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basket {

    private String startShopping;
    private ArrayList<Product> list;
    private String finishShopping;
    private Double totalPrice;

}
