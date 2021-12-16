package Tree_design;

public class Def {
	public String name;
	public Base_tree exp;
	public int[] dim;
	public Base_tree[] initial;
	boolean isshuzu = false;
	public Def(String name,Base_tree exp) {
		this.exp = exp;
		this.name = name;
	}
	public Def(String name,int[] dim,Base_tree[] initial,boolean isshuzu) {
		this.dim = dim;
		this.initial = initial;
		this.isshuzu  = isshuzu;
		this.name = name;
	}
}
