import java.util.ArrayList;
import java.util.Stack;

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
		ArrayList<Base_tree> temitem = new ArrayList<Base_tree>();
		while(!deal.Wordlist.get(position).getName().equals("}")) {
			temitem.add(Blockitem());
		}
		if(!deal.Wordlist.get(position).getName().equals("}")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return null;
		}
		Block_tree temBlock_tree = new Block_tree(null, temitem);
		return temBlock_tree;
	}
	public static Base_tree Blockitem(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		Base_tree tempBase_tree = null;
		if(deal.Wordlist.get(position).getName().equals(";")){
			position++;
			return null;
		}
		else if(deal.Wordlist.get(position).getName().equals("const")||deal.Wordlist.get(position).getName().equals("int")){
			tempBase_tree = Decl();
		}
		else if(deal.Wordlist.get(position).getType().equals("Ident")||deal.Wordlist.get(position).getName().equals("return")||deal.Wordlist.get(position).getName().equals("+")||deal.Wordlist.get(position).getName().equals("-")||deal.Wordlist.get(position).getName().equals("(")){
			tempBase_tree = Stmt();
		}
		else {
			testfalse(deal.Wordlist.get(position).getName()+"blockitem");
			flag = false;
			return null;
		}
		return tempBase_tree;
	}
	public static Base_tree Decl(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(deal.Wordlist.get(position).getName().equals("const")){
			position++;
			return ConstDecl();
		}
		else if(deal.Wordlist.get(position).getName().equals("int")){
			return VarDecl();
		}
		else {
			testfalse(deal.Wordlist.get(position).getName()+"baseDecl");
			flag = false;
			return null;
		}
	}
	private static Base_tree VarDecl() {
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		String typeString = Btype();
		if(!deal.Wordlist.get(position).getType().equals("Ident")){
			testfalse(deal.Wordlist.get(position).getName()+"varDecl");
			flag = false;
			return null;
		}
		String name = deal.Wordlist.get(position).getName();
		position++;
		Def temDef;
		ArrayList<Def> var = new ArrayList<Def>();
		if(deal.Wordlist.get(position).getName().equals("=")){
			position++;
			temDef = new Def(name, AddExp());
		}
		else {
			temDef = new Def(name, null);
		}		
		var.add(temDef);
		while(deal.Wordlist.get(position).getName().equals(",")) {
			position++;
			if(deal.Wordlist.get(position+1).getName().equals("=")){
				position++;
				temDef = new Def(deal.Wordlist.get(position).getName(), AddExp());
			}
			else {
				temDef = new Def(deal.Wordlist.get(position).getName(), null);
			}		
			var.add(temDef);
			position++;
		}
		if(!deal.Wordlist.get(position).getName().equals(";")){
			testfalse(deal.Wordlist.get(position).getName()+"var2Decl");
			flag = false;
			return null;
		}
		position++;
		return new Decl_tree(typeString,false,var);
	}
	public static Base_tree ConstDecl(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		String typeString = Btype();
		if(!deal.Wordlist.get(position).getType().equals("Ident")){
			testfalse(deal.Wordlist.get(position).getName()+"constDecl");
			flag = false;
			return null;
		}
		String name = deal.Wordlist.get(position).getName();
		position++;
		if(!deal.Wordlist.get(position).getName().equals("=")){
			testfalse(deal.Wordlist.get(position).getName()+"Decl");
			flag = false;
			return null;
		}
		position++;
		Def temDef = new Def(name, AddExp());
		ArrayList<Def> var = new ArrayList<Def>();
		var.add(temDef);
		while(deal.Wordlist.get(position).getName().equals(",")) {
			position++;
			temDef = new Def(name, AddExp());
			var.add(temDef);
		}
		if(!deal.Wordlist.get(position).getName().equals(";")){
			testfalse(deal.Wordlist.get(position).getName()+"Decl");
			flag = false;
			return null;
		}
		position++;
		return new Decl_tree(typeString,true,var);
	}
	private static String Btype() {
		if(!deal.Wordlist.get(position).getName().equals("int")){
			testfalse(deal.Wordlist.get(position).getName()+"Btype");
			flag = false;
			return null;
		}
		position++;
		return "int";
	}
	public static Base_tree Stmt(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(deal.Wordlist.get(position).getType().equals("Ident")&&deal.Wordlist.get(position+1).getName().equals("=")){
			String typeString = deal.Wordlist.get(position).getName();
			position++;position++;
			Base_tree R = Exp();
			Base_tree L = new Lvar_tree(typeString);
			Base_tree tem = new Stmt_tree("assign", L, R);
			if(!deal.Wordlist.get(position).getName().equals(";")){
				testfalse(deal.Wordlist.get(position).getName() +" stmt;");
				flag = false;
				return null;
			}
			position++;
			return tem;
		}
		else if(deal.Wordlist.get(position).getName().equals("return")){
			position++;
			Base_tree R = null;
			Base_tree L = Exp();
			Base_tree tem = new Stmt_tree("return", L, R);
			if(!deal.Wordlist.get(position).getName().equals(";")){
				testfalse(deal.Wordlist.get(position).getName() +" stmt;");
				flag = false;
				return null;
			}
			position++;
			return tem;
		}
		else {
			Base_tree R = null;
			Base_tree L = Exp();
			Base_tree tem = new Stmt_tree("exp", L, R);
			if(!deal.Wordlist.get(position).getName().equals(";")){
				testfalse(deal.Wordlist.get(position).getName() +" stmt;");
				flag = false;
				return null;
			}
			position++;
			return tem;
		}
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
			L = new MulExp_tree(typeString, L, R);
		}
		return L;
	}
	public static Base_tree UnaryExp(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(deal.Wordlist.get(position).getType().equals("Ident")&&deal.Wordlist.get(position+1).getName().equals("(")) {
			return Funcdeal();
		}
		if(deal.Wordlist.get(position).getName().equals("(")||deal.Wordlist.get(position).getType().equals("Number")||deal.Wordlist.get(position).getType().equals("Ident")) {
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
	public static Base_tree Funcdeal(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		Funcdeal_tree tem = null;
		if(deal.Wordlist.get(position).getName().equals("putint")) {
			String name = deal.Wordlist.get(position).getName();
			position+=2;
			tem = new Funcdeal_tree(name, null, Exp());
		}	
		if(!deal.Wordlist.get(position).getName().equals(")")){
			testfalse(deal.Wordlist.get(position).getName()+" funcdeal");
			flag = false;
			return null;
		}
		position++;
		return tem;
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
		if(deal.Wordlist.get(position).getType().equals("Ident")) {
			expression+=deal.Wordlist.get(position).getName();
			Base_tree L = new Lvar_tree(deal.Wordlist.get(position).getName());
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