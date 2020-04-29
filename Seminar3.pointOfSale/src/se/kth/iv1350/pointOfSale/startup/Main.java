package se.kth.iv1350.pointOfSale.startup;

import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.ExternalSystemHandler;
import se.kth.iv1350.pointOfSale.integration.Printer;
import se.kth.iv1350.pointOfSale.integration.RegistryHandler;
import se.kth.iv1350.pointOfSale.view.View;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Printer printer = new Printer();
		ExternalSystemHandler exSysHandler = new ExternalSystemHandler();
		RegistryHandler regHandler = new RegistryHandler();
		Controller contr = new Controller(printer, exSysHandler, regHandler);
		View view = new View(contr);
		view.runFakeExecution();
	}

}
