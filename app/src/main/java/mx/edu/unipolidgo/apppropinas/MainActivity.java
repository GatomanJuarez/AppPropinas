package mx.edu.unipolidgo.apppropinas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText EdtCantidad;
    Button btnCalcular;
    TextView txtPorcentaje;
    TextView txtPropina;
    TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar menu = (Toolbar) findViewById(R.id.menu);
        setSupportActionBar(menu);


        EdtCantidad = (EditText)this.findViewById(R.id.cantidad);
        btnCalcular = (Button)this.findViewById(R.id.calcular);
        txtPorcentaje = (TextView)this.findViewById(R.id.porcentaje_propina);
        txtPropina = (TextView)this.findViewById(R.id.propina);
        txtTotal = (TextView)this.findViewById(R.id.total);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double prop = calcularPropina();
                obtenerPropina();
                txtPropina.setText(String.format("%.2f", prop));
                txtTotal.setText(String.format("%.2f", calcularTotal(prop)));
            }
        });
    }

    protected void obtenerPropina(){
        SharedPreferences preferencias = this.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        String porcentaje = preferencias.getString("porcentajePropina", "15.0");
        txtPorcentaje.setText(String.format("%s%%", porcentaje));
    }
    private double calcularPropina(){
        double subT = Double.parseDouble(EdtCantidad.getText().toString());

        SharedPreferences preferencias = this.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        double porcentaje = Double.parseDouble(preferencias.getString("porcentajePropina", "15.0"));

        return (subT*(porcentaje/100));
    }
    private double calcularTotal(double propina){
        return (Float.parseFloat(EdtCantidad.getText().toString()) + propina);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    //Respond to menu selections
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle item selection
        switch (item.getItemId()){
            case R.id.configuracion:
                this.empezarConfiguracion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void empezarConfiguracion() {
        Intent i = new Intent(getBaseContext(), Configuracion.class);
        startActivity(i);
    }
}
