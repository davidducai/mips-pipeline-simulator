package model.processor;

import java.util.ArrayList;

import model.component.bufferregister.DecodeExecuteRegister;
import model.component.bufferregister.ExecuteMemoryRegister;
import model.component.bufferregister.FetchDecodeRegister;
import model.component.bufferregister.MemoryWriteBackRegister;
import model.instruction.Instruction;
import model.stage.InstructionDecode;
import model.stage.InstructionExecute;
import model.stage.InstructionFetch;
import model.stage.Memory;
import model.stage.WriteBack;
import view.HelpText;


public class MIPS {

	private InstructionFetch instructionFetch;
	private FetchDecodeRegister fetchDecode = FetchDecodeRegister.getRegister();
	private InstructionDecode instructionDecode;
	private DecodeExecuteRegister decodeExecute = DecodeExecuteRegister.getRegister();
	private InstructionExecute instructionExecute;
	private ExecuteMemoryRegister executeMemory = ExecuteMemoryRegister.getRegister();
	private Memory memory;
	private MemoryWriteBackRegister memoryWriteBack = MemoryWriteBackRegister.getRegister();
	private WriteBack writeBack;
	
	public MIPS() {
		instructionFetch = new InstructionFetch();
		instructionDecode = new InstructionDecode();
		instructionExecute = new InstructionExecute();
		memory = new Memory();
		writeBack = new WriteBack();
	}
	
	// PENTRU DEMO
	/*
	   0 NoOp
	   1 000000 00001 00010 00011 00000000000  // add $3, $1, $2 => 2 in reg 3
	   2 000111 00000 00000 00000 00000000100 // j 4 => sare la 4
	   3 000000 00000 00100 00101 00010 000010 // sll $5, $4, 2
	   4 000000 00110 00111 01000 00000 000100 // and $8, $6, $7 => 1 in reg 8
	   5 000000 01001 01010 01011 00000 000110 // sllv $11, $9, $10 => 2 in reg 11
	   6 000010 00001 01100 00000 00000000000 // lw $12 , 0($1) => scrie in registrul 12 valoarea din memorie de la adresa 1
	   7 000011 00011 00010 00000 00000000000 // sw $2, 0($3) => scrie la adresa 2 din mem valoarea din reg 2
	   8 NoOp
	   9 NoOp
	   10 NoOp
	 */
	
	public void initializeDemo() {
		instructionDecode.getRegisterFile().getRegisters().set(1, 1);
		instructionDecode.getRegisterFile().getRegisters().set(2, 1);
		instructionDecode.getRegisterFile().getRegisters().set(4, 1);
		instructionDecode.getRegisterFile().getRegisters().set(6, 3);
		instructionDecode.getRegisterFile().getRegisters().set(7, 1);
		instructionDecode.getRegisterFile().getRegisters().set(9, 1);
		instructionDecode.getRegisterFile().getRegisters().set(10, 1);
		memory.getMemory().getMemory().set(1, 5);
		
		
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction("00000000000000000000000000000000")); //  0
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction("00000000001000100001100000000000")); //  1
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction("00011100000000000000000000000100")); //  2
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction("00000000000001000010100010000010")); //  3
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction("00000000110001110100000000000100")); //  4
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction("00000001001010100101100000000110")); //  5
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction("00001000001011000000000000000000")); //  6
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction("00001100011000100000000000000000")); //  7
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction("00000000000000000000000000000000")); //  8
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction("00000000000000000000000000000000")); //  9
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction("00000000000000000000000000000000")); // 10
	}
	
	public void executeCycle() {
		writeBack.execute(memoryWriteBack, instructionDecode);
		memory.execute(executeMemory, memoryWriteBack, instructionFetch);
		instructionExecute.execute(decodeExecute, executeMemory);
		instructionDecode.execute(fetchDecode, decodeExecute);
		instructionFetch.execute(fetchDecode, instructionDecode);
	}
	
	public void executeCycle(HelpText help) {
		writeBack.execute(memoryWriteBack, instructionDecode);
		help.setWriteBackText(writeBack, memoryWriteBack);
		
		memory.execute(executeMemory, memoryWriteBack, instructionFetch);
		help.setMemoryText(memory, executeMemory);
		
		instructionExecute.execute(decodeExecute, executeMemory);
		help.setExecuteText(instructionExecute, decodeExecute);
		
		instructionDecode.execute(fetchDecode, decodeExecute);
		help.setDecodeText(instructionDecode, fetchDecode);
		
		instructionFetch.execute(fetchDecode, instructionDecode);
		help.setFetchText(instructionFetch, fetchDecode);
	}
	
	public void reset() {
		clearInstructionMemory();
		instructionFetch.getProgramCounter().setValue(-1);
		fetchDecode.reset();
		decodeExecute.reset();
		executeMemory.reset();
		memoryWriteBack.reset();
	}
	
	private void addInstruction(String bitsString) {
		instructionFetch.getInstructionMemory().getInstructions().add(new Instruction(bitsString));
	}
	
	public void loadProgram(ArrayList<String> program) {
		for(String instruction : program) {
			addInstruction(instruction);
		}
	}
	
	public ArrayList<String> treatHazards(ArrayList<String> program){
		ArrayList<String> treatedProgram = new ArrayList<String>();
		
		for(int index = 0; index < program.size(); index++) {
			String bitsString = program.get(index);
			
			treatedProgram.add(bitsString);
			
			switch(bitsString.substring(0, 6)) { // opcode
				case "000100": // beq
					for(int i = 1; i <= 3 ; i++) {
						treatedProgram.add("00000000000000000000000000000000");
					}
					break;
				case "000111": // j
					treatedProgram.add("00000000000000000000000000000000");
					break;
				default:
					break;
			}
		}
		
		return treatedProgram;
	}
	
	public void writeInRegisterFile(int address, int value) {
		instructionDecode.getRegisterFile().writeData(address, value);
	}
	
	public void writeInMemory(int address, int value) {
		memory.getMemory().writeData(address, value);
	}

	public InstructionDecode getInstructionDecode() {
		return instructionDecode;
	}
	
	private void clearInstructionMemory() {
		instructionFetch.getInstructionMemory().getInstructions().clear();
	}
	

	public Memory getMemory() {
		return memory;
	}
	
	public int getProgramCounterValue() {
		return instructionFetch.getProgramCounter().getValue();
	}
}
