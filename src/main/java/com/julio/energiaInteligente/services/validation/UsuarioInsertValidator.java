package com.julio.energiaInteligente.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.julio.energiaInteligente.domain.Usuario;
import com.julio.energiaInteligente.dto.UsuarioNewDTO;
import com.julio.energiaInteligente.repositories.UsuarioRepository;
import com.julio.energiaInteligente.resources.exceptions.FieldMessage;
import com.julio.energiaInteligente.services.validation.utils.BR;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {
		
		@Autowired
		private UsuarioRepository repo;
		
		@Override
		public void initialize(UsuarioInsert ann) {

		}

		@Override
		public boolean isValid(UsuarioNewDTO objDto, ConstraintValidatorContext context) {
			List<FieldMessage> list = new ArrayList<>();
			
			if (!BR.isValidCPF(objDto.getCpf())) {
				list.add(new FieldMessage("cpfOuCnpj", "CPF Invalido"));
			}
			
			Usuario aux = repo.findByEmail(objDto.getEmail());
			if (aux != null) {
				list.add(new FieldMessage("Email", "Email ja existente"));
			}
			
			for (FieldMessage e : list) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
						.addConstraintViolation();
			}
			return list.isEmpty();
		}

	}
