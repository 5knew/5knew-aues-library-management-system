package com.aues.library.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data // Lombok annotation to create getters, setters, toString, equals, and hashCode methods
@Entity // JPA annotation to denote this class as an entity
public class Book {

    @Id // JPA annotation to denote the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Long id;

    private String name;

    @Column(length = 1024) // Specifies the column definition, useful for longer texts
    private String description;

    @ElementCollection // JPA annotation to denote a collection of simple elements
    @CollectionTable(name = "book_photos") // Specifies the table that stores the collection
    private List<String> photos; // List of URLs or identifiers for the photos

    private String pdf; // URL or identifier for the PDF file

    private String author;

    @Column(name = "publication_date")
    private Date publicationDate; // Use java.util.Date or java.time.LocalDate

    private String isbn;

    private String category;

    @Column(name = "availability_status")
    private String availabilityStatus;

}

