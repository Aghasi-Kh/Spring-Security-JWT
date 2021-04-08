package com.example.springsecurity.model;

import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String name;


}
