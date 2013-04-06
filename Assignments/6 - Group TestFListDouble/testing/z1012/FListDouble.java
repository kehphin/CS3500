public abstract class FListDouble {
	
    abstract boolean isEmptyMethod();
	
    abstract Double getMethod(int x);
	
    abstract FListDouble setMethod(int x, Double s);
	
    abstract int sizeMethod();
    	
    abstract boolean containsM(Double y);
	
    public static FListDouble emptyList() {
	return new EmptyList();
    }
	
    public static FListDouble add(FListDouble l, Double s) {
	return new Add(l,s);
    }
	

    public static boolean isEmpty(FListDouble l) {
	return l.isEmptyMethod();
    }
	
    public static Double get(FListDouble l, int x) {
	return l.getMethod(x);
    }
	
    public static FListDouble set(FListDouble l, int x, Double s) {
	return l.setMethod(x, s);
    }
	
    public static int size(FListDouble l) {
	return l.sizeMethod();
    }
	
    public static boolean contains(FListDouble f, Double y){
	return f.containsM(y);
    }

    @Override
	public boolean equals (Object o) {
	if (!(o instanceof FListDouble)) {
	    return false;
	}
		
	FListDouble f2 = (FListDouble) o;
		
	if (isEmpty(this) && isEmpty(f2)) {
	    return true;
	}
		
	if (size(this) != size(f2))
	    return false;

	for (int i = 0; i < size(this); i++) {
	    if (!(get(this, i).equals(get(f2, i))))
		return false;
	}
		
	return true;
    }
	
    @Override
	public int hashCode() {
        String hash = this.toString();
        return hash.hashCode();
    }
}


class EmptyList extends FListDouble {

    EmptyList() {}
	
    @Override
	boolean isEmptyMethod() {
	return true;
    }

    @Override
	Double getMethod(int x) {
	return null;
    }

    @Override
	FListDouble setMethod(int x, Double s) {
	return add(this, s);
    }

    @Override
	int sizeMethod() {
	return 0;
    }
	
    boolean containsM(Double y){
	return false;
    }

    @Override
	public String toString()
    {
	return "[]";
    }
}
	
class Add extends FListDouble {

    FListDouble l0;
    Double s0;
	
    Add (FListDouble l, Double s) {
	this.l0 = l;
	this.s0 = s;
    }
	
    @Override
	boolean isEmptyMethod() {
	return false;
    }

    @Override
	Double getMethod(int n) {
	if (n == 0) {
	    return s0;
	}
	if (n > 0) {
	    return get(l0, n-1);
	}
	return null;
    }

    @Override
	FListDouble setMethod(int n, Double y) {
	if (n == 0) {
	    return add(l0,y);
	}
		
	if (n > 0) {
	    return add(set (l0, n - 1, y), s0);
	}
		
	return null;

    }

    @Override
	int sizeMethod() {
	return 1 + size(l0);
    }

    boolean containsM(Double y){
	if (s0.equals(y)){
	    return true;
	}else{
	    return FListDouble.contains (l0, y);
	}
    }
	
    @Override
	public String toString()
    {
	if (FListDouble.isEmpty(l0))
	    return "[" + s0.toString() + "]";
	else
	    return "[" + s0.toString() + ", " + 
		l0.toString().substring(1, l0.toString().length());
    }
}