package Symbol;

public class Symbol_base {
	public String name;
	public String number;
	public boolean isconst;
	public String type;
	public String var_pointer;
	public int div;
	public int [] dim;
	public int length;
	public Symbol_base(String name,String number,boolean isconst,String type,String var_pointer,int div){
		this.name = name;
		this.number = number;
		this.isconst = isconst;
		this.type = type;
		this.var_pointer = var_pointer;
		this.div = div;
	}
	public Symbol_base(String name,String number,boolean isconst,String type,String var_pointer,int div,int[] dim,int length){
		this.name = name;
		this.number = number;
		this.isconst = isconst;
		this.type = type;
		this.var_pointer = var_pointer;
		this.div = div;
		this.dim =dim;
		this.length = length;
	}
}
