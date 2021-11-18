package Tree_design;

import Symbol.Symbol_base;
import Symbol.Symbol_table;

public class Lvar_tree extends Base_tree{
	 public String varname;
	 public boolean isL;	 
	 public Lvar_tree(String varname,boolean isL) {
		super();
		this.varname = varname;
		this.isL = isL;
	}
	 public String traverse_tree() {
		 for(int j = 0;j<Symbol_table.table.size();j++) {
				if(Symbol_table.table.get(j).name.equals(varname)) {
					if(!isL&&!Symbol_table.table.get(j).isconst) {
					AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = load i32, i32* "+Symbol_table.table.get(j).var_pointer);
					Symbol_base tem = Symbol_table.table.get(j);
					tem.number = "%x"+(AddExp_tree.varinum-1);
					Symbol_table.table.set(j, tem);}
					return Symbol_table.table.get(j).number;
				}
			}
		 System.exit(9);
		 return null;
	}
	 public String traverse_first() {
			return null;
		}
}
