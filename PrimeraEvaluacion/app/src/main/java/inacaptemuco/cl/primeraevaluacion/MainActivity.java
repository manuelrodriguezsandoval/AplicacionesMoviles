package inacaptemuco.cl.primeraevaluacion;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static String urlStringMarcas;
    private static String urlString;
    TextView valoruf;
    JSONObject objetoJSON;
    Button activity2;
    Button actualizar;
    EditText anio1;
    EditText patente;
    Spinner marca;
    EditText modelo;
    String seleccionaMarca;
    ArrayList<String> listaMarcas = new ArrayList<String>();
    Calendar fecha = new GregorianCalendar();
    int anio = fecha.get(Calendar.YEAR);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anio1 = (EditText) findViewById(R.id.txt_anio);
        patente = (EditText) findViewById(R.id.txt_patente);
        marca = (Spinner) findViewById(R.id.txt_marca);
        modelo = (EditText) findViewById(R.id.txt_modelo);
        actualizar = (Button) findViewById(R.id.btn_actualizar);
        activity2 = (Button) findViewById(R.id.btn_calcular);
        urlString = "http://portal.unap.cl/kb/aula_virtual/serviciosremotos/datos-uf-dia.php";
        urlStringMarcas = "https://fipe.parallelum.com.br/api/v1/carros/marcas";
        valoruf = (TextView) findViewById(R.id.txt_uf);
        new ProcessJSON().execute(urlString, urlStringMarcas);

        actualizar.setOnClickListener(this);
        activity2.setOnClickListener(this);
        new ProcessJSON().execute(urlString, urlStringMarcas);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_calcular:

                try {
                    if (patente.getText().toString().isEmpty()) {
                        patente.setError("Debe ingresar patente");
                    } else if (marca.getAdapter().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "No es posible registrar Marca", Toast.LENGTH_SHORT).show();
                    } else if (modelo.getText().toString().isEmpty()) {
                        modelo.setError("Ingresar modelo de vehículo");
                    } else if (anio1.getText().toString().isEmpty()) {
                        anio1.setError("Año del vehiculo por favor");
                    } else if (Integer.parseInt(anio1.getText().toString()) > anio) {
                        anio1.getError();
                        Toast.makeText(getApplicationContext(), "Año ingresado no válido", Toast.LENGTH_SHORT).show();
                    } else if (valoruf.getText().toString().isEmpty()) {
                        valoruf.getError();
                        Toast.makeText(getApplicationContext(), "Valor UF está vacío", Toast.LENGTH_SHORT).show();
                    } else {

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
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "No es posible registrar Marca", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_actualizar:
                new ProcessJSON().execute(urlString, urlStringMarcas);
                break;
        }

    }


    private class ProcessJSON extends AsyncTask<String, Void, Wrapper> {


        protected Wrapper doInBackground(String... strings) {

            String stream1 = null;
            String stream = null;
            String urlstring = strings[0];
            String urlStringMarcas = strings[1];


            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlStringMarcas);
            stream1 = hh.GetHTTPData(urlstring);

            // Return the data from specified url

            Wrapper w = new Wrapper();
            w.almacenaMarcas = stream;
            w.almacenaUF = stream1;
            return w;
        }


        protected void onPostExecute(Wrapper w) {
            TextView valoruf = (TextView) findViewById(R.id.txt_uf);


            if (w != null) {
                try {
                    // Obtenemos todos los datos HTTP mediante un objeto JSONObject
                    JSONObject reader = new JSONObject(w.almacenaUF);
                    JSONArray reader1 = new JSONArray(w.almacenaMarcas);

                    // Obtenemos uno de los valores que necesitamos
                    String uf = reader.getString("VALOR_UF");


                    valoruf.setText(uf);
                    for (int i = 0; i < reader1.length(); i++) {
                        objetoJSON = reader1.getJSONObject(i);
                        seleccionaMarca = objetoJSON.getString("nome");
                        listaMarcas.add(seleccionaMarca);

                    }
                    marca.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_list_item_activated_1, listaMarcas));

                    Toast.makeText(getApplicationContext(), "Conexión Establecida", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No se detecta conexión a Internet", Toast.LENGTH_SHORT).show();
                    patente.setText("");
                    anio1.setText("");
                    modelo.setText("");
                    valoruf.setText("");


                }


            }


        }

    }


}

class Wrapper {
    public String almacenaMarcas;
    public String almacenaUF;
}















