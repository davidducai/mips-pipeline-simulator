package model.component.bufferregister;

import model.instruction.Instruction;

public class FetchDecodeRegister {

	private static FetchDecodeRegister register = new FetchDecodeRegister();
	
	private Instruction instruction;
	private int nextInstruction;
	
	
	public void reset() {
		instruction = new Instruction("00000000000000000000000000000000");
		nextInstruction = 0;
	}
	
	private FetchDecodeRegister() {
		instruction = new Instruction("00000000000000000000000000000000");
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	public int getNextInstruction() {
		return nextInstruction;
	}

	public void setNextInstruction(int nextInstruction) {
		this.nextInstruction = nextInstruction;
	}

	public static FetchDecodeRegister getRegister() {
		return register;
	}
}
