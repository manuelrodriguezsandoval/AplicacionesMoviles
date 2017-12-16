package cl.inacap.nuevohorizonte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class activityBuscarRecepcion extends AppCompatActivity implements View.OnClickListener {
    EditText txt_buscar, txt_factura, txt_monto, txt_fechaRecepcion, txt_fechaVencimiento, txt_observaciones;
    EditText usuarioBusqueda, proveedorBusqueda;
    Button btn_buscarRecepcion, btn_actualizar, btn_eliminar;
    TextView etiqueta_factura, txv_resultado;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_recepcion);

        vincularElementos();
        habilitarListener();

    }

    private void habilitarListener() {
        btn_buscarRecepcion.setOnClickListener(this);
    }

    private void vincularElementos() {
        txt_buscar              =   (EditText) findViewById(R.id.txt_busqueda);
        btn_buscarRecepcion     =   (Button) findViewById(R.id.btn_buscarRecepcion);
        etiqueta_factura        =   (TextView) findViewById(R.id.etiqueta_factura);
        txt_factura             =   (EditText) findViewById(R.id.txt_factura);
        txt_monto               =   (EditText) findViewById(R.id.txt_monto);
        txt_fechaRecepcion      =   (EditText) findViewById(R.id.txt_fecha_recepcion);
        txt_fechaVencimiento    =   (EditText) findViewById(R.id.txt_fecha_vencimiento);

        usuarioBusqueda         =   (EditText) findViewById(R.id.txt_usuario);
        proveedorBusqueda       =   (EditText) findViewById(R.id.txt_proveedor);

        txt_observaciones       =   (EditText) findViewById(R.id.txt_observaciones);
        btn_actualizar          =   (Button) findViewById(R.id.btn_actualizar);
        btn_eliminar            =   (Button) findViewById(R.id.btn_eliminar);

    }
    private void consultarDatos(int idConsulta) {

        //Creamos un objeto RequestQueue para efectuar el envío con la librería Volley
        RequestQueue colaSolicitudVolley = Volley.newRequestQueue(this);
        //Ruta al servicio


        String urlServicioAPI ="http://192.168.182.135/app_dev.php/l_r_v_ingresos/"+idConsulta;


        //Configuración de la solicitud. Observar que se utiliza GET.
        StringRequest cadenaSolicitud = new StringRequest(Request.Method.GET, urlServicioAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respuestaRecibida) {
                        //En caso de éxito en la solicitud. Aquí se gestionan los datos de la respuesta.
                        //En este caso se extrae un valor de la respuesta, convirtiendola primero a un objeto JSON.

                        try {
                            JSONObject respuestaJson = new JSONObject(respuestaRecibida);

                            txv_resultado.setText("Respuesta "+respuestaJson.getString("id_ingreso"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //En caso de error en la solicitud
                txv_resultado.setText("Se ha producido un error "+error.getMessage());
            }
        });
        //La solicitud se agrega a la cola y es gestionada por Volley.
        colaSolicitudVolley.add(cadenaSolicitud);

    }
    public void onClick(View view) {
        try {
            int id = Integer.parseInt(txt_buscar.getText().toString());
            consultarDatos(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        };
    }
}
