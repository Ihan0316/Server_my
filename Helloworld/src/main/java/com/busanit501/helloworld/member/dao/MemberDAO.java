package com.busanit501.helloworld.member.dao;

import com.busanit501.helloworld.jdbcex.dao.ConnectionUtil;
import com.busanit501.helloworld.member.vo.MemberVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    // 1
    // insert 입력
    public void insert(MemberVO memberVO) throws SQLException {

        String sql = "insert into member (name, dueDate, finished) " +
                "values (?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberVO.getName());
        preparedStatement.setDate(2, Date.valueOf(memberVO.getDueDate()));
        preparedStatement.setBoolean(3, memberVO.isFinished());
        preparedStatement.executeUpdate();
    } //insert

    // 2
    // select , DB에서 전체 조회.
    public List<MemberVO> selectAll() throws SQLException {
        String sql = "select * from member";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<MemberVO> list = new ArrayList<>();
        while (resultSet.next()) {
            MemberVO memberVO = MemberVO.builder()
                    .mno(resultSet.getLong("mno"))
                    .name(resultSet.getString("name"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(memberVO);
        }
        return list;
    }

    // 3
    // 하나 조회. 상세보기.
    public MemberVO selectOne(Long mno) throws SQLException {
        String sql = "select * from member where mno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, mno);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        MemberVO memberVO = MemberVO.builder()
                .mno(resultSet.getLong("mno"))
                .name(resultSet.getString("name"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();
        return memberVO;
    }

    // 4
    // 수정, update,
    public void updateOne(MemberVO memberVO) throws SQLException {
        String sql = " update member set name=?, dueDate=?, finished=?" +
                " where mno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberVO.getName());
        preparedStatement.setDate(2, Date.valueOf(memberVO.getDueDate()));
        preparedStatement.setBoolean(3,memberVO.isFinished());
        preparedStatement.setLong(4,memberVO.getMno());
        preparedStatement.executeUpdate();

    }

    // 5
    //삭제, delete
    public void deleteMember(Long mno) throws SQLException {
        String sql = "delete from member where mno =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, mno);
        preparedStatement.executeUpdate();

    }

} //class

