package test;
import java.util.Scanner;
import java.math.*;

public class Charpter6_4 {
		public static void main(String args[]) {
			Scanner input = new Scanner(System.in);
			System.out.print("please enter a number: ");
			int number = input.nextInt();
			reverse(number);
		}	
		
		public static void reverse(int number ) {
			final int len = LengthNum(number);
			int[] a = new int [len+1];
			a[0] = 0;
			int y = 0;
			for(int i=1; i<= len; i++) {
				number = number- a[i-1] * (int)Math.pow(10, i-2);
	
				y = number % (int)Math.pow(10, i) ;
				
				a[i] = y / (int)Math.pow(10, i-1);
			}
			System.out.print("the reverse is: ");
			int x=0;
			for( int j=1; j <=len; j++) {
				x= x + a[j] * (int)Math.pow(10, len - j);
			}
			
			System.out.println(x);
		}
		
		public static int LengthNum(int number) {
			int n = 1;
			while (number / 10 != 0) {
				number /= 10;
				n++;
			}
			return n;		
		}
		
		
}
