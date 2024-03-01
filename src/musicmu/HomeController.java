package musicmu;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController implements Initializable {
 TranslateTransition animation =new TranslateTransition();
 FadeTransition ft ;

 int count=0,countSlide=0,i=0;
    @FXML
    Pane lbSetting;
    @FXML        
    WebView webView;
    @FXML
    AnchorPane  paneCon;
    @FXML
    BorderPane borderpane;
    @FXML
    Slider slider,sliderSound,sliderSpeed;
    @FXML
     HBox hboxCenter,hboxButton;
    @FXML      
     TextField searchField;
    @FXML
     ListView<String> lis;
     ObservableList<String> res =FXCollections.observableArrayList();
     ObservableList<String> resFav=FXCollections.observableArrayList() ;
     @FXML
     VBox slidepane,vboxSetting;
     @FXML
     Label l,l1,labelname,Labelsection;
     @FXML
     Button btnpause,btnplay,btnpre,btnnext,btnexitWeb,menu,remove,choose ,
             btnFav,btnFavList,btnhome,btnReplay,valume,btnSetting,btnexit,
             about,bntpink,btnBlue;
     @FXML
     ImageView imgv;
     @FXML
     ComboBox<String> speedComb;
     @FXML
     ProgressBar prog;
     File directary,dirImg;
     File[] files,filesImg;
     ArrayList<File> songs,imgList;
     int numSong=0, speeds[]={25,50,75,100,125,150,175,200};
     private Timer timer;
     private TimerTask task;
     private boolean runnig=false;
     Media media;
     MediaPlayer mediaplayer;
      ObservableList<String> dieDaten;
      FileChooser fc=new FileChooser();
          Iterator<File> itr ;
FilteredList<String> filteredData = new FilteredList<>(res, s -> true);
       Image myImage0 ;

  //  Image image = new Image("/bellie/download.jpeg", 100, 100, false, false);
      Path path;
      Alert msg=new Alert(Alert.AlertType.ERROR);
      
      
   public void choosefile() throws IOException{
  
FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.mp3)", "*.mp3");
fc.getExtensionFilters().add(extFilter);

//Show save file dialog
File file = fc.showOpenDialog(new Stage());
if(file!=null){
String fileName = file.getName();
  java.nio.file.Path target = Paths.get("src/music", fileName);
    try {
         path
            = Paths.get("src/music/"+fileName);
      if(Files.notExists(path)){
        Files.copy(file.toPath(), target);   
      }
        
    } catch (IOException ex) {
        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
    }
     
        songs.add(file);
      lis.getItems().add(file.getName());
      
}
else {
msg.setTitle("Error");
msg.setHeaderText("Please choose file mp3 or wav and not exist!");
Optional<ButtonType> result = msg.showAndWait();
}
 
         
   }
   
   public void changeSound(){
 
       if(sliderSound.isVisible()){
           sliderSound.setVisible(false);
       }
       else{
           sliderSound.setVisible(true);
       }
       
   }
   public void pauseMedia(){
      
       if(i%2==0){
       
        mediaplayer.play();
        btnpause.setId("pause");
        i++;
       }
       else{
            mediaplayer.pause();
             btnpause.setId("play");i++;
       }
       count=lis.getSelectionModel().getSelectedIndex();
       if(count!=-1){
       lis.getSelectionModel().select(count);
       }
       else{
          lis.getSelectionModel().select(numSong);
       }
      // imgv.setImage(image);
     }
        public void preMedia(){
       
       numSong=lis.getSelectionModel().getSelectedIndex();
      
      if(numSong>0&&numSong!=-1){
             numSong--;
            mediaplayer.stop();
       media =new Media(songs.get(numSong).toURI().toString());
        mediaplayer= new MediaPlayer(media);
        mediaplayer.play();
      // pauseMedia();
        lis.getSelectionModel().select(numSong);
       }
       else {
           numSong=songs.size()-1;
       
              mediaplayer.stop();
       media =new Media(songs.get(numSong).toURI().toString());
        mediaplayer= new MediaPlayer(media);
        mediaplayer.play();
        lis.getSelectionModel().select(numSong);
       }
       
 btnpause.setId("pause");
    i=1;
    sliderSong();
labelname.setText(lis.getSelectionModel().getSelectedItem());
      if(lis.getSelectionModel().getSelectedItem().startsWith("2Pac")){
              myImage0   = new Image(getClass().getResourceAsStream("/bellie/2pac1.jpg"));
             imgv.setImage(myImage0);}
               else if(lis.getSelectionModel().getSelectedItem().startsWith("2pac")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/2pac.jpg"));
             imgv.setImage(myImage0);}
             else if(lis.getSelectionModel().getSelectedItem().startsWith("Coolio")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/2pac1.jpg"));
             imgv.setImage(myImage0);}
                 else if(lis.getSelectionModel().getSelectedItem().startsWith("Alan")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/alan.jpg"));
             imgv.setImage(myImage0);}
              else if(lis.getSelectionModel().getSelectedItem().startsWith("1")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/download.jpeg"));
             imgv.setImage(myImage0);}
               else if(lis.getSelectionModel().getSelectedItem().startsWith("D")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/DHARIA.jpg"));
             imgv.setImage(myImage0);}

     }
     
        
   public void nextMedia(){
       
       
      numSong=lis.getSelectionModel().getSelectedIndex();
      
      if(numSong<songs.size()-1&&numSong!=-1){
             numSong++;
            mediaplayer.stop();
       media =new Media(songs.get(numSong).toURI().toString());
        mediaplayer= new MediaPlayer(media);
        mediaplayer.play();
      // pauseMedia();
        lis.getSelectionModel().select(numSong);
       }
       else {
           numSong=0;
       
              mediaplayer.stop();
       media =new Media(songs.get(numSong).toURI().toString());
        mediaplayer= new MediaPlayer(media);
        mediaplayer.play();
        lis.getSelectionModel().select(0);
       }
       
 btnpause.setId("pause");
    i=1;
    sliderSong();
    labelname.setText(lis.getSelectionModel().getSelectedItem());
          if(lis.getSelectionModel().getSelectedItem().startsWith("2Pac")){
              myImage0   = new Image(getClass().getResourceAsStream("/bellie/2pac1.jpg"));
             imgv.setImage(myImage0);}
               else if(lis.getSelectionModel().getSelectedItem().startsWith("2pac")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/2pac.jpg"));
             imgv.setImage(myImage0);}
             else if(lis.getSelectionModel().getSelectedItem().startsWith("Coolio")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/2pac1.jpg"));
             imgv.setImage(myImage0);}
                 else if(lis.getSelectionModel().getSelectedItem().startsWith("Alan")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/alan.jpg"));
             imgv.setImage(myImage0);}
              else if(lis.getSelectionModel().getSelectedItem().startsWith("1")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/download.jpeg"));
             imgv.setImage(myImage0);}
               else if(lis.getSelectionModel().getSelectedItem().startsWith("D")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/DHARIA.jpg"));
             imgv.setImage(myImage0);}
                else {
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/def.jpg"));
             imgv.setImage(myImage0);}
     }
     public void Replay(){
            mediaplayer.seek(Duration.ZERO);
        }
    public void speedMedia(){
     
     }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // pane1.setVisible(false);
       /*FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), labelname);
       labelname.setWrapText(true);
       fadeTransition.setFromValue(5.0);
       fadeTransition.setToValue(0.0);
       fadeTransition.setCycleCount(Animation.INDEFINITE);
       //fadeTransition.play();*/
       /* Timeline timeline = new Timeline(
       new KeyFrame(Duration.seconds(2), new KeyValue(labelname.textFillProperty(), Color.RED)),
       new KeyFrame(Duration.seconds(4), new KeyValue(labelname.textFillProperty(), Color.GREEN))
       );
       timeline.setAutoReverse(true);
       timeline.setCycleCount(Animation.INDEFINITE);
       timeline.play();*/
       
       //labelname.textProperty().bind();
