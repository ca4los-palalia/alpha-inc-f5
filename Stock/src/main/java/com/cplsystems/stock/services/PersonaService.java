/**
 * 
 */
package com.cplsystems.stock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.PersonaDAO;


/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 *
 */
@Service
public class PersonaService {

	 @Autowired
	 private PersonaDAO personaDAO;
}
