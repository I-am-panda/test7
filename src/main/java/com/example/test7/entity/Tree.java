package com.example.test7.entity;

import lombok.Data;

import java.util.List;

@Data
public class Tree {
    private String name;
    private int id;
    private boolean spread;
    private List<Tree> children;
}
