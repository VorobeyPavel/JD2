package by.vorobey.shoppingBasket.server;

import by.vorobey.shoppingBasket.dao.DAOTable;
import by.vorobey.shoppingBasket.model.Basket;
import by.vorobey.shoppingBasket.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface Order {

    Basket addProduct(DAOTable daoTable);
    void deleteProduct(String order, ArrayList<Product> orderList);
    Basket totalPriceItem(Basket basket);
    Basket totalPrice(Basket basket);

}
