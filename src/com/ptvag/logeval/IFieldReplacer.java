/*
 * IFieldReplacer.java
 *
 * Created on 24. Februar 2007, 08:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ptvag.logeval;

/**
 *
 * @author schoerk
 */
public interface IFieldReplacer {
    void doReplacement(ReplacementContext context, StringBuffer sb);
    
}
