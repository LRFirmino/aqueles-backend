package com.firmino.aqueles.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name="quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "quote")
    @JsonProperty("quote")
    private String quote;
    @Column(name = "answer_1")
    @JsonProperty("answer1")
    private String answer1;
    @Column(name = "answer_2")
    @JsonProperty("answer2")
    private String answer2;
    @Column(name = "answer_3")
    @JsonProperty("answer3")
    private String answer3;
    @Column(name = "correct_answer")
    @JsonProperty("correctAnswer")
    private String correctAnswer;
    @Column(name = "user_id")
    @JsonProperty("userId")
    private int userId;
    @Column(name = "updated_date")
    @JsonProperty("updatedDate")
    private LocalDate updatedDate;
    @Column(name = "created_date")
    @JsonProperty("createdDate")
    private LocalDate createdDate;
    @Column(name = "chosen_count")
    @JsonProperty("chosenCount")
    private int chosenCount;
    @Transient
    @JsonProperty("chosenDate")
    private LocalDate chosenDate;

}

