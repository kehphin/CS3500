/**
 * Client code using StackInt with IntVisitor
 */

public class ClientStackInt {

    public static void main(String args[]) {
	StackInt s = StackInt.empty();
        s = StackInt.push (s, 1);
        s = StackInt.push (s, 2);
        s = StackInt.push (s, 3);
        s = StackInt.push (s, 4);
	printstack(s);
        System.out.println();
        IntVisitor v5 = new Adder (5);
        IntVisitor v99 = new Adder (99);
        printstack (s.accept (v5));
        System.out.println();
        printstack (s.accept (v99));
        System.out.println();
	IntVisitor vsq = new Square();
	printstack (s.accept (vsq));
        System.out.println();
    }

    private static void printstack (StackInt s) {
        if (! (StackInt.isEmpty (s))) {
            System.out.println(StackInt.top (s));
            printstack (StackInt.pop (s));
        }
    }

    private static class Adder implements IntVisitor {
        private int increment;
        Adder (int increment) { this.increment = increment; }
        public int visit (int n) { return n + increment; }
    }

    private static class Square implements IntVisitor{
	public int visit(int n){ return (n*n);}
    }

}