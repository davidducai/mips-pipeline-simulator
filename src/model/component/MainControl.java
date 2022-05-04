package model.component;

import model.instruction.Instruction;

public class MainControl {

	private static MainControl mainControl = new MainControl();
	
	private boolean registerDestination;
	private boolean extensionOperand;
	private boolean aluSource;
	private boolean branch;
	private boolean jump;
	private boolean memoryWrite;
	private boolean memoryToRegister;
	private boolean registerWrite;
	private int aluOperation;
	
	
	private MainControl() {}
	
	public void configureControlSignals(Instruction instruction) {
		reset();
		switch(instruction.getOperationCode()) {
			case 0: // Instructiuni de tip R
				registerDestination = true;
				registerWrite = true;
				aluOperation = 0;
				break;
			case 1: // addi
				registerWrite = true;
				aluSource = true;
				extensionOperand = true;
				aluOperation = 1;
				break;
			case 2: // lw
				registerWrite = true;
				aluSource = true;
				extensionOperand = true;
				memoryToRegister = true;
				aluOperation = 2;
				break;
			case 3: // sw
				aluSource = true;
				extensionOperand = true;
				memoryWrite = true;
				aluOperation = 3;
				break;
			case 4: // beq
				extensionOperand = true;
				branch = true;
				aluOperation = 4;
				break;
			case 5: // andi
				registerWrite = true;
				aluSource = true;
				aluOperation = 5;
				break;
			case 6: // ori
				registerWrite = true;
				aluSource = true;
				aluOperation = 6;
				break;
			case 7: // jump
				jump = true;
				break;
		}
	}
	
	private void reset() {
		registerDestination = false;
		extensionOperand = false;
	    aluSource = false ;
		branch = false;
		jump = false ;
		memoryWrite = false;
		memoryToRegister = false;
		registerWrite = false;
		aluOperation = 0;
	}

	public boolean isRegisterDestination() {
		return registerDestination;
	}

	public boolean isExtensionOperand() {
		return extensionOperand;
	}

	public boolean isAluSource() {
		return aluSource;
	}

	public boolean isBranch() {
		return branch;
	}

	public boolean isJump() {
		return jump;
	}

	public boolean isMemoryWrite() {
		return memoryWrite;
	}

	public boolean isMemoryToRegister() {
		return memoryToRegister;
	}

	public boolean isRegisterWrite() {
		return registerWrite;
	}

	public int getAluOperation() {
		return aluOperation;
	}

	public static MainControl getMainControl() {
		return mainControl;
	}
}
