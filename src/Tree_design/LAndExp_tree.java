package Tree_design;

public class LAndExp_tree extends Base_tree{
	 public String type;
	 public Base_tree LBase = null;
	 public Base_tree RBase = null;
	 public String ifnum = null;
	 public int originnum = 0;
	 public int nextnum = 0;
	 public static int ynum = 0;
	 public LAndExp_tree(String type,Base_tree LBase ,Base_tree RBase) {
		super();
		this.type = type;
		this.LBase = LBase;
		this.RBase = RBase;
	}
	 public String traverse_tree() {
		 	int temnextnum = 0;
		 	if(RBase!=null) {
		 		temnextnum = AddExp_tree.varinum++;
		 	}
		 	if(LBase.getClass().toString().equals("class Tree_design.LAndExp_tree")) {
		 		LAndExp_tree tem_tree = (LAndExp_tree) LBase;
		 		tem_tree.nextnum = temnextnum;
		 		tem_tree.originnum = originnum;
		 		LBase = tem_tree;
		 	}
			String lString = LBase.traverse_tree();	
			if(temnextnum!=0) {
				if(Base_tree.ifexp(LBase)) {
					AddExp_tree.cal.add("%y"+ynum++ +" = icmp ne i32 "+lString+", 0");	
					lString = "%y"+LAndExp_tree.ynum++;
				}
				AddExp_tree.cal.add("br i1 "+lString+", label "+"%x"+temnextnum+", label "+"%x"+originnum);	
			}
			if(RBase!=null) {
				EqExp_tree tem_tree2 = (EqExp_tree) RBase;
				tem_tree2.originnum = originnum;
				RBase = tem_tree2;
				AddExp_tree.cal.add("x"+temnextnum+":");
				String rString = RBase.traverse_tree();
			}
			// AddExp_tree.cal.add("%x"+AddExp_tree.varinum+" = srem i32 "+lString+", "+rString);
			//return (" %x"+(AddExp_tree.varinum++));
			return (" %x"+(AddExp_tree.varinum-1));
	}
	public String traverse_first() {
			return null;
	}
}
