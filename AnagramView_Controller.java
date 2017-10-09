
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AnagramView_Controller implements Initializable
{
    @FXML
    JFXButton title = new JFXButton();
    @FXML
    JFXListView listView = new JFXListView();

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    public void setSolutions(String myInput, ArrayList<String> solutions)
    {
        this.title.setText(solutions.size() + " anagrams found for \"" + myInput + "\"");
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(solutions);
        this.listView.setItems(items);
    }

}
