package com.busanit501.helloworld.book.DAO;

import com.busanit501.helloworld.JDBCex.dao.ConnectionUtil;
import com.busanit501.helloworld.book.VO.BookVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // 1. 삽입 insert
    public void insertBook(BookVO bookVO) throws SQLException {
        String sql = "insert into book_list(title, dueDate, finished, bno)" + "values(?,?,?,?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, bookVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(bookVO.getDueDate()));
        preparedStatement.setBoolean(3, bookVO.isFinished());
        preparedStatement.setLong(4, bookVO.getBno());
        preparedStatement.executeUpdate();
    } // insert

    // 2. 조회 select DB에서 전체 조회
    public List<BookVO> selectAllBook() throws SQLException {
        String sql = "select * from book_list";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        List<BookVO> list = new ArrayList<>();
        while (resultSet.next()) {
            BookVO bookVO = BookVO.builder()
                    .bno(resultSet.getLong("bno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(bookVO);
        }
        return list;
    }// select

    // 3. 하나 조회하기
    public BookVO selectOne(Long bno) throws SQLException {
        String sql = "select * from book_list where bno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, bno);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        BookVO bookVO = BookVO.builder()
                .bno(resultSet.getLong("bno"))
                .title(resultSet.getString("title"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();
        return bookVO;
    } // selectOne

    // 4. 수정 update
    public void updateBook(BookVO bookVO) throws SQLException {
        String sql = "update book_list set title=?, dueDate=?, finished=?" +
                " where bno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, bookVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(bookVO.getDueDate()));
        preparedStatement.setBoolean(3,bookVO.isFinished());
        preparedStatement.setLong(4,bookVO.getBno());
        preparedStatement.executeUpdate();
    } // updateBook

    // 5. 삭제 delete
    public void deleteBook(Long bno) throws SQLException {
        String sql = "delete from book_list where bno =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, bno);
        preparedStatement.executeUpdate();
    } // deleteBook
} //class