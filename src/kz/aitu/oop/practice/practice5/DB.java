package kz.aitu.oop.practice.practice5;

import java.sql.*;


//DB - Data Base
// a class for any database
public class  DB {

    private Connection con;
    private Statement stmt;

    //By default it will connect to my database called stones
    public DB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stones?autoReconnect=true&useSSL=false","root","123456");
        this.stmt =  con.createStatement();
    }

    //Connecting to others databases
    public DB(String conURL,String conUser, String conPassword) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        this.con = DriverManager.getConnection( conURL, conUser, conPassword);
        this.stmt =  con.createStatement();
    }

    //Closing connection
    public void Close() throws SQLException {
        con.close();
    }

    //Making Queries
    public ResultSet QueryDB(String query) throws SQLException {
        return getStmt().executeQuery(query);
    }

    //Getters
    public Connection getCon() {
        return con;
    }

    public Statement getStmt() {
        return stmt;
    }

    //Setters
    public void setCon(String conURL,String conUser, String conPassword) throws SQLException {
        this.con = DriverManager.getConnection( conURL, conUser, conPassword);
        setStmt();
    }

    public void setStmt() throws SQLException {
        this.stmt =  con.createStatement();
    }



}
