package Tree_design;

import java.util.ArrayList;
import Symbol.*;
import Cal.*;
public class Decl_tree extends Base_tree{
	 public static int shuzunum = 0;
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
					if(!def.isshuzu) {
						String temf = def.exp.traverse_cal();
						Symbol_table.table.add(new Symbol_base(def.name,temf,true,"int",null,Symbol_table.nowdiv));
					}
					else {
						int maxlength = def.initial.length;
						AddExp_tree.cal.add("@a"+shuzunum+" = alloca ["+maxlength+" x i32]");
						Symbol_table.table.add(new Symbol_base(def.name,"@a"+shuzunum,true,"int",null,Symbol_table.nowdiv,def.dim,def.initial.length));	
						AddExp_tree.cal.add("call void @memset(i32* "+"@a"+shuzunum+", i32 0, i32 "+def.initial.length+")");
						//AddExp_tree.cal.add("%x"+AddExp_tree.varinum+++" = getelementptr i32, i32* %4, i32 3");
						for(int k =0;k<def.initial.length;k++) {
							if(!(def.initial[k].getClass().toString().equals("class Tree_design.Number_tree")&&((Number_tree)def.initial[k]).type.equals("0"))) {
								int tem  = AddExp_tree.varinum++;
								AddExp_tree.cal.add("%x"+tem+" = getelementptr i32, i32* "+"@a"+shuzunum+", i32 "+k);
								AddExp_tree.cal.add("store i32 "+def.initial[k].traverse_tree()+", i32* "+"%x"+tem);
							}	
						}
						shuzunum++;
//						String temString = "@a"+shuzunum+" = dso_local constant ["+maxlength+" x i32] [";
//						
//						for(int k=0;k<maxlength-1;k++) {
//							String calString = def.initial[k].traverse_cal();
//							temString+=("i32 "+calString+",");
//						}
//						temString+=("i32 "+def.initial[maxlength-1].traverse_tree());
//						temString+="]";
//						AddExp_tree.cal.add(temString);	
					}
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
					if(!def.isshuzu) {		
						String temnum = "%x"+AddExp_tree.varinum++;
						AddExp_tree.cal.add(temnum+" = "+"alloca i32");
						Symbol_table.table.add(new Symbol_base(def.name,temnum,false,"int",temnum,Symbol_table.nowdiv));
					}
					else {
						int maxlength = def.initial.length;
						AddExp_tree.cal.add("@a"+shuzunum+" = alloca ["+maxlength+" x i32]");
						Symbol_table.table.add(new Symbol_base(def.name,"@a"+shuzunum,false,"int",null,Symbol_table.nowdiv,def.dim,def.initial.length));	
						AddExp_tree.cal.add("call void @memset(i32* "+"@a"+shuzunum+", i32 0, i32 "+def.initial.length+")");
						//AddExp_tree.cal.add("%x"+AddExp_tree.varinum+++" = getelementptr i32, i32* %4, i32 3");
						for(int k =0;k<def.initial.length;k++) {
							if(!(def.initial[k].getClass().toString().equals("class Tree_design.Number_tree")&&((Number_tree)def.initial[k]).type.equals("0"))) {
								int tem  = AddExp_tree.varinum++;
								AddExp_tree.cal.add("%x"+tem+" = getelementptr i32, i32* "+"@a"+shuzunum+", i32 "+k);
								AddExp_tree.cal.add("store i32 "+def.initial[k].traverse_tree()+", i32* "+"%x"+tem);
							}	
						}
						shuzunum++;
						//System.out.println(temString);
					}
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
							break;
						}
					}if(j<0) {System.out.println("decltree"+def.exp.traverse_cal());System.exit(8);}
				}
		 	}
		 }
		 	return null;
	}
	 public String traverse_global() {
		 int temdiv = Symbol_table.nowdiv;
		 if(isconst) {
				for(int i = 0;i < var.size();i++) {
					Def def = var.get(i);
					for(int j = Symbol_table.table.size()-1;j>=0&&Symbol_table.table.get(j).div==temdiv;j--) {
						if(def.name.equals(Symbol_table.table.get(j).name)) {
							System.out.println("samename");System.exit(10);
						}
					}
					if(!def.isshuzu) {
						String temf = def.exp.traverse_cal();
						Symbol_table.table.add(new Symbol_base(def.name,temf,true,"int",null,Symbol_table.nowdiv));
					}
					else {
						int maxlength = def.initial.length;
						Symbol_table.table.add(new Symbol_base(def.name,"@a"+shuzunum,true,"int",null,Symbol_table.nowdiv,def.dim,def.initial.length));
						String temString = "@a"+shuzunum+" = dso_local constant ["+maxlength+" x i32] [";
						shuzunum++;
						for(int k=0;k<maxlength-1;k++) {
							String calString = def.initial[k].traverse_cal();
							temString+=("i32 "+calString+",");
						}
						temString+=("i32 "+def.initial[maxlength-1].traverse_tree());
						temString+="]";
						AddExp_tree.cal.add(temString);	
						//System.out.println(temString);
					}
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
					if(!def.isshuzu) {		
						String temnum = "%x"+AddExp_tree.varinum++;
						AddExp_tree.cal.add(temnum+" = "+"alloca i32");
						Symbol_table.table.add(new Symbol_base(def.name,temnum,false,"int",temnum,Symbol_table.nowdiv));
					}
					else {
						int maxlength = def.initial.length;
						Symbol_table.table.add(new Symbol_base(def.name,"@a"+shuzunum,false,"int",null,Symbol_table.nowdiv,def.dim,def.initial.length));
						String temString = "@a"+shuzunum+" = dso_local global ["+maxlength+" x i32] [";
						shuzunum++;
						for(int k=0;k<maxlength-1;k++) {
							//String calString = def.initial[k].traverse_cal();
							temString+=("i32 "+def.initial[k].traverse_tree()+",");
						}
						temString+=("i32 "+def.initial[maxlength-1].traverse_tree());
						temString+="]";
						AddExp_tree.cal.add(temString);	
						//System.out.println(temString);
					}
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
							break;
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
