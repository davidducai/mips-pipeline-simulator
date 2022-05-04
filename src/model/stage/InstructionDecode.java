package model.stage;

import model.component.MainControl;
import model.component.RegisterFile;
import model.component.bufferregister.DecodeExecuteRegister;
import model.component.bufferregister.FetchDecodeRegister;

public class InstructionDecode {

	private RegisterFile registerFile;
	private MainControl mainControl = MainControl.getMainControl();
	
	public InstructionDecode() {
		registerFile = new RegisterFile();
	}
	
	public void execute(FetchDecodeRegister fetchDecode, DecodeExecuteRegister decodeExecute) {
		mainControl.configureControlSignals(fetchDecode.getInstruction());
		
		decodeExecute.setMemoryToRegister(mainControl.isMemoryToRegister());
		decodeExecute.setRegisterWrite(mainControl.isRegisterWrite());
		decodeExecute.setMemoryWrite(mainControl.isMemoryWrite());
		decodeExecute.setBranch(mainControl.isBranch());
		decodeExecute.setAluOperation(mainControl.getAluOperation());
		decodeExecute.setAluSource(mainControl.isAluSource());
		decodeExecute.setRegisterDestination(mainControl.isRegisterDestination());
		
		decodeExecute.setNextInstruction(fetchDecode.getNextInstruction());
		
		decodeExecute.setReadData1(registerFile.readData(fetchDecode.getInstruction().getRS()));
		decodeExecute.setReadData2(registerFile.readData(fetchDecode.getInstruction().getRT()));
		
		decodeExecute.setImmediateValue(fetchDecode.getInstruction().getImmediateValue());
		decodeExecute.setShiftAmount(fetchDecode.getInstruction().getShiftAmount());
		decodeExecute.setFunctionCode(fetchDecode.getInstruction().getFunctionCode());
		decodeExecute.setRT(fetchDecode.getInstruction().getRT());
		decodeExecute.setRD(fetchDecode.getInstruction().getRD());
	}

	public MainControl getMainControl() {
		return mainControl;
	}

	public RegisterFile getRegisterFile() {
		return registerFile;
	}

	public void setMainControl(MainControl mainControl) {
		this.mainControl = mainControl;
	}
}
