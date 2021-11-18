package Tree_design;

import java.util.ArrayList;

import Symbol.Symbol_table;

public class Funcdeal_tree extends Base_tree{
	 public String name;
	 public String contain_str;
	 public Base_tree contain_Tree;
	 public Funcdeal_tree (String name,String contain_str,Base_tree contain_Tree) {
		super();
		this.name = name;
		this.contain_str = contain_str;
		this.contain_Tree = contain_Tree;
	}
	public String traverse_tree() {
		 if(name.equals("putint")) {
			String temString = contain_Tree.traverse_tree();
			AddExp_tree.cal.add(temString);
			return temString;
		 }
		 return null;
	}
	public String traverse_first() {
		    System.out.println("in");
			return null;
	}
}

