package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import model.processor.MIPS;
import view.HelpText;
import view.View;

public class Controller implements ActionListener {

	private MIPS mips;
	private View view;
	
	private HelpText help;
	
	private boolean simulationOn;
	
	private int cycleIndex;
	private int stopSimulationIndex;
	
	public Controller(MIPS mips, View view) {
		this.mips = mips;
		this.view = view;
		this.help = new HelpText();
		this.simulationOn = false;
		this.cycleIndex = 0;
		this.stopSimulationIndex = 0;
		
		// Setare sectiune de ajutor
		this.view.getTextArea().setText(help.getWelcomeText());
		
		// Adaugare listenere
		this.view.getResetButton().addActionListener(this);
		this.view.getStartButton().addActionListener(this);
		this.view.getNextButton().addActionListener(this);
		this.view.getFinishButton().addActionListener(this);
		
		this.view.getSimulatorPanel().addMouseListener(new MouseAdapter() {
		    
			// Actualizare sectiune de ajutor in functie de etajul apasat
			@Override
		    public void mouseClicked(MouseEvent e) {
				if(simulationOn) {
					int x = e.getX();
					
					// Instruction Fetch 
					if(x >= 0 && x <= 230) {
						view.getTextArea().setText(help.getFetchText());
					}
					
					// Instruction Decode
					if(x >= 250 && x <= 442) {
						view.getTextArea().setText(help.getDecodeText());
					}
					
					// Instruction Execute
					if(x >= 462 && x <= 593) {
						view.getTextArea().setText(help.getExecuteText());
					}
					
					// Memory
					if(x >= 614 && x <= 744) {
						view.getTextArea().setText(help.getMemoryText());
					}
					
					// Write Back
					if(x >= 764 && x <= 855) {
						view.getTextArea().setText(help.getWriteBackText());
					}
				}
		    }
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Butonul de reset
		if(e.getSource() == view.getResetButton()) {
			cycleIndex = 0;
			stopSimulationIndex = 0;

			// Resetare tabele
			view.getRegistersTable().resetTable();
			view.getMemoryTable().resetTable();
			
			// Resetare miniprogram
			view.getProgramArea().setText("");
			
			// Resetare interfata dupa simulare
			if(simulationOn) {
				resetView();
				mips.reset();
			}
		}
		
		// Butonul de start
		if(e.getSource() == view.getStartButton()) {
			// Colectare instructiuni
			ArrayList<String> program = new ArrayList<String>();
			Collections.addAll(program, view.getProgramArea().getText().split("\\n"));
			
			// Colectare valori din tabele
			ArrayList<String> registerTableValues = new ArrayList<String>();
			ArrayList<String> memoryTableValues = new ArrayList<String>();
			
			registerTableValues.add("0");
			
			for(int row = 1; row < 32; row++) {
				registerTableValues.add(view.getRegistersTable().getModel().getValueAt(row, 1).toString());
			}
			
			for(int row = 0; row < 64; row++) {
				memoryTableValues.add(view.getMemoryTable().getModel().getValueAt(row, 1).toString());
			}
			
			// Verificare program si tabele
			if(verifyProgram(program)) {
				if(verifyTableValues(registerTableValues) && verifyTableValues(memoryTableValues)) {
					// Activare mod simulare
					simulationOn = true;
					
					// Dezactivare elemente de interfata
					view.getStartButton().setEnabled(false);
					view.getTreatHazardsBox().setEnabled(false);
					view.getResetButton().setEnabled(false);
					view.getRegistersTable().setEditable(false);
					view.getMemoryTable().setEditable(false);
					view.getProgramArea().setEditable(false);
					
					// Activare elemente de interfata
					view.getNextButton().setEnabled(true);
					view.getFinishButton().setEnabled(true);
					
					view.getCycleLabel().setText("Apăsați butonul URMĂTORUL pentru a trece la primul ciclu de ceas.");
					
					// Actualizare sectiune de ajutor
					view.getTextArea().setText(help.getSimulationText());
					
					// Incarcare in blocul de registri si memorie
					for(int row = 1; row < 32; row++) {
						//mips.writeInRegisterFile(row, Integer.parseInt(view.getRegistersTable().getModel().getValueAt(row, 1).toString()));
						mips.writeInRegisterFile(row, Integer.parseInt(registerTableValues.get(row)));
					}
					
					for(int row = 0; row < 64; row++) {
						//mips.writeInMemory(row, Integer.parseInt(view.getMemoryTable().getModel().getValueAt(row, 1).toString()));
						mips.writeInMemory(row, Integer.parseInt(memoryTableValues.get(row)));
					}
					
					// Tratare hazarduri
					if(view.getTreatHazardsBox().isSelected()) {
						program = mips.treatHazards(program);
						
						// Actualizare sectiune program
						StringBuilder sb = new StringBuilder();
						program.forEach(bitsString -> sb.append(bitsString + "\n"));
						view.getProgramArea().setText(sb.toString());
					}
					
					// Incarcare program in MIPS
					mips.loadProgram(program);
					
					// Setare index oprire simulare
					stopSimulationIndex = program.size() + 3;
				}
				else {
					JOptionPane.showMessageDialog(view,"Valorile introduse în tabele nu sunt corespunzătoare! Introduceți doar valori numerice!","Eroare",JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(view,"Programul nu este scris corespunzător! Încercați din nou.","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		// Butonul pentru ciclul de ceas urmator
		if(e.getSource() == view.getNextButton()) {
			// Executare ciclu de ceas
			cycleIndex++;
			mips.executeCycle(help);
			
			// Pentru ciclul final
			if(mips.getProgramCounterValue() == stopSimulationIndex) {
				setSimulationDoneInterface();
			}
			
			// Actualizare elemente de interfata
			updateInterface();
			view.getTextArea().setText(help.getSimulationText());
		}
		
		// Butonul de finalizare
		if(e.getSource() == view.getFinishButton()) {
			// Executare cicluri de ceas ramase
			while(mips.getProgramCounterValue() != stopSimulationIndex) {
				cycleIndex++;
				mips.executeCycle();
			}
			
			// Dezactivare butoane de simulare
			setSimulationDoneInterface();
			
			// Actualizare elemente de interfata
			updateInterface();
			
			//System.out.println(mips.getInstructionDecode().getRegisterFile().getRegisters());
			//System.out.println(mips.getMemory().getMemory().getMemory());
		}
	}
	
	private void resetView() {
		// Dezactivare mod simulare
		simulationOn = false;
		
		// Resetare contor cicluri de ceas
		cycleIndex = 0;
		
		// Activare elemente de interfata
		view.getStartButton().setEnabled(true);
		view.getTreatHazardsBox().setEnabled(true);
		view.getRegistersTable().setEditable(true);
		view.getMemoryTable().setEditable(true);
		view.getProgramArea().setEditable(true);
		
		// Dezactivare elemente de interfata
		view.getCycleLabel().setText(null);
		
		// Setare sectiune de ajutor
		view.getTextArea().setText(help.getWelcomeText());
	}
	
	private void setSimulationDoneInterface() {
		// Activare buton de resetare
		view.getResetButton().setEnabled(true);
					
		// Dezactivare butoane pentru simulare
		view.getNextButton().setEnabled(false);
		view.getFinishButton().setEnabled(false);
		
		// Setare sectiune de ajutor
		view.getTextArea().setText(help.getFinalText());
	}
	
	private void updateInterface() {
		// Actualizare elemente de interfata
		view.getRegistersTable().updateTable(mips.getInstructionDecode().getRegisterFile().getRegisters());
		view.getMemoryTable().updateTable(mips.getMemory().getMemory().getMemory());
			
		view.getCycleLabel().setText("Ciclul " + cycleIndex);
	}
	
	private boolean verifyProgram(ArrayList<String> program) {
		Pattern pattern = Pattern.compile("^[0-1]{32}$"); // 32 bits string
		
		for(String bitsString : program) {
			Matcher matcher = pattern.matcher(bitsString);
			if(matcher.matches() == false) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean verifyTableValues(ArrayList<String> values) {
		Pattern pattern = Pattern.compile("^([+-]?[1-9]\\d*|0)$"); // cel putin o cifra
		
		for(String value : values) {
			Matcher matcher = pattern.matcher(value);
			if(matcher.matches() == false) {
				return false;
			}
		}
		
		return true;
	}
}
