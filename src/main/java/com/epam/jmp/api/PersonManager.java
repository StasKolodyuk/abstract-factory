package com.epam.jmp.api;


import com.epam.jmp.api.db.DBPersonManager;
import com.epam.jmp.api.file.FilePersonManager;

public interface PersonManager {

    PersonReader getReader() throws ResourceException;
    PersonWriter getWriter() throws ResourceException;

    static PersonManager from(String filePath) {
        return new FilePersonManager(filePath);
    }

    static PersonManager from(String dbUrl, String login, String password) throws ResourceException {
        return new DBPersonManager(dbUrl, login, password);
    }

}
