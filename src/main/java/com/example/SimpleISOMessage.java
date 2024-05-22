package com.example;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.ISO87APackager;

public class SimpleISOMessage {
    public static void main(String[] args) {
        try {
            // Create a new ISO-8583 message
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(new ISO87APackager());

            // Set MTI (Message Type Indicator) :  to indicate that the message arrive
            // without any change
            //0: ISO 8583-1:1987 version
            //2: Financial transaction
            //0: Request
            //0: Acquirer
            isoMsg.setMTI("0200");


            //DE 2: Primary Account Number (PAN)
            //DE 3: Processing Code
            //DE 4: Transaction Amount
            //DE 7: Transmission Date and Time
            //DE 11: Systems Trace Audit Number (STAN)
            //DE 37: Retrieval Reference Number
            //DE 49: Currency Code
            // Set some fields
            isoMsg.set(3, "000000");
            isoMsg.set(4, "1000");
            isoMsg.set(7, "110722180");
            isoMsg.set(11, "123456");
            isoMsg.set(41, "12345678");
            isoMsg.set(49, "840");

            // Print the message
            System.out.println("ISO-8583 Message: ");
            for (int i = 0; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                    System.out.println("  Field-" + i + ": " + isoMsg.getString(i));
                }
            }

            // Pack the message
            byte[] packedMessage = isoMsg.pack();
            System.out.println("Packed Message: " + new String(packedMessage));

            // Unpack the message
            ISOMsg unpackedMsg = new ISOMsg();
            unpackedMsg.setPackager(new ISO87APackager());
            unpackedMsg.unpack(packedMessage);

            System.out.println("Unpacked Message: ");
            for (int i = 0; i <= unpackedMsg.getMaxField(); i++) {
                if (unpackedMsg.hasField(i)) {
                    System.out.println("  Field-" + i + ": " + unpackedMsg.getString(i));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
