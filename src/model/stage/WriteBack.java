package model.stage;

import model.component.bufferregister.MemoryWriteBackRegister;

public class WriteBack {

	 private int writeData;
	 
	 public void execute(MemoryWriteBackRegister memoryWriteBack, InstructionDecode instructionDecode) {
		 if(memoryWriteBack.isMemoryToRegister()) {
			 writeData = memoryWriteBack.getReadData();
		 } else {
			 writeData = memoryWriteBack.getAluResult();
		 }
		 
		 if(memoryWriteBack.isRegisterWrite()) {
			 instructionDecode.getRegisterFile().writeData(memoryWriteBack.getWriteAddress(), writeData);
		 }
	 }

	public int getWriteData() {
		return writeData;
	}
}
