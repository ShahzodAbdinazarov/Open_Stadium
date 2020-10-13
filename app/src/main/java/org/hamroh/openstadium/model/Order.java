package org.hamroh.openstadium.model;

public class Order {

    private int id;
    private String title;
    private long first, last;
    private int status;

    public Order() {
    }

    public Order(int id, String title, long first, long last, int status) {
        this.id = id;
        this.title = title;
        this.first = first;
        this.last = last;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getFirst() {
        return first;
    }

    public void setFirst(long first) {
        this.first = first;
    }

    public long getLast() {
        return last;
    }

    public void setLast(long last) {
        this.last = last;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
