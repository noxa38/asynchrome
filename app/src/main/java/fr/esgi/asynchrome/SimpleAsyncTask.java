package fr.esgi.asynchrome;

import android.os.AsyncTask;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

    // Référence faible au TextView pour éviter les fuites de mémoire
    private WeakReference<TextView> mTextView;

    // Constructeur prenant un TextView comme paramètre
    public SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Générer un nombre aléatoire entre 0 et 10
        Random random = new Random();
        int sleepTime = random.nextInt(11) * 200; // Multipliez par 200 pour obtenir une pause plus longue
        try {
            // Faire dormir le thread pendant une durée aléatoire
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Retourner une chaîne indiquant la durée de sommeil
        return "Enfin réveillé après avoir dormi pendant " + sleepTime + " millisecondes !";
    }

    @Override
    protected void onPostExecute(String result) {
        // Récupérer la référence TextView
        TextView textView = mTextView.get();
        // Vérifier si la référence est toujours valide et le TextView est toujours disponible
        if (textView != null) {
            // Mettre à jour le TextView avec le résultat de la tâche asynchrone
            textView.setText(result);
        }
    }
}
