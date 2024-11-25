package com.busanit501.helloworld.book.DTO;

import java.time.LocalDate;


public class BookDTO {
    private Long fno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;

    // 게터/세터/toString, 재정의.
    // 자바, 소스 -> 반자동으로 -> 롬복 라이브러리 사용법,

    public Long getFno() {

        return fno;
    }

    public String getTitle() {

        return title;
    }

    public LocalDate getDueDate() {

        return dueDate;
    }

    public boolean isFinished() {

        return finished;
    }

    public void setFno(Long fno) {

        this.fno = fno;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setDueDate(LocalDate dueDate) {

        this.dueDate = dueDate;
    }

    public void setFinished(boolean finished) {

        this.finished = finished;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "fno=" + fno +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", finished=" + finished +
                '}';
    }

}
