package documentListeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class PassFieldListener implements DocumentListener {
	
	String password = "";

	@Override
	public void changedUpdate(DocumentEvent e) {
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		Document document = (Document)e.getDocument();
		try {
			setPassword(document.getText(0, document.getLength()));	
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		Document document = (Document)e.getDocument();
		try {
			setPassword(document.getText(0, document.getLength()));	
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}		
	}
	
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
