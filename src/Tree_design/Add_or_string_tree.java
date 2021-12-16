package Tree_design;

public class Add_or_string_tree {
	public Base_tree nexTree;
	public String nextString;
	public boolean isexp;
	public Add_or_string_tree(Base_tree nexTree,String nextString,boolean isexp) {
		this.nexTree = nexTree ;
		this.nextString = nextString;
		this.isexp = isexp;
	}
}
