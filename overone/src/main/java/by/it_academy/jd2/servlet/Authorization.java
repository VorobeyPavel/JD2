package by.it_academy.jd2.servlet;

import by.it_academy.jd2.dao.DAOImpl;
import by.it_academy.jd2.dto.PersonRegistration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Authorization", urlPatterns = "/Authorization")
public class Authorization extends HttpServlet {

    DAOImpl daoImpl = DAOImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        PersonRegistration personRegistration = null;
        try {
            personRegistration = daoImpl.authorizationPerson(new PersonRegistration(login, password));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        if (personRegistration==null){
            session.setAttribute("notAuthorization", "Вы не прошли авторизоцию");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/authorization.jsp");
            requestDispatcher.forward(req, resp);
        }else {
            session.setAttribute("user", login);

            session.setAttribute("authorization", "welcome");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/welcome.jsp");
            requestDispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
