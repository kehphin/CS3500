
public abstract class FListDouble {
    abstract boolean isEmpty();
	abstract Double get(int n);
	    abstract FListDouble set(int n, Double y);
		abstract int size();
		    public abstract String toString();
			public boolean equals(Object o){
			    if (o instanceof FListDouble){
				FListDouble f2 = (FListDouble) o;
				    return (this.isEmpty() && f2.isEmpty()) ||
					(this.size() == f2.size()) &&
					this.equalsHelp(f2);
					}else return false;
				}
			    public boolean equalsHelp(FListDouble f2){
				for(int i = 0; i <= this.size(); i++){
				    return this.get(i).equals(f2.get(i));
					}
				    return false;
					}
				public int hashCode(){
				    return 0;
					}
				    static FListDouble emptyList(){
					return new EmptyList();
					    }
					static FListDouble add(FListDouble f, Double x){
					    return new Add(f, x);
						}
					    static boolean isEmpty(FListDouble f){
						return f.isEmpty();
						    }
						static Double get(FListDouble f, int n){
						    return f.get(n);
							}
						    static FListDouble set(FListDouble f, int n, Double y){
							return f.set(n, y);
							    }
							static int size(FListDouble f){
							    return f.size();
								}
}
    
	class EmptyList extends FListDouble{
	    EmptyList(){}
		@Override
		    public String toString(){
		    return "[]";
			}
		    @Override
			boolean isEmpty() {
			return true;
			    }
			@Override
			    String get(int n) {
			    throw new RuntimeException(
        "Not valid: cannot get with an empty list");
				}
			    @Override
				FListString set(int n, String y) {
				throw new RuntimeException(
        "Not valid: cannot set with an empty list");
				    }
				@Override
				    int size() {
				    return 0;
					}
	}
	    
		class Add extends FListDouble{
		    FListDouble f;
			Double x;
			    @Override
				public String toString(){
				return "[" + x.toString() + ", "
				    + f.toString().substring(1, f.toString().length());
				    }
				
				Add (FListDouble f, Double x){
				    this.f = f;
					this.x = x;
					    }
				    @Override
					boolean isEmpty() {
					return false;
					    }
					@Override
					    Double get(int n) {
					    if (n == 0)
						return x;
					    else
						return FListDouble.get (f, n - 1);
						}
					    @Override
						FListDouble set(int n, Double y) {
						if (n == 0)
						    return FListDouble.add (f, y);
						else
						    return FListDouble.add (FListDouble.set (f, n - 1, y), x);
						    }
						@Override
						    int size() {
						    return 1 + FListDouble.size (f);
							}
		}