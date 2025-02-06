package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.FileInfoDTO;
import com.tj.inventorySpringBoot.entity.FileData;
import com.tj.inventorySpringBoot.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class FileDataService {

    @Autowired
    private FileDataRepository fileDataRepository;

    @Value("${file.path}")
    private String FOLDER_PATH;


    public FileData uploadFile(MultipartFile file) throws IOException {

        long unixTime = System.currentTimeMillis() / 1000L;

        Path userPath = Paths.get(FOLDER_PATH);
        if (!Files.exists(userPath)) {
            Files.createDirectories(userPath);  // Create the directory if it doesn't exist
        }
        Path filePath = userPath.resolve(unixTime + file.getOriginalFilename());

        String stringFilePath = FOLDER_PATH  + unixTime + file.getOriginalFilename();
        // C://Users/Lab1/Documents/image/1736937355abc.jpg


        FileData fileData = new FileData();
        fileData.setName(unixTime + file.getOriginalFilename());
        fileData.setType(file.getContentType());
        fileData.setFilePath(stringFilePath);

        fileData = fileDataRepository.save(fileData);

//        file.transferTo(new File(stringFilePath));

        Files.write(filePath, file.getBytes());
        return fileData;

    }
}
