package homework4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    public TextField messageTextField;
    @FXML
    public TextArea dialogTextArea;


    public boolean acceptMessage(ActionEvent actionEvent) {
        String message = messageTextField.getText();

        if (message == null || message.isEmpty()) return false;

        if(dialogTextArea.getText().isEmpty()) {
            dialogTextArea.setText(message);
        } else {
            dialogTextArea.setText(dialogTextArea.getText() + "\n" + message);
        }

        messageTextField.setText("");
        messageTextField.requestFocus();
        dialogTextArea.positionCaret(dialogTextArea.getText().length());
        return true;
    }
}
