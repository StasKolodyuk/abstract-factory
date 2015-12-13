package com.epam.jmp.api.db;


import com.epam.jmp.api.PersonReader;
import com.epam.jmp.api.PersonManager;
import com.epam.jmp.api.PersonWriter;
import com.epam.jmp.api.ResourceException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBPersonManager implements PersonManager {

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private Connection connection;

    public DBPersonManager(String dbUrl, String login, String password) throws ResourceException {
        try {
            connection = DriverManager.getConnection(dbUrl, login, password);
        } catch (SQLException e) {
            throw new ResourceException(e);
        }
    }

    @Override
    public PersonReader getReader() throws ResourceException {
        return new DBPersonReader(connection);
    }

    @Override
    public PersonWriter getWriter() throws ResourceException {
        return new DBPersonWriter(connection);
    }
}
