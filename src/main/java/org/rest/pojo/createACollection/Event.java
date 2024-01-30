package org.rest.pojo.createACollection;

public class Event {
    private String listen;
    private Script script;

    public Event(){

    }

    public Event(String listen,Script script){
        this.listen=listen;
        this.script=script;

    }
    public String getListen() {
        return listen;
    }
    public void setListen(String listen) {
        this.listen = listen;
    }
    public Script getScript() {
        return script;
    }
    public void setScript(Script script) {
        this.script = script;
    }
}
