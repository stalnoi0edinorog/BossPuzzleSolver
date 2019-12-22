package bossPuzzle;

import a_star.A_Star;
import a_star.State;
import bossPuzzleSolver.BossPuzzleRules;
import bossPuzzleSolver.BossPuzzleState;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static javafx.geometry.Pos.CENTER;


public class BossPuzzle extends Application {
    private Pane pane = new Pane();
    private Stage mainStage = new Stage();
    private GameLogic gameLogic = new GameLogic();
    private int counter;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        Scene scene = new Scene(pane, 400, 470);
        pane.setStyle("-fx-background-color: #FFC973");
        gameLogic.newGame();
        repaintField();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void repaintField() {
        pane.getChildren().clear();

        Button restart = new Button("\uD83D\uDD03");
        Button solve = new Button("\uD83D\uDDF8");
        restart.setMinSize(25,25);
        restart.setStyle("-fx-background-radius: 60");
        restart.setOnAction(this::newGame);
        restart.setLayoutX(362);
        restart.setLayoutY(20);

        solve.setMinSize(25,25);
        solve.setStyle("-fx-background-radius: 60");
        solve.setOnAction(this::solveGame);
        solve.setLayoutX(326);
        solve.setLayoutY(20);

        pane.getChildren().add(restart);
        pane.getChildren().add(solve);

        Label moveCounter = new Label();
        moveCounter.setLayoutX(240);
        moveCounter.setLayoutY(20);
        moveCounter.setText(String.valueOf(counter));
        moveCounter.setFont(new Font(18));
        moveCounter.setMinSize(53,27);
        moveCounter.setStyle("-fx-background-color: #FFE873;"
        + "-fx-background-radius: 20");
        moveCounter.setAlignment(CENTER);
        pane.getChildren().add(moveCounter);

        int x = 0, y = 67;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = new Button(Integer.toString(gameLogic.numbers[i * 4 + j]));
                button.setLayoutX(x);
                button.setLayoutY(y);
                button.setFont(new Font(18));
                button.setMinSize(100, 100);

                if (gameLogic.numbers[i * 4 + j] == 0)
                    button.setVisible(false);
                else
                    button.setOnAction(this::clicked);

                pane.getChildren().add(button);
                x += 100;
            }
            x = 0;
            y += 100;
        }
    }

    private void solveGame(ActionEvent event) {
        BossPuzzleRules rules = new BossPuzzleRules(4, gameLogic.getWin());
        BossPuzzleState startState = new BossPuzzleState(null);
        startState.setField(gameLogic.numbers);
        A_Star<BossPuzzleState, BossPuzzleRules> alg = new A_Star<>(rules);
        //Collection<State> search = alg.search(startState);
       // System.out.println(6);

        BossPuzzleState test = new BossPuzzleState(null);
        //test.setField(new int[]{ 1,2,3,4,5,6,7,8,9,14,0,15,13,12,10,11});
       // test.setField(new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,0,13,14,15});
        test.setField(new int[]{ 1, 4, 3, 2, 5, 6, 12, 8, 9, 10, 11, 7, 13, 14, 15, 0});
        //System.out.println(Arrays.toString(test.getField()));
        alg.search(test);

        /*if (search == null) {
            System.out.println("Solution not found");
            return;
        }*/
    }

    private void newGame(ActionEvent event) {
        gameLogic.newGame();
        repaintField();
    }

    private void clicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        String number = button.getText();
        boolean check = gameLogic.change(Integer.parseInt(number));

        if (check)
            counter++;

        if (gameLogic.isWin())
            win();

        repaintField();
    }

    private void win() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("winDialog.fxml"));
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root, 350, 80);
            stage.setScene(scene);
            stage.show();

            WinDialogController controller = loader.getController();
            controller.setParent(mainStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
