package org.rest.pojo.createACollection;

public class Root {
    private Collection collection;

    public Root(){

    }
    public Root(Collection collection){
    this.collection=collection;
    }
    public Collection getCollection() {
        return collection;
    }
    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
