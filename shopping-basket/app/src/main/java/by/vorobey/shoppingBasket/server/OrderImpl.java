package by.vorobey.shoppingBasket.server;

import by.vorobey.shoppingBasket.dao.DAOTable;
import by.vorobey.shoppingBasket.model.Basket;
import by.vorobey.shoppingBasket.model.Product;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
@Component
public class OrderImpl implements Order{

    private static final Logger log = Logger.getLogger(OrderImpl.class);

    @Override
    public Basket addProduct(DAOTable daoTable) {
        System.out.println("Для добавления товара в корзину введите id и количество желаемого товара. Формат ввода: id-count "+
                "Если вы хотите завершить покупку введите Exit");

        SimpleDateFormat dateStart = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ArrayList<Product> orderList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String order = scanner.nextLine();
        Basket basket = new Basket(dateStart.format(new Date()), null, null, null );
        while (!Objects.equals(order, "Exit")){

            if (order.contains("delete-")){
                deleteProduct(order, orderList);
                System.out.println(basket.toString());

            }else {
                try {
                    int indexBlank = order.indexOf("-");
                    int idProduct = Integer.parseInt(order.substring(0, indexBlank));
                    int countProduct = Integer.parseInt(order.substring(indexBlank+1));

                    System.out.println("idProduct: " + idProduct);
                    System.out.println("countProduct: " + countProduct);

                    Product orderProduct;
                    try {
                        orderProduct = daoTable.findProduct(idProduct);
                        orderProduct.setCount(countProduct);
                    }catch (EmptyResultDataAccessException e){
                        System.out.println("Вы ввели недоступный id товара. Введите id товара из доступного перечня.");
                        order = scanner.nextLine();
                        continue;
                    }

                    boolean addProduct = true;
                    for (Product product : orderList) {
                        if (Objects.equals(product.getId(), orderProduct.getId())){
                            product.setCount(product.getCount() + orderProduct.getCount());
                            addProduct = false;
                        }
                    }
                    if (addProduct){
                        orderList.add(orderProduct);
                    }
                    basket.setList(orderList);

                    System.out.println(basket.toString());
                }catch (NumberFormatException | StringIndexOutOfBoundsException e){
                    log.info("Некорректный запрос пользователя: "+order);
                    System.out.println("Вы ввели неверный формат данных. Формат данных: id-count(целочисленные значения). " +
                            "Для выхода введите Exit");
                }
            }
            System.out.println("Для продолжения покупки вводите id и count следующего выбранного товара. " +
                    "Для удаления товара из корзины введите delete-id "
                    + "Для завершения покупки введите Exit");

            order = scanner.nextLine();
            if (Objects.equals(order, "Exit")){
                basket.setFinishShopping(dateStart.format(new Date()));
                basket = totalPriceItem(basket);
                basket = totalPrice(basket);
                System.out.println(basket.toString());
            }
        }
        return basket;
    }

    @Override
    public void deleteProduct(String order, ArrayList<Product> orderList) {
        int indexDelete = order.indexOf("delete-");
        int idDelete = 0;
        if (indexDelete==0){
            idDelete = Integer.parseInt(order.substring(7));
        }

        Iterator<Product> iterator = orderList.iterator();
        while (iterator.hasNext()){
            Product element = iterator.next();
            if (element.getId()==idDelete){
                iterator.remove();
            }
        }
    }

    @Override
    public Basket totalPriceItem(Basket basket) {
        ArrayList<Product> orderList = basket.getList();
        for (Product product : orderList) {
            product.setTotalPriceItem(product.getCount()*product.getPrice());
        }
        basket.setList(orderList);
        return basket;
    }

    @Override
    public Basket totalPrice(Basket basket) {
        ArrayList<Product> orderList = basket.getList();
        double totalPrice = 0;
        for (Product product : orderList) {
            totalPrice = totalPrice + product.getTotalPriceItem();
        }
        basket.setTotalPrice(totalPrice);
        return basket;
    }

}
