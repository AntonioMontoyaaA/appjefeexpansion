package expansion.neto.com.mx.jefeapp.ui.crear;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.ActivityFinalizaBinding;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentDialogCancelarMd;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentDialogFinalizar;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentDialogGuardarFinaliza;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.DatosPuntuacion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.GuardarFinalizar;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderConsultaFinaliza;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderGuardaFinaliza;
import expansion.neto.com.mx.jefeapp.ui.porterminar.ActivityFinalizaTerminar;

import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentAutoriza.loadingProgress;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class ActivityFinaliza extends AppCompatActivity {

    private ActivityFinalizaBinding binding;

    String mdId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        initDataBinding();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding.btnGuardar.setEnabled(false);
        binding.btnGuardar.setAlpha(.4f);
        progressDialog = new ProgressDialog(ActivityFinaliza.this);
        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        final long mdid = preferences.getLong("mdId", 0);
        final String usuario = preferences.getString("usuario", "");
        getDatos();

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.btnGuardar.setEnabled(false);
                binding.btnGuardar.setAlpha(0.35f);
                GuardarFinalizar guardarFinalizar = new GuardarFinalizar(
                        String.valueOf(mdid),usuario,"1",puntuacion
                );
                loadingProgress(progressDialog, 0);

                ProviderGuardaFinaliza.getInstance(getApplicationContext()).finalizaGuardaMd(guardarFinalizar,
                        new ProviderGuardaFinaliza.InterfaceGuardarFinalizar() {
                            @Override
                            public void resolve(Codigos codigo) {

                                if(codigo.getCodigo()==200){

                                    binding.btnGuardar.setEnabled(true);
                                    binding.btnGuardar.setAlpha(1);

                                    editor.putString("puntuacion", puntuacion);
                                    editor.apply();
                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentDialogGuardarFinaliza dFragment = new FragmentDialogGuardarFinaliza();
                                    dFragment.show(fm, "Dialog Fragment");
                                    binding.btnFinalizar.setEnabled(true);
                                    binding.btnFinalizar.setAlpha(1.0f);
                                    binding.btnGuardar.setEnabled(true);
                                    binding.btnGuardar.setAlpha(1.0f);
                                    loadingProgress(progressDialog, 1);


                                }else{
                                    Toast.makeText(ActivityFinaliza.this, codigo.getMensaje(),
                                            Toast.LENGTH_SHORT).show();

                                    binding.btnGuardar.setEnabled(true);
                                    binding.btnGuardar.setAlpha(1);

                                    binding.btnFinalizar.setEnabled(true);
                                    binding.btnFinalizar.setAlpha(1.0f);
                                    loadingProgress(progressDialog, 1);

                                }
                            }
                            @Override
                            public void reject(Exception e) {
                            }
                        });
            }
        });

        binding.btnFinalizar.setEnabled(false);
        binding.btnFinalizar.setAlpha(0.35f);

        binding.btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnFinalizar.setEnabled(false);
                binding.btnFinalizar.setAlpha(0.35f);
                GuardarFinalizar guardarFinalizar = new GuardarFinalizar(
                        String.valueOf(mdid),usuario,"2",puntuacion
                );
                loadingProgress(progressDialog, 0);

                ProviderGuardaFinaliza.getInstance(getApplicationContext()).finalizaGuardaMd(guardarFinalizar,
                        new ProviderGuardaFinaliza.InterfaceGuardarFinalizar() {
                            @Override
                            public void resolve(Codigos codigo) {

                                if(codigo.getCodigo()==200){
                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentDialogFinalizar dFragment = new FragmentDialogFinalizar();
                                    dFragment.show(fm, "Dialog Fragment Finaliza");
                                    binding.btnGuardar.setEnabled(true);
                                    binding.btnGuardar.setAlpha(1);
                                    loadingProgress(progressDialog, 1);

                                }else{
                                    Toast.makeText(ActivityFinaliza.this, codigo.getMensaje(),
                                            Toast.LENGTH_SHORT).show();

                                    binding.btnGuardar.setEnabled(true);
                                    binding.btnGuardar.setAlpha(1);
                                    loadingProgress(progressDialog, 1);

                                }
                            }

                            @Override
                            public void reject(Exception e) {

                            }
                        });
            }
        });

        binding.flechaAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentDialogCancelarMd dFragment = new FragmentDialogCancelarMd();
                dFragment.show(fm, "Dialog Fragment");
            }
        });

    }

    /**
     * Método que setea la vista con el binding
     */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_finaliza);

    }


    ArrayList<DatosPuntuacion.Factore> factoresMacro;
    ArrayList<DatosPuntuacion.Factore> factoresMicro;
    ProgressDialog progressDialog;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    String puntuacion, categoria;
    public void getDatos(){
        loadingProgress(progressDialog, 0);
        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        long mdid = preferences.getLong("mdId", 0);
        String usuarioId = preferences.getString("usuario", "");

        ProviderConsultaFinaliza.getInstance(this).obtenerPuntos(String.valueOf(mdid), usuarioId, new ProviderConsultaFinaliza.ConsultaPuntos() {
            @Override
            public void resolve(DatosPuntuacion datosPuntuacion) {
                if(datosPuntuacion.getCodigo()==200){

                    binding.btnGuardar.setEnabled(true);
                    binding.btnGuardar.setAlpha(1);
                    factoresMacro = new ArrayList<>();
                    factoresMicro = new ArrayList<>();
                    loadingProgress(progressDialog, 1);

                    SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    binding.textoTipo.setText(datosPuntuacion.getUbicacionMD()+"");

                    categoria = datosPuntuacion.getNomcategoria();
                    for(int i = 0;i<datosPuntuacion.getFactores().size();i++){
                        if(datosPuntuacion.getFactores().get(i).getNombrenivel().equals("TOTAL")){
                            puntuacion = datosPuntuacion.getFactores().get(i).getPuntuacion().toString();
                        }
                    }

                    for(int i = 0;i<datosPuntuacion.getFactores().size();i++){
                        if(datosPuntuacion.getFactores().get(i).getRangoubica().equals("MACRO UBICACION")){
                            factoresMacro.add(datosPuntuacion.getFactores().get(i));
                        }else{
                            factoresMicro.add(datosPuntuacion.getFactores().get(i));
                        }

                    }

                    if(factoresMicro.size()<=0){
                        binding.tituloMicro.setVisibility(View.GONE);
                    }

                    if(factoresMacro.size()<=0){
                        binding.tituloMacro.setVisibility(View.GONE);
                    }

                    generarDetallesMicro(binding, factoresMicro);
                    generarDetallesMacro(binding, factoresMacro);

                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("categoria", categoria);
                    editor.putString("puntuacion", puntuacion);
                    editor.apply();

                }else{
                    Toast.makeText(ActivityFinaliza.this, "Error al guardar tu MD",
                            Toast.LENGTH_SHORT).show();
                    loadingProgress(progressDialog, 1);

                }

            }

            @Override
            public void reject(Exception e) {

            }
        });
    }


    public void generarDetallesMacro(ActivityFinalizaBinding binding,  ArrayList<DatosPuntuacion.Factore> datosPuntuacion){

        Resources resource = this.getResources();
        binding.factores.removeAllViews();
        TableRow rowPlomo = new TableRow(this);
        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
        int paddingDp = 2;

        float density = getResources().getDisplayMetrics().density;
        int paddingPixel = (int)(paddingDp * density);

        for(int i = 0; i < datosPuntuacion.size(); i ++){

            TableRow tbrow = new TableRow(this);
            tbrow.setBackgroundColor(resource.getColor(R.color.blanco));
            TextView t1v1 = new TextView(this);
            t1v1.setTextSize(12);
            t1v1.setText(datosPuntuacion.get(i).getNombrenivel()+"");
            t1v1.setTextColor(resource.getColor(R.color.azul));
            t1v1.setPadding(0, paddingPixel,0,0);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(this);
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");
            t3v1.setTextColor(resource.getColor(R.color.azul));
            t3v1.setGravity(Gravity.LEFT);
            t3v1.setLayoutParams( new TableRow.LayoutParams( 50,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v1);

            TextView t3v2 = new TextView(this);
            t3v2.setTextSize(12);
            t3v2.setText("/"+datosPuntuacion.get(i).getTotalxfactor()+"");
            t3v2.setTextColor(resource.getColor(R.color.azul));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams( new TableRow.LayoutParams( 75,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v2);

            binding.factores.addView(tbrow);
        }
    }

    public void generarDetallesMicro(ActivityFinalizaBinding binding,  ArrayList<DatosPuntuacion.Factore> datosPuntuacion){

        Resources resource = this.getResources();
        binding.factoresMicro.removeAllViews();
        TableRow rowPlomo = new TableRow(this);
        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
        int paddingDp = 2;

        float density = getResources().getDisplayMetrics().density;
        int paddingPixel = (int)(paddingDp * density);

        for(int i = 0; i < datosPuntuacion.size(); i ++){

            TableRow tbrow = new TableRow(this);
            tbrow.setBackgroundColor(resource.getColor(R.color.blanco));
            TextView t1v1 = new TextView(this);
            t1v1.setTextSize(12);
            t1v1.setText(datosPuntuacion.get(i).getNombrenivel()+"");
            t1v1.setTextColor(resource.getColor(R.color.azul));
            t1v1.setPadding(0, paddingPixel,0,0);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(this);
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");
            t3v1.setTextColor(resource.getColor(R.color.azul));
            t3v1.setGravity(Gravity.END);
            t3v1.setLayoutParams( new TableRow.LayoutParams( 50,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v1);

            TextView t3v2 = new TextView(this);
            t3v2.setTextSize(12);
            t3v2.setText("/"+datosPuntuacion.get(i).getTotalxfactor()+"");
            t3v2.setTextColor(resource.getColor(R.color.azul));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams( new TableRow.LayoutParams( 75,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v2);

            binding.factoresMicro.addView(tbrow);
        }
    }


}
