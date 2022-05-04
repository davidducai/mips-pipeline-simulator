package model.stage;

import model.component.DataMemory;
import model.component.bufferregister.ExecuteMemoryRegister;
import model.component.bufferregister.MemoryWriteBackRegister;

public class Memory {

	private DataMemory memory;
	
	private boolean pcSource;
	
	
	public Memory() {
		memory = new DataMemory();
	}
	
	public void execute(ExecuteMemoryRegister executeMemory, MemoryWriteBackRegister memoryWriteBack, InstructionFetch instructionFetch) {
		if(executeMemory.isMemoryWrite()) {
			memory.writeData(executeMemory.getALUResult(), executeMemory.getWriteData());
		}
		
		pcSource = executeMemory.isZeroSignal() & executeMemory.isBranch();
		
		instructionFetch.setAddress(pcSource ? executeMemory.getBranchAddress() : instructionFetch.getProgramCounter().getValue() + 1);
		
		int aluResult = executeMemory.getALUResult();
		
		memoryWriteBack.setMemoryToRegister(executeMemory.isMemoryToRegister());
		memoryWriteBack.setRegisterWrite(executeMemory.isRegisterWrite());
		if(aluResult >= 0) {
			memoryWriteBack.setReadData(memory.readData(aluResult));
		}
		memoryWriteBack.setAluResult(aluResult);
		memoryWriteBack.setWriteAddress(executeMemory.getWriteAddress());
	}

	public boolean isPcSource() {
		return pcSource;
	}

	public DataMemory getMemory() {
		return memory;
	}
}
