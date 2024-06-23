package org.example.springsecuritycrud.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GenericResponse {
    LocalDateTime datetime;
    String message;
    ArrayList<Object> data;

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GenericResponse(ArrayList<Object> data) {
        this.datetime = LocalDateTime.now();
        this.data = data;
    }

    public GenericResponse(Object data) {
        this.datetime = LocalDateTime.now();
        this.data = new ArrayList<>();
        this.data.add(data);
    }

    public GenericResponse(String message) {
        this.datetime = LocalDateTime.now();
        this.message = message;
    }
}
