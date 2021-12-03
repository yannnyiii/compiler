package Tree_design;

import java.util.ArrayList;
import Symbol.*;

public class Block_tree extends Base_tree{
	 public String type;
	 public ArrayList<Base_tree> item = new ArrayList<Base_tree>();
	 public Block_tree(String type,ArrayList<Base_tree> item) {
		super();
		this.type = type;
		this.item = item;
	}
	 public String traverse_tree() {
		 Symbol_table.nowdiv++;
		 test();
		 for(int i = 0;i < item.size();i++) {
				Base_tree tem = item.get(i);
				tem.traverse_tree();
			}
		for(;(!Symbol_table.table.isEmpty())&&(Symbol_table.table.peek().div==Symbol_table.nowdiv);) {
			Symbol_table.table.pop();
		}
		Symbol_table.nowdiv--;
		return null;
	}	
	 public String traverse_first() {
		 for(int i = 0;i < item.size();i++) {
				Base_tree tem = item.get(i);
				tem.traverse_first();
			}
		 return null;
	}
}
