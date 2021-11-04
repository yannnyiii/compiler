
import java.util.Stack;

public class Calculator {
	public static String tempculString;
	private static int NP = 1;											
	private static boolean NPCanUse = true;							
	//private boolean flag = true;									
	private static Stack<Float> figure = new Stack<Float>();				
	private static Stack<Character> operator = new Stack<Character>();		
	private static StringBuffer figureString = new StringBuffer();			

	public static float calculate(String formula) throws Exception
	{
		formula = ("0+(0+")+(formula)+")";
		formula += "#";
		//operator.push('#');
		for (int i = 0;formula.charAt(i)!='#';i ++)
		//for (int i = 0;i < formula.length();i ++)
		{
			//
			if (!isOperator(formula.charAt(i)))
			{
				doWhenCharIsNotOperator(formula.charAt(i));
			}
			//
			else
				doWhenCharIsOperator(formula.charAt(i),formula.charAt(i - 1));
		}
		//if (getPriority(operator.pop()) != -4)
		while(!operator.isEmpty()) {
			//
			//return 0;
			float a = figure.pop();
			float b = figure.pop();
			figure.push(operate(operator.pop(),b,a));}
			//throw new Exception("not#");
		return figure.pop();
	}
	
	

	private static boolean isOperator(char c)
	{
		return getPriority(c) != -5;
	}


	private static int getPriority(char c)
	{
		switch(c)
		{
			case '+':return 1;
			case '-':return 1;
			case '*':return 2;
			case '/':return 2;
			case '(':return 3;
			case ')':return -3;
			case '#':return -4;
			default:return -5;
		}
	}


	private static float operate(char c,float u,float d) throws Exception
	{
//		if(c!='+'&&c!='-'&&c!='*'&&c!='/') {
//		throw new Exception();	}
		try {
			switch(c)
			{
				case '+':return u + d;
				case '-':return u - d;
				case '*':return u * d;
				case '/':return u / d;
				default:return (float)0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private static void doWhenCharIsNotOperator(char currentCharacter)
	{
		figureString.append(currentCharacter);	
		NPCanUse = false;		
	}
	
	private static void doWhenCharIsOperator(char currentCharacter,char proCharacter) throws Exception
	{
		if (!(getPriority(currentCharacter) == 1 && NPCanUse == true))
		{
			pushFigure(currentCharacter,proCharacter);		
			operate(currentCharacter);		
		}
		else
			NP *= currentCharacter == '-' ? -1: 1; 
		if (getPriority(currentCharacter) == 1 || getPriority(currentCharacter) == 3)
			NPCanUse = true;
	}
	private static void pushFigure(char currentCharacter,char proCharacter)
	{
		if (currentCharacter != '(' && proCharacter != ')')
		{
			figure.push(Float.valueOf(figureString.toString()) * NP);	
			figureString.setLength(0);									
			NP = 1;				
			NPCanUse = false;		
		}		
	}
	private static void operate(char currentCharacter) throws Exception
	{
		boolean tem =  true;
		if(!operator.isEmpty()&&currentCharacter == ')'&&(operator.peek()=='(')) {
			operator.pop();
			tem = false;
		}
		if(tem) {
		while(!operator.isEmpty() && (getPriority(currentCharacter) <= getPriority(operator.peek())) && operator.peek() != '(')
		{
			float u = 0;
			float d = 0;
			if(figure.isEmpty()) {
				throw new Exception("pop d");
			}
			else {d = figure.pop();}
			if(figure.isEmpty()) {
				throw new Exception("pop u");
			}
			else {u = figure.pop();}
			figure.push(operate(operator.pop(),u,d));
		}}
		if(tem) {
			if (!operator.isEmpty() && getPriority(currentCharacter) < getPriority(operator.peek()) && getPriority(currentCharacter) + getPriority(operator.peek()) == 0)
			{
				if(operator.isEmpty()) {
					System.out.println("lalala");
				}
				else operator.pop();						
			}
			else
				operator.push(currentCharacter);	
		}
	}
	
	
	
}