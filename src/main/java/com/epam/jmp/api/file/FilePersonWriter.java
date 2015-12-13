package com.epam.jmp.api.file;


import com.epam.jmp.api.ResourceException;
import com.epam.jmp.api.PersonWriter;
import com.epam.jmp.api.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FilePersonWriter implements PersonWriter {

    private String filePath;

    public FilePersonWriter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeObject(Person person) throws ResourceException {
        try {
            Files.write(Paths.get(filePath), person.toString().getBytes(), StandardOpenOption.APPEND);
        } catch(IOException e) {
            throw new ResourceException(e);
        }
    }
}
