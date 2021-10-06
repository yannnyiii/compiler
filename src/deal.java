import java.util.*;
public class deal {
	public static void compile(char temchar) {
		if(Character.isDigit(temchar)) {
			compilenum(temchar);
			return;
		}
		if(Character.isLowerCase(temchar)) {
			compilelow(temchar);
			return;
		}
		if(Character.isUpperCase(temchar)||(temchar=='_')) {
			compileup(temchar);
			return;
		}
		if(temchar==';') {
			System.out.println("Semicolon");
			return;
		}
		if(temchar=='(') {
			System.out.println("LPar");
			return;
		}
		if(temchar==')') {
			System.out.println("RPar");
			return;
		}
		if(temchar=='{') {
			System.out.println("LBrace");
			return;
		}
		if(temchar=='}') {
			System.out.println("RBrace");
			return;
		}
		if(temchar=='+') {
			System.out.println("Plus");
			return;
		}
		if(temchar=='*') {
			System.out.println("Mult");
			return;
		}
		if(temchar=='/') {
			System.out.println("Div");
			return;
		}
		if(temchar=='<') {
			System.out.println("Lt");
			return;
		}
		if(temchar=='>') {
			System.out.println("Gt");
			return;
		}
		if(temchar==' '||temchar=='\r'||temchar=='\n'||temchar=='\t') {
			return;
		}
		if(temchar=='=') {
			test.flag++;
			if(test.flag < test.length&&test.temString.charAt(test.flag)=='=') {
				System.out.println("Eq");
			}
			else {
				System.out.println("Assign");
				test.flag--;
			}
			return;
		}
		System.out.println("Err");
		test.esc = true;
		return;
	}
	public static void compilenum(char temchar){
		test.flag++;
		test.num = String.valueOf(temchar);
		while((test.flag < test.length)&&Character.isDigit(test.temString.charAt(test.flag))) {
			test.num = test.num+test.temString.charAt(test.flag);
			test.flag++;
		}
		System.out.println("Number("+test.num+")");
		test.flag--;
	}
	public static void compilelow(char temchar){
		test.flag++;
		test.alphString = String.valueOf(temchar);
		while((test.flag < test.length)&&(Character.isLowerCase(test.temString.charAt(test.flag))||Character.isUpperCase(test.temString.charAt(test.flag))||Character.isDigit(test.temString.charAt(test.flag))||(test.temString.charAt(test.flag)=='_'))) {
			test.alphString = test.alphString+test.temString.charAt(test.flag);
			test.flag++;
		}
		if(!iskey(test.alphString))  {
			System.out.println("Ident("+test.alphString+")");	
		}
		test.flag--;
	}
	public static void compileup(char temchar){
		test.flag++;
		test.alphString = String.valueOf(temchar);
		while((test.flag < test.length)&&(Character.isLowerCase(test.temString.charAt(test.flag))||Character.isUpperCase(test.temString.charAt(test.flag))||Character.isDigit(test.temString.charAt(test.flag))||(test.temString.charAt(test.flag)=='_'))) {
			test.alphString = test.alphString+test.temString.charAt(test.flag);
			test.flag++;
		}
		System.out.println("Ident("+test.alphString+")");	
		test.flag--;
	}
	public static boolean iskey(String temString) {
		if(temString.equals("if")) {
			System.out.println("If");
			return true;
		}
		if(temString.equals("else")) {
			System.out.println("Else");
			return true;
		}
		if(temString.equals("while")) {
			System.out.println("While");
			return true;
		}
		if(temString.equals("break")) {
			System.out.println("Break");
			return true;
		}
		if(temString.equals("continue")) {
			System.out.println("Continue");
			return true;
		}
		if(temString.equals("return")) {
			System.out.println("Return");
			return true;
		}
		return false;
	}
}
