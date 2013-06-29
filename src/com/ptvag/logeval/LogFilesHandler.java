/*
 * LogFilesHandler.java
 *
 * Created on 23. Februar 2007, 10:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ptvag.logeval;

import com.ptvag.logeval.extraction.BaseLineHandler;
import com.ptvag.logeval.extraction.LineHandler;
import com.ptvag.logeval.extraction.LineHandlerException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author xzas
 */
public class LogFilesHandler {
    LogEvalConfig config;
    Pattern nodeRecognizerPattern;
    Pattern logFileRecognizerPattern;
    ReplacementContext context;
    IOutputter outputter;
    
    /** Creates a new instance of LogFilesHandler */
    public LogFilesHandler(LogEvalConfig config, IOutputter outputter, ReplacementContext context) {
        this.config = config;
        nodeRecognizerPattern = Pattern.compile(config.getNodeRecognizer());
        logFileRecognizerPattern = Pattern.compile(config.getLogRecognizer());
        this.outputter = outputter;
        this.context = context;
    }
    
    
    static class FilePair implements Comparable {
        File f1;
        File f2;
        FilePair(File f1, File f2) {
            if (f1.lastModified() < f2.lastModified()) {
                this.f1 = f1;
                this.f2 = f2;
            } else {
                this.f1 = f2;
                this.f2 = f1;
            }
        }
        FilePair(Object f1, Object f2) {
            this((File)f1,(File)f2);
        }
        public int compareTo(Object o) {
            FilePair fp = (FilePair)o;
            int res1 = f1.compareTo(fp.f2);
            if (res1 == 0) {
                return f2.compareTo(fp.f2);
            } else
                return res1;
        }
    }
    
    private HashSet sortDup(ArrayList al) {
        final HashSet duplicates = new HashSet();
        Collections.sort(al,new Comparator() {
            public int compare(Object o1, Object o2) {
                int res = ((File)o1).getName().compareTo(((File)o2).getName());
                if (res == 0) {
                    FilePair fp = new FilePair(o1,o2);
                    if (!duplicates.contains(fp))
                        duplicates.add(fp);
                }
                return res;
            }
        });
        return duplicates.size() > 0 ? duplicates : null;
    }
    
    private void dupRemove(ArrayList al, HashSet s) {
        if (s != null)
            for (Iterator it = s.iterator(); it.hasNext();) {
                FilePair fp = (FilePair) it.next();
                if (config.isRemoveOlderDuplicates()) {
                    al.remove(fp.f1);
                    outputter.println("removed duplicate: " + fp.f1.getPath() + "\n");
                } else {
                    outputter.println("should remove duplicate: " + fp.f1.getPath() + "\n");
                }
            }
    }
    
    StringBuffer showFiles() {
        File f = new File(config.getLogDir());
        HashMap res = new HashMap();
        collectFiles(f,res);
        StringBuffer files = new StringBuffer();
        for (Iterator it = res.keySet().iterator(); it.hasNext();) {
            String node = (String) it.next();
            ArrayList al = (ArrayList)res.get(node);
            HashSet duplicates = sortDup(al);
            for (Iterator alit = al.iterator(); alit.hasNext();) {
                File currentf = (File) alit.next();
                files.append(node);
                files.append(": ");
                files.append(currentf.getName());
                files.append("\n");
            }
            if (duplicates != null && duplicates.size() > 0) {
                files.append("Duplicates: \n");
                for (Iterator dupit = duplicates.iterator(); dupit.hasNext();) {
                    FilePair fp = (FilePair) dupit.next();
                    files.append(fp.f1.getPath());
                    files.append(" ****|**** ");
                    files.append(fp.f2.getPath());
                    files.append("\n");
                }
            }
        }
        return files;
    }
    
    
    
    void collectFiles(File f, HashMap filesPerNode) {
        if (f.isDirectory()) {
            File[] files = f.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    return pathname.isDirectory() || logFileRecognizerPattern.matcher(pathname.getName()).matches();
                }
            });
            for (int i = 0; i < files.length; i++) {
                collectFiles(files[i],filesPerNode);
            }
        } else {
            String absPath = f.getAbsolutePath();
            Matcher matcher = nodeRecognizerPattern.matcher(absPath);
            if (matcher.find()) {
                String node = absPath.substring(matcher.start(), matcher.end());
                ArrayList fileList;
                if (!filesPerNode.containsKey(node)) {
                    fileList = new ArrayList();
                    filesPerNode.put(node,fileList);
                } else fileList = (ArrayList)filesPerNode.get(node);
                fileList.add(f);
            }
        }
    }
    
    protected void handleFiles(LineHandler lineHandler) {
        File f = new File(config.getLogDir());
        HashMap filesPerNode = new HashMap();
        collectFiles(f,filesPerNode);
        for (Iterator it = filesPerNode.keySet().iterator(); it.hasNext();) {
            String node = (String)it.next();
            ArrayList al = (ArrayList)filesPerNode.get(node);
            HashSet duplicates = sortDup(al);
            dupRemove(al, duplicates);
        }
        for (Iterator it = filesPerNode.keySet().iterator(); it.hasNext();) {
            String node = (String)it.next();
            ArrayList fileList = (ArrayList) filesPerNode.get(node);
            context.setNode(node);
            for (Iterator fileit = fileList.iterator(); fileit.hasNext();) {
                f = (File) fileit.next();
                outputter.println("start extracting: " + f.getPath());
                
                BufferedReader br = null;
                try {
                    if (f.getPath().toLowerCase().endsWith(".gz")) {
                        GZIPInputStream is = new GZIPInputStream(new FileInputStream(f));
                        InputStreamReader reader = new InputStreamReader(is);
                        br  = new BufferedReader(reader);
                        
                    } else {
                        FileReader reader = new FileReader(f);
                        br = new BufferedReader(reader);
                    }
                    String line;
                    do {
                        line = br.readLine();
                        if (line != null)
                            try {
                                lineHandler.handleLine(line);
                            } catch(Exception e) {
                                outputter.print(e.toString());
                            }
                    }
                    while (line != null);
                } catch (IOException e) {
                    outputter.print(e.toString());
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException ex) {
                            outputter.println(ex.toString());
                        }
                    }
                }
            }
        }
    }
    
    void extractFilesToFileSystem()  {
        context = new ReplacementContext();
        TextFileCreator fileCreator = new TextFileCreator(config, context);
        extractFiles(fileCreator);
    }
    
    Thread handlerThread = null;
    void extractFiles(final LineHandler handler) {
        if (handlerThread != null)
            throw new LogEvaluatorException("operation already started");
        Thread handlerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    outputter.println("start extracting");
                    handler.startWriting();
                    handleFiles(handler);
                    handler.ready();
                    outputter.println("ready extracting");
                } catch (Throwable thw) {
                    outputter.println("Handler thread interrupted because of: " + thw.toString());
                }
            }
        });
        handlerThread.start();
    }
}
