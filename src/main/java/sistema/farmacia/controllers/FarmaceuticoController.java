package sistema.farmacia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sistema.farmacia.entities.Farmaceutico;
import sistema.farmacia.entities.Farmaceutico;
import sistema.farmacia.services.FarmaceuticoService;
import sistema.farmacia.services.FarmaceuticoService;

import javax.validation.Valid;

@Controller
@RequestMapping("/farmaceutico")
public class FarmaceuticoController {

    @Autowired
    private FarmaceuticoService farmaceuticoService;

    @RequestMapping(path ="editar")
    public ModelAndView editarFarmaceutico(@RequestParam(required = false) Long id) {

        ModelAndView mv = new ModelAndView("farmaceutico/form.html");
        Farmaceutico farmaceutico;

        if(id==null) {
            farmaceutico = new Farmaceutico();
        } else {

            try {
                farmaceutico = farmaceuticoService.obterFarmaceutico(id);
            } catch (Exception e) {
                farmaceutico = new Farmaceutico();
                mv.addObject("mensagem", e.getMessage());
            }
        }
        mv.addObject("farmaceutico", farmaceutico);

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "editar")
    public ModelAndView salvarFarmaceutico(@Valid Farmaceutico farmaceutico, BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("farmaceutico/form.html");

        boolean novo = true;

        if(farmaceutico.getId() != null) {
            novo = false;
        }

        if (bindingResult.hasErrors()) {
            mv.addObject("farmaceutico", farmaceutico);
            return mv;
        }

        farmaceuticoService.salvarFarmaceutico(farmaceutico);

        if(novo) {
            mv.addObject("farmaceutico", new Farmaceutico());
        } else {
            mv.addObject("farmaceutico", farmaceutico);
        }

        mv.addObject("mensagem", "Farmacêutico salvo com sucesso");
        return mv;
    }

    @RequestMapping
    public ModelAndView listarFarmaceuticos() {

        ModelAndView mv = new ModelAndView("farmaceutico/listar.html");

        mv.addObject("lista", farmaceuticoService.listarFarmaceutico());

        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirFarmaceutico(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("redirect:/farmaceutico");

        try {
            farmaceuticoService.excluirFarmaceutico(id);
            redirectAttributes.addFlashAttribute("mensagem", "Farmacêutico excluido com sucesso.");
        } catch(Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir farmacêutico. "+e.getMessage());
        }
        return mv;
    }

}
