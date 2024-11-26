package com.busanit501.helloworld.book.Service;

import com.busanit501.helloworld.book.util.MapperUtil;
import com.busanit501.helloworld.book.DAO.BookDAO;
import com.busanit501.helloworld.book.DTO.BookDTO;
import com.busanit501.helloworld.book.VO.BookVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public enum BookService {
    INSTANCE;
    private BookDAO bookDAO;
    private ModelMapper modelMapper;

    BookService(){
        bookDAO = new BookDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public BookDTO getOne(Long bno){
        // 실제로는 DB에서 받아와야함.
        // 더미 데이터 이용
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBno(5L);
        bookDTO.setTitle("하나 조회 더미 데이터");
        bookDTO.setDueDate(LocalDate.now());
        return bookDTO;
    }
    public List<BookDTO> getList() {
        List<BookDTO> bookList = IntStream.range(0,10).mapToObj(
                i -> {
                    // 10 반복 해서, 더미 인스턴스 10개 생성,
                    BookDTO bookDTO = new BookDTO();
                    bookDTO.setTitle("테스트AA " + i);
                    bookDTO.setBno((long) i);
                    bookDTO.setDueDate(LocalDate.now());
                    return  bookDTO;
                }).collect(Collectors.toList());
        return bookList;
    }

    public void register (BookDTO bookDTO) throws SQLException {
        BookVO bookVO = modelMapper.map(bookDTO, BookVO.class);

        log.info("bookVO" + bookVO);

        bookDAO.insertBook(bookVO);
    }

    // 2. 전체 조회
    public List<BookDTO> listAll() throws SQLException {
        List<BookVO> voList = bookDAO.selectAllBook();
        log.info("voList" + voList);

        List<BookDTO> dtoList = voList.stream().map(vo ->
                modelMapper.map(vo, BookDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

    public BookDTO get(Long bno) throws SQLException {
        log.info("bno" + bno);
        // db에서 하나 조회 결과 받기
        BookVO bookVO = bookDAO.selectOne(bno);
        // vo -> dto
        BookDTO bookDTO = modelMapper.map(bookVO, BookDTO.class);

        return bookDTO;
    }
}
