package com.example.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.app.models.Cliente;
import com.example.app.models.dao.IClienteDao;

import jakarta.validation.Valid;

@Controller
public class ClienteController {
 
	@Autowired
	
	private IClienteDao clienteDao;
	@RequestMapping(value = "/listar",method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes" );
		model.addAttribute("clientes",clienteDao.findAll());
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo","Formulario de cliente");
		return"form";
		
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		if(id>0) {
			cliente = clienteDao.findOne(id);
		}else {
			return "redirect:/1istar";
		}
		model.put("cliente", cliente);
		model.put("titulo","editar registro");
		return "form";
		
	}
	
	
	@RequestMapping(value="/form", method =RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result,Model model) {
		if(result.hasErrors()) {
		     model.addAttribute("titulo", "Formulario de cliente");
			return "form";
		}
			
		clienteDao.save(cliente);
		return "redirect:listar";
	}
}
