package test;
import java.util.Scanner;
import java.util.Random;

public class Charter7_7 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		System.out.println("以下生成100个随机数字：");
		int[] a = new int [100];
		for (int i=0; i<100; i++) {
			a[i] = random.nextInt(10);
		}
		
		for (int j =0; j<100; j++) {
			System.out.print(a[j]);
			System.out.print(" ");
			if ((j+1) % 10 == 0) 
				System.out.println();
		}
		
		int[] b = new int [10];
		for (int k =0; k<10; k++) {
			int count = 0;
			for(int h=0; h<100; h++) {
				if (k == a[h])
					count++;
			}
			b[k] = count;	
		}
		
		for (int u = 0; u< 10; u++) {
			System.out.print(u);
			System.out.print("有");
			System.out.print(b[u]);
			System.out.print("个。");
			System.out.println();
		}
	}
}
