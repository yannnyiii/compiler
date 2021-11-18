package Symbol;

public class Symbol_base {
	public String name;
	public String number;
	public boolean isconst;
	public String type;
	public Symbol_base(String name,String number,boolean isconst,String type){
		this.name = name;
		this.number = number;
		this.isconst = isconst;
		this.type = type;
	}
}
