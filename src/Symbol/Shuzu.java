package Symbol;

import java.util.ArrayList;

import Tree_design.AddExp_tree;
import Tree_design.Add_or_string_tree;
import Tree_design.Base_tree;
import Tree_design.Number_tree;

public class Shuzu {
	public static Base_tree[] deal(ArrayList<Add_or_string_tree> x,int dim[]){
		int length = dim.length,biglength = 1;
		//System.out.println(length);
		for(int i = 0;i<length;i++) {
			biglength*=dim[i];
		}
		//System.out.println("big"+biglength);
		Base_tree[] real = new Base_tree[biglength];
		for(int i = 0;i<biglength;i++) {
			real[i] = new Number_tree("0");
		}
		if(x==null) {
//			for(int i=0;i<biglength;i++) {
//				System.out.println(((Number_tree)real[i]).type+",");
//			}
			return real;
		}
		int[] move = new int[length];
		int level = -1;
		int now = 0;
		for(int i=0;i<x.size();i++) {
			if(!x.get(i).isexp&&x.get(i).nextString.equals("{")) {
			level++;
			}
			else if(!x.get(i).isexp&&x.get(i).nextString.equals("}")) {
				if(level>=0) move[level] = 0;
				else {
					break;
				}
				if(level-1>=0) move[level-1]++;
//				for(int j = 0;j<length;j++) {
//					System.out.println(move[j]+" "+level);
//				}
				int nownow = move[0];
				for(int j = 0;j<length-1;j++) {
					nownow=nownow*dim[j+1];
					nownow+=move[j+1];
				}
				nownow+=move[length-1];
				now = nownow;
				//System.out.println(now);
				level--;
			}
			else if(x.get(i).isexp) {
				//System.out.println(real.getClass());
				real[now] = x.get(i).nexTree;
				now++;
			}
		}
//		for(int i=0;i<biglength;i++) {
//			System.out.println(((Number_tree)real[i]).type+",");
//		}
		//System.out.println();
		return real;
	}
}
