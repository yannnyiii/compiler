package Tree_design;

import Symbol.Symbol_base;
import Symbol.Symbol_table;

public class Stmt_tree extends Base_tree{
	 public static String return_value;
	 public String type;
	 public Base_tree LBase = null;
	 public Base_tree RBase = null;
	 public Stmt_tree(String type,Base_tree LBase ,Base_tree RBase) {
		super();
		this.type = type;
		this.LBase = LBase;
		this.RBase = RBase;
	}
	 public String traverse_tree() {
		 test();
		 if(type.equals("return")) {
			 return_value = LBase.traverse_tree();
		 }
		 else if(type.equals("assign")){
			 String expString = RBase.traverse_tree();
			 String nameString = LBase.traverse_tree();
			 int j;
			 for(j = 0;j<Symbol_table.table.size();j++) {
				 if(Symbol_table.table.get(j).number.equals(nameString)&&!Symbol_table.table.get(j).isconst) {
					    AddExp_tree.cal.add("store i32 "+expString+" ,i32* "+Symbol_table.table.get(j).number);
						AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = load i32, i32* "+Symbol_table.table.get(j).number);
						Symbol_base tem = Symbol_table.table.get(j);
						tem.number = "%x"+(AddExp_tree.varinum-1);
						Symbol_table.table.set(j, tem);
				 }
			 }
			 if(j>Symbol_table.table.size()) System.exit(8);
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
