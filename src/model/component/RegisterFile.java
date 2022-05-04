package model.component;

import java.util.ArrayList;
import java.util.Collections;

public class RegisterFile {

	private ArrayList<Integer> registers;
	
	public RegisterFile() {
		registers = new ArrayList<Integer>(Collections.nCopies(32, 0));
	}

	public int readData(int address) {
		return registers.get(address);
	}
	
	public void writeData(int address, int value) {
		registers.set(address, value);
	}
	
	public ArrayList<Integer> getRegisters() {
		return registers;
	}
}
