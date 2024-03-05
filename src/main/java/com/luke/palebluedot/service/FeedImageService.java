package com.luke.palebluedot.service;


import com.luke.palebluedot.domain.FeedImage;
import com.luke.palebluedot.repository.FeedImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


@Slf4j
@Service
public class FeedImageService {

    private String fileUploadDir;
    private final FeedImageRepository feedImageRepository;

    public FeedImageService(FeedImageRepository feedImageRepository) {
        this.feedImageRepository = feedImageRepository;
    }

    public FeedImage uploadFile(MultipartFile file, Long memberId) {
        String currentDate = LocalDate.now().toString();
        String filePath = fileUploadDir + File.separator + memberId + File.separator + currentDate;

        /*실제 파일 저장 로직 - 현재 생략*/

        FeedImage feedImage = FeedImage.builder()
                .filePath(filePath)
                .build();
        return feedImageRepository.save(feedImage);
    }
}
