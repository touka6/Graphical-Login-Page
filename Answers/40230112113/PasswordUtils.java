import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.regex.Pattern;

public class PasswordUtils
{

    public String SHA_256(String password)
    {
        //we could also use bouncy castle library to hash but we need to download it first
        //bouncy castle library provides a utility class for converting hex data to bytes and back again :>
        //there is also sth related to maven which i don't understand
        String s="";
        block2 : try
        {
            MessageDigest digesting = MessageDigest.getInstance("SHA-256");
            byte[] hashing = digesting.digest(password.getBytes(StandardCharsets.UTF_8));
            s = converter(hashing);
            break block2;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return s;
    }

    private static String converter(byte[] hashing)
    {
        StringBuilder hex1 = new StringBuilder(2*hashing.length);
        for (int i=1;i<=hashing.length;i++)
        {
            String hex2=Integer.toHexString(0xff & hashing[i]);
            if(hex2.length()==1)
            {
                hex1.append('0');
            }
            hex1.append(hex1);
        }
        return hex1.toString();
    }

    public int checkpassword(String password)
    {
        int t=0;

        boolean s1 = Pattern.compile("[a-z]").matcher(password).find();
        boolean s2 = Pattern.compile("[A-Z]").matcher(password).find();
        boolean s3 = Pattern.compile("[0-9]").matcher(password).find();
        boolean s4 = Pattern.compile("[.@_-]").matcher(password).find();

        if (s1&&s2&&s3&&s4&&(password.length()>=8))
        {
            t=5;
        }
        else if(s1&&s2&&s3&&s4)
        {
            t=4;
        }
        else if(s1&&s2&&s3)
        {
            t=3;
        }
        else if((s1&&s2)||(s3&&s2)||(s1&&s3))
        {
            t=2;
        }
        else
        {
            t=1;
        }
        return t;
    }
}
