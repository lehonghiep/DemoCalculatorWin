/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calactor.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Hong Hiep
 */
public class LuuDocFileIO {

    public String docFile() {
        String s="";
        try {
            FileInputStream fis = new FileInputStream("History/History.txt");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            while (br.ready()) {
               String line = br.readLine();
               s=line+"\n"+s;
            }
            br.close();
            isr.close();
            fis.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return s;
    }

    public Boolean luuFile(String s, Boolean kt) {
        try {
            FileOutputStream fos = new FileOutputStream("History/History.txt", kt);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(s);
            bw.newLine();

            bw.close();
            osw.close();
            fos.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
