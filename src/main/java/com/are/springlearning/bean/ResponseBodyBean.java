package com.are.springlearning.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseBodyBean {

    private int state = 200;

    private String message = "success";

    private Object body = null;

    public ResponseBodyBean(){
        final ObjectMapper mapper = new ObjectMapper();
        body = mapper.createObjectNode();
    }

    public ResponseBodyBean(int state, String message, Object body){
        setState(state);
        setMessage(message);
        setBody(body);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
