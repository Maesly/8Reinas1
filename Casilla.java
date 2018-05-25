package com.tec.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Casilla{

    private int reina, color;
    private float x, y, ancho, largo;
    private Sprite sprite_reina;

    public Casilla(float x, float y, float ancho, float largo){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.largo = largo;
        sprite_reina = new Sprite(new Texture("queen.png"));
        sprite_reina.setBounds(x,y,ancho,largo);
    }

    public void draw(ShapeRenderer shapeRenderer){
        if(color == 0){
            shapeRenderer.setColor(Color.WHITE);
        }else{
            shapeRenderer.setColor(Color.BLACK);
        }
        shapeRenderer.rect(x,y,ancho,largo);
    }

    public void draw(SpriteBatch spriteBatch){
        if(reina == 1) {
            sprite_reina.draw(spriteBatch);
        }
    }

    public void setReina(int reina){
        this.reina = reina;
    }

    public void setColor(int color){
        this.color = color;
    }
}
