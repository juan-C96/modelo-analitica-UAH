package com.modeloanalitica.uahdatos.servicio.implementacion;

import com.modeloanalitica.uahdatos.jpa.dao.ICursoDAO;
import com.modeloanalitica.uahdatos.modelo.Curso;
import com.modeloanalitica.uahdatos.servicio.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    ICursoDAO cursoDAO;

    @Override
    public List<Curso> buscarTodos() {
        return cursoDAO.buscarTodos();
    }

    @Override
    public Curso buscarCursoPorId(Long c_id) {
        return cursoDAO.buscarCursoPorId(c_id);
    }

    @Override
    public void guardarCurso(Curso curso) {
        cursoDAO.guardarCurso(curso);
    }

    @Override
    public void eliminarCurso(Long c_id) {
        cursoDAO.eliminarCurso(c_id);
    }

    @Override
    public void actualizarCurso(Curso curso) {
        cursoDAO.actualizarCurso(curso);
    }
}
