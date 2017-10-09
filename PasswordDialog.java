
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class PasswordDialog extends Dialog<String>
{

    private TextField usernameField;
    private PasswordField passwordField;

    public PasswordDialog()
    {
        setTitle("Password");
        setHeaderText("Please enter your password");

        ButtonType passwordButtonType = new ButtonType("Login", ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(passwordButtonType, ButtonType.CANCEL);

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        usernameField = new TextField();
        usernameField.setPromptText("Soton username");

        HBox hBox = new HBox();
        hBox.getChildren().add(usernameField);
        hBox.getChildren().add(passwordField);
        hBox.setPadding(new Insets(20));

        HBox.setHgrow(passwordField, Priority.ALWAYS);

        getDialogPane().setContent(hBox);

        Platform.runLater(() -> passwordField.requestFocus());

        setResultConverter(dialogButton ->
        {
            if (dialogButton == passwordButtonType)
            {
                String[] uAndP = {this.usernameField.getText(), this.passwordField.getText()};
                return passwordField.getText();
            }
            return null;
        });
    }

    public String[] getuAndp()
    {
        String[] uAndp = {this.usernameField.getText(), this.passwordField.getText()};
        return uAndp;
    }
}
