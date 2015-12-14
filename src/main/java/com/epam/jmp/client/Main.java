package com.epam.jmp.client;


import com.epam.jmp.api.PersonReader;
import com.epam.jmp.api.ResourceException;
import com.epam.jmp.api.PersonManager;
import com.epam.jmp.api.PersonWriter;
import com.epam.jmp.api.Person;


public class Main {

    private static final String DB_URL = "jdbc:h2:./test;INIT=RUNSCRIPT FROM 'classpath:create.sql'";
    private static final String DB_LOGIN = "user";
    private static final String DB_PASSWORD = "";

    private static final String FILE_NAME = "persons.txt";

    public static void main(String[] args) throws ResourceException{

        PersonManager personManager;

        if(args.length == 1 && "db".equals(args[0])) {
            personManager = PersonManager.from(DB_URL, DB_LOGIN, DB_PASSWORD);
        } else {
            //Use file by default
            personManager = PersonManager.from(FILE_NAME);
        }

        Person mentee = new Person("Stanislau", "Kaladziuk");
        Person mentor = new Person("Viktar", "Strok");

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
