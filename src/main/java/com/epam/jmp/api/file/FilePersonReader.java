package com.epam.jmp.api.file;


import com.epam.jmp.api.PersonReader;
import com.epam.jmp.api.ResourceException;
import com.epam.jmp.api.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.apache.commons.lang3.StringUtils.substringBefore;
import static org.apache.commons.lang3.StringUtils.substringAfter;

public class FilePersonReader implements PersonReader {

    private String filePath;

    public FilePersonReader(String filePath) throws ResourceException {
        this.filePath = filePath;
    }

    @Override
    public Person readPerson() throws ResourceException {
        try {
            Person person = new Person();
            Files.lines(Paths.get(filePath)).reduce((a, b) -> b).ifPresent(line -> populatePerson(person, line));
            return person;
        } catch (IOException e) {
            throw new ResourceException(e);
        }
    }

    @Override
    public Person readPerson(String name) throws ResourceException {
        try {
            Person person = new Person();
            Files.lines(Paths.get(filePath)).filter(l -> l.contains(name)).findFirst().ifPresent(line -> populatePerson(person, line));
            return person;
        } catch (IOException e) {
            throw new ResourceException(e);
        }
    }

    private void populatePerson(Person person, String personString) {
        person.setFirstName(substringBefore(personString, " "));
        person.setLastName(substringAfter(personString, " "));
    }
}
