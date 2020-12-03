package es.studium.datospersonales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    //Declaración de variables
    EditText textoNombre, textoApellidos, textoEdad;
    RadioGroup radioGroup;
    RadioButton radioH, radioM;
    Spinner comboEstaCiv;
    Switch switchHijs;
    Button btnGenerate, btnClear;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazamos las vistas de los componentes con el código, "inicializamos"
        textoNombre = findViewById(R.id.textNombre);
        textoApellidos = findViewById(R.id.textApellidos);
        textoEdad = findViewById(R.id.textEdad);
        radioGroup = findViewById(R.id.radioGroupGenero);
        comboEstaCiv = findViewById(R.id.comboEstadoCivil);
        switchHijs = (Switch) findViewById(R.id.switchHijos);
        switchHijs.setOnClickListener(this);


        //Gracias a la clase ArrayAdapter creamos un objeto que guarde una lista a partir de los datos almacenados en un array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.comboEstadoCivil, android.R.layout.simple_spinner_item);
        comboEstaCiv.setAdapter(adapter);
        //Se enlaza con las vistas gracias al id y se añade el listener del botón generar
        btnGenerate = findViewById(R.id.buttonGenerar);
        btnGenerate.setOnClickListener(this);
        //Se enlaza con las vistas gracias al id y se añade el listener del botón limpiar
        btnClear = findViewById(R.id.buttonLimpiar);
        btnClear.setOnClickListener(this);

        message = findViewById(R.id.textLabel);
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.switchHijos)
        {
            if (switchHijs.isChecked())
            {

                Toast.makeText(this, "Si tiene hijo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No tiene hijo", Toast.LENGTH_SHORT).show();
            }
        }
        if (v.equals(btnGenerate))
        {
            generarNotificacion();
        }
        if (v.equals(btnClear))
        {
            LimpiarCampos();
        }
    }

    //Método que genera la notificación según los datos introducidos
    public void generarNotificacion()
    {
        String nombre = "";
        String apellidos = "";
        String edad = "";
        String total = "";
        String genero = "";
        String hijo = "";
        String estadCiv=" ";

        //Convertimos la cadena texto a entero y evaluamos la edad introducida
        if (Integer.parseInt(String.valueOf(textoEdad.getText())) > 18)
        {
            edad = "Mayor de edad";
        }
        else
        {
           edad = String.valueOf(textoEdad.getText());
        }
        /*if (radioH.isChecked()==true)
        {
            genero= "Hombre";
        }
        else
        {
            genero= "Mujer";
        }*/

        if (switchHijs.isChecked())
        {
            hijo = "Con hijos";
        }
        else
        {
            hijo = "Sin hijos";
        }


        nombre = String.valueOf(textoNombre.getText());
        apellidos = String.valueOf(textoApellidos.getText());
        estadCiv = String.valueOf(comboEstaCiv.getSelectedItem());
        total = apellidos + "," + " " + nombre + "," + " " + edad + ","+" "+genero + " " + estadCiv + "," + " " + hijo;
        message.setText(total);

    }

    public void LimpiarCampos()
    {
        textoNombre.setText("");
        textoApellidos.setText("");
        textoEdad.setText("");
        radioH.setChecked(false);
        radioM.setChecked(false);
        comboEstaCiv.setSelection(0);
        switchHijs.setChecked(false);
        message.setText("");


    }
}
