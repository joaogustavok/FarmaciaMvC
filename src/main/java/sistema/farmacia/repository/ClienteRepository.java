package sistema.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sistema.farmacia.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
