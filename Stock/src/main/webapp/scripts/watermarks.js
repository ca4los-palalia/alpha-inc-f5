zk.afterMount(function() {
    jq("$buscador").Watermark("Buscar clave, nombre o RFC","gray");
});

zk.afterMount(function() {
    jq("$buscadorAsociacion").Watermark("clave, nombre o RFC","gray");
});


zk.afterMount(function() {
    jq("$buscadorProducto").Watermark("Ingresar nombre o clave del producto","gray");
});

zk.afterMount(function() {
    jq("$areaEntrada").Watermark("Teclear el nombre dl área","gray");
});

zk.afterMount(function() {
    jq("$puestoId").Watermark("Teclear el nombre de la posición","gray");
});

zk.afterMount(function() {
    jq("$buscarRequisicionId").Watermark("Buscar fólio o unidad responsiva","gray");
});

zk.afterMount(function() {
    jq("$buscadorConcentradoId").Watermark("clave productos o folio de requicición","gray");
});

zk.afterMount(function() {
    jq("$buscadorRequisicionId").Watermark("Ingrese folio de requisicion","gray");
});
zk.afterMount(function() {
    jq("$correoDesarrolloUserId").Watermark("Correo de desarrollo","gray");
});
zk.afterMount(function() {
    jq("$correoDesarrolloPassId").Watermark("contraseña ","gray");
});

zk.afterMount(function() {
    jq("$buscarCotizacionId").Watermark("Fólio de requisicion, cotizacion o proveedor","gray");
});
zk.afterMount(function() {
    jq("$buscarOrdCompraId").Watermark("Fólio de Orden o proveedor","gray");
});

