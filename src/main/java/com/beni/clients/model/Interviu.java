package com.beni.clients.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Interviu {
	
	
	// 1,5,6,4,8,10,5
	// 1,4,5,5,6,8,10

   public boolean findDuplicate() {
	   List<Integer>numbers = new ArrayList<Integer>();
	   numbers.add(1);
	   numbers.add(5);
	   numbers.add(6);
	   numbers.add(4);
	   numbers.add(8);
	   numbers.add(10);
	   numbers.add(5);
	   Collections.sort(numbers); 
	   for(int i = 0; i < numbers.size() -1; i++) {
		   if(numbers.get(i) == numbers.get(i +1)) {
			   return true;
		   }
	   }
	   return false;
   }
   
   public boolean findDuplicate1() {
	   List<Integer>numbers = new ArrayList<Integer>();
	   numbers.add(1);
	   numbers.add(5);
	   numbers.add(6);
	   numbers.add(4);
	   numbers.add(8);
	   numbers.add(10);
	   numbers.add(5);
	   Set<Integer>numbers1 = new HashSet<Integer>();
	   for(Integer num : numbers) {
		   if(numbers1.add(num) == false) {
			   return true;
		   }
	   }
	   return false;
   }
}
