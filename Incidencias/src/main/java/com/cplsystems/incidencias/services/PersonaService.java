/**
 * 
 */
package com.cplsystems.incidencias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.incidencias.dao.PersonaDAO;
import com.cplsystems.incidencias.domain.Persona;

/**
 * 
 * 
 */

@Service
public class PersonaService {

    @Autowired
    private PersonaDAO personaDAO;

    public void save(Persona persona) {
	personaDAO.save(persona);
    }

}
