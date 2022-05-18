package by.vorobey.myProject.dao;

import by.vorobey.myProject.bean.News;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class NewsDAO {

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

    public void addNews(News news) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO news (title, anons, fullText) VALUES(?, ?, ?)");
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getAnons());
            preparedStatement.setString(3, news.getFullText());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<News> getNews(){
        String sql = "SELECT * FROM news";

        ArrayList<News> list = new ArrayList<>();
        News news;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                int id = rs.getInt(1);
                String anons = rs.getString(2);
                String title = rs.getString(3);
                String fullText = rs.getString(4);
                news = new News((long)id, anons, title, fullText);
                list.add(news);
                System.out.println(news);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public News newsDetails(int id){
        String sql = "SELECT * FROM news WHERE id="+"'"+id+"'";

        News news = null;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                int idNews = rs.getInt(1);
                String titleNews = rs.getString(2);
                String anonsNews = rs.getString(3);
                String fullTextNews = rs.getString(4);

                news = new News((long)idNews, titleNews, anonsNews, fullTextNews);
                System.out.println(news);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return news;
    }

    public void updateNews(int id, String title, String anons, String full_text) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE public.news SET title='"+title+"'"+
                            ", anons='"+anons+"'"+", fulltext='"+ full_text+"'"+" where id='"+id+"'");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNews(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM news where id='"+id+"'");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
