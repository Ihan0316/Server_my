package com.busanit501.springminitest.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_food_fno", columnList = "food_fno")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    private String replyText;

    private String replyer;

    public void changeFood(Food food) {
        this.food = food;
    }
    // 수정시 내용변경 불변성 유지
    public void changeReplyTextReplyer(String replyText, String replyer) {
        this.replyText = replyText;
        this.replyer = replyer;
    }
}
