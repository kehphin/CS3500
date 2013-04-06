// Kevin (Yang) Yang
// kyang@ccs.neu.edu
// CS3500
// Assignment 3: FListDouble.java


// FListDouble Abstract Class
public abstract class FListDouble {

  // isEmptyMethod : -> Boolean
  // Is the FListDouble empty?
  abstract boolean isEmptyMethod();

  // getMethod : Integer -> Double
  // Returns the last element of the list.
  abstract Double getMethod(Integer n);

  // setMethod : Integer Double -> FListDouble
  // sets the nth element from the end of the list to the given Double.
  abstract FListDouble setMethod(Integer n, Double y);

  // sizeMethod : -> int
  // Determines the size of the FListDouble.
  abstract int sizeMethod();

  // containsMethod : Double -> boolean
  // Does the FListDouble contain the given double?
  abstract boolean containsMethod(Double y);

  // emptyList : -> FListDouble
  // Constructs a new instance of EmptyList.
  public static FListDouble emptyList() {
    return new EmptyList();
  }

  // add : FListDouble Double -> FListDouble
  // Constructs a new instance of Add.
  public static FListDouble add(FListDouble f, Double x) {
    return new Add(f, x);
  }

  // isEmpty : FListDouble -> Boolean
  // Is the FListDouble empty?
  public static boolean isEmpty(FListDouble f) {
    return f.isEmptyMethod();
  }

  // get : FListDouble Integer -> Double
  // Returns the last element of the list.
  public static Double get(FListDouble f, Integer n) {
    return f.getMethod(n);
  }

  // set : FListDouble Integer Double -> FListDouble
  // sets the nth element from the end of the list to the given Double.
  public static FListDouble set(FListDouble f, Integer n, Double y) {
    return f.setMethod(n, y);
  }

  // size : FListDouble -> int
  // Determines the size of the FListDouble.
  public static int size(FListDouble f) {
    return f.sizeMethod();
  }

  // contains : FListDouble Double -> boolean
  // Does the FListDouble contain the given double?
  public static boolean contains(FListDouble f, Double y) {
    return f.containsMethod(y);
  }

  // equals : Object -> Boolean
  // Are the given object and FListDouble equal?
  public boolean equals(Object o) {
    if (o instanceof FListDouble) {
      FListDouble f2 = ((FListDouble) o);
      return this.equalsHelper(f2);
    } else {
      return false;
    }
  }

  // equalsHelper : FListDouble -> Boolean
  // Determines if both FListDoubles are equal.
  public boolean equalsHelper(FListDouble f2) {
    if (FListDouble.size(this) == FListDouble.size(f2)) {
      for (int i=0; i<FListDouble.size(this); i++) {
        if (FListDouble.get(this, i).equals(FListDouble.get(f2, i))) {
        } else {
          return false;
        }
      }
      return true;
    } else {
      return false;
    }
  }


  // hashCode : -> int
  // gives a hashCode for the FListDouble.
  public int hashCode() {
    return FListDouble.size(this);
  }
}


// EmptyList Concete Subclass
class EmptyList extends FListDouble {

  // Constructor
  EmptyList() {
  }

  // isEmptyMethod : -> Boolean
  // Is the EmptyList empty?
  boolean isEmptyMethod() {
    return true;
  }

  // getMethod : Integer -> Double
  // Returns the last element of the list.
  Double getMethod(Integer n) {
    throw new RuntimeException("get on empty");
  }

  // setMethod : Integer Double -> FListDouble
  // sets the ith element of the list to the given Double.
  FListDouble setMethod(Integer n, Double y) {
    throw new RuntimeException("set on empty");
  }

  // sizeMethod : -> int
  // Determines the size of the EmptyList.
  int sizeMethod() {
    return 0;
  }

  // containsMethod : Double -> boolean
  // Does the EmptyList contain the given double?
  boolean containsMethod(Double y) {
    return false;
  }

  // toString : -> String
  // Converts the EmptyList into a String
  public String toString() {
    return "[]";
  }
}


// Add Concrete Subclass
class Add extends FListDouble {
  FListDouble f; // Rest of the list.
  Double x; // Outermost Double element of the list.

  // Constructor
  Add(FListDouble f, Double x) {
    this.f = f;
    this.x = x;
  }

  // isEmptyMethod : -> Boolean
  // Is the FListDouble empty?
  boolean isEmptyMethod() {
    return false;
  }

  // getMethod : Integer -> Double
  // Returns the last element of the list.
  Double getMethod(Integer n) {
    if (n==0) {
      return this.x;
    } else {
      return FListDouble.get (f, n-1);
    }
  }

  // setMethod : Integer Double -> FListDouble
  // sets the nth element from the end of the list to the given Double.
  FListDouble setMethod(Integer n, Double y) {
    if (n==0) {
      return FListDouble.add(f, y);
    } else {
      return FListDouble.add(FListDouble.set (f, n-1, y), x);
    }
  }

  // sizeMethod : -> int
  // Determines the size of the FListDouble.
  int sizeMethod() {
    return 1 + FListDouble.size (f);
  }

  // containsMethod : Double -> boolean
  // Does the FListDouble contain the given double?
  boolean containsMethod(Double y) {
    if (x.equals(y)) {
      return true;
    } else {
      return FListDouble.contains(f, y);
    }
  }

  // toString : -> String
  // Converts the FListDouble into a String.
  public String toString() {
    if (FListDouble.isEmpty (f)) {
      return "[" + x.toString() + "]";
    } else {
      return "[" + x.toString() + ", " +
            f.toString().substring(1, f.toString().length());
    }
  }
}

