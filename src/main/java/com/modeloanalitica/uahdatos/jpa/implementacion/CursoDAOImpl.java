package com.modeloanalitica.uahdatos.jpa.implementacion;

import com.modeloanalitica.uahdatos.jpa.ICursoJPA;
import com.modeloanalitica.uahdatos.jpa.dao.ICursoDAO;
import com.modeloanalitica.uahdatos.modelo.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoDAOImpl implements ICursoDAO {

    @Autowired
    ICursoJPA cursoJPA;

    public CursoDAOImpl(ICursoJPA cursoJPA) {
        super();
        this.cursoJPA = cursoJPA;
    }

    @Override
    public List<Curso> buscarTodos() {
        return cursoJPA.findAll();
    }

    @Override
    public Curso buscarCursoPorId(Long c_id) {
        Optional<Curso> optional = cursoJPA.findById(c_id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardarCurso(Curso curso) {
        cursoJPA.save(curso);
    }

    @Override
    public void eliminarCurso(Long c_id) {
        Optional<Curso> optional = cursoJPA.findById(c_id);
        if(optional.isPresent()){
            cursoJPA.delete(optional.get());
        }
    }

    @Override
    public void actualizarCurso(Curso curso) {
        cursoJPA.save(curso);
    }
}
