package inacaptemuco.cl.primeraevaluacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {


    Button activity2;
    EditText anio1;
    EditText patente;
    Spinner marca;
    EditText modelo;
    EditText valoruf;
    String[] seleccionaMarca = new String[]{
            "Seleccionar Marca",
            "Kia", "Ferrari", "Audi", "Mercedes-Benz",
            "BMW", "Porsche", "Chevrolet", "Ford", "VolksWagen", "Nissan",
            "Toyota", "Honda", "Mazda"};
    Calendar fecha = new GregorianCalendar();
    int anio = fecha.get(Calendar.YEAR);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anio1 = (EditText) findViewById(R.id.txt_anio);
        patente = (EditText) findViewById(R.id.txt_patente);
        marca = (Spinner) findViewById(R.id.txt_marca);
        ArrayAdapter<String> lista = new ArrayAdapter<String>
                (getBaseContext(), android.R.layout.simple_list_item_activated_1, seleccionaMarca);
        marca.setAdapter(lista);

        modelo = (EditText) findViewById(R.id.txt_modelo);
        valoruf = (EditText) findViewById(R.id.txt_uf);
        activity2 = (Button) findViewById(R.id.btn_calcular);
        activity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (patente.getText().toString().isEmpty()) {
                    patente.setError("Debe ingresar patente");
                } else if (marca.getSelectedItem().toString().equals("Seleccionar Marca")) {
                    Toast.makeText(getApplicationContext(), "Seleccione un marca", Toast.LENGTH_SHORT).show();
                } else if (modelo.getText().toString().isEmpty()) {
                    modelo.setError("Ingresar modelo de vehículo");
                } else if (anio1.getText().toString().isEmpty()) {
                    anio1.setError("Año del vehiculo por favor");
                } else if (Integer.parseInt(anio1.getText().toString()) > anio) {
                    anio1.getError();
                    Toast.makeText(getApplicationContext(), "Año ingresado no válido", Toast.LENGTH_SHORT).show();
                } else if (valoruf.getText().toString().isEmpty()) {
                    valoruf.setError("Debe ingresar valor de la UF");
                } else


                    EnviarDatos();
            }


        });
    }


    private void EnviarDatos() {
        String patenteEnviar, marcaEnviar, modeloEnviar;
        int anioEnviar, ufEnviar;
        patenteEnviar = patente.getText().toString();
        marcaEnviar = marca.getSelectedItem().toString();
        modeloEnviar = modelo.getText().toString();
        anioEnviar = Integer.parseInt(anio1.getText().toString());
        ufEnviar = Integer.parseInt(valoruf.getText().toString());

        Intent activity2 = new Intent(MainActivity.this, Main2Activity.class);
        activity2.putExtra("patente", patenteEnviar);
        activity2.putExtra("marca", marcaEnviar);
        activity2.putExtra("modelo", modeloEnviar);
        activity2.putExtra("anio", anioEnviar);
        activity2.putExtra("valoruf", ufEnviar);
        startActivity(activity2);

    }
}



