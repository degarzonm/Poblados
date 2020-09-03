package com.zeldem.pobladosmod.util.structures.house;
import java.util.concurrent.ThreadLocalRandom;


public class Helpers {

	public static int rand(int min, int max) { 
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
}
