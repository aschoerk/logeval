/*
 * FieldReplacer.java
 *
 * Created on 24. Februar 2007, 07:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ptvag.logeval.extraction;

import com.ptvag.logeval.IFieldReplacer;
import com.ptvag.logeval.ReplacementContext;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

/**
 *
 * @author schoerk
 */
public class FieldReplacer implements IFieldReplacer {
    static String ESCAPEDLOWER = "!ESCAPEDLOWERSIGN!";
    static String ESCAPEDGREATER = "!ESCAPEDGREATERSIGN!";
    
    interface Op {
        void doOp(StringBuffer sb);
    }
    class InsertTextOp implements Op {
        String text;
        InsertTextOp(String text) {
            this.text = text.replaceAll(ESCAPEDLOWER,"<").replaceAll(ESCAPEDGREATER,">");
        }
        public void doOp(StringBuffer sb) {
            sb.append(text);
        }
    }
    
    class InsertNodeOp implements Op {
        InsertNodeOp() {
            
        }
        public void doOp(StringBuffer sb) {
            sb.append(context.getNode());
        }
        
    }
    
    class InsertGroupOp implements Op {
        int group;
        InsertGroupOp(String opCode) {
            group = Integer.parseInt(opCode);
            if (group < 0)
                throw new NumberFormatException("illegal negativ group number");
            if (group > 20)
                throw new NumberFormatException("illegal group number > 20");
        }
        public void doOp(StringBuffer sb) {
            Matcher matcher = context.getMatcher();
            if (matcher.groupCount() < group)
                throw new LineHandlerException("can't add Group: " + group + " according to current matcher" + matcher.toString());
            sb.append(matcher.group(group));
        }
    }
    
    class InsertNumMonthOp extends InsertGroupOp implements Op {
        InsertNumMonthOp(String group) {
            super(group);
            monthNameToNumber.put("Jan","01");
            monthNameToNumber.put("Feb","02");
            monthNameToNumber.put("Mar","03");
            monthNameToNumber.put("Apr","04");
            monthNameToNumber.put("May","05");
            monthNameToNumber.put("Jun","06");
            monthNameToNumber.put("Jul","07");
            monthNameToNumber.put("Aug","08");
            monthNameToNumber.put("Sep","09");
            monthNameToNumber.put("Oct","10");
            monthNameToNumber.put("Nov","11");
            monthNameToNumber.put("Dec","12");
        }
        HashMap monthNameToNumber = new HashMap();
        public void doOp(StringBuffer sb) {
            StringBuffer tmp = new StringBuffer();
            super.doOp(tmp);
            String month = tmp.toString();
            if (monthNameToNumber.containsKey(month))
                sb.append(monthNameToNumber.get(month));
        }
        
    }
    
    public FieldReplacer(String pattern) {
        String replacement = pattern.replaceAll("\\\\<",ESCAPEDLOWER).replaceAll("\\\\>",ESCAPEDGREATER);
        int lastpos = -1;
        ops = new ArrayList();
        do {
            int pos = replacement.indexOf('<', lastpos + 1);
            if (pos >= 0) {
                if (lastpos < pos - 1) {
                    String toInsert = replacement.substring(lastpos+1,pos);
                    ops.add(new InsertTextOp(toInsert));
                }
                int gpos = replacement.indexOf(">", pos + 1);
                if (gpos < 0)
                    throw new LineHandlerException("expected > after < in pattern " + pattern);
                lastpos = gpos;
                String opCode = replacement.substring(pos+1,gpos);
                String opCodeLower = opCode.toLowerCase();
                if (opCodeLower.equals("node"))
                    ops.add(new InsertNodeOp());
                else if (opCodeLower.startsWith("monthtonum(")) {
                    ops.add(new InsertNumMonthOp(opCode.substring(11, opCode.length()-1)));
                } else
                    try {
                        ops.add(new InsertGroupOp(opCode));
                    } catch (NumberFormatException nex) {
                        throw new LineHandlerException("Illegal Numberformat or unknown opCode: " + opCode + "  in Replacementpattern: " + pattern);
                    }
                continue;
            }
            else {
                if (lastpos < replacement.length()-1) {
                    ops.add(new InsertTextOp(replacement.substring(lastpos+1)));
                }
            }
            lastpos = pos;
        } while (lastpos >= 0);
        
    }
    
    ReplacementContext context;
    ArrayList ops;
    public void doReplacement(ReplacementContext context, StringBuffer sb) {
        this.context = context;
        for (Iterator it = ops.iterator(); it.hasNext();) {
            Op op = (Op) it.next();
            op.doOp(sb);
        }
    }
    
}
