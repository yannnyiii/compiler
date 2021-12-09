package Tree_design;

public class LOrExp_tree extends Base_tree{
	 public String type;
	 public Base_tree LBase = null;
	 public Base_tree RBase = null;
	 public int ifnum = 0;
	 public int whilenum = 0;
	 public int whilenextnum = 0;
	 public int nextnum = 0;
	 public LOrExp_tree(String type,Base_tree LBase ,Base_tree RBase) {
		super();
		this.type = type;
		this.LBase = LBase;
		this.RBase = RBase;
	}
	 public String traverse_tree() {
		 	int temnextnum = 0;
		 	if(RBase==null) {
		 		temnextnum = ifnum+1;
		 	}
		 	else {
		 		temnextnum = AddExp_tree.varinum++;
		 	}
		 	if(LBase.getClass().toString().equals("class Tree_design.LOrExp_tree")) {
		 		LOrExp_tree tem_tree = (LOrExp_tree) LBase;
		 		tem_tree.nextnum = temnextnum;
		 		tem_tree.ifnum = ifnum;
		 		LBase = tem_tree;
		 	}
		 	if(LBase.getClass().toString().equals("class Tree_design.LAndExp_tree")) {
		 		LAndExp_tree tem_tree = (LAndExp_tree) LBase;
		 		//tem_tree.nextnum = temnextnum;
		 		tem_tree.originnum = temnextnum;
		 		LBase = tem_tree;
		 	}
			String lString = LBase.traverse_tree();
			if(temnextnum!=0) {
				if(Base_tree.ifexp(LBase)) {
					AddExp_tree.cal.add("%y"+LAndExp_tree.ynum +" = icmp ne i32 "+lString+", 0");	
					lString = "%y"+LAndExp_tree.ynum++;
				}
				AddExp_tree.cal.add("br i1 "+lString+", label "+"%x"+ifnum+", label "+"%x"+temnextnum);	
			}
			if(RBase!=null) {
				LAndExp_tree tem_tree2 = (LAndExp_tree) RBase;
				tem_tree2.originnum = nextnum;
				RBase = tem_tree2;
				AddExp_tree.cal.add("x"+temnextnum+":");
			}
			String rString = null;
			if(RBase!=null) rString = RBase.traverse_tree();
			// AddExp_tree.cal.add("%x"+AddExp_tree.varinum+" = srem i32 "+lString+", "+rString);
			//return (" %x"+(AddExp_tree.varinum++));
			return (" %x"+(AddExp_tree.varinum-1));
	}
	public String traverse_first() {
			return null;
	}
}
