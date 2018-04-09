package mx.edu.unipolidgo.apppropinas;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Configuracion extends AppCompatActivity {

    EditText edtPropina;
    Button btnAplicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        edtPropina = (EditText)this.findViewById(R.id.P_Propina);
        btnAplicar = (Button)this.findViewById(R.id.Aplicar);

        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarPropina();
            }
        });

    }

    protected void guardarPropina() {
        SharedPreferences preferences =
                this.getSharedPreferences("AppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = preferences.edit();
        prefEditor.putString("porcentajePropina", edtPropina.getText().toString());
        prefEditor.commit();
        this.finish();
    }
}
