package de.aschoerk.logeval;

import de.aschoerk.logeval.extraction.BaseLineHandler;
import de.aschoerk.logeval.extraction.LineHandler;
import de.aschoerk.logeval.extraction.LineHandlerException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;


class TextFileCreator extends BaseLineHandler implements LineHandler {
    FileWriter fw;
    ArrayList fieldnames = new ArrayList();
    void prepareOutputConfig() {
        StringTokenizer stok = new StringTokenizer(config.getOutputFileFields(),", ");
        while (stok.hasMoreTokens()) {
            String fname = stok.nextToken();
            fieldnames.add(fname);
        }        
    }
    
    public TextFileCreator(LogEvalConfig config, ReplacementContext context) {
        super(config, context);
        prepareOutputConfig();
    }
    
    public void startWriting() {
        File f = new File(config.getOutputFileName());
        try {
            fw = new FileWriter(f);
            if (config.isIncludeHeader()) {
                boolean first = true;
                for (Iterator it = fieldnames.iterator(); it.hasNext();) {
                    Object fname =  it.next();
                    if (fieldResults.containsKey(fname)) {
                        if (first)
                            first = false;
                        else
                            sb.append(config.getOutputFileFieldSeparator());
                        sb.append(fname);
                    }
                }
                fw.write(sb.toString());                
                sb.delete(0,sb.length());
            }
        }  catch (IOException e) {
            throw new LineHandlerException(e);
        }
    }
    StringBuffer sb = new StringBuffer();
    public void handleLine(String line) {
        super.handleLine(line);
        boolean first = true;
        for (Iterator it = fieldnames.iterator(); it.hasNext();) {
            Object fname =  it.next();
            if (fieldResults.containsKey(fname)) {
                if (first)
                    first = false;
                else
                    sb.append(config.getOutputFileFieldSeparator());
                sb.append(fieldResults.get(fname));
            }
        }
        try {
            if (sb.length() > 0) {
                fw.write(sb.toString());
                fw.write("\n");
                sb.delete(0,sb.length());
            }
        } catch (IOException e) {
            throw new LineHandlerException("Writing: " + sb.toString() + " on " + config.getOutputFileName(), e);
        }
    }
    
    public void ready() {
        try {
            fw.close();
        } catch (IOException e) {
            throw new LineHandlerException(e);
        }
    }
    
}