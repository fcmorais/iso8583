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
		case 1:
			return "Bitmap";
		case 2:
			return "Primary Account Number (PAN)";
		case 3:
			return "Processing Code";
		case 4:
			return "Transaction Amount";
		case 5:
			return "Settlement Amount";
		case 6:
			return "Amount, Cardholder Billing";
		case 7:
			return "Transmission Date & Time";
		case 8:
			return "Amount, Cardholder Billing Fee";
		case 9:
			return "Conversion Rate, Settlement";
		case 10:
			return "Conversion Rate, Cardholder Billing";
		case 11:
			return "System Trace Audit Number";
		case 12:
			return "Local Transaction Time";
		case 13:
			return "Local Transaction Date";
		case 14:
			return "Expiration Date";
		case 15:
			return "Settlement Date";
		case 16:
			return "Currency Conversion Date";
		case 17:
			return "Capture Date";
		case 18:
			return "Merchant Type";
		case 19:
			return "Acquiring Institution Country Code";
		case 20:
			return "PAN Extended Country Code";
		case 21:
			return "Forwarding Institution Country Code";
		case 22:
			return "Point of Service Entry Mode";
		case 23:
			return "Application PAN Sequence Number";
		case 24:
			return "Function Code";
		case 25:
			return "Point of Service Condition Code";
		case 26:
			return "Point of Service Capture Code";
		case 27:
			return "Authorizing Identification Response Length";
		case 28:
			return "Transaction Fee Amount";
		case 29:
			return "Settlement Fee Amount";
		case 30:
			return "Transaction Processing Fee Amount";
		case 31:
			return "Settlement Processing Fee Amount";
		case 32:
			return "Acquiring Institution Identification Code";
		case 33:
			return "Forwarding Institution Identification Code";
		case 34:
			return "Primary Account Number, Extended";
		case 35:
			return "Track 2 Data";
		case 36:
			return "Track 3 Data";
		case 37:
			return "Retrieval Reference Number";
		case 38:
			return "Authorization Identification Response";
		case 39:
			return "Response Code";
		case 40:
			return "Service Restriction Code";
		case 41:
			return "Card Acceptor Terminal Identification";
		case 42:
			return "Card Acceptor Identification Code";
		case 43:
			return "Card Acceptor Name/Location";
		case 44:
			return "Additional Response Data";
		case 45:
			return "Track 1 Data";
		case 46:
			return "Additional Data - ISO";
		case 47:
			return "Additional Data - National";
		case 48:
			return "Additional Data - Private";
		case 49:
			return "Currency Code, Transaction";
		case 50:
			return "Currency Code, Settlement";
		case 51:
			return "Currency Code, Cardholder Billing";
		case 52:
			return "Personal Identification Number Data";
		case 53:
			return "Security Related Control Information";
		case 54:
			return "Additional Amounts";
		case 55:
			return "ICC Data – EMV having multiple tags";
		case 56:
			return "Reserved ISO";
		case 57:
			return "Reserved National";
		case 58:
			return "Reserved National";
		case 59:
			return "Reserved National";
		case 60:
			return "Reserved National";
		case 61:
			return "Reserved Private";
		case 62:
			return "Reserved Private";
		case 63:
			return "Reserved Private";
		case 64:
			return "Message Authentication Code (MAC)";
		case 65:
			return "Bitmap, Extended";
		case 66:
			return "Settlement Code";
		case 67:
			return "Extended Payment Code";
		case 68:
			return "Receiving Institution Country Code";
		case 69:
			return "Settlement Institution Country Code";
		case 70:
			return "Network Management Information Code";
		case 71:
			return "Message Number";
		case 72:
			return "Message Number, Last";
		case 73:
			return "Action Date (YYMMDD)";
		case 74:
			return "Credits, Number";
		case 75:
			return "Credits, Reversal Number";
		case 76:
			return "Debits, Number";
		case 77:
			return "Debits, Reversal Number";
		case 78:
			return "Transfer Number";
		case 79:
			return "Transfer, Reversal Number";
		case 80:
			return "Inquiries, Number";
		case 81:
			return "Authorizations, Number";
		case 82:
			return "Credits, Processing Fee Amount";
		case 83:
			return "Credits, Transaction Fee Amount";
		case 84:
			return "Debits, Processing Fee Amount";
		case 85:
			return "Debits, Transaction Fee Amount";
		case 86:
			return "Credits, Amount";
		case 87:
			return "Credits, Reversal Amount";
		case 88:
			return "Debits, Amount";
		case 89:
			return "Debits, Reversal Amount";
		case 90:
			return "Original Data Elements";
		case 91:
			return "File Update Code";
		case 92:
			return "File Security Code";
		case 93:
			return "Response Indicator";
		case 94:
			return "Service Indicator";
		case 95:
			return "Replacement Amounts";
		case 96:
			return "Message Security Code";
		case 97:
			return "Amount, Net Settlement";
		case 98:
			return "Payee";
		case 99:
			return "Settlement Institution Identification Code";
		case 100:
			return "Receiving Institution Identification Code";
		case 101:
			return "File Name";
		case 102:
			return "Account Identification 1";
		case 103:
			return "Account Identification 2";
		case 104:
			return "Transaction Description";
		case 105:
			return "Reserved for ISO Use";
		case 106:
			return "Reserved for ISO";
		case 107:
			return "Reserved for ISO";
		case 108:
			return "Reserved for ISO";
		case 109:
			return "Reserved for ISO";
		case 110:
			return "Reserved for ISO";
		case 111:
			return "Reserved for ISO";
		case 112:
			return "Reserved for National Use";
		case 113:
			return "Reserved for National";
		case 114:
			return "Reserved for National";
		case 115:
			return "Reserved for National";
		case 116:
			return "Reserved for National";
		case 117:
			return "Reserved for National";
		case 118:
			return "Reserved for National";
		case 119:
			return "Reserved for National";
		case 120:
			return "Reserved for Private Use";
		case 121:
			return "Reserved for Private";
		case 122:
			return "Reserved for Private";
		case 123:
			return "Reserved for Private";
		case 124:
			return "Reserved for Private";
		case 125:
			return "Reserved for Private";
		case 126:
			return "Reserved for Private";
		case 127:
			return "Reserved for Private";
		case 128:
			return "Message Authentication Code";
		default:
			return "Field " + fieldNumber;
		}
	}

}