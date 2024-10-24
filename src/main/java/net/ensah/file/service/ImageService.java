package net.ensah.file.service;

import net.ensah.file.dto.request.ImageRequestDto;
import net.ensah.file.dto.response.ImageResponseDto;
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
import java.time.Instant;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageResponseDto addNewImage(MultipartFile file, ImageRequestDto imageRequestDto) throws IOException {
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
                .uploadDate(Instant.now())
                .build();

        Image saved = imageRepository.save(image);
        return  ImageResponseDto.builder()
                .imageId(saved.getImageId())
                .imageName(saved.getImageName())
                .imagePath(filePath.toUri().toString())
                .imageType(saved.getImageType())
                .approvalStatus(saved.getApprovalStatus())
                .author(saved.getAuthor())
                .contentType(saved.getContentType())
                .description(saved.getDescription())
                .dominantColor(saved.getDominantColor())
                .width(saved.getWidth())
                .fileFormat(saved.getFileFormat())
                .height(saved.getHeight())
                .location(saved.getLocation())
                .tags(saved.getTags())
                .build();
    }

    public byte[] getByImage(String imageId) throws IOException {
        Image image = imageRepository.findById(imageId).orElse(null);
        assert image != null;
        String imagePath = image.getImagePath();
         return Files.readAllBytes(Path.of(URI.create(imagePath)));
    }
}
