
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * @author marcoedoardopalma
 */
public class mainClass extends Application
{

    @Override
    public void start(Stage primaryStage)
    {

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            Parent rootTMP = (Parent)loader.load();
            Scene sceneTMP = new Scene(rootTMP);

            primaryStage.setScene(sceneTMP);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e)
        {
            ;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
