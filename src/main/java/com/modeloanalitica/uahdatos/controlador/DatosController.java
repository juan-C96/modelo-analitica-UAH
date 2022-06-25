package com.modeloanalitica.uahdatos.controlador;

import com.google.gson.*;
import com.modeloanalitica.uahdatos.modelo.Evento;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class DatosController {

    public LocalDateTime aDate(String date){
        try {
            String dateFix = date.substring(0,22);
            LocalDateTime dateTime = LocalDateTime.parse(dateFix);
            return dateTime;
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    @GetMapping("/cargar")
    public void cargarDatos() {
        Gson gson = new Gson();

        String fichero = "";

        try (BufferedReader br = new BufferedReader(new FileReader("C://Users/jcher/OneDrive/Desktop/Escuela/TFM/1--output-Friday-June-03-2022-07-46-27.json"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
            //System.out.println(fichero);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        JsonParser parser = new JsonParser();
        Object object =  parser.parse(fichero);
        // Obtain Array
        JsonArray gsonArr = (JsonArray) object;
        //System.out.println(gsonArr.toString());

        // for each element of array
        for (JsonElement obj : gsonArr) {

            // Object of array
            JsonObject gsonObj = obj.getAsJsonObject();
            System.out.println(gsonObj.toString());

            // EVENTO
            String type = gsonObj.get("type").getAsString();

            // ACTOR
            String actorType = gsonObj.getAsJsonObject("actor").get("type").getAsString();
            String actorId = gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.id").getAsString();
            String actorUser = gsonObj.getAsJsonObject("actor").getAsJsonObject("extensions").get("bb:user.externalId").getAsString();

            // ACTION
            String action = gsonObj.get("action").getAsString();
            String objectType= gsonObj.getAsJsonObject("object").get("type").getAsString();

            // TARGET
            String TargetType= gsonObj.getAsJsonObject("target").get("type").getAsString();

            // DATETIME
            String eventTime = gsonObj.get("eventTime").getAsString();
            LocalDateTime dateTime = aDate(eventTime);
            String edAppType= gsonObj.getAsJsonObject("edApp").get("type").getAsString();

            // GRUPO
            String grupoType = gsonObj.getAsJsonObject("group").get("type").getAsString();
            String grupoId = gsonObj.getAsJsonObject("group").getAsJsonObject("extensions").get("bb:course.id").getAsString();
            String grupoNumber = gsonObj.getAsJsonObject("group").get("courseNumber").getAsString();

            // MEMBERSHIP
            String membershipstatus = gsonObj.getAsJsonObject("membership").get("status").getAsString();
            String membershipType = gsonObj.getAsJsonObject("membership").get("type").getAsString();
            String membershipExtCourseId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:course.id").getAsString();
            String membershipExtCourseExtId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:course.externalId").getAsString();
            String membershipExtUserId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:user.id").getAsString();
            String membershipExtUserExtId = gsonObj.getAsJsonObject("membership").getAsJsonObject("extensions").get("bb:user.externalId").getAsString();

            JsonArray data = gsonObj.getAsJsonObject("membership").get("roles").getAsJsonArray();
            List roles = new ArrayList();
            for (JsonElement event : data) {
                roles.add(event.getAsString());
            }

            // FEDERATED SESSION
            String fsStartedAtTime = gsonObj.getAsJsonObject("federatedSession").get("startedAtTime").getAsString();
            LocalDateTime federatedSessionStartedAtTime = aDate(fsStartedAtTime);
            String federatedSessionType = gsonObj.getAsJsonObject("federatedSession").get("type").getAsString();
            String federatedSessionName = gsonObj.getAsJsonObject("federatedSession").get("name").getAsString();
            String fsDateCreated = gsonObj.getAsJsonObject("federatedSession").get("dateCreated").getAsString();
            LocalDateTime federatedSessionDateCreated = aDate(fsDateCreated);
            String federatedSessionUserType = gsonObj.getAsJsonObject("federatedSession").getAsJsonObject("user").get("type").getAsString();
            String federatedSessionUserId = gsonObj.getAsJsonObject("federatedSession").getAsJsonObject("user").getAsJsonObject("extensions").get("bb:user.id").getAsString();
            String federatedSessionUserExtId = gsonObj.getAsJsonObject("federatedSession").getAsJsonObject("user").getAsJsonObject("extensions").get("bb:user.externalId").getAsString();

            System.out.println("=====================");
            System.out.println("====    EVENT   =====");
            System.out.println("=====================");
            System.out.println("TIPO = " + type);

            System.out.println("=====================");
            System.out.println("====    ACTOR   =====");
            System.out.println("=====================");
            System.out.println("ACTOR TYPE = " + actorType);
            System.out.println("ACTOR ID = " + actorId);
            System.out.println("ACTOR USER = " + actorUser);

            System.out.println("=====================");
            System.out.println("=====   ACTION  =====");
            System.out.println("=====================");
            System.out.println("ACTION = " + action);
            System.out.println("ACTION OBJECT TYPE = " + objectType);

            System.out.println("=====================");
            System.out.println("====   TARGET  ======");
            System.out.println("=====================");
            System.out.println("TARGET TYPE = " + TargetType);

            System.out.println("=====================");
            System.out.println("==== EVENT TIME =====");
            System.out.println("=====================");
            System.out.println("EVENTTIME = " + dateTime);
            System.out.println("EDAPP TYPE = " + edAppType);

            System.out.println("=====================");
            System.out.println("=====   GROUP   =====");
            System.out.println("=====================");
            System.out.println("GROUP TYPE = " + grupoType);
            System.out.println("GROUP ID = " + grupoId);
            System.out.println("GROUP NUMBER = " + grupoNumber);

            System.out.println("=====================");
            System.out.println("==== MEMBERSHIP =====");
            System.out.println("=====================");
            System.out.println("MEMBERSHIP TYPE = " + membershipType);
            System.out.println("MEMBERSHIP STATUS = " + membershipstatus);
            System.out.println("MEMBERSHIP COURSE ID = " + membershipExtCourseId);
            System.out.println("MEMBERSHIP COURSE EXT ID = " + membershipExtCourseExtId);
            System.out.println("MEMBERSHIP USER ID = " + membershipExtUserId);
            System.out.println("MEMBERSHIP USER EXT ID = " + membershipExtUserExtId);
            System.out.println("MEMBERSHIP ROLES = " + roles.toString());

            System.out.println("=====================");
            System.out.println("= FEDERATED SESSION =");
            System.out.println("=====================");
            System.out.println("FEDERATED SESSION STARTED AT TIME = " + federatedSessionStartedAtTime);
            System.out.println("FEDERATED SESSION TYPE = " + federatedSessionType);
            System.out.println("FEDERATED SESSION NAME = " + federatedSessionName);
            System.out.println("FEDERATED SESSION DATE CREATED = " + federatedSessionDateCreated);
            System.out.println("FEDERATED SESSION USER TYPE = " + federatedSessionUserType);
            System.out.println("FEDERATED SESSION USER ID = " + federatedSessionUserId);
            System.out.println("FEDERATED SESSION USER EXT ID = " + federatedSessionUserExtId);

/*
            // List of primitive elements
           JsonArray data = gsonObj.get("actor").getAsJsonArray();
            List actorm = new ArrayList();
            for (JsonElement event : data) {
                actorm.add(event.getAsString());
                System.out.println("ACTOR = " + actorm.toString());
            }

            // Object Constructor
                FootballPlayer iniesta = new FootballPlayer(dorsal, name,
                        listDemarcation, team);
                System.out.println(iniesta);
            }
            for (int i = 0; i < listDemarcation.size(); i++) {
                System.out.println(listDemarcation.get(i).toString());
            }
            */
        }
    }
}
