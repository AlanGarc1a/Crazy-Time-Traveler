package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.game.CrazyTimeTraveler;

public class GameOverScreen extends AbstractScreen {

    private Stage gameOverStage;
    private Table gameOverTable;
    private Label gameOverLabel;
    private TextButton playAgainButton;
    private TextButton mainMenuButton;
    private Label scoreLabel, newHighScore;
    private int score, highScore;

    public GameOverScreen(CrazyTimeTraveler game, int score) {
        super(game);
        this.score = score;
        highScore = game.preferences.getPrefs().getInteger("highscore");
        newHighScore = new Label(null, style);

        if(score > highScore){
            highScore = score;
            game.preferences.getPrefs().putInteger("highscore", highScore);
            game.preferences.getPrefs().flush();
            newHighScore.setText("New High Score: " + highScore);
        } else {
            newHighScore.setText("High Score: " + highScore);
        }
    }

    @Override
    public void show() {
        gameOverStage = new Stage(game.viewport);
        gameOverTable = new Table();

        gameOverLabel = new Label("GAME OVER", style);
        scoreLabel = new Label("Score: " + score, style);
        playAgainButton = new TextButton("RETRY", buttonStyle);
        mainMenuButton = new TextButton("MENU", buttonStyle);

        root.setFillParent(true);
        root.add(gameOverLabel).padBottom(20f);
        root.row();
        root.add(scoreLabel).padBottom(20f);
        root.row();
        root.add(newHighScore).padBottom(20f);
        root.row();
        gameOverTable.add(mainMenuButton).padRight(10f);
        gameOverTable.add(playAgainButton).padLeft(15f);
        root.add(gameOverTable);

        gameOverStage.addActor(root);

        Gdx.input.setInputProcessor(gameOverStage);
    }

    @Override
    public void render(float delta) {
        game.camera.update();
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateScreen();

        gameOverStage.act();
        gameOverStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameOverStage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        gameOverStage.dispose();
    }

    private void updateScreen(){
        playAgainButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });

        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });
    }
}
