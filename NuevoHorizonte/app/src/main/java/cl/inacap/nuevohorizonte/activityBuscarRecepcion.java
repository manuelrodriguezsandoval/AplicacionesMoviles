package cl.inacap.nuevohorizonte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class activityBuscarRecepcion extends AppCompatActivity {
    EditText txt_buscar, txt_factura, txt_monto, txt_fechaRecepcion, txt_fechaencimiento, txt_observaciones;
    Spinner usuarioBusqueda, proveedorBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_recepcion);

    }
}
