package model.component;


public class ArithmeticLogicUnit {

	private int result;
	private boolean zeroSignal;
	
	
	public int executeOperation(int aluControl, int firstOperand, int secondOperand, int shiftAmount) {
		int result = 0;
		zeroSignal = false;
		
		switch(aluControl) {
			case 0:
				result = firstOperand + secondOperand;
				break;
			case 1:
				result = firstOperand - secondOperand;
				zeroSignal = (result == 0);
				break;
			case 2:
				result = secondOperand << shiftAmount;
				break;
			case 3:
				result = secondOperand >> shiftAmount;
				break;
			case 4:
				result = firstOperand & secondOperand;
				break;
			case 5:
				result = firstOperand | secondOperand;
				break;
			case 6:
				result = firstOperand << secondOperand;
				break;
			case 7:
				result = firstOperand ^ secondOperand;
				break;
		}
		
		this.result = result;
		return result;
	}

	public int getResult() {
		return result;
	}

	public boolean isZeroSignal() {
		return zeroSignal;
	}
}
