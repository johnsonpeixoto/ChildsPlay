import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.util.Duration;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

public class Controler implements Initializable {

    private Parque parquinho = new Parque(2);
    public Path pathBrincando;
    public Path pathBloqueada;
    public Path pathQuieta;


    private String location = System.getProperty("user.dir");

    @FXML
    private Button Btn;

    @FXML
    private TextField id;

    @FXML
    private TextField tempo_brincando;

    @FXML
    private TextField tempo_quieto;

    @FXML
    private CheckBox bola;

    @FXML
    private TableView<Kid> tabela;

    @FXML
    private TableColumn<Kid,String> tabela_nome;

    @FXML
    private TableColumn<Kid,String> tabela_bola;

    @FXML
    private TableColumn<Kid,String> tabela_brinc;

    @FXML
    private TableColumn<Kid,String> tabela_queit;

    @FXML
    private TextArea log;

    @FXML
    private Label capacidadeCesto;

    @FXML
    private VBox kidbox;
    @FXML
    private ImageView kid1;

    @FXML
    private ImageView kid2;

    @FXML
    private ImageView kid3;

    private CallBacks callback1 = new CallBacks() {


        @Override
        public void updateTable() {
            Platform.runLater(() -> {
                tabela.getItems().setAll(parquinho.kids);
            });
        }

        @Override
        public void updateLog(String oldLog) {
            Platform.runLater(() -> {
                String aux = log.getText();
                log.setText(aux + oldLog);
                log.positionCaret(log.getLength());
            });
        }

        @Override
        public void updateCesta() {
            Platform.runLater(() -> {
                capacidadeCesto.setText(String.valueOf(Cesta.bolas));
            });

        }

        @Override
        public void setPathBrincando(Kid kid) {
            callback1.updatePath(kid, pathBrincando);
        }

        @Override
        public void setPathQuieta(Kid kid) {
            callback1.updatePath(kid, pathQuieta);
        }

        @Override
        public void setPathBloqueada(Kid kid) {
            callback1.updatePath(kid, pathBloqueada);
        }

        @Override
        public void updatePath(Kid kid, Path path) {
            Platform.runLater(() -> {
                int index = parquinho.getKids().indexOf(kid);
                System.out.println("Index update: "+index);
                kidbox.getChildren().remove(index);
                ImageView image = new ImageView();
                try {
                    String imageURL = "";
                    if(path == pathBrincando) {
                        imageURL = location + "/src/combola.png";
                        System.out.println(imageURL);
                    }
                    else if(path == pathQuieta) {
                        imageURL = location + "/src/sembola.png";
                        System.out.println(imageURL);
                    }
                    else if(path == pathBloqueada) {
                        imageURL = location + "/src/bloqueada.png";
                        System.out.println(imageURL);
                    }
                    image.setImage(new Image(new FileInputStream(imageURL)));
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                kidbox.getChildren().add(index, image);
                PathTransition pt1 = new PathTransition(Duration.millis(1000), path, image);
                if(path != pathBloqueada) {
                    pt1.setCycleCount(Animation.INDEFINITE);
                    pt1.setAutoReverse(true);
                    pt1.play();
                }
                else {
                    pt1.setDuration(Duration.INDEFINITE);
                    pt1.play();
                }
            });
        }
    };

    @FXML
    void add_child(ActionEvent event) {
        //evento do botao
       Kid newKid = parquinho.addKid(bola.isSelected(),
                id.getText(),
                Long.parseLong(tempo_brincando.getText()),
                Long.parseLong(tempo_quieto.getText()));
        tabela.getItems().setAll(parquinho.kids);
        parquinho.kids.get(parquinho.kids.size()-1).setCalback(callback1);
        id.setText("");
        tempo_brincando.setText("");
        tempo_quieto.setText("");

        if(bola.isSelected()) {
            callback1.setPathBrincando(newKid);
        }
        else {
            callback1.setPathBloqueada(newKid);
        }
        /*
        if (bola.isSelected()){
            File imageLocation = new File(location + ".\\src\\combola.png");
            Image image = new Image(imageLocation.toURI().toString());
            ImageView img = new ImageView(image);

            Path path = new Path();
            path.getElements().addAll(new MoveTo(50,50), new HLineTo(100));
            path.setFill(null);
            kidbox.getChildren().add(path);

            PathTransition pt = new PathTransition(Duration.millis(4000),path,img);
            pt.setCycleCount(Animation.INDEFINITE);
            pt.setAutoReverse(true);
            pt.play();
            kidbox.getChildren().add(img);
        }else{

            File  imageLocation = new File(location + ".\\src\\sembola.png");
            Image image = new Image(imageLocation.toURI().toString());
            ImageView img = new ImageView(image);

            Path path = new Path();
            path.getElements().addAll(new MoveTo(50,50), new VLineTo(100));
            path.setFill(null);
            kidbox.getChildren().add(path);

            PathTransition pt = new PathTransition(Duration.millis(4000),path,img);
            pt.setCycleCount(Animation.INDEFINITE);
            pt.setAutoReverse(true);
            pt.play();
            kidbox.getChildren().add(img);
        }

         */
        bola.setSelected(false);


    }



    @FXML
    void close(MouseEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //colocando os campos da crianca na tabela
        tabela_nome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        tabela_bola.setCellValueFactory(
                new PropertyValueFactory<>("temBola"));
        tabela_brinc.setCellValueFactory(
                new PropertyValueFactory<>("tempoBrinca"));
        tabela_queit.setCellValueFactory(
                new PropertyValueFactory<>("tempoDorme"));

        tabela.getItems().setAll(parquinho.kids);

        //inicializando paths
        pathBrincando = new Path();
        pathBrincando.getElements().addAll(new MoveTo(50, 280), new LineTo(50, 260));
        pathQuieta = new Path();
        pathQuieta.getElements().addAll(new MoveTo(50,280), new LineTo(70, 280));
        pathBloqueada = new Path();
        pathBloqueada.getElements().addAll(new MoveTo(50,280), new LineTo(70, 280));
    }



}
