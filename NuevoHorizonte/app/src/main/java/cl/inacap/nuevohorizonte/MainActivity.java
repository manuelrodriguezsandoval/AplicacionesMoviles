package cl.inacap.nuevohorizonte;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView logoIngreso;
    TextView labelFactura, labelMonto, labelFechaRecepcion, labelFechaVencimiento, labelUsuario,
            labelProveedor, labelObservaciones, txv_resultado;
    EditText txt_Factura, txt_Monto, txt_FechaRecepcion, txt_FechaVencimiento, txt_Observaciones;
    Spinner usuario, proveedor;
    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logoIngreso     = (ImageView) findViewById(R.id.img_logo);
        labelFactura    = (TextView) findViewById(R.id.etiqueta_factura);
        labelMonto      = (TextView) findViewById(R.id.etiqueta_monto);
        labelFechaRecepcion = (TextView) findViewById(R.id.etiqueta_fecha_recepcion);
        labelFechaVencimiento = (TextView) findViewById(R.id.etiqueta_fecha_vencimiento);
        labelUsuario = (TextView) findViewById(R.id.etiqueta_usuario);
        labelProveedor = (TextView) findViewById(R.id.etiqueta_proveedor);
        labelObservaciones = (TextView) findViewById(R.id.etiqueta_observacion);
        txt_Factura = (EditText) findViewById(R.id.txt_factura);
        txt_Monto = (EditText) findViewById(R.id.txt_monto);
        txt_FechaRecepcion = (EditText) findViewById(R.id.txt_fecha_recepcion);
        txt_FechaVencimiento = (EditText) findViewById(R.id.txt_fecha_vencimiento);
        txt_Observaciones = (EditText) findViewById(R.id.txt_observaciones);
        usuario = (Spinner) findViewById(R.id.spinner_usuario);
        proveedor = (Spinner) findViewById(R.id.spinner_proveedor);
        ingresar = (Button) findViewById(R.id.btn_ingreso);
        logoIngreso.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.supermercadp));




    }


    private void habilitarListener() {
        ingresar.setOnClickListener(this);
    }


    private void enviarDatos(String enviar_factura, String enviar_monto,
                             String enviar_fechaRecepcion,String enviar_fechaVencimiento,String enviar_observaciones ) {
        //Definimos un objeto JSON con los datos a enviar a la API mediante PUT
        JSONObject objetoJsonEnvio = new JSONObject();
        try {
            //Aquí se definen los datos a enviar. En este caso sólo uno.
            objetoJsonEnvio.put("numeroFactura",enviar_factura);
            objetoJsonEnvio.put("monto",enviar_monto);
            objetoJsonEnvio.put("fechaRecepcion",enviar_fechaRecepcion);
            objetoJsonEnvio.put("fechaVencimiento",enviar_fechaVencimiento);
            objetoJsonEnvio.put("observaciones",enviar_observaciones);

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
        habilitarListener();

        try {
            String enviar_factura = txt_Factura.getText().toString();
            String enviar_monto = txt_Monto.getText().toString();
            String enviar_fechaRecepcion = txt_FechaRecepcion.getText().toString();
            String enviar_fechaVencimiento = txt_FechaVencimiento.getText().toString();

            String enviar_observaciones = txt_Observaciones.getText().toString();

            enviarDatos(enviar_factura,enviar_monto,
                    enviar_fechaRecepcion,enviar_fechaVencimiento,enviar_observaciones);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}























   /* public void invocaCalendario(){



        Calendar mcurrentDate=Calendar.getInstance();
        int anio =mcurrentDate.get(Calendar.YEAR);
        int mes =mcurrentDate.get(Calendar.MONTH);
        int dia=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String currentdate= ss.format(date);
        txt_FechaRecepcion.setText(String.valueOf(currentdate));

        DatePickerDialog mDatePicker=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                // Tu código
            }
        },anio, mes, dia);
        mDatePicker.setTitle("Selecciona Fecha");

        mDatePicker.show();

    }*/






