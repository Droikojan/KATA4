package kata4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class KATA4 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = "C:\\Users\\usuario\\Documents";
        File file1 = new File(path);
        
        String from = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Lighthouse.jpg";
        String to = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Lighthouse (1).jpg";
        
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(new File(from)));
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(new File(to)));
        Date date = new Date();
        byte [] buffer = new byte [1024];
        
        while(true){
            int read = input.read(buffer);
            if(read < 0) break;
            output.write(buffer);
        }
        System.out.println(new Date().getTime() - date.getTime() + " msg");
        input.close();
        output.flush();
        output.close();
        //print (file1.listFiles(),"");
        
        /*
        String[] files = file.list();
        for (String file1 : files) {
            System.out.println(file1);
        
        }
        */
    }
    
    private static void print(File[] listFiles, String ident) {
        if (listFiles == null) {
            return;
        }
        
        for (File listFile : listFiles) {
            System.out.println(ident + (listFile.isDirectory()?"+":" ") + listFile.getName());
            
            if (!listFile.isDirectory() || listFile.isHidden()){
                continue;
            }
            print (listFile.listFiles(), ident+" ");
        }
    }
    
}
