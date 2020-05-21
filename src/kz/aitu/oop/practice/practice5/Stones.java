package kz.aitu.oop.practice.practice5;

public interface Stones {
    //Methods that will be overridden in Precious and SemiPrecious
    double weight(int number);
    double cost(int num);
    Stones NewStone(String name, double cost, double weight);
}
