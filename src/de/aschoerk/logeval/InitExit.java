/*
 * InitExit.java
 *
 * Created on 26. Februar 2007, 09:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.aschoerk.logeval;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author xzas
 */
public class InitExit {
    
    /** Creates a new instance of InitExit */
    public InitExit() {
    }
    static String PROPERTIES = "logeval.properties";
    static String CONFIGDIR = "configdir";
    static String CURRENTCONFIG = "currentconfig";
    
    static File propertyFile() {
        File f = new File(System.getProperty("user.home") + File.separator + "logeval");
        f.mkdir();
        return new File(f,PROPERTIES);
    }
    public static void exit(MainFrame frame) {
        Properties props = new Properties();
        props.setProperty(CONFIGDIR, frame.getKonfigDirTextField().getText());
        props.setProperty(CURRENTCONFIG,frame.getKonfigNameTextField().getText());    
        try {
          props.store(new FileOutputStream(propertyFile()),"parameters for log-evaluation tool");        
        }
        catch (IOException e) {
            frame.println(e.toString());
        }
    }
    public static void init(MainFrame frame) {
        Properties props = new Properties();
        try {            
            props.load(new FileInputStream(propertyFile()));
        } catch (FileNotFoundException ex) {
            frame.println(ex.toString());
        } catch (IOException ex) {
            frame.println(ex.toString());
        }
        if (props.containsKey(CONFIGDIR))
            frame.getKonfigDirTextField().setText(props.getProperty(CONFIGDIR));
        if (props.containsKey(CURRENTCONFIG)) {
            frame.setCurrentConfig(props.getProperty(CURRENTCONFIG));
        }
    }
    
}
