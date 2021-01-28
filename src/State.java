import javafx.scene.canvas.GraphicsContext;

public interface State {
	public void update();
	public void draw(GraphicsContext gc);
}
