package org.rest.pojo.createACollection;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    private Info info;
    private List<Item> item = new ArrayList<Item>();

    public Collection(){

    }

    public Collection(Info info,List<Item> item){
        this.info=info;
        this.item=item;

    }
    public Info getInfo() {
        return info;
    }
    public void setInfo(Info info) {
        this.info = info;
    }
    public List<Item> getItem() {
        return item;
    }
    public void setItem(List<Item> item) {
        this.item = item;
    }
}
