package Tree_design;

import java.util.ArrayList;
import Symbol.*;
import Cal.*;
public class Decl_tree extends Base_tree{
	 public String type;
	 public ArrayList<Def> var = new ArrayList<Def>();
	 public boolean isconst = false;
	 public Decl_tree(String type,Boolean isconst,ArrayList<Def> var) {
		super();
		this.var = var;
		this.type = type;
		this.isconst = isconst;
	}
	 public String traverse_tree() {
		 for(int i = 0;i < var.size();i++) {
				Def def = var.get(i);
				if(def.exp!=null) {
					String tempString = def.exp.traverse_tree();
					int j;
					for(j = 0;j<Symbol_table.table.size();j++) {
						if(Symbol_table.table.get(j).name.equals(def.name)&&!isconst) {							
							AddExp_tree.cal.add("store i32 "+tempString+" ,i32"+Symbol_table.table.get(j).number);
							AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = load i32, i32"+Symbol_table.table.get(j).number);
							Symbol_base tem = Symbol_table.table.get(j);
							tem.number = "%x"+(AddExp_tree.varinum-1);
							Symbol_table.table.set(j, tem);
						}
					}if(j>Symbol_table.table.size()) System.exit(8);
					//AddExp_tree.cal.add(temnum+" = "+"alloca i32");
				}
		 }
		 return null;
	}
	 public String traverse_first() {
		 test();
		if(isconst) {
			for(int i = 0;i < var.size();i++) {
				Def def = var.get(i);
				String temnum = def.exp.traverse_tree();
				int temf = 0;
				try {
					 temf = (int)Calculator.calculate(temnum);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Symbol_table.table.add(new Symbol_base(def.name,Integer.toString(temf),true,"int"));
				//AddExp_tree.cal.add("%x"+temnum+" = "+"i32");
			}
//			for(int i = 0;i < var.size();i++) {
//				Def def = var.get(i);
//				def.exp.traverse_tree();
//			}
		}
		else {
			for(int i = 0;i < var.size();i++) {
				Def def = var.get(i);
				String temnum = "%x"+AddExp_tree.varinum++;
				AddExp_tree.cal.add(temnum+" = "+"alloca i32");
				Symbol_table.table.add(new Symbol_base(def.name,"* "+temnum,false,"int"));
			}
//			for(int i = 0;i < var.size();i++) {
//				Def def = var.get(i);
//				def.exp.traverse_tree();
//			}
		}
		return null;
	}
	 
}
