package sistema.farmacia.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.farmacia.entities.Medicamento;
import sistema.farmacia.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public Medicamento salvarMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    public List<Medicamento> listarMedicamento() {

        return medicamentoRepository.findAll();
    }

    public Medicamento obterMedicamento(Long id) throws Exception {

        Optional<Medicamento> Medicamento = medicamentoRepository.findById(id);
        if (Medicamento.isEmpty()) {
            throw new Exception("Medicamento nao encontrado");
        }
        return Medicamento.get();
    }

    public void excluirMedicamento(Long id) {
        medicamentoRepository.deleteById(id);
    }

    public Medicamento diminuiQuantidade(Medicamento medicamento, Integer quantidade) throws Exception {
        if (medicamento.getQuantidade() - quantidade < 0) {
            throw new Exception("Medicamento " + medicamento.getNome() + " não possui quantidade em estoque necessaria. Quantidade atual: " + medicamento.getQuantidade());
        }
        medicamento.setQuantidade(medicamento.getQuantidade() - quantidade);
        return medicamentoRepository.save(medicamento);
    }

    public List<Medicamento> listarMedicamentoValidos() {
        List<Medicamento> todos = medicamentoRepository.findAll();

        List<Medicamento> validos = todos.stream().filter(medicamento -> (medicamento.getQuantidade()>0)).collect(Collectors.toList());
        return validos;
    }
}

