package model.component;

import java.util.ArrayList;

import model.instruction.Instruction;

public class InstructionMemory {

	private ArrayList<Instruction> instructions;
	
	
	public InstructionMemory() {
		instructions = new ArrayList<Instruction>(32);
	}

	public Instruction getInstruction(int address) {
		return instructions.get(address);
	}
	
	public ArrayList<Instruction> getInstructions() {
		return instructions;
	}
}
