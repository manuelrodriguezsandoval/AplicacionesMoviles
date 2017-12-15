package inacaptemuco.cl.primeraevaluacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuPrincipal extends AppCompatActivity {

        ImageView bienvenida;
        Button ingreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        bienvenida = (ImageView) findViewById(R.id.img_principal);
        ingreso = (Button) findViewById(R.id.btn_ingreso);
        bienvenida.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.portada));


        ingreso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent ingresar = new Intent(MenuPrincipal.this, MainActivity.class);
                startActivity(ingresar);
            }
        });
    }
}
