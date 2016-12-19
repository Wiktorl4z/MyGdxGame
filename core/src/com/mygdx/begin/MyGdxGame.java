package com.mygdx.begin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyGdxGame extends ApplicationAdapter {


	private Music music;
	private OrthographicCamera camera; // 2D
	private SpriteBatch batch;
	private Texture texture;
	private GameObject gameObject1, gameObject2;
	private BitmapFont font;
	private float timerHelper;
	
	@Override
	public void create () {
		music = Gdx.audio.newMusic(Gdx.files.internal("alah2.mp3"));
		music.play();
		camera = new OrthographicCamera(800,480);
		batch = new SpriteBatch();
		texture = new Texture("libgdx.png");
		font = new BitmapFont();
		font.setColor(Color.GREEN);
		gameObject1 = new GameObject(texture);
		gameObject1.x = 50;
		gameObject1.y = 50;
		gameObject1.width = gameObject1.getTexture().getWidth();
		gameObject1.height = gameObject1.getTexture().getHeight();
		camera.position.set(gameObject1.x + gameObject1.width/2,gameObject1.y + gameObject1.width/2, 0);

		gameObject2 = new GameObject(texture);
		gameObject2.x = 400;
		gameObject2.y = 400;
		gameObject2.width = gameObject2.getTexture().getWidth();
		gameObject2.height = gameObject2.getTexture().getHeight();


	}

	@Override
	public void render () {
        update();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(gameObject1.getTexture(), gameObject1.x, gameObject1.y);
		batch.draw(gameObject2.getTexture(), gameObject2.x, gameObject2.y);
		batch.end();
	}

    private void update() {
		camera.update();
		batch.setProjectionMatrix(camera.combined); // connecting camera with batch
		camera.position.set(gameObject1.x + gameObject1.width/2, gameObject1.y + gameObject1.height/2, 0);


		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
			camera.zoom += 0.02f;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)){
			camera.zoom -= 0.02f;
		}
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
			gameObject1.x -= 500 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D))
		{
			gameObject1.x += 500 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W))
		{
			gameObject1.y += 500 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S))
		{
			gameObject1.y -= 500 * Gdx.graphics.getDeltaTime();
		}
		if (gameObject1.overlaps(gameObject2))
		{
			music.play();
			gameObject1.x = 0;
			gameObject1.y = 0;
		}

		timerHelper += Gdx.graphics.getDeltaTime();
		if (timerHelper > 0.02f) {
		//	camera.rotate(-0.80f);
			timerHelper = 0;
		}
    }

    @Override
	public void dispose () {
		batch.dispose();
		texture.dispose();
		font.dispose();
		music.dispose();
	}
}
