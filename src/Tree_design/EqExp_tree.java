package Tree_design;

public class EqExp_tree extends Base_tree{
	 public String type;
	 public Base_tree LBase = null;
	 public Base_tree RBase = null;
	 public int originnum = 0;
	 public EqExp_tree(String type,Base_tree LBase ,Base_tree RBase) {
		super();
		this.type = type;
		this.LBase = LBase;
		this.RBase = RBase;
	}
	 public String traverse_tree() {
			String lString = LBase.traverse_tree();
			String rString = null;
			if(RBase!=null) {rString = RBase.traverse_tree();}
			else return lString;
			if(type.equals("=="))  AddExp_tree.cal.add("%x"+AddExp_tree.varinum+" = icmp eq i32 "+lString+", "+rString);
			else AddExp_tree.cal.add("%x"+AddExp_tree.varinum+" = icmp ne i32 "+lString+", "+rString);
			return (" %x"+(AddExp_tree.varinum++));
	}
	public String traverse_first() {
			return null;
	}
}
