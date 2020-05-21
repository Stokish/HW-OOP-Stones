package kz.aitu.oop.practice.practice5;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Necklace{
    //db for connecting to database
    private DB db;
    //neck is brief from necklace. it will store stones and their quantity
    private HashMap<Stones, Integer> neck;
    //will store all cost and weight of stones used in necklace
    private double all_cost;
    private double all_weight;

    private Precious pr;
    private SemiPrecious spr;

    //Constructor that will automatically connect to my database and make default values for all other
    public Necklace() throws SQLException, ClassNotFoundException {
        this.db = new DB();
        this.neck = new HashMap<Stones,Integer>();
        this.all_cost = 0;
        this.all_weight = 0;
        this.pr = new Precious();
        this.spr = new SemiPrecious();
    }

    //with this constructor you can access your database with your stones
    public Necklace(String conURL,String conUser, String conPassword) throws SQLException, ClassNotFoundException {
        this.db = new DB(conURL, conUser, conPassword);
        this.neck = new HashMap<Stones, Integer>();
        this.all_cost = 0;
        this.all_weight = 0;
        this.pr = new Precious();
        this.spr = new SemiPrecious();
    }

    //with this constructor you can access your database with your stones
    public Necklace(DB db) {
        this.db = db;
        this.neck = new HashMap<Stones, Integer>();
        this.all_cost = 0;
        this.all_weight = 0;
        this.pr = new Precious();
        this.spr = new SemiPrecious();
    }


    //Pushing stone from database to necklace
    public void NecklacePush(String stone_name, int quant) throws SQLException {
        //getting stone from database
        ResultSet byName = this.db.QueryDB("SELECT * FROM stone where stone_name = '" + stone_name + "';");
        if( byName.next()) {
            //storing main characteristics
            String name = byName.getString("stone_name");
            double cost =  byName.getDouble("stone_cost");
            double weight = byName.getDouble("stone_weight");
            int type = byName.getInt("stone_type_id");

            //adding a stone and quantity based on a  type
            Stones s = (type == 1)? pr.NewStone(name,cost,weight) : spr.NewStone(name, cost, weight);
            neck.put(s, quant);

            //adding cost and weight of a stone to variables for cost and weight of necklace
            all_cost += s.cost(neck.get(s));
            all_weight += s.weight(neck.get(s));
        }
    }



    //GETTERS(ACCESSORS)
    public double getAll_cost() {
        return all_cost;
    }

    public double getAll_weight() {
        return all_weight;
    }

    public HashMap<Stones, Integer> getNeck() {
        return neck;
    }




    //SETTERS(MUTATORS)
    public void setAll_weight(int all_weight) {
        this.all_weight = all_weight;
    }

    public void setAll_cost(int all_cost) {
        this.all_cost = all_cost;
    }

    //toString for a necklace
    @Override
    public String toString(){
        String[] n = {""};
        System.out.println("\nUsed Stones:");
        neck.forEach(
                (stone, num) -> {
            n[0] = n[0] + (
                    stone.toString()
                            + "  $" + stone.cost(1)
                            + "  " + stone.weight(1) + "carat"
                            + " x" + num
                            + "( $" + stone.cost(num)
                            + ", " + stone.weight(num) + " carat )\n"
            );
        });
        return n[0];
    }
}
