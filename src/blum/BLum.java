package blum;

import java.io.UnsupportedEncodingException;
import sun.security.util.BitArray;
import java.util.Scanner;

public class BLum {

    public static void main(String[] args) throws UnsupportedEncodingException {

        System.out.println("Co chcesz zaszyfrować?");

        Scanner scanner = new Scanner(System.in);
        String msg = scanner.next();

        boolean[] booleans = writingStringToBoolean(msg);

        BlumBlumShub blumBlumShub = new BlumBlumShub();
        boolean[] sequence = blumBlumShub.getSequence(booleans.length);
        for (int i = 0; i < booleans.length; i++) {
            booleans[i] = booleans[i] ^ sequence[i];
        }
         System.out.println("Zaszyfroowane" + readingBooleansToBit(booleans));
        for (int i = 0; i < booleans.length; i++) {
            booleans[i] = booleans[i] ^ blumBlumShub.getKey()[i];
        }
        readingBitToString(booleans);

    }

    private static boolean[] writingStringToBoolean(String msg) throws UnsupportedEncodingException {
        byte[] infoBin = null;
        infoBin = msg.getBytes("UTF-8");
        String message = "";
        for (byte b : infoBin) {

            String bin = Integer.toBinaryString(b);
            while (bin.length() < 8) {
                bin = "0" + bin;
            }

            message = message + bin;
        }
        System.out.println("Bitowa: " + message);

        boolean[] bools = new boolean[message.length()];
        int j = 0;
        for (char a : message.toCharArray()) {
            if (a == '1') {
                bools[j] = true;
            } else {
                bools[j] = false;
            }
            //asd   System.out.println("bools[j]" + bools[j]);
            j++;
        }
        return bools;
    }
    
     private static String readingBooleansToBit(boolean[] booleans) {
         String mess = "";
        for (boolean a : booleans) {
            if (a == true) {
                mess += '1';
            } else {
                mess += '0';
            }
        }
        return mess;
     }

    private static String readingBitToString(boolean[] booleans) {
        String mess = readingBooleansToBit(booleans);
        
        String a = "";
        for (int i = 0; i < mess.length(); i += 8) {
            String temp = mess.substring(i, i + 8);
            int num = Integer.parseInt(temp, 2);
            char letter = (char) num;
            a = a + letter;
        }
        System.out.println("Rozszyfrowany: " + a);
        return mess;
    }

}
