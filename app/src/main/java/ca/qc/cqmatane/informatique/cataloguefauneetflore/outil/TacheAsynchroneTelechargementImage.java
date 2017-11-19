package ca.qc.cqmatane.informatique.cataloguefauneetflore.outil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class TacheAsynchroneTelechargementImage extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> referenceVueImage;

    public TacheAsynchroneTelechargementImage(ImageView vueImage){
        referenceVueImage = new WeakReference<ImageView>(vueImage);
    }

    @Override
    protected Bitmap doInBackground(String... parametres){
        return telechargerBitmap(parametres[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        if(isCancelled()){
            bitmap = null;
        }

        if(referenceVueImage != null){
            ImageView vueImage = referenceVueImage.get();
            if(vueImage != null){
                if(bitmap != null){
                    vueImage.setImageBitmap(bitmap);
                }
                else{
                    Drawable substitut = vueImage.getContext().getResources().getDrawable(android.R.drawable.presence_offline);
                    vueImage.setImageDrawable(substitut);
                }
            }
        }
    }

    private Bitmap telechargerBitmap(String url){
        HttpURLConnection connexionUrl = null;
        try{
            URL uri = new URL(url);
            connexionUrl = (HttpURLConnection) uri.openConnection();
            int codeStatut = connexionUrl.getResponseCode();
            if(codeStatut != HttpURLConnection.HTTP_OK){
                return null;
            }

            InputStream fluxEntree = connexionUrl.getInputStream();
            if(fluxEntree != null){
                Bitmap bitmap = BitmapFactory.decodeStream(fluxEntree);
                return bitmap;
            }
        }
        catch(Exception e){
            if(connexionUrl != null)
                connexionUrl.disconnect();
            Log.w("TelechargementImage", "Erreur lors du telechargement de l\'image depuis " + url);
        }
        finally {
            if(connexionUrl != null)
                connexionUrl.disconnect();
        }
        return null;
    }
}
