package com.julio.energiaInteligente.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/medicoes")
public class MedicaoResource {

	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return "funciona porra";

	}
}
