package Tree_design;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


import Symbol.Symbol_table;

public class Funcdeal_tree extends Base_tree{
	 public Scanner compile2 = new Scanner(System.in);
	 public String name;
	 public String contain_str;
	 public Base_tree contain_Tree;
	 public Funcdeal_tree (String name,String contain_str,Base_tree contain_Tree) {
		super();
		this.name = name;
		this.contain_str = contain_str;
		this.contain_Tree = contain_Tree;
	}
	public String traverse_tree() {
		 if(name.equals("putint")) {
			String temString = contain_Tree.traverse_tree();
			AddExp_tree.cal.add("call void @putint(i32 "+temString+")");
			return temString;
		 }
		 if(name.equals("putch")) {
			String temString = contain_Tree.traverse_tree();
			AddExp_tree.cal.add("call void @putch(i32 "+temString+")");
			return temString;
		 }
		 if(name.equals("getint")) {
			//int a = compile2.nextInt();

			AddExp_tree.cal.add("%x"+AddExp_tree.varinum+" = call void @getint()");
			return ("%x"+(AddExp_tree.varinum++));
		 }
		 if(name.equals("getch")) {
			char c = 0;
			AddExp_tree.cal.add("%x"+AddExp_tree.varinum+" = call void @getch()");
			return ("%x"+(AddExp_tree.varinum++));
		 }
		 return null;
	}
	public String traverse_first() {
		    System.out.println("in");
			return null;
	}
}

