package sistema.farmacia.services;

import java.util.List;
import java.util.Optional;

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
	
	public List<Medicamento> listarMedicamento(){
		
		return medicamentoRepository.findAll();
	}
	
	public Medicamento obterMedicamento(Long id) throws Exception {
		
		Optional<Medicamento> Medicamento = medicamentoRepository.findById(id);
		if(Medicamento.isEmpty()) {
			throw new Exception("Medicamento nao encontrada");
		}
		return Medicamento.get();
	}

	public void excluirMedicamento(Long id) {
		medicamentoRepository.deleteById(id);
	}
}

