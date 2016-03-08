package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.CodigoBarrasProducto;
import com.cplsystems.stock.domain.Producto;
import java.util.List;

public abstract interface CodigoBarrasProductoDAO
{
  public abstract void save(CodigoBarrasProducto paramCodigoBarrasProducto);
  
  public abstract void delete(CodigoBarrasProducto paramCodigoBarrasProducto);
  
  public abstract CodigoBarrasProducto getById(Long paramLong);
  
  public abstract List<CodigoBarrasProducto> getAll();
  
  public abstract List<CodigoBarrasProducto> getByCodigo(String paramString);
  
  public abstract List<CodigoBarrasProducto> getByProducto(Producto paramProducto);
}
