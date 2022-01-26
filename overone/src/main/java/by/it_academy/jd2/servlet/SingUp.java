package by.it_academy.jd2.servlet;


import by.it_academy.jd2.dao.DAOImpl;
import by.it_academy.jd2.dto.PersonRegistration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SingUp", urlPatterns = "/SingUp")
public class SingUp extends HttpServlet {

    DAOImpl daoImpl = DAOImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login=req.getParameter("login");
        String password=req.getParameter("password");
        String name=req.getParameter("name");
        String surname=req.getParameter("surname");
        String middleName=req.getParameter("middleName");
        String birthday=req.getParameter("birthday");

        PersonRegistration personRegistration = new PersonRegistration
                (login, password, name, surname, middleName, birthday);
        System.out.println(personRegistration);

        //daoImpl.saveParson(personRegistration);
        try {
            daoImpl.registrationPerson(personRegistration);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //resp.sendRedirect("http://localhost:8080/Mk-JD2-78-21-0.0.0-SHAPSHOT/NameViewStart.jsp");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/authorization.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
