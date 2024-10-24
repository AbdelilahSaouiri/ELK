package net.ensah.file.controller;

import net.ensah.file.dto.request.ImageRequestDto;
import net.ensah.file.dto.response.ImageResponseDto;
import net.ensah.file.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?>  storeNewImage(@RequestPart("file") MultipartFile file, @RequestPart("data") ImageRequestDto imageRequestDto) throws IOException {
        ImageResponseDto image = imageService.addNewImage(file, imageRequestDto);
        return ResponseEntity.ok(image);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getJpegImage(@RequestParam String imageId) throws IOException {
        return imageService.getByImage(imageId);
    }

}
