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
		while (compile1.hasNextLine()) {
			System.out.println(compile1.nextLine());
		}
//		while (compile1.hasNextLine()) {
//			line++;
//			temString = compile1.nextLine();
//			length = temString.length();
//			if(temString.charAt(0)=='\r') {
//				continue;
//			}
//			for(flag = 0;flag< length;flag++) {
//				if(esc) {
//					System.exit(1);
//				}
//				deal.compile(temString.charAt(flag),compile1);
//			}
//		}
//		if(!Gram.flag) {
//			System.exit(8);
//		}
//		Gram.CompUnit();
//		if(Gram.flag) {
//			System.out.println("define dso_local i32 @main(){");
//			try {
//				System.out.println("    ret i32 " + (int)Calculator.calculate(Gram.expression));
//			} catch (Exception e) {
//				System.exit(8);
//			}
//			System.out.println("}");
//			return;
//		}
//		else {
//			System.exit(8);
//		}

	}
}