//       JFXRadioButton javaRadio = new JFXRadioButton("JavaFX");

     //  vboxSetting.getChildren().add(jfxRadio);
          ft= new FadeTransition();
            ft.setFromValue(0.0);
            // ft.setToValue(1.0);
             ft.play();
       dirImg =new File("src/bellie");
        imgList=new ArrayList<>();
        filesImg=dirImg.listFiles();
             if(files!=null){
            imgList.addAll(Arrays.asList(filesImg)); //  System.out.println(file);
        }
     
     
       vboxSetting.setVisible(false);
       sliderSound.setVisible(false);
       btnexitWeb.setVisible(false);
       webView.setVisible(false);
        try {
        webView.getEngine().load(getClass().getResource("/meInfo/aboutMe.html").toExternalForm());
        } catch (Exception e) {
        }
       
        directary =new File("src/music");
        songs=new ArrayList<>();
        files=directary.listFiles();
        
        if(files!=null){
            songs.addAll(Arrays.asList(files)); //  System.out.println(file);
        }
        media =new Media(songs.get(numSong).toURI().toString());
        mediaplayer= new MediaPlayer(media);
        mediaplayer.setAutoPlay(runnig);
       files = new File("src/music").listFiles();
//If this pathname does not denote a directory, then listFiles() returns null. 

