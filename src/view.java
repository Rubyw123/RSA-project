import java.lang.reflect.Method;
import javax.swing.*;

/**
 * view class for RSA project
 * 
 * @author Yizheng Wang
 */
public class view extends JFrame
{
    private JPanel         myPanel;
    private JTextField     myInput;
    private JTextArea      myOutput;
    private JButton        myEncry;
    private JButton        myDecry;
    private JLabel         input;
    private JLabel         output;
    private controller     mycontroller;
    private ButtonListener myEncryListener;
    private ButtonListener myDecryListener;

    public view(controller myc)
    {
        // controller
        mycontroller = myc;

        // Frame
        this.setSize(440, 400); 
        this.setTitle("RSA Project");
        this.setResizable(false);

        // Panel
        myPanel = new JPanel();
        myPanel.setSize(440, 400);
        myPanel.setLayout(null);

        myInput = new JTextField();
        myInput.setSize(400, 25);
        myInput.setLocation(10, 50);

        myOutput = new JTextArea();
        myOutput.setSize(400, 150);
        myOutput.setLocation(10, 100);
        myOutput.setLineWrap(true);
        myOutput.setWrapStyleWord(true);
        myOutput.setEditable(false);

        input = new JLabel("Please type the message you want encrypt: ");
        input.setLocation(10, 50);
        input.setVisible(true);

        output = new JLabel("Output: ");
        output.setLocation(0, 50);
        output.setVisible(true);
        output.setOpaque(true);

        myEncry = new JButton("Encrypt");
        myEncry.setSize(80, 25);
        myEncry.setLocation(125, 300);
        this.associateListeners1(mycontroller);

        myDecry = new JButton("Decrypt");
        myDecry.setSize(80, 25);
        myDecry.setLocation(225, 300);
        this.associateListeners2(mycontroller);

        // addPanel
        myPanel.add(myInput);
        myPanel.add(myOutput);
        myPanel.add(input);
        myPanel.add(output);
        myPanel.add(myEncry);
        myPanel.add(myDecry);
        this.add(myPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getInput()
    {

        return myInput.getText();
    }

    public void setOutput(String o)
    {
        myOutput.append(o);;
    }

    public void clearOutput()
    {
        myOutput.setText(null);
    }

    /**
     * associateListeners for encryption button
     */
    private void associateListeners1(controller myC)
    {
        Class<? extends controller> controllerClass;
        Method encryptionMethod;

        controllerClass = mycontroller.getClass();

        encryptionMethod = null;

        try
        {
            encryptionMethod = controllerClass.getMethod("encryption",
                    (Class<?>[]) null);
        } catch (SecurityException e)
        {
            String error;

            error = e.toString();
            System.out.println(error);
        } catch (NoSuchMethodException e)
        {
            String error;

            error = e.toString();
            System.out.println(error);
        }

        myEncryListener = new ButtonListener(mycontroller, encryptionMethod,
                null);

        myEncry.addMouseListener(myEncryListener);
    }

    /**
     * associateListeners for decryption button
     */
    private void associateListeners2(controller myC)
    {
        Class<? extends controller> controllerClass;
        Method decryptionMethod;

        controllerClass = mycontroller.getClass();

        decryptionMethod = null;

        try
        {
            decryptionMethod = controllerClass.getMethod("decryption",
                    (Class<?>[]) null);
        } catch (SecurityException e)
        {
            String error;

            error = e.toString();
            System.out.println(error);
        } catch (NoSuchMethodException e)
        {
            String error;

            error = e.toString();
            System.out.println(error);
        }

        myDecryListener = new ButtonListener(mycontroller, decryptionMethod,
                null);

        myDecry.addMouseListener(myDecryListener);
    }
}
