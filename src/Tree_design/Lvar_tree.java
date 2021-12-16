package Tree_design;

import java.util.ArrayList;

import Symbol.Symbol_base;
import Symbol.Symbol_table;

public class Lvar_tree extends Base_tree{
	 public String varname;
	 public boolean isL;
	 public ArrayList<Base_tree>  expBase_tree;
	 public boolean isshuzu = false;
	 public Lvar_tree(String varname,boolean isL) {
		super();
		this.varname = varname;
		this.isL = isL;
	}
	 public String traverse_tree() {
		 for(int j = Symbol_table.table.size()-1;j>=0;j--) {
				if(Symbol_table.table.get(j).name.equals(varname)) {
					if(!isL) {
						if(!isshuzu) {
							if(!Symbol_table.table.get(j).isconst) {
								AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = load i32, i32* "+Symbol_table.table.get(j).var_pointer);
								Symbol_base tem = Symbol_table.table.get(j);
								tem.number = "%x"+(AddExp_tree.varinum-1);
								Symbol_table.table.set(j, tem);
							}
							return Symbol_table.table.get(j).number;
						}
						else {
							int dim[] = Symbol_table.table.get(j).dim;
							//System.out.println(varname);
							String initString = expBase_tree.get(0).traverse_tree();
							for(int k=0;k<expBase_tree.size()-1;k++) {
								String temString = expBase_tree.get(k+1).traverse_tree();
								AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = mul i32 "+initString+", "+dim[k+1]);
								AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = add i32 "+""+"%x"+(AddExp_tree.varinum-2)+','+temString);
								initString = "%x"+(AddExp_tree.varinum-1);
;							}
							AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = getelementptr ["+Symbol_table.table.get(j).length+" x i32], ["+Symbol_table.table.get(j).length+" x i32]* "+Symbol_table.table.get(j).number+",i32 0, i32 "+initString);
							Symbol_base temBase= Symbol_table.table.get(j); 
							temBase.var_pointer = "%x"+(AddExp_tree.varinum-1);
							Symbol_table.table.set(j,temBase); 
							AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = load i32, i32* "+Symbol_table.table.get(j).var_pointer);
							return "%x"+(AddExp_tree.varinum-1);
						}
					}
					else {
						if(!isshuzu) return Symbol_table.table.get(j).number;
						else {
							int dim[] = Symbol_table.table.get(j).dim;
							String initString = expBase_tree.get(0).traverse_tree();
							for(int k=0;k<expBase_tree.size()-1;k++) {
								String temString = expBase_tree.get(k).traverse_tree();
								AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = mul i32 "+initString+", "+dim[k+1]);
								AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = add i32 "+""+"%x"+(AddExp_tree.varinum-2)+','+temString);
								initString = "%x"+(AddExp_tree.varinum-1);
;							}
							AddExp_tree.cal.add("%x"+(AddExp_tree.varinum++)+" = getelementptr ["+Symbol_table.table.get(j).length+" x i32], ["+Symbol_table.table.get(j).length+" x i32]* "+Symbol_table.table.get(j).number+",i32 0, i32 "+initString);
							Symbol_base temBase= Symbol_table.table.get(j); 
							temBase.var_pointer = "%x"+(AddExp_tree.varinum-1);
							Symbol_table.table.set(j,temBase); 
							return "%x"+(AddExp_tree.varinum-1);
						}
					}
				}
		}
		 Symbol_table.print();
		 System.out.println("\n"+varname);
		 System.exit(9);
		 return null;
	}
	 public String traverse_first() {
			return null;
	}
	public String traverse_cal() {
		for(int j = 0;j<Symbol_table.table.size();j++) {
			if(Symbol_table.table.get(j).name.equals(varname)) {
				if(!isL&&!Symbol_table.table.get(j).isconst) {
				System.exit(8);
				}
				return Symbol_table.table.get(j).number;
			}
		}
		System.exit(8);
		return null;
	}	 
}
