package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class data_source {
    private Connection cnx;
    private  String url="jdbc:mysql://localhost:3306/foodyb";
    private  String login="root";
    private  String pwd="";
    private static data_source instance;

    private data_source() {
        try {
            cnx= DriverManager.getConnection(url,login,pwd);
            System.out.println("succes");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static data_source getInstance(){
        if(instance==null)
            instance=new data_source();
        return instance;
    }


    public Connection getCnx() {
        return cnx;
    }
}
