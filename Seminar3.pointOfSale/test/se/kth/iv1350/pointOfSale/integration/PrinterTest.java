package se.kth.iv1350.pointOfSale.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pointOfSale.model.Change;
import se.kth.iv1350.pointOfSale.model.Payment;
import se.kth.iv1350.pointOfSale.model.Receipt;
import se.kth.iv1350.pointOfSale.model.Sale;

class PrinterTest {
	private Printer instanceToTest;
	private Sale saleInstance;
	private Receipt receiptInstance;
	private ByteArrayOutputStream printoutBuffer;
	private PrintStream originalSysOut;
	
	@BeforeEach
	void setUp() throws Exception {
		saleInstance = new Sale();
		instanceToTest = new Printer();
		
		printoutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysOut = new PrintStream(printoutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysOut);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		saleInstance = null;
		instanceToTest = null;
		
		printoutBuffer = null;
		System.setOut(originalSysOut);
	}

	@Test
	void testPrintReceipt() {
		ItemDTO anItem = new ItemDTO("P074T0", "Potatosalad", 1, 25.0, 25);
		Payment amountPaid = new Payment(700);
		saleInstance.updateSale(anItem);
		saleInstance.setAmountPaid(amountPaid);
		saleInstance.setChange(new Change(saleInstance.getTotalPrice(), amountPaid));
		receiptInstance = new Receipt(saleInstance);
		instanceToTest.printReceipt(receiptInstance);
		String result = printoutBuffer.toString();
		String expString = 
				"Sale started: " + saleInstance.getSaleTime() + " \n"
				+ "Items bought: \n\n" + anItem 
				+ "\nAmount of items bought: " + saleInstance.getQuantity() + " \n" 
				+ "Running total: " + saleInstance.getTotalPrice() + " \n"
				+ "VAT for sale: " + saleInstance.getVATforSale() + " \n"
				+ "Amount paid: " + saleInstance.getAmountPaid().getAmount() + " \n"
				+ "Change: " + (-saleInstance.getChange().getAmount()) + " \n";
		assertTrue(result.contains(expString), "Wrong printout.");
	}

}
