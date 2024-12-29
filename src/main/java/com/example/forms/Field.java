package com.example.forms;

import jakarta.persistence.*;

@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String type; // Text, Number, Boolean, Date
    private String defaultValue;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    // Getters, Setters, and Constructors
}
