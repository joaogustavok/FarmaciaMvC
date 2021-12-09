package sistema.farmacia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema.farmacia.entities.Farmaceutico;
import sistema.farmacia.repository.FarmaceuticoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FarmaceuticoService {
    @Autowired
    private FarmaceuticoRepository farmaceuticoRepository;

    public Farmaceutico salvarFarmaceutico(Farmaceutico farmaceutico) {
        return farmaceuticoRepository.save(farmaceutico);
    }

    public List<Farmaceutico> listarFarmaceutico() {

        return farmaceuticoRepository.findAll();
    }

    public Farmaceutico obterFarmaceutico(Long id) throws Exception {

        Optional<Farmaceutico> Farmaceutico = farmaceuticoRepository.findById(id);
        if (Farmaceutico.isEmpty()) {
            throw new Exception("Farmacêutico nao encontrada");
        }
        return Farmaceutico.get();
    }

    public void excluirFarmaceutico(Long id) {
        farmaceuticoRepository.deleteById(id);
    }
}
