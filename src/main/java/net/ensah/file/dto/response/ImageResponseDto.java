package net.ensah.file.dto.response;

import lombok.Builder;
import net.ensah.file.entity.Location;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ImageResponseDto(
        String imageId,
        String imageName,
        String imageType,
        String imagePath,
        String description,
        String author,
        String fileFormat,
        Integer width,
        Integer height,
        Location location,
        String dominantColor,
        String contentType,
        String approvalStatus,
        List<String> tags
) {
}
