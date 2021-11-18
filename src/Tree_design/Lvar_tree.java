package Tree_design;

import Symbol.Symbol_table;

public class Lvar_tree extends Base_tree{
	 public String varname;
	 
	 public Lvar_tree(String varname) {
		super();
		this.varname = varname;
	}
	 public String traverse_tree() {
		 for(int j = 0;j<Symbol_table.table.size();j++) {
				if(Symbol_table.table.get(j).name.equals(varname)) {							
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
