package sistema.farmacia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sistema.farmacia.entities.Compra;
import sistema.farmacia.services.ClienteService;
import sistema.farmacia.services.CompraService;
import sistema.farmacia.services.FarmaceuticoService;
import sistema.farmacia.services.MedicamentoService;

import javax.validation.Valid;

@Controller
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    private FarmaceuticoService farmaceuticoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CompraService compraService;

    @RequestMapping(path = "editar")
    public ModelAndView editarCompra(@RequestParam(required = false) Long id) {

        ModelAndView mv = new ModelAndView("compra/form.html");
        Compra compra;

        if (id == null) {
            compra = new Compra();
        } else {
            try {
                compra = compraService.obterCompra(id);
            } catch (Exception e) {
                compra = new Compra();
                mv.addObject("mensagem", e.getMessage());
            }
        }

        mv.addObject("compra", compra);
        mv.addObject("listaMedicamentos", medicamentoService.listarMedicamentoValidos());
        mv.addObject("listaFarmaceuticos", farmaceuticoService.listarFarmaceutico());
        mv.addObject("listaClientes", clienteService.listarCliente());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "editar")
    public ModelAndView salvarCompra(@Valid Compra compra, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("compra/form.html");

        boolean novo = true;

        if (compra.getId() != null) {
            novo = false;
        }

        if (bindingResult.hasErrors()) {
            mv.addObject("compra", compra);
            return mv;
        }

        if (novo) {
            mv.addObject("compra", new Compra());
        } else {
            mv.addObject("compra", compra);
        }

        try {
            medicamentoService.diminuiQuantidade(compra.getMedicamento(),compra.getQuantidade());
            compraService.salvarCompra(compra);
            mv.addObject("mensagem", "Compra salva com sucesso");
        } catch (Exception e) {
            mv.addObject("mensagem", e.getMessage());
        }

        mv.addObject("listaMedicamentos", medicamentoService.listarMedicamentoValidos());
        mv.addObject("listaFarmaceuticos", farmaceuticoService.listarFarmaceutico());
        mv.addObject("listaClientes", clienteService.listarCliente());
        
        return mv;
    }

    @RequestMapping
    public ModelAndView listarCompra() {

        ModelAndView mv = new ModelAndView("compra/listar.html");

        mv.addObject("lista", compraService.listarCompra());

        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirCompra(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("redirect:/compra");

        try {
            compraService.excluirCompra(id);
            redirectAttributes.addFlashAttribute("mensagem", "Compra excluida com sucesso.");
        } catch(Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir compra."+e.getMessage());
        }

        return mv;
    }
}
