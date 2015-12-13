package com.epam.jmp.api.file;


import com.epam.jmp.api.PersonReader;
import com.epam.jmp.api.ResourceException;
import com.epam.jmp.api.PersonManager;
import com.epam.jmp.api.PersonWriter;

public class FilePersonManager implements PersonManager {

    private String filePath;

    public FilePersonManager(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public PersonReader getReader() throws ResourceException{
        return new FilePersonReader(filePath);
    }

    @Override
    public PersonWriter getWriter() throws ResourceException {
        return new FilePersonWriter(filePath);
    }
}
