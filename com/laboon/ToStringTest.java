package com.laboon;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.Test;

public class ToStringTest {
	
	// This test creates two identical worlds and makes sure that their toString() is identical to one another
	// This is important to know that copies can be made
	
	@Test
	public void SameWorldTest() {
		World w1=new World(100, 1, 30);
		World w2=new World(100, 1, 30);
		
		assertEquals(w1.toString(),w2.toString());
	}
	
	// This test creates two identical worlds and makes sure that their toString() is identical to one another after iterate() same times
	@Test
	public void SameWorldIterateTest() {
		World w1=new World(100, 1, 30);
		World w2=new World(100, 1, 30);
		w1=w1.iterate();
		w2=w2.iterate();
			
		assertEquals(w1.toString(),w2.toString());
	}
	
	// This test creates two worlds with different parameters to makes sure that their toString() is different
	
	@Test
	public void DifferentWorldTest() {
		World w1=new World(100, 1, 30);
		World w2=new World(100, 2, 40);
		
		assertNotEquals(w1.toString(),w2.toString());
	}
	
	//making two same worlds with the second constructor
	
	@Test
	public void SameWorldTest2() {
		Random m =new Random(1);
		Cell[][] c= new Cell[10][10];
		for (int j = 0; j < 10; j++ ) {
			for (int k = 0; k < 10; k++) {
				c[j][k] = new Cell(State.DEAD, j, k);
			}
		}
		World w1=new World(c,m);
		World w2=new World(c,m);
		
		assertEquals(w1.toString(),w2.toString());
	}
	
	//making two different worlds with the second constructor
	
	@Test
	public void DifferentWorldTest2() {
		Random m =new Random(1);
		Cell[][] c1= new Cell[10][10];
		for (int j = 0; j < 10; j++ ) {
			for (int k = 0; k < 10; k++) {
				c1[j][k] = new Cell(State.DEAD, j, k);
			}
		}
		Cell[][] c2= new Cell[10][10];
		for (int j = 0; j < 10; j++ ) {
			for (int k = 0; k < 10; k++) {
				c2[j][k] = new Cell(State.ALIVE, j, k);
			}
		}
		World w1=new World(c1,m);
		World w2=new World(c2,m);
		
		assertNotEquals(w1.toString(),w2.toString());
	}
}
