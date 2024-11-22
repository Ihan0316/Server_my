package com.busanit501.helloworld.JDBCex.dao;

import com.busanit501.helloworld.JDBCex.dto.BookVO;
import lombok.Cleanup;

import java.sql.*;

public class BookDAO {

    public void insert(BookVO bookVO) throws SQLException {

        String sql = "insert into book_list(title, dueDate, finished)" + "values(?,?,?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, bookVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(bookVO.getDueDate()));
        preparedStatement.setBoolean(3, bookVO.isFinished());
        preparedStatement.executeUpdate();
    } // insert
} //class