for (File file : files) {
    if (file.isFile()) {
         res.add((file.getName().substring(0, (int) (file.getName().length()-4))));
        
    }
}
        lis.getItems().addAll(res);
      
        lis.setFixedCellSize(45);
   
        lis.setCellFactory(param -> new ListCell<String>() {
            {
                prefWidthProperty().bind(lis.widthProperty().subtract(10));
                setMaxWidth(Control.USE_PREF_SIZE);
            }
            @Override
              protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item);
                } else {
                    setText(null);
                }
            }
  });
     sliderSound.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {

     mediaplayer.setVolume(sliderSound.getValue()*0.01);
     });
         
         sliderSong();
 

      }

    public void listFav() throws Exception{

              mediaplayer.stop();
          directary =new File("src/favorites");
       
        files=directary.listFiles();
        songs=new ArrayList<>();
        resFav=FXCollections.observableArrayList();
        if(files!=null){
            songs.addAll(Arrays.asList(files)); //  System.out.println(file);
        }
 
for (File file : files) {
    if (file.isFile()) {
        resFav.add((file.getName().substring(0, (int) (file.getName().length()-4))));
       
}}
  Labelsection.setText("FAVORITE LIST");
  lis.setItems(resFav);
             btnFav.setId("listfav"); 
               lis.getSelectionModel().clearSelection();
         i=1;
       
        if(resFav==null){
                msg.setTitle("Error");
        msg.setHeaderText("Have not songs in favorite list");
        msg.show();

            System.out.println("ok");
        
        }
             if(lis.getSelectionModel().getSelectedIndex()!=-1){
             btnpause.setId("pause");
        } else {
             btnpause.setId("play");
        }
         //i=1;
    }
    
    
    public void HomeMusic(){
        
        Labelsection.setText("ALL SONGS");
       // lis.getItems().removeAll(res);
         mediaplayer.stop();
             directary =new File("src/music");
        songs=new ArrayList<>();
        files=directary.listFiles();
       
        if(files!=null){
            songs.addAll(Arrays.asList(files)); //  System.out.println(file);
        }
        media =new Media(songs.get(numSong).toURI().toString());
        mediaplayer= new MediaPlayer(media);
       
res=FXCollections.observableArrayList();
for (File file : files) {
    if (file.isFile()) {
         res.add((file.getName().substring(0, (int) (file.getName().length()-4))));
        
    }
}
        lis.setItems( res);

        btnFav.setId("fav"); 
        if(lis.getSelectionModel().getSelectedIndex()!=-1){
             btnpause.setId("play");
        } else {
             btnpause.setId("pause");
        }
         i=1;
      pauseMedia();
        sliderSong();
              lis.getSelectionModel().clearSelection();
         i=1;
}
    public void selectSong() throws Exception{
        labelname.setText(lis.getSelectionModel().getSelectedItem());
            mediaplayer.stop();
       media =new Media(songs.get(lis.getSelectionModel().getSelectedIndex()).toURI().toString());
        mediaplayer= new MediaPlayer(media);
        mediaplayer.play();
       sliderSong();
       btnpause.setId("pause");
   // if(resFav)
    //   if(fileslis.getSelectionModel().getSelectedIndex()].)
    
             if(lis.getSelectionModel().getSelectedItem().startsWith("2Pac")){
              myImage0   = new Image(getClass().getResourceAsStream("/bellie/2pac1.jpg"));
             imgv.setImage(myImage0);}
               else if(lis.getSelectionModel().getSelectedItem().startsWith("2pac")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/2pac.jpg"));
             imgv.setImage(myImage0);}
             else if(lis.getSelectionModel().getSelectedItem().startsWith("Coolio")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/2pac1.jpg"));
             imgv.setImage(myImage0);}
                 else if(lis.getSelectionModel().getSelectedItem().startsWith("Alan")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/alan.jpg"));
             imgv.setImage(myImage0);}
              else if(lis.getSelectionModel().getSelectedItem().startsWith("1")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/download.jpeg"));
             imgv.setImage(myImage0);}
               else if(lis.getSelectionModel().getSelectedItem().startsWith("D")){
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/dharia.jpg"));
             imgv.setImage(myImage0);}
               else {
                 myImage0   = new Image(getClass().getResourceAsStream("/bellie/def.jpg"));
             imgv.setImage(myImage0);}
           //  try {
            
        try {
                          path = Paths.get("src/favorites/"+files[lis.getSelectionModel().getSelectedIndex()].getName());
             if(Files.notExists(path))
        btnFav.setId("fav"); 
             
      else
     btnFav.setId("listfav");
              } catch (Exception e) {
               btnFav.setId("fav");     
        }
                   
    }
    
    public void slidePane(){
          if( countSlide%2==0){
       animation.setDuration(Duration.seconds(0.5));
       animation.setNode(slidepane);
       animation.setToX(55);
       animation.play();
       menu.setId("remove");
 countSlide++;
       }
   else{
       animation.setNode(slidepane);
       animation.setToX(-55);
       animation.play();
       menu.setId("menu");
       countSlide++;
          }
    }
     void sliderSong(){
          mediaplayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> 
                observable, Duration oldValue, Duration newValue) -> {
          slider.setValue(newValue.toSeconds());
        });
        slider.setOnMousePressed((MouseEvent event) -> {
            mediaplayer.seek(Duration.seconds(slider.getValue()));
        });
         slider.setOnMouseDragged((MouseEvent event) -> {
            mediaplayer.seek(Duration.seconds(slider.getValue()));
        });
         
        mediaplayer.setOnReady(() -> {
            Duration t=media.getDuration();
            slider.setMax(t.toSeconds());
        });
        l.textProperty().bind(
    Bindings.createStringBinding(() -> {
            Duration time = mediaplayer.getCurrentTime();
            return String.format("%4d:%02d:%04.1f",
                (int) time.toHours(),
                (int) time.toMinutes() % 60,
                time.toSeconds() % 60);
        },
        mediaplayer.currentTimeProperty()));
