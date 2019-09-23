import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class Controler implements Initializable {

    private Parque parquinho = new Parque(2);

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
    private TextArea capacidadeCesto;



    @FXML
    void add_child(ActionEvent event) {
        //evento do botao
        parquinho.addKid(bola.isSelected(),
                id.getText(),
                Long.parseLong(tempo_brincando.getText()),
                Long.parseLong(tempo_quieto.getText()));
        tabela.getItems().setAll(parquinho.kids);
        parquinho.kids.get(parquinho.kids.size()-1).setCalback(callback1);
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

    }

    private CallBacks callback1 = new CallBacks() {
        @Override
        public void updateTable() {
            tabela.getItems().setAll(parquinho.kids);
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
            System.out.println(Cesta.bolas);
        }
    };

}
