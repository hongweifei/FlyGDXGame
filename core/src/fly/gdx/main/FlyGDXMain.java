package fly.gdx.main;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


public class FlyGDXMain extends ApplicationAdapter
{
	
	@Override
	public void create ()
	{
		
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void dispose()
	{
		
	}
}











