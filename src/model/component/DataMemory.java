package model.component;

import java.util.ArrayList;
import java.util.Collections;

public class DataMemory {

	private ArrayList<Integer> memory = new ArrayList<Integer>(32);
	
	public DataMemory() {
		 memory = new ArrayList<Integer>(Collections.nCopies(64, 0));
	}

	public int readData(int address) {
		return memory.get(address);
	}
	
	public void writeData(int address, int value) {
		memory.set(address, value);
	}
	
	public ArrayList<Integer> getMemory() {
		return memory;
	}
}
