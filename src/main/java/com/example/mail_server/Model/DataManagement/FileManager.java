package com.example.mail_server.Model.DataManagement;

public class FileManager implements IDirectory {
    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean move() {
        return false;
    }

    public boolean delete(){
        return false;
    }
}
