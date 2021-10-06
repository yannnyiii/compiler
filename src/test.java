import java.util.*;
public class test {
	public static int flag = 0;
	public static long num = 0;
	public static String temString;
	public static int length;
	public static boolean esc = false;
	public static String alphString;
	public static void main(String[] args) {
		Scanner compile1 = new Scanner(System.in);
		while (compile1.hasNext()) {
			temString = compile1.nextLine();
			length = temString.length();
			for(flag = 0;flag< length;flag++) {
				if(esc) {
					return;
				}
				deal.compile(temString.charAt(flag));
			}
		}
	}
}
