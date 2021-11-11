package Tree_design;

public class MulExp_tree extends Base_tree{
	 public String type;
	 public Base_tree LBase = null;
	 public Base_tree RBase = null;
	 public MulExp_tree(String type,Base_tree LBase ,Base_tree RBase) {
		super();
		this.type = type;
		this.LBase = LBase;
		this.RBase = RBase;
	}
	 public void traverse_tree() {
		LBase.traverse_tree();
		RBase.traverse_tree();
		System.out.println(type);
	}
}
