package com.guarented.ecommerce.commonUtils;

import java.util.ArrayList;

public class Sort {
	
	public static boolean isSorted(ArrayList<Integer> list , boolean isAsc){
		
		
		for(int i=0;i<list.size()-1;i++){
			
			if (isAsc && (list.get(i) > list.get(i+1))) {
				
				Logging.log("Checking Column Data is Ascending Order or not");
				return false;
			}
			
			if (!isAsc && (list.get(i) < list.get(i+1))){
				
				Logging.log("Checking Column Data is Descending Order or not");
				return false;
			}
			
		}
		
		return true;
		
	}
	
	public static void main(String[] args) {
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		list.add(12);
		list.add(55);
		list.add(20);
		list.add(25);
		
		System.out.println(isSorted(list, true));
		
		
	}

}
