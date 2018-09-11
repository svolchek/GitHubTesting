package util;

import java.util.*;
import java.util.function.Function;

public class Cat {
    int age;
    int height;
    String name;
    static  List<Cat> cats = new ArrayList<>();

    public Cat(String name, int age, int height) {
        this.name=name;
        this.age = age;
        this.height = height;
    }
    void changeName(){
        this.name="old"+this.name;
    }

    static Comparator<Cat> comparator = (x, y)->(x.age-y.age);
    static Function<Integer, Cat> addAge = x->new Cat(String.format("new_cat%d",x),x,x*x);

    public static void main(String[] args) {
        Cat vasya = new Cat("vasya3",15,150);
        vasya.changeName();
        Cat murka = new Cat("murka1",10,130);
        murka.changeName();
        Cat borka = new Cat("borka2",12,110){
            @Override
            void changeName() {
                this.name="new_+"+this.name;
            }
        };
        borka.changeName();
       cats.add(vasya);
       cats.add(murka);
       cats.add(borka);
       cats.add(addAge.apply(25));
       cats.add(addAge.apply(15));

        Collections.sort(cats, (a,b)->(a.height-b.height));

//        Collections.sort(cats,Cat.comparator);
//        Collections.reverse(cats);
        cats.forEach(s-> System.out.println(s.name+" "+s.height));

    }
}
