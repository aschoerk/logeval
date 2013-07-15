package de.aschoerk.logeval.extraction;

import de.aschoerk.logeval.FieldExtractionConfig;
import de.aschoerk.logeval.IFieldReplacer;
import de.aschoerk.logeval.LogEvalConfig;
import de.aschoerk.logeval.ReplacementContext;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class BaseLineHandler implements LineHandler {
    protected LogEvalConfig config;
    protected String node;
    protected HashMap fieldResults = new HashMap();
    ReplacementContext context;
    protected BaseLineHandler(LogEvalConfig config, ReplacementContext context) {
        this.config = config;
        this.context = context;
    }
    public void handleLine(String line) {
        fieldResults.clear();
        context.setLine(line);
        for (int i = 0; i < config.getFieldExtractionConfigs().length; i++) {
            handleField(line, i);
        }
    }
    protected Matcher extractFieldValue(String line, FieldExtractionConfig config) {
        Pattern lineRecognizer = config.getLineRecognizerPattern();
        if (lineRecognizer != null && !lineRecognizer.matcher(line).find()) {
            return null;
        }
        Pattern pattern = config.getFieldRecognizerPattern();
        return pattern.matcher(line);
    }
    protected void replaceFieldValues(Matcher fieldValue, FieldExtractionConfig config, StringBuffer dest) {
      context.setMatcher(fieldValue);
      IFieldReplacer fieldReplacer = config.getFieldReplacer();
      if (fieldReplacer == null) {
          fieldReplacer = new FieldReplacer(config.getReplacePattern());
          config.setFieldReplacer(fieldReplacer);
      }
      fieldReplacer.doReplacement(context, dest);
    }
    public void handleField(String line, int fno) {
        FieldExtractionConfig feConfig = config.getFieldExtractionConfigs()[fno];
        Matcher matcher = extractFieldValue(line,feConfig);
        if (matcher != null && matcher.find()) {
            StringBuffer sb = new StringBuffer();
            replaceFieldValues(matcher,feConfig,sb);
            fieldResults.put(feConfig.getFieldName(),sb.toString());
        }
    }

    
    public void ready() {
        
    }
}