package com.modeloanalitica.uahdatos.controlador;

import com.google.gson.*;
import com.modeloanalitica.uahdatos.modelo.Actor;
import com.modeloanalitica.uahdatos.modelo.Evento;
import com.modeloanalitica.uahdatos.modelo.Grupo;
import com.modeloanalitica.uahdatos.modelo.Role;
import com.modeloanalitica.uahdatos.servicio.IActorService;
import com.modeloanalitica.uahdatos.servicio.IEventoService;
import com.modeloanalitica.uahdatos.servicio.IGrupoService;
import com.modeloanalitica.uahdatos.servicio.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DatosController {

    @Autowired
    IEventoService eventoService;
    @Autowired
    IActorService actorService;
    @Autowired
    IGrupoService grupoService;
    @Autowired
    IRoleService roleService;

    public DatosController(IEventoService eventoService, IActorService actorService, IGrupoService grupoService, IRoleService roleService) {
        this.eventoService = eventoService;
        this.actorService = actorService;
        this.grupoService = grupoService;
        this.roleService = roleService;
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

    @GetMapping("/cargar")
    public String cargarDatos() {

        Gson gson = new Gson();

        String ficheroFail = "";
        String fichero = "";


        File folder = new File("C://Users/jcher/OneDrive/Desktop/Escuela/TFM/test/");
        int registro = 1;

        for (File file : folder.listFiles()) {
            System.out.println(registro + "   " + file.getName());
            registro++;

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

                Evento evento = new Evento();

                // Object of array
                JsonObject gsonObj = obj.getAsJsonObject();
                //System.out.println(gsonObj.toString());

                // EVENTO
                if (!(gsonObj.get("id") == null)) {
                    if (gsonObj.getAsJsonObject("group") != null &&
                            gsonObj.getAsJsonObject("group").get("courseNumber") != null &&
                            gsonObj.getAsJsonObject("group").get("courseNumber").getAsString().equals("26L1H21")) {

                        String type = gsonObj.get("type").getAsString();
                        String id = gsonObj.get("id").getAsString();

//                        System.out.println("=====================");
//                        System.out.println("====    EVENT   =====");
//                        System.out.println("=====================");
//                        System.out.println("EVENT TIPO = " + type);
//                        System.out.println("EVENT ID = " + id);

                        evento.setE_uuid_real(id);
                        evento.setE_tipo(type);

                        // ACTOR
                        if (!(gsonObj.getAsJsonObject("actor") == null)) {

                            String actorType = gsonObj.getAsJsonObject("actor").get("type").getAsString();
//                            System.out.println("ACTOR TYPE = " + actorType);

                            Actor actor = new Actor();

                            actor.setA_tipo(actorType);

                            if (!(gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.id") == null)) {
                                String actorId = gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.id").getAsString();
//                                System.out.println("ACTOR ID = " + actorId);

                                actor.setA_id_real(actorId);
                            }
                            if (!(gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.externalId") == null)) {
                                String actorUser = gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.externalId").getAsString();
//                                System.out.println("ACTOR USER = " + actorUser);

                                actor.setA_usuario(actorUser);
                            }

                            List<Actor> actores = actorService.buscarTodos();
                            Long id_actor = null;
                            Boolean flag = true;

                            for (int i = 0; i < actores.size(); i++) {
                                if (actores.get(i).getA_id_real().equals(actor.getA_id_real())) {
                                    id_actor = actores.get(i).getA_id();
                                    flag = false;
                                }
                            }

                            if (flag) {
                                actorService.guardarActor(actor);
                                evento.setE_actor(actorService.buscarActorPorId(actor.getA_id()));
                                evento.setE_usuarios(actorService.buscarActorPorId(actor.getA_id()).getA_usuario());
                            } else {
                                evento.setE_actor(actorService.buscarActorPorId(id_actor));
                                evento.setE_usuarios(actorService.buscarActorPorId(id_actor).getA_usuario());
                            }
                        }

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

                        // DATETIME
                        if (!(gsonObj.get("eventTime") == null)) {
                            String eventTime = gsonObj.get("eventTime").getAsString();
                            LocalDateTime dateTime = aDate(eventTime);

//                            System.out.println("EVENTTIME = " + dateTime);

                            evento.setE_datetime(dateTime);
                        }

                        // EDAPP
                        if (!(gsonObj.getAsJsonObject("edApp") == null)) {
                            String edAppType = gsonObj.getAsJsonObject("edApp").get("type").getAsString();

//                            System.out.println("EDAPP TYPE = " + edAppType);
                        }

                        // GRUPO
                        if (!(gsonObj.getAsJsonObject("group") == null)) {
                            String grupoType = gsonObj.getAsJsonObject("group").get("type").getAsString();

//                            System.out.println("GROUP TYPE = " + grupoType);

                            Grupo grupo = new Grupo();
                            grupo.setG_tipo(grupoType);

                            if (!(grupoType.equals("Group"))) {
                                String grupoId = gsonObj.getAsJsonObject("group").getAsJsonObject("extensions").get("bb:course.id").getAsString();
//                                System.out.println("GROUP ID = " + grupoId);
                                grupo.setG_id_real(grupoId);
                            }

                            if (!(gsonObj.getAsJsonObject("group").get("courseNumber") == null)) {
                                String grupoNumber = gsonObj.getAsJsonObject("group").get("courseNumber").getAsString();
//                                System.out.println("GROUP NUMBER = " + grupoNumber);
                                grupo.setG_numero(grupoNumber);
                            }

                            List<Grupo> grupos = grupoService.buscarTodos();
                            Long id_grupo = null;
                            Boolean flag_2 = true;

                            for (int i = 0; i < grupos.size(); i++) {
                                if ((grupos.get(i).getG_id_real().equals(grupo.getG_id_real())) && (grupos.get(i).getG_tipo().equals(grupo.getG_tipo()))) {
                                    id_grupo = grupos.get(i).getG_id();
                                    flag_2 = false;
                                }
                            }

                            if (flag_2) {
                                grupoService.guardarGrupo(grupo);
                                evento.setE_grupo(grupoService.buscarGrupoPorId(grupo.getG_id()));
                                evento.setE_grupos(grupoService.buscarGrupoPorId(grupo.getG_id()).getG_numero());
                            } else {
                                evento.setE_grupo(grupoService.buscarGrupoPorId(id_grupo));
                                evento.setE_grupos(grupoService.buscarGrupoPorId(id_grupo).getG_numero());
                            }
                        }

                        // MEMBERSHIP
                        if (!(gsonObj.getAsJsonObject("membership") == null)) {

                            Role roles = new Role();
                            Actor actor = new Actor();

                            Boolean flag_role = true;

                            String membershipstatus = gsonObj.getAsJsonObject("membership").get("status").getAsString();
//                            System.out.println("MEMBERSHIP STATUS = " + membershipstatus);
                            String membershipType = gsonObj.getAsJsonObject("membership").get("type").getAsString();
//                            System.out.println("MEMBERSHIP TYPE = " + membershipType);
                            String membershipExtCourseId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:course.id").getAsString();
//                            System.out.println("MEMBERSHIP COURSE ID = " + membershipExtCourseId);
                            String membershipExtCourseExtId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:course.externalId").getAsString();
//                            System.out.println("MEMBERSHIP COURSE EXT ID = " + membershipExtCourseExtId);
                            String membershipExtUserId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:user.id").getAsString();
//                            System.out.println("MEMBERSHIP USER ID = " + membershipExtUserId);
                            String membershipExtUserExtId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:user.externalId").getAsString();
//                            System.out.println("MEMBERSHIP USER EXT ID = " + membershipExtUserExtId);

                            JsonArray data = gsonObj.getAsJsonObject("membership").get("roles").getAsJsonArray();
                            List rol = new ArrayList();;
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
                        eventoService.guardarEvento(evento);
                    }
                }
            }
        }
        System.out.println("Total de archivos cargados = " + --registro);
        return "redirect:/";
    }
}