package se.kth.iv1350.pointOfSale.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CashRegisterTest {
	
	private CashRegister cashReg;
	private Payment onePayment;
	private Payment anotherPayment;
	
	@BeforeEach
	void setUp() throws Exception {
		cashReg = new CashRegister(100);
		onePayment = new Payment(340);
		anotherPayment = new Payment(660);
	}

	@AfterEach
	void tearDown() throws Exception {
		cashReg = null;
		onePayment = null;
		anotherPayment = null;
	}

	@Test
	void testUpdateBalance() {
		cashReg.updateBalance(onePayment);
		cashReg.updateBalance(anotherPayment);
		double expectedBalance = 1100;
		double actualBalance = cashReg.getAmountInCashRegister();
		assertEquals(expectedBalance, actualBalance, "Balance not update correctly.");
	}
	
	@Test
	void testUpdateBalanceZero() {
		cashReg.updateBalance(onePayment);
		Payment zero = new Payment(0);
		cashReg.updateBalance(zero);
		double expectedBalance = 440;
		double actualBalance = cashReg.getAmountInCashRegister();
		assertEquals(expectedBalance, actualBalance, "Balance not update correctly.");
	}
	
	@Test
	void testUpdateBalanceNegative() {
		cashReg.updateBalance(onePayment);
		Payment negValue = new Payment(-100);
		cashReg.updateBalance(negValue);
		double expectedBalance = 340;
		double actualBalance = cashReg.getAmountInCashRegister();
		assertEquals(expectedBalance, actualBalance, "Balance not update correctly.");
	}
	
}
