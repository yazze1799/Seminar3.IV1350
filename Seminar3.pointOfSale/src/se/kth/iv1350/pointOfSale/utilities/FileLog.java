package se.kth.iv1350.pointOfSale.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class FileLog {
	private static final String LOG_FILE_NAME = "POS-log.txt";
	private PrintWriter logFile;
	
	public FileLog() throws IOException {
		logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
	}
	
	public void logException (Exception e) {
		StringBuilder logMsg = new StringBuilder();
		logMsg.append("### LOG FILE ### \n");
		logMsg.append(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
		logMsg.append(": Exception thrown: ");
		logFile.println(logMsg);
		e.printStackTrace(logFile);
	}
}
