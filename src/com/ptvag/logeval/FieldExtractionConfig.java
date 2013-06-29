/**
 *
 */
package com.ptvag.logeval;

import java.util.Properties;
import java.util.regex.Pattern;

public class FieldExtractionConfig implements Constants {
    private String fieldName;
    private String fieldType;
    private String fieldLength;
    private String fieldRecognizer;
    private String lineRecognizer;
    private String replacePattern;
    public String getFieldLength() {
        return fieldLength;
    }
    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldRecognizer() {
        return fieldRecognizer;
    }
    public void setFieldRecognizer(String fieldRecognizer) {
        this.fieldRecognizer = fieldRecognizer;
    }
    public String getFieldType() {
        return fieldType;
    }
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
    public String getLineRecognizer() {
        return lineRecognizer;
    }
    public void setLineRecognizer(String lineRecognizer) {
        this.lineRecognizer = lineRecognizer;
    }
    public String getReplacePattern() {
        return replacePattern;
    }
    public void setReplacePattern(String replacePattern) {
        this.replacePattern = replacePattern;
    }
    private Pattern fieldRecognizerPattern;
    public Pattern getFieldRecognizerPattern() {
        if (fieldRecognizerPattern == null) {
            fieldRecognizerPattern = Pattern.compile(fieldRecognizer);            
        }
        return fieldRecognizerPattern;
    }
    
    private Pattern lineRecognizerPattern;
    public Pattern getLineRecognizerPattern() {
        if (lineRecognizerPattern == null) {
            if (lineRecognizer.length() == 0)
                return null;
            lineRecognizerPattern = Pattern.compile(lineRecognizer);            
        }
        return lineRecognizerPattern;
    }
    
    public void toProperties(Properties props, int extractorCount) {
        props.setProperty(FIELDNAME + extractorCount,fieldName);
        props.setProperty(FIELDLENGTH + extractorCount,fieldLength);
        props.setProperty(FIELDRECOGNIZER + extractorCount,fieldRecognizer);
        props.setProperty(FIELDTYPE + extractorCount,fieldType);
        props.setProperty(LINERECOGNIZER + extractorCount,lineRecognizer);
        props.setProperty(REPLACEPATTERN+extractorCount,replacePattern);
    }
    
    public boolean fromProperties(Properties props, int extractorCount) {
        if (props.containsKey(FIELDNAME + extractorCount)) {
            fieldName = props.getProperty(FIELDNAME + extractorCount);
            fieldLength = props.getProperty(FIELDLENGTH + extractorCount);
            fieldRecognizer = props.getProperty(FIELDRECOGNIZER + extractorCount);
            fieldType = props.getProperty(FIELDTYPE + extractorCount);
            lineRecognizer = props.getProperty(LINERECOGNIZER + extractorCount);
            replacePattern = props.getProperty(REPLACEPATTERN + extractorCount);
            return true;
        } else return false;        
    }
    
    private IFieldReplacer fieldReplacer;

    public IFieldReplacer getFieldReplacer() {
        return fieldReplacer;
    }

    public void setFieldReplacer(IFieldReplacer fieldReplacer) {
        this.fieldReplacer = fieldReplacer;
    }
    
}