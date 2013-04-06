// Kevin (Yang) Yang
// kyang@ccs.neu.edu
// CS3500
// Assignment 1: FSet.java

// FSet Class
public abstract class FSet {
  abstract int sizeMethod();
  abstract boolean isEmptyMethod();
  abstract boolean containsMethod(Integer k);
  abstract boolean isSubsetMethod(FSet s);
  abstract FSet removeMethod(Integer k);
  abstract FSet unionMethod(FSet s);
  abstract FSet intersectMethod(FSet s);

  // Static Methods

  // Creates a new empty FSet.
  public static FSet emptySet() {
    return new EmptySet();
  }
 
  // Creates a new non-empty FSet.
  public static FSet include(FSet s, Integer k) {
    return new Include(s, k);
  }
  
  // Adds an integer to the FSet.
  public static FSet add(FSet s, Integer k) {
    if (FSet.contains(s, k)) {
      return s;
    } else {
      return FSet.include(s, k);
    }
  }
  
  // Determines how many unique integers are in the FSet.
  public static int size(FSet s) {
    return s.sizeMethod();
  }
  
  // Is the FSet empty?
  public static boolean isEmpty(FSet s) {
    return s.isEmptyMethod();
  }

  // Does the FSet contain the specified integer?
  public static boolean contains(FSet s, Integer k) {
    return s.containsMethod(k);
  }

  // Is FSet s1 a subset of FSet s2?
  public static boolean isSubset(FSet s1, FSet s2) {
    return s1.isSubsetMethod(s2);
  }
  
  // Removes the first integer from the FSet.
  public static FSet remove(FSet s, Integer k) {
    return s.removeMethod(k);
  }

  // Creates an FSet that is the union of the two.
  public static FSet union(FSet s1, FSet s2) {
    return s1.unionMethod(s2);
  }

  // Creates an FSet that is the intersection of the two.
  public static FSet intersect (FSet s1, FSet s2) {
    return s1.intersectMethod(s2);
  }


  // Dynamic Methods

  // toString : -> String
  // Converts the FSet into a string
  public String toString() {
    return "{...(" + FSet.size(this) +                                                     " elements, contains 2: " + FSet.contains(this, 2) + ")...}";
  }

  // equals : Object -> Boolean
  // Are the given object and FSet equal?
  public boolean equals(Object o) {
    if (o instanceof FSet) {
      FSet f = ((FSet) o);
      if (this.isSubsetMethod(f) && f.isSubsetMethod(this)) {
        return true;
      } else {
        return false;
      }

    } else {
      return false;
    }
  }

  // hashCode : -> int
  // Assigns a hashCode to the FSet.
  public int hashCode() {
    return 1;
  }
}


// Subclass EmptySet
class EmptySet extends FSet {

  // Constructor
  EmptySet() {
  }
  
  // sizeMethod : -> int
  // Determines the size of the EmptySet.
  int sizeMethod() {
    return 0;
  }
  
  // isEmptyMethod : -> Boolean
  // Is the EmptySet empty?
  boolean isEmptyMethod() {
    return true;
  }

  // containsMethod : Integer -> Boolean
  // Does the EmptySet contain the given Integer?
  boolean containsMethod(Integer i) {
    return false;
  }
	
  // isSubsetMethod : FSet -> Boolean
  // is the EmptySet a subset of the given FSet?
  boolean isSubsetMethod(FSet s) {
    return true;
  }

  // removeMethod : Integer -> FSet
  // Removes the given Integer from the EmptySet.
  FSet removeMethod(Integer i) {
    return this;
  }

  // unionMethod : FSet -> FSet
  // Forms a new FSet with unique integers from EmptySet and the given FSet.
  FSet unionMethod(FSet s) {
    return s;
  }

  // intersectMethod : FSet -> FSet
  // Returns a new FSet with the common integers from EmptySet and FSet.
  FSet intersectMethod(FSet s) {
    return new EmptySet();
  }
}


// Subclass Include
class Include extends FSet {
  FSet s; // Rest of FSet
  int k; // First of FSet 

  // Constructor
  Include(FSet s, int k) {
    this.s = s;
    this.k = k;
  }

  // sizeMethod : -> int
  // Determines the size of FSet.
  int sizeMethod() {
    if (FSet.contains(s, k)) {
      return FSet.size(s);
    } else {
      return 1 + FSet.size(s);
    }
  }

  // isEmptyMethod : -> Boolean
  // Is the FSet empty?
  boolean isEmptyMethod() {
    return false;
  }

  // containsMethod : Integer -> Boolean
  // Does the FSet contain the given Integer?
  boolean containsMethod(Integer i) {
    if (i.equals(k)) {
      return true;
    } else {
      return FSet.contains(s, i);
    }
  }

  // isSubsetMethod : FSet -> Boolean
  // is the FSet a subset of the given FSet?
  boolean isSubsetMethod(FSet s2) {
    if (FSet.contains(s2, k)) {
      return FSet.isSubset (s, s2);
    } else {
      return false;
    }
  }

  // removeMethod : Integer -> FSet
  // Removes the given Integer from the FSet.
  FSet removeMethod(Integer i) {
    if (i.equals(k)) {
      return FSet.remove(s, i);
    } else {
      return FSet.include(FSet.remove(s, i), k);
    }
  }

  // unionMethod : FSet -> FSet
  // Forms a new FSet with unique integers from given FSets.
  FSet unionMethod(FSet s2) {
    if (FSet.contains(s2, k)) {
      return FSet.union(s, s2);
    } else {
      return FSet.include(FSet.union(s, s2), k);
    }
  }

  // intersectMethod : FSet -> FSet
  // Returns a new FSet with the common integers from given FSets.
  FSet intersectMethod(FSet s2) {
    if (FSet.contains(s2, k)) {
      return FSet.include (FSet.intersect(s,s2), k);
    } else {
      return FSet.intersect(s, s2);
    }
  }
}


	

