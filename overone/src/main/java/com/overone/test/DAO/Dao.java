package com.overone.test.DAO;

import com.overone.test.dto.PersonRegistration;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public interface Dao {

    public void registrationPerson(PersonRegistration personRegistration) throws SQLException, ClassNotFoundException;

    public  PersonRegistration authorizationPerson(PersonRegistration personRegistration) throws PropertyVetoException, SQLException;

}
