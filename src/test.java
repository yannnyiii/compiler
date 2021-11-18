import java.util.*;

import Symbol.Symbol_table;
import Tree_design.*;
public class test {
	public static int flag = 0;
	public static int line = 0;
	public static String num ;
	public static String temString;
	public static int length;
	public static boolean esc = false;
	public static String alphString;
	public static void main(String[] args) {
		Scanner compile1 = new Scanner(System.in);
		while (compile1.hasNextLine()) {
			line++;
			temString = compile1.nextLine();
			length = temString.length();
			if(temString.charAt(0)=='\r') {
				continue;
			}
			for(flag = 0;flag< length;flag++) {
				if(esc) {
					//System.out.println(temString);
					System.exit(1);
				}
				deal.compile(temString.charAt(flag),compile1);
			}
		}
		if(!Gram.flag) {
			System.exit(8);
		}
		Base_tree temBase_tree = Gram.CompUnit();
		
//		Block_tree aBlock_tree = (Block_tree) temBase_tree;
//		Decl_tree aDecl_tree = (Decl_tree) aBlock_tree.item.get(0);
//		Def aDef = aDecl_tree.var.get(0);
//		AddExp_tree exp_tree = (AddExp_tree) aDef.exp;
//		Number_tree nummNumber_tree = (Number_tree) exp_tree.RBase;
//		//System.out.println(nummNumber_tree.type);
//		//System.out.println(exp_tree.RBase.getClass());
//		

		String aString = temBase_tree.traverse_first();
		aString = temBase_tree.traverse_tree();
		String teeeString;
//		for(int i = 0;i<Symbol_table.table.size();i++) {
//			System.out.println(Symbol_table.table.get(i).number+" "+Symbol_table.table.get(i).name);
//		}
		if(Gram.flag) {
			System.out.println("define dso_local i32 @main(){");
			try {
				for(int i=0;i<AddExp_tree.cal.size();i++) {
					System.out.println(AddExp_tree.cal.get(i));
				}
				System.out.println("    ret i32 " + Stmt_tree.return_value);
				//System.out.println("    ret i32 " + (int)Calculator.calculate(Gram.expression));
			} catch (Exception e) {
				System.exit(8);
			}
			System.out.println("}");
			return;
		}
		else {
			System.exit(8);
		}

	}
}
