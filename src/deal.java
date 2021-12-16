import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class deal {
	public static List<Word> Wordlist = new ArrayList<Word>();
	public static List<String> printtt= new ArrayList<String>();
	public static String lab1 ;
	public static Word temWord ;
	public static void printword() {
		Iterator<Word> it = Wordlist.iterator();
		Word temp = temWord;
		while (it.hasNext()) {
			temp = it.next();
			System.out.print(temp.getName());
		}
	}
	public static void compile(char temchar,Scanner temScanner) {
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
			temWord = new Word("Semicolon", ";", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar==',') {
			temWord = new Word(",", ",", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='(') {
			temWord = new Word("Cal", "(", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar==')') {
			temWord = new Word("Cal", ")", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='{') {
			temWord = new Word("LBrace", "{", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='}') {
			temWord = new Word("RBrace", "}", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='+') {
			temWord = new Word("Cal", "+", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='-') {
			temWord = new Word("Cal", "-", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='*') {
			temWord = new Word("Cal", "*", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='%') {
			temWord = new Word("Cal", "%", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='/') {
			if((test.flag<test.length-1)&&(test.temString.charAt(test.flag+1)=='/')){
				test.flag = test.length;
				return;
			}
			if((test.flag<test.length-1)&&(test.temString.charAt(test.flag+1)=='*')) {
				test.flag++;
				for(test.flag++;test.flag< test.length;test.flag++) {
					if(test.esc) {
						System.exit(1);
					}
					//System.out.println(test.temString.charAt(test.flag)+"?"+test.flag);
					if((test.flag<test.length-1)&&(test.temString.charAt(test.flag)=='*')&&(test.temString.charAt(test.flag+1)=='/')) {
						//System.out.println(test.temString.charAt(test.flag)+">"+test.flag);
						test.flag++;
						return;
					}
				}
				while (temScanner.hasNext()) {
					test.line++;
					test.temString = temScanner.nextLine();
					if(test.temString.length()==0||test.temString == null) {
						return;
					}
					test.length = test.temString.length();
					for(test.flag=0;test.flag< test.length;test.flag++) {
						if(test.esc) {
							System.exit(1);
						}
						if((test.flag<test.length-1)&&(test.temString.charAt(test.flag)=='*')&&(test.temString.charAt(test.flag+1)=='/')) {
							test.flag++;
							return;
						}
					}
				}
				Gram.flag = false;
				return;
			}
			temWord = new Word("Cal", "/", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='<') {
			temWord = new Word("Lt", "<", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='>') {
			temWord = new Word("Gt", ">", test.line);
			Wordlist.add(temWord);
			return;
		}
//		if(temchar==' ') {
//			temWord = new Word("Space", " ", test.line);System.out.println("ao");
//			Wordlist.add(temWord);
//			return;
//		}
		if(temchar==' '||temchar=='\r'||temchar=='\n'||temchar=='\t') {
			return;
		}
//		if(temchar=='=') {
//			test.flag++;
//			if(test.flag < test.length&&test.temString.charAt(test.flag)=='=') {
//				temWord = new Word("Eq", "==", test.line);
//				Wordlist.add(temWord);
//			}
//			else {
//				temWord = new Word("Assign","=", test.line);
//				Wordlist.add(temWord);
//				test.flag--;
//			}
//			return;
//		}
		if(temchar=='=') {
			temWord = new Word("Eq", "=", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='|') {
			temWord = new Word("|", "|", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='&') {
			temWord = new Word("&", "&", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='!') {
			temWord = new Word("!", "!", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar=='[') {
			temWord = new Word("[", "[", test.line);
			Wordlist.add(temWord);
			return;
		}
		if(temchar==']') {
			temWord = new Word("]", "]", test.line);
			Wordlist.add(temWord);
			return;
		}
		System.out.println("Err");//System.out.println(temchar);
		test.esc = true;
		return;
	}
	public static void compilenum(char temchar){
		test.flag++;
		if(test.flag >= test.length) {
			temWord = new Word("Number", ""+temchar , test.line);
			Wordlist.add(temWord);
			lab1 = ""+temchar;
			test.flag--;
			return ;
		}
		if(temchar == '0') {
			temchar = test.temString.charAt(test.flag);
			if(temchar == 'x'||temchar == 'X') {
				test.flag++;
				long num = 0;
				char now = test.temString.charAt(test.flag);
				while((test.flag < test.length)&&(Character.isDigit(test.temString.charAt(test.flag))||((test.temString.charAt(test.flag)>='a')&&(test.temString.charAt(test.flag)<='f'))||((test.temString.charAt(test.flag)>='A')&&(test.temString.charAt(test.flag)<='F')))) {
					now = test.temString.charAt(test.flag);
					int k;
					if(now>'9'&&now<'A') {
						k = now - 'a' + 10;
					}
					else if(now>='A'){
						k = now - 'A' + 10;
					}
					else {
						k = now -'0';
					}
					num = num*16 + k;
					test.flag++;
				}
				temWord = new Word("Number", String.valueOf(num) , test.line);
				Wordlist.add(temWord);
				lab1 = String.valueOf(num);
				test.flag--;
				return;
			}
			else {
				long num = 0;
				char now = test.temString.charAt(test.flag);
				while((test.flag < test.length)&&(Character.isDigit(test.temString.charAt(test.flag)))&&(test.temString.charAt(test.flag)<'8')) {
					now = test.temString.charAt(test.flag);
					num = num*8 + now -'0';
					test.flag++;
				}
				temWord = new Word("Number", String.valueOf(num) , test.line);
				Wordlist.add(temWord);
				lab1 = String.valueOf(num);
				test.flag--;
				return;
			}
		}
		test.num = String.valueOf(temchar);
		while((test.flag < test.length)&&Character.isDigit(test.temString.charAt(test.flag))) {
			test.num = test.num+test.temString.charAt(test.flag);
			test.flag++;
		}
		temWord = new Word("Number", test.num , test.line);
		Wordlist.add(temWord);
		lab1 = String.valueOf(test.num);
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
			temWord = new Word("Ident", test.alphString, test.line); 
			Wordlist.add(temWord);
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
		temWord = new Word("Ident", test.alphString, test.line);
		Wordlist.add(temWord);
		//System.out.println("Ident("+test.alphString+")");	
		test.flag--;
	}
	public static boolean iskey(String temString) {
		if(temString.equals("if")) {
			temWord = new Word("if","if", test.line);
			Wordlist.add(temWord);
			return true;
		}
		if(temString.equals("else")) {
			temWord = new Word("else","else", test.line);
			Wordlist.add(temWord);
			return true;
		}
		if(temString.equals("while")) {
			temWord = new Word("while","while", test.line);
			Wordlist.add(temWord);
			return true;
		}
		if(temString.equals("break")) {
			temWord = new Word("break","break", test.line);
			Wordlist.add(temWord);
			return true;
		}
		if(temString.equals("continue")) {
			temWord = new Word("continue","continue", test.line);
			Wordlist.add(temWord);
			return true;
		}
		if(temString.equals("return")) {
			temWord = new Word("return","return", test.line);
			Wordlist.add(temWord);
			return true;
		}
		if(temString.equals("const")) {
			temWord = new Word("var_type","const", test.line);
			Wordlist.add(temWord);
			return true;
		}
		if(temString.equals("int")) {
			temWord = new Word("var_type","int", test.line);
			Wordlist.add(temWord);
			return true;
		}
		return false;
	}
}
