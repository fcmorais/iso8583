package com.fcmorais.app.iso8583;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.ParseException;

import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;

public class App {

	public static void main(String[] args) {
		try {
			MessageFactory<IsoMessage> messageFactory = ConfigParser.createDefault();

			processMessageFile(messageFactory, "financial_transaction_message.dat",
					"translate_financial_transaction_message.txt");

			processMessageFile(messageFactory, "message_with_hex_bcd.dat", "translate_message_with_hex_bcd.txt");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void processMessageFile(MessageFactory<IsoMessage> messageFactory, String inputFileName,
			String outputFileName) {
		try {
			InputStream inputStream = App.class.getClassLoader().getResourceAsStream(inputFileName);
			if (inputStream == null) {
				throw new FileNotFoundException("Could not find '" + inputFileName + "'");
			}
			byte[] messageBytes = inputStream.readAllBytes();
			IsoMessage message;
			try {
				message = messageFactory.parseMessage(messageBytes, 0);
				writeParsedMessageToFile(message, outputFileName);
			} catch (ParseException e) {
				System.out.println("Error parsing message from " + inputFileName + ": " + e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeParsedMessageToFile(IsoMessage message, String filePath) throws Exception {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			if (message != null) {
				writer.write("Message: " + message.getType() + "\n");
				System.out.println(message.getBinaryIsoHeader());
				System.out.println(message.debugString());
				System.out.println(message.getCharacterEncoding());
				System.out.println(message.getIsoHeader());
				for (int i = 2; i <= 128; i++) {
					if (message.hasField(i)) {
						String fieldName = getFieldName(i);
						String fieldValue = message.getObjectValue(i).toString();
						writer.write(fieldName + ": " + fieldValue + "\n");
					}
				}
			}
		}
	}

	private static String getFieldName(int fieldNumber) {
		switch (fieldNumber) {
		case 3:
			return "Processing Code";
		case 4:
			return "Transaction Amount";
		case 5:
			return "Settlement Amount";
		case 7:
			return "Transmission Date & Time";
		case 8:
			return "Fee Amount";
		case 11:
			return "System Trace Audit Number";
		case 12:
			return "Local Transaction Time";
		case 15:
			return "Settlement Date";
		case 19:
			return "Acquiring Country Code";
		case 20:
			return "PAN Country Code";
		case 23:
			return "PAN Sequence Number";
		case 24:
			return "Function Code";
		case 26:
			return "POS Capture Code";
		case 32:
			return "Acquiring Identification Code";
		case 35:
			return "Track 2";
		default:
			return "Field " + fieldNumber;
		}
	}
}