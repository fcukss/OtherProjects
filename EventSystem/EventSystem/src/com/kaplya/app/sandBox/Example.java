package com.kaplya.app.sandBox;

import com.kaplya.app.events.Dispatcher;
import com.kaplya.app.events.Event;
import com.kaplya.app.events.types.MouseMotionEvent;
import com.kaplya.app.events.types.MousePressedEvent;
import com.kaplya.app.events.types.MouseReleasedEvent;
import com.kaplya.app.layres.Layer;

import java.awt.*;
import java.util.Random;

public class Example extends Layer {
    private String name;
    private Color color;
    private Rectangle rectangle;
    private boolean dragging = false;
    //px = previous x
    private int px, py;
    private static final Random random = new Random();


    public Example(String name, Color color) {
        this.name = name;
        this.color = color;

        rectangle = new Rectangle(random.nextInt(100) + 150,
                random.nextInt(100) + 250, 120, 150);
    }

    public void onEvent(Event event) {
        Dispatcher dispatcher = new Dispatcher(event);
        dispatcher.dispatch(Event.Type.MOUSE_PRESSED,
                (Event e) -> onPressed((MousePressedEvent) e));
        dispatcher.dispatch(Event.Type.MOUSE_RELEASED,
                (Event e) -> onReleased((MouseReleasedEvent) e));
        dispatcher.dispatch(Event.Type.MOUSE_MOVED,
                (Event e) -> onMoved((MouseMotionEvent) e));
    }

    public void onRender(Graphics g) {
        g.setColor(color);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(Color.WHITE);
        g.drawString(name, rectangle.x + 15, rectangle.y + 35);
    }

    private boolean onPressed(MousePressedEvent event) {
//        System.out.printf("Mouse pressed: %d \n", event.getKeyCode());
//        return false;

        if (rectangle.contains(new Point(event.getX(), event.getY())))
            dragging = true;

        return false;


    }

    private boolean onReleased(MouseReleasedEvent event) {
          System.out.printf("Mouse released  \n");
        dragging = false;
        return dragging;
    }

    private boolean onMoved(MouseMotionEvent event) {
       System.out.printf("Mouse moved: %d | %d \n", event.getX(), event.getY());

        //вычисления дельты
        if (dragging) {
            rectangle.x += event.getX() - px;
            rectangle.y += event.getY() - py;
        }
        px = event.getX();
        py = event.getY();
            return false;
    }
}
