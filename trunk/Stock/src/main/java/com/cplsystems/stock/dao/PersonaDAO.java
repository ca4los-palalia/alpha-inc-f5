/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;

/**
 * @author Carlos Palalía López
 */

public interface PersonaDAO {

    public void save(Persona persona);
    public void update(Persona persona);
    public void delete(Persona persona);
    public List<Persona> getByDireccion(Direccion direccion);
    public List<Persona> getByContacto(Contacto contacto);
    public Persona getById(Long persona);
    public List<Persona> getAll();
    public List<Persona> getBySexo(Long sexo);

}
