package by.vorobey;

import by.vorobey.shoppingBasket.dao.DAOTable;
import by.vorobey.shoppingBasket.model.Basket;
import by.vorobey.shoppingBasket.model.Product;
import by.vorobey.shoppingBasket.server.Order;
import by.vorobey.shoppingBasket.utils.DBUtils;
import by.vorobey.shoppingBasket.view.Print;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class AppTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBUtils.class);
    DAOTable daoTable = context.getBean(DAOTable.class);
    Print print = context.getBean(Print.class);
    Order order = context.getBean(Order.class);

    @Test
    public void  deleteProductTest(){
        String orderDelete = "delete-1";
        ArrayList<Product> orderList = new ArrayList<>();
        orderList.add(new Product(1, "Orange", 3.5, 2, null));
        orderList.add(new Product(2, "Banana", 4.0, 4, null));

        int sizeBefore = orderList.size();
        order.deleteProduct(orderDelete, orderList);
        int sizeAfter = orderList.size();

        Assert.assertEquals(sizeBefore, sizeAfter+1);
    }

    @Test
    public void priceItemTest(){
        Product orange = new Product(1, "Orange", 3.5, 2, null);
        double priceOrange = orange.getPrice()*orange.getCount();

        ArrayList<Product> orderList = new ArrayList<>();
        orderList.add(orange);
        Basket basket = new Basket(null, orderList, null, null);

        Basket basketPriceItem = order.totalPriceItem(basket);
        Assert.assertEquals(priceOrange, basketPriceItem.getList().get(0).getTotalPriceItem(), 0.001);
    }

    @Test
    public void totalPriceItemTest(){
        Product orange = new Product(1, "Orange", 3.5, 2, null);
        Product banana = new Product(2, "Banana", 4.0, 4, null);
        double priceProduct = orange.getPrice()*orange.getCount() + banana.getPrice()*banana.getCount();

        ArrayList<Product> orderList = new ArrayList<>();
        orderList.add(new Product(1, "Orange", 3.5, 2, null));
        orderList.add(new Product(2, "Banana", 4.0, 4, null));
        Basket basket = new Basket(null, orderList, null, null);

        Basket basketPriceItem = order.totalPriceItem(basket);
        Basket basketPrice = order.totalPrice(basket);

        Assert.assertEquals(priceProduct, basketPrice.getTotalPrice(), 0.001);
    }

}
