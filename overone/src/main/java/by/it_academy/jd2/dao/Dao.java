package by.it_academy.jd2.dao;

import by.it_academy.jd2.dto.PersonRegistration;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public interface Dao {

    public void registrationPerson(PersonRegistration personRegistration) throws SQLException, ClassNotFoundException;

    public  PersonRegistration authorizationPerson(PersonRegistration personRegistration) throws PropertyVetoException, SQLException;

}
