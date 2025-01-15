package chat;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Playground {

    public enum Nums { ONE, TWO, THREE};
    public Nums num;

    public Playground(Nums num) {
        this.num = num;
    }

    public static void main(String[] args) {
        Playground p = new Playground(Nums.ONE);
        p.go();
    }

    public void go() {
        switch (num) {
            case ONE: System.out.println("One"); break;
            case TWO: System.out.println("Two"); break;
            case THREE: System.out.println("Three"); break;
        }
    }

}
