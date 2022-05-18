package by.vorobey.myProject.dao;

import by.vorobey.myProject.bean.Person;
import org.springframework.stereotype.Component;

import java.sql.*;


@Component
public class PersonDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/alishev";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registration(Person person) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO users (name, surname, email, login, password) VALUES(?, ?, ?, ?, ?)");
            //String sql ="INSERT INTO person (login, password, name, surname, middleName, birthday) VALUES(?,?,?,?,?,?)";

            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getLogin());
            preparedStatement.setString(5, person.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /*public void registration(Person person) {
        // используем hibernate для сохранения пользователя
        Session session = HibernateCreator.getInstance().openSession();

        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();

    }*/

    public Person authorization(String login, String password){
        String sql = "SELECT * FROM users WHERE login="+"'"+login+"'"+" and password="+"'"+password+"'";

        Person authorization = null;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                String nameAuthorization = rs.getString(1);
                String surnameAuthorization = rs.getString(2);
                String emailAuthorization = rs.getString(3);
                String loginAuthorization = rs.getString(4);
                String passwordAuthorization = rs.getString(5);
                authorization = new Person(nameAuthorization, surnameAuthorization, emailAuthorization,
                        loginAuthorization, passwordAuthorization);
                System.out.println(authorization);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return authorization;
    }
}
