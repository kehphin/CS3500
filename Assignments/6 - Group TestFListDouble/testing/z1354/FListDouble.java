public abstract class FListDouble{
	
    abstract boolean isEmptyMethod();
	
    abstract Double getMethod(int n);
	
    abstract FListDouble setMethod(int n, Double y);
	
    abstract int sizeMethod();
	
    abstract boolean containsMethod(Double y);
	
    abstract String toStringMethod();
	
    public static FListDouble emptyList(){
	return new EmptyList();
    }

    public static FListDouble add(FListDouble f, Double x){
	return new Add(f, x);
    }

    public static boolean isEmpty(FListDouble f){
	return f.isEmptyMethod();
    }

    public static Double get(FListDouble f, int n){
	return f.getMethod(n);
    }

    public static FListDouble set(FListDouble f, int n, Double x){
	return f.setMethod(n, x);
    }

    public static int size(FListDouble f){
	return f.sizeMethod();
    }

    public static boolean contains(FListDouble f, Double x){
	return f.containsMethod(x);
    }

    public String toString(){
	return this.toStringMethod();
    }

    public boolean equals(Object o){
	if(o instanceof FListDouble){
	    FListDouble that = (FListDouble) o;
			
	    if(!FListDouble.isEmpty(this) &&
	       FListDouble.size(this) == FListDouble.size(that)){
		int i = 0;
		while(i < FListDouble.size(this)){
		    if(FListDouble.get(that, i) == FListDouble.get(this, i))
			i = i+1;
		    else
			return false;
		} 
		return true;
	    } 
	    else
		return FListDouble.isEmpty(this) &&  FListDouble.isEmpty(that);
	}
	return false;
    }

    public int hashCode(){
	return FListDouble.size(this);

    }
	
    private static class EmptyList extends FListDouble{
	EmptyList(){}

	boolean isEmptyMethod(){return true;}

	Double getMethod(int n){
	    throw new RuntimeException("The list must contain at least " +
				       "one element");
	}

	FListDouble setMethod(int n, Double y){
	    throw new RuntimeException("The list must contain at least " +
				       "one element");
	}

	int sizeMethod(){
	    return 0;
	}

	boolean containsMethod(Double y){
	    return false;
	}

	String toStringMethod(){
	    return "[ ]";
	}

    }

    private static class Add extends FListDouble{
	private FListDouble f;
	private Double x;

	Add(FListDouble f, Double x){
	    this.f = f;
	    this.x = x;
	}

	boolean isEmptyMethod(){return false;}

	Double getMethod(int n){
	    if(n == 0)
		return x;
	    else if(n > 0)
		return FListDouble.get(f, n-1);
	    else
		throw new RuntimeException("The number must be " +
					   "greater than 0");
	}

	FListDouble setMethod(int n, Double y){
	    if(n == 0)
		return FListDouble.add(f, y);
	    else if(n > 0)
		return FListDouble.add(FListDouble.set(f, n-1, y), x);
	    else
		throw new RuntimeException("The number must be " +
					   "greater tahn 0");
	}

	int sizeMethod(){
	    return 1 + FListDouble.size(f);
	}

	boolean containsMethod(Double y){
	    if(x.equals(y))
		return true;
	    else
		return FListDouble.contains(f, y);
	}

	String toStringMethod(){
	    if(FListDouble.isEmpty(f))
		return "[" + x.toString() + "]";
	    else 
		return "[" + x.toString() + ", " 
		    + f.toString().substring(1,  f.toString().length());
	}
    }


}

