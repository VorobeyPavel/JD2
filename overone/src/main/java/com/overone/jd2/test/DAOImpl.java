package com.overone.jd2.test;

import com.overone.jd2.JDBS.DataSourceCreator;
import com.overone.jd2.dto.PersonRegistration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.Objects;

public class DAOImpl implements Dao{

    private static DAOImpl instance;

    public DAOImpl() {
    }

    public static DAOImpl getInstance(){
        if (instance==null){
            instance = new DAOImpl();
        }
        return instance;
    }

    @Override
    public void registrationPerson(PersonRegistration personRegistration) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Veb7Message",
                "postgres", "postgres")) {

            String sql ="INSERT INTO person (login, password, name, surname, middleName, birthday) VALUES(?,?,?,?,?,?)";
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                //for (int i = 0; i < 1; i++) {
                    ps.setString(1, personRegistration.getLogin());
                    ps.setString(2, personRegistration.getPassword());
                    ps.setString(3, personRegistration.getName());
                    ps.setString(4, personRegistration.getSurname());
                    ps.setString(5, personRegistration.getMiddleName());
                    ps.setString(6, personRegistration.getBirthday());
                    ps.executeUpdate();
                //}
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public  PersonRegistration authorizationPerson(PersonRegistration personRegistration) throws PropertyVetoException, SQLException {

        // используем пул соединений DataSourceCreator
        DataSource dataSource = DataSourceCreator.getInstance();
        Connection connection = dataSource.getConnection();

            PersonRegistration authorization = null;
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("Select login, password From person");) {

                while (rs.next()) {
                    String loginBase = rs.getString(1);
                    String password = rs.getString(2);
                    System.out.println("loginBase-" + loginBase + " and " + "password-" + password);

                    if (Objects.equals(personRegistration.getLogin(), loginBase) &&
                            Objects.equals(personRegistration.getPassword(), password)) {
                        authorization = new PersonRegistration(loginBase, password);
                        System.out.println(authorization.toString());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return authorization;
        }

}
