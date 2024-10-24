package net.ensah.file.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;
import java.util.List;

@Document(indexName = "image-data")
@AllArgsConstructor  @NoArgsConstructor @Builder @Getter
public class Image {
     @Id
    private String imageId;
    private String imageName;
    private String imageType;
    private String imagePath;
    private String description;
    private String author;
    private String fileFormat;
    private Integer width;
    private Integer height;

    private Location location;
    private String dominantColor;
    private LocalDate uploadDate;
    private String contentType;
    private String approvalStatus;

    private List<String> tags;

}
