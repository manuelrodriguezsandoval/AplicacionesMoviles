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


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView logoIngreso;
    TextView labelFactura, labelMonto, labelFechaRecepcion, labelFechaVencimiento, labelUsuario,
            labelProveedor, labelObservaciones;
    EditText txt_Factura, txt_Monto, txt_FechaRecepcion, txt_FechaVencimiento, txt_Observaciones;
    Spinner usuario, proveedor;
    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logoIngreso = (ImageView) findViewById(R.id.img_logo);
        labelFactura = (TextView) findViewById(R.id.etiqueta_factura);
        labelMonto = (TextView) findViewById(R.id.etiqueta_monto);
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
        ingresar.setOnClickListener(this);
        logoIngreso.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.supermercadp));




    }


    @Override
    public void onClick(View view) {


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






