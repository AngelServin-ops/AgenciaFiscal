/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import dominio.*;
import implementaciones.*;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utils.ConfiguracionPaginado;

/**
 *
 * @author Angel
 */
public class TestDAOs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Inicializa el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionPU");
        EntityManager em = emf.createEntityManager();

        // DAOs
        PersonaDAO personaDAO = new PersonaDAO();
        LicenciaDAO licenciaDAO = new LicenciaDAO();
        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        PlacaDAO placaDAO = new PlacaDAO();
        TramiteDAO tramiteDAO = new TramiteDAO();

        try {

            // FASE 1: INSERCIÓN INICIAL DE DATOS (EVITANDO DUPLICADOS)
            long totalInicial = personaDAO.contarPersonas();
            System.out.println("Personas encontradas al inicio: " + totalInicial);

            // Solo inserta personas si la BD está vacía
            if (totalInicial == 0) {
                System.out.println("INSERTANDO PERSONAS");
                personaDAO.insertarPersonas();
                long totalDespues = personaDAO.contarPersonas();
                System.out.println("Total de personas insertadas: " + totalDespues);
            } else {
                System.out.println("OMITIENDO INSERCIÓN: YA EXISTEN " + totalInicial + " PERSONAS");
            }

            // FASE 2: PRUEBAS PARA JUAN GONZÁLEZ (RFC: JUAG8609081W1)
            String rfc1 = "JUAG8609081W1";
            Persona persona1 = personaDAO.buscarPersonasRFC(rfc1);
            System.out.println("\n--- PRUEBAS PARA: " + persona1.getNombre() + " " + persona1.getApellidoPaterno() + " ---");

            // Insertar una licencia
            System.out.println("\n[P1] INSERTANDO LICENCIA");
            licenciaDAO.insertarTramiteLicencia(persona1, 1500.0, 3, 0);
            boolean tieneLicencia = licenciaDAO.validarLicenciaVigente(rfc1);
            System.out.println("¿Tiene licencia vigente?: " + tieneLicencia);

            // Registrar un vehículo
            System.out.println("\n[P1] REGISTRANDO VEHÍCULO ");
            Automovil auto1 = new Automovil();
            auto1.setSerie("SJN1234");
            auto1.setMarca("Nissan");
            auto1.setLinea("Versa");
            auto1.setModelo(2020);
            auto1.setColor("Gris");
            vehiculoDAO.registrarVehiculoPersona(auto1, persona1);
            System.out.println("Vehículo registrado correctamente.");

            // Generar y asignar placa
            System.out.println("\n[P1] GENERANDO Y ASIGNANDO PLACA");
            String nuevaPlaca1 = placaDAO.generarPlaca();
            System.out.println("Placa generada: " + nuevaPlaca1);
            placaDAO.insertarTramitePlacasNuevo(persona1, auto1, nuevaPlaca1, 1200.0);
            System.out.println("Placa registrada para el vehículo.");

            // FASE 3: PRUEBAS PARA PEDRO ALVAREZ (RFC: PEAH8805206G4)
            String rfc2 = "PEAH8805206G4";
            Persona persona2 = personaDAO.buscarPersonasRFC(rfc2);
            System.out.println("\n--- PRUEBAS PARA: " + persona2.getNombre() + " " + persona2.getApellidoPaterno() + " ---");

            // Registrar un vehículo
            System.out.println("\n[P2] REGISTRANDO SEGUNDO VEHÍCULO");
            Automovil auto2 = new Automovil();
            auto2.setSerie("HDA567");
            auto2.setMarca("Honda");
            auto2.setLinea("Civic");
            auto2.setModelo(2018);
            auto2.setColor("Rojo");
            vehiculoDAO.registrarVehiculoPersona(auto2, persona2);
            System.out.println("Vehículo registrado correctamente.");

            // Generar y asignar placa
            System.out.println("\n[P2] GENERANDO Y ASIGNANDO PLACA");
            String nuevaPlaca2 = placaDAO.generarPlaca();
            System.out.println("Placa generada: " + nuevaPlaca2);
            placaDAO.insertarTramitePlacasNuevo(persona2, auto2, nuevaPlaca2, 1350.0);
            System.out.println("Placa registrada para el segundo vehículo.");

            // FASE 4: CONSULTAS FINALES
            ConfiguracionPaginado paginado = new ConfiguracionPaginado(0, 10);

            // Consultar los vehículos de la Persona 1
            System.out.println("\nCONSULTANDO VEHÍCULOS DE JUAN");
            List<Automovil> autos1 = vehiculoDAO.consultaVehiculos(paginado, persona1);
            for (Automovil a : autos1) {
                System.out.println("Automóvil: " + a.getMarca() + " " + a.getLinea() + " - " + a.getColor());
            }

            // Consultar los vehículos de la Persona 2
            System.out.println("\nCONSULTANDO VEHÍCULOS DE PEDRO");
            List<Automovil> autos2 = vehiculoDAO.consultaVehiculos(paginado, persona2);
            for (Automovil a : autos2) {
                System.out.println("Automóvil: " + a.getMarca() + " " + a.getLinea() + " - " + a.getColor());
            }

            System.out.println("\nPRUEBAS COMPLETADAS EXITOSAMENTE ");

        } catch (Exception e) {
            System.err.println("\n ERROR DURANTE LA EJECUCIÓN DE PRUEBAS");
            e.printStackTrace();
        }
    }
}
