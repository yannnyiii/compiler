package Symbol;

public class Symbol_base {
	public String name;
	public String number;
	public boolean isconst;
	public String type;
	public String var_pointer;
	public int div;
	public Symbol_base(String name,String number,boolean isconst,String type,String var_pointer,int div){
		this.name = name;
		this.number = number;
		this.isconst = isconst;
		this.type = type;
		this.var_pointer = var_pointer;
		this.div = div;
	}
}
