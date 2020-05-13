package se.kth.iv1350.pointOfSale.controller;

public class OperationFailedException extends Exception {
	public OperationFailedException(String msg, Exception exceptionCause) {
		super(msg, exceptionCause);
	}
}
