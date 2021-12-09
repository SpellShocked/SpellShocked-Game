package com.spellshocked.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.entity.Entity;
import com.spellshocked.game.entity.PlayerEntity;
import com.spellshocked.game.entity.SheepEntity;
import static com.spellshocked.game.world.Perlin.GenerateWhiteNoise;
import static com.spellshocked.game.world.Perlin.GenerateSmoothNoise;
import static com.spellshocked.game.world.Perlin.GeneratePerlinNoise;

import java.util.Random;

public class ShockWaveMode extends World{
    final static long mapSeed = 10000000;
    Random randomSeed;

    private PlayerEntity p;
    private SheepEntity s;

    float[][] perlinNoise;

    float health = 1;//0 = dead, 1 = full health
    Texture healthbarTexture;

    public ShockWaveMode(Spellshocked g) {
        super(g, 100, 32, 32, 400, 240);
        this.randomSeed = new Random(this.mapSeed);
        this.perlinNoise = GeneratePerlinNoise(GenerateSmoothNoise(GenerateWhiteNoise(this.randomSeed ,33, 33), 4), 6);

        this.p = new PlayerEntity(2);
        this.s = new SheepEntity();
        this.p.followWithCamera(super.orthographicCamera);
        this.p.setOrthographicCamera(super.orthographicCamera); //to get current zoom
        super.addEntity(this.s);
        super.addEntity(this.p);
        create_Tile_with_Perlin(this.perlinNoise);
        healthbarTexture = new Texture("image/World/healthBars/healthBarGreen.png");




    }

    public void create_Tile_with_Perlin(float[][] perlinNoise){
        /**
         * even Z tile - main tile
         * odd Z tile - tran    sitional tile - might be two types
         * for the random Obstacle must use nextFloat same as when generating Perlin noise otherwise will cause different map from the same seed
         */
        for(int j = 0; j <= super.xValue; j++) {
            for (int i = 0; i <= super.yValue; i++) {
                switch ((int) (perlinNoise[j][i] * 20)) {
                    case 0:
                    case 1:
                        super.tiles[j][i] = new Tile(j, i, 0, World.SAND);
                        break;
                    case 2:
                        super.tiles[j][i] = new Tile(j, i, 1, World.SAND);
                        break;
                    case 3:
                        super.tiles[j][i] = new Tile(j, i, 1, World.GRASS);
                        break;
                    case 4:
                    case 5:
                        super.tiles[j][i] = new Tile(j, i, 2, World.GRASS);
                        break;
                    case 6:
                    case 7:
                        super.tiles[j][i] = new Tile(j, i, 3, World.GRASS);
                        break;
                    case 8:
                    case 9:
                        super.tiles[j][i] = new Tile(j, i, 4, World.GRASS);
                        break;
                    case 10:
                    case 11:
                        super.tiles[j][i] = new Tile(j, i, 5, World.GRASS);
                        break;
                    case 12:
                    case 13:
                        super.tiles[j][i] = new Tile(j, i, 6, World.GRASS);
                        break;
                    case 14:
                        super.tiles[j][i] = new Tile(j, i, 7, World.GRASS);
                        break;
                    case 15:
                        super.tiles[j][i] = new Tile(j, i, 7, World.LAVA);
                        break;
                    case 16:
                    case 17:
                        super.tiles[j][i] = new Tile(j, i, 8, World.LAVA);
                        break;
                    case 18:
                    case 19:
                        super.tiles[j][i] = new Tile(j, i, 9, World.LAVA);
                        break;
                }
                if (Math.random()*200 < 1) {
                    if (Math.random() < 0.5) {
                        tiles[j][i].setObstacle(ROCK);
                    }
                    else {
                        tiles[j][i].setObstacle(new Chest("./json/Object/chest.json", g, p));
                    }
                }
            }
        }

        for (int i = 0; i < super.tiles.length; i++) {
            for (int j = 0; j < super.tiles[i].length; j++) {
                super.tiles[i][j].setNeighbors(super.tiles[Math.max(0,i-1)][j], super.tiles[Math.min(super.xValue,i+1)][j],
                        super.tiles[i][Math.min(super.yValue,j+1)], super.tiles[i][Math.max(0,j-1)]);
            }
        }

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();

        s.targetTile(p.getTile());
        if(s.isAtTarget(p)) p.modifyHealth(-2);
        //if(p.health <= 0)
        if(p.obstacleNear() != null && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (p.obstacleNear().obstacle instanceof Chest) {
                ((Chest) p.obstacleNear().obstacle).getBlockInventoryGUI().wasClicked(mouse);
            }
        }

        if (health>0.7){
            super.spriteBatch.draw(healthbarTexture, 100,100/*, healthbarTexture.getWidth()*health, healthbarTexture.getHeight()*/);
        }
        else if(health > 0.3){

        }
        else {

        }


        spriteBatch.end();
        super.render(delta);

    }

    @Override
    public void print_debug(Entity entity, Tile tile) {
    }
}
