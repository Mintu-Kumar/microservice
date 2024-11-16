package com.kafkaproject.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="wikimedia_recentchange")
public class WikimediaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //event data is quite huge so inorder to store large data we can use lob annotation
    @Lob
    private String wikiEventData;
}
