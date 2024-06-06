package fr.esgi.asynchrome;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Clé de sauvegarde de l'état du TextView
    private static final String TEXT_STATE = "currentText";

    // Le TextView où nous afficherons les résultats
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser mTextView avec le TextView du layout
        mTextView = findViewById(R.id.textView1);

        // Appliquer les marges système
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Restaurer l'état si possible
        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    // Méthode pour démarrer la tâche asynchrone
    public void startTask(View view) {
        mTextView.setText(R.string.napping); // Mettre à jour le TextView pour afficher "Sieste..."
        new SimpleAsyncTask(mTextView).execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Enregistrer l'état actuel du TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
}
