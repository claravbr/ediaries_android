package com.uclm.louise.ediaries.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.android.material.appbar.MaterialToolbar;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.utils.ExpLVAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsejosActivity extends AppCompatActivity {

    private ExpandableListView listaconsejos;
    private ExpLVAdapter adapter;
    private ArrayList<String> listPregunta;
    private Map<String, List<String>> mapChild;

    MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consejos);

        listaconsejos = (ExpandableListView) findViewById(R.id.listaconsejos);
        listPregunta = new ArrayList<>();
        mapChild = new HashMap<>();

        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationIcon(R.drawable.ic_ab_back_material);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsejosActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                Log.i("Info log", "Vuelta al menu principal");
            }
        });

        cargarDatos();
    }

    private void cargarDatos(){

        List<String> respuestasAtencion = Arrays.asList(
                "Descubre lo que te molesta, evita los desencadenantes, reconoce los primeros signos de tu enfado o nerviosismo.",
                "Tómate un descanso, aléjate de la situación.",
                "Intenta contar hasta 10 despacio, bebe agua.",
                "Date un paseo si la situación lo permite.",
                "Imagina que estás en otro lugar, piensa en un sitio en el que te sientas cómodo.",
                "Respira profundamente: inspira lentamente contando hasta 8 y mantén la respiración contando hasta 4, luego exhala lentamente contando hasta 8. Repítelo 3 o 4 veces.",
                "Practica la relajación muscular, yoga o meditación."
        );

        List<String> respuestasRelajarse = Arrays.asList(
                "Juega con un pequeño objeto entre los dedos, una pelota anti-estrés por ejemplo.",
                "Busca un lugar para estudiar libre de distracciones.",
                "Intenta garabatear o dibujar en un papel mientras escuchas. Solo funciona si puedes dibujar y escuchar al mismo tiempo.",
                "Utiliza diferentes tipos de lápices, rotuladores, bolígrafos para hacer que te resulte más interesante la tarea.",
                "Ten a mano una botella de agua cerca para ir bebiendo mientras trabajas o escuchas.",
                "A veces estudiar o trabajar con música de fondo puede resultar útil. Averigua qué música te ayuda a concentrarte.",
                "Tómate descansos. Trabaja durante 15-20 minutos, luego muévete, levántate o haz algún movimiento. Vuelve al trabajo otros 16 minutos y tómate otro descanso de 2 minutos.",
                "Utiliza un reloj o cronómetro para controlar el tiempo.",
                "Realiza ejercicio físico de manera regular. Incluye el deporte en tu rutina diaria.",
                "Incluye en tu rutina diaria salir al exterior, especialmente a espacios verdes.",
                "Utiliza subrayadores para concentrarte en lo importante que tienes que estudiar.",
                "Para estudiar, puedes probar a grabar lo que necesitas aprender y escucharlo varias veces o repetir la grabación y comprobar que lo has hecho bien.",
                "Algunas personas se concentran más cuando se mueven. Intenta leer de pie o caminando por casa si necesitas moverte.",
                "Hacer ejercicio físico antes de estudiar puede ayudar a concentrarte.",
                "En clase, siéntate delante."
        );

        List<String> respuestasOrganizarse = Arrays.asList(
                "Anota palabras clave en clase mientras escuchas.",
                "Distribuye bien el tiempo.",
                "Calcula el tiempo necesario para realizar una actividad antes de realizarla.",
                "Controla el tiempo y compara el resultado con tu predicción.",
                "Usa cronómetro, avisadores, alarmas o recordatorios en el móvil.",
                "Anota la tarea y fecha de entrega.",
                "Cuando tengas tareas importantes, divide la tarea en pasos más pequeños y anota cada uno de los pasos en el calendario.",
                "Asigna tiempo a cada tarea.",
                "Intenta repartir las cosas que tienes que hacer para que no quede demasiado recargado el horario.",
                "Deja tiempo para imprevistos.",
                "Revisa la agenda cada día. Ponte una rutina para revisar la agenda, por ejemplo, por las mañanas antes de ir al colegio y por las tardes al volver del colegio.",
                "Al llegar a casa, después de comer, repasa la agenda para poder planificarte.",
                "Organiza tus cosas. Coloca tus libros y materiales en una estantería en tu habitación o armario.",
                "Si te gustan los colores, también los puedes utilizar para organizarte. Por ejemplo, rojo para matemáticas, separador azul para inglés. Utiliza clasificadores.",
                "Elige un día y hora de cada semana para limpiar la mochila, mesa o estantería.",
                "Prepara tu mochila para ir al colegio la noche anterior.",
                "Mira el tiempo que hará al día siguiente y prepárate la ropa la noche anterior.",
                "Si trabajas con ordenador para realizar trabajos, haz una copia de seguridad de tus trabajos.",
                "Revisa el trabajo realizado para detectar errores. Para revisar, puede ayudarte leerlo en voz alta para comprobar que lo que hayas escrito tiene sentido y es lo que quieres decir.",
                "Utiliza la opción de revisión de la ortografía en el ordenador.",
                "Organiza las ideas con esquemas, por ejemplo, o por prioridad e importancia."
        );

        List<String> respuestaOrganizarTiempo = Arrays.asList(
                "Establece un tiempo para cuidarte, ducharte, comer, dormir, etc.",
                "Duerme lo suficiente.",
                "Come adecuadamente."
        );

        listPregunta.add("¿Cómo mejoro mi atención?");
        listPregunta.add("¿Qué puedo hacer para relajarme?");
        listPregunta.add("¿Cómo puedo organizarme mejor?");
        listPregunta.add("¿Cómo puedo organizar mi tiempo?");

        mapChild.put(listPregunta.get(0), respuestasAtencion);
        mapChild.put(listPregunta.get(1), respuestasRelajarse);
        mapChild.put(listPregunta.get(2), respuestasOrganizarse);
        mapChild.put(listPregunta.get(3), respuestaOrganizarTiempo);

        adapter = new ExpLVAdapter(listPregunta, mapChild, this);
        listaconsejos.setAdapter((adapter));
    }
}