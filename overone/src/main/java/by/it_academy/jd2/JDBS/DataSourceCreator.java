package by.it_academy.jd2.JDBS;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class DataSourceCreator {
    private static DataSourceCreator instance;
    private ComboPooledDataSource cpds;

    private DataSourceCreator() throws PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/Veb7Message");
        cpds.setUser("postgres");
        cpds.setPassword("postgres");
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
    }

    public static DataSource getInstance() throws PropertyVetoException {
        if (instance == null){
            synchronized (DataSourceCreator.class){
                if (instance == null){
                    instance = new DataSourceCreator();
                }
            }
        }
        return instance.cpds;
    }
}

