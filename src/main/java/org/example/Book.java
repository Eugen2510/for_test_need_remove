package org.example;

import jakarta.persistence.*;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name1;


    public void setId(int id){
        this.id = id;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

//    public void setAuthor(int authorId) {
//        this.authorId = authorId;
//    }


    @ManyToOne (fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author){
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name1 + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
