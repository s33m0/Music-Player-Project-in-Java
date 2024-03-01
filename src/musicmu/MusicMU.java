
package musicmu;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MusicMU extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
 Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
       
        Scene scene = new Scene(root);
        
        stage.setTitle("Music Player");
     //   scene.getStylesheets().add(MusicMU.class.getResource("styleMu.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event ->{
   stage.close();
   
});
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
