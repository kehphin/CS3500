/*
  StackInt implementation from 2013.01.08 and 2013.01.11 class
  Modified in class on 2013.01.25 based on refactoring
  
  Cookbook implementation of the StackInt ADT,
  with the Push subclass merged into the StackInt class.
  Empty nested in StackInt
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

public class StackInt{

    public static StackInt empty(){
	return new Empty();
    }

    public static StackInt push(StackInt s, int n){
	return new StackInt(s, n);
    }

    public static boolean isEmpty(StackInt s){
	return s.isEmptyMethod();
    }

    public static int top (StackInt s){
	return s.topMethod();
    }

    public static StackInt pop (StackInt s){
	return s.popMethod();
    }

    public static int size(StackInt s){
	return s.sizeMethod();
    }


    private static class Empty extends StackInt{
	Empty(){ }

	boolean isEmptyMethod(){
	    return true;
	}

	int topMethod(){
	    throw new RuntimeException("top of empty StackInt");
	}

	StackInt popMethod(){
	    throw new RuntimeException("pop of empty StackInt");
	}

	int sizeMethod(){
	    return 0;
	}
    }

    private StackInt s;
    private int n;

    private StackInt(){}

    private StackInt(StackInt s, int n){
	this.s = s;
	this.n = n;
    }

    boolean isEmptyMethod(){
	return false;
    }

    int topMethod(){
	return n;
    }

    StackInt popMethod(){
	return s;
    }

    int sizeMethod(){
	return 1 + size(s);
    }
}