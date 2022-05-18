package by.vorobey.shoppingBasket.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan("by.vorobey.shoppingBasket")
@PropertySource("classpath:application.properties")
public class DBUtils {

    @Autowired
    Environment environment;

    private final String driverName = "driver-name";
    private final String url = "jdbc-url";
    private final String username = "jdbc-username";
    private final String password = "jdbc-password";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty(driverName)));
        dataSource.setUrl(environment.getProperty(url));
        dataSource.setUsername(environment.getProperty(username));
        dataSource.setPassword(environment.getProperty(password));
        return dataSource;
    }

}
