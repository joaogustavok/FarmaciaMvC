package sistema.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.farmacia.entities.Compra;

public interface CompraRepository extends JpaRepository<Compra,Long> {
}
