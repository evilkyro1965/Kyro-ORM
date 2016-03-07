package com.kyrosoft.quizapp.entity;

/**
 * Created by Administrator on 3/5/2016.
 */
public class Quiz {

    private Integer id;

    private String name;

    private QuizCategory category;

    public Quiz() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuizCategory getCategory() {
        return category;
    }

    public void setCategory(QuizCategory category) {
        this.category = category;
    }
}
