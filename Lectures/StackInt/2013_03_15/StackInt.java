/**
 * StackInt implementation from 2013.01.08 and 2013.01.11 class 
 * Recipe implementation of a stack of int values.

 * Also has a (dynamic) accept(IntVisitor) method,
 * which returns a stack of the values returned by
 * the visitor.
 * Added on 2013.03.15
 */

//     empty:                    -->  StackInt
//     push:     StackInt x int  -->  StackInt
//     isEmpty:  StackInt        -->  boolean
//     top:      StackInt        -->  int
//     pop:      StackInt        -->  StackInt
//     size:     StackInt        -->  int
// 
//     isEmpty (empty())  =  true
//     isEmpty (push (s, n))  =  false
// 
//     top (push (s, n))  =  n
// 
//     pop (push (s, n))  =  s
// 
//     size (empty())  =  0
//     size (push (s, n))  =  1 + size (s)

// Cookbook implementation of the StackInt ADT with comments

public abstract class StackInt{
    // Abstract methods for the accessors.
    // Returns true iff this StackInt is empty.
    abstract boolean isEmptyMethod();
    // Returns the top element of this StackInt, which must be nonempty.
    abstract int topMethod();
    // Returns the other elements of this StackInt, which must be nonempty.
    abstract StackInt popMethod();
    // Returns the size of the StackInt
    abstract int sizeMethod();
    //Returns a StackInt of the results returned by the IntVisitor
    //when it visits the elements of this StackInt
    abstract StackInt accept (IntVisitor v);
 
    // Returns true iff this StackInt is empty.
    public static StackInt empty(){
	return new Empty();
    }

    // Returns a StackInt representing n pushed onto s.
    public static StackInt push(StackInt s, int n){
	return new Push(s, n);
    }

    // Returns true iff the given StackInt is empty.
    public static boolean isEmpty(StackInt s){
	return s.isEmptyMethod();
    }

    // Returns the top element of this StackInt, which must be nonempty.
    public static int top (StackInt s){
	return s.topMethod();
    }

    // Returns the other elements of this StackInt, which must be nonempty.
    public static StackInt pop (StackInt s){
	return s.popMethod();
    }

    // Returns the size of the StackInt
    public static int size(StackInt s){
	return s.sizeMethod();
    }
}

// The class of objects created by empty().
class Empty extends StackInt{
    Empty(){ }

    // Returns true iff this StackInt is empty.
    boolean isEmptyMethod(){
	return true;
    }

    // Returns the top element of this StackInt, which must be nonempty.
    int topMethod(){
	throw new RuntimeException("top of empty StackInt");
    }

    // Returns the other elements of this StackInt, which must be nonempty.
    StackInt popMethod(){
	throw new RuntimeException("pop of empty StackInt");
    }

    // Returns the size of the StackInt
    int sizeMethod(){
	return 0;
    }

    //Returns a StackInt of the results returned by the IntVisitor
    //when it visits the elements of this StackInt
    StackInt accept (IntVisitor v){
	return this;
    }

}

// The class of objects created by push(s,n).
class Push extends StackInt{
    StackInt s;        //The StackInt (pop value)
    int n;             //The top of the StackInt

    Push(StackInt s, int n){
	this.s = s;
	this.n = n;
    }

    // Returns true iff this StackInt is empty.
    boolean isEmptyMethod(){
	return false;
    }

    // Returns the top element of this StackInt, which must be nonempty.
    int topMethod(){
	return n;
    }

    // Returns the other elements of this StackInt, which must be nonempty.
    StackInt popMethod(){
	return s;
    }

    // Returns the size of the StackInt
    int sizeMethod(){
	return 1 + size(s);
    }

    //Returns a StackInt of the results returned by the IntVisitor
    //when it visits the elements of this StackInt
    StackInt accept (IntVisitor v){
	return (StackInt.push(s.accept(v), v.visit(n)));
    }
}