// Kevin (Yang) Yang
// kyang@ccs.neu.edu
// CS3500
// Assignment 7: FMap.java

import java.util.*;

// An FMap is one of:
// -- Empty();
// -- Put(FMap<K,V>, K, V);

// To Represent an FMap.
public abstract class FMap<K,V> implements Iterable<K> {

  // isEmpty : -> Boolean
  // Is the FMap empty?
  public abstract boolean isEmpty();

  // size : -> Integer
  // How many K,V pairs are there?
  public abstract int size();

  // containsKey : K -> Boolean
  // Does the FMap contain the given key?
  public abstract boolean containsKey(K k0);

  // containsValue : V -> Boolean
  // Does the FMap contain the given value?
  public abstract boolean containsValue(V v0);

  // get : K -> V
  // Gets the Value from the given Key.
  public abstract V get(K k0);

  /*
  // removeKey : K -> FMap<K,V>
  // Removes the key.
  public abstract FMap<K,V> removeKey(K k0);

  // removeValue : V -> FMap<K,V>
  // Removes the value.
  public abstract FMap<K,V> removeValue(V v0);
  */

  // toString : -> String
  // Converts the FMap into a String.
  public abstract String toString();

  // equals : -> Boolean
  // Is this FMap equal to that Object?
  public abstract boolean equals(Object o);

  // sameKeys : FMap<K,V> FMap<K,V> -> Boolean
  // Does this FMap have all the same keys as the given FMap?
  public abstract boolean sameKeys(FMap<K,V> m1, FMap<K,V> m2);

  // sameValues : FMap<K,V> FMap<K,V> -> Boolean
  // Are the values of each key the same for both FMaps?
  public abstract boolean sameValues(FMap<K,V> m1, FMap<K,V> m2);

  // hashCode : -> Integer
  // Assigns this FMap a HashCode.
  public abstract int hashCode();

  // put : K V -> FMap<K,V>
  // Puts a given Key and Value to this FMap.
  public abstract FMap<K,V> put(K k, V v);

  // empty : -> FMap<K,V>
  // Creates an instance of the Empty class.
  public static <K,V> FMap<K,V> empty() {
    return new Empty<K,V>();
  }

  // empty : Comparator -> FMap<K,V> 
  // Creates an instance of the Empty class.
  public static <K,V> FMap<K,V> empty(Comparator<? super K> c) {
   /* return new Empty<K,V>(); */
    return new EmptyTree<K,V>(c);
  }

  // getAllKeys : -> ArrayList<K>
  // Creates an ArrayList of all the Keys in the FMap.
  public abstract ArrayList<K> getAllKeys(ArrayList<K> a);

  // iterator : -> KeyIterator<K>
  // Creates an iterator.
  public KeyIterator<K> iterator() {
    return new KeyIterator<K>(this.getAllKeys(new ArrayList<K>()));
  }

  // iterator : java.util.Comparator<? super K> -> KeyIterator<K>
  // Creates an iterator with keys in increasing order as
  // determined by the Comparator passed in as the argument.
  public KeyIterator<K> iterator(Comparator<? super K> c) {
    return new KeyIterator<K>(this.getAllKeys(new ArrayList<K>()), c);
  }
}


// To represent the Empty FMap.
class Empty<K,V> extends FMap<K,V> {
  // Constructor
  Empty(){}

  // put : K V -> FMap<K,V>
  // Puts a given Key and Value to this FMap.
  public FMap<K,V> put(K k, V v) {
    return new Put<K,V>(this, k, v);
  }

  // isEmpty : -> Boolean
  // Is the Empty FMap empty?
  public boolean isEmpty() {
    return true;
  }

  // size : -> Integer
  // How many K,V pairs are there in the Empty FMap??
  public int size() {
    return 0;
  }

  // containsKey : K -> Boolean
  // Does the Empty FMap contain the given key?
  public boolean containsKey(K k) {
    return false;
  }

  // containsValue : V -> Boolean
  // Does the Empty FMap contain the given value?
  public boolean containsValue(V v) {
    return false;
  }

  // get : K -> V
  // Gets the Value from the given Key.
  public V get(K k0) {
    throw new RuntimeException("Can't get K,V from an Empty FMap.");
  }

  /*
  // removeKey : K -> FMap<K,V>
  // Removes the key.
  public FMap<K,V> removeKey(K k) {
    return this;
  }

  // removeValue : V -> FMap<K,V>
  // Removes the value.
  public FMap<K,V> removeValue(V v) {
    return this;
  }
  */

  // toString : -> String
  // Converts this Empty FMap into a String.
  public String toString() {
    return "{...(" + this.size() + " entries)...}";
  }

