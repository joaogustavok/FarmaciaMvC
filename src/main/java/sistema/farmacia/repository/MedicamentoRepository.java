package sistema.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sistema.farmacia.entities.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

}
