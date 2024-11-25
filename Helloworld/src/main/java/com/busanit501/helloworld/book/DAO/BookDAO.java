package com.busanit501.helloworld.book.DAO;

import com.busanit501.helloworld.JDBCex.dao.ConnectionUtil;
import com.busanit501.helloworld.book.VO.BookVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // 2. select DB에서 전체 조회
    public List<BookVO> selectAllBook() throws SQLException {
        String sql = "select * from book_list";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        // 넘어온 데이터를 임시로 보관할 리스트 인스턴스 만들고, 반복문 통해서 넘어온 각 행을 리스트에 요소로 하나씩 담기
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
} //class
