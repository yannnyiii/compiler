package Tree_design;
import java.util.ArrayList;
public class AddExp_tree extends Base_tree{
	public static int varinum;
	public static ArrayList<String> cal = new ArrayList<String>();
	 public String type;
	 public Base_tree LBase = null;
	 public Base_tree RBase = null;
	 
	 public AddExp_tree(String type,Base_tree l ,Base_tree r) {
		super();
		this.type = type;
		this.LBase = l;
		this.RBase = r;
	}
	public String traverse_tree() {
		String lString = LBase.traverse_tree();
		String rString = RBase.traverse_tree();
		if(type.equals("+"))  cal.add("%x"+varinum+" = add i32 "+lString+", "+rString);
		else cal.add("%x"+varinum+" = sub i32 "+lString+", "+rString);
		return ("%x"+(AddExp_tree.varinum));
	}
}
