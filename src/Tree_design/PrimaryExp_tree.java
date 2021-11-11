package Tree_design;

public class PrimaryExp_tree extends Base_tree{
	 public String type;
	 public Base_tree LBase = null;
	 
	 public PrimaryExp_tree(Base_tree l) {
		super();
		this.LBase = l;
	}
	 public void traverse_tree() {
			LBase.traverse_tree();
		}
}
