package com.epam.jmp.client;


import com.epam.jmp.api.PersonReader;
import com.epam.jmp.api.ResourceException;
import com.epam.jmp.api.PersonManager;
import com.epam.jmp.api.PersonWriter;
import com.epam.jmp.api.Person;


public class Main {

    public static void main(String[] args) throws ResourceException{

        Person mentee = new Person("Stanislau", "Kaladziuk");
        Person mentor = new Person("Viktar", "Strok");

        /* Uncomment to use H2 in-memory database
        String url = "jdbc:h2:./test;INIT=RUNSCRIPT FROM 'classpath:create.sql'";
        String login = "user";
        String password = "";
        PersonManager personManager = PersonManager.from(url, login, password);*/

        PersonManager personManager = PersonManager.from("persons.txt");

        PersonWriter personWriter = personManager.getWriter();
        PersonReader personReader = personManager.getReader();

        personWriter.writeObject(mentee);
        personWriter.writeObject(mentor);

        mentee = personReader.readPerson("Stanislau");
        mentor = personReader.readPerson();

        System.out.println(mentee);
        System.out.println(mentor);
    }

}