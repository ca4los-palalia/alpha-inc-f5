package com.cplsystems.stock.app.vm.producto.utils;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class ProductoValidator extends AbstractValidator implements Serializable {
	private static final long serialVersionUID = 175310348606245201L;
	private Map<String, Property> beanProps;

	public void validate(ValidationContext ctx) {
		this.beanProps = ctx.getProperties(ctx.getProperty().getBase());
		Producto producto = (Producto) ((Property) this.beanProps.get(".")).getBase();
		if (producto != null) {
			validateProductRequiredFields(ctx, producto);
		}
	}

	private void validateProductRequiredFields(ValidationContext ctx, Producto producto) {
		StringBuilder errores = new StringBuilder();
		errores.append("Antes de continuar verifique que los siguientes campos contengan la informaci�n requerida: ");
		List<String> fields = new ArrayList();
		if ((producto.getClave() == null) || (producto.getClave().isEmpty())) {
			fields.add("Clave");
		}
		if ((producto.getNombre() == null) || (producto.getNombre().isEmpty())) {
			if (!fields.isEmpty()) {
				fields.add(", nombre");
			} else if (fields.isEmpty()) {
				fields.add("Nombre");
			}
		}
		if ((producto.getPrecio() == null) || (producto.getPrecio().floatValue() <= 0.0F)) {
			if (!fields.isEmpty()) {
				fields.add(", precio");
			} else if (fields.isEmpty()) {
				fields.add("Precio");
			}
		}
		if (producto.getUnidad() == null) {
			if (!fields.isEmpty()) {
				fields.add(" y unidad");
			} else if (fields.isEmpty()) {
				fields.add("Unidad");
			}
		}
		if (fields.size() > 0) {
			addInvalidMessage(ctx, "");
			for (String string : fields) {
				errores.append(string);
			}
			StockUtils.showSuccessmessage(errores.toString(), "warning", Integer.valueOf(0), null);
		}
	}
}
