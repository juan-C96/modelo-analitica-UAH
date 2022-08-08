package com.modeloanalitica.uahdatos.controlador;

import com.google.gson.*;
import com.modeloanalitica.uahdatos.modelo.*;
import com.modeloanalitica.uahdatos.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class CargaController {

    @Autowired
    IEventoService eventoService;
    @Autowired
    IActorService actorService;
    @Autowired
    IRoleService roleService;
    @Autowired
    ICursoService cursoService;

    public CargaController(IEventoService eventoService, IActorService actorService, IRoleService roleService, ICursoService cursoService) {
        this.eventoService = eventoService;
        this.actorService = actorService;
        this.roleService = roleService;
        this.cursoService = cursoService;
    }

    public LocalDateTime aDate(String date) {
        try {
            String dateFix = date.substring(0, 22);
            LocalDateTime dateTime = LocalDateTime.parse(dateFix);
            return dateTime;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    @GetMapping("/cargarDatos")
    public String cargarDatos() throws ParseException {

        Gson gson = new Gson();

        File folder = new File("C://Users/jcher/OneDrive/Desktop/Escuela/TFM/test/");
        int registro = 1;

        for (File file : folder.listFiles()) {
            System.out.println(registro + "   " + file.getName());
            registro++;

            String ficheroFail = "";
            String fichero = "";

            // try (BufferedReader br = new BufferedReader(new FileReader("C://Users/jcher/OneDrive/Desktop/Escuela/TFM/test/817--output-Friday-June-03-2022-07-47-58.json"))) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    ficheroFail += linea;
                }

                int lastpos = ficheroFail.length();
                fichero = ficheroFail.substring(160, lastpos - 1);

                while (fichero.indexOf("sensor") != -1) {
                    int pos = fichero.indexOf("sensor");
                    fichero = fichero.substring(0, pos - 4) + "," + fichero.substring((pos - 4) + 163);
                }

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            JsonParser parser = new JsonParser();
            Object object = parser.parse(fichero);
            // Obtain Array
            JsonArray gsonArr = (JsonArray) object;
            //System.out.println(gsonArr.toString());

            // for each element of array
            for (JsonElement obj : gsonArr) {

                // Object of array
                JsonObject gsonObj = obj.getAsJsonObject();

                if (!(gsonObj.get("id") == null)) {
                    if (gsonObj.getAsJsonObject("group") != null &&
                            gsonObj.getAsJsonObject("group").get("courseNumber") != null &&
                            gsonObj.getAsJsonObject("group").get("courseNumber").getAsString().equals("26L1H21")) {

                        Evento evento = new Evento();

                        List<Evento> eventos = eventoService.buscarTodos();
                        List<Curso> cursos = cursoService.buscarTodos();
                        List<Actor> actores = actorService.buscarTodos();
                        List<Role> rolesList = roleService.buscarTodos();

                        String action = "";

                        String type = gsonObj.get("type").getAsString();
                        String id = gsonObj.get("id").getAsString();
                        if (!(gsonObj.get("action") == null)) {
                            action = gsonObj.get("action").getAsString();
                        }

                        if (eventos.size() == 0) {
                            evento.setE_tipo(type);
                            evento.setE_uuid_real(id);
                            evento.setE_objeto(file.getName());
                            if (!action.equals("")) {
                                evento.setE_accion(action);
                            }
                            eventoService.guardarEvento(evento);
                        }

                        if (!type.equals("ViewEvent") ||
                                (gsonObj.getAsJsonObject("actor") != null ) &&
                                (gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.externalId").getAsString().equals("chrisitian.dopico"))) {
                            evento.setE_tipo(type);
                            evento.setE_usuarios(gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.externalId").getAsString());
                            evento.setE_uuid_real(id);
                            evento.setE_objeto(file.getName());
                            if (!action.equals("")) {
                                evento.setE_accion(action);
                            }
                            eventoService.guardarEvento(evento);
                        }


                        // CURSO
                        String grupoType = gsonObj.getAsJsonObject("group").get("type").getAsString();

                        Curso curso = new Curso();

                        if (!(grupoType.equals("Group"))) {
                            String cursoId = gsonObj.getAsJsonObject("group").getAsJsonObject("extensions").get("bb:course.id").getAsString();
                            curso.setC_id_real(cursoId);
                        }

                        if (!(gsonObj.getAsJsonObject("group").get("courseNumber") == null)) {
                            String courseNumber = gsonObj.getAsJsonObject("group").get("courseNumber").getAsString();
                            curso.setC_numero(courseNumber);
                        }

                        Long id_curso = null;
                        Boolean flag_2 = true;

                        //Validar si existe el grupo
                        for (int i = 0; i < cursos.size(); i++) {
                            if ((cursos.get(i).getC_id_real()).equals(curso.getC_id_real())) {
                                id_curso = cursos.get(i).getId_curso();
                                flag_2 = false;
                            }
                        }

                        //Agregarlo sino existe
                        if (flag_2) {
                            if (!(gsonObj.get("eventTime") == null)) {
                                String eventTime = gsonObj.get("eventTime").getAsString();
                                LocalDateTime dateTime = aDate(eventTime);

                                curso.setC_fechaInicio(dateTime);
                            }

                            if (action.equals("Started")) {
                                curso.setC_numero_actividades(1);
                            } else if (action.equals("Submitted")) {
                                curso.setC_numero_actividades_terminadas(1);
                            } else {
                                curso.setC_numero_actividades(0);
                                curso.setC_numero_actividades_terminadas(0);
                            }
                            cursoService.guardarCurso(curso);
                        } else {

                            // DATETIME
                            if (!(gsonObj.get("eventTime") == null)) {
                                String eventTime = gsonObj.get("eventTime").getAsString();
                                LocalDateTime dateTime = aDate(eventTime);

                                curso = cursoService.buscarCursoPorId(id_curso);

                                if (curso.getC_fechaInicio().isAfter(dateTime)) {
                                    curso.setC_fechaInicio(dateTime);
                                }

                                LocalDateTime currentTime = LocalDateTime.now();

                                String start = curso.getC_fechaInicio().toString().split("T")[0].split("-")[2] + " "
                                        + curso.getC_fechaInicio().toString().split("T")[0].split("-")[1] + " "
                                        + curso.getC_fechaInicio().toString().split("T")[0].split("-")[0];

                                LocalDateTime fix = aDate(currentTime.toString());
                                String end = fix.toString().split("T")[0].split("-")[2] + " "
                                        + fix.toString().split("T")[0].split("-")[1] + " "
                                        + fix.toString().split("T")[0].split("-")[0];

                                SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

                                Date dateClassStart = myFormat.parse(start);
                                Date dateClassEnd = myFormat.parse(end);

                                long differenceWeek = dateClassEnd.getTime() - dateClassStart.getTime();
                                int programLength = (int) (TimeUnit.DAYS.convert(differenceWeek, TimeUnit.MILLISECONDS) / 7) * 168;

                                curso.setC_tiempo_horas(programLength);

                                if (action.equals("Started")) {
                                    curso.setC_numero_actividades(cursoService.buscarCursoPorId(id_curso).getC_numero_actividades() + 1);
                                } else if (action.equals("Submitted")) {
                                    curso.setC_numero_actividades_terminadas(cursoService.buscarCursoPorId(id_curso).getC_numero_actividades_terminadas() + 1);
                                }

                                // ACTOR
                                if (!(gsonObj.getAsJsonObject("actor") == null)) {

                                    Actor actor = new Actor();

                                    if (!(gsonObj.getAsJsonObject("actor").get("type").getAsString() == null)) {
                                        String actorType = gsonObj.getAsJsonObject("actor").get("type").getAsString();
                                        actor.setA_tipo(actorType);
                                    }

                                    if (!(gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.id") == null)) {
                                        String actorId = gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.id").getAsString();
                                        actor.setA_id_real(actorId);
                                    }

                                    if (!(gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.externalId") == null)) {
                                        String actorUser = gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.externalId").getAsString();
                                        actor.setA_usuario(actorUser);
                                    }

                                    Long id_actor = null;
                                    Boolean flag_actor = true;

                                    for (int i = 0; i < actores.size(); i++) {
                                        if (actores.get(i).getA_id_real().equals(actor.getA_id_real())) {
                                            id_actor = actores.get(i).getA_id();
                                            flag_actor = false;
                                            break;
                                        }
                                    }

                                    if (!(gsonObj.getAsJsonObject("membership") == null)) {

                                        Role roles = new Role();
                                        Boolean flag_role = true;

                                        JsonArray data = gsonObj.getAsJsonObject("membership").get("roles").getAsJsonArray();
                                        List rol = new ArrayList();

                                        for (JsonElement event : data) {
                                            rol.add(event.getAsString());
                                        }

                                        roles.setR_rol(rol.get(0).toString());

                                        Long idRol = null;

                                        for (int i = 0; i < rolesList.size(); i++) {
                                            if (rolesList.get(i).getR_rol().equals(roles.getR_rol())) {
                                                idRol = rolesList.get(i).getR_id();
                                                flag_role = false;
                                                break;
                                            }
                                        }

                                        if (flag_role) {
                                            roleService.guardarRol(roles);
                                            for (int i = 0; i < rolesList.size(); i++) {
                                                if (rolesList.get(i).getR_rol().equals(roles.getR_rol())) {
                                                    idRol = rolesList.get(i).getR_id();
                                                    break;
                                                }
                                            }
                                        }

                                        if (flag_actor) {
                                            actor.setA_roles(roles.getR_rol());
                                            if (action.equals("Submitted")) {
                                                actor.setA_trabajosTerminados(1);
                                            }
                                            actor.setA_ultimaConcexion(dateTime);
                                            actorService.guardarActor(actor);
                                            actores.add(actorService.buscarActorPorId(actor.getA_id()));
                                            curso.setC_actores(actores);
                                            if (cursoService.buscarCursoPorId(id_curso).getC_personas() == null) {
                                                curso.setC_personas(actorService.buscarActorPorId(actor.getA_id()).getA_usuario());
                                            } else {
                                                if (!cursoService.buscarCursoPorId(id_curso).getC_personas().contains(actorService.buscarActorPorId(actor.getA_id()).getA_usuario())) {
                                                    curso.setC_personas(cursoService.buscarCursoPorId(id_curso).getC_personas() + ", " + actorService.buscarActorPorId(actor.getA_id()).getA_usuario());
                                                }
                                            }
                                        } else {
                                            actor = actorService.buscarActorPorId(id_actor);
                                            if (action.equals("Submitted")) {
                                                actor.setA_trabajosTerminados(actor.getA_trabajosTerminados() + 1);
                                            }
                                            if (actor.getA_ultimaConcexion().isBefore(dateTime)) {
                                                actor.setA_ultimaConcexion(dateTime);

                                                if (actorService.buscarActorPorId(id_actor).getA_roles() == null) {
                                                    actor.setA_roles(roles.getR_rol());
                                                    List<Role> listRole = new ArrayList<>();
                                                    listRole.add(roleService.buscarRolPorID(idRol));
                                                    actor.setA_rol(listRole);
                                                }
                                                actorService.actualizarActor(actor);
                                            }
                                        }
                                    }
                                }

                                cursoService.actualizarCurso(curso);


                            }
                        }



/*

                        // ACTION
                        if (!(gsonObj.get("action") == null)) {
                            String action = gsonObj.get("action").getAsString();

//                            System.out.println("ACTION = " + action);

                            evento.setE_accion(action);
                        }

                        // OBJECT
                        if (!(gsonObj.getAsJsonObject("object") == null)) {
                            String objectType = gsonObj.getAsJsonObject("object").get("type").getAsString();

//                            System.out.println("ACTION OBJECT TYPE = " + objectType);

                            if ((objectType.equals("SoftwareApplication"))) {
                                String objectHeaderAgent = gsonObj.getAsJsonObject("object").getAsJsonObject("extensions").get("bb:request.headers.agent").getAsString();
//                                System.out.println("OBJECT HEADERS AGENT = " + objectHeaderAgent);
                                String objectHeaderIp = gsonObj.getAsJsonObject("object").getAsJsonObject("extensions").get("bb:request.headers.ipAddress").getAsString();
//                                System.out.println("OBJECT HEADERS IP ADDRESS = " + objectHeaderIp);
                            }

                            evento.setE_objeto(objectType);
                        }

                        // TARGET
                        if (!(gsonObj.getAsJsonObject("target") == null)) {
                            String TargetType = gsonObj.getAsJsonObject("target").get("type").getAsString();

//                            System.out.println("TARGET TYPE = " + TargetType);
                        }



                        // EDAPP
                        if (!(gsonObj.getAsJsonObject("edApp") == null)) {
                            String edAppType = gsonObj.getAsJsonObject("edApp").get("type").getAsString();

//                            System.out.println("EDAPP TYPE = " + edAppType);
                        }

                        // MEMBERSHIP
                        if (!(gsonObj.getAsJsonObject("membership") == null)) {

                            Role roles = new Role();
                            Actor actor = new Actor();

                            Boolean flag_role = true;

                            String membershipstatus = gsonObj.getAsJsonObject("membership").get("status").getAsString();
                            System.out.println("MEMBERSHIP STATUS = " + membershipstatus);
                            String membershipType = gsonObj.getAsJsonObject("membership").get("type").getAsString();
                            System.out.println("MEMBERSHIP TYPE = " + membershipType);
                            String membershipExtCourseId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:course.id").getAsString();
                            System.out.println("MEMBERSHIP COURSE ID = " + membershipExtCourseId);
                            String membershipExtCourseExtId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:course.externalId").getAsString();
                            System.out.println("MEMBERSHIP COURSE EXT ID = " + membershipExtCourseExtId);
                            String membershipExtUserId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:user.id").getAsString();
                            System.out.println("MEMBERSHIP USER ID = " + membershipExtUserId);
                            String membershipExtUserExtId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:user.externalId").getAsString();
                            System.out.println("MEMBERSHIP USER EXT ID = " + membershipExtUserExtId);

                            JsonArray data = gsonObj.getAsJsonObject("membership").get("roles").getAsJsonArray();
                            List rol = new ArrayList();
                            ;
                            for (JsonElement event : data) {
                                rol.add(event.getAsString());
                            }

                            roles.setR_rol(rol.get(0).toString());

                            Long idRol = null;

                            for (int i = 0; i < roleService.buscarTodos().size(); i++) {
                                if (roleService.buscarTodos().get(i).getR_rol().equals(roles.getR_rol())) {
                                    idRol = roleService.buscarTodos().get(i).getR_id();
                                    flag_role = false;
                                    break;
                                }
                            }

                            if (flag_role) {
                                roleService.guardarRol(roles);
                                for (int i = 0; i < roleService.buscarTodos().size(); i++) {
                                    if (roleService.buscarTodos().get(i).getR_rol().equals(rol.get(0).toString())) {
                                        idRol = roleService.buscarTodos().get(i).getR_id();
                                    }
                                }
                            }

                            for (int i = 0; i < actorService.buscarTodos().size(); i++) {
                                if (actorService.buscarTodos().get(i).getA_rol() == null) {
                                    actor = actorService.buscarTodos().get(i);
                                    actor.setA_roles(roles.getR_rol());
                                    List<Role> listRole = new ArrayList<>();
                                    listRole.add(roleService.buscarRolPorID(idRol));
                                    actor.setA_rol(listRole);
                                    actorService.actualizarActor(actor);
                                }
                            }

//                            System.out.println("MEMBERSHIP ROLES = " + roles.toString());

                            //=====================================================

                            Long idGrupo = null;
                            boolean flag_grupo = true;

                            Grupo grupo = null;
                            List<Actor> actores_grupo = new ArrayList<>();

                            for (int i = 0; i < grupoService.buscarTodos().size(); i++) {
                                if (grupoService.buscarTodos().get(i).getG_id_real().equals(membershipExtCourseId)) {
                                    idGrupo = grupoService.buscarTodos().get(i).getG_id();
                                    flag_grupo = false;
                                    break;
                                }
                            }

                            Long idActor = null;
                            String nombres_actores = "";

                            for (int i = 0; i < actorService.buscarTodos().size(); i++) {
                                if(actorService.buscarTodos().get(i).getA_id_real().equals(membershipExtUserId)){
                                    idActor = actorService.buscarTodos().get(i).getA_id();
                                }
                            }
/*
                            if(idActor == null){
                                Actor newActor = new Actor();
                                newActor.setA_id_real(membershipExtUserId);
                                newActor.setA_tipo();
                                newActor.setA_usuario(membershipExtUserExtId);
                            }

                            if (flag_grupo) {
                                grupo.setG_id_real(membershipExtCourseId);
                                grupo.setG_numero(membershipExtCourseExtId);
                                actores_grupo.add(actorService.buscarActorPorId(idActor));
                                grupo.setG_actores(actores_grupo);
                                nombres_actores = actores_grupo.get(0).getA_usuario();
                                grupo.setG_personas(nombres_actores);
                                grupoService.guardarGrupo(grupo);
                            } else {
                                grupo = grupoService.buscarGrupoPorId(idGrupo);
                                if(idActor != null && (grupo.getG_actores() == null || !grupo.getG_actores().contains(actorService.buscarActorPorId(idActor)))){
                                    if(grupo.getG_actores() == null){
                                        actores_grupo = new ArrayList<>();
                                    }else{
                                        actores_grupo = grupo.getG_actores();
                                    }
                                    actores_grupo.add(actorService.buscarActorPorId(idActor));
                                    List<String> nombres = new ArrayList<>();
                                    for (int i = 0; i < actores_grupo.size(); i++) {
                                        nombres.add(actores_grupo.get(i).getA_usuario());
                                    }
                                    grupo.setG_actores(actores_grupo);
                                    grupo.setG_personas(nombres.toString());
                                    grupoService.actualizarGrupo(grupo);
                                }
                            }

                            //=====================================================

                        }

                        // FEDERATED SESSION
                        if (!(gsonObj.getAsJsonObject("federatedSession") == null)) {

                            String fsStartedAtTime = gsonObj.getAsJsonObject("federatedSession").get("startedAtTime").getAsString();
                            LocalDateTime federatedSessionStartedAtTime = aDate(fsStartedAtTime);
//                            System.out.println("FEDERATED SESSION STARTED AT TIME = " + federatedSessionStartedAtTime);
                            String federatedSessionType = gsonObj.getAsJsonObject("federatedSession").get("type").getAsString();
//                            System.out.println("FEDERATED SESSION TYPE = " + federatedSessionType);
                            String federatedSessionName = gsonObj.getAsJsonObject("federatedSession").get("name").getAsString();
//                            System.out.println("FEDERATED SESSION NAME = " + federatedSessionName);
                            if (!(gsonObj.getAsJsonObject("federatedSession").get("dateCreated") == null)) {
                                String fsDateCreated = gsonObj.getAsJsonObject("federatedSession").get("dateCreated").getAsString();
                                LocalDateTime federatedSessionDateCreated = aDate(fsDateCreated);
//                                System.out.println("FEDERATED SESSION DATE CREATED = " + federatedSessionDateCreated);
                            }
                            if (!(gsonObj.getAsJsonObject("federatedSession").get("dateModified") == null)) {
                                String fsDateModified = gsonObj.getAsJsonObject("federatedSession").get("dateModified").getAsString();
                                LocalDateTime federatedSessionDateModified = aDate(fsDateModified);
//                                System.out.println("FEDERATED SESSION DATE MODIYIED = " + federatedSessionDateModified);
                            }
                            String federatedSessionUserType = gsonObj.getAsJsonObject("federatedSession").getAsJsonObject("user").get("type").getAsString();
//                            System.out.println("FEDERATED SESSION USER TYPE = " + federatedSessionUserType);
                            String federatedSessionUserId = gsonObj.getAsJsonObject("federatedSession").getAsJsonObject("user").getAsJsonObject("extensions").get("bb:user.id").getAsString();
//                            System.out.println("FEDERATED SESSION USER ID = " + federatedSessionUserId);
                            String federatedSessionUserExtId = gsonObj.getAsJsonObject("federatedSession").getAsJsonObject("user").getAsJsonObject("extensions").get("bb:user.externalId").getAsString();
//                            System.out.println("FEDERATED SESSION USER EXT ID = " + federatedSessionUserExtId);
                        }
*/

                        // eventoService.guardarEvento(evento);
                    }
                }
            }
            //file.delete();
        }
        System.out.println("Total de archivos cargados = " + --registro);
        return "redirect:/";
    }


}

