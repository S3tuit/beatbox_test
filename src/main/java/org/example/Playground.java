package org.example;

public class Playground {
    int x = 11;
    MyInner inner = new MyInner();

    public static void main(String[] args) {
        Playground p = new Playground();
        System.out.println(p.x);
        p.inner.go();
        System.out.println(p.x);
    }

    class MyInner {
        void go(){
            x = 42;
        }
    }
}
