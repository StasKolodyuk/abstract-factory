package com.epam.jmp.api.db;


import com.epam.jmp.api.PersonReader;
import com.epam.jmp.api.ResourceException;
import com.epam.jmp.api.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBPersonReader implements PersonReader {

    private static final String SELECT_PERSON_BY_NAME = "SELECT * FROM PERSON WHERE firstName = ?";
    private static final String SELECT_LAST_PERSON = "SELECT * FROM PERSON WHERE person_id = (SELECT MAX(person_id) FROM PERSON)";


    private Connection connection;

    public DBPersonReader(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Person readPerson() throws ResourceException {
        Person person = new Person();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_LAST_PERSON)) {
            populatePerson(person, statement);
        } catch (SQLException e) {
            throw new ResourceException(e);
        }

        return person;
    }

    @Override
    public Person readPerson(String name) throws ResourceException {
        Person person = new Person();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_BY_NAME)) {
            statement.setString(1, name);
            populatePerson(person, statement);
        } catch (SQLException e) {
            throw new ResourceException(e);
        }

        return person;
    }

    private void populatePerson(Person person, PreparedStatement statement) throws SQLException {
        try(ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                person.setFirstName(resultSet.getString("firstName"));
                person.setLastName(resultSet.getString("lastName"));
            }
        }
    }
}
