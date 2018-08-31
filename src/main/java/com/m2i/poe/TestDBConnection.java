package com.m2i.poe;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnection{

    public static void main(String[] args) {
        String Url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=sorpres2";
        try {
            Class.forName("org.postgresql.Driver");

            System.out.println("Trying to connect");
            Connection connection = DriverManager.getConnection(Url);

            System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
                    + connection.getMetaData().getDatabaseProductName());
        } catch (Exception e) {
            System.out.println("Unable to make connection with DB");
            e.printStackTrace();
        }
    }
}