package it.unisa.aDoctor.ui;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

/**
 * An output stream that writes its output to a javax.swing.JTextArea control.
 *
 * @author Ranganath Kini
 * @see javax.swing.JTextArea
 */
public class TextAreaOutputStream extends OutputStream {

    private final JTextArea textControl;

    /**
     * Creates a new instance of TextAreaOutputStream which writes to the
     * specified instance of javax.swing.JTextArea control.
     *
     * @param control A reference to the javax.swing.JTextArea control to which
     * the output must be redirected to.
     */
    public TextAreaOutputStream(JTextArea control) {
        textControl = control;
    }

    /**
     * Writes the specified byte as a character to the javax.swing.JTextArea.
     *
     * @param b The byte to be written as character to the JTextArea.
     * @throws java.io.IOException
     */
    @Override
    public void write(int b) throws IOException {
        // append the data as characters to the JTextArea control
        textControl.append(String.valueOf((char) b));
        textControl.update(textControl.getGraphics());
        textControl.setCaretPosition(textControl.getText().length() - 1);
    }
}
