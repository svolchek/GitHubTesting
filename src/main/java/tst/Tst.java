package tst;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Tst {
    int age;
    String name;

    public Tst(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public static void main(String[] args) throws IOException {


        String s1 =null ;
        String s2 = "bcccb";
        Tst tst1 = new Tst(12, "vasya");
        Tst tst2 = new Tst(15, "petya");
        Tst tst3 = new Tst(9, "olya");
        Tst tst6 = new Tst(9, "olya1");
        Tst tst7 = new Tst(9, "olya2");
        Tst tst4 = new Tst(11, "mitya");

        List<Tst> tstList = Arrays.asList(tst1,tst1,tst2,tst3,tst4,tst6,tst7);

        Runnable runnable = ()-> System.out.println("ky-ky");

        new Thread(runnable).start();

        tstList.stream().forEach(s-> System.out.println(s.name+" "+s.age));
        Comparator<Tst> comparatorAge = Comparator.comparing(p->p.age);
        Comparator<Tst> comparatorName = Comparator.comparing(p->p.name);

        System.out.println("++++++++++++++++++++++");
        tstList.stream().sorted(comparatorAge).distinct().forEach(s-> System.out.println(s.name+" "+s.age));
        System.out.println(tstList.stream().anyMatch(s->s.age==16));
        Optional<Tst> optionalTst = tstList.stream().reduce((t1, t2)->new Tst(t1.age+t2.age,t1.name+"#"+t2.name));
        System.out.println(optionalTst.get().name+"    age: "+optionalTst.get().age);
       // tstList.stream().peek(l->{l.name="unknown_user";l.age=(Integer)(int)(Math.random()*20);}).forEach(s->System.out.println("name: "+s.name+" age:"+s.age));

        Optional<Tst> first =tstList.stream().findFirst();
        System.out.println(first.get().name);

       // Function<Comparator,Comparator> comparatorFunction = x->Comparator.comparing().

        Function<Integer,Integer> someFunction = x->x*3;
        System.out.println(someFunction.apply(15));
        Function<Integer,Character> someFunctionS = x->(char)(int)x;
        Function<Integer,Function<Integer,Integer>> addFunction = x->y->x+y;

        System.out.println(addFunction.apply(3).apply(5));

        System.out.println(someFunction.apply(someFunction.apply(5)));
        System.out.println(someFunctionS.apply(76));
        System.out.println("-----------------------------------------");
        Function<Integer,Comparator<Tst>> comparatorFunction = x-> Comparator.comparingInt(o -> Math.abs(o.age - x));

        Comparator<Tst> comparator12 = comparatorFunction.apply(11);
        tstList.stream().sorted(comparator12).forEach(s-> System.out.println(s.name+" "+s.age));
        Stream<String> streamFromFiles = Files.lines(Paths.get("c:\\Users\\Siarhei_Volchak\\Desktop\\webdrivers\\hub_3.14.0_firefox.bat "));
        streamFromFiles.forEach(System.out::println);









    }
}

