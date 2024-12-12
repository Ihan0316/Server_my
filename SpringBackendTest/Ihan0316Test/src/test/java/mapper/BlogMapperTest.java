package mapper;

import com.busanit501.ihan0316test.mapper.BlogMapper;
import com.busanit501.ihan0316test.domain.BlogVO;
import com.busanit501.ihan0316test.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정.
//JUnit4 테스트 설정. @Runwith
// 설정 파일의 경로를 지정.
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class BlogMapperTest {

    // 해당 인스턴스가 없다면, 널로 받을게.
    // 기본값은 required = true
    @Autowired(required = false)
    private BlogMapper blogMapper;

    @Test
    public void testInsert() {
        // 더미 데이터 , BlogVO 담아서, 진행.
        BlogVO blogVO = BlogVO.builder()
                .title("샘플 테스트")
                .dueDate(LocalDate.now())
                .writer("이상용")
                .build();
        blogMapper.insert(blogVO);
    }

    @Test
    public void testSelectAll() {
        List<BlogVO> lists = blogMapper.selectAll();
        for (BlogVO blogVo:lists) {
            log.info("blogVo : " + blogVo);
        }
    }

    @Test
    public void testSelectOne() {
        BlogVO  blogVo = blogMapper.selectOne(9L);
            log.info("blogVo : " + blogVo);
    }

    @Test
    public void testDelete() {
        blogMapper.delete(9L);
    }

    @Test
    public void testUpdate() {
        // 업데이트 할 더미 데이터 필요, BlogVO
        BlogVO blogVO = BlogVO.builder()
                .rno(6L)
                .title("수정 제목")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();

        blogMapper.update(blogVO);
    }

    // 페이징 처리해서 전체 조회
    @Test
    public void testSelectAllWithPage() {
        // 페이징 준비물을 담은 PageRequestDTO 필요함,
        // 더미로 PageRequestDTO 만들고,
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("입력")
                .types(new String[]{"t", "w"})
                .from(LocalDate.of(2023,11,01))
                .to(LocalDate.of(2025,12,31))
                .finished2(true)
                .build();
        // 검색, 필터 조건 sql 작성중
        // where , and 조건이 어떻게 sql 문장이 구성 되는지 각각의 과정을 보기.

        List<BlogVO> list = blogMapper.selectList(pageRequestDTO);
        list.forEach(vo -> log.info("vo : " + vo));
    }

    // 페이징 처리해서 전체 갯수 조회
    @Test
    public void testGetCount() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("입력")
                .types(new String[]{"t", "w"})
                .from(LocalDate.of(2023,11,01))
                .to(LocalDate.of(2025,12,31))
                .finished2(true)
                .build();
        int total = blogMapper.getCount(pageRequestDTO);
        log.info("total : " + total);
    }

}
