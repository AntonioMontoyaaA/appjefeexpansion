package expansion.neto.com.mx.jefeapp.cameraApi2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.FragmentEdithPhotoBinding;

public class FragmentEditPhoto extends DialogFragment {
    FragmentEdithPhotoBinding binding;
    Uri photoURI;
    private Bitmap bitmap = null;
    public String direcionFile, imgSaved;
    public static final String TAG = "FragmentEditPhoto";
    public static int width,height;
    private Bitmap bitmap2 = null;
    OutputStream os;
    File image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edith_photo, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), Uri.fromFile(new File(direcionFile)));
            binding.lienzo.setBitmap(bitmap);
        } catch (IOException io) {
            io.printStackTrace();
            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
        }
        binding.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Lienzo.bandera_logo == true ) {
                    Lienzo.bandera_logo = false;
                    binding.lienzo.setDrawingCacheEnabled( true );
                    //UUID.randomUUID().toString()
                    //imgSaved = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), binding.lienzo.getDrawingCache(), "adolfo" + ".png", "drawing");
                    bitmap2 = binding.lienzo.getDrawingCache();
                    try {
                        File storageDir = getContext().getExternalFilesDir( Environment.DIRECTORY_PICTURES );
                        image = File.createTempFile( "arc",  /* prefix */
                                ".png",         /* suffix */
                                storageDir      /* directory */
                        );
                        os = new FileOutputStream( image );
                        bitmap2.compress( Bitmap.CompressFormat.PNG, 100, os );
                        os.flush();
                        os.close();
                    } catch (IOException e) {
                        Log.e( getClass().getSimpleName(), "Error writing bitmap", e );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (image != null) {
                        listener.guardarFoto( image.getAbsolutePath() );
                    } else {
                        Snackbar.make( view, "¡ERROR! La imagen no se ha podido guardar", Snackbar.LENGTH_LONG ).setAction( "Action", null ).show();
                    }
                }else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
                    builder.setTitle( "⚠ ERROR" )
                            .setMessage( "Lo sentimos debe insertar bien el Logo de la empresa para poder continuar" )
                            .setNeutralButton( "REINTENTAR", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            } );
                    builder.show();
                }

            }
        });
        binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = getActivity();
                if (null != activity) {
                    new AlertDialog.Builder(activity)
                            .setMessage(R.string.intro_message)
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                }
            }
        });

    }

    public void setDirecionFile(String direcionFile) {
        this.direcionFile = direcionFile;
    }


    private FragmentEditPhoto.FragmentEditPhotoInterface listener;

    public interface FragmentEditPhotoInterface {
        void guardarFoto(String ImgCuadro);
    }

    public void setListener(FragmentEditPhoto.FragmentEditPhotoInterface listener) {
        this.listener = listener;
    }

    public void onResume() {
        super.onResume();

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        System.out.println( "ancho absoluto en pixels "+ width );
        height = metrics.heightPixels;
        System.out.println( "alto absoluto en pixels " + height );

        Window window = getDialog().getWindow();


        if (width <= 400 && height <= 400){

            window.setLayout(240, 240);
        }else if (width <= 500){
            window.setLayout( 450, 450);
        }else{
            window.setLayout(1100, 1100);
        }

        window.setGravity(Gravity.CENTER);
    }
}
