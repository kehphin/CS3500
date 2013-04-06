

	

	public abstract class FListDouble {
	
		
		    abstract boolean isEmptyMethod();
		        abstract Double getMethod(int n);
			    abstract FListDouble setMethod(int n, Double y);
			        abstract int sizeMethod();
				
					
					    public static FListDouble emptyList() {
					            return new EmptyList();
						        }
							
								
								    public static FListDouble add(FListDouble f, Double x) {
								            return new Add(f, x);
									        }
										
											
											    public static boolean isEmpty(FListDouble f) {
											            return f.isEmptyMethod();
												        }
													
													    
													        public static Double get(FListDouble f, int n) {
														        return f.getMethod(n);
															    }
															    
															        
																    public static FListDouble set(FListDouble f, int n, Double y) {
																            return f.setMethod(n, y);
																	        }
																		
																			
																			    public static int size(FListDouble f) {
																			            return f.sizeMethod();
																				        }
																					
																					    @Override
																					    	
																						    public boolean equals (Object x) {
																						            if (!(x instanceof FListDouble))
																							                    return false;
																									    
																									            FListDouble f2 = (FListDouble) x;
																										            int counter = 0;
																											            if (isEmpty(this) && isEmpty(f2))
																												            	return true;
																														    	else if (size(this) == size(f2)) {
																															            for (int i = 0; i < size(this); i++)
																																                    if (get(this, i).equals(get(f2, i)))
																																		                    	
																																					                    counter++;	
																																							    
																																							                if (counter == size(this))
																																									            return true;
																																										        	}
																																												        
																																													        return false;
																																														    }
																																														    
																																														        @Override
																																																
																																																    public int hashCode() {
																																																            return size(this);
																																																	        }
																																																		   abstract boolean containsM(Double y);
																																																		       public static boolean contains(FListDouble f, Double y){
																																																		       	return f.containsM(y);
																																																			    }
																																																			    }
																																																			    

																																																			    class EmptyList extends FListDouble {
																																																			    
																																																			    	
																																																				    EmptyList () { }
																																																				    
																																																				    	
																																																					    boolean isEmptyMethod () {
																																																					            return true;
																																																						        }
																																																							
																																																							    
																																																							        Double getMethod (int n) {
																																																								        return null;
																																																									    }
																																																									    
																																																									        
																																																										    FListDouble setMethod (int n, Double y) {
																																																										            return add(this, y);
																																																											        }
																																																												
																																																													
																																																													    int sizeMethod () {
																																																													            return 0;
																																																														        }
																																																															
																																																															    @Override
																																																															    	
																																																																    public String toString () {
																																																																            return "[]";
																																																																	        }

																																																																		    boolean containsM(Double y){
																																																																		    	return false;
																																																																			    }
																																																																			    }
																																																																			    

																																																																			    class Add extends FListDouble {
																																																																			    
																																																																			    	
																																																																				    FListDouble f;	
																																																																				        Double x;	
																																																																					
																																																																					    Add (FListDouble f0, Double x0) {
																																																																					            this.f = f0;
																																																																						            this.x = x0;
																																																																							        }
																																																																								
																																																																									
																																																																									    boolean isEmptyMethod () {
																																																																									            return false;
																																																																										        }
																																																																											
																																																																											    
																																																																											        Double getMethod (int n) {
																																																																												        if (n == 0)
																																																																													            return x;
																																																																														            else if (n > 0)
																																																																															                return get(f, n - 1);
																																																																																	        return null;
																																																																																		    }
																																																																																		    
																																																																																		        
																																																																																			    FListDouble setMethod (int n, Double y) {
																																																																																			            if (n == 0)
																																																																																				                return add(f, y);
																																																																																						        else if (n > 0)
																																																																																							            return add(set(f, n - 1, y), x);
																																																																																								            return null;
																																																																																									        }
																																																																																										
																																																																																											
																																																																																											    int sizeMethod () {
																																																																																											            return 1 + size(f);
																																																																																												        }
																																																																																													
																																																																																													    @Override
																																																																																													    	
																																																																																														    public String toString () {
																																																																																														            if (isEmpty(f))
																																																																																															                return "[" + x.toString() + "]";
																																																																																																	        else
																																																																																																		            return "[" + x.toString() + ", "
																																																																																																			                   + f.toString().substring(1, f.toString().length());
																																																																																																					       }

																																																																																																					           boolean containsM(Double y){
																																																																																																						   	if (x.equals(y)){
																																																																																																								    return true;
																																																																																																								    	}else{
																																																																																																										    return FListDouble.contains (f, y);
																																																																																																										    	}
																																																																																																											    }
																																																																																																											    }
