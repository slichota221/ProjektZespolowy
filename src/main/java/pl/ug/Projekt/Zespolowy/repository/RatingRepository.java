package pl.ug.Projekt.Zespolowy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ug.Projekt.Zespolowy.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
