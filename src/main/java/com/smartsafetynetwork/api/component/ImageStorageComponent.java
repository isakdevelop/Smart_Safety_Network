package com.smartsafetynetwork.api.component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@Component
public class ImageStorageComponent {
    @Value("${file.upload.directory}")
    private String directory;

    public String saveImage(MultipartFile image, String path) throws IOException {
        String fileName;

        if (image == null || image.isEmpty()) {
            fileName = "No_Image.png";
        } else {
            fileName = UUID.randomUUID() + ".jpg";
        }

        String imagePath = directory + path + fileName;

        if (image != null && !image.isEmpty()) {
            Files.write(Paths.get(imagePath), image.getBytes());
        }

        return imagePath;
    }
}
