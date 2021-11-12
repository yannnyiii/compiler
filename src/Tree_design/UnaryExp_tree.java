package Tree_design;

public class UnaryExp_tree extends Base_tree{
	 public String type;
	 public Base_tree LBase = null;
	 
	 public UnaryExp_tree(String type,Base_tree l) {
		super();
		this.type = type;
		this.LBase = l;
	}
	 public String traverse_tree() {
			String lString = LBase.traverse_tree();
			if(type.equals("+"))  AddExp_tree.cal.add("%x"+AddExp_tree.varinum+" = add i32 0 "+", "+lString);
			else AddExp_tree.cal.add("%x"+AddExp_tree.varinum+" = sub i32 0 "+", "+lString);
			return ("%x"+(AddExp_tree.varinum++));
		}
}
