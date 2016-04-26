package com.cplsystems.stock.domain;

public class SistemaOperativo
{
  private String version;
  private String arquitectura;
  private String directorioTemporal;
  private String separadorDeArchivos;
  private String separadorDePath;
  private String usuario;
  private String directorioDeTrabajoDelusuario;
  private String directorioDeInicioDelUsuario;
  
  public String getVersion()
  {
    this.version = System.getProperty("os.version");
    return this.version;
  }
  
  public String getArquitectura()
  {
    this.arquitectura = System.getProperty("os.arch");
    return this.arquitectura;
  }
  
  public String getDirectorioTemporal()
  {
    this.directorioTemporal = System.getProperty("java.io.tmpdir");
    return this.directorioTemporal;
  }
  
  public String getSeparadorDeArchivos()
  {
    this.separadorDeArchivos = System.getProperty("file.separator");
    return this.separadorDeArchivos;
  }
  
  public String getSeparadorDePath()
  {
    this.separadorDePath = System.getProperty("path.separator");
    return this.separadorDePath;
  }
  
  public String getUsuario()
  {
    this.usuario = System.getProperty("user.name");
    return this.usuario;
  }
  
  public String getDirectorioDeTrabajoDelusuario()
  {
    this.directorioDeTrabajoDelusuario = System.getProperty("user.dir");
    return this.directorioDeTrabajoDelusuario;
  }
  
  public String getDirectorioDeInicioDelUsuario()
  {
    this.directorioDeInicioDelUsuario = System.getProperty("user.home");
    return this.directorioDeInicioDelUsuario;
  }
}
