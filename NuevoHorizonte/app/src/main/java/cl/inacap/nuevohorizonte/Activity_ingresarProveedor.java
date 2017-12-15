package cl.inacap.nuevohorizonte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity_ingresarProveedor extends AppCompatActivity implements View.OnClickListener {

    EditText nombreProveedor, tipoProveedor;
    Button ingresarProveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_proveedor);

        nombreProveedor=(EditText)findViewById(R.id.txt_nombreProveedor);
        tipoProveedor=(EditText)findViewById(R.id.txt_tipo_proveedor);
        ingresarProveedor=(Button)findViewById(R.id.btn_ingresoProveedor);

        ingresarProveedor.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {

    }
}