  // equals : -> Boolean
  // Is this Empty FMap equal to that Object?
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (o instanceof FMap) {
      FMap<K,V> m1 = (FMap<K,V>) o;
      return m1.isEmpty();
    } else {
      return false;
    }
  }

  // sameKeys : FMap<K,V> FMap<K,V> -> Boolean
  // Does this FMap have all the same keys as the given FMap?
  public boolean sameKeys(FMap<K,V> m1, FMap<K,V> m2) {
    return true;
  }

  // sameValues : FMap<K,V> FMap<K,V> -> Boolean
  // Are the values of each key the same for both FMaps?
  public boolean sameValues(FMap<K,V> m1, FMap<K,V> m2) {
    return true;
  }

  // hashCode : -> Integer
  // Assigns this Empty FMap a HashCode.
  public int hashCode() {
    return this.size();
  }

  // getAllKeys : -> ArrayList<K>
  // Creates an ArrayList of all the Keys in the FMap.
  public ArrayList<K> getAllKeys(ArrayList<K> a) {
    return a;
  }
}


// To represent the Put FMap.
class Put<K,V> extends FMap<K,V> {
  FMap<K,V> m0; // The previous FMap.
  K k0; // The Key to be added.
  V v0; // The Value to be added.

  // Constructor
  Put(FMap<K,V> m0, K k0, V v0) {
    this.m0 = m0;
    this.k0 = k0;
    this.v0 = v0;
  }

  // put : K V -> FMap<K,V>
  // Puts a given Key and Value to this FMap.
  public FMap<K,V> put(K k, V v) {
    return new Put<K,V>(this, k, v);
  }

  // isEmpty : -> Boolean
  // Is the Put FMap empty?
  public boolean isEmpty() {
    return false;
  }

  // size : -> Integer
  // How many K,V pairs are there in the Empty FMap??
  public int size() {
    if (m0.containsKey(k0)) {
      return m0.size();
    } else {
      return 1 + m0.size();
    }
  }

  // containsKey : K -> Boolean
  // Does the Put FMap contain the given key?
  public boolean containsKey(K k) {
    if (k.equals(k0)) {
      return true;
    } else {
      return m0.containsKey(k);
    }
  }

  // containsValue : V -> Boolean
  // Does the Put FMap contain the given value?
  public boolean containsValue(V v) {
    if (m0.containsKey(k0)) {
      return m0.containsValue(v);
    } else if (!m0.containsKey(k0) && v.equals(v0)) {
      return true;
    } else {
      return m0.containsValue(v);
    }
  }

  // get : K -> V
  // Gets the Value from the given Key.
  public V get(K k) {
    if (m0.containsKey(k)) {
      return m0.get(k);
    } else {
      return v0;
    }
  }

  /*
  // removeKey : K -> FMap<K,V>
  // Removes the key.
  public FMap<K,V> removeKey(K k) {
    if (k.equals(k0)) {
      return m0.removeKey(k);
    } else {
      return m0.removeKey(k).put(k0, v0);
    }
  }

  // removeValue : V -> FMap<K,V>
  // Removes the value.
  public FMap<K,V> removeValue(V v) {
    if (v.equals(v0)) {
      return m0.removeValue(v);
    } else {
      return m0.removeValue(v).put(k0, v0);
    }
  }
  */

  // toString : -> String
  // Converts this Empty FMap into a String.
  public String toString() {
    return "{...(" + this.size() + " entries)...}";
  }

