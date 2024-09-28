package com.AppRH.AppRH.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.models.Candidato;
import com.AppRH.AppRH.models.Vaga;
import com.AppRH.AppRH.repository.CandidatoRepository;
import com.AppRH.AppRH.repository.VagaRepository;
import jakarta.validation.Valid;

@Controller
public class VagaController {

    private final VagaRepository vr;
    private final CandidatoRepository cr;

    @Autowired
    public VagaController(VagaRepository vr, CandidatoRepository cr) {
        this.vr = vr;
        this.cr = cr;
    }

    // CADASTRAR VAGAS
    
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
    public String form() {
        return "vaga/formVaga";
    }

    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/cadastrarVaga";
        }
        vr.save(vaga);
        attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso");
        return "redirect:/cadastrarVaga";
    }

    // LISTAR VAGAS
    
    @RequestMapping(value = "vagas")
    public ModelAndView listaVagas() {
        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vr.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesvaga(@PathVariable("codigo") long codigo) {
        Vaga vaga = vr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("vaga/detalhesVaga");
        
        if (vaga != null) {
            mv.addObject("vaga", vaga);
            Iterable<Candidato> candidatos = cr.findByVaga(vaga);
            mv.addObject("candidatos", candidatos);
        } else {
            mv.addObject("mensagem", "Vaga não encontrada");
        }
        
        return mv;
    }
    
    // DELETAR VAGAS
    
    @RequestMapping("/deletarVaga")
    public String deletarVaga(long codigo) {
    	Vaga vaga = vr.findByCodigo(codigo);
    	vr.delete(vaga);
    	return "redirect:/vagas";
    }
    
    // DETALHAR VAGAS
    
	public String detalhesVagaPost(@PathVariable("codigo") long codigo, @Valid Candidato candidato,
			BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "mensagem, verifi os campos");
			return "redirect:/{codigo}";
		}
		// Cpf duplicado
		if(cr.findByCpf(candidato.getCpf()) != null) {
			attributes.addFlashAttribute("mensagem erro", "CPF duplicado");
			return "redirect:/{codigo}";
		}
    	Vaga vaga = vr.findByCodigo(codigo);
    	candidato.setVaga(vaga);
    	cr.save(candidato);
    	attributes.addFlashAttribute("mensagem","candidato adicionado com sucesso");
    	return "redirect:/{codigo}";
    }
    
	// DELETAR CANDIDATO pelo cpf
	
	@RequestMapping("/deletarCandidato")
	public String deletarCandidato(String cpf) {
		Candidato candidato = cr.findByCpf(cpf);
		Vaga vaga  = candidato.getVaga();
		String codigo = "" + vaga.getCodigo();
		cr.delete(candidato);
		return "redirect:/" + codigo;
		
	}
    
	// METODOS ATUALIZA VAGA 
	// FORMULARIO EDIÇÃO VAGA
	
	@RequestMapping(value = "editar-vaga", method = RequestMethod.GET)
	public ModelAndView editarVaga(long codigo) {
		Vaga vaga = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("vaga/update-vaga");
		mv.addObject("vaga", vaga);
		return mv;
		}
	
	// UPDATE DA VAGA
	
	@RequestMapping(value ="/editar-vaga", method = RequestMethod.POST)
	public String updateVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {
		vr.save(vaga);
		attributes.addFlashAttribute("sucess", "vaga alterada com sucesso");
		long codigoLong = vaga.getCodigo();
		String codigo = ""+codigoLong;
		return "redirection:/" + codigo;
		
	}
	
}
