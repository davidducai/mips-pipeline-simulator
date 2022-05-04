package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class View extends JFrame {

	private JPanel contentPane;
	
	private JPanel simulatorPanel = new JPanel();
	
	private Table registersTable;
	private Table memoryTable;
	
	private JButton startButton = new JButton("Start");
	private JButton nextButton = new JButton("Următorul >");
	private JButton resetButton = new JButton("Resetare");
	private JButton finishButton = new JButton("Finalizare");
	private JTextArea programArea = new JTextArea();
	private JTextArea textArea = new JTextArea();
	
	private JCheckBox treatHazardsBox = new JCheckBox("Tratează hazarduri");
	
	private JLabel cycleLabel = new JLabel("");
	

	public View() {
		setTitle("Simulator MIPS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon icon = new ImageIcon("images\\icon.png");
		setIconImage(icon.getImage());
		
		JLabel nameLabel = new JLabel("2021-2022  David Alex Ducai ");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(1089, 736, 165, 14);
		contentPane.add(nameLabel);
		
		simulatorPanel.setBounds(10, 27, 855, 502);
		contentPane.add(simulatorPanel);
		simulatorPanel.setLayout(null);
		
		ImageIcon mips = new ImageIcon("images\\mipsImage.png");
		ImageIcon register = new ImageIcon("images\\registerfile.png");
		ImageIcon memory = new ImageIcon("images\\memory.png");

		JLabel registerImage = new JLabel("", register, JLabel.CENTER);
		registerImage.setBounds(342, 255, 71, 109);
		simulatorPanel.add(registerImage);
		
		JLabel memoryImage = new JLabel("", memory, JLabel.CENTER);
		memoryImage.setBounds(655, 292, 71, 70);
		simulatorPanel.add(memoryImage);
		
		JLabel mipsImage = new JLabel("", mips, JLabel.CENTER);
		mipsImage.setBounds(0, 0, 855, 502);
		simulatorPanel.add(mipsImage);
		
		startButton.setBounds(1175, 540, 79, 23);
		contentPane.add(startButton);
		
		nextButton.setEnabled(false);
		nextButton.setBounds(749, 540, 116, 23);
		contentPane.add(nextButton);
		
		JLabel programLabel = new JLabel("Miniprogram");
		programLabel.setBounds(1031, 11, 223, 14);
		contentPane.add(programLabel);
		
		JLabel dataLabel = new JLabel("Componente");
		dataLabel.setBounds(875, 11, 146, 14);
		contentPane.add(dataLabel);
		
		JLabel simulatorLabel = new JLabel("MIPS");
		simulatorLabel.setBounds(10, 11, 29, 14);
		contentPane.add(simulatorLabel);
		
		JLayeredPane componentsPane = new JLayeredPane();
		componentsPane.setBounds(875, 27, 149, 502);
		contentPane.add(componentsPane);
		
		JScrollPane registersPane = new JScrollPane();
		registersPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		registersPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		registersPane.setBounds(0, 0, 149, 502);
		componentsPane.add(registersPane);
		
		registersTable = new Table(true);
		registersTable.getTable().setFillsViewportHeight(true);
		registersTable.getTable().setTableHeader(null);
		registersPane.setViewportView(registersTable.getTable());
		
		JScrollPane memoryPane = new JScrollPane();
		memoryPane.setVisible(false);
		memoryPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		memoryPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		memoryPane.setBounds(0, 0, 149, 502);
		componentsPane.add(memoryPane);
		
		memoryTable = new Table(false);
		memoryTable.getTable().setFillsViewportHeight(true);
		memoryTable.getTable().setTableHeader(null);
		memoryPane.setViewportView(memoryTable.getTable());
		
		JScrollPane programPanel = new JScrollPane();
		programPanel.setBounds(1031, 27, 223, 502);
		contentPane.add(programPanel);
		
		programPanel.setViewportView(programArea);
		
		resetButton.setBounds(935, 540, 89, 23);
		contentPane.add(resetButton);
		
		treatHazardsBox.setBounds(1031, 540, 138, 23);
		contentPane.add(treatHazardsBox);
		
		finishButton.setEnabled(false);
		finishButton.setBounds(650, 540, 89, 23);
		contentPane.add(finishButton);
		
		cycleLabel.setBounds(10, 544, 630, 14);
		contentPane.add(cycleLabel);
		
		JScrollPane helpPanel = new JScrollPane();
		helpPanel.setBounds(10, 574, 1244, 176);
		contentPane.add(helpPanel);
		
		textArea.setEditable(false);;
		helpPanel.setViewportView(textArea);
		
		memoryImage.addMouseListener(new MouseAdapter() {
		    
			@Override
		    public void mouseClicked(MouseEvent e) {
				registersPane.setVisible(false);
				memoryPane.setVisible(true);
		    }
		});
		
		registerImage.addMouseListener(new MouseAdapter() {
		    
			@Override
		    public void mouseClicked(MouseEvent e) {
				registersPane.setVisible(true);    
				memoryPane.setVisible(false);
		    }
		});
		
		setVisible(true);
	}

	public JPanel getSimulatorPanel() {
		return simulatorPanel;
	}

	public Table getRegistersTable() {
		return registersTable;
	}

	public Table getMemoryTable() {
		return memoryTable;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public JButton getFinishButton() {
		return finishButton;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JTextArea getProgramArea() {
		return programArea;
	}

	public JCheckBox getTreatHazardsBox() {
		return treatHazardsBox;
	}

	public JLabel getCycleLabel() {
		return cycleLabel;
	}
}
