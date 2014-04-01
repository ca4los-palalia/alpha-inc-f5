/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.PersonaDAO;
import com.cplsystems.stock.domain.Persona;

@Service
public class PersonaService {

	@Autowired
	private PersonaDAO personaDAO;

	public void save(Persona persona){
		personaDAO.save(persona);
	}

	public void update(Persona persona){
		personaDAO.update(persona);
	}

	public Persona getById(Long persona){
		return personaDAO.getById(persona);
	}

	public List<Persona> getAll(){
		return personaDAO.getAll();
	}
	/**
	 * @return Lista de personas de determinado sexo
	 * **/
	public List<Persona> getBySexo(Long sexo){
		return personaDAO.getBySexo(sexo);
	}
}
