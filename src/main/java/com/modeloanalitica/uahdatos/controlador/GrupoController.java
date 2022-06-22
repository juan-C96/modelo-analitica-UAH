package com.modeloanalitica.uahdatos.controlador;

import com.modeloanalitica.uahdatos.modelo.Grupo;
import com.modeloanalitica.uahdatos.servicio.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GrupoController {

    @Autowired
    IGrupoService grupoService;

    public GrupoController(IGrupoService grupoService) {
        super();
        this.grupoService = grupoService;
    }

    @GetMapping("/grupos")
    public List<Grupo> buscarTodos() {
        return grupoService.buscarTodos();
    }

    @GetMapping("/grupos/{g_id}")
    public Grupo buscarGrupoPorId(@PathVariable("g_id") Long g_id) {
        return grupoService.buscarGrupoPorId(g_id);
    }

    @PostMapping("/gruposS")
    public void guardarGrupo(@RequestBody Grupo grupo) {
        grupoService.guardarGrupo(grupo);
    }

    @PutMapping("/gruposU")
    public void actualizarGrupo(@RequestBody Grupo grupo) {
        grupoService.actualizarGrupo(grupo);
    }
    @DeleteMapping("/grupos/{g_id}")
    public void eliminarGrupo(@PathVariable("g_id") Long g_id) {
        grupoService.eliminarGrupo(g_id);
    }
}
