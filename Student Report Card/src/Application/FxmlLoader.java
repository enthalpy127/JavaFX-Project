package Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FxmlLoader {

    public Pane pane;

    public Pane getPage(String file){
        try{
            //Parent root = FXMLLoader.load(Main.class.getResource(file));
            URL fileUrl = Main.class.getResource("/Application/" + file + ".fxml");
            if(fileUrl == null){
                throw new java.io.FileNotFoundException("File not found.");
            }
            pane = new FXMLLoader().load(fileUrl);
            //pane = (Pane) root;
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("No page " + file + ". Please check fxml file.");
        }
        return pane;
    }
}
