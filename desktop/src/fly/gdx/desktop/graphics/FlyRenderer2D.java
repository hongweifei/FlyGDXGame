package fly.gdx.desktop.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class FlyRenderer2D
{
	private ShapeRenderer renderer = null;
	private SpriteBatch batch = null;
	
	private BitmapFont font = null;
	
	private ArrayList<Texture> texture = null;
	private ArrayList<String> texture_path = null;
	private int texture_draw_index = -1;
	

	public FlyRenderer2D()
	{
		this(false);
	}
	
	@SuppressWarnings("deprecation")
	public FlyRenderer2D(boolean font_use_chinese)
	{
		renderer = new ShapeRenderer();
		batch = new SpriteBatch();
		
		texture = new ArrayList<Texture>(0);
		texture_path = new ArrayList<String>(0);
		
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		//FreeTypeBitmapFontData font_data = generator.generateData(12);
		
		if(font_use_chinese)
			font = generator.generateFont(12, FreeTypeFontGenerator.DEFAULT_CHARS + Chinese.chinese, false);
		else if(!font_use_chinese)
			font = generator.generateFont(12, FreeTypeFontGenerator.DEFAULT_CHARS, false);
		
		generator.dispose();
		//font_data.dispose();
	}
	
	/**
	 * 
	 */
	public void Dispose()
	{
		renderer.dispose();
		batch.dispose();
		
		font.dispose();
		
		for(int i = 0;i < texture.size();i++)
			texture.get(i).dispose();
	}
	
	
	public ShapeRenderer GetRenderer()
	{
		return this.renderer;
	}
	
	
	public SpriteBatch GetBatch()
	{
		return this.batch;
	}
	
	
	public BitmapFont GetFont()
	{
		return this.font;
	}
	
	public void SetFont(BitmapFont font)
	{
		this.font = font;
	}
	
	
	public void ShapeRendererBegin(ShapeRenderer.ShapeType type)
	{
		renderer.begin(type);
	}
	
	public void ShapeRendererEnd()
	{
		renderer.end();
	}
	
	public void BatchBegin()
	{
		batch.begin();
	}
	
	public void BatchEnd()
	{
		batch.end();
	}
	
	
	public void Begin(ShapeRenderer.ShapeType type)
	{
		batch.begin();
		renderer.begin(type);
	}
	
	public void End()
	{
		batch.end();
		renderer.end();
	}
	
	
	public void flush()
	{
		batch.flush();
		renderer.flush();
	}
	
	
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void DrawLine(float x1,float y1,float x2,float y2)
	{
		renderer.line(x1, y1, x2, y2);
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void DrawRect(float x,float y,float width,float height)
	{
		renderer.rect(x, y, width, height);
	}
	
	
	/**
	 * 
	 * @param str
	 * @param x
	 * @param y
	 */
	public void DrawText(String str,float x,float y)
	{
		font.draw(batch, str, x, y);
	}
	
	
	
	public void DrawImage(Texture texture,float x,float y)
	{
		batch.draw(texture, x, y);
	}
	
	public void DrawImage(Texture texture,float x,float y,float width,float height)
	{
		batch.draw(texture, x, y, width, height);
	}
	
	public void DrawImage(Texture texture,float x,float y,int sx,int sy,int sw,int sh)
	{
		batch.draw(texture, sx, sy, sx, sy, sw, sh);
	}
	
	public void DrawImage(Texture texture,float x,float y,float w,float h,
				int sx,int sy,int sw,int sh)
	{
		batch.draw(texture, x, y, w, h, sx, sy, sw, sh, false, false);
	}
	
	public void DrawImage(Texture texture,float x,float y,float w,float h,
				int sx,int sy,int sw,int sh,boolean flip_x,boolean flip_y)
	{
		batch.draw(texture, x, y, w, h, sx, sy, sw, sh, flip_x, flip_y);
	}
	
	
	/**
	 * 
	 * @param list
	 * @param str
	 * @return 如果没有匹配的返回-1,否则返回索引
	 */
	private int ArrayListHaveString(ArrayList<String> list,String str)
	{
		for(int i = 0;i < list.size();i++)
		{
			if(list.get(i).equals(str))
				return i;
		}
		return -1;
	}
	
	
	/**
	 * 
	 * @param path
	 */
	public void AddTexture(String path)
	{
		Texture texture = new Texture(path);
		this.texture.add(texture);
		this.texture_path.add(path);
		//System.out.println(this.toString() + ":AddTexture");
	}
	
	
	public void DrawImage(String path,float x,float y)
	{
		if((texture_draw_index = this.ArrayListHaveString(texture_path, path)) >= 0)
		{
			this.DrawImage(this.texture.get(texture_draw_index), x, y);
			return;
		}
		
		this.AddTexture(path);
		this.DrawImage(texture.get(texture.size() - 1), x, y);
	}
	
	public void DrawImage(String path,float x,float y,float w,float h)
	{
		if((texture_draw_index = this.ArrayListHaveString(texture_path, path)) >= 0)
		{
			this.DrawImage(this.texture.get(texture_draw_index), x, y, w, h);
			return;
		}
		
		this.AddTexture(path);
		this.DrawImage(texture.get(texture.size() - 1), x, y, w, h);
	}
	
	public void DrawImage(String path,float x,float y,int sx,int sy,int sw,int sh)
	{
		if((texture_draw_index = this.ArrayListHaveString(texture_path, path)) >= 0)
		{
			this.DrawImage(this.texture.get(texture_draw_index), x, y, sx, sy, sw, sh);
			return;
		}
		
		this.AddTexture(path);
		this.DrawImage(texture.get(texture.size() - 1), x, y, sx, sy, sw, sh);
	}
	
	public void DrawImage(String path,float x,float y,float w,float h,
				int sx,int sy,int sw,int sh)
	{
		if((texture_draw_index = this.ArrayListHaveString(texture_path, path)) >= 0)
		{
			this.DrawImage(this.texture.get(texture_draw_index), x, y, w, h, sx, sy, sw, sh);
			return;
		}
		
		this.AddTexture(path);
		this.DrawImage(texture.get(texture.size() - 1), x, y, w, h, sx, sy, sw, sh);
	}
	
	public void DrawImage(String path,float x,float y,float w,float h,
				int sx,int sy,int sw,int sh,boolean flip_x,boolean flip_y)
	{
		if((texture_draw_index = this.ArrayListHaveString(texture_path, path)) >= 0)
		{
			this.DrawImage(this.texture.get(texture_draw_index), x, y, w, h, sx, sy, sw, sh, flip_x, flip_y);
			return;
		}
		
		this.AddTexture(path);
		this.DrawImage(texture.get(texture.size() - 1), x, y, w, h, sx, sy, sw, sh, flip_x, flip_y);
	}
	
	
	
	public void DrawImage(int texture_index,float x,float y)
	{
		this.DrawImage(texture.get(texture_index), x, y);
	}
	
	public void DrawImage(int texture_index,float x,float y,float w,float h)
	{
		this.DrawImage(texture.get(texture_index), x, y, w, h);
	}
	
	public void DrawImage(int texture_index,float x,float y,int sx,int sy,int sw,int sh)
	{
		this.DrawImage(texture.get(texture_index), x, y, sx, sy, sw, sh);
	}
	
	public void DrawImage(int texture_index,float x,float y,float w,float h,
				int sx,int sy,int sw,int sh)
	{
		this.DrawImage(texture.get(texture_index), x, y, w, h, sx, sy, sw, sh);
	}
	
	public void DrawImage(int texture_index,float x,float y,float w,float h,
				int sx,int sy,int sw,int sh,boolean flip_x,boolean flip_y)
	{
		this.DrawImage(texture.get(texture_index), x, y, w, h, sx, sy, sw, sh, flip_x, flip_y);
	}
	
}

















