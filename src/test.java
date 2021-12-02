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
			System.out.print(compile1.nextLine()+" ");
		}
		//if(true) return;System.out.println(Gram.flag);
//		while (compile1.hasNextLine()) {
//			line++;
//			temString = compile1.nextLine();
//			if(temString!=null&&temString.length()!=0&&temString.charAt(0)=='\r') {
//				continue;
//			}
//			length = temString.length();				
//			for(flag = 0;flag< length;flag++) {
//				if(esc) {
//					System.exit(1);
//				}
//				deal.compile(temString.charAt(flag),compile1);
//			}
//		}
//		compile1.close();
//		if(!Gram.flag) {
//			System.exit(8);
//		}	
//		Base_tree temBase_tree = Gram.CompUnit();
//		String aString = temBase_tree.traverse_tree();
//		String teeeString;
//		if(Gram.flag) {
//			System.out.println("declare void @putint(i32)");
//			System.out.println("declare i32 @getint()");
//			System.out.println("declare void @putch(i32)");
//			System.out.println("declare i32 @getch()");
//			System.out.println("define dso_local i32 @main(){");
//			try {
//				for(int i=0;i<AddExp_tree.cal.size();i++) {
//					System.out.println(AddExp_tree.cal.get(i));
//				}
//				System.out.println("    ret i32 " + Stmt_tree.return_value);
//				//System.out.println("    ret i32 " + (int)Calculator.calculate(Gram.expression));
//			} catch (Exception e) {
//				System.exit(8);
//			}
//			System.out.println("}");
//			return;
//		}
//		else {
//			System.exit(8);
//		}
//
	}
}
