package kz.aitu.oop.practice.practice5;

import kz.aitu.oop.practice.practice5.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

//Subclass of a DB for database of stones
//adds its own methods
    public class DBStones extends DB {

        //Again by default it will connect to my database
        public DBStones() throws SQLException, ClassNotFoundException {
            super();
        }

        //Connecting to other database of stones (other computer has the same database but called in other way for example)
        public DBStones(String conURL,String conUser, String conPassword) throws SQLException, ClassNotFoundException{
            super(conURL, conUser, conPassword);
        }


        //Overloaded functions ShowStones
        //Showing all stones of particular type (based on id of a type)
        public String ShowStones(int stone_type_id) throws SQLException {
            String result = "";
            ResultSet rs = QueryDB(
                            "SELECT * " +
                            "FROM stone " +
                            "WHERE stone_type_id = " + stone_type_id
            );

            if (stone_type_id == 1)
                System.out.println("Precious stones(Price for each one of them will be +20% after purchasing):");
            else if (stone_type_id == 2)
                System.out.println("Semi-Precious stones(Price for each one of them will be -5% after purchasing):");
            else
                System.out.println("No such type exist");


            while (rs.next()) {
                result +=
                        rs.getInt("stone_id")
                        + "  " + rs.getString("stone_name")
                        + "  $" + rs.getDouble("stone_cost")
                        + "  " + rs.getDouble("stone_weight") + "carat\n"
                ;
            }

            return result;
        }


    //Showing all stones of particular type (based on name of a type)
        public String ShowStones(String stone_type_name) throws SQLException {

            String result = "";
            stone_type_name = stone_type_name.toLowerCase();

            ResultSet rs = QueryDB(
                         "SELECT * " +
                         "FROM stone" +
                         " INNER JOIN stone_type ON stone.stone_type_id = stone_type.stone_type_id" +
                         " WHERE stone_type.stone_type_name= '" + stone_type_name + "';"
            );

            if (stone_type_name.equals("precious"))
                System.out.println("Precious stones(Price for each one of them will be +20% after purchasing):");
            else if (stone_type_name.equals("semi_precious"))
                System.out.println("Semi-Precious stones(Price for each one of them will be -5% after purchasing):");
            else
                System.out.println("No such type exist");


            while (rs.next()) {
                result += rs.getInt("stone_id")
                          + "  " + rs.getString("stone_name")
                          + "  $" + rs.getDouble("stone_cost")
                          + "  " + rs.getDouble("stone_weight")
                          + "carat\n";
            }
            return result;
        }



        //Checking if stone_name is in database
        public boolean IsStoneInDB(String stone_name) throws SQLException {
            ResultSet byName = QueryDB("SELECT * FROM stone where stone_name LIKE '" + stone_name + "';");
            return byName.next();
        }

}