  // equals : -> Boolean
  // Is this Empty FMap equal to that Object?
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (o instanceof FMap) {
      FMap<K,V> that = (FMap<K,V>) o;
      if (that.isEmpty()) {
        return false;
      } else if (this.sameKeys(this, that) &&
                 that.sameKeys(that, this)) {
        return this.sameValues(this, that) && 
               this.sameValues(that, this);
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  // sameKeys : FMap<K,V> FMap<K,V> -> Boolean
  // Does this FMap have all the same keys as the given FMap?
  public boolean sameKeys(FMap<K,V> m1, FMap<K,V> m2) {
    if (m0.containsKey(k0)) {
      return m0.sameKeys(m1, m2);
    } else if (m1.containsKey(k0) == m2.containsKey(k0)) {
      return m0.sameKeys(m1, m2);
    } else {
      return false;
    }
  }

  // sameValues : FMap<K,V> -> Boolean
  // Are the values of each key the same for both FMaps?
  public boolean sameValues(FMap<K,V> m1, FMap<K,V> m2) {
    if (m0.containsKey(k0)) {
      return m0.sameValues(m1, m2);
    } else if (m1.get(k0).equals(m2.get(k0))) {
      return m0.sameValues(m1, m2);
    } else {
      return false;
    }
  }

  // hashCode : -> Integer
  // Assigns this FMap a HashCode.
  public int hashCode() {
    return this.size();
  
    /*
    if (m0.containsKey(k0)) {
      return m0.hashCode();
    } else {
      return this.get(k0).hashCode() * m0.hashCode();
    } */

  }

  // getAllKeys : ArrayList<K> -> ArrayList<K>
  // Creates an ArrayList of all the Keys in the FMap.
  public ArrayList<K> getAllKeys(ArrayList<K> a) {
    if (m0.containsKey(k0)) {
      return m0.getAllKeys(a);
    } else {
      a.add(this.k0);
      return this.m0.getAllKeys(a);
    }
  }
}


// To represent an FTree of Key-Value pairs.
abstract class FTree<K,V> extends FMap<K,V> {

  // put : -> FTree<K,V>
  // Puts a K,V pair into the FTree.
  public abstract FTree<K,V> put(K k, V v);

  // isEmpty : -> Boolean
  // Is the FTree empty?
  public abstract boolean isEmpty();

  // size : -> int
  // Determines the number of KV pairs in the FTree.
  public abstract int size();

  // containsKey : K -> Boolean
  // Does the FTree contain the given key?
  public abstract boolean containsKey(K k);

  // containsValue : V -> Boolean
  // Does the FTree contain the given value?
  public abstract boolean containsValue(V v);

  // get : K -> V
  // Gets the Value from the given Key.
  public abstract V get(K k);

  // getAllKeys : -> ArrayList<K>
  // Creates an ArrayList of all the Keys in the FTree.
  public abstract ArrayList<K> getAllKeys(ArrayList<K> a);

  // iterator : -> KeyIterator<K>
  // Creates an iterator.
  public KeyIterator<K> iterator() {
    return new KeyIterator<K>(this.getAllKeys(new ArrayList<K>()));
  }
}


// To represent an empty FTree.
class EmptyTree<K,V> extends FTree<K,V> {
  Comparator<? super K> c; // Comparator to sort FTree.

  // Constructor
  EmptyTree(Comparator<? super K> c) {
    this.c = c;
  }

  // put : -> FTree<K,V>
  // Puts a K,V pair into the FTree.
  public FTree<K,V> put(K k, V v) {
    return new Node(k, v, this, this, this.c);
  }

  // isEmpty : -> Boolean
  // Is the FTree empty?
  public boolean isEmpty() {
    return true;
  }

  // size : -> int
  // Determines the number of KV pairs in the FTree.
  public int size() {
    return 0;
  }

  // containsKey : K -> Boolean
  // Does the FTree contain the given key?
  public boolean containsKey(K k) {
    return false;
  }

  // containsValue : V -> Boolean
  // Does the FTree contain the given value?
  public boolean containsValue(V v) {
    return false;
  }

  // get : K -> V
  // Gets the Value from the given Key.
  public V get(K k) {
    throw new RuntimeException("No such Key");
  }

  // getAllKeys : -> ArrayList<K>
  // Creates an ArrayList of all the Keys in the FMap.
  public ArrayList<K> getAllKeys(ArrayList<K> a) {
    return a;
  }

  // hashCode : -> int
  // Assigns a hashcode to this.
  public int hashCode() {
    return this.size();
  }

  // sameKeys : FMap<K,V> FMap<K,V> m2 -> Boolean
  // Does this FTree have all the same keys as the given FTree?
  public boolean sameKeys(FMap<K,V> m1, FMap<K,V> m2) {
    return true;
  }

  // sameValues : FMap<K,V> FMap<K,V> -> Boolean
  // Are the values of each key the same for both FTrees?
  public boolean sameValues(FMap<K,V> m1, FMap<K,V> m2) {
    return true;
  }

  // equals : -> boolean
  // Is this FTree equal to that FTree?
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (o instanceof FMap) {
      FMap<K,V> that = (FMap<K,V>)o;
      return that.isEmpty();
    } else {
      return false;
    }
  }

  // toString : -> String
  // Converts this Empty FMap into a String.
  public String toString() {
    return "{...(" + this.size() + " entries)...}";
  }
}


// To represent a Node FTree.
class Node<K,V> extends FTree<K,V> {
  K k0; // The key of the pair.
  V v0; // The value of the pair.
  FTree<K,V> left; // The left branch of the tree.
  FTree<K,V> right; // The right branch of the tree.
  Comparator<? super K> c; // Comparator to sort FTree.
  int size;

  // Constructor
  Node(K k0, V v0, FTree<K,V> left, 
      FTree<K,V> right, Comparator<? super K> c) {
    this.k0 = k0;
    this.v0 = v0;
    this.left = left;
    this.right = right;
    this.c = c;
    // Precompute size
    this.size = 1 + left.size() + right.size();
  }

