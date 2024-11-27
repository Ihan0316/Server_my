package com.busanit501.helloworld.member;

import com.busanit501.helloworld.member.dao.MemberDAO;
import com.busanit501.helloworld.member.vo.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class memberDAOTest {
    private MemberDAO memberDAO;

    @BeforeEach
    public void ready() {
        memberDAO = new MemberDAO();
    }

    // 1
    @Test
    public void insetTest() throws Exception {
        MemberVO memberVO1 = MemberVO.builder()
                .name("샘플 디비 작성 테스트")
                .dueDate(LocalDate.of(2024, 12, 31))
                .build();

        memberDAO.insert(memberVO1);

    }

    //2, 전체 목록 조회 테스트
    @Test
    public void testList() throws SQLException {
        List<MemberVO> list = memberDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    //3, 하나 조회 테스트
    @Test
    public void getOneTest() throws SQLException {
        Long mno = 3L;
        MemberVO memberVO = memberDAO.selectOne(mno);
        System.out.println(memberVO);
    }

    // 4, 삭제 테스트
    @Test
    public void deleteTest() throws SQLException {
        Long mno = 4L;
        memberDAO.deleteMember(mno);
    }

    // 4, 수정 테스트
    @Test
    public void updateTest() throws SQLException {
        MemberVO memberVO = MemberVO.builder()
                .mno(3L)
                .name("수정 테스트 중")
                .finished(true)
                .dueDate(LocalDate.of(2024, 11, 25))
                .build();

        memberDAO.updateOne(memberVO);

    }
}
