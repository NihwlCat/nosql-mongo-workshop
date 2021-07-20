package br.pedro.workshopnosql.models;

import java.time.Instant;

public class Comment {
    private Instant moment;
    private String text;

    private Author author;

    public Comment(){
    }
    public Comment(Instant moment, String text, Author author) {
        this.moment = moment;
        this.text = text;
        this.author = author;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
