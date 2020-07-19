package interpreter;

import java.util.HashMap;

public class Context {
	private HashMap varList=new HashMap();
	
	

	public Context() {
		initialize();
	}
	public void assign(String var, int value) {
		varList.put(var, new Integer(value));
	}
	public int getValue(String var) {
		Integer objInt=(Integer) varList.get(var);
		return objInt.intValue();
	}

	private void initialize() {
		
		assign("a",20);
		assign("b",40);
		assign("c",30);
		assign("d",10);
		
	}

}
