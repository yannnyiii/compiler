package Tree_design;

public class UnaryExp_tree extends Base_tree{
	 public String type;
	 public Base_tree LBase = null;
	 
	 public UnaryExp_tree(String type,Base_tree l) {
		super();
		this.type = type;
		this.LBase = l;
	}
	 public void traverse_tree() {
			LBase.traverse_tree();
			System.out.println(type);
		}
}
