package Tree_design;
public abstract class Base_tree {
	public abstract String traverse_tree();
	public abstract String traverse_first();
	public static void test2(String s){
		//System.out.println(s);
	}
	public void test(){
	}
	public String traverse_cal() {
		return null;
	};
    public static boolean ifexp(Base_tree tem){
    	//System.out.println(tem.getClass().toString());
    	if(tem.getClass().toString().equals("class Tree_design.LOrExp_tree")) {
    		test2("lor");
    		LOrExp_tree temlLOrExp_tree = (LOrExp_tree) tem;
    		if(temlLOrExp_tree.RBase!=null) {
    			return false;
    		}
    		else {
    			tem = temlLOrExp_tree.LBase;
    		}
    	}
    	if(tem.getClass().toString().equals("class Tree_design.LAndExp_tree")) {
    		test2("land");
    		LAndExp_tree temlLAndExp_tree = (LAndExp_tree) tem;
    		if(temlLAndExp_tree.RBase!=null) {
    			return false;
    		}
    		else {
    			tem = temlLAndExp_tree.LBase;
    		}
    	}
    	if(tem.getClass().toString().equals("class Tree_design.EqExp_tree")) {
    		test2("eq");
    		EqExp_tree temEqExp_tree = (EqExp_tree) tem;
    		if(temEqExp_tree.RBase!=null) {
    			return false;
    		}
    		else {
    			tem = temEqExp_tree.LBase;
    		}
    	}
    	if(tem.getClass().toString().equals("class Tree_design.RelExp_tree")) {
    		test2("re");
    		RelExp_tree temRelExp_tree = (RelExp_tree) tem;
    		if(temRelExp_tree.RBase!=null) {
    			return false;
    		}
    		else {
    			tem = temRelExp_tree.LBase;
    		}
    	}
    	if(tem.getClass().toString().equals("class Tree_design.AddExp_tree")||tem.getClass().toString().equals("class Tree_design.MulExp_tree")||tem.getClass().toString().equals("class Tree_design.UnaryExp_tree")||tem.getClass().toString().equals("class Tree_design.UnaryOp_tree")) {
    		return true;
    	}
    	return false;
    }
}
