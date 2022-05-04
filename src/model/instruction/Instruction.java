package model.instruction;

import java.util.ArrayList;
import java.util.List;

public class Instruction {

	protected ArrayList<Boolean> bits;
	
	
	public Instruction() {
		bits = new ArrayList<Boolean>(32);
	}
	
	public Instruction(String bitsString) {
		bits = convertStringtoBooleanList(bitsString);
	}
	
	public int getOperationCode() { 
		return convertBooleanToInteger(bits.subList(0, 6));
	}

	public int getRS() {
		return convertBooleanToInteger(bits.subList(6, 11));
	} 
	
	public int getRT() {
		return convertBooleanToInteger(bits.subList(11, 16));
	}
	
	public int getRD() {
		return convertBooleanToInteger(bits.subList(16, 21));
	}
	
	public int getShiftAmount() {
		return convertBooleanToInteger(bits.subList(21, 26));
	}
	
	public int getFunctionCode() {
		return convertBooleanToInteger(bits.subList(26, 32));
	}
	
	public int getImmediateValue() {
		return convertBooleanToInteger(bits.subList(16, 32));
	}
	
	public int getAddress() {
		return convertBooleanToInteger(bits.subList(6, 32));
	}
	
	
	protected int convertBooleanToInteger(List<Boolean> bits) {
		int value = 0;
		
		for(boolean bit : bits) {
			value = (value << 1) | (bit ? 1 : 0);
		}
		
		return value;
	}
	
	private ArrayList<Boolean> convertStringtoBooleanList(String bitsString){
		ArrayList<Boolean> result = new ArrayList<Boolean>(32);
		
		for (int i = 0; i < bitsString.length(); i++){
		    char b = bitsString.charAt(i);
		    if(b == '0') {
		    	result.add(false);
		    } else {
		    	result.add(true);
		    }
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(boolean bit : bits) {
			String b = bit ? "1" : "0";
			sb.append(b);
		}
		
		return sb.toString();
	}

	public ArrayList<Boolean> getBits() {
		return bits;
	}
}
