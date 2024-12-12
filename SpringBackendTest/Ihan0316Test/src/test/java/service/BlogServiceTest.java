package service;

import com.busanit501.ihan0316test.dto.PageRequestDTO;
import com.busanit501.ihan0316test.dto.PageResponseDTO;
import com.busanit501.ihan0316test.dto.BlogDTO;
import com.busanit501.ihan0316test.service.BlogService;
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
//방법2
//@RequiredArgsConstructor
public class BlogServiceTest {

    // 방법1
    @Autowired
    private BlogService blogService;

    @Test
    public void testRegister() {
        BlogDTO blogDTO = BlogDTO.builder()
                .title("샘플데이터 서비스에서 입력")
                .dueDate(LocalDate.now())
                .writer("이상용")
                .build();
        blogService.register(blogDTO);
    } //

    @Test
    public void testGetAll() {
        List<BlogDTO> list = blogService.getAll();
        for (BlogDTO blogDTO : list) {
            log.info("blogDTO : " + blogDTO);
        }
    } //

    @Test
    public void testGetOne() {
        BlogDTO blogDTO = blogService.getOne(9L);
        log.info("blogDTO : " + blogDTO);

    } //

    @Test
    public void testDelete() {
        blogService.delete(8L);
    } //

    @Test
    public void testUpdate() {
        // 업데이트 할 더미 데이터 필요, BlogVO
        BlogDTO blogDTO = BlogDTO.builder()
                .rno(5L)
                .title("수정 제목")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();

        blogService.update(blogDTO);
    }

    @Test
    public void testPageList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(180)
                .size(10)
                .types(new String[]{"t", "w"})
                .from(LocalDate.of(2023,11,01))
                .to(LocalDate.of(2025,12,31))
                .finished2(true)
                .build();
        // PageResponseDTO, 안에는 , page, size, skip, start,end,
        // prev, next,  페이징된 목록 요소들
        PageResponseDTO<BlogDTO> list = blogService.selectList(pageRequestDTO);
        list.getDtoList().stream().forEach(dto -> log.info("dto : " + dto));
        log.info("list total : " + list.getTotal());
        log.info("list prev : " + list.isPrev());
        log.info("list next : " + list.isNext());
        log.info("list start : " + list.getStart());
        log.info("list end : " + list.getEnd());

    }

}//
