import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.locks.Condition;

import Symbol.Symbol_base;
import Symbol.Symbol_table;
import Tree_design.*;
public class Gram {
	public static boolean flag = true;
	public static int position = 0;
	public static String expression="";
	public static void testfalse(String s) {
		System.out.println(s+" das");
	}
	public static Base_tree CompUnit() {
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		while(deal.Wordlist.get(position).getName().equals("const")||deal.Wordlist.get(position).getName().equals("int")&&!deal.Wordlist.get(position+1).getName().equals("main")) {
			if(deal.Wordlist.get(position).getName().equals("const")){
				position+=1;
				Base_tree consTree = ConstDecl();
				consTree.traverse_tree();
			}
			else {
				String typeString = Btype();
				if(!deal.Wordlist.get(position).getType().equals("Ident")){
					testfalse(deal.Wordlist.get(position).getName()+"varDecl");
					flag = false;
					return null;
				}
				String name = deal.Wordlist.get(position).getName();
				position++;
				Def temDef;
				Symbol_base temBase;
				ArrayList<Def> var = new ArrayList<Def>();
				if(deal.Wordlist.get(position).getName().equals("=")){
					position++;
					Base_tree addBase_tree = Exp();
					String temString = addBase_tree.traverse_cal();
					Symbol_table.table.add(new Symbol_base(name, temString, false, "int", "@"+name, 0));
					System.out.println("@"+name+" = dso_local global i32 "+temString);
				}
				else {
					Symbol_table.table.add(new Symbol_base(name,"0", false, "int", "@"+name, 0));
					System.out.println("@"+name+" = dso_local global i32 "+0);
				}		
				while(deal.Wordlist.get(position).getName().equals(",")) {
					position++;
					name = deal.Wordlist.get(position).getName();
					if(deal.Wordlist.get(position+1).getName().equals("=")){
						position+=2;
						Base_tree addBase_tree = AddExp();
						String temString = addBase_tree.traverse_cal();
						Symbol_table.table.add(new Symbol_base(name, temString, false, "int", "@"+name, 0));
						System.out.println("@"+name+" = dso_local global i32 "+temString);
					}
					else {
						position++;
						Symbol_table.table.add(new Symbol_base(name,"0", false, "int", "@"+name, 0));
						System.out.println("@"+name+" = dso_local global i32 "+0);
						
					}		
				}
				if(!deal.Wordlist.get(position).getName().equals(";")){
					testfalse(deal.Wordlist.get(position).getName()+"var2Decl");
					flag = false;
					return null;
				}
				position++;
			}
		}
		Symbol_table.nowdiv++;
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
		position++;
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
		else if(deal.Wordlist.get(position).getType().equals("Ident")||deal.Wordlist.get(position).getType().equals(";")||deal.Wordlist.get(position).getName().equals("while")||deal.Wordlist.get(position).getName().equals("if")||deal.Wordlist.get(position).getName().equals("{")||deal.Wordlist.get(position).getName().equals("return")||deal.Wordlist.get(position).getName().equals("+")||deal.Wordlist.get(position).getName().equals("-")||deal.Wordlist.get(position).getName().equals("(")){
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
				name = deal.Wordlist.get(position).getName();
				position+=2;
				temDef = new Def(name, AddExp());
			}
			else {
				temDef = new Def(deal.Wordlist.get(position).getName(), null);
				position++;
			}		
			var.add(temDef);
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
			name = deal.Wordlist.get(position).getName();
			position++;
			if(!deal.Wordlist.get(position).getName().equals("=")){
				testfalse(deal.Wordlist.get(position).getName()+"Decl"+deal.Wordlist.get(position+1).getName());
				flag = false;
				return null;
			}
			position++;
			temDef = new Def(name, AddExp());
			var.add(temDef);
		}
		if(!deal.Wordlist.get(position).getName().equals(";")){
			testfalse(deal.Wordlist.get(position).getName()+"Decl"+deal.Wordlist.get(position+1).getName()+deal.Wordlist.get(position+2).getName()+deal.Wordlist.get(position+3).getName());
			flag = false;
			return null;
		}
		position++;
		return new Decl_tree(typeString,true,var);
	}
	public static String Btype() {
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
			Base_tree L = new Lvar_tree(typeString,true);
			Base_tree tem = new Stmt_tree("assign", L, R);
			if(!deal.Wordlist.get(position).getName().equals(";")){
				testfalse(deal.Wordlist.get(position).getName() +" stmt;");
				flag = false;
				return null;
			}
			position++;
			return tem;
		}
		else if(deal.Wordlist.get(position).getName().equals("if")){
			if(!deal.Wordlist.get(position+1).getName().equals("(")){
				testfalse(deal.Wordlist.get(position).getName() +" stmt;");
				flag = false;
				return null;
			}
			position+=2;
			Base_tree In = Cond();
			if(!deal.Wordlist.get(position).getName().equals(")")){
				testfalse(deal.Wordlist.get(position).getName() +" stmt;");
				flag = false;
				return null;
			}
			position++;
			Base_tree L = Stmt();
			Base_tree R = null;
			if(deal.Wordlist.get(position).getName().equals("else")) {
				position++;
				R = Stmt();
			}
			Base_tree tem = new Stmt_tree("condition", L, R, In);
			return tem;
		}
		else if(deal.Wordlist.get(position).getName().equals("while")){
			if(!deal.Wordlist.get(position+1).getName().equals("(")){
				testfalse(deal.Wordlist.get(position).getName() +" stmt;");
				flag = false;
				return null;
			}
			position+=2;
			Base_tree In = Cond();
			if(!deal.Wordlist.get(position).getName().equals(")")){
				testfalse(deal.Wordlist.get(position).getName() +" stmt;");
				flag = false;
				return null;
			}
			position++;
			Base_tree L = Stmt();
			Base_tree tem = new Stmt_tree("while", L, null, In);
			return tem;
		}
		else if(deal.Wordlist.get(position).getName().equals(";")){
			position++;
			return null;
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
		else if(deal.Wordlist.get(position).getName().equals("{")){
			Base_tree R = null;
			Base_tree L = Block();
			Base_tree tem = new Stmt_tree("block", L, R);
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
	public static Base_tree Cond(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		return LorExp();
	}
	public static Base_tree LorExp(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		Base_tree L = LandExp();
		Base_tree R = null;
		while(deal.Wordlist.get(position).getName().equals("|")&&deal.Wordlist.get(position+1).getName().equals("|")) {
			//System.out.print(deal.Wordlist.get(position).getName());
			String typeString = deal.Wordlist.get(position).getName();
			position+=2;
			R = LandExp();
			L = new LOrExp_tree("or", L, R);
		}
		L = new LOrExp_tree("or", L, null);
		return L;
	}
	public static Base_tree LandExp(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		Base_tree L = EqExp();
		Base_tree R = null;
		while(deal.Wordlist.get(position).getName().equals("&")&&deal.Wordlist.get(position+1).getName().equals("&")) {
			//System.out.print(deal.Wordlist.get(position).getName());
			String typeString = deal.Wordlist.get(position).getName();
			position+=2;
			R = EqExp();
			L = new LAndExp_tree("and", L, R);
		}
		L = new LAndExp_tree("and", L, null);
		return L;
	}
	public static Base_tree EqExp(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		Base_tree L = RelExp();
		Base_tree R= null;
		while((deal.Wordlist.get(position).getName().equals("=")||(deal.Wordlist.get(position).getName().equals("!")))&&(deal.Wordlist.get(position+1).getName().equals("="))) {
			//System.out.print(deal.Wordlist.get(position).getName());
			String typeString = deal.Wordlist.get(position).getName()+'=';
			position+=2;
			R = RelExp();
			L = new EqExp_tree(typeString, L, R);
		}
		L = new EqExp_tree("pass", L, null);
		return L;
	}
	public static Base_tree RelExp(){
		if(!flag) return null;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		Base_tree L = AddExp();
		while(deal.Wordlist.get(position).getName().equals("<")||deal.Wordlist.get(position).getName().equals(">")) {
			String temstr = deal.Wordlist.get(position).getName();
			position++;
			if(deal.Wordlist.get(position).getName().equals("=")) {
				temstr += '=';
				position++;
			}
			//System.out.print(deal.Wordlist.get(position).getName());
			String typeString = deal.Wordlist.get(position).getName();
			//position++;
			Base_tree R = AddExp();
			L = new RelExp_tree(temstr, L, R);
		}
		return L;
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
		else if(deal.Wordlist.get(position).getName().equals("+")||deal.Wordlist.get(position).getName().equals("-")||deal.Wordlist.get(position).getName().equals("!")) {
			Base_tree L = new UnaryExp_tree(UnaryOp(),UnaryExp());
			return L;
		}
		else {
			testfalse(deal.Wordlist.get(position).getName()+" UnaryExp"+deal.Wordlist.get(position+1).getName()+deal.Wordlist.get(position+2).getName()+deal.Wordlist.get(position+4).getName()+deal.Wordlist.get(position+9).getName());
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
		else if(deal.Wordlist.get(position).getName().equals("putch")) {
			String name = deal.Wordlist.get(position).getName();
			position+=2;
			tem = new Funcdeal_tree(name, null, Exp());
		}	
		else if(deal.Wordlist.get(position).getName().equals("getint")) {
			String name = deal.Wordlist.get(position).getName();
			if((!deal.Wordlist.get(position+1).getName().equals("("))||(!deal.Wordlist.get(position+2).getName().equals(")"))) {
				testfalse(deal.Wordlist.get(position).getName()+" funcdealget");
				flag = false;
				return null;
			}
			position+=2;
			tem = new Funcdeal_tree(name, null, null);
		}
		if(deal.Wordlist.get(position).getName().equals("getch")) {
			String name = deal.Wordlist.get(position).getName();
			if((!deal.Wordlist.get(position+1).getName().equals("("))||(!deal.Wordlist.get(position+2).getName().equals(")"))) {
				testfalse(deal.Wordlist.get(position).getName()+" funcdealget");
				flag = false;
				return null;
			}
			position+=2;
			tem = new Funcdeal_tree(name, null, null);
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
		else if(deal.Wordlist.get(position).getName().equals("!")) {
			//expression+=deal.Wordlist.get(position).getName();
			position++;
			return "!";
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
			Base_tree L = new Lvar_tree(deal.Wordlist.get(position).getName(),false);
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