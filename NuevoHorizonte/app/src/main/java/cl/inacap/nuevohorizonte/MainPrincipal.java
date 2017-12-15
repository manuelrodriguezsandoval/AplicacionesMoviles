package cl.inacap.nuevohorizonte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainPrincipal extends AppCompatActivity implements View.OnClickListener {ImageView bienvenida;
    Button ingreso, usuario, buscar, ingresarProveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

        ingreso = (Button) findViewById(R.id.btn_recepción);
        usuario = (Button) findViewById(R.id.btn_usuarios);
        ingresarProveedor = (Button) findViewById(R.id.btn_Proveedor);
        buscar = (Button) findViewById(R.id.btn_recepciones);

        ingreso.setOnClickListener(this);
        usuario.setOnClickListener(this);
        ingresarProveedor.setOnClickListener(this);
        buscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_recepción:
                Intent ingresar = new Intent(MainPrincipal.this, MainActivity.class);
                startActivity(ingresar);
                break;
            case R.id.btn_usuarios:
                Intent ingresarUsuarios = new Intent(MainPrincipal.this, ActivityUsuario.class);
                startActivity(ingresarUsuarios);
                break;
            case R.id.btn_Proveedor:
                Intent ingresarProveedor = new Intent(MainPrincipal.this, Activity_ingresarProveedor.class);
                startActivity(ingresarProveedor);
                break;
            case R.id.btn_recepciones:
                Intent ingresarRecepcion = new Intent(MainPrincipal.this, activityBuscarRecepcion.class);
                startActivity(ingresarRecepcion);
                break;

        }
    }
}

