package view;

import model.component.bufferregister.DecodeExecuteRegister;
import model.component.bufferregister.ExecuteMemoryRegister;
import model.component.bufferregister.FetchDecodeRegister;
import model.component.bufferregister.MemoryWriteBackRegister;
import model.stage.InstructionDecode;
import model.stage.InstructionExecute;
import model.stage.InstructionFetch;
import model.stage.Memory;
import model.stage.WriteBack;

public class HelpText {

	private String welcomeText;
	private String simulationText;
	private String finalText;
	
	private String fetchText;
	private String decodeText;
	private String executeText;
	private String memoryText;
	private String writeBackText;
	
	
	public HelpText() {
		setWelcomeText();
		setSimulationText();
		setFinalText();
	}
	
	private void setWelcomeText() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Instrucțiuni de utilizare:\n\n");
		sb.append(" 1. Introduceți miniprogramul în secțiunea dedicată.\n");
		sb.append(" 2. Dacă doriți, apăsați, pe schema procesorului, blocul de regiștri și memoria pentru a putea introduce date.\n");
		sb.append(" 3. Bifați căsuța TRATEAZĂ HAZARDURI dacă doriți tratarea hazardurilor din miniprogram.\n");
		sb.append(" 4. Apăsați pe butonul START pentru a începe simularea!\n\n");
		
		welcomeText = sb.toString();
	}
	
	private void setSimulationText() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Puteți apăsa pe etajele procesorului pentru a vedea rezultatele parțiale de pe etajul respectiv.\n");
		sb.append(" Puteți apăsa, pe schemă, blocul de regiștri și memoria de date pentru a vedea conținutul acestora în secțiunea COMPONENTE.\n\n");
		sb.append(" Pentru a trece la următorul ciclu de ceas, apăsați butonul URMĂTORUL.\n");
		sb.append(" Pentru a ajunge la ultimul ciclu de ceas, apăsați butonul FINALIZARE.\n");
		
		simulationText = sb.toString();
	}
	
	private void setFinalText() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Simulare terminată!\n\n");
		sb.append(" Puteți apăsa, pe schemă, blocul de regiștri și memoria de date pentru a vedea conținutul acestora în secțiunea COMPONENTE.\n\n");
		sb.append(" Pentru a porni o nouă simulare, apăsați butonul RESETARE.\n");
		sb.append(" Prin apăsarea butonului RESETARE se va șterge conținutul secțiunilor COMPONENTE și MINIPROGRAM.");
		
		finalText = sb.toString();
	}
	
	public void setFetchText(InstructionFetch fetch, FetchDecodeRegister register) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Instruction Fetch:\n\n");
		sb.append(" Program Counter: " + fetch.getProgramCounter().getValue() + "\n");
		sb.append(" Instruction: " + register.getInstruction().toString() + "\n");
		sb.append(" Next Instruction Index: " + register.getNextInstruction() + "\n");
		
		fetchText = sb.toString();
	}
	
	public void setDecodeText(InstructionDecode decode, FetchDecodeRegister register) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Instruction Decode:\n\n");
		sb.append(" Instruction: " + register.getInstruction().toString() + "\n");
		sb.append(" Opcode: " + register.getInstruction().getOperationCode() + "\n");
		sb.append(" RS: " + register.getInstruction().getRS() + "\n");
		sb.append(" RT: " + register.getInstruction().getRT() + "\n");
		sb.append(" RD: " + register.getInstruction().getRD() + "\n");
		sb.append(" Imm: " + register.getInstruction().getImmediateValue() + "\n");
		sb.append(" Shift Amount: " + register.getInstruction().getShiftAmount() + "\n");
		sb.append(" Function Code: " + register.getInstruction().getFunctionCode() + "\n\n");
		sb.append(" Main Control Signals: \n");
		sb.append(" MemToReg: " + decode.getMainControl().isMemoryToRegister() + "\n");
		sb.append(" RegWrite: " + decode.getMainControl().isRegisterWrite() + "\n");
		sb.append(" MemWrite: " + decode.getMainControl().isMemoryWrite() + "\n");
		sb.append(" Branch: " + decode.getMainControl().isBranch() + "\n");
		sb.append(" ALUOp: " + decode.getMainControl().getAluOperation() + "\n");
		sb.append(" ALUSrc: " + decode.getMainControl().isAluSource() + "\n");
		sb.append(" RegDst: " + decode.getMainControl().isRegisterDestination() + "\n");
		sb.append(" ExtOp: " + decode.getMainControl().isExtensionOperand() + "\n");
		
		decodeText = sb.toString();
	}
	
	public void setExecuteText(InstructionExecute execute, DecodeExecuteRegister register) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Instruction Execute:\n\n");
		sb.append(" RT: " + register.getRT() + "\n");
		sb.append(" RD: " + register.getRD() + "\n");
		sb.append(" RegDst: " + register.isRegisterDestination() + "\n");
		sb.append(" RD1: " + register.getReadData1() + "\n");
		sb.append(" RD2: " + register.getReadData2() + "\n");
		sb.append(" ALUSrc: " + register.isAluSource() + "\n");
		sb.append(" ExtImm: " + register.getImmediateValue() + "\n");
		sb.append(" Shift Amount: " + register.getShiftAmount() + "\n");
		sb.append(" Function Code: " + register.getFunctionCode() + "\n");
		sb.append(" ALUOp: " + register.getAluOperation() + "\n");
		sb.append(" RegDst: " + register.isRegisterDestination() + "\n");
		sb.append(" ALURes: " + execute.getAlu().getResult() + "\n");
		sb.append(" Zero Signal: " + execute.getAlu().isZeroSignal() + "\n");
		
		executeText = sb.toString();
	}
	
	public void setMemoryText(Memory memory, ExecuteMemoryRegister register) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Memory:\n\n");
		sb.append(" Address: " + register.getWriteAddress() + "\n");
		sb.append(" Write Data to Memory: " + register.getWriteData() + "\n");
		sb.append(" MemWrite: " + register.isMemoryWrite() + "\n");
		sb.append(" Zero: " + register.isZeroSignal() + "\n");
		sb.append(" Branch: " + register.isBranch() + "\n");
		sb.append(" PCSource: " + memory.isPcSource() + "\n");
		sb.append(" Branch Address Index: " + register.getBranchAddress() + "\n");
		
		memoryText = sb.toString();
	}
	
	public void setWriteBackText(WriteBack writeBack, MemoryWriteBackRegister register) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Write Back:\n\n");
		sb.append(" Read Data from Memory: "  + register.getReadData() + "\n");
		sb.append(" ALU Result: "  + register.getAluResult() + "\n");
		sb.append(" MemToReg: "  + register.isMemoryToRegister() + "\n");
		sb.append(" Write Data to Register: "  + writeBack.getWriteData() + "\n");
		sb.append(" RegWrite: " + register.isRegisterWrite() + "\n");
		
		writeBackText = sb.toString();
	}

	public String getWelcomeText() {
		return welcomeText;
	}

	public String getSimulationText() {
		return simulationText;
	}

	public String getFetchText() {
		return fetchText;
	}

	public String getDecodeText() {
		return decodeText;
	}

	public String getExecuteText() {
		return executeText;
	}

	public String getMemoryText() {
		return memoryText;
	}

	public String getWriteBackText() {
		return writeBackText;
	}

	public String getFinalText() {
		return finalText;
	}
}
