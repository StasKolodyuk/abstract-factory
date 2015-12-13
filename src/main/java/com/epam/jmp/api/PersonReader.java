package com.epam.jmp.api;


public interface PersonReader {

    Person readPerson() throws ResourceException;
    Person readPerson(String name) throws ResourceException;

}
