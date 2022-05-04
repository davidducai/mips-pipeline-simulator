package model.stage;

import model.component.ArithmeticLogicUnit;
import model.component.bufferregister.DecodeExecuteRegister;
import model.component.bufferregister.ExecuteMemoryRegister;

public class InstructionExecute {

	private ArithmeticLogicUnit alu;
	
	
	public InstructionExecute() {
		alu = new ArithmeticLogicUnit();
	}
	
	
	public void execute(DecodeExecuteRegister decodeExecute, ExecuteMemoryRegister executeMemory) { 
		// RegDst MUX
		if(decodeExecute.isRegisterDestination()) {
			executeMemory.setWriteAddress(decodeExecute.getRD());
		} else {
			executeMemory.setWriteAddress(decodeExecute.getRT());
		}
		
		// ALUControl
		int aluControl = 0;
		
		switch(decodeExecute.getAluOperation()) {
		case 0:
			aluControl = decodeExecute.getFunctionCode();
			break;
		case 1:
			aluControl = 0;
			break;
		case 2:
			aluControl = 0;
			break;
		case 3:
			aluControl = 0;
			break;
		case 4:
			aluControl = 1;
			break;
		case 5:
			aluControl = 4;
			break;
		case 6:
			aluControl = 5;
			break;
		case 7:
			aluControl = 0;
			break;
		}
		
		// ALU
		if(decodeExecute.isAluSource()) {
			executeMemory.setALUResult(alu.executeOperation(aluControl, decodeExecute.getReadData1(), decodeExecute.getImmediateValue(), decodeExecute.getShiftAmount()));
		} else {
			executeMemory.setALUResult(alu.executeOperation(aluControl, decodeExecute.getReadData1(), decodeExecute.getReadData2(), decodeExecute.getShiftAmount()));
		}
		
		executeMemory.setMemoryToRegister(decodeExecute.isMemoryToRegister());
		executeMemory.setRegisterWrite(decodeExecute.isRegisterWrite());
		executeMemory.setMemoryWrite(decodeExecute.isMemoryWrite());
		executeMemory.setBranch(decodeExecute.isBranch());
		executeMemory.setBranchAddress(decodeExecute.getNextInstruction() + decodeExecute.getImmediateValue());
		//System.out.println(decodeExecute.getNextInstruction() + decodeExecute.getImmediateValue() - 2);
		executeMemory.setZeroSignal(alu.isZeroSignal());
		executeMemory.setWriteData(decodeExecute.getReadData2());
	}
	
	public void transfer() {
		
	}


	public ArithmeticLogicUnit getAlu() {
		return alu;
	}
}