  // put : -> FTree<K,V>
  // Puts a K,V pair into the FTree.
  public FTree<K,V> put(K k, V v) {
    if (this.k0.equals(k)) {
      return this;
    } else if (this.c.compare(this.k0, k) > 0) {
      return new Node<K,V>(
          this.k0, this.v0, this.left.put(k, v), this.right, this.c);
    } else {
      return new Node<K,V>(
          this.k0, this.v0, this.left, this.right.put(k, v), this.c);
    }
  }

  // isEmpty : -> Boolean
  // Is the FTree empty?
  public boolean isEmpty() {
    return false;
  }

  // size : -> int
  // Determines the number of KV pairs in the FTree.
  public int size() {
    return this.size;
  }

  // containsKey : K -> Boolean
  // Does the FTree contain the given key?
  public boolean containsKey(K k) {
    if (c.compare(this.k0, k) == 0) {
      return true;
    } else if (c.compare(this.k0, k) > 0) {
      return this.left.containsKey(k);
    } else {
      return this.right.containsKey(k);
    }
  }

  // containsValue : V -> Boolean
  // Does the FTree contain the given value?
  public boolean containsValue(V v) {
    return v0.equals(v) ||
           this.left.containsValue(v) ||
           this.right.containsValue(v);
  }

  // get : K -> V
  // Gets the Value from the given Key.
  public V get(K k) {
    if (c.compare(this.k0, k) == 0) {
      return this.v0;
    } else if (c.compare(this.k0, k) > 0) {
      return this.left.get(k);
    } else {
      return this.right.get(k);
    }
  }

  // getAllKeys : -> ArrayList<K>
  // Creates an ArrayList of all the Keys in the FMap.
  public ArrayList<K> getAllKeys(ArrayList<K> a) {
    a.add(this.k0);
    a.addAll(this.left.getAllKeys(new ArrayList<K>()));
    a.addAll(this.right.getAllKeys(new ArrayList<K>()));
    return a;
  }

  // hashCode : -> int
  // Assigns a hashcode to this.
  public int hashCode() {
    return this.size();
    /*
    return this.k0.hashCode() +
           this.left.hashCode() + this.right.hashCode(); */
  }
  
  // sameKeys : FMap<K,V> FMap<K,V> -> Boolean
  // Does this FTree have all the same keys as the given FTree?
  public boolean sameKeys(FMap<K,V> m1, FMap<K,V> m2) {
    if (m1.containsKey(this.k0) == m2.containsKey(this.k0)) {
      return this.left.sameKeys(m1, m2) &&
             this.right.sameKeys(m1, m2);
    } else {
      return false;
    }
    /*
    return this.k0.equals(m1.k0) && 
           this.left.sameKeys(m1.left) &&
           this.right.sameKeys(m1.right); */
  }

  // sameValues : FMap<K,V> FMap<K,V> -> Boolean
  // Are the values of each key the same for both FTrees?
  public boolean sameValues(FMap<K,V> m1, FMap<K,V> m2) {
    if (m1.get(k0).equals(m2.get(k0))) {
      return this.left.sameValues(m1, m2) &&
             this.right.sameValues(m1, m2);
    } else {
      return false;
    }
    /*
    return this.v0.equals(m1.v0) && 
           this.left.sameValues(m1.left) &&
           this.right.sameValues(m1.right); */
  }

  // equals : -> boolean
  // Is this FTree equal that that FTree?
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (o instanceof FMap) {
      FMap<K,V> that = (FMap<K,V>)o;
      if (that.isEmpty()) {
        return false;
      } else if (this.sameKeys(this, that) && 
                 that.sameKeys(that, this)) {
        return (this.sameValues(this, that) && 
                that.sameValues(that, this));
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  // toString : -> String
  // Converts this Empty FMap into a String.
  public String toString() {
    return "{...(" + this.size() + " entries)...}";
  }
}


// To represent an Iterator that generates every Key 
// in the FMap exactly once.
class KeyIterator<K> implements Iterator<K> {
  ArrayList<K> k; // The list of Keys.
  int i = 0; // Index of a Key in the list.

  // Constructor for an Iterator without a Comparator.
  KeyIterator(ArrayList<K> k) {
    this.k = k;
  }

  // Constructor for an Iterator with a Comparator.
  KeyIterator(ArrayList<K> k, Comparator<? super K> c) {
    this.k = k;
    Collections.sort(k, c);
  }

  // Does the ArrayList have a K after the current one?
  public boolean hasNext() {
    return i < k.size();
  }

  // Returns the next K in the iteration.
  public K next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException(
          "Attempting to call next() on an empty list of keys");
    } else {
      this.i = i+1;
      return k.get(i-1);
    }
  }

  // Removes the last element returned by the Iterator.
  public void remove() {
    throw new UnsupportedOperationException(
        "Can't call remove on iterator");
  }
}








