package sistema.farmacia.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sistema.farmacia.entities.Cliente;
import sistema.farmacia.services.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(path ="editar")
	public ModelAndView editarCliente(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("cliente/form.html");
		Cliente cliente;
		
		if(id==null) {
			cliente = new Cliente();
		} else {
			try {
				cliente = clienteService.obterCliente(id);
			} catch (Exception e) {
				cliente = new Cliente();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		mv.addObject("cliente", cliente);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarCliente(@Valid Cliente cliente,  BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("cliente/form.html");
		
		boolean novo = true;
		
		if(cliente.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("cliente", cliente);
			return mv;
		}
		
		clienteService.salvarCliente(cliente);
		
		if(novo) {
			mv.addObject("cliente", new Cliente());
		} else {
			mv.addObject("cliente", cliente);
		}
		
		mv.addObject("mensagem", "Cliente salvo com sucesso");
		return mv;
}
	
	@RequestMapping
	public ModelAndView listarClientes() {
		
		ModelAndView mv = new ModelAndView("cliente/listar.html");
		
		mv.addObject("lista", clienteService.listarCliente());
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirVacina(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/cliente");
		
		try {
			clienteService.excluirCliente(id);
			redirectAttributes.addFlashAttribute("mensagem", "Cliente excluido com sucesso.");
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir cliente. "+e.getMessage());
		}
		return mv;
	}
	
}
