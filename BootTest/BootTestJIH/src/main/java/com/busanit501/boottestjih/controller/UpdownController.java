package com.busanit501.boottestjih.controller;

import com.busanit501.boottestjih.dto.upload.UploadFileDTO;
import com.busanit501.boottestjih.dto.upload.UploadResultDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@Log4j2
public class UpdownController {

    @Value("${com.busanit501.upload.path}")
    private String uploadPath;


    @Tag(name = "파일 등록 post", description = "멀티파트 타입 형식 이용해서, post 형식으로 업로드테스트")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
        log.info("UpdownController uploadFileDTO 내용 확인: " + uploadFileDTO);

        if (uploadFileDTO.getFiles() != null && !uploadFileDTO.getFiles().isEmpty()) {

            final List<UploadResultDTO> list = new ArrayList<>();

            uploadFileDTO.getFiles().forEach(multipartFile -> {
                log.info("UpdownController multipartFile.getOriginalFilename() 실제 파일 이름 확인 : " + multipartFile.getOriginalFilename());
                String originName = multipartFile.getOriginalFilename();

                String uuid = UUID.randomUUID().toString();
                log.info("UpdownController uuid 랜덤 생성 문자열 확인: " + uuid);

                Path savePath = Paths.get(uploadPath, uuid + "_" + originName);

                boolean image = false;

                try {
                    multipartFile.transferTo(savePath);

                    if (Files.probeContentType(savePath).startsWith("image")) {
                        image = true;
                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.add(UploadResultDTO.builder()
                        .uuid(uuid)
                        .fileName(originName)
                        .img(image)
                        .build());

            });// end forEach

            return list;
        } // end if
        return null;
    }// upload

    @Tag(name = "파일 조회 get", description = "멀티파트 타입 형식 이용해서, get 형식으로 이미지 읽기")
    @GetMapping(value = "/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName) {

        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();
        log.info("UpdownController resourceName : " + resourceName);

        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type",
                    Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @Tag(name = "파일 다운로드 get", description = "멀티파트 타입 형식 이용해서, get 형식으로 이미지 다운로드")
    @GetMapping(value = "/download/{filename}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadPath).resolve(filename).normalize();
            log.info("UpdownController fileDownload filePath : " + filePath);
            Resource resource = new UrlResource(filePath.toUri());
            log.info("UpdownController fileDownload resource : " + resource);

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Tag(name = "파일 삭제 delete",
            description = "멀티파트 타입 형식 이용해서, delete 형식으로 이미지 삭제")
    @DeleteMapping(value = "/delete/{filename}")
    public Map<String,Boolean> fileDelete(@PathVariable String filename) {
        Resource resource = new FileSystemResource(uploadPath+File.separator+filename);

        Map<String,Boolean> resultMap = new HashMap<>();
        boolean deleteCheck = false;
        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            deleteCheck =resource.getFile().delete();

            if (contentType.startsWith("image")) {
                File thumbFile = new File(uploadPath+File.separator,"s_"+ filename);
                thumbFile.delete();
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        resultMap.put("result", deleteCheck);
        return resultMap;
    }
}
