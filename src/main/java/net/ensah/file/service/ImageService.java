package net.ensah.file.service;

import net.ensah.file.dto.request.ImageRequestDto;
import net.ensah.file.entity.Image;
import net.ensah.file.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image addNewImage(MultipartFile file, ImageRequestDto imageRequestDto) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"), "ELK", "images");
       if(!Files.exists(path)){
           Files.createDirectories(path);
       }
         String fileName = file.getOriginalFilename();
        Path filePath= Paths.get(System.getProperty("user.home"), "ELK", "images",fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Image image= Image.builder()
                .imageId(UUID.randomUUID().toString())
                .imageName(fileName)
                .imagePath(filePath.toUri().toString())
                .imageType(imageRequestDto.imageType())
                .approvalStatus(imageRequestDto.approvalStatus())
                .author(imageRequestDto.author())
                .contentType(imageRequestDto.contentType())
                .description(imageRequestDto.description())
                .dominantColor(imageRequestDto.dominantColor())
                .width(imageRequestDto.width())
                .fileFormat(imageRequestDto.fileFormat())
                .height(imageRequestDto.height())
                .location(imageRequestDto.location())
                .tags(imageRequestDto.tags())
                .uploadDate(imageRequestDto.uploadDate())
                .build();

        return imageRepository.save(image);

    }

    public byte[] getByImage(String imageId) throws IOException {
        Image image = imageRepository.findById(imageId).orElse(null);
        assert image != null;
        String imagePath = image.getImagePath();
         return Files.readAllBytes(Path.of(URI.create(imagePath)));
    }
}
