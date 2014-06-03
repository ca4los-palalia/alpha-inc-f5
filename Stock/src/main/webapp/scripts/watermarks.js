zk.afterMount(function() {
    jq("$buscador").Watermark("Buscar clave, nombre o RFC","gray");
});

zk.afterMount(function() {
    jq("$buscadorAsociacion").Watermark("clave, nombre o RFC","gray");
});


zk.afterMount(function() {
    jq("$buscadorProducto").Watermark("nombre o clave","gray");
});

zk.afterMount(function() {
    jq("$areaEntrada").Watermark("Teclear el nombre dl área","gray");
});

zk.afterMount(function() {
    jq("$puestoId").Watermark("Teclear el nombre de la posición","gray");
});
