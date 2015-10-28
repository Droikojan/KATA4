package kata4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MailListReader {

    public static ArrayList<String> read(String name) throws FileNotFoundException, IOException {

        ArrayList<String> mailList = new ArrayList<>();
        BufferedReader read = new BufferedReader(new FileReader(new File(name)));

        String mail;
        while ((mail = read.readLine()) != null) {
            if (mail.contains("@")) {
                mailList.add(mail);
            }
        }
        return mailList;
    }
}
