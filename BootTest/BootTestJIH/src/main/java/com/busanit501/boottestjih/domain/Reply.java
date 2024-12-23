package com.busanit501.boottestjih.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_blog_blogno", columnList = "blog_blogno")
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
    private Blog blog;

    private String replyText;

    private String replyer;

    public void changeBlog(Blog blog) {
        this.blog = blog;
    }

    public void changeReplyTextReplyer(String replyText,String replyer) {
        this.replyText = replyText;
        this.replyer = replyer;

    }

}
