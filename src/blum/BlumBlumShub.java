package blum;


import java.math.BigInteger;
import java.security.SecureRandom;


class BlumBlumShub {

 
    private boolean[] key;
    

    private static final BigInteger four = BigInteger.valueOf(4L);
    

    boolean[] getSequence(int size) {
        SecureRandom secureRandom = new SecureRandom();

        BigInteger seedBI = new BigInteger(secureRandom.generateSeed(size-1));
        BigInteger p, q, n;

        do {
            p = getRandomProbablePrime(secureRandom);
            q = getRandomProbablePrime(secureRandom);
            n = p.multiply(q);
        } while (!n.gcd(seedBI).equals(BigInteger.ONE));

        BigInteger xn_1 = seedBI.pow(2).mod(n);

        boolean[] booleanBitArray = new boolean[size];
        for (int i = 0; i < size; i++) {
            BigInteger xn = xn_1.pow(2).mod(n);
            xn_1 = xn;
            booleanBitArray[i] = xn.getLowestSetBit() == 1;
        }
        key = booleanBitArray;
        return booleanBitArray;

    }

    private BigInteger getRandomProbablePrime(SecureRandom secureRandom) {
        BigInteger p = BigInteger.probablePrime(512, secureRandom);
        if (p.mod(four).intValue() == 3) {
            return p;
        } else {
            return getRandomProbablePrime(secureRandom);
        }
    }

    public boolean[] getKey() {
        return key;
    }
}