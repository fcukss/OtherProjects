package com.kaplya.app.events;

public class Dispatcher {
    private Event event;

    public Dispatcher(Event event) {
        this.event = event;
    }

    public void dispatch(Event.Type type, EventHandler eventHandler) {
        if (event.handled)
            return;
        if(event.getType()==type){
            event.handled =  eventHandler.handler(event);
        }
    }
}
