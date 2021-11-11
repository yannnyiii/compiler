import com.sun.org.apache.xpath.internal.operations.Number;

import Tree_design.*;
public class Gram {
	public static boolean flag = true;
	public static int position = 0;
	public static String expression="";
	public static void testfalse(String s) {
		System.out.println(s+" das");
	}
	public static Base_tree CompUnit() {
		return FuncDef();
	}
	public static Base_tree FuncDef(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		FuncType();
		Ident();
		if(!deal.Wordlist.get(position).getName().equals("(")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return null;
		}
		position++;
		if(!deal.Wordlist.get(position).getName().equals(")")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return null;
		}
		position++;
		return Block();
	}
	public static void FuncType() {
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(!deal.Wordlist.get(position).getName().equals("int")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return;
		}
		position++;
	}
	public static void Ident() {
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(!deal.Wordlist.get(position).getName().equals("main")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return;
		}
		position++;
	}
	public static Base_tree Block(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(!deal.Wordlist.get(position).getName().equals("{")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return null;
		}
		position++;
		Base_tree tempBase_tree = Stmt();
		if(!deal.Wordlist.get(position).getName().equals("}")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return null;
		}
		return tempBase_tree;
	}
	public static Base_tree Stmt(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(!deal.Wordlist.get(position).getName().equals("return")){
			testfalse(deal.Wordlist.get(position).getName()+" stmt return");
			flag = false;
			return null;
		}
		position++;
		Base_tree tempBase_tree = Exp();
		if(!deal.Wordlist.get(position).getName().equals(";")){
			testfalse(deal.Wordlist.get(position).getName() +" stmt;");
			flag = false;
			return null;
		}
		position++;
		return tempBase_tree;
	}
	public static void number(){
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(!deal.Wordlist.get(position).getType().equals("Number")){
			testfalse(deal.Wordlist.get(position).getName()+" number");
			flag = false;
			return;
		}
		position++;
	}
	public static Base_tree Exp(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		return AddExp();
	}
	public static Base_tree AddExp(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		Base_tree L = MulExp();
		while(deal.Wordlist.get(position).getName().equals("+")||deal.Wordlist.get(position).getName().equals("-")) {
			//System.out.print(deal.Wordlist.get(position).getName());
			String typeString = deal.Wordlist.get(position).getName();
			expression+=deal.Wordlist.get(position).getName();
			position++;
			Base_tree R = MulExp();
			L = new AddExp_tree(typeString, L, R);
		}
		return L;
	}
	public static Base_tree MulExp(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		Base_tree L = UnaryExp();
		while(deal.Wordlist.get(position).getName().equals("*")||deal.Wordlist.get(position).getName().equals("/")||deal.Wordlist.get(position).getName().equals("%")) {
			//System.out.print(deal.Wordlist.get(position).getName());
			String typeString = deal.Wordlist.get(position).getName();
			expression+=deal.Wordlist.get(position).getName();
			position++;
			Base_tree R = UnaryExp();
			L = new AddExp_tree(typeString, L, R);
		}
		return L;
	}
	public static Base_tree UnaryExp(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(deal.Wordlist.get(position).getName().equals("(")||deal.Wordlist.get(position).getType().equals("Number")) {
			return PrimaryExp();
		}
		else if(deal.Wordlist.get(position).getName().equals("+")||deal.Wordlist.get(position).getName().equals("-")) {
			Base_tree L = new UnaryExp_tree(UnaryOp(),UnaryExp());
			return L;
		}
		else {
			testfalse(deal.Wordlist.get(position).getName()+" UnaryExp");
			flag = false;
			return null;
		}
	}
	public static String UnaryOp(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(deal.Wordlist.get(position).getName().equals("+")) {
			expression+=deal.Wordlist.get(position).getName();
			position++;
			return "+";
		}
		else if(deal.Wordlist.get(position).getName().equals("-")) {
			expression+=deal.Wordlist.get(position).getName();
			position++;
			return "-";
		}
		else {
			testfalse(deal.Wordlist.get(position).getName()+" UnaryOp");
			flag = false;
			return null;
		}
	}
	public static Base_tree PrimaryExp(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(deal.Wordlist.get(position).getType().equals("Number")) {
			expression+=deal.Wordlist.get(position).getName();
			Base_tree L = new Number_tree(deal.Wordlist.get(position).getName());
			position++;
			return  L;
		}
		if(!deal.Wordlist.get(position).getName().equals("(")){
			testfalse(deal.Wordlist.get(position).getName()+" pri(");
			flag = false;
			return null;
		}
		expression+=deal.Wordlist.get(position).getName();
		position++;
		Base_tree L = AddExp();
		if(!deal.Wordlist.get(position).getName().equals(")")) {
			testfalse(deal.Wordlist.get(position).getName()+" pri)");
			flag = false;
			return null;
		}
		expression+=deal.Wordlist.get(position).getName();
		position++;
		return L;
	}
	
}
//Block    -> '{' Stmt '}'
//Stmt     -> 'return' Number ';'