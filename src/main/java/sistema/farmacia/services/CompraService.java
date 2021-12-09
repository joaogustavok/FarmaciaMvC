package sistema.farmacia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.farmacia.entities.Compra;
import sistema.farmacia.repository.CompraRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;


    public Compra salvarCompra(Compra compra) {

        return compraRepository.save(compra);
    }

    public List<Compra> listarCompra() {

        return compraRepository.findAll();
    }

    public Compra obterCompra(Long id) throws Exception {

        Optional<Compra> compra = compraRepository.findById(id);

        if (compra.isEmpty()) {
            throw new Exception("Compra não encontrada");
        }

        return compra.get();

    }

    public void excluirCompra(Long id) {

        compraRepository.deleteById(id);
    }
}
