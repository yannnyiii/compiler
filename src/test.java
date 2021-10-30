import java.util.*;
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
		while (compile1.hasNext()) {
			line++;
			temString = compile1.nextLine();
			if(temString.length()==0||temString == null) {
				return;
			}
			length = temString.length();
			for(flag = 0;flag< length;flag++) {
				if(esc) {
					System.exit(1);
				}
				deal.compile(temString.charAt(flag));
//			System.out.println(deal.Wordlist.get(deal.Wordlist.size()-1).getName());
			}
		}
		if(!Gram.flag) {
			System.exit(8);
		}
		Gram.CompUnit();
//		deal.printword();
//		System.out.println(Gram.flag);
		if(Gram.flag) {
			System.out.println("define dso_local i32 @main(){");
			System.out.println("    ret i32 " + deal.lab1);
			System.out.println("}");
			return;
		}
		else {
//			System.exit(Integer.parseInt(deal.lab1));
			System.exit(8);
		}

	}
}
