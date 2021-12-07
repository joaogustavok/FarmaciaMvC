package sistema.farmacia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.farmacia.entities.Cliente;
import sistema.farmacia.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public List<Cliente> listarCliente(){
		
		return clienteRepository.findAll();
	}
	
	public Cliente obterCliente(Long id) throws Exception {
		
		Optional<Cliente> Cliente = clienteRepository.findById(id);
		if(Cliente.isEmpty()) {
			throw new Exception("Cliente nao encontrada");
		}
		return Cliente.get();
	}

	public void excluirCliente(Long id) {
		clienteRepository.deleteById(id);
	}
}
