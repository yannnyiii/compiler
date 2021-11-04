
import java.util.Stack;

public class Calculator {
	public static String tempculString;
	private static int NP = 1;												//��ǽ�ջ����Ԫ�ص�������
	private static boolean NPCanUse = true;								//NP����Ƿ���**״̬
	//private boolean flag = true;									//���ڱ��ǰһ�������Ƿ���')'�����ǣ���ǰ�����ַ���һ��Ϊ��
	private static Stack<Float> figure = new Stack<Float>();				//����ջ
	private static Stack<Character> operator = new Stack<Character>();		//����ջ
	private static StringBuffer figureString = new StringBuffer();			//�����ַ�����������ʱ��Ŷ�ȡ���ĵ��������ַ�����������ƴ�ӳ�һ������������
	
	/**
	 * @description ��������ʽ��������
	 * 				<br>
	 * 				���ݴ���Ĵ�����ʽ���ַ���������������
	 * @param formula ������ʽ���ַ���
	 * @return ������
	 */
	public static float calculate(String formula) throws Exception
	{
		formula = ("0+(0+")+(formula)+")";
		formula += "#";
		//operator.push('#');
		for (int i = 0;formula.charAt(i)!='#';i ++)
		//for (int i = 0;i < formula.length();i ++)
		{
			//��ȡ�������������ʱ���еĲ���
			if (!isOperator(formula.charAt(i)))
			{
				doWhenCharIsNotOperator(formula.charAt(i));
			}
			//��ȡ�����������ʱ���еĲ���
			else
				doWhenCharIsOperator(formula.charAt(i),formula.charAt(i - 1));
		}
		//if (getPriority(operator.pop()) != -4)
		while(!operator.isEmpty()) {
			//�ַ�����ȡ��ɣ����Ƿ���ջ����Ȼ�������
			//return 0;
			float a = figure.pop();
			float b = figure.pop();
			figure.push(operate(operator.pop(),b,a));}
			//throw new Exception("not#");
		return figure.pop();
	}
	
	
	//�жϷ���c�Ƿ�Ϊ������������򷵻�true
	private static boolean isOperator(char c)
	{
		return getPriority(c) != -5;
	}

	//��ȡ�ַ�c�����ȼ�������
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

	//������������������ּ�����
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * @description ��ǰ��ȡ�����ַ����������ʱ���еĲ���
	 * 				<br>
	 * 				˵����ȡ�����ַ������֣������ӵ������ַ���֮��
	 * 				<br>
	 * 				������һ���ַ�һ�������������ţ�����**���˯��
	 * 
	 * @param currentCharacter ��ǰ��ȡ�����ַ�
	 */
	private static void doWhenCharIsNotOperator(char currentCharacter)
	{
		figureString.append(currentCharacter);	
		NPCanUse = false;		//NP�������
	}
	
	
	/**
	 * @description ��ǰ��ȡ�����ַ��������ʱ���еĲ���
	 * 				<br>
	 * 
	 * @param currentCharacter ��ǰ��ȡ�����ַ�
	 * @throws Exception 
	 */
	private static void doWhenCharIsOperator(char currentCharacter,char proCharacter) throws Exception
	{
		//���ǣ���ǰ�����ǼӼ����������ű��**״̬��������ǰ���Ų���������
		if (!(getPriority(currentCharacter) == 1 && NPCanUse == true))
		{
			pushFigure(currentCharacter,proCharacter);		//���Խ������ַ�����ջ����ջ����Щ����������ܽ�ջ��
			operate(currentCharacter);			//�������㣨��Щ�����ǰ�ַ�����������㣩
		}
		//��ǰ�����������ţ�����Ϊ����ʱ�������ű��*-1
		else
			NP *= currentCharacter == '-' ? -1: 1; 
		//����ȡ���Ӽ��Ż������ţ���һ���Ӽ�����Ϊ�����ţ�NP**
		if (getPriority(currentCharacter) == 1 || getPriority(currentCharacter) == 3)
			NPCanUse = true;
	}
	
	
	
	/**
	 * @description ����ջ��ջ
	 * 				<br>
	 * 				���ݵ�ǰ�����ж��Ƿ����ַ��Ŵ��ڵ�����תΪ���ֲ���ջ����ջ
	 * 				<br>
	 * 				������NP��ǣ��������Ϊ����״̬
	 * 				<br>
	 * @param currentCharacter ��ǰ��ȡ�ķ���
	 * @param proCharacter ��ǰ��ȡ�ķ��ŵ���һ������
	 */
	private static void pushFigure(char currentCharacter,char proCharacter)
	{
		//����ǰ���Ų�����������ǰһ�����Ų��������ţ�����ջ��ջ���������ַ�����ա�����ǰ�����ַ�����Ӧ����û�����ݵ�
		//����ǰ�����������ţ�˵��֮ǰ�Ĳ������֣�����֮ǰ�����ַ����ͱ���չ���ǰһ��������������ʱͬ��
		if (currentCharacter != '(' && proCharacter != ')')
		{
			figure.push(Float.valueOf(figureString.toString()) * NP);		//�������ַ���������תΪ���ֲ���ջ����ջ
			//System.out.println(figure);
			figureString.setLength(0);										//��������ַ���
			NP = 1;					//����NP����**״̬
			NPCanUse = false;		//NP����
		}		
	}
	
	
	
	/**
	 * @description �������
	 * 				<br>
	 * 				���ݵ�ǰ���ŵ������ж��Ƿ�������㣬��������������
	 * 				<br>
	 * 				�����ǰ������ʱ���ܲ������㣬���ݵ�ǰ��������Ӧ�Ĵ���
	 * 				<br>
	 * @param currentCharacter ��ǰ��ȡ�ķ���
	 * @throws Exception 
	 */
	private static void operate(char currentCharacter) throws Exception
	{
		boolean tem =  true;
		if(!operator.isEmpty()&&currentCharacter == ')'&&(operator.peek()=='(')) {
			operator.pop();
			tem = false;
		}
		//���Խ���ǰ����������������
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
				else operator.pop();							//ȥ����
			}
			else
				operator.push(currentCharacter);		//��ǰ�������ȼ�̫��û�������㣬�Ҳ��������ţ���ջ����ջ
		}
		//System.out.println(operator);
	}
	
	
	
}