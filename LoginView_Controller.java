
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


import java.net.URL;
import java.util.ResourceBundle;

public class LoginView_Controller implements Initializable
{

    @FXML
    private JFXTextField username = new JFXTextField();
    @FXML
    private JFXPasswordField password = new JFXPasswordField();
    @FXML
    private Label entryFeedback = new Label();

    private WebDriver driver = new HtmlUnitDriver();

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.entryFeedback.setVisible(false);
    }

    @FXML
    public void login()
    {
        this.driver.get("https://secure.ecs.soton.ac.uk/login/?reason=login&uri=%2Fpeople");

        // Find the text input element by its name
        this.driver.findElement(By.name("ecslogin_username")).sendKeys(username.getText());
        this.driver.findElement(By.name("ecslogin_password")).sendKeys(password.getText());

        this.driver.findElement(By.name("ecslogin_uri")).submit();

        if(this.driver.getTitle().equals("People"))
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
                Parent rootTMP = (Parent) loader.load();
                Scene sceneTMP = new Scene(rootTMP);

                Stage newStage = new Stage();
                newStage.setScene(sceneTMP);
                newStage.show();

                MainView_Controller a = (MainView_Controller) loader.getController();
                a.setDriver(this.driver);

                // get a handle to the stage
                Stage stage = (Stage) this.username.getScene().getWindow();
                // do what you have to do
                stage.close();

            }catch (Exception a)
            {
                a.printStackTrace();
            }
        }
        else
        {
            this.entryFeedback.setVisible(true);
        }
    }

}
