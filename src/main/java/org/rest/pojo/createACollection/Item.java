package org.rest.pojo.createACollection;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String name;
    private List<Event> event = new ArrayList<Event>();
    private Request request;

    public Item(){

    }
    public Item(String name,List<Event> event,Request request){
        this.name=name;
        this.event=event;
        this.request=request;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Event> getEvent() {
        return event;
    }
    public void setEvent(List<Event> event) {
        this.event = event;
    }
    public Request getRequest() {
        return request;
    }
    public void setRequest(Request request) {
        this.request = request;
    }
}
