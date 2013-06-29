/*
 * LogEvalConfig.java
 *
 * Created on 23. Februar 2007, 16:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ptvag.logeval;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

/**
 *
 * @author xzas
 */
public class LogEvalConfig implements Constants {
    /** Creates a new instance of LogEvalConfig */
    public LogEvalConfig() {
    }
    private String dbUser;
    private String dbPassword;
    private String dbUrl;
    private String jdbcDriverClass;
    private String logDir;
    private String logRecognizer;
    private String nodeRecognizer;
    private boolean removeOlderDuplicates;
    private String outputFileName;
    private String outputFileFieldSeparator;
    private String outputFileFields;
    private FieldExtractionConfig[] fieldExtractionConfigs;
    private boolean includeHeader;
    
    private String testLine; 
    private String testRegex;
    private String testReplace;
    
    private boolean allowToCreateTable;
    private boolean truncIfExistsTable;
    private boolean forceCreateTable;
    private String transactionSize;
    private String batchSize;
    private String maxVarcharSize;
    private String destTableName;

    public String getDbPassword() {
        return dbPassword;
    }
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
    public String getDbUrl() {
        return dbUrl;
    }
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }
    public String getDbUser() {
        return dbUser;
    }
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }
    public String getJdbcDriverClass() {
        return jdbcDriverClass;
    }
    public void setJdbcDriverClass(String jdbcDriverClass) {
        this.jdbcDriverClass = jdbcDriverClass;
    }
    public String getLogDir() {
        return logDir;
    }
    public void setLogDir(String logDir) {
        this.logDir = logDir;
    }
    public String getLogRecognizer() {
        return logRecognizer;
    }
    public void setLogRecognizer(String logRecognizer) {
        this.logRecognizer = logRecognizer;
    }
    public String getNodeRecognizer() {
        return nodeRecognizer;
    }
    public void setNodeRecognizer(String nodeRecognizer) {
        this.nodeRecognizer = nodeRecognizer;
    }
    public String getOutputFileName() {
        return outputFileName;
    }
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
    public String getOutputFileFieldSeparator() {
        return outputFileFieldSeparator;
    }
    public void setOutputFileFieldSeparator(String outputFileFieldSeparator) {
        this.outputFileFieldSeparator = outputFileFieldSeparator;
    }
    public boolean isIncludeHeader() {
        return includeHeader;
    }
    public void setIncludeHeader(boolean includeHeader) {
        this.includeHeader = includeHeader;
    }
    public FieldExtractionConfig[] getFieldExtractionConfigs() {
        return fieldExtractionConfigs;
    }
    public void setFieldExtractionConfigs(
            FieldExtractionConfig[] fieldExtractionConfigs) {
        this.fieldExtractionConfigs = fieldExtractionConfigs;
    }
    
    public void toProperties(Properties props) {
        props.setProperty(DBURL, dbUrl);
        props.setProperty(DBUSER,dbUser);
        props.setProperty(DBPASSWORD,dbPassword);
        props.setProperty(JDBCDRIVERCLASS,jdbcDriverClass);
        props.setProperty(LOGDIR,logDir);
        props.setProperty(LOGRECOGNIZER, logRecognizer);
        props.setProperty(NODERECOGNIZER,nodeRecognizer);
        props.setProperty(OUTPUTFILEFIELDS,outputFileFields);
        props.setProperty(OUTPUTFILEINSERTHEADER,includeHeader ? "true" : "false");
        props.setProperty(OUTPUTFILENAME,outputFileName);
        props.setProperty(OUTPUTFILEFIELDSEPARATOR,outputFileFieldSeparator);

        props.setProperty(TESTLINE, getTestLine());
        props.setProperty(TESTREGEX, getTestRegex());
        props.setProperty(TESTREPLACE, getTestReplace());
        
        props.setProperty(ALLOWTOCREATETABLE,allowToCreateTable ? "true" : "false");
        props.setProperty(TRUNCIFEXISTSTABLE,truncIfExistsTable ? "true" : "false");
        props.setProperty(DBTRASIZE, getTransactionSize());
        props.setProperty(DBBATCHSIZE, getBatchSize());
        props.setProperty(DESTTABLENAME, getDestTableName());
        props.setProperty(MAXVARCHARSIZE, getMaxVarcharSize());
        props.setProperty(FORCECREATETABLE, isForceCreateTable() ? "true" : "false");
        props.setProperty(REMOVEOLDERDUPLICATES, isRemoveOlderDuplicates() ? "true" : "false");
        
        
        for (int i = 1; i <= this.fieldExtractionConfigs.length; i++) {
            FieldExtractionConfig feConfig = fieldExtractionConfigs[i-1];
            feConfig.toProperties(props, i);
        }
    }
    
    public void fromProperties(Properties props) {
        if (props.containsKey(DBPASSWORD))
            dbPassword = props.getProperty(DBPASSWORD);
        if (props.containsKey(DBURL))
            dbUrl = props.getProperty(DBURL);
        if (props.containsKey(DBUSER))
            dbUser = props.getProperty(DBUSER);
        if (props.containsKey(JDBCDRIVERCLASS))
            jdbcDriverClass = props.getProperty(JDBCDRIVERCLASS);
        if (props.containsKey(ALLOWTOCREATETABLE))
            allowToCreateTable = props.getProperty(ALLOWTOCREATETABLE).equalsIgnoreCase("true") ? true : false;
        if (props.containsKey(TRUNCIFEXISTSTABLE))
            truncIfExistsTable = props.getProperty(TRUNCIFEXISTSTABLE).equalsIgnoreCase("true") ? true : false;
        if (props.containsKey(NODERECOGNIZER))
            nodeRecognizer = props.getProperty(NODERECOGNIZER);
        if (props.containsKey(LOGRECOGNIZER))
            logRecognizer = props.getProperty(LOGRECOGNIZER);
        if (props.containsKey(LOGDIR))
            logDir = props.getProperty(LOGDIR);
        if (props.containsKey(REMOVEOLDERDUPLICATES))
            removeOlderDuplicates = props.getProperty(REMOVEOLDERDUPLICATES).equalsIgnoreCase("true") ? true : false;
        if (props.containsKey(OUTPUTFILEFIELDS))
            outputFileFields = props.getProperty(OUTPUTFILEFIELDS);
        if (props.containsKey(OUTPUTFILEINSERTHEADER))
            includeHeader = props.getProperty(OUTPUTFILEINSERTHEADER).equalsIgnoreCase("true") ? true : false;
        if (props.containsKey(OUTPUTFILENAME))
            outputFileName = props.getProperty(OUTPUTFILENAME);
        if (props.containsKey(OUTPUTFILEFIELDSEPARATOR))
            outputFileFieldSeparator = props.getProperty(OUTPUTFILEFIELDSEPARATOR);
        if (props.containsKey(TESTLINE))
            setTestLine(props.getProperty(TESTLINE));
        if (props.containsKey(TESTREGEX))
            setTestRegex(props.getProperty(TESTREGEX));
        if (props.containsKey(TESTREPLACE))
            setTestReplace(props.getProperty(TESTREPLACE));
        if (props.containsKey(DBTRASIZE))
            setTransactionSize(props.getProperty(DBTRASIZE));
        if (props.containsKey(DBBATCHSIZE))
            setBatchSize(props.getProperty(DBBATCHSIZE));
        if (props.containsKey(DESTTABLENAME))
            setDestTableName(props.getProperty(DESTTABLENAME));
        if (props.containsKey(MAXVARCHARSIZE))
            setMaxVarcharSize(props.getProperty(MAXVARCHARSIZE));
        if (props.containsKey(FORCECREATETABLE))
            setForceCreateTable(props.getProperty(FORCECREATETABLE).equalsIgnoreCase("true") ? true : false);
        ArrayList feConfigs = new ArrayList();
        int count = 1;
        do {
            FieldExtractionConfig feConfig = new FieldExtractionConfig();
            if (feConfig.fromProperties(props, count)) {
                feConfigs.add(feConfig);
            } else break;
            count++;
        }
        while (true);
        count = 0;
        this.fieldExtractionConfigs = new FieldExtractionConfig[feConfigs.size()];
        for (Iterator it = feConfigs.iterator(); it.hasNext();) {
            FieldExtractionConfig elem = (FieldExtractionConfig) it.next();
            fieldExtractionConfigs[count++] = elem;
        }
    }
    
    public void toConfigFile(File f) throws IOException {
        Properties props = new Properties();
        toProperties(props);
        FileOutputStream fo = new FileOutputStream(f);
        props.store(fo,"Konfig for LogEvaluation: " +  f.getPath());
        fo.close();
    }
    
    public void fromConfigFile(File f) throws IOException {
        Properties props = new Properties();
        FileInputStream fi = new FileInputStream(f);
        props.load(fi);
        fi.close();
        fromProperties(props);
        
    }

    public String getOutputFileFields() {
        return outputFileFields;
    }

    public void setOutputFileFields(String outputFileFields) {
        this.outputFileFields = outputFileFields;
    }

    public String getTestLine() {
        return testLine;
    }

    public void setTestLine(String testLine) {
        this.testLine = testLine;
    }

    public String getTestRegex() {
        return testRegex;
    }

    public void setTestRegex(String testRegex) {
        this.testRegex = testRegex;
    }

    public String getTestReplace() {
        return testReplace;
    }

    public void setTestReplace(String testReplace) {
        this.testReplace = testReplace;
    }

    public boolean isAllowToCreateTable() {
        return allowToCreateTable;
    }

    public void setAllowToCreateTable(boolean allowToCreateTable) {
        this.allowToCreateTable = allowToCreateTable;
    }

    public boolean isTruncIfExistsTable() {
        return truncIfExistsTable;
    }

    public void setTruncIfExistsTable(boolean truncIfExistsTable) {
        this.truncIfExistsTable = truncIfExistsTable;
    }

    public String getTransactionSize() {
        return transactionSize;
    }

    public void setTransactionSize(String transactionSize) {
        this.transactionSize = transactionSize;
    }

    public String getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(String batchSize) {
        this.batchSize = batchSize;
    }

    public String getDestTableName() {
        return destTableName;
    }

    public void setDestTableName(String destTableName) {
        this.destTableName = destTableName;
    }

    public boolean isForceCreateTable() {
        return forceCreateTable;
    }

    public void setForceCreateTable(boolean forceCreateTable) {
        this.forceCreateTable = forceCreateTable;
    }

    public String getMaxVarcharSize() {
        return maxVarcharSize;
    }

    public void setMaxVarcharSize(String maxVarcharSize) {
        this.maxVarcharSize = maxVarcharSize;
    }

    public boolean isRemoveOlderDuplicates() {
        return removeOlderDuplicates;
    }

    public void setRemoveOlderDuplicates(boolean removeOlderDuplicates) {
        this.removeOlderDuplicates = removeOlderDuplicates;
    }
    
    
}
