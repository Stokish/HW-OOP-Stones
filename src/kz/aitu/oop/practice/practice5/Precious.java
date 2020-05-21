package kz.aitu.oop.practice.practice5;

public class Precious implements Stones{
    private double weight;
    private String name;
    private double cost;

    //Constructors
    public Precious(String name, double cost,double weight) {
        this.weight = weight;
        this.name = name;
        this.cost = cost;
    }
    public Precious(){
        this.weight = 0;
        this.name = " ";
        this.cost = 0;
    }



    //Overridden methods from Interface (Stones)
    //weight method will give only 95% of original weight for Precious Stones
    @Override
    public double weight(int number) {
        return getWeight() * number * 0.95;
    }

    //cost method will give an additional 20%  of original weight for Precious Stones
    @Override
    public double cost(int num) {
        return num * getCost()* 1.20;
    }

    @Override
    public Stones NewStone(String name, double cost, double weight) {
        return new Precious(name, cost, weight);
    }

    //Overridden method toString from Object.java
    @Override
    public String toString(){
        return getName() + " Precious Stone";
    }



    //Setters
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }



    //Getters
    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }


    public double getCost() {
        return cost;
    }

}
