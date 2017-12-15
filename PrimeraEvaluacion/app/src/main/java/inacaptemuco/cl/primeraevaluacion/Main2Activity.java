package inacaptemuco.cl.primeraevaluacion;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView anio;
    TextView patente;
    TextView marca;
    TextView modelo;
    TextView valoruf;
    TextView antiguedad;
    ImageView estado;
    TextView valorseguro;
    ImageView resultadoImagen;
    String patenteRecibida, marcaRecibida, modeloRecibido;
    int anioRecibido, ufRecibida;
    Metodos acciones = new Metodos();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        patente = (TextView) findViewById(R.id.resultado_patente);
        marca = (TextView) findViewById(R.id.resultados_marca);
        modelo = (TextView) findViewById(R.id.resultado_modelo);
        anio = (TextView) findViewById(R.id.resultado_anio);
        valoruf = (TextView) findViewById(R.id.resultado_uf);
        antiguedad = (TextView) findViewById(R.id.resultado_antiguedad);
        estado = (ImageView) findViewById(R.id.img_asegurado);
        valorseguro = (TextView) findViewById(R.id.resultado_valorSeguro);
        resultadoImagen = (ImageView) findViewById(R.id.img_resultado);

        Bundle bundle = this.getIntent().getExtras();
        patenteRecibida = bundle.getString("patente");
        marcaRecibida = bundle.getString("marca");
        modeloRecibido = bundle.getString("modelo");
        anioRecibido = bundle.getInt("anio");
        ufRecibida = bundle.getInt("valoruf");

        patente.setText(patenteRecibida);
        marca.setText(marcaRecibida);
        modelo.setText(modeloRecibido);
        anio.setText(String.valueOf(anioRecibido));
        valoruf.setText("$ "+String.valueOf(ufRecibida));

        String antiguedadVehiculo = acciones.calcularAntiguedad(anioRecibido);
        antiguedad.setText(antiguedadVehiculo);

        String asegurable = acciones.calcularSeguro(anioRecibido);
        if (asegurable.equals("Es asegurable")) {
            estado.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.asegurado));
        } else {
            estado.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.noasegurado));
        }

        String ResulValorSeguro = acciones.calcularValorSeguro(ufRecibida, anioRecibido);
        valorseguro.setText(ResulValorSeguro);

        mostrarImagen(marcaRecibida);

    }

    private void mostrarImagen(String marca) {

        switch (marca) {
            case "Kia":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.kia));
                break;
            case "Ferrari":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ferrari));
                break;
            case "AUDI":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.audi));
                break;
            case "Mercedes-Benz":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.mercedes));
                break;
            case "BMW":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.bmw));
                break;
            case "Porsche":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.porsche));
                break;
            case "Chevrolet":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.chevrolet));
                break;
            case "Ford":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ford));
                break;
            case "VolksWagen":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.volkswagen));
                break;
            case "Nissan":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nissan));
                break;
            case "Toyota":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.toyota));
                break;
            case "Honda":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.honda));
                break;
            case "Mazda":
                resultadoImagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.mazda));
                break;
        }
    }
}


