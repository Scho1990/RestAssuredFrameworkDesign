package org.rest.pojo.createACollection;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private String url;
    private String method;
    private List<Header> header;

    public Request(String url, String method, List<Header> header) {
        this.url = url;
        this.method = method;
        this.header = header;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Header> getHeader() {
        return header;
    }

    public void setHeader(List<Header> header) {
        this.header = header;
    }




}
