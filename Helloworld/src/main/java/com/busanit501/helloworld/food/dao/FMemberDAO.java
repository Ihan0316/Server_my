package com.busanit501.helloworld.food.dao;

import com.busanit501.helloworld.food.vo.FMemberVO;
import com.busanit501.helloworld.jdbcex.dao.ConnectionUtil;
import com.busanit501.helloworld.jdbcex.vo.MemberVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FMemberDAO {

    public FMemberVO getMemberWithMpw(String mid, String mpw) throws SQLException {
        String query = "select * from tbl_fmember where mid=? and mpw=?";
        FMemberVO fmemberVO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, mid);
        preparedStatement.setString(2, mpw);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        fmemberVO = FMemberVO.builder()
                .mid(resultSet.getString("mid"))
                .mpw(resultSet.getString("mpw"))
                .mname(resultSet.getString("mname"))
                .uuid(resultSet.getString("uuid"))
                .build();

        return fmemberVO;
    }

    public void updateUuid(String mid, String uuid) throws SQLException {
        String query = "update tbl_fmember set uuid=? where mid=?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uuid);
        preparedStatement.setString(2, mid);
        preparedStatement.executeUpdate();
    }

    public FMemberVO getMemberWithUuid(String uuid) throws SQLException {
        String query = "select * from tbl_fmember where uuid=?";
        FMemberVO fmemberVO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uuid);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        fmemberVO = FMemberVO.builder()
                .mid(resultSet.getString("mid"))
                .mpw(resultSet.getString("mpw"))
                .mname(resultSet.getString("mname"))
                .uuid(resultSet.getString("uuid"))
                .build();

        return fmemberVO;
    }
}
