import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class WinDialogController {
    private Stage parent = new Stage();

    public void newGame(ActionEvent event) throws Exception {
        Button value = (Button) event.getSource();
        Stage stage = (Stage) value.getScene().getWindow();
        stage.close();
        new BossPuzzle().start(new Stage());
        parent.close();
    }

    public void close(ActionEvent event) {
        Button value = ((Button) event.getSource());
        Stage stage = (Stage) value.getScene().getWindow();
        parent.close();
        stage.close();
    }

    void setParent(Stage parent) {
        this.parent = parent;
    }
}
