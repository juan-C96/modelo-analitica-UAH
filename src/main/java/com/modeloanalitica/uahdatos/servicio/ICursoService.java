package com.modeloanalitica.uahdatos.servicio;

import com.modeloanalitica.uahdatos.modelo.Curso;

import java.util.List;

public interface ICursoService {

    List<Curso> buscarTodos();

    Curso buscarCursoPorId(Long c_id);

    //SE DESPRECIAN PUES NO ES REQUISITO DEL SISTEMA
    void guardarCurso(Curso curso);

    void eliminarCurso(Long c_id);

    void actualizarCurso(Curso curso);

}
