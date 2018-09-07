package util;

public class tst {
    public static void main(String[] args) {
        Integer s = 100;
        double p1;
        double p2;
        double div;
        int count=0;
        for (double j = 10000.0; j <= 29999.0; j++) {
            for (double i = 1000.0; i <= 9999.0; i++) {
                div = (int) (j-i);
                p1 =  j/div;
                p2 =  i/div;
                boolean eq1 = (p1%1<0.0000000000000000000000000000001);
                boolean eq2 = (p2%1<0.0000000000000000000000000000001);
                 if (eq1) {
                    if(eq2) {
                        count++;
                        System.out.println(eq1 + " i=" + i+" j="+j);
                    }
                }
            }
        }
        System.out.println(count);
    }
}
