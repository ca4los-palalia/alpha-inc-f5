/**
 * 
 */
package com.cplsystems.stock.app.vm.producto.utils;

import java.io.Serializable;
import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

import com.cplsystems.stock.domain.Producto;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class ProductoValidator extends AbstractValidator implements
		Serializable {

	private static final long serialVersionUID = 175310348606245201L;
	private Map<String, Property> beanProps;

	public void validate(ValidationContext ctx) {
		beanProps = ctx.getProperties(ctx.getProperty().getBase());
		Producto producto = (Producto) beanProps.get(".").getBase();
		if (producto != null) {
			validateProductRequiredFields(ctx, producto);
		}
	}

	private void validateProductRequiredFields(ValidationContext ctx,
			Producto producto) {
		if (producto.getClave() == null || producto.getClave().isEmpty()) {
			this.addInvalidMessage(ctx, "clave",
					"La clave es un valor requerido");
		}
		if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
			this.addInvalidMessage(ctx, "nombre",
					"El nombre del producto es un valor requerido");
		}
		if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
			this.addInvalidMessage(ctx, "precio",
					"El precio es un valor requerido");
		}
		if (producto.getUnidad() == null) {
			this.addInvalidMessage(ctx, "unidad",
					"La unidad es un valo requerido");
		}
	}

}
