package elementi;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Bird implements DrawableElement, SolidElement {
    protected Image image;
    private float x;
    private float y;
    private float speed;
    private float size;
    private GameContainer container;
    private static final float G_PROPORTION = 0.005f;
    private static final float MAX_SPEED_PROPORTION = 0.0016f;
    private static final float SIZE_PROPORTION= 0.15f;
    private Shape shape;

    public Bird(GameContainer container) throws SlickException {
        this.container= container;
        x = container.getWidth()/5;
        y = container.getHeight()/2;
        size= container.getWidth()*SIZE_PROPORTION;
        speed= 0;
        image= new Image("res/bird.png");
        shape= new Rectangle(x, y, size, size);

    }
    @Override
    public void update(GameContainer container, int delta) {

        speed += G_PROPORTION * delta;
        y += speed*delta;
        if (y>container.getHeight())
            jump();
//      shape.setX(x);
        shape.setY(y);
    }

    @Override
    public void render(GameContainer container, Graphics g) {



        image.setRotation( (float) 180*( (float) Math.atan2(speed, MAX_SPEED_PROPORTION*container.getHeight()))/((float)Math.PI)) ;
        image.setCenterOfRotation( size/2,  size/2);

        image.draw((int) x , (int) y, size, size);

//        g.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }
    public void jump(){
        speed= - MAX_SPEED_PROPORTION * container.getHeight();
    }

    @Override
    public boolean collides(Shape otherShape) {
        return shape.intersects(otherShape);
    }

    public Shape getShape() {
        return shape;
    }

    public float getX() {
        return x;
    }
}