//l1.setText((mediaplayer.getStopTime()).toString());
//l1.setText(" 00:00:00 ");
l1.textProperty().bind(
    Bindings.createStringBinding(() -> {
            Duration time = mediaplayer.getStopTime();
            return String.format("%4d:%02d:%04.1f",
                (int) time.toHours(),
                (int) time.toMinutes() % 60,
                time.toSeconds() % 60
            );
        },
        mediaplayer.currentTimeProperty()));

  sliderSpeed.setOnMousePressed((MouseEvent event) -> {
            mediaplayer.setRate(sliderSpeed.getValue());
        });
         sliderSpeed.setOnMouseDragged((MouseEvent event) -> {
            mediaplayer.setRate(sliderSpeed.getValue());
        });
    slider.getValue();
    /* if(slider.==mediaplayer.getTotalDuration()){
    //itr=songs.iterator();
    media =new Media(songs.get(++numSong).toURI().toString());
    mediaplayer= new MediaPlayer(media);
    mediaplayer.setAutoPlay(true);
    
    }*/
    }
     
     public void addFav() throws IOException{
           try {           
            path
            = Paths.get("src/favorites/"+files[lis.getSelectionModel().getSelectedIndex()].getName());
            for (int j = 0; j < resFav.size(); j++) {
             if(resFav.get(j) != null && resFav.get(j).equals(lis.getSelectionModel().getSelectedItem()))
                 runnig=true;
         }
            // boolean check = new File(directary, temp).exists();
       if(songs!=null&&Files.notExists(path)&&lis.getSelectionModel().getSelectedIndex()!=-1 ){
String fileName = songs.get(lis.getSelectionModel().getSelectedIndex()).getName();
  java.nio.file.Path target = Paths.get("src/favorites", fileName);
    try {
        Files.copy(songs.get(lis.getSelectionModel().getSelectedIndex()).toPath(), target);
    } catch (IOException ex) {
        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
    }
      
     }
       else  {
               Files.deleteIfExists(path);
         }
  
              path = Paths.get("src/favorites/"+files[lis.getSelectionModel().getSelectedIndex()].getName());
             if(Files.notExists(path))
        btnFav.setId("fav"); 
             
      else
     btnFav.setId("listfav");
      } catch (IOException e) {
         }
         
           
         }
     
     
     public void formSetting( ) throws IOException{
      vboxSetting.setVisible(true);
      ft.setNode(vboxSetting);
      ft.setToValue(1.0);
      ft.setDuration(Duration.seconds(0.4));
      ft.play();
       animation.setDuration(Duration.seconds(0.2));
       animation.setNode(slidepane);
       animation.setToX(-55);
       animation.play();

     }
 
     public void exit(){
           vboxSetting.setVisible(false);
      ft.setDuration(Duration.seconds(0.9));   
      ft.setToValue(0.0);
      ft.play();
      animation.setDuration(Duration.seconds(0.4));
      animation.setNode(slidepane);
       animation.setToX(55);
       animation.play();
        menu.setId("remove");
     }
       public void exitWeb(){
           vboxSetting.setVisible(false);
      webView.setVisible(false);
      ft.setNode(webView);
      ft.setToValue(1.0);
      ft.setDuration(Duration.seconds(0.4));
      ft.play();
      animation.setDuration(Duration.seconds(0.4));
      animation.setNode(slidepane);
       animation.setToX(55);
       animation.play();
        menu.setId("remove");
        btnexitWeb.setVisible(false);
     }
     public void search(){
      //  IntStream.range(0, 1000).mapToObj(Integer::toString).forEach(res::add);
    filteredData = new FilteredList<>(res, s -> true);

     searchField.textProperty().addListener(((observable, oldValue, newValue) -> {
filteredData.predicateProperty().bind(Bindings.createObjectBinding(() -> name ->
        name.contains(searchField.getText()), searchField.textProperty()));
// Use the filtered list in the list view
lis.setItems(filteredData);
     }));

     }
     public void btnAbout(){
      webView.setVisible(true);
      ft.setToValue(1.0);
      ft.setDuration(Duration.seconds(0.4));
      ft.play();
      animation.setDuration(Duration.seconds(0.4));
      animation.setNode(slidepane);
       animation.setToX(-55);
       animation.play();
       btnexitWeb.setVisible(true);
     }
        public void themePink(){
         slider.setId("sliderSong1");
         paneCon.setId("homeCon1");
         hboxButton.setId("hbox1");
         slidepane.setId("slide1");
         searchField.setId("search1");
         lis.setStyle( ".list-cell:filled:selected:focused, .list-cell:filled:selected {\n" +
"  -fx-background-color: linear-gradient(#328BDB 0%, #207BCF 25%, #390099 75%, #ef476f 100%);\n" +
"  -fx-text-fill: white;\n" +
"  -fx-border-radius: 3;\n" +
"}");
         vboxSetting.setId("paneSetting1");
         lbSetting.setId("lbSetting1");
     }
        public void themeBlue(){
         slider.setId("sliderSong");
         paneCon.setId("homeCon");
         hboxButton.setId("hbox");
         slidepane.setId("slide");
         searchField.setId("search");
         lis.setStyle( ".list-cell:filled:selected:focused, .list-cell:filled:selected {\n" +
"  -fx-background-color: linear-gradient(#328BDB 0%, #207BCF 25%, #1973C9 75%, #0A65BF 100%);\n" +
"  -fx-text-fill: white;\n" +
"  -fx-border-radius: 3;\n" +
"}");
         vboxSetting.setId("paneSetting");
         lbSetting.setId("lbSetting");
     }
       public void logoutMethod(){
          msg= new Alert(Alert.AlertType.CONFIRMATION);
           msg.setTitle("Message");
msg.setHeaderText("Are you sure you want to exit ?");
Optional<ButtonType> result = msg.showAndWait();
if (result.get().equals(ButtonType.OK)) { 
 System.exit(0);
}else if (result.get().equals(ButtonType.NO)) {
       msg.close();
}
       }
    }
