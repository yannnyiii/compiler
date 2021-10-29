
public class Gram {
	public static boolean flag = true;
	public static int position = 0;
	public static void testfalse(String s) {
//		System.out.println(s+"das");
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
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return;
		}
		position++;
		number();
		if(!deal.Wordlist.get(position).getName().equals(";")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return;
		}
		position++;
	}
	public static void number(){
		while(deal.Wordlist.get(position).getName().equals(" ")) {
			position++;
		}
		if(!flag) return;
		if(!deal.Wordlist.get(position).getType().equals("Number")){
			testfalse(deal.Wordlist.get(position).getName());
			flag = false;
			return;
		}
		position++;
	}
}
//Block    -> '{' Stmt '}'
//Stmt     -> 'return' Number ';'