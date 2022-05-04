package main;

import controller.Controller;
import model.processor.MIPS;
import view.View;


public class Launch {

	public static void main(String[] argv) {

		new Controller(new MIPS(), new View());
	}
}
