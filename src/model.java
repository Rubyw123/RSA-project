import java.math.*;
import java.util.*;

/**
 * Model class for RSA project Block length is 32 bits(4 ascii char), public key
 * n is 40 bits.
 * 
 * @author Yizheng Wang
 */
public class model
{
    private BigInteger         n;
    private BigInteger         phi;
    private BigInteger         e;
    private BigInteger         d;
    private String             input;
    private Vector<BigInteger> myC;
    private Vector<BigInteger> myI;
    private Vector<BigInteger> myD;

    /**
     * Constructor automatically generate two different primes and calculate
     * properties: n, phi, e, d
     */
    public model()
    {
        BigInteger p, q, temp;
        Random rnd = new Random();
        p = BigInteger.probablePrime(20, rnd);
        temp = BigInteger.probablePrime(20, rnd);

        while (temp.intValue() == p.intValue())
        {
            temp = BigInteger.probablePrime(20, rnd);
        }
        q = temp;

        n = p.multiply(q);
        phi = p.subtract(BigInteger.valueOf(1))
                .multiply(q.subtract(BigInteger.valueOf(1)));
        e = this.findPublicKey();
        d = this.findPrivateKey();

    }

    /**
     * Find fixed public key in "3","17","65537"
     * 
     * @return BigInteger public key
     */
    private BigInteger findPublicKey()
    {
        BigInteger e = new BigInteger("3");
        if (e.gcd(phi).intValue() == 1)
        {
            return e;
        } else
        {
            e = new BigInteger("17");
            if (e.gcd(phi).intValue() == 1)
            {
                return e;
            } else
            {
                e = new BigInteger("65537");
                if (e.gcd(phi).intValue() == 1)
                {
                    return e;
                }
            }
        }

        return null;
    }

    /**
     * Find private key through calculation
     * 
     * @return BigInteger private key
     */
    private BigInteger findPrivateKey()
    {
        return (e.modInverse(phi));
    }

    /**
     * Break the plain text blocks with 12 digits length
     * 
     * @return blocks
     */
    public Vector<BigInteger> stringToNum()
    {
        char[] c = input.toCharArray();
        myI = new Vector<BigInteger>();
        StringBuilder sb;

        for (int i = 0; i < c.length; i += 4)
        {
            sb = new StringBuilder();

            for (int j = 0; j < 4; j++)
            {
                if ((i + j) > c.length - 1)
                {
                    sb.append("000");
                } else
                {
                    if (String.valueOf((int) c[i + j]).length() == 3)
                    {
                        sb.append(String.valueOf((int) c[i + j]));
                    } else if (String.valueOf((int) c[i + j]).length() == 2)
                    {
                        sb.append(0);
                        sb.append(String.valueOf((int) c[i + j]));
                    } else if (String.valueOf((int) c[i + j]).length() == 1)
                    {
                        sb.append(0);
                        sb.append(0);
                        sb.append(String.valueOf((int) c[i + j]));
                    }

                }

            }
            myI.add(new BigInteger(sb.toString()));
        }
        return myI;
    }

    /**
     * Encryption char by char
     */
    public void encryption()
    {
        myC = new Vector<BigInteger>();
        for (int i = 0; i < myI.size(); i++)
        {
            myC.add(myI.get(i).modPow(e, n));
        }
    }

    /**
     * Decryption char by char
     */
    public void decryption()
    {
        myD = new Vector<BigInteger>();
        for (int i = 0; i < myC.size(); i++)
        {
            myD.add(myC.get(i).modPow(d, n));
        }
    }

    public Vector<BigInteger> getMyD()
    {
        return myD;
    }

    public Vector<BigInteger> getMyC()
    {
        return myC;
    }

    public Vector<BigInteger> getMyI()
    {
        return myI;
    }

    public void setInput(String s)
    {
        input = s;
    }
}
