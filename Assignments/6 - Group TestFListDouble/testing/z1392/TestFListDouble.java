// Kevin (Yang) Yang
// Mostafa Al Khonaizi
// Tejorn Davis
//
// kyang@ccs.neu.edu
// khonaimn@ccs.neu.edu
// davis.tej@husky.neu.edu
//
// CS3500
// Assignment 6: TestFListDouble.java


// TestFListDouble Class
public class TestFListDouble {

    // Runs the tests.
               
    public static void main(String args[]) {
        TestFListDouble test = new TestFListDouble();

        // Test with 0-argument FListDouble.emptyList().

        System.out.println("Testing 0-argument emptyList()");
        test.creation();
        test.accessors(0);
        test.usual();
        test.accessors(0);    // test twice to detect side effects
        test.usual();

        test.summarize();
    }

    // Prints a summary of the tests.

    private void summarize () {
        System.out.println();
        System.out.println (totalErrors + " errors found in " +
                            totalTests + " tests.");
    }

    public TestFListDouble () { }

    // Double objects to serve as elements.

    private final Double one   = new Double(1.0);
    private final Double two   = new Double(2.0);
    private final Double three = new Double(3.0);
    private final Double four  = new Double(4.0);
    private final Double five  = new Double(5.0);
    private final Double six   = new Double(6.0);

    // FListDouble objects to be created and then tested.

    private FListDouble f0;    // [ ]
    private FListDouble f1;    // [ 1.0 ]
    private FListDouble f2;    // [ 2.0, 1.0 ]
    private FListDouble f3;    // [ 3.0, 2.0, 1.0 ]
    private FListDouble f4;    // [ 4.0, 3.0, 2.0, 1.0 ]
    private FListDouble f5;    // [ 1.0, 2.0, 1.0 ]
    private FListDouble f6;    // [ 3.0, 4.0, 2.0, 1.0 ]
    private FListDouble f7;    // [ 1.0, 2.0, 2.0, 1.0 ]

    private FListDouble s1;    // [ 1.0 ]
    private FListDouble s2;    // [ 2.0 ]
    private FListDouble s3;    // [ 3.0 ]
    private FListDouble s4;    // [ 4.0 ]

    private FListDouble s12;    // [ 1.0, 2.0 ]
    private FListDouble s13;    // [ 1.0, 3.0 ]
    private FListDouble s14;    // [ 1.0, 4.0 ]
    private FListDouble s23;    // [ 2.0, 3.0 ]
    private FListDouble s34;    // [ 3.0, 4.0 ]

    private FListDouble s123;   // [ 1.0, 2.0, 3.0 ]
    private FListDouble s124;   // [ 1.0, 2.0, 4.0 ]
    private FListDouble s134;   // [ 1.0, 3.0, 4.0 ]
    private FListDouble s234;   // [ 2.0, 3.0, 4.0 ]

    // Creates some FListDouble objects.

    private void creation () {
        try {
            f0 = FListDouble.emptyList();
            f1 = FListDouble.add (f0, one);
            f2 = FListDouble.add (f1, two);
            f3 = FListDouble.add (f2, three);
            f4 = FListDouble.add (f3, four);
            f5 = FListDouble.add (f2, one);
            f6 = FListDouble.add (FListDouble.add (f2, four), three);

            f7 = FListDouble.add (f0, one);
            f7 = FListDouble.add (f7, two);
            f7 = FListDouble.add (f7, two);
            f7 = FListDouble.add (f7, one);

            s1 = FListDouble.add (f0, one);
            s2 = FListDouble.add (f0, two);
            s3 = FListDouble.add (f0, three);
            s4 = FListDouble.add (f0, four);

            s12 = FListDouble.add (f1, new Double(2.0));
            s13 = FListDouble.add (f1, new Double(3.0));
            s14 = FListDouble.add (f1, new Double(4.0));
            s23 = FListDouble.add (s2, new Double(3.0));
            s34 = FListDouble.add (s3, new Double(4.0));

            s123 = FListDouble.add (s12, three);
            s124 = FListDouble.add (s12, four);
            s134 = FListDouble.add (s13, four);
            s234 = FListDouble.add (s23, four);

            s134 = FListDouble.add (s134, four);
            s234 = FListDouble.add (s234, four);

        }

        // Check for exception.

        catch (Exception e) {
            System.out.println("Exception thrown during creation tests:");
            System.out.println(e);
            assertTrue ("creation", false);
        }
    }

    // Tests the accessors.

