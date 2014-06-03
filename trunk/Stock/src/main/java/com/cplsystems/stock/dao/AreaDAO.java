/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Area;


/**
 * @author Carlos Palalía López
 */
public interface AreaDAO {

	public void save(Area area);
	
	public void update(Area area);

	public void delete(Area area);

	public Area getById(Long idArea);

	public List<Area> getAll();
}
