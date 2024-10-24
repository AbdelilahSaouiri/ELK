package net.ensah.file.repository;

import net.ensah.file.entity.Image;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ImageRepository extends ElasticsearchRepository<Image, String> {
}
