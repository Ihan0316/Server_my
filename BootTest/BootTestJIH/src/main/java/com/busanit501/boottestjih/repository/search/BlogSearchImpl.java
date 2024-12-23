package com.busanit501.boottestjih.repository.search;

import com.busanit501.boottestjih.domain.Blog;
import com.busanit501.boottestjih.domain.QBlog;
import com.busanit501.boottestjih.domain.QReply;
import com.busanit501.boottestjih.dto.BlogListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
public class BlogSearchImpl extends QuerydslRepositorySupport
        implements BlogSearch {

    public BlogSearchImpl() {
        super(Blog.class);
    }

    @Override

    public Page<Blog> search(Pageable pageable) {
        QBlog blog = QBlog.blog;
        JPQLQuery<Blog> query = from(blog);
        query.where(blog.title.contains("3"));

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(blog.title.contains("3"));
        booleanBuilder.or(blog.content.contains("7"));
        query.where(booleanBuilder);
        query.where(blog.blogno.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);
        List<Blog> list = query.fetch();
        long total = query.fetchCount();

        return null;
    }

    @Override
    public Page<Blog> searchAll(String[] types, String keyword, Pageable pageable) {
        QBlog blog = QBlog.blog;
        JPQLQuery<Blog> query = from(blog);
        if (types != null && types.length > 0 && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(blog.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(blog.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(blog.writer.contains(keyword));
                        break;
                } // switch
            }// end for
            query.where(booleanBuilder);
        } //end if
        query.where(blog.blogno.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);
        List<Blog> list = query.fetch();
        long total = query.fetchCount();
        Page<Blog> result = new PageImpl<Blog>(list, pageable, total);

        return result;
    }

    @Override
    public Page<BlogListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        QBlog blog = QBlog.blog;
        QReply reply = QReply.reply;
        JPQLQuery<Blog> query = from(blog);
        query.leftJoin(reply).on(reply.blog.blogno.eq(blog.blogno));
        query.groupBy(blog);

        if (types != null && types.length > 0 && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(blog.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(blog.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(blog.writer.contains(keyword));
                        break;
                } // switch
            }// end for
            query.where(booleanBuilder);
        } //end if
        query.where(blog.blogno.gt(0L));
        JPQLQuery<BlogListReplyCountDTO> dtoQuery =
                query.select(Projections.bean(BlogListReplyCountDTO.class,
                        blog.blogno,
                        blog.title,
                        blog.content,
                        blog.writer,
                        blog.regDate,
                        reply.count().as("replyCount")));

        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<BlogListReplyCountDTO> list = dtoQuery.fetch();
        long total = dtoQuery.fetchCount();
        Page<BlogListReplyCountDTO> result = new PageImpl<BlogListReplyCountDTO>(list, pageable, total);

        return result;
    }
}
