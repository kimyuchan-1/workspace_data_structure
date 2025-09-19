package chap3_검색;

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

class Term {
    int coef; // 계수
    int exp;  // 지수
    Term() {}
    Term(int c, int e) { this.coef = c; this.exp = e; }
}

class Polynomial {
    private static Term[] TermElement = new Term[128];
    private static int capacity = 128;
    private static int free = 0; 

    private int start = -1;
    private int finish = -1; 
    private int terms = 0;


    private boolean isEmpty() {
        return (start == -1 || terms == 0);
    }

    public void newTerm(int c, int e) {
        if (c == 0) return;
        if (TermElement[free] == null) TermElement[free] = new Term();
        TermElement[free].coef = c;
        TermElement[free].exp  = e;
        free++;
    }

    public void getData(int numTerms, Random rand) {

        start = free;
        int degree = 2 * numTerms;
        int expo = degree;

        for (int i = degree; i > 0; i--) {
            int coef;
            do { coef = rand.nextInt(numTerms); } while (coef == 0);

            int newExpo;
            do { newExpo = rand.nextInt(degree); } while (newExpo >= expo);
            expo = newExpo;

            newTerm(coef, expo);
            if (expo == 0) break;
        }
        finish = free - 1;
        terms = (finish >= start) ? (finish - start + 1) : 0;
        if (terms == 0) { start = finish = -1; }
    }

  
}
public class train_실습3_12다항식공유배열연산_상급자 {
    private static final Random RAND = new Random(49);

    private static void printMenu() {
        System.out.println("1.INPUT");
        System.out.println("2.ADD");
        System.out.println("3.SUBTRACT");
        System.out.println("4.MULTIPLY");
        System.out.println("5.EVAL");
        System.out.println("6.ADDTERM");
        System.out.println("7.EXIT");
        System.out.print("선택> ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Polynomial P1 = new Polynomial();
        Polynomial P2 = new Polynomial();
        Polynomial P3 = new Polynomial();
        Polynomial P4 = new Polynomial();
        Polynomial P5 = new Polynomial();

        while (true) {
            printMenu();
            if (!sc.hasNextInt()) break;
            int choice = sc.nextInt();

            switch (choice) {
                case 1: // INPUT
                    P1.getData(5, RAND);
                    P2.getData(9, RAND);
                    System.out.print(P1);
                    System.out.print(P2);
                    break;

                case 2: // ADD
                    P3 = P1.add(P2);
                    System.out.print(P3);
                    break;

                case 3: // SUBTRACT
                    P4 = P1.subtract(P2);
                    System.out.print(P4);
                    break;

                case 4: // MULTIPLY
                    P5 = P1.multiply(P2);
                    System.out.print(P5);
                    break;

                case 5: // EVAL
                    int result = P5.eval(3);
                    System.out.println(result);
                    break;

                case 6: // ADDTERM
                    P1.addTerm(9, 9);
                    System.out.print(P1);
                    break;

                case 7: // EXIT
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return;

                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }

        System.out.println("프로그램을 종료합니다.");
        sc.close();
    }
}
