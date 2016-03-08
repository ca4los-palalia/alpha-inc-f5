package com.cplsystems.stock.app.utils;

import com.cplsystems.stock.services.ProductoService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public class InitData
{
  @Autowired
  private ProductoService productoService;
  
  @PostConstruct
  public void init()
  {
    insertProductos();
  }
  
  private void insertProductos() {}
}
