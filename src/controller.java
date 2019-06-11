import java.util.*;
import java.math.BigInteger;

/**
 * controller class for RSA project
 * 
 * @author Yizheng Wang
 */
public class controller
{
    model myModel;
    view  myView;

    public controller()
    {
        myModel = new model();
        myView = new view(this);
    }

    public void encryption()
    {
        myView.clearOutput();
        myModel.setInput(myView.getInput());
        myModel.stringToNum();
        Vector<BigInteger> temp = myModel.getMyI();
        myView.setOutput("ASCII code(block with '**'): " + "\r\n");
        StringBuilder sba = new StringBuilder();
        StringBuilder sbe = new StringBuilder();
        for (int i = 0; i < temp.size(); i++)
        {
            if (temp.get(i).toString().length() == 11)
            {
                sba.append(0);
            }
            sba.append(temp.get(i).toString());
            sba.append("**");
        }
        myView.setOutput(sba.toString() + "\r\n");
        myModel.encryption();
        temp = myModel.getMyC();
        myView.setOutput("Encryption(block with '**'): " + "\r\n");
        
        for (int i = 0; i < temp.size(); i++)
        {

            if (temp.get(i).toString().length() == 11)
            {
                int c = Integer
                        .parseInt(temp.get(i).toString().substring(0, 2));
                char test = (char) c;
                sbe.append(test);
                for (int j = 2; j < 11; j += 3)
                {
                    c = Integer.parseInt(
                            temp.get(i).toString().substring(j, j + 3));
                    test = (char) c;
                    sbe.append(test);
                }
            } else
            {
                for (int j = 0; j < 12; j += 3)
                {
                    int c = Integer.parseInt(
                            temp.get(i).toString().substring(j, j + 3));

                    sbe.append((char) c);

                }
            }
        }

        /*
        for (int i = 0; i < temp.size(); i++)
        {
            sbe.append(temp.get(i).toString());
            sbe.append("**");
        }
        */
        myView.setOutput(sbe.toString() + "\r\n");

    }

    public void decryption()
    {
        myView.clearOutput();
        myModel.decryption();
        Vector<BigInteger> temp = myModel.getMyD();
        StringBuilder sbd = new StringBuilder();
        StringBuilder sbm = new StringBuilder();
        myView.setOutput("Decryption: (block with '**')" + "\r\n");
        for (int i = 0; i < temp.size(); i++)
        {
            if (temp.get(i).toString().length() == 11)
            {
                sbd.append(0);
            }
            sbd.append(temp.get(i).toString());
            sbd.append("**");
        }
        myView.setOutput(sbd.toString() + "\r\n");

        myView.setOutput("Message: " + "\r\n");
        for (int i = 0; i < temp.size(); i++)
        {

            if (temp.get(i).toString().length() == 11)
            {
                int c = Integer
                        .parseInt(temp.get(i).toString().substring(0, 2));
                char test = (char) c;
                sbm.append(test);
                for (int j = 2; j < 11; j += 3)
                {
                    c = Integer.parseInt(
                            temp.get(i).toString().substring(j, j + 3));
                    test = (char) c;
                    sbm.append(test);
                }
            } else
            {
                for (int j = 0; j < 12; j += 3)
                {
                    int c = Integer.parseInt(
                            temp.get(i).toString().substring(j, j + 3));

                    sbm.append((char) c);

                }
            }
        }

        myView.setOutput(sbm.toString() + "\r\n");

    }

    public static void main(String[] args)
    {
        new controller();
    }

}
