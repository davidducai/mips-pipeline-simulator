package model.component.bufferregister;

public class ExecuteMemoryRegister {

	private static ExecuteMemoryRegister register = new ExecuteMemoryRegister();
	
	private boolean memoryToRegister;
	private boolean registerWrite;
	
	private boolean branch;
	private boolean memoryWrite;
	
	private int branchAddress;
	private boolean zeroSignal;
	private int ALUResult;
	private int writeData;
	private int writeAddress;
	
	
	public void reset() {
		memoryToRegister = false;
		registerWrite = false;
		
		branch = false;
		memoryWrite = false;
		
		branchAddress = 0;
		zeroSignal = false;
		ALUResult = 0;
		writeData = 0;
		writeAddress = 0;
	}
	
	public int getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(int branchAddress) {
		this.branchAddress = branchAddress;
	}

	public boolean isZeroSignal() {
		return zeroSignal;
	}

	public void setZeroSignal(boolean zeroSignal) {
		this.zeroSignal = zeroSignal;
	}

	public int getALUResult() {
		return ALUResult;
	}

	public void setALUResult(int aLUResult) {
		ALUResult = aLUResult;
	}

	public int getWriteData() {
		return writeData;
	}

	public void setWriteData(int writeData) {
		this.writeData = writeData;
	}

	public int getWriteAddress() {
		return writeAddress;
	}

	public void setWriteAddress(int writeAddress) {
		this.writeAddress = writeAddress;
	}

	private ExecuteMemoryRegister() {}

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

	public boolean isBranch() {
		return branch;
	}

	public void setBranch(boolean branch) {
		this.branch = branch;
	}

	public boolean isMemoryWrite() {
		return memoryWrite;
	}

	public void setMemoryWrite(boolean memoryWrite) {
		this.memoryWrite = memoryWrite;
	}

	public static ExecuteMemoryRegister getRegister() {
		return register;
	}
}
