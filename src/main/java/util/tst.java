package util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class tst {
    int age;
    String name;

    public tst(int age, String name) {
        this.age = age;
        this.name = name;
    }
    static void changeAge(tst tst1){
        tst1.age+=1;
    }

    public static void main(String[] args) {
        String s1 =null ;
        String s2 = "bcccb";
        Consumer<tst> changeAge = s->changeAge(s);
        tst tst1 = new tst(12, "vasya");
        tst tst2 = new tst(15, "petya");
        tst tst3 = new tst(9, "olya");
        tst tst6 = new tst(9, "olya1");
        tst tst7 = new tst(9, "olya2");
        tst tst4 = new tst(11, "mitya");


        List<tst> tstList = Arrays.asList(tst1,tst2,tst3,tst4,tst6,tst7);

        Comparator<tst> comparatorTST =Comparator.comparing(s->s.age);
        tstList.forEach(s->changeAge.accept(s));
        tstList.forEach(s->tst.changeAge(s));

       tstList.forEach(changeAge::accept);
        tstList.forEach(tst::changeAge);
        tstList.stream().sorted(comparatorTST).forEach(s->System.out.println(s.name+" "+s.age));
        Runnable myRun = ()->System.out.println("ky-ky");
        new Thread(myRun).start();
       // new Thread(new Runnable() = ()->System.out.println("ky-ky")).start();



    }
}

