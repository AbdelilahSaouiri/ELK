package net.ensah.file.entity;


import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Location {
    private Double latitude;
    private Double longitude;
}
