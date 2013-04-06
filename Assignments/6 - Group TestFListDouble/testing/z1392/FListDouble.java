public abstract class FListDouble {

    public static FListDouble emptyList() {
	return new EmptyList();
    }

    public static FListDouble add(FListDouble f, Double d) {
	return new AddList(f, d);
    }

    public static boolean isEmpty(FListDouble f) {
	return f.isEmptyMethod();
    }

    public static Double get(FListDouble f, int i) {
	return f.getMethod(i);
    }

    public static FListDouble set(FListDouble f, int i, Double d) {
	return f.setMethod(i, d);
    }

    public static int size(FListDouble f) {
	return f.sizeMethod();
    }
    public static boolean contains(FListDouble f, Double d) {
	return f.containsMethod(d);
    }

    abstract boolean isEmptyMethod();

    abstract Double getMethod(int i);

    abstract FListDouble setMethod(int i, Double d);

    abstract int sizeMethod();

    abstract boolean containsMethod(Double d);

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract int hashCode();
}

class EmptyList extends FListDouble {
    EmptyList() {

    }

    boolean containsMethod(Double d) {
	return false;
    }

    public boolean equals(Object o) {
	return o instanceof EmptyList;
    }

    Double getMethod(int i) {
	return null;
    }
    public int hashCode() {
	return 47;
    }

    boolean isEmptyMethod() {
	return true;
    }

    FListDouble setMethod(int i, Double d) {
	throw new RuntimeException("set on empty");
    }

    int sizeMethod() {
	return 0;
    }

    public String toString() {
	return "[]";
    }
}

class AddList extends FListDouble {
    Double d;
    FListDouble next;

    AddList(FListDouble next, Double d) {
	this.d = d;
	this.next = next;
    }

    boolean containsMethod(Double d) {
	if (d == this.d)
	    return true;
	else
	    return FListDouble.contains(this.next, d);
    }

    public boolean equals(Object o) {
	if (o instanceof AddList) {
	    AddList l = (AddList) o;
	    if (l.d == this.d) {
		return this.next.equals(l.next);
	    } else
		return false;
	} else
	    return false;
    }

    Double getMethod(int i) {
	if (i == 0) {
	    return this.d;
	} else
	    return FListDouble.get(this.next, i-1);
    }

    public int hashCode() {
	double i = this.d;
	int j = (int) i;
	return 7 + j * 3 + this.next.hashCode();
    }

    boolean isEmptyMethod() {
	return false;
    }

    FListDouble setMethod(int i, Double d) {
	if (i == 0)
	    return FListDouble.add(this.next, d);
	else {
	    return FListDouble
		.add(FListDouble.set(this.next, i - 1, d), this.d);
	}
    }

    int sizeMethod() {
	return 1 + FListDouble.size(this.next);
    }

    public String toString() {
	if (FListDouble.isEmpty(this.next)) {
	    return "[" + this.d.toString() + "]";
	} else {
	    return "["
		+ this.d.toString()
		+ ", "
		+ this.next.toString().substring(1,
						 this.next.toString().length());
	}
    }
}
