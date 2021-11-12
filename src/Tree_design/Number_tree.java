package Tree_design;

public class Number_tree extends Base_tree{
	 public String type;
	 
	 public Number_tree (String typeString) {
		super();
		this.type = typeString;
	}
	 public String traverse_tree() {
			return type;
		}
}
