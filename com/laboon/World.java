package com.laboon;

import java.util.Random;

public class World {

	/**
	 * Size of the world
	 */
	
	int _size;
	
	/**
	 * The world, a 2D array of Cells which can be living or dead.
	 */
	
	Cell[][] _world;
	
	/**
	 * A random number generator.
	 */
	
	Random _rng;
	
	/**
	 * Initial world constructor.
	 * @param size Size of world
	 * @param seed Random number generator seed
	 * @param percent Initial percentage of cells alive
	 */
	
	public World(int size, int seed, int percent) {
		_size = size;
		_rng = new Random(seed);
		_world = generateBoard(size, percent);
	}
	
	/**
	 * Iterated (non-initial) world constructor.
	 * @param cells Cells living in the new world
	 * @param rng Random number generator
	 */
	
	public World(Cell[][] cells, Random rng) {
		_size = cells.length;
		_rng = rng;
		_world = cells;
	}
	
	/**
	 * Generate the initial state of a cell, given the percentage
	 * chance that it is living.
	 * @param percent percentage chance that it is living
	 * @return state of cell, State.ALIVE or State.DEAD
	 */
	
	private State generateInitialState(int percent) {
		int livingChance = _rng.nextInt(100);
		State toReturn = (livingChance < percent) ? State.ALIVE : State.DEAD;
		return toReturn;
	}
	
	/**
	 * The number of living neighbors that a cell has.
	 * @param world the world
	 * @param x x location of cell
	 * @param y y location of cell
	 * @return
	 */
	
	private int getNumNeighbors(Cell[][] world, int x, int y) {
		int size = world.length;
		int leftX = (x - 1) % size;
		int rightX = (x + 1) % size;
		int upY = (y - 1) % size;
		int downY = (y + 1) % size;
		
		if (leftX == -1) { leftX = size - 1; }
		if (rightX == 0) { rightX = 0; }//
		if (upY == -1) { upY = size - 1; }
		if (downY == 0) { downY = 0; }//
		
		int numNeighbors = 0;

		if (world[leftX][upY].isAlive())    { numNeighbors++; }
		if (world[leftX][downY].isAlive())  { numNeighbors++; }
		if (world[leftX][y].isAlive())      { numNeighbors++; }
		if (world[rightX][upY].isAlive())   { numNeighbors++; }
		if (world[rightX][downY].isAlive()) { numNeighbors++; }
		if (world[rightX][y].isAlive())     { numNeighbors++; }
		if (world[x][upY].isAlive())        { numNeighbors++; }
		if (world[x][downY].isAlive())      { numNeighbors++; }

		return numNeighbors;
	}
	
	/**
	 * Go through one iteration of this World and return new World.
	 * @return New world
	 */
	
	public World iterate() {
		final long startTime = System.currentTimeMillis();
		Cell[][] newCells = new Cell[_size][_size];
		for (int j = 0; j < _size; j++ ) {
			for (int k = 0; k < _size; k++) {
				newCells[j][k] = new Cell(_world[j][k].iterate(getNumNeighbors(_world, j, k)), j, k);
			}
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("iterate execution time: " + (endTime - startTime) );//record the time
		return new World(newCells, _rng);
	}

	/**
	 * Convert this World to a string for display.
	 * @return String representation of world
	 */
	
//	public String toString() {
//		final long startTime = System.currentTimeMillis();
//		String toReturn = "  ";
//		for (int j= 0; j < _size; j++) {
//			toReturn += String.valueOf(j % 10);
//		}
//		toReturn += "\n";
//		for (int j = 0; j < _size; j++ ) {
//			toReturn += String.valueOf(j % 10) + " ";
//			for (int k = 0; k < _size; k++) {
//				toReturn += (_world[j][k].getStateRep());
//			}
//			toReturn += "\n";
//		}
//		final long endTime = System.currentTimeMillis();
//		System.out.println("toString execution time: " + (endTime - startTime) );//record the time
//		return toReturn;
//	}
	public String toString() {
		//since  String objects are immutable. Once constructed they cannot be changed anymore.
		//Whenever you "change" the value of a String you create a new object and make that variable reference this new object.
		//so I use StringBuilder to refactor this function
		StringBuilder toReturn=new StringBuilder();
		toReturn.append("  ");
		for (int j= 0; j < _size; j++) {
			toReturn.append(String.valueOf(j % 10));
		}
		
		toReturn.append("\n");
		for (int j = 0; j < _size; j++ ) {
			toReturn.append(String.valueOf(j % 10) + " ");
			for (int k = 0; k < _size; k++) {
				toReturn.append(_world[j][k].getStateRep());
			}
			toReturn.append("\n");
		}
		return toReturn.toString();
	}
	
	/**
	 * Generate initial game board.
	 * @param size Size of board
	 * @param percent Percent alive
	 * @return Initial world
	 */
	
	private Cell[][] generateBoard(int size, int percent) {
		Cell[][] world = new Cell[size][size];
		for (int j = 0; j < size; j++ ) {
			for (int k = 0; k < size; k++) {
				world[j][k] = new Cell(generateInitialState(percent), j, k);
			}
		}
		return world;
	}
	
}
