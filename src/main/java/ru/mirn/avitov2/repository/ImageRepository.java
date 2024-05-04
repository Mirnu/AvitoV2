package ru.mirn.avitov2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirn.avitov2.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
