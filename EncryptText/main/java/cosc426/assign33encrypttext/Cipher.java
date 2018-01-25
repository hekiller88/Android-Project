package cosc426.assign33encrypttext;

/**
 * Created by lhe on 11/10/17.
 */

public class Cipher {

    private int key;            //key value
    private String text;        //input text

    public static final int ENCRYPT = 0;
    public static final int DECRYPT = 1;


    private static final String alphabet
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Cipher()
    {
        key = 0;
        text = "";
    }

    public void setKey(int key)
    {
        this.key = key;
    }

    public int getKey()
    {
        return key;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    public char keyShift(char a, int flag)
    {
        //error check: if a is a alphabet
        if(!alphabet.contains(String.valueOf(a))
                || (flag != ENCRYPT && flag != DECRYPT) )
            throw new IllegalArgumentException("Invalid Key Shift");

        //flag = true: encrypt
        //flag = false: decrpt
        char out;
        if(flag == ENCRYPT)
        {
            int i = alphabet.indexOf(a);
            out = alphabet.charAt( (i + key) %52);
        }
        else
        {
            int i = alphabet.indexOf(a);
            int tmp = i - key % 52;
            // e.g. i is 1, key%52 is 10,
            // leftshit will end in 52 + (1 - 10) = 41
            if(tmp < 0)
                tmp = 52 + tmp;
            out = alphabet.charAt(tmp);
        }

        return out;
    }

    public String encrypts()
    {

        String output = "";

        for(int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);

            //only shift alphabet
            if(alphabet.contains(String.valueOf(c)))
                output += keyShift(c, ENCRYPT);
            else
                output += c;

        }

        return text = output;
    }

    public String decrypts()
    {

        String output = "";

        for(int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);

            //only shift alphabet
            if(alphabet.contains(String.valueOf(c)))
                output += keyShift(c, DECRYPT);
            else
                output += c;
        }

        return text = output;
    }

}
