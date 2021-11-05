
public class Gram {
	public static boolean flag = true;
	public static int position = 0;
	public static String expression="";
	public static void testfalse(String s) {
		System.out.println(s+" das");
	}
	public static void CompUnit() {
		FuncDef();
	}
	public static void FuncDef(){
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		FuncType();
		Ident();
		if(!deal.Wordlist.get(position).getName().equals("(")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return;
		}
		position++;
		if(!deal.Wordlist.get(position).getName().equals(")")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return;
		}
		position++;
		Block();
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
	public static void Block(){
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(!deal.Wordlist.get(position).getName().equals("{")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return;
		}
		position++;
		Stmt();
		if(!deal.Wordlist.get(position).getName().equals("}")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return;
		}
	}
	public static void Stmt(){
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(!deal.Wordlist.get(position).getName().equals("return")){
			testfalse(deal.Wordlist.get(position).getName()+" stmt return");
			flag = false;
			return;
		}
		position++;
		Exp();
		if(!deal.Wordlist.get(position).getName().equals(";")){
			testfalse(deal.Wordlist.get(position).getName() +" stmt;");
			flag = false;
			return;
		}
		position++;
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
	public static void Exp(){
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		AddExp();
	}
	public static void AddExp(){
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		MulExp();
		while(deal.Wordlist.get(position).getName().equals("+")||deal.Wordlist.get(position).getName().equals("-")) {
			expression+=deal.Wordlist.get(position).getName();
			position++;
			MulExp();
		}
	}
	public static void MulExp(){
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		UnaryExp();
		while(deal.Wordlist.get(position).getName().equals("*")||deal.Wordlist.get(position).getName().equals("/")||deal.Wordlist.get(position).getName().equals("%")) {
			expression+=deal.Wordlist.get(position).getName();
			position++;
			UnaryExp();
		}
	}
	public static void UnaryExp(){
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(deal.Wordlist.get(position).getName().equals("(")||deal.Wordlist.get(position).getType().equals("Number")) {
			PrimaryExp();
		}
		else if(deal.Wordlist.get(position).getName().equals("+")||deal.Wordlist.get(position).getName().equals("-")) {
			UnaryOp();
			UnaryExp();
		}
		else {
			testfalse(deal.Wordlist.get(position).getName()+" UnaryExp");
			flag = false;
			return;
		}
	}
	public static void UnaryOp(){
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(deal.Wordlist.get(position).getName().equals("+")) {
			expression+=deal.Wordlist.get(position).getName();
			position++;
			return;
		}
		else if(deal.Wordlist.get(position).getName().equals("-")) {
			expression+=deal.Wordlist.get(position).getName();
			position++;
			return;
		}
		else {
			testfalse(deal.Wordlist.get(position).getName()+" UnaryOp");
			flag = false;
			return;
		}
	}
	public static void PrimaryExp(){
		if(!flag) return;
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(deal.Wordlist.get(position).getType().equals("Number")) {
			expression+=deal.Wordlist.get(position).getName();
			position++;
			return;
		}
		if(!deal.Wordlist.get(position).getName().equals("(")){
			testfalse(deal.Wordlist.get(position).getName()+" pri(");
			flag = false;
			return;
		}
		expression+=deal.Wordlist.get(position).getName();
		position++;
		Exp();
		if(!deal.Wordlist.get(position).getName().equals(")")) {
			testfalse(deal.Wordlist.get(position).getName()+" pri)");
			flag = false;
			return;
		}
		expression+=deal.Wordlist.get(position).getName();
		position++;
		return;
	}
	
}
//Block    -> '{' Stmt '}'
//Stmt     -> 'return' Number ';'