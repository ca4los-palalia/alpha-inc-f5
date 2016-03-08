package com.cplsystems.stock.app.vm.requisicion.bandeja;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionBandejaVariables;
import com.cplsystems.stock.domain.Requisicion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver({DelegatingVariableResolver.class})
public class RequisicionBandejaVM
  extends RequisicionBandejaVariables
{
  private static final long serialVersionUID = 3854968949208776845L;
  
  @Init
  public void init()
  {
    super.init();
  }
  
  @Command
  public void transferirRequsicionToFormular(@BindingParam("index") Integer index)
  {
    if (index != null)
    {
      Requisicion toTransfer = (Requisicion)this.requisiciones.get(index.intValue());
      toTransfer.setBuscarRequisicion(toTransfer.getFolio());
      Map<String, Object> args = new HashMap();
      args.put("requisicion", toTransfer);
      BindUtils.postGlobalCommand(null, null, "loadRequisicionFromInbox", args);
      
      this.stockUtils.redirect("/modulos/requisicion/requisicion.zul");
    }
  }
}
