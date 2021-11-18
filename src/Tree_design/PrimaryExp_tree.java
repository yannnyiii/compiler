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
	public String traverse_first() {
			return null;
	}
	public String traverse_cal() {
		int l =Integer.parseInt(LBase.traverse_cal());
		if(type.equals("+"))  return ""+l;
		else return ""+(-l);
	}
}
