/*
 * TestPanel.java
 *
 * Created on 23. Februar 2007, 12:47
 */

package de.aschoerk.logeval;

import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;

/**
 *
 * @author  xzas
 */
public class FieldExtraction extends javax.swing.JPanel {
    
    /** Creates new form TestPanel */
    MainFrame mainFrame;
    public FieldExtraction(MainFrame mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lineRecognizerTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fieldRecognizerTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fieldTypeComboBox = new javax.swing.JComboBox();
        fieldNameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fieldLengthTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        replacePatternTextField = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        countIn500Label = new javax.swing.JLabel();

        jLabel7.setText("jLabel7");

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Line recognized by:");

        lineRecognizerTextField.setToolTipText("regex to select lines to be extracted, empty means all lines");
        lineRecognizerTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                changed(evt);
            }
        });

        jLabel2.setText("Pattern:");

        fieldRecognizerTextField.setToolTipText("pattern by which the interesting content is selected, groups in this pattern must match replacestring");
        fieldRecognizerTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                changed(evt);
            }
        });

        jLabel6.setText("Name:");

        fieldTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Varchar", "Timestamp", "Number", "Date", "Time" }));
        fieldTypeComboBox.setToolTipText("type of field in database, varchar may be truncated, date and time not implemented yet");
        fieldTypeComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                changed(evt);
            }
        });

        fieldNameTextField.setToolTipText("name of field, will be used in sql-table creation of extraction file header");
        fieldNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                changed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldNameTextFieldKeyTyped(evt);
            }
        });

        jLabel3.setText("Type:");

        jLabel4.setText("Length:");

        fieldLengthTextField.setText("-");
        fieldLengthTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                changed(evt);
            }
        });

        jLabel5.setText("Replace:");

        replacePatternTextField.setToolTipText("replace string,\n<number> stands for group in pattern,\n<monthtonum(number)>  replaces three letter monthname recognized by corresponding group in pattern by 2 digit number, \n<node> stands for dirname according to node-definition,\n content between is added unchanged");
        replacePatternTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                changed(evt);
            }
        });

        deleteButton.setText("D");
        deleteButton.setToolTipText("deleted this extraction rule");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        countIn500Label.setText(".........");
        countIn500Label.setToolTipText("number of times done on testline in 500 ms");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(fieldNameTextField)
                    .add(fieldTypeComboBox, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(fieldLengthTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(12, 12, 12)
                        .add(countIn500Label, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel1)
                        .add(4, 4, 4)
                        .add(lineRecognizerTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 112, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(deleteButton))
                    .add(layout.createSequentialGroup()
                        .add(fieldRecognizerTextField)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(replacePatternTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jLabel2)
                    .add(fieldNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(replacePatternTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5)
                    .add(fieldRecognizerTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(fieldTypeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(fieldLengthTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(deleteButton)
                    .add(lineRecognizerTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(countIn500Label)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void changed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_changed
      int code = evt.getKeyCode();
      if (code != KeyEvent.VK_TAB && code != KeyEvent.VK_ENTER)
        this.mainFrame.setChanged(true);
    }//GEN-LAST:event_changed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
      mainFrame.deleteFieldExtraction(this);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void fieldNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNameTextFieldKeyTyped
        /*
        if (fieldNameTextField.getText().length() > 5) {
            TitledBorder border = (TitledBorder)getBorder();
            border.setTitle(fieldNameTextField.getText() + " ");
            this.validate();
        }
         */
        
        // fieldTypeComboBox.getSelectedItem()
    }//GEN-LAST:event_fieldNameTextFieldKeyTyped
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel countIn500Label;
    public javax.swing.JButton deleteButton;
    public javax.swing.JTextField fieldLengthTextField;
    public javax.swing.JTextField fieldNameTextField;
    public javax.swing.JTextField fieldRecognizerTextField;
    public javax.swing.JComboBox fieldTypeComboBox;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JTextField lineRecognizerTextField;
    public javax.swing.JTextField replacePatternTextField;
    // End of variables declaration//GEN-END:variables
	public javax.swing.JTextField getFieldLengthTextField() {
		return fieldLengthTextField;
	}

	public javax.swing.JTextField getFieldNameTextField() {
		return fieldNameTextField;
	}

	public javax.swing.JTextField getFieldRecognizerTextField() {
		return fieldRecognizerTextField;
	}

	public javax.swing.JComboBox getFieldTypeComboBox() {
		return fieldTypeComboBox;
	}

	public javax.swing.JTextField getLineRecognizerTextField() {
		return lineRecognizerTextField;
	}

	public javax.swing.JTextField getReplacePatternTextField() {
		return replacePatternTextField;
	}
	
	void fromFieldExtractionConfig(FieldExtractionConfig config) {
		fieldLengthTextField.setText(config.getFieldLength());
		fieldNameTextField.setText(config.getFieldName());
		fieldTypeComboBox.setSelectedItem(config.getFieldType());
		fieldRecognizerTextField.setText(config.getFieldRecognizer());
		lineRecognizerTextField.setText(config.getLineRecognizer());
		replacePatternTextField.setText(config.getReplacePattern());
	}
	FieldExtractionConfig toFieldExtractionConfig() {
		FieldExtractionConfig config = new FieldExtractionConfig();
		config.setFieldLength(fieldLengthTextField.getText());
		config.setFieldName(fieldNameTextField.getText());
		config.setFieldType((String)fieldTypeComboBox.getSelectedItem());
		config.setFieldRecognizer(fieldRecognizerTextField.getText());
		config.setLineRecognizer(lineRecognizerTextField.getText());
		config.setReplacePattern(replacePatternTextField.getText());
		return config;
		
		
	}
        
        public void setCountIn500(int count) {
            countIn500Label.setText(Integer.toString(count));
        }
	
    
}