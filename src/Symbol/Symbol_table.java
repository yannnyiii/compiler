package Symbol;

import java.util.Stack;

public class Symbol_table {
	public static Stack<Symbol_base> table = new Stack<Symbol_base>();	
	public static int nowdiv = 0;
	public static void print(){
		for(int i=0;i<table.size();i++) {	
			System.out.println(table.get(i).name);
		}
	}
}
