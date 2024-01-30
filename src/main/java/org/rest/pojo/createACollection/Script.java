package org.rest.pojo.createACollection;

import java.util.ArrayList;
import java.util.List;

public class Script {

    private String id;
    private List<String> exec = new ArrayList<String>();
    private String type;

    public Script(String id, List<String> exec, String type) {
        this.id = id;
        this.exec = exec;
        this.type = type;
    }

    public Script(){

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<String> getExec() {
        return exec;
    }
    public void setExec(List<String> exec) {
        this.exec = exec;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
