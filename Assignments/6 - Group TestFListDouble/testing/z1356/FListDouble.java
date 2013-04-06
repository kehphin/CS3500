public abstract class FListDouble{
    abstract boolean isEmptyMethod();
    abstract Double getMethod(int i);
    abstract FListDouble setMethod(int i, Double d);
    abstract int sizeMethod();
    abstract boolean containsMethod(Double d);
	
    public static FListDouble emptyList(){
	return new EmptyList();
    }
	
    public static FListDouble add(FListDouble f, Double k){
	return new Add(f, k);
    }
	
    public static boolean isEmpty(FListDouble f){
	return f.isEmptyMethod(); 
    }
	
    public static Double get(FListDouble f, int i){
	return f.getMethod(i);
    }
	
    public static FListDouble set(FListDouble f, int i, Double d){
	return f.setMethod(i, d);
    }
	
    public static int size(FListDouble f){
	return f.sizeMethod();
    }
	
    public static boolean contains(FListDouble f, Double d){
	return f.containsMethod(d);
    }
	
    public abstract String toString();
		
    public boolean equals(Object o){
	if (o instanceof FListDouble){
	    FListDouble f = ((FListDouble) o);
	    return (this.isEmpty(f) && f.isEmpty(this)) ||
		(FListDouble.size(f) == FListDouble.size(this));}
	else return false;
    }

    public int hashCode(){
	return 0;
    }
		
}

class EmptyList extends FListDouble{
    EmptyList(){}

    boolean isEmptyMethod(){
	return true;
    }
	
    Double getMethod(int i){
	throw new RuntimeException 
	    ("Cannot get anything from an empty FListDouble");
    }
	
    FListDouble setMethod(int i, Double d){
	throw new RuntimeException
	    ("Cannot set anything in an empty FListDouble");
    }
	
    int sizeMethod(){
	return 0;
    }
	
    boolean containsMethod(Double y){
	return false;
    }
	
    public String toString(){
	return "[]";
    }
}

class Add extends FListDouble{
	
    FListDouble f;
    Double x;
	
    Add(FListDouble f, Double x){
	this.f = f;
	this.x = x;
    }
	
    boolean isEmptyMethod(){
	return false;
    }
	
    Double getMethod(int n){
	if (n == 0)
	    return this.x;
	else return FListDouble.get(f, n-1);
    }
	
    FListDouble setMethod(int n, Double y){
	if (n == 0)
	    return FListDouble.add(f, y);
	else return FListDouble.add(FListDouble.set(f,  n-1,  y), x);
    }
	
    int sizeMethod(){
	return 1 + FListDouble.size(f);
    }
	
    boolean containsMethod(Double y){
	if (x.equals(y))
	    return true;
	else return FListDouble.contains(f, y);
    }
	
    public String toString(){
	if (FListDouble.isEmpty(f))
	    return "[" + x.toString() + "]";
	else return "[" + x.toString() + ", "
		 +  f.toString().substring(1, f.toString().length());
    }
}