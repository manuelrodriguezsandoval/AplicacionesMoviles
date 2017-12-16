package cl.inacap.nuevohorizonte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ActivityUsuario extends AppCompatActivity implements View.OnClickListener {

    TextView txv_resultado;

    EditText nombre_usuario;

    EditText clave_usuario;

    Button ingresa_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        vincularElementos();
        habilitarListener();
    }



    private void habilitarListener() {
        ingresa_usuario.setOnClickListener(this);
    }

    private void vincularElementos() {
        nombre_usuario  =   (EditText) findViewById(R.id.txt_nombreUsuario);
        clave_usuario   =   (EditText) findViewById(R.id.txt_clave);
        ingresa_usuario =   (Button) findViewById(R.id.btn_ingresoUsuario);
        txv_resultado   =   (TextView) findViewById(R.id.txt_resultado);


    }

    private void enviarDatos(String nombre, String clave ) {
        //Definimos un objeto JSON con los datos a enviar a la API mediante PUT
        JSONObject objetoJsonEnvio = new JSONObject();
        try {
            //Aquí se definen los datos a enviar. En este caso sólo uno.
            objetoJsonEnvio.put("nombre",nombre);
            objetoJsonEnvio.put("clave",clave);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //El objeto JSON lo convertimos en un string para enviar a la API. Esto es requerido por api-platform.
        final String datosAEnviar = objetoJsonEnvio.toString();

        //Creamos un objeto RequestQueue para efectuar el envío con la librería Volley
        RequestQueue colaEnvioVolley = Volley.newRequestQueue(this);

        //Definimos la ruta al servicio.
        String urlServicioAPI ="http://192.168.153.130/foos";

        //Configuramos la solicitud a la API mediante un String Request el cual posteriormente agregaremos a la cola de envío (queue)
        //Observar que se utiliza método POST (Puede ser PUT, GET, etc)
        StringRequest cadenaSolicitud = new StringRequest(Request.Method.POST, urlServicioAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respuestaRecibida) {
                        // Bloque de instrucciones cuando la operación es exitosa. Este String
                        // denominado respuestaRecibida puede ser convertido a otros formatos (Por ejemplo json)
                        // En este caso se concatena como texto en un elemento TextView
                        try {

                            txv_resultado.setText("Respuesta "+respuestaRecibida);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Bloque de instrucciones en caso de error. En este caso se muestra en un TextView.
                txv_resultado.setText("Se ha producido un error al agregar "+error.getMessage());
            }

        })
        {
            @Override
            public Map<String, String> getHeaders()  {
                //Se configuran los encabezados HTTP. Establece el formato de envío requerido por la API.
                //La documentación de la API nos indica como acepta solicitudes
                Map<String,String> headers=new HashMap<String,String>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                headers.put("Accept-Encoding", "utf-8");
                return headers;
            }
            @Override
            public byte[] getBody() {
                try {
                    //Se extraen los bytes de los datos a enviar.
                    return datosAEnviar == null ? null : datosAEnviar.getBytes("utf-8");
                } catch (UnsupportedEncodingException ex) {
                    txv_resultado.setText("Error al extraer bytes"+ex.getMessage());
                    return null;
                }
            }
            @Override
            public String getBodyContentType() {
                //En este caso se está forzando a enviar el encabezado HTTP Content-type como se indica
                return "application/json";
            }
        };

        //Finalmente se agrega a la cola de envío con lo cual Volley gestiona la solicitud.
        colaEnvioVolley.add(cadenaSolicitud);
    }

    @Override
    public void onClick(View view) {

        try {
            String ingresar_nombre_usuario = nombre_usuario.getText().toString();
            String ingresar_clave_usuario = clave_usuario.getText().toString();

            enviarDatos(ingresar_nombre_usuario,ingresar_clave_usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
