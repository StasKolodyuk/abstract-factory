package com.epam.jmp.api.db;


import com.epam.jmp.api.PersonWriter;
import com.epam.jmp.api.ResourceException;
import com.epam.jmp.api.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBPersonWriter implements PersonWriter {

    private static final String INSERT_PERSON = "INSERT INTO PERSON (firstName, lastName) VALUES (?, ?)";

    private Connection connection;

    public DBPersonWriter(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void writeObject(Person person) throws ResourceException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_PERSON)) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceException(e);
        }
    }
}
