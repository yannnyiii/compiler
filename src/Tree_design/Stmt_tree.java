package Tree_design;

import Symbol.Symbol_base;
import Symbol.Symbol_table;

public class Stmt_tree extends Base_tree{
	 public static String return_value;
	 public String type;
	 public Base_tree LBase = null;
	 public Base_tree RBase = null;
	 public Base_tree InBase = null;
	 public Stmt_tree(String type,Base_tree LBase ,Base_tree RBase) {
		super();
		this.type = type;
		this.LBase = LBase;
		this.RBase = RBase;
	}
	 public Stmt_tree(String type,Base_tree LBase ,Base_tree RBase,Base_tree inBase) {
		super();
		this.type = type;
		this.LBase = LBase;
		this.RBase = RBase;
		this.InBase = inBase;
	}
	 public String traverse_tree() {
		 test();
		 if(type.equals("return")) {
			 return_value = LBase.traverse_tree();
		 }
		 else if(type.equals("assign")){
			 String expString = RBase.traverse_tree();
			 String nameString = LBase.traverse_tree();
			 nameString = ((Lvar_tree)LBase).varname;
			 int j;
			 for(j = Symbol_table.table.size()-1;j>=0;j--) {
				 if(Symbol_table.table.get(j).name.equals(nameString)) {
					 	if(Symbol_table.table.get(j).isconst) {
					 		System.exit(8);
					 	}
					    AddExp_tree.cal.add("store i32 "+expString+" ,i32* "+Symbol_table.table.get(j).var_pointer);
						//AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = load i32, i32"+Symbol_table.table.get(j).number);
						Symbol_base tem = Symbol_table.table.get(j);
						tem.number = "%x"+(AddExp_tree.varinum-1);
						Symbol_table.table.set(j, tem);
						return null;
				 }
			 }
			 if(j>Symbol_table.table.size()) System.exit(8);
		 }
		 else if(type.equals("block")){
			 LBase.traverse_tree();
		 }
		 else if(type.equals("condition")){
			 int num = AddExp_tree.varinum++;
			 AddExp_tree.varinum+=2;
			 if(InBase.getClass().toString().equals("class Tree_design.LOrExp_tree")) {
				 LOrExp_tree tem_tree = (LOrExp_tree) InBase;
				 tem_tree.ifnum = num;
				 tem_tree.nextnum = num+1;
				 InBase = tem_tree;
			 }
			 InBase.traverse_tree();
			 AddExp_tree.cal.add("x"+(num++)+":");
			 LBase.traverse_tree();
			 AddExp_tree.cal.add("br label %x"+(num+1));
			 AddExp_tree.cal.add("x"+(num++)+":");
			 if(RBase!=null) RBase.traverse_tree();
			 AddExp_tree.cal.add("br label %x"+(num));
			 AddExp_tree.cal.add("x"+(num++)+":");
		 }
		 else {
			 LBase.traverse_tree();
		 }
		 return null;
	}
	public String traverse_first() {
			return null;
	}
}
