package com.busanit501.helloworld.todo.DTO;

import java.util.Date;

public class TodoDTO {
    private Long tno;
    private String title;
    private Date dueDate;
    private boolean finished;
    
    // getter, setter, tostring 재정의
    // getter
    public Long getTno() {
        return tno;
    }

    public String getTitle() {
        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setTno(Long tno) {
        this.tno = tno;
    }

    // setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    //toString
    @Override
    public String toString() {
        return "TodoDTO{" +
                "tno=" + tno +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", finished=" + finished +
                '}';
    }
}
