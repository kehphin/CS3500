public abstract class FListDouble{
    abstract FListDouble addMethod(Double y);
    abstract boolean isEmptyMethod();
    abstract Double getMethod(int n);
    abstract FListDouble setMethod(int n, Double y);
    abstract int sizeMethod();
    abstract boolean containsMethod(Double y);
    abstract String toStringMethod();
    abstract boolean equalsMethod(FListDouble l1);
    abstract int hashCodeMethod();
    abstract FListDouble getFMethod();
	
    public static FListDouble emptyList(){
	return new Empty();
    }
	
    public static FListDouble add(FListDouble f, Double y){
	return f.addMethod(y);
    }
	
    public static boolean isEmpty(FListDouble f){
	return f.isEmptyMethod();
    }
	
    public static Double get(FListDouble f, int n){
	return f.getMethod(n);
    }
	
    public static FListDouble set(FListDouble f, int n, Double y){
	return f.setMethod(n, y);
    }
	
    public static int size(FListDouble f){
	return f.sizeMethod();
    }
	
    public static boolean contains(FListDouble f, Double y){
	return f.containsMethod(y);
    }
	
    public String toString(){
	return this.toStringMethod();
    }
	
    public static FListDouble getF(FListDouble f){
	return f.getFMethod();
    }
	
    public boolean equals(Object obj){
	if (obj == null) {return false;
	} else {
	    if (obj instanceof FListDouble) 
		{FListDouble l1 = (FListDouble) obj; 
		    return this.equalsMethod(l1);
		} else {return false;
	    }
	}
    }
	
    public int hashCode(){
	return this.hashCodeMethod();
    }
}

class Empty extends FListDouble{
    Empty(){}
	
    FListDouble addMethod(Double y){
	return new List(this, y);
    }
	
    boolean isEmptyMethod(){
	return true;
    }
	
    Double getMethod(int n){
	throw new RuntimeException 
	    ("Cannot get the Double of an empty list");
    }
	
    FListDouble setMethod(int n, Double y){
	throw new RuntimeException 
	    ("Cannot set the Double of an empty list");
    }
	
    int sizeMethod(){
	return 0;
    }
	
    boolean containsMethod(Double y){
	return false;
    }
	
    String toStringMethod(){
	return "[]";
    }
	
    FListDouble getFMethod(){
	throw new RuntimeException
	    ("Cannot get the FListDouble of an empty");
    }
	
    boolean equalsMethod(FListDouble l1){
	if (FListDouble.isEmpty(l1)) {return true;
	} else {return false;
	}
    }
	
    int hashCodeMethod(){
	return 0;
    }
}

class List extends FListDouble{
    FListDouble f;
    Double x;
    List(FListDouble f, Double x){
	this.f = f;
	this.x = x;
    }
	
    FListDouble addMethod(Double y){
	return new List(this, y);
    }
	
    boolean isEmptyMethod(){
	return false;
    }
	
    Double getMethod(int n){
	if (n == 0){return x;
	} else {if (n > 0){return f.getMethod(n - 1);
	    } else {throw new RuntimeException 
		    ("Cannot get the Double of a -nth list");
	    }}
    }
	
    FListDouble setMethod(int n, Double y){
	if (n == 0){return new List(f, y);
	} else {if (n > 0){return new List(f.setMethod(n - 1, y), x);
	    } else {throw new RuntimeException 
		    ("Cannot set the double of a -nth list");
	    }}
    }
	
    int sizeMethod(){
	return 1 + f.sizeMethod();
    }
	
    boolean containsMethod(Double y){
	if (x.equals(y)) {return true;
	} else {return f.containsMethod(y);
	}
    }
	
    String toStringMethod(){
	if (f.isEmptyMethod()) {return "[" + x.toString() + "]";
	} else {return "[" + x.toString() + ", " + 
		f.toString().substring(1, f.toString().length());
	}
    }
	
    FListDouble getFMethod(){
	return f;
    }
	
    boolean equalsMethod(FListDouble l1){
	if (x == l1.getMethod(0)) 
	    {if (this.sizeMethod() == l1.sizeMethod()) 
		    {return f.equalsMethod(l1.getFMethod());
		    } else {return false;
		}
	    } else {return false;
	}
    }
	
    int hashCodeMethod(){
	return (int)Math.round(x) + f.hashCodeMethod();
    }
}