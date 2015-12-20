package com.epam.jmp.api;


public interface PersonFacade {

    Person readPerson() throws ResourceException;
    Person readPerson(String name) throws ResourceException;
    void writeObject(Person person) throws ResourceException;

}
