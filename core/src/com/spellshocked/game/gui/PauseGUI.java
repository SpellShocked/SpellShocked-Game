package com.spellshocked.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spellshocked.game.Spellshocked;

public class PauseGUI extends GUI {
    public static final String SKIN = "./pixthulhu/skin/pixthulhu-ui.json";
    public PauseGUI(Spellshocked g) {
        super(SKIN);
        TextButton quit = new TextButton("Quit Game", skin);
        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.graphics.getGL20().glClearColor( 1, 0, 0, 1 );
                Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT |  GL20.GL_DEPTH_BUFFER_BIT );

                g.setScreen(g.titleGUI);
            }
        });
//        quit.setSize(600, 100);
//        quit.setPosition(100, 20);
        quit.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        quit.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/24f));
        addActor(quit);

        TextButton resume = new TextButton("Resume Game",skin);
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.graphics.getGL20().glClearColor( 1, 0, 0, 1 );
                Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT |  GL20.GL_DEPTH_BUFFER_BIT );

                g.setScreen(g.world);
            }
        });
//        resume.setSize(600, 100);
//        resume.setPosition(100, 140);
        resume.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        resume.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/3.42f));
        addActor(resume);

        TextButton settings = new TextButton("Game Settings", skin);
        settings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
//        settings.setSize(600, 100);
//        settings.setPosition(100, 260);
        settings.setSize((Gdx.graphics.getWidth()/1.33f), (Gdx.graphics.getHeight()/4.8f));
        settings.setPosition((Gdx.graphics.getWidth()/8f), (Gdx.graphics.getHeight()/1.84f));
        addActor(settings);
    }
}
