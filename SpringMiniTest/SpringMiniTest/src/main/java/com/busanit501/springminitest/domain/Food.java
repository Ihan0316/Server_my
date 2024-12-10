package com.busanit501.springminitest.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Food extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    @Column(length = 100, nullable = false)
    private String foodName;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String chefName;

    public void changeTitleContent(String foodName, String content) {
        this.foodName = foodName;
        this.content = content;
    }
}