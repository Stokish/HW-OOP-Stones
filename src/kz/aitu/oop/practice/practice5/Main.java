package kz.aitu.oop.practice.practice5;

import java.util.*;

public class Main{

    public static void main(String[] args) {
        //preparing
        Scanner in = new Scanner(System.in);

        System.out.println("Hello dear, customer. Here you can make a necklace on your own!");

/*just in case...*/try{
            //Down casting
            DB connector = new DBStones("jdbc:mysql://localhost:3306/stones?autoReconnect=true&useSSL=false", "root", "123456");
            DBStones info = (DBStones) connector;

            //Showing stones from Data Base
            System.out.println(info.ShowStones("precious"));
            System.out.println("\n");
            System.out.println(info.ShowStones(2));

            // Creating Necklace
            Necklace necklace = new Necklace(info);
            System.out.println("Create a necklace?\n Write Y/N");
            String create = in.nextLine();

            if(create.equals("Y")) {
                while (create.equals("Y")) {
                    //bool variables
                    boolean boolname = false;
                    boolean boolquant = false;
                    //while name of stone is not valid
                    while (!boolname) {
                        //taking an input from a user
                        System.out.println("Write name of a stone, that you want to add.");
                        String input_stone_name =  in.nextLine();

                        //checking is such stone in my DB
                        if (info.IsStoneInDB(input_stone_name)) {
                            boolname = true;
                            //while number input is not valid (user may write not an integer)
                        while (!boolquant) {
                            int quant;// quantity of a stone
                            //preventing user writing non-integer input
                                  try {
                                            System.out.println("Write quantity of it");
                                            quant = in.nextInt();
                                            boolquant = true;
                                      //pushing stone its  quantity to the necklace
                                            necklace.NecklacePush(input_stone_name, quant) ;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Please use digits!");
                                            in.nextLine();
                                        }

                          }
                        } else {
                            //Show this message in case of not finding a stone with written by user name
                            System.out.println("Wrong input, try again.");
                        }
                    }

                    in.nextLine();
                    //add another stone if user writes Y
                    System.out.println("Add another one?\n Write Y/N");
                    create = in.nextLine();
                }

            }

            //rounding all cost and all_weight to 0.000 template
            System.out.printf("\n\nTotal:\nYour necklace will cost $%.3f %n", necklace.getAll_cost());
            System.out.printf("Your necklace will weight $%.3f carat %n", necklace.getAll_weight() );

            //showing stones in necklace
            System.out.println(necklace.toString());

            //closing connection
            info.Close();
            connector.Close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}
