package com.busanit501.boot501.controller;

import com.busanit501.boot501.dto.upload.UploadFileDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@Log4j2
public class UpdownController {
    // application.propeties, 업로드 경로 설정.
    // com.busanit501.upload.path=/Users/ihanjo/Documents/K-Digital/Upload/SpringTest
    // 컨트롤러에서 사용해보기.
    @Value("${com.busanit501.upload.path}")
    private String uploadPath;

    // 파일 업로드시,
    // 같은 이름의 파일명 문제가 됨, -> UUID , 자바에서 제공해주는 랜덤 문자열을 생성하는 도구
    //  UUID(임시 생성된 문자열)+ 파일명, 작업.
    @Tag(name = "파일 등록 post",
            description = "멀티파트 타입 형식 이용해서, post 형식으로 업로드테스트")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(UploadFileDTO uploadFileDTO) {
        log.info("UpdownController uploadFileDTO 내용 확인: "+uploadFileDTO);

        if(uploadFileDTO.getFiles() != null && uploadFileDTO.getFiles().size() > 0){
            uploadFileDTO.getFiles().forEach(multipartFile -> {
                log.info("UpdownController multipartFile.getOriginalFilename() 실제 파일 이름 확인 : "+multipartFile.getOriginalFilename());
                String originName = multipartFile.getOriginalFilename();

                String uuid = UUID.randomUUID().toString();
                log.info("UpdownController uuid 랜덤 생성 문자열 확인: "+uuid);

                // savePath -> /Users/ihanjo/Documents/K-Digital/Upload/SpringTest/UUID임시생성문자열_파일명
                Path savePath = Paths.get(uploadPath,uuid+"_"+originName);


                //화면 -> 서버, 이미지 파일을 받았고,
                // 받은 이미지 파일명 중복 안되게 설정,
                // 실제 저장 경로를 , 패스 클래스 이용해서, 설정,

                // 파일 업로드 이동시, 반드시, 예외처리.
                try {
                    // 실제 파일 -> 해당 경로 -> 물리 파일 복사하는 일.
                    // 원본으로 저장,
                    multipartFile.transferTo(savePath);

                    // 컨텐츠 타입을 확인해서, 이미지라면, 썸네일 도구 이용해서,
                    // 작은 이미지로 변환 해서, 저장,
                    // 작은 이미지라는 표시, 이름 앞에 s_ 이런식으로 이름을 변경.
                    if(Files.probeContentType(savePath).startsWith("image")){
                        // 새로운 파일을 생성. 기존 원본 이미지 -> 작은 이미지
                        File thumbFile = new File(uploadPath,"s_"+ uuid+"_"+originName);
                        // 작은 이미지 변환 도구 이용해서, 축소 작업.
                        Thumbnailator.createThumbnail(savePath.toFile(),thumbFile, 200,200);
                    }

                } catch (IOException e)
                {
                    e.printStackTrace();
                }

            });
        }

        return null;
    }


}
