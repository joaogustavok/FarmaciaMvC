package sistema.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.farmacia.entities.Farmaceutico;

public interface FarmaceuticoRepository extends JpaRepository<Farmaceutico,Long> {
}
