package by.vorobey.shoppingBasket.dao;

import by.vorobey.shoppingBasket.model.Product;
import by.vorobey.shoppingBasket.model.ProductMapper;
import by.vorobey.shoppingBasket.server.ReadSQLFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DAOTableImpl implements DAOTable{

    private final JdbcTemplate template;

    @Autowired
    public DAOTableImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public void createTable() {
        template.execute(ReadSQLFile.readFile("schema.sql").get(0));
    }

    @Override
    public void insertTable() {
        ArrayList<String> list = ReadSQLFile.readFile("werehouse.sql");
        for (String s : list) {
            template.execute(s);
        }
    }

    @Override
    public void deleteTable() {
        template.execute(ReadSQLFile.readFile("schema.sql").get(1));
    }

    @Override
    public List<Product> findAll() {
        return template.query("select * from warehouse", new ProductMapper());
    }

    @Override
    public Product findProduct(int id) {
        return template.queryForObject("select * from warehouse WHERE id = ?", new ProductMapper(), id);
    }


}














