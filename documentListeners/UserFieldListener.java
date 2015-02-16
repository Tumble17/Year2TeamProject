package documentListeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class UserFieldListener implements DocumentListener {

	String username = "";

	
	@Override
	public void changedUpdate(DocumentEvent e) {
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		Document document = (Document)e.getDocument();
		try {
			setUsername(document.getText(0, document.getLength()));
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		Document document = (Document)e.getDocument();
		try {
			setUsername(document.getText(0, document.getLength()));
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}		
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
