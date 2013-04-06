public abstract class FListDouble {
  
    private static final FListDouble empty = new EmptyList();
  
  
    abstract boolean isEmptyMethod();
  
    abstract Double getMethod(int i);
  
    abstract FListDouble setMethod(int i, Double d);
  
    abstract int sizeMethod();
  
    abstract boolean containsMethod(Double d);

  
    public static FListDouble emptyList() {
	return empty;
    }
  
  
    public static FListDouble add(FListDouble l, Double d) {
	return new ConsList(d, l);
    }
  
  
    public static boolean isEmpty(FListDouble l) {
	return l.isEmptyMethod();
    }
  
  
    public static Double get(FListDouble l, int i) {
	return l.getMethod(i);
    }
  
  
    public static FListDouble set(FListDouble l, int i, Double d) {
	return l.setMethod(i, d);
    }
  
  
    public static int size(FListDouble l) {
	return l.sizeMethod();
    }
  
  
    public static boolean contains(FListDouble l, Double d) {
	return l.containsMethod(d);
    }
  
  
    public abstract String toString();
  
  
    public boolean equals(Object o) {            
	if (o != null) {                           
	    if (o instanceof FListDouble) {          
        
		FListDouble that = (FListDouble) o;
		int size = this.sizeMethod();
		if (size == that.sizeMethod()) {       
          
		    for (int i = 0; i < size; i++) {    
			if (!this.getMethod(i).equals(that.getMethod(i))) { 
			    return false;                   
			}                                 
		    }
          
		    return true;                        
		}
	    }
	}
	return false;                             
    }                                           
  
  
    public abstract int hashCode();
  
  
    private static void throwIndexEx(FListDouble l, int i) {
	throw new IndexOutOfBoundsException("Index: " + i 
					    + ", Size: " + l.sizeMethod());
    }
  
  
  
    private static class EmptyList extends FListDouble {
    
    
	EmptyList() { }
  
    
	boolean isEmptyMethod() {
	    return true;
	}
    
    
	Double getMethod(int i) {
	    throwIndexEx(this, i);
	    return null;
	}
    
    
	FListDouble setMethod(int i, Double d) {
	    throwIndexEx(this, i);
	    return null;
	}
  
    
	int sizeMethod() {
	    return 0;
	}
  
    
	boolean containsMethod(Double n) {
	    return false;
	}
    
    
	public String toString() {
	    return "[]";
	}
  
    
	public int hashCode() {
	    return 23;
	}
    
    }
  
  
  
    private static class ConsList extends FListDouble {
    
	Double value;              
	FListDouble rest;          
    
    
	ConsList(Double d, FListDouble l) {
	    this.value = d;
	    this.rest = l;
	}
  
    
	boolean isEmptyMethod() {
	    return false;
	}
    
    
	Double getMethod(int i) {
	    if (i == 0) {
		return this.value;
	    }
	    else if (i > 0) {
		return get(this.rest, i - 1);
	    }
	    else {
		throwIndexEx(this, i);
		return null;
	    }
	}
    
    
	FListDouble setMethod(int i, Double d) {
	    if (i == 0) {
		return add(this.rest, d);
	    }
	    else if (i > 0) {
		return add(set(this.rest, i - 1, d), this.value);
	    }
	    else {
		throwIndexEx(this, i);
		return null;
	    }
	}
    
    
	int sizeMethod() {
	    return 1 + size(this.rest);
	}
  
  
	boolean containsMethod(Double n) {
	    if (this.value.equals(n)) {
		return true;
	    }
	    else {
		return contains(this.rest, n);
	    }
	}
    
    
	public String toString() {
	    if (isEmpty(this.rest)) {
		return "[" + this.value.toString() + "]";
	    }
	    else {
		return "[" + this.value.toString() + ", " 
		    + this.rest.toString().substring(1);
	    }
	}
  
    
	public int hashCode() {
	    return this.value.hashCode() + this.rest.hashCode() * 31;
	}
    
    }
  
}
