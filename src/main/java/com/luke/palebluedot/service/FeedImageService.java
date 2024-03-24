package com.luke.palebluedot.service;


import com.luke.palebluedot.domain.FeedImage;
import com.luke.palebluedot.repository.FeedImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;


@Slf4j
@Service
public class FeedImageService {

    private final FeedImageRepository feedImageRepository;
    public FeedImageService(FeedImageRepository feedImageRepository) {
        this.feedImageRepository = feedImageRepository;
    }

    @Value("${feedImage.path}")
    private String fileUploadDir;

    public FeedImage uploadFile(MultipartFile file, Long memberId) throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString();
        String currentDate = LocalDate.now().toString();
        String filePath = fileUploadDir + File.separator + memberId + File.separator + currentDate + File.separator + fileName;

        file.transferTo(new File(filePath));

        FeedImage feedImage = FeedImage.builder()
                .filePath(filePath)
                .build();
        return feedImageRepository.save(feedImage);
    }
}
