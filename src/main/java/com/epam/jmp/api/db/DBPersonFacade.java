package com.epam.jmp.api.db;

import com.epam.jmp.api.Person;
import com.epam.jmp.api.PersonFacade;
import com.epam.jmp.api.PersonReader;
import com.epam.jmp.api.PersonManager;
import com.epam.jmp.api.PersonWriter;
import com.epam.jmp.api.ResourceException;


public class DBPersonFacade implements PersonFacade {

    private static final String DB_URL = "jdbc:h2:./test;INIT=RUNSCRIPT FROM 'classpath:create.sql'";
    private static final String DB_LOGIN = "user";
    private static final String DB_PASSWORD = "";

    private PersonReader personReader;
    private PersonWriter personWriter;

    public DBPersonFacade() throws ResourceException {
        PersonManager personManager = PersonManager.from(DB_URL, DB_LOGIN, DB_PASSWORD);
        personReader = personManager.getReader();
        personWriter = personManager.getWriter();
    }

    @Override
    public Person readPerson() throws ResourceException {
        return personReader.readPerson();
    }

    @Override
    public Person readPerson(String name) throws ResourceException {
        return personReader.readPerson(name);
    }

    @Override
    public void writeObject(Person person) throws ResourceException {
        personWriter.writeObject(person);
    }
}
