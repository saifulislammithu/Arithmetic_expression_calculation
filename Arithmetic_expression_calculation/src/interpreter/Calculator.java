package interpreter;

import java.util.HashMap;
import java.util.Stack;

public class Calculator {
	private String expression;
	private HashMap operator;
	private Context ctx;



	public static void main(String[] args) {
		Calculator cal=new Calculator();
		Context ctx=new Context();
		cal.setExpression("(a+b)*(c-d)");

		//cal.setContext(ctx);
		System.out.println("Values of variable:"
				+"a="+ctx.getValue("a")+","
				+ "b="+ctx.getValue("b")+","
				+ "c="+ctx.getValue("c")+","
				+ "d="+ctx.getValue("d"));
		System.out.println("Expression:=(a+b)*(c-d)");
		System.out.println("Result="+cal.evaluate());



	}

	public Calculator() {
		operator=new HashMap();
		operator.put("+", "1");
		operator.put("-", "1");
		operator.put("/", "2");
		operator.put("*", "2");
		operator.put("(", "0");
	}
	public String evaluate() {
		String pfExpr=infixToPostFix(expression);
		return pfExpr;
	}
	public boolean isOperator(String op) {
		Boolean opCheck=false;
		if((op.equals("+"))||(op.equals("-"))||(op.equals("*")) ||(op.equals("/"))){
			opCheck=true;
		}

		return opCheck;
	}
	private String infixToPostFix(String str) {
		Stack s=new Stack();
		String pfExpr="";
		String tempStr="";
		String expr=str.trim();
		for(int i=0;i<str.length();i++) {
			String currChar=str.substring(i, i+1);
			if((isOperator(currChar)==false)
					&&(!currChar.equals("("))
					&&(!currChar.equals(")"))
					) {
				pfExpr=pfExpr+currChar;
				System.out.println("PostFixExpr:"+pfExpr);

			}
			if(currChar.equals("(")) {

				s.push(currChar);
			}
			//System.out.println(s.lastElement());
			if(currChar.equals(")")){
				tempStr=(String)s.pop();
				while(!tempStr.equals("(")) {
					pfExpr=pfExpr+tempStr;
					tempStr=(String)s.pop();

				}

			}
			if(isOperator(currChar)) {
				if(s.isEmpty()==false) {
					tempStr=(String)s.pop();
					String strVal1=(String)operator.get(tempStr);
					int val1=new Integer(strVal1).intValue();
					String strVal2=(String)operator.get(currChar);
					int val2=new Integer(strVal2).intValue();
					while(val1>=val2) {
						pfExpr=pfExpr+tempStr;
						val1=-100;
						if(s.isEmpty()==false) {
							tempStr=(String)s.pop();
							strVal1=(String)operator.get(tempStr);
							val1=new Integer(strVal1).intValue();
						}

					}
					if((val1<val2) && (val1!=-100)){
						s.push(tempStr);

					}
					


				}
				s.push(currChar);
			}

		}
		while(s.isEmpty()==false) {
			tempStr=(String)s.pop();
			pfExpr=pfExpr+tempStr;

		}
		

		return pfExpr;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

}

