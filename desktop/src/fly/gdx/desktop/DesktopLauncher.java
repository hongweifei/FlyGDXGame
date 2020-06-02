package fly.gdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fly.gdx.desktop.graphics.FlyRenderer2D;
import fly.gdx.desktop.graphics.FlyScene;
import fly.gdx.desktop.graphics.RenderEvent;
import fly.shared.Event;

public class DesktopLauncher
{
	static FlyScene scene = new FlyScene();
	
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(scene, config);
		
		scene.SetRenderEvent(new Event<FlyRenderer2D>() {

			@Override
			public void invoke(FlyRenderer2D renderer)
			{
				renderer.DrawRect(0, 300, 200, 200);
				renderer.DrawImage("badlogic.jpg", 100, 100);
			}
			
		});
		
		scene.SetRenderEvent(new RenderEvent() {

			@Override public void RenderShape(FlyRenderer2D renderer)
			{
				renderer.DrawRect(100, 100, 300, 300);
			}
			
			@Override public void RenderText(FlyRenderer2D renderer)
			{
				
			}

			@Override public void RenderImage(FlyRenderer2D renderer)
			{
				
			}

			
		});
	}
	
	
}






















