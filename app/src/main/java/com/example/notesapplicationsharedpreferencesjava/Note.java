package com.example.notesapplicationsharedpreferencesjava;

public class Note {
    private String date;
    private String note;

    public Note(String date, String note) {
        this.date = date;
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }
}
