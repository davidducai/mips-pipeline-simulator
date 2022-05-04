package model.component.bufferregister;


public class DecodeExecuteRegister {

	private static DecodeExecuteRegister register = new DecodeExecuteRegister();
	
	// Semnale de control
	private boolean registerDestination;
	private boolean aluSource;
	private boolean branch;
	private boolean memoryWrite;
	private boolean memoryToRegister;
	private boolean registerWrite;
	private int aluOperation;
	
	
	private int nextInstruction;
	private int readData1;
	private int readData2;
	private int immediateValue;
	private int shiftAmount;
	private int functionCode;
	private int rt;
	private int rd;
	
	private DecodeExecuteRegister() {}

	public void reset() {
		registerDestination = false;
		aluSource = false;
		branch = false;
		memoryWrite = false;
		memoryToRegister = false;
		registerWrite = false;
		aluOperation = 0;
		
		
		nextInstruction = 0;
		readData1 = 0;
		readData2 = 0;
		immediateValue = 0;
		shiftAmount = 0;
		functionCode = 0;
		rt = 0;
		rd = 0;
	}
	
	public boolean isRegisterDestination() {
		return registerDestination;
	}

	public void setRegisterDestination(boolean registerDestination) {
		this.registerDestination = registerDestination;
	}

	public boolean isAluSource() {
		return aluSource;
	}

	public void setAluSource(boolean aluSource) {
		this.aluSource = aluSource;
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

	public int getAluOperation() {
		return aluOperation;
	}

	public void setAluOperation(int aluOperation) {
		this.aluOperation = aluOperation;
	}

	public static DecodeExecuteRegister getRegister() {
		return register;
	}

	public int getNextInstruction() {
		return nextInstruction;
	}

	public void setNextInstruction(int nextInstruction) {
		this.nextInstruction = nextInstruction;
	}

	public int getReadData1() {
		return readData1;
	}

	public void setReadData1(int readData1) {
		this.readData1 = readData1;
	}

	public int getReadData2() {
		return readData2;
	}

	public void setReadData2(int readData2) {
		this.readData2 = readData2;
	}

	public int getImmediateValue() {
		return immediateValue;
	}

	public void setImmediateValue(int immediateValue) {
		this.immediateValue = immediateValue;
	}

	public int getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(int functionCode) {
		this.functionCode = functionCode;
	}

	public int getRT() {
		return rt;
	}

	public void setRT(int rt) {
		this.rt = rt;
	}

	public int getRD() {
		return rd;
	}

	public void setRD(int rd) {
		this.rd = rd;
	}

	public int getShiftAmount() {
		return shiftAmount;
	}

	public void setShiftAmount(int shiftAmount) {
		this.shiftAmount = shiftAmount;
	}
}
