
import java.util.Stack;

public class Calculator {
	public static String tempculString;
	private static int NP = 1;												//标记进栈数字元素的正负性
	private static boolean NPCanUse = true;								//NP标记是否是**状态
	//private boolean flag = true;									//用于标记前一个符号是否是')'，若是，则当前数字字符串一定为空
	private static Stack<Float> figure = new Stack<Float>();				//数字栈
	private static Stack<Character> operator = new Stack<Character>();		//符号栈
	private static StringBuffer figureString = new StringBuffer();			//数字字符串，用于暂时存放读取到的单个数字字符，并将它们拼接成一个完整的数字
	
	/**
	 * @description 计算运算式（主程序）
	 * 				<br>
	 * 				根据传入的代表算式的字符串计算结果并返回
	 * @param formula 代表算式的字符串
	 * @return 计算结果
	 */
	public static float calculate(String formula) throws Exception
	{
		formula = ("0+(0+")+(formula)+")";
		formula += "#";
		//operator.push('#');
		for (int i = 0;formula.charAt(i)!='#';i ++)
		//for (int i = 0;i < formula.length();i ++)
		{
			//读取到非运算符（）时进行的操作
			if (!isOperator(formula.charAt(i)))
			{
				doWhenCharIsNotOperator(formula.charAt(i));
			}
			//读取到运算符（）时进行的操作
			else
				doWhenCharIsOperator(formula.charAt(i),formula.charAt(i - 1));
		}
		//if (getPriority(operator.pop()) != -4)
		while(!operator.isEmpty()) {
			//字符串读取完成，但是符号栈内仍然有运算符
			//return 0;
			float a = figure.pop();
			float b = figure.pop();
			figure.push(operate(operator.pop(),b,a));}
			//throw new Exception("not#");
		return figure.pop();
	}
	
	
	//判断符号c是否为运算符，若是则返回true
	private static boolean isOperator(char c)
	{
		return getPriority(c) != -5;
	}

	//获取字符c的优先级并返回
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

	//根据运算符和两个数字计算结果
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * @description 当前读取到的字符不是运算符时进行的操作
	 * 				<br>
	 * 				说明读取到的字符是数字，将它接到数字字符串之后
	 * 				<br>
	 * 				由于下一个字符一定不会是正负号，所以**标记睡眠
	 * 
	 * @param currentCharacter 当前读取到的字符
	 */
	private static void doWhenCharIsNotOperator(char currentCharacter)
	{
		figureString.append(currentCharacter);	
		NPCanUse = false;		//NP标记休眠
	}
	
	
	/**
	 * @description 当前读取到的字符是运算符时进行的操作
	 * 				<br>
	 * 
	 * @param currentCharacter 当前读取到的字符
	 * @throws Exception 
	 */
	private static void doWhenCharIsOperator(char currentCharacter,char proCharacter) throws Exception
	{
		//不是（当前符号是加减号且正负号标记**状态），即当前符号不是正负号
		if (!(getPriority(currentCharacter) == 1 && NPCanUse == true))
		{
			pushFigure(currentCharacter,proCharacter);		//尝试将数字字符串进栈数字栈（有些特殊情况不能进栈）
			operate(currentCharacter);			//尝试运算（有些情况当前字符不会参与运算）
		}
		//当前符号是正负号，并且为符号时，正负号标记*-1
		else
			NP *= currentCharacter == '-' ? -1: 1; 
		//当读取到加减号或左括号，下一个加减号视为正负号，NP**
		if (getPriority(currentCharacter) == 1 || getPriority(currentCharacter) == 3)
			NPCanUse = true;
	}
	
	
	
	/**
	 * @description 数字栈进栈
	 * 				<br>
	 * 				根据当前符号判断是否将数字符号串内的内容转为数字并进栈数字栈
	 * 				<br>
	 * 				并重置NP标记，并将其改为休眠状态
	 * 				<br>
	 * @param currentCharacter 当前读取的符号
	 * @param proCharacter 当前读取的符号的上一个符号
	 */
	private static void pushFigure(char currentCharacter,char proCharacter)
	{
		//若当前符号不是左括号且前一个符号不是右括号，数字栈进栈并将数字字符串清空。否则当前数字字符串内应该是没有内容的
		//若当前符号是左括号，说明之前的不是数字，那在之前数字字符串就被清空过；前一个符号是右括号时同理
		if (currentCharacter != '(' && proCharacter != ')')
		{
			figure.push(Float.valueOf(figureString.toString()) * NP);		//将数字字符串的内容转为数字并进栈数字栈
			//System.out.println(figure);
			figureString.setLength(0);										//清空数字字符串
			NP = 1;					//重置NP及其**状态
			NPCanUse = false;		//NP休眠
		}		
	}
	
	
	
	/**
	 * @description 运算操作
	 * 				<br>
	 * 				根据当前符号的意义判断是否进行运算，如果是则进行运算
	 * 				<br>
	 * 				如果当前符号暂时不能参与运算，根据当前符号做相应的处理
	 * 				<br>
	 * @param currentCharacter 当前读取的符号
	 * @throws Exception 
	 */
	private static void operate(char currentCharacter) throws Exception
	{
		boolean tem =  true;
		if(!operator.isEmpty()&&currentCharacter == ')'&&(operator.peek()=='(')) {
			operator.pop();
			tem = false;
		}
		//尝试将当前符号拿来参与运算
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
			//System.out.println(figure);
		}}
		if(tem) {
			if (!operator.isEmpty() && getPriority(currentCharacter) < getPriority(operator.peek()) && getPriority(currentCharacter) + getPriority(operator.peek()) == 0)
			{
				if(operator.isEmpty()) {
					System.out.println("lalala");
				}
				else operator.pop();							//去括号
			}
			else
				operator.push(currentCharacter);		//当前符号优先级太低没参与运算，且不是右括号，进栈符号栈
		}
		//System.out.println(operator);
	}
	
	
	
}