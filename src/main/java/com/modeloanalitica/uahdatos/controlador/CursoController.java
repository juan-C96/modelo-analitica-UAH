package com.modeloanalitica.uahdatos.controlador;

import com.modeloanalitica.uahdatos.modelo.Curso;
import com.modeloanalitica.uahdatos.servicio.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CursoController {
    @Autowired
    ICursoService cursoService;

    public CursoController(ICursoService cursoService) {
        super();
        this.cursoService = cursoService;
    }

    @GetMapping("/cursos")
    public List<Curso> buscarTodos() {
        return cursoService.buscarTodos();
    }

    @GetMapping("/cursos/{c_id}")
    public Curso buscarCursoPorId(@PathVariable("c_id") Long c_id) {
        return cursoService.buscarCursoPorId(c_id);
    }

    @PostMapping("/cursosS")
    public void guardarCurso(@RequestBody Curso curso) {
        cursoService.guardarCurso(curso);
    }

    @PutMapping("/cursosU")
    public void actualizarCurso(@RequestBody Curso curso) {
        cursoService.actualizarCurso(curso);
    }
    @DeleteMapping("/cursos/{c_id}")
    public void eliminarCurso(@PathVariable("c_id") Long c_id) {
        cursoService.eliminarCurso(c_id);
    }

}