    private void accessors (int nargs) {
        try {

            //Tests Empty subclass.
            
            assertTrue ("empty", FListDouble.isEmpty (f0));
            assertFalse ("nonempty", FListDouble.isEmpty (f1));
            assertFalse ("nonempty", FListDouble.isEmpty (f3));

            // Tests for size Method.

            assertTrue ("f0.size()", FListDouble.size (f0) == 0);
            assertTrue ("f1.size()", FListDouble.size (f1) == 1);
            assertTrue ("f2.size()", FListDouble.size (f2) == 2);
            assertTrue ("f3.size()", FListDouble.size (f3) == 3);
            assertTrue ("f4.size()", FListDouble.size (f4) == 4);
            assertTrue ("f5.size()", FListDouble.size (f5) == 3);
            assertTrue ("f7.size()", FListDouble.size (f7) == 4);

            // Tests for contains Method.

            assertFalse ("contains01", FListDouble.contains (f0, one));
            assertFalse ("contains04", FListDouble.contains (f0, four));
            assertTrue  ("contains11", FListDouble.contains (f1, one));
            assertTrue  ("contains11new", FListDouble.contains (f1, 
                  new Double(one)));
            assertFalse ("contains14", FListDouble.contains (f1, four));
            assertTrue  ("contains21", FListDouble.contains (f2, one));
            assertFalse ("contains24", FListDouble.contains (f2, four));
            assertTrue  ("contains31", FListDouble.contains (f3, one));
            assertFalse ("contains34", FListDouble.contains (f3, four));
            assertTrue  ("contains41", FListDouble.contains (f4, one));
            assertTrue  ("contains44", FListDouble.contains (f4, four));
            assertTrue  ("contains51", FListDouble.contains (f5, one));
            assertFalse ("contains54", FListDouble.contains (f5, four));

            // Tests for get Method.

            assertTrue ("get f1 0", FListDouble.get (f1, 0).equals(one));
            assertTrue ("get f2 0", FListDouble.get (f2, 0).equals(two));
            assertTrue ("get f2 1", FListDouble.get (f2, 1).equals(one));
            assertTrue ("get f2 0", FListDouble.get (f2, 0).equals(two));
            assertTrue ("get f7 3", FListDouble.get (f7, 3).equals(one));
            assertTrue ("get f6 2", FListDouble.get (f6, 2).equals(two));
            assertFalse ("get s3 0", FListDouble.get (s3, 0).equals(one));
            assertFalse ("get s234 2", FListDouble.get (s234, 
                  2).equals(one));
            assertFalse ("get s14 0", FListDouble.get (s14, 
                  0).equals(one));

            // Tests for set Method.

            assertTrue ("set f1 0 three", FListDouble.set (f1, 
                  0, three).equals(s3));
            assertFalse ("set s3 0 two", FListDouble.set (s3, 
                  0, two).equals(s4));

            System.out.println(FListDouble.set (f5, 
                  0, three).toString());
            System.out.println(s123.toString());

            assertTrue ("set f5 0 three", FListDouble.set (f5, 
                  0, three).equals(s123));
            assertTrue ("set s13 0 four", FListDouble.set (s13, 
                  0, four).equals(s14));
            assertFalse ("set s234 2 five", FListDouble.set (s234, 
                  2, five).equals(s123));
            assertFalse ("set s4 0 one", FListDouble.set (s4, 
                  0, one).equals(s2));

        }

        // Check for exception.

        catch (Exception e) {
            System.out.println("Exception thrown during accessors tests:");
            System.out.println(e);
            assertTrue ("accessors", false);
        }
    }

    // Tests the usual overridden methods.

    private void usual () {
        try {

            // Tests for toString Method.

            assertTrue ("toString0",
                        f0.toString().equals("[]"));
            assertTrue ("toString1",
                        f1.toString().equals("[1.0]"));
            assertTrue ("toString7",
                        f7.toString().equals("[1.0, 2.0, 2.0, 1.0]"));
            
            // Tests for equals Method.

            assertTrue ("equals00", f0.equals(f0));
            assertTrue ("equals33", f3.equals(f3));
            assertTrue ("equals55", f5.equals(f5));

            assertFalse ("equals01", f0.equals(f1));
            assertFalse ("equals02", f0.equals(f2));
            assertFalse ("equals10", f1.equals(f0));
            assertFalse ("equals12", f1.equals(f2));
            assertFalse ("equals21", f2.equals(f1));
            assertFalse ("equals23", f2.equals(f3));
            assertFalse ("equals35", f3.equals(f5));

            assertFalse ("equals0string", f0.equals("just a string"));
            assertFalse ("equals4string", f4.equals("just a string"));

            assertFalse ("equals0null", f0.equals(null));
            assertFalse ("equals1null", f1.equals(null));

            // Tests for hashCode Method.

            assertTrue ("hashCode00", f0.hashCode() == f0.hashCode());
            assertTrue ("hashCode44", f4.hashCode() == f4.hashCode());
            assertTrue ("hashCode46", f4.hashCode() == f6.hashCode());
            assertFalse ("hashCode27", f2.hashCode() == f7.hashCode());
        }

        // Check for exception.

        catch (Exception e) {
            System.out.println("Exception thrown during usual tests:");
            System.out.println(e);
            assertTrue ("usual", false);
        }
    }

////////////////////////////////////////////////////////////////

    private int totalTests = 0;       // tests run so far
    private int totalErrors = 0;      // errors so far

    // For anonymous tests.  Deprecated.

    private void assertTrue (boolean result) {
      assertTrue ("anonymous", result);
    }

    // Prints failure report if the result is not true.

    private void assertTrue (String name, boolean result) {
        if (! result) {
            System.out.println ();
            System.out.println ("***** Test failed ***** "
                                + name + ": " +totalTests);
            totalErrors = totalErrors + 1;
        }
        totalTests = totalTests + 1;
    }

    // For anonymous tests.  Deprecated.

    private void assertFalse (boolean result) {
        assertTrue (! result);
    }

    // Prints failure report if the result is not false.

    private void assertFalse (String name, boolean result) {
        assertTrue (name, ! result);
    }

}
