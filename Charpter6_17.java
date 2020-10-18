package test;
import java.util.Scanner;
import java.util.Random;

public class Charpter6_17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a number:");
		int n = input.nextInt();
		printMatrix(n);
	}
	
	public static void printMatrix(int n) {	
		Random random = new Random();
		int[][] a= new int [n][n];
		for (int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				a[i][j] = random.nextInt(2);
			}
		}	
		//´òÓ¡³öÀ´£»
		for (int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print( a[i][j] );
				System.out.print( ' ' );
			}
			System.out.println();
		}		
	}
}
