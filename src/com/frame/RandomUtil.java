package com.frame;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;

public class RandomUtil {

	public static Hashtable<Integer,Integer> randSequence(int total){
		Hashtable<Integer,Integer> sequence = new Hashtable<>();
		Random random = new Random();
		HashSet <Integer> set=new HashSet<Integer>();
		int k=0;
		while (set!=null&&set.size()<=total) {
			int data = random.nextInt(total);
			//System.out.println(set + "-add-"+data);

			if (!set.contains(data)) {
				sequence.put(k, data);
				k++;
			}else {

			}
			set.add(data);

			if (set.size()==total) {
				break;
			}
		}
		return sequence;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
