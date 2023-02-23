package com.example.task.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "socialNetworkPost")
public class SocialNetworkPost {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    // I considered some validation but the requirements of the task does not specify anything ,so I did not implement it
    @Column
    private LocalDate postDate;
    // Should be considered to make it and entity with one to many relation
    @Column
    private String author;

    @Column
    private String content;
    // I implemented it as a counter of how many times specified post was requested it is increased with every getById
    @Column
    private Integer viewCount = 0;



    public SocialNetworkPost update(SocialNetworkPost postEdit){
        if (postEdit.getContent() != null) content = postEdit.content;
        // Here we can add whatever fields we want to allow edition on (for example author) or viewCount etc.
        // I allowed only edition of content because other fields in this class does not seem manually editable
        // if (postEdit.getAuthor() != null) author = postEdit.author; <- example for author
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SocialNetworkPost that = (SocialNetworkPost) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
