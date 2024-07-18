package code.ui;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * 限制只能输入正确的端口
 */
public class PortInput extends JTextField {
    public PortInput(){
        this(10);
    }

    public PortInput(int length){
        super(length);
        ((AbstractDocument) this.getDocument()).setDocumentFilter(
                new DocumentFilter() {
                    @Override
                    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                            throws BadLocationException {
                        StringBuilder sb = new StringBuilder();
                        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
                        sb.insert(offset, text);

                        if (isNumeric(sb.toString())) {
                            super.insertString(fb, offset, text, attr);
                        }
                    }

                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                            throws BadLocationException {
                        StringBuilder sb = new StringBuilder();
                        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
                        sb.replace(offset, offset + length, text);

                        if (isNumeric(sb.toString())) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }

                    @Override
                    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                        StringBuilder sb = new StringBuilder();
                        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
                        sb.delete(offset, offset + length);

                        if (isNumeric(sb.toString())) {
                            super.remove(fb, offset, length);
                        }
                    }

                    private boolean isNumeric(String text) {
                        if(text.equals("")) return true;
                        if(text.matches("\\d*")) {
                            // 使用正则表达式验证是否为数字
                            int port = Integer.parseInt(text);
                            return port >= 1 && port<= 65535;  // 端口的范围
                        }
                        return false;
                    }
                }
        );
    }
}
