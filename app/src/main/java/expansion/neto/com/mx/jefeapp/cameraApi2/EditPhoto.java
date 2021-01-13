package expansion.neto.com.mx.jefeapp.cameraApi2;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import expansion.neto.com.mx.jefeapp.R;
public class EditPhoto extends AppCompatActivity implements Serializable{


    transient Lienzo lienzo;
    private transient  Bitmap bitmap = null;
    public String imgSaved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);
        lienzo = findViewById(R.id.lienzo);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.setDrawingCacheEnabled(true);
                imgSaved = MediaStore.Images.Media.insertImage(getContentResolver(), lienzo.getDrawingCache(), UUID.randomUUID().toString() + ".png", "drawing");
                if (imgSaved != null) {
                    Snackbar.make(view, "¡dibujo salvado correctamente en la galería!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    if(listener != null){
                        Toast.makeText(EditPhoto.this, "diferente de null", Toast.LENGTH_SHORT).show();
                        listener.OnGetUrl(imgSaved);
                    }else{
                        Toast.makeText(EditPhoto.this, "liseter es mull", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(view, "¡ERROR! La imagen no se ha podido guardar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(new File(bundle.getString("direcionFile"))));
            String a = bundle.getString("direcionFile");
            Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
            lienzo.setBitmap(bitmap);
            this.listener = (EditPhoto.EdithPhotoInterface) bundle.getSerializable("inter");
        } catch (IOException io) {
            io.printStackTrace();
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
    }

    public EditPhoto.EdithPhotoInterface listener;
    public interface EdithPhotoInterface extends Serializable {
        void OnGetUrl(String s);
    }
    public void setListener(EditPhoto.EdithPhotoInterface listener){
        this.listener = listener;
    }


}