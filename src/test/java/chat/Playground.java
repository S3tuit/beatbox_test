package chat;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

interface Animal {
    
    public void eat();
}

class Cat implements Animal, Comparable<Cat> {

    String name;
    int weight;
    int age;

    public Cat(String name, int weight, int age) {
        this.name = name;
        this.weight = weight;
        this.age = age;
    }
    
    @Override
    public boolean equals(Object o) {
        Cat cat = (Cat) o;
        return name.equals(cat.name) && weight == cat.weight && age == cat.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, age);
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

class Dog implements Animal {
    String name;
    int weight;

    public Dog(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }


    @Override
    public void eat() {
        System.out.println("Dog");
    }
}

public class Playground {

    public static void main(String[] args) {
        Playground p = new Playground();
        p.go();
    }

    public void go(){
        ArrayList<Cat> cats = new ArrayList<Cat>();
        cats.add(new Cat("bob", 15, 1));
        cats.add(new Cat("bub", 14, 0));
        cats.add(new Cat("bub", 14, 0));
        Cat test = new Cat("bab", 13, 2);
        cats.add(test);

    }
// xxvvxvvvx

    class nameComparator implements Comparator<Cat> {
        @Override
        public int compare(Cat o1, Cat o2) {
            if (o1.age > o2.age) {
                return 1;
            } else if (o1.age < o2.age) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}
