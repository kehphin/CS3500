
import java.util.*;
 
public class FListDouble {
  ArrayList<Double> values;
	
  
  public static FListDouble emptyList() {
    return new FListDouble();
  }
	
  public static FListDouble add(FListDouble fls, Double s) {
    FListDouble ret = new FListDouble(fls.values);
    ret.values.add(s);
    return ret;
  }
	
  
  public static Boolean isEmpty(FListDouble fls) {
    return fls.values.size() < 1;
  }
	
  
  public static Double get(FListDouble fls, int index) {
    return fls.values.get(index);
  }
  
  public static FListDouble set(FListDouble fls, int index, Double newValue) {
    FListDouble ret = new FListDouble(fls.values);
    ret.values.set(index, newValue);
    return ret;
  }
  
  public static int size(FListDouble fls) {
    return fls.values.size();
  }
	
  
  private FListDouble() {
    values = new ArrayList<Double>();
  }
  
  private FListDouble(ArrayList<Double> values) {
    this.values = (ArrayList<Double>)values.clone();
  }
	
  
  public String toString() {
    if (values.size() < 1) return "[]";
		
    String ret = "[";
		
    for (Integer i = 0; i < values.size(); ++i)
      if (i.equals(values.size() - 1))
        ret += values.get(i).toString() + "]";
      else ret += values.get(i).toString() + ", ";
				
      return ret;
    }
 
  
  public int hashCode() {
    return values.size();
  }
	
  
  public boolean equals(Object o) {
    try {
      FListDouble f = (FListDouble)o;
			
        if (values.size() != f.values.size()) return false;
          for (Integer i = 0; i < values.size(); ++i)
            if (!(f.values.get(i).equals(values.get(i))))
              return false;
			
        return true;
    }
      catch (Exception e) { return false; }
  }

    public static boolean contains(FListDouble f, Double y){
	return true;
    }
}