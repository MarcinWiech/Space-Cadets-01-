
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * FXML Controller class
 *
 * @author marcoedoardopalma
 */
public class MainView_Controller implements Initializable
{

    private WebDriver driver;
    private ArrayList<Person> people = new ArrayList<Person>();
    private String[] names;

    @FXML
    JFXListView mainListView = new JFXListView();
    @FXML
    JFXTextField email_id_input = new JFXTextField();
    @FXML
    SplitPane mainSplitPane = new SplitPane();
    @FXML
    Label name = new Label();
    @FXML
    Label email = new Label();
    @FXML
    Label id = new Label();
    @FXML
    Label position = new Label();
    @FXML
    Label tutor = new Label();
    @FXML
    Label homepage = new Label();
    @FXML
    Label dPA_Visibility = new Label();
    @FXML
    Label eCS_Member_ID = new Label();
    @FXML
    Label role_ID_Number = new Label();
    @FXML
    AnchorPane listSideAnchor = new AnchorPane();


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //https://secure.ecs.soton.ac.uk/people/mep2g17
        //this.driver.quit();
        this.email_id_input.setStyle("-fx-text-inner-color: #ffffff");
        this.mainSplitPane.setDividerPositions(1.0);

        this.mainListView.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                viewPerson(mainListView.getSelectionModel().getSelectedIndex());
            }
        });
    }

    public void setDriver(WebDriver driver)
    {
        this.driver = driver;
    }

    private void viewPerson(int index)
    {
        this.name.setText(this.people.get(index).getName());
        this.email.setText("   E-Mail: " + this.people.get(index).getEmail());
        this.id.setText("   ID: " + this.people.get(index).getId());
        this.dPA_Visibility.setText("   DPA Visibility: " + this.people.get(index).getdPA_Visibility());
        this.eCS_Member_ID.setText("   ECS Member ID: " + this.people.get(index).geteCS_Member_ID());
        this.homepage.setText("   Homepage: " + this.people.get(index).getHomepage());
        this.position.setText("   Position: " + this.people.get(index).getPosition());
        this.role_ID_Number.setText("   Role ID number: " + this.people.get(index).getRole_ID_Number());
        this.tutor.setText("   Tutor: " + this.people.get(index).getTutor());
        this.mainSplitPane.setDividerPositions(0.3);
    }

    @FXML
    private void get_Clicked()
    {
        char[] searchObject_array = this.email_id_input.getText().toCharArray();
        String searchObject = "";

        for (char i : searchObject_array)
        {
            if (i != '@')
            {
                searchObject += i;
            }
            else
            {
                break;
            }
        }

        navigate(searchObject);
    }

    private void navigate(String id)
    {
        try
        {
            this.driver.get("https://secure.ecs.soton.ac.uk/people/" + id);
            
            String pageSource = this.driver.getPageSource();
            Look l = new Look(pageSource);

            Person newPerson = new Person();
            newPerson.setName(this.driver.findElement(By.id("name")).getText());
            newPerson.setPosition(this.driver.findElement(By.className("role")).getText() + l.find("Undergraduate"));
            
            newPerson.setId(l.find("UoS Username:"));
            newPerson.setRole_ID_Number(l.find("Role ID Number:"));
            newPerson.seteCS_Member_ID(l.find("ECS Member ID:"));
            newPerson.setdPA_Visibility(l.find(" Visibility:"));
            newPerson.setEmail(l.find("Email:"));
            if(newPerson.getPosition().contains("Undergraduate"))
                newPerson.setTutor(l.find("Tutor:"));
            newPerson.setHomepage(l.find("Homepage:"));
            
            this.people.add(newPerson);

            ObservableList<String> items = FXCollections.observableArrayList();
            items.addAll(getNames());
            this.mainListView.setItems(items);
            viewPerson(this.names.length - 1);
            this.listSideAnchor.setMaxWidth(180);
        } catch (Exception e)
        {
            this.email_id_input.setText(this.email_id_input.getText() + " --> entry not valid!");
        }

    }

    private String[] getNames()
    {
        this.names = new String[this.people.size()];

        for (int i = 0; i < this.people.size(); i++)
        {
            names[i] = this.people.get(i).getName();
        }

        return this.names;
    }

    @FXML
    private void showAnagrams(Event e)
    {
        Object soruce = e.getSource();

        if(soruce instanceof Label){
            String text = ((Label)soruce).getText();
            AnagramManager am = new AnagramManager();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AnagramView.fxml"));
                Parent rootTMP = (Parent) loader.load();
                Scene sceneTMP = new Scene(rootTMP);

                Stage newStage = new Stage();
                newStage.setScene(sceneTMP);
                newStage.show();

                AnagramView_Controller a = (AnagramView_Controller) loader.getController();
                a.setSolutions(text, am.getSolutions(text));

            }catch(Exception a)
            {
                a.printStackTrace();
            }

        }

    }

}
