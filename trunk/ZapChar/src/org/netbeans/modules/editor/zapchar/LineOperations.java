package org.netbeans.modules.editor.zapchar;

import java.awt.Toolkit;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import org.netbeans.editor.BaseDocument;
import org.openide.ErrorManager;

/**
 *
 * @author Sandip V. Chitale (sandipchitale@gmail.com)
 */
public class LineOperations {

    static final void fromChar(JTextComponent textComponent, char fromChar, boolean matchCase, int times) {
        if (textComponent.isEditable()) {
            Document doc = textComponent.getDocument();
            if (doc instanceof BaseDocument) {
                ((BaseDocument) doc).atomicLock();
            }
            try {
                Element rootElement = doc.getDefaultRootElement();

                Caret caret = textComponent.getCaret();
                int start = textComponent.getCaretPosition();

                int zeroBaseStartLineNumber = rootElement.getElementIndex(start);

                if (zeroBaseStartLineNumber == -1) {
                    // could not get line number
                    beep();
                    return;
                } else {
                    int startLineStartOffset = rootElement.getElement(zeroBaseStartLineNumber).getStartOffset();
                    try {
                        String text = doc.getText(startLineStartOffset, start - startLineStartOffset);

                        char lowercaseFromChar = Character.toLowerCase(fromChar);
                        int textLength = text.length();
                        for (int i = textLength - 1; i >= 0; i--) {
                            char charAt = text.charAt(i);
                            if (charAt == fromChar || (!matchCase && Character.toLowerCase(charAt) == lowercaseFromChar)) {
                                times--;
                                if (times == 0) {
                                    caret.moveDot(startLineStartOffset + i);
                                    break;
                                }
                            }
                        }
                    } catch (BadLocationException ex) {
                        ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, ex);
                    }
                }
            } finally {
                if (doc instanceof BaseDocument) {
                    ((BaseDocument) doc).atomicUnlock();
                }
            }
        } else {
            beep();
        }
    }

    static final void afterChar(JTextComponent textComponent, char afterChar, boolean matchCase, int times) {
        if (textComponent.isEditable()) {
            Document doc = textComponent.getDocument();
            if (doc instanceof BaseDocument) {
                ((BaseDocument) doc).atomicLock();
            }
            try {
                Element rootElement = doc.getDefaultRootElement();

                Caret caret = textComponent.getCaret();
                int start = textComponent.getCaretPosition();

                int zeroBaseStartLineNumber = rootElement.getElementIndex(start);

                if (zeroBaseStartLineNumber == -1) {
                    // could not get line number
                    beep();
                    return;
                } else {
                    int startLineStartOffset = rootElement.getElement(zeroBaseStartLineNumber).getStartOffset();
                    try {
                        String text = doc.getText(startLineStartOffset, start - startLineStartOffset);

                        char lowercaseAfterChar = Character.toLowerCase(afterChar);
                        int textLength = text.length();
                        for (int i = textLength - 1; i >= 0; i--) {
                            char charAt = text.charAt(i);
                            if (charAt == afterChar || (!matchCase && Character.toLowerCase(charAt) == lowercaseAfterChar)) {
                                times--;
                                if (times == 0) {
                                    caret.moveDot(startLineStartOffset + i + 1);
                                    break;
                                }
                            }
                        }
                    } catch (BadLocationException ex) {
                        ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, ex);
                    }
                }
            } finally {
                if (doc instanceof BaseDocument) {
                    ((BaseDocument) doc).atomicUnlock();
                }
            }
        } else {
            beep();
        }
    }

    static final void uptoChar(JTextComponent textComponent, char uptoChar, boolean matchCase, int times) {
        if (textComponent.isEditable()) {
            Document doc = textComponent.getDocument();
            if (doc instanceof BaseDocument) {
                ((BaseDocument) doc).atomicLock();
            }
            try {
                Element rootElement = doc.getDefaultRootElement();

                Caret caret = textComponent.getCaret();
                int start = textComponent.getCaretPosition();

                int zeroBaseStartLineNumber = rootElement.getElementIndex(start);

                if (zeroBaseStartLineNumber == -1) {
                    // could not get line number
                    beep();
                    return;
                } else {
                    int startLineEndOffset = rootElement.getElement(zeroBaseStartLineNumber).getEndOffset();
                    try {
                        String text = doc.getText(start + 1, startLineEndOffset - start - 1);

                        char lowercaseUptoChar = Character.toLowerCase(uptoChar);
                        int textLength = text.length();
                        for (int i = 0; i < textLength; i++) {
                            char charAt = text.charAt(i);
                            if (charAt == uptoChar || (!matchCase && Character.toLowerCase(charAt) == lowercaseUptoChar)) {
                                times--;
                                if (times == 0) {
                                    caret.moveDot(start + 1 + i);
                                    break;
                                }
                            }
                        }
                    } catch (BadLocationException ex) {
                        ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, ex);
                    }
                }
            } finally {
                if (doc instanceof BaseDocument) {
                    ((BaseDocument) doc).atomicUnlock();
                }
            }
        } else {
            beep();
        }
    }

    static final void toChar(JTextComponent textComponent, char toChar, boolean matchCase, int times) {
        if (textComponent.isEditable()) {
            Document doc = textComponent.getDocument();
            if (doc instanceof BaseDocument) {
                ((BaseDocument) doc).atomicLock();
            }
            try {
                Element rootElement = doc.getDefaultRootElement();

                Caret caret = textComponent.getCaret();
                int start = textComponent.getCaretPosition();

                int zeroBaseStartLineNumber = rootElement.getElementIndex(start);

                if (zeroBaseStartLineNumber == -1) {
                    // could not get line number
                    beep();
                    return;
                } else {
                    int startLineEndOffset = rootElement.getElement(zeroBaseStartLineNumber).getEndOffset();
                    try {
                        String text = doc.getText(start + 1, startLineEndOffset - start - 1);

                        char lowercaseToChar = Character.toLowerCase(toChar);
                        int textLength = text.length();
                        for (int i = 0; i < textLength; i++) {
                            char charAt = text.charAt(i);
                            if (charAt == toChar || (!matchCase && Character.toLowerCase(charAt) == lowercaseToChar)) {
                                times--;
                                if (times == 0) {
                                    caret.moveDot(start + 1 + i + 1);
                                    break;
                                }
                            }
                        }
                    } catch (BadLocationException ex) {
                        ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, ex);
                    }
                }
            } finally {
                if (doc instanceof BaseDocument) {
                    ((BaseDocument) doc).atomicUnlock();
                }
            }
        } else {
            beep();
        }
    }

    static void beep() {
        Toolkit.getDefaultToolkit().beep();
    }
}
