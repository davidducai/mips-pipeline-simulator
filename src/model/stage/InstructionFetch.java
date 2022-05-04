package model.stage;

import model.component.InstructionMemory;
import model.component.ProgramCounter;
import model.component.bufferregister.FetchDecodeRegister;

public class InstructionFetch {

	private ProgramCounter programCounter;
	private InstructionMemory instructionMemory;
	
	private int address;
	
	
	public InstructionFetch() {
		this.programCounter = new ProgramCounter();
		this.instructionMemory = new InstructionMemory();
	}
	
	public void execute(FetchDecodeRegister fetchDecode, InstructionDecode instructionDecode) {
		if(instructionDecode.getMainControl().isJump()) {
			programCounter.setValue(fetchDecode.getInstruction().getAddress());
			
		} else {
			programCounter.setValue(address);
		}
		
		if(address < instructionMemory.getInstructions().size()) {
			fetchDecode.setNextInstruction(programCounter.getValue() + 1);
			fetchDecode.setInstruction(instructionMemory.getInstruction(programCounter.getValue()));
		}
		
		// fetchDecode.setNextInstruction(programCounter.getValue() + 1);
		// fetchDecode.setInstruction(instructionMemory.getInstruction(programCounter.getValue()));
	}
	
	public ProgramCounter getProgramCounter() {
		return programCounter;
	}

	public void setProgramCounter(ProgramCounter programCounter) {
		this.programCounter = programCounter;
	}

	public InstructionMemory getInstructionMemory() {
		return instructionMemory;
	}

	public void setInstructionMemory(InstructionMemory instructionMemory) {
		this.instructionMemory = instructionMemory;
	}

	public void setAddress(int address) {
		this.address = address;
	}
}
