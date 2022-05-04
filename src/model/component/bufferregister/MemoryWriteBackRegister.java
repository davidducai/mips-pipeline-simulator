package model.component.bufferregister;

public class MemoryWriteBackRegister {

	private static MemoryWriteBackRegister register = new MemoryWriteBackRegister();
	
	private boolean memoryToRegister;
	private boolean registerWrite;
	
	private int readData;
	private int aluResult;
	private int writeAddress;
	
	
	public void reset() {
		memoryToRegister = false;
		registerWrite = false;
		
		readData = 0;
		aluResult = 0;
		writeAddress = 0;
	}
	
	private MemoryWriteBackRegister() {}

	public boolean isMemoryToRegister() {
		return memoryToRegister;
	}

	public void setMemoryToRegister(boolean memoryToRegister) {
		this.memoryToRegister = memoryToRegister;
	}

	public boolean isRegisterWrite() {
		return registerWrite;
	}

	public void setRegisterWrite(boolean registerWrite) {
		this.registerWrite = registerWrite;
	}

	public int getReadData() {
		return readData;
	}

	public void setReadData(int readData) {
		this.readData = readData;
	}

	public int getAluResult() {
		return aluResult;
	}

	public void setAluResult(int aluResult) {
		this.aluResult = aluResult;
	}

	public int getWriteAddress() {
		return writeAddress;
	}

	public void setWriteAddress(int writeAddress) {
		this.writeAddress = writeAddress;
	}

	public static MemoryWriteBackRegister getRegister() {
		return register;
	}
}
