package com.epam.jmp.client;


import com.epam.jmp.api.PersonReader;
import com.epam.jmp.api.ResourceException;
import com.epam.jmp.api.PersonFacade;
import com.epam.jmp.api.db.DBPersonFacade;
import com.epam.jmp.api.PersonManager;
import com.epam.jmp.api.PersonWriter;
import com.epam.jmp.api.Person;


public class Main {


    private static final String FILE_NAME = "persons.txt";

    public static void main(String[] args) throws ResourceException{

        Person mentee = new Person("Stanislau", "Kaladziuk");
        Person mentor = new Person("Viktar", "Strok");

        if(args.length == 1 && "db".equals(args[0])) {
            PersonFacade personFacade = new DBPersonFacade();

            personFacade.writeObject(mentee);
            personFacade.writeObject(mentor);

            mentee = personFacade.readPerson("Stanislau");
            mentor = personFacade.readPerson();
        } else {
            //Use file by default
            PersonManager personManager = PersonManager.from(FILE_NAME);

            PersonWriter personWriter = personManager.getWriter();
            PersonReader personReader = personManager.getReader();

            personWriter.writeObject(mentee);
            personWriter.writeObject(mentor);

            mentee = personReader.readPerson("Stanislau");
            mentor = personReader.readPerson();
        }

        System.out.println(mentee);
        System.out.println(mentor);
    }

}
