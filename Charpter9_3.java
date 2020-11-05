package test;
import java.util.*;

public class Datec {

	public static void main(String[] args) {
		java.util.Date date = new java.util.Date();
		long base = 10000;
		for (int i=0; i<8; i++) {
			date.setTime((long)(base*Math.pow(10, i)));
			System.out.println(date.toString());
		
		}

	}
}
