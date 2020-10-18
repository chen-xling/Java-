package test;
import java.util.*;


public class charpter8_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter a 4-by-4 matrix row by row:");
		final int N = 4;
		Scanner input = new Scanner(System.in);
		
		double[][] a = new double [N][N];
		
		for (int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				a[i][j] = input.nextDouble();
			}
			//System.out.println();
			
		}
		System.out.print("Sum of the elements in the major diagonal is ");
		System.out.println(sumMajorDiagonal ( a ) );
	}

	public static double sumMajorDiagonal (double[][] m) {
		final int N = 4;
		double sum = 0;
		for (int k =0 ; k<N; k++) {
			sum += m[k][k];
		}
		
		return sum;
	}
}
