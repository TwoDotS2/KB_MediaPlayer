package mp.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class DC_MP implements Initializable {
    
    public DC_MP() {
        mediaPlayer = null;
    }
    
    private final String curDirectory = System.getProperty("user.dir").concat("/src/mp/Playlist/");
    private String filePath = null, mediaPath, playlistPath;
    private File espDirFile = null;
    
    @FXML
    private TextField playlistName, playlistName1, musicName;
    
    @FXML
    private AnchorPane paneCreate, paneDelete, paneRemove;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private MediaPlayer mediaPlayer;
    
    @FXML
    private MediaView mediaView;
    
    @FXML
    private Slider volumeSlider, seekSlider;

    @FXML
    private ListView<String> musicLV, playlistsLV;
    
    
    @FXML
    public void playVid(ActionEvent event){
        mediaPlayer.play();
    }
    
    @FXML
    public void pauseVid(ActionEvent event){
        mediaPlayer.pause();
    }
    

    public void instanceMediaPlayer(String path){
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    
        volumeSlider.setValue(mediaPlayer.getVolume() * 100);
        
        volumeSlider.valueProperty().addListener((Observable observable) -> {
            mediaPlayer.setVolume(volumeSlider.getValue()/100);
        });
           
        mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
        seekSlider.setValue(newValue.toSeconds());
        });
  
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Select a Media(*.mp4)", "*.mp4"),
                new FileChooser.ExtensionFilter("Select a Media(*.mp3)", "*.mp3"));

        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();

        if(mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING){
                mediaPlayer.stop();
        }  
        try{
            if(filePath != null){
                 instanceMediaPlayer(filePath);

                    DoubleProperty width = mediaView.fitWidthProperty();
                    DoubleProperty height = mediaView.fitHeightProperty();

                    width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
                    height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

                mediaPlayer.play();
            }    
        }catch(Exception e){
            e.getStackTrace();
    } 
    filePath = null;
    
} 

    @FXML
    public void nextMedia(ActionEvent event){   
        ArrayList<String> list = new ArrayList<>();
        String[] mediaList;
        mediaList = mediaFiles(playlistPath);

        String p;
        String path = playlistPath;

        for (String mediaList1 : mediaList) list.add(mediaList1);
                
        int i;

            for(i = 0; i < list.size(); i++){
                p = path.concat(list.get(i));

                if((p.equals(mediaPath)) && (i != (list.size() - 1) ) ){
                    p = path.concat(list.get(i+1));
                    mediaPath = p;

                        if(mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING){
                            mediaPlayer.stop();
                        }
                    instanceMediaPlayer(p);
                    mediaPlayer.play();
                    break;
                } else if((p.equals(mediaPath)) && (i == (list.size() - 1) ) ){
                      p = path.concat(list.get(0));
                      mediaPath = p;

                          if(mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING){
                              mediaPlayer.stop();
                    }
                    instanceMediaPlayer(p);
                    mediaPlayer.play();
                    break;
                    }
            }

}
 
    @FXML
    public void previousMedia(ActionEvent event){
        ArrayList<String> list = new ArrayList<>();
        String[] mediaList;
        mediaList = mediaFiles(playlistPath);
        String p;
        String path = playlistPath;

                for (String mediaList1 : mediaList) {
                    list.add(mediaList1);
                }

                int i;

                for(i = 0; i < list.size(); i++){
                    p = path.concat(list.get(i));

                if((p.equals(mediaPath)) && (i != 0) ){
                    p = path.concat(list.get(i-1));
                    mediaPath = p;

                        if(mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING){
                            mediaPlayer.stop();
                    }
                    instanceMediaPlayer(p);
                    mediaPlayer.play();
                    break;
                } else if((p.equals(mediaPath)) && (i == 0)) {
                        p = path.concat(list.get(list.size()-1));
                        mediaPath = p;

                          if(mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING){
                              mediaPlayer.stop();
                        }
                        instanceMediaPlayer(p);
                        mediaPlayer.play();                    
                        break;
                    }
            }
  }
    
    
    @FXML
    public void clickOnPlaylist(MouseEvent event){
        try{
            playlistPath = "";
            playlistPath = curDirectory.replace("\\", "/");
            ObservableList<String> media;
            media = playlistsLV.getSelectionModel().getSelectedItems();

            for(String m: media){
                playlistPath = playlistPath.concat(m) + "/";
            }
            
            musicLV.getItems().clear();
            musicLV.getItems().addAll(mediaFiles(playlistPath));
        }catch(Exception e){
            e.getStackTrace();
        }
        
    }
    
    
    @FXML
    public void clickOnMedia(MouseEvent event){
        if(espDirFile == null){
            try{
                String path = playlistPath;

                ObservableList<String> media;
                media = musicLV.getSelectionModel().getSelectedItems();

                    for(String m: media){
                       mediaPath = "file:///" + path.concat(m);
                    }

                    if(mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING){
                                    mediaPlayer.stop();
                        }

                instanceMediaPlayer(mediaPath);                
                mediaPlayer.play();

            } catch(Exception e){
                e.getStackTrace();
            }
        } else{
            String path = espDirFile.getAbsolutePath().replace("\\", "/") + "/";
            System.out.println(path);

                ObservableList<String> media;
                media = musicLV.getSelectionModel().getSelectedItems();

                    for(String m: media){
                       mediaPath = "file:///" + path.concat(m);
                    }

                    if(mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING){
                                    mediaPlayer.stop();
                        }

                instanceMediaPlayer(mediaPath);                
                mediaPlayer.play();
        }
    }
 
    public String[] mediaFiles(String dirPath){
        File dir = new File(dirPath);
        File[] filelist = dir.listFiles();
        String[] theNamesOfFiles = new String[filelist.length];
        
        for (int i = 0; i < theNamesOfFiles.length; i++) {
            theNamesOfFiles[i] = filelist[i].getName();
    }
        
    return theNamesOfFiles;
}
    
    @FXML
    public void atualizaLista(ActionEvent event){
        playlistsLV.getItems().clear();
        playlistsLV.getItems().addAll(mediaFiles(curDirectory));
        
        
        musicLV.getItems().clear();
        musicLV.getItems().addAll(mediaFiles(playlistPath));
    }
    
    
    @FXML
    public void showCreatePlaylist(ActionEvent event){
        paneCreate.setVisible(false);
        paneDelete.setVisible(false);        
        paneCreate.setVisible(true);
        paneRemove.setVisible(false);
    }
    
    @FXML
    public void createPlaylist(ActionEvent event){
        filePath = "src/mp/Playlist/".concat(playlistName.getText());
        Boolean success = (new File("src/mp/Playlist/".concat(playlistName.getText()))).mkdir();
        if(!success){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("This playlist already exists!");
            alert.showAndWait();
        }
        
        playlistName.setText("");
        paneCreate.setVisible(false);
        
        playlistsLV.getItems().clear();
        playlistsLV.getItems().addAll(mediaFiles(curDirectory));
    }
    
    @FXML
    public void choosePlaylist(ActionEvent event){
        try{
            final DirectoryChooser dirChooser = new DirectoryChooser();  
            Stage stage = (Stage) borderPane.getScene().getWindow();
            espDirFile = dirChooser.showDialog(stage);

            if(espDirFile != null){
                musicLV.getItems().clear();
                musicLV.getItems().addAll(mediaFiles(espDirFile.getAbsolutePath()));
            }
            
            playlistPath = espDirFile.getAbsolutePath();
            
        } catch(Exception e){
            e.getStackTrace();
        }
    }
    
    @FXML
    public void deletePlaylist(ActionEvent event){
        espDirFile = new File(curDirectory + playlistName1.getText());
        String[] entries = mediaFiles(curDirectory + playlistName1.getText() + "/");
        
            for(String s: entries){
                File currentFile = new File(espDirFile.getPath(),s);
                currentFile.delete();
            }
       
        espDirFile.delete();
        espDirFile = null;
        playlistName1.setText("");
        paneDelete.setVisible(false);
    } 
    
    @FXML
    public void showDeletePlaylist(ActionEvent event){
        paneCreate.setVisible(false);
        paneDelete.setVisible(true);        
        paneCreate.setVisible(false);
        paneRemove.setVisible(false);
    }

    
    @FXML
    public void addMscPlaylist(ActionEvent event) throws IOException{                              
        FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Select a Media(*.mp3)", "*.mp3"));
        File fileSource = fileChooser.showOpenDialog(null);
        File fileDest = new File(filePath + "/" + fileSource.getName());
   
            try{
                Files.copy(fileSource.toPath(), fileDest.toPath());
            }
            catch(Exception e){
                e.getStackTrace();
            }
            
        musicLV.getItems().clear();
        musicLV.getItems().addAll(mediaFiles(playlistPath));
    }
    
    @FXML
    public void showRemoveMscPlaylist(ActionEvent event){
        paneDelete.setVisible(false);        
        paneCreate.setVisible(false);
        paneRemove.setVisible(true);
    }
    
    @FXML
    public void removeMscPlaylist(ActionEvent event){
        File dir = new File(playlistPath);
        File[] filelist = dir.listFiles();
        
        for (int i = 0; i < filelist.length; i++) 
            if (filelist[i].getName().equals(musicName.getText())){
                filelist[i].delete();   
            }
        
       paneRemove.setVisible(false);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
       playlistsLV.getItems().addAll(mediaFiles(curDirectory));
       playlistsLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

       }
    }    