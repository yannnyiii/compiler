package Tree_design;

public class AddExp_tree extends Base_tree{
	 public String type;
	 public Base_tree LBase = null;
	 public Base_tree RBase = null;
	 
	 public AddExp_tree(String type,Base_tree l ,Base_tree r) {
		super();
		this.type = type;
		this.LBase = l;
		this.RBase = r;
	}
	public void traverse_tree() {
		LBase.traverse_tree();
		RBase.traverse_tree();
		System.out.println(type);
	}
}
