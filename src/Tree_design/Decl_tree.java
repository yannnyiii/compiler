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
		 int temdiv = Symbol_table.nowdiv;
		 if(isconst) {
				for(int i = 0;i < var.size();i++) {
					Def def = var.get(i);
					for(int j = Symbol_table.table.size()-1;j>=0&&Symbol_table.table.get(j).div==temdiv;j--) {
						if(def.name.equals(Symbol_table.table.get(j).name)) {
							System.out.println("samename");System.exit(10);
						}
					}
					String temf = def.exp.traverse_cal();
					Symbol_table.table.add(new Symbol_base(def.name,temf,true,"int",null,Symbol_table.nowdiv));
					//AddExp_tree.cal.add("%x"+temnum+" = "+"i32");
				}
//				for(int i = 0;i < var.size();i++) {
//					Def def = var.get(i);
//					def.exp.traverse_tree();
//				}
			}
			else {
				for(int i = 0;i < var.size();i++) {
					Def def = var.get(i);
					for(int j = Symbol_table.table.size()-1;j>=0&&Symbol_table.table.get(j).div==temdiv;j--) {
						if(def.name.equals(Symbol_table.table.get(j).name)) {
							System.out.println("samename");System.exit(10);
						}
					}
					String temnum = "%x"+AddExp_tree.varinum++;
					AddExp_tree.cal.add(temnum+" = "+"alloca i32");
					Symbol_table.table.add(new Symbol_base(def.name,temnum,false,"int",temnum,Symbol_table.nowdiv));
					//System.out.println(def.name);
				}
//				for(int i = 0;i < var.size();i++) {
//					Def def = var.get(i);
//					def.exp.traverse_tree();
//				}
//			}
		 	for(int i = 0;i < var.size();i++) {
				Def def = var.get(i);
				if(def.exp!=null) {
					String tempString = def.exp.traverse_tree();
					int j;
					for(j = Symbol_table.table.size()-1;j>=0;j--) {
						if(Symbol_table.table.get(j).name.equals(def.name)&&!isconst) {		
							AddExp_tree.cal.add("store i32 "+tempString+" ,i32* "+Symbol_table.table.get(j).var_pointer);
							Symbol_base tem = Symbol_table.table.get(j);
							tem.number = "%x"+(AddExp_tree.varinum-1);
							Symbol_table.table.set(j, tem);
							return null;
						}
					}if(j<0) {System.out.println("decltree"+def.exp.traverse_cal());System.exit(8);}
				}
		 	}
		 }
		 	return null;
	}
//	 public String traverse_first() {
//		 test();
//		if(isconst) {
//			for(int i = 0;i < var.size();i++) {
//				Def def = var.get(i);
//				String temf = def.exp.traverse_cal();
//				Symbol_table.table.add(new Symbol_base(def.name,temf,true,"int",null));
//				//AddExp_tree.cal.add("%x"+temnum+" = "+"i32");
//			}
//		}
//		else {
//			for(int i = 0;i < var.size();i++) {
//				Def def = var.get(i);
//				String temnum = "%x"+AddExp_tree.varinum++;
//				AddExp_tree.cal.add(temnum+" = "+"alloca i32");
//				Symbol_table.table.add(new Symbol_base(def.name,temnum,false,"int",temnum));
//			}
//		}
//		return null;
//	}
	 public String traverse_first() {
		 return  null;
	 } 
}
