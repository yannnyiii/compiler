package Tree_design;

import Symbol.Symbol_table;

public class Number_tree extends Base_tree{
	 public String type;
	 
	 public Number_tree (String typeString) {
		super();
		this.type = typeString;
	}
	 public String traverse_tree() {
			return type;
		}
	 public String traverse_first() {
			return null;
		}
		public String traverse_cal() {
			return type;
		}	 	 
}
