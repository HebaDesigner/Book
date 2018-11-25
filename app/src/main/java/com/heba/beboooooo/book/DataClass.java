package com.heba.beboooooo.book;

public class DataClass {

    String bookTitle ,author ,publisher ,publishDate ,descripBook ,imageUrl ;

    public DataClass(String bookTitle, String author, String publisher, String publishDate, String descripBook, String imageUrl) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.descripBook = descripBook;
        this.imageUrl = imageUrl;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescripBook() {
        return descripBook;
    }

    public void setDescripBook(String descripBook) {
        this.descripBook = descripBook;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
