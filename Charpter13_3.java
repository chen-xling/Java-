package test;
import java.util.*;
public class Ch13_3 {
	public static void main(String[] args) {
		ArrayList<Number> list1 = new ArrayList<>();
		list1.add( 2 );
		list1.add(3);
		list1.add(99);
		list1.add(89);
		list1.add(7352);
		list1.add(7889);
		sort(list1);
		System.out.println( list1 );
	}
	public static void  sort (ArrayList<Number> list) {
		int len = list.size();
		int j;
		int max;
		Number temp;
		// 选择排序法
		for (int i = 0; i< len-1; i++) {
			max = i;
			for( j = i+1 ; j<len; j++ ) {
				if( list.get(max).doubleValue() <list.get(j).doubleValue()){
					temp = list.get(max);
					list.set(max, list.get(j));
					list.set(j, temp);
				}
			}				
		}
	}
	
}


