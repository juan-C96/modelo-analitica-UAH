package com.modeloanalitica.uahdatos.jpa.dao;

import com.modeloanalitica.uahdatos.modelo.Curso;

import java.util.List;

public interface ICursoDAO {
    List<Curso> buscarTodos();

    Curso buscarCursoPorId(Long c_id);

    //SE DESPRECIAN PUES NO ES REQUISITO DEL SISTEMA
    void guardarCurso(Curso curso);

    void eliminarCurso(Long c_id);

    void actualizarCurso(Curso curso);
}
