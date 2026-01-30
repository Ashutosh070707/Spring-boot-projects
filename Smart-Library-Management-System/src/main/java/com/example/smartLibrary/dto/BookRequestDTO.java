package com.example.smartLibrary.dto;

public class BookRequestDTO {
    private String title;
    private String author;
    private String isbn;
    private int totalCopies;

    public BookRequestDTO(String title, String author, String isbn, int totalCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
    }
    public BookRequestDTO() {
    }

    
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getIsbn() {
        return isbn;
    }
    public int getTotalCopies() {
        return totalCopies;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
    

    
}
