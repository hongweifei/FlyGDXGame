package fly.gdx.desktop.graphics;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import fly.shared.Event;

public class FlyScene extends ApplicationAdapter
{
	private FlyRenderer2D renderer = null;
	
	private Event<FlyRenderer2D> render_event_a = null;
	private RenderEvent render_event_b = null;
	
	private boolean font_use_chinese = false;
	
	public FlyScene()
	{
		this(false);
	}
	
	public FlyScene(boolean font_use_chinese)
	{
		render_event_a = new Event<FlyRenderer2D>() {
			@Override public void invoke(FlyRenderer2D t) {	}
		};
		
		render_event_b = new RenderEvent() {
			@Override public void RenderShape(FlyRenderer2D renderer) {}
			@Override public void RenderText(FlyRenderer2D renderer) {}
			@Override public void RenderImage(FlyRenderer2D renderer) {}
		};
		
		this.font_use_chinese = font_use_chinese;
	}
	
	@Override
	public void create ()
	{
		renderer = new FlyRenderer2D(this.font_use_chinese);
	}
	
	@Override
	public void resize (int width, int height)
	{
		
	}
	
	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.renderer.Begin(ShapeRenderer.ShapeType.Line);
		this.render_event_a.invoke(this.renderer);
		this.renderer.End();
		
		this.renderer.ShapeRendererBegin(ShapeRenderer.ShapeType.Line);
		this.render_event_b.RenderShape(this.renderer);
		this.renderer.ShapeRendererEnd();
		
		this.renderer.BatchBegin();
		this.render_event_b.RenderText(this.renderer);
		this.render_event_b.RenderImage(this.renderer);
		this.renderer.BatchEnd();
		
		//this.renderer.flush();
	}
	
	@Override
	public void pause ()
	{
		
	}

	@Override
	public void resume ()
	{
		
	}
	
	@Override
	public void dispose()
	{
		this.renderer.Dispose();
		super.dispose();
	}
	
	
	public FlyRenderer2D GetRenderer()
	{
		return this.renderer;
	}
	
	
	public void SetRenderEvent(Event<FlyRenderer2D> render_event)
	{
		this.render_event_a = render_event;
	}
	
	public void SetRenderEvent(RenderEvent render_event)
	{
		this.render_event_b = render_event;
	}
}











