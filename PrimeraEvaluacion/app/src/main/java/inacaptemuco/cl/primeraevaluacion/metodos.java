package inacaptemuco.cl.primeraevaluacion;

import android.content.Context;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Manuel Rodríguez on 19-09-2017.
 */

public class Metodos {


    public String calcularAntiguedad(int anioRecibido) {

        String antiguo;
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int antiguedad = anio - anioRecibido;

        if (anioRecibido == anio) {
            antiguo = "El vehículo es del año";

        } else if (anioRecibido == anio - 1) {
            antiguo = "El vehículo tiene 1 año";
        } else {
            antiguo = "El vehículo tiene " + antiguedad + " años";


        }
        return antiguo;
    }

    public String calcularSeguro(int anioRecibido) {

        String asegurable;
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);

        if (anioRecibido >= anio - 10) {
            asegurable = "Es asegurable";

        } else {
            asegurable = "No es asegurable";
        }
        return asegurable;
    }

    public String calcularValorSeguro(int ufRecibida, int anioRecibido) {

        Calendar fecha = new GregorianCalendar();

        int anio = fecha.get(Calendar.YEAR);
        int antiguedad = anio - anioRecibido;
        int resultados = (int) ((ufRecibida * (0.1 * antiguedad)));
        int resultados2 = (anioRecibido + 1) - anio;
        int igualdad = (int) (ufRecibida * (0.1 * resultados2));

        String mensaje;

        if (anioRecibido == anio) {
            mensaje = "El valor del seguro es de $" + igualdad + " pesos";
        } else if (anioRecibido >= anio - 10) {
            mensaje = "El valor del seguro es de $" + resultados + " pesos";
        } else {
            mensaje = "Mayor de 10 años no es asegurable";
        }
        return mensaje;


    }

}