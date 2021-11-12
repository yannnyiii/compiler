package Tree_design;

public class PrimaryExp_tree extends Base_tree{
	 public String type;
	 public Base_tree LBase = null;
	 
	 public PrimaryExp_tree(Base_tree l) {
		super();
		this.LBase = l;
	}
	 public String traverse_tree() {
			String lString = LBase.traverse_tree();
			if(type.equals("+"))  System.out.println("%x"+AddExp_tree.varinum+" = add i32 0 "+", "+lString);
			else System.out.println("%x"+AddExp_tree.varinum+" = sub i32 0 "+", "+lString);
			return ("%x"+(AddExp_tree.varinum++));
		}
}
