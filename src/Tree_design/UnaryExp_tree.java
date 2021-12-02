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
			else if(type.equals("-"))AddExp_tree.cal.add("%x"+AddExp_tree.varinum+" = sub i32 0 "+", "+lString);
			else{
				AddExp_tree.cal.add("%x"+AddExp_tree.varinum+++" = icmp eq i32 "+lString+" , 0");
				AddExp_tree.cal.add("%x"+AddExp_tree.varinum+" = zext i1 %x"+(AddExp_tree.varinum-1)+" to i32");
			}
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
