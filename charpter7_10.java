package test;
import java.util.*;


public class charpter7_10 {

	public static void main(String[] args) {
		final int N = 10;
		Scanner input = new Scanner(System.in);
		System.out.println("������10λ����: ");
		int[] array = new int [N];
		for(int i=0; i<N; i++) {
			array[i] = input.nextInt();
		}
		System.out.print("��10��������С�����±�Ϊ�� ");
		System.out.println(indexOfSmal1estEIement( array ));
	}
	
	public static int indexOfSmal1estEIement(int[] array) {
		final int N = 10;
		int min= 0;
		for (int j=0; j<N; j++) {
			if(array[min] > array[j])
				min = j;
		}		
		return min;
	}

}
