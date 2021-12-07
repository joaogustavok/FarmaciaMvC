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

import sistema.farmacia.entities.Medicamento;
import sistema.farmacia.services.MedicamentoService;

@Controller
@RequestMapping("/medicamento")
public class MedicamentoController {
	
	@Autowired
	private MedicamentoService medicamentoService;
	
	@RequestMapping(path ="editar")
	public ModelAndView editarMedicamento(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("medicamento/form.html");
		Medicamento medicamento;
		
		if(id==null) {
			medicamento = new Medicamento();
		} else {
			
			try {
				medicamento = medicamentoService.obterMedicamento(id);
			} catch (Exception e) {
				medicamento = new Medicamento();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		mv.addObject("medicamento", medicamento);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarVacina(@Valid Medicamento medicamento,  BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("medicamento/form.html");
		
		boolean novo = true;
		
		if(medicamento.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("medicamento", medicamento);
			return mv;
		}
		
medicamentoService.salvarMedicamento(medicamento);
		
		if(novo) {
			mv.addObject("medicamento", new Medicamento());
		} else {
			mv.addObject("medicamento", medicamento);
		}
		
	
		mv.addObject("mensagem", "Medicamento salvo com sucesso");
	
		return mv;
		
}
	
	@RequestMapping
	public ModelAndView listarMedicamentos() {
		
		ModelAndView mv = new ModelAndView("medicamento/listar.html");
		
		mv.addObject("lista", medicamentoService.listarMedicamento());
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirMedicamento(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/medicamento");
		
		try {
	medicamentoService.excluirMedicamento(id);
			redirectAttributes.addFlashAttribute("mensagem", "Medicamento excluido com sucesso.");
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir medicamento. "+e.getMessage());
		}
	
		return mv;
	}

}
