/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase validadores que se encarga de todos los metodos validar
 *
 * @author Angel
 */
public class Validadores {

    /**
     * Metodo que se encarga de validar el RFC
     *
     * @param s la cadena a validar
     * @return si cumple con el formato RFC
     */
    public boolean validaRfc(String s) {
        CharSequence cadena = s;
        String recadena = "^(?!^.{13}$)(^[A-Z]{4})(^[0-9]{6})(^([A-Z]|[0-9]){3}$)$";
        Pattern pattern = Pattern.compile(recadena);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

    /**
     * Metodo que valida el numero de serie
     *
     * @param s cadena a validar
     * @return si cumple el formato de numero de serie
     */
    public boolean validaNumSerie(String s) {
        CharSequence cadena = s;
        String recadena = "^[A-Z]{3}-\\\\d{3}$";
        Pattern pattern = Pattern.compile(recadena);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

    /**
     * Metodo que valida las caracteristicas del vehiculo
     *
     * @param s cadena a validar
     * @return si cumple el formato de caracteristicad de vehiculo
     */
    public boolean validaCaracteristicasVehiculo(String s) {
        CharSequence cadena = s;
        String recadena = "^[a-zA-Z]{100}$";
        Pattern pattern = Pattern.compile(recadena);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

    /**
     * Metodo que valia el modelo del vehiculo
     *
     * @param s cadena a validar
     * @return si cumple el formato modelo
     */
    public boolean validaModelo(String s) {
        CharSequence cadena = s;
        String recadena = "^[0-9]{1,10}$";
        Pattern pattern = Pattern.compile(recadena);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

    /**
     * Metodo que valida la placa
     *
     * @param s cadena a validar
     * @return si cumple el formato de placa
     */
    public boolean validaPlaca(String s) {
        CharSequence cadena = s;
        String recadena = "^[A-Za-z]{3}-\\\\d{3}$";
        Pattern pattern = Pattern.compile(recadena);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }
}
