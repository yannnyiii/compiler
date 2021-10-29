public class Word {
	private String type;
	private String name;
	private int line;
	public Word(String type,String name,int line){
		this.type = type;
		this.name = name;
		this.line = line;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
}
