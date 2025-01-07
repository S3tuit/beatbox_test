package chat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

interface Animal {

    public void eat();
}

class Cat implements Animal, Comparable<Cat> {

    String name;
    int weight;

    public Cat(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }


    @Override
    public void eat() {
        System.out.println("Cat");
    }

    @Override
    public int compareTo(Cat o) {
        if (this.weight > o.weight) {
            return 1;
        } else if (this.weight < o.weight) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

public class Playground {

    public static void main(String[] args) {
        ArrayList<Cat> cats = new ArrayList<Cat>();
        cats.add(new Cat("bob", 15));
        cats.add(new Cat("bub", 14));
        cats.add(new Cat("bab", 13));
        Collections.sort(cats);
        System.out.println(cats);
    }
}
