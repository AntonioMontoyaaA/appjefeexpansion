package expansion.neto.com.mx.jefeapp.ui.porterminar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

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

import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentAutoriza.loadingProgress;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class ActivityFinalizaTerminar extends AppCompatActivity {

    private ActivityFinalizaBinding binding;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        initDataBinding();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        progressDialog = new ProgressDialog(ActivityFinalizaTerminar.this);

        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        final String mdid = preferences.getString("mdIdterminar", "");
        final String usuario = preferences.getString("usuario", "");
        getDatos();

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnGuardar.setEnabled(false);
                binding.btnGuardar.setAlpha(0.35f);
                GuardarFinalizar guardarFinalizar = new GuardarFinalizar(
                        mdid,usuario,"1",puntuacion
                );

                loadingProgress(progressDialog, 0);

                ProviderGuardaFinaliza.getInstance(getApplicationContext()).finalizaGuardaMd(guardarFinalizar,
                        new ProviderGuardaFinaliza.InterfaceGuardarFinalizar() {
                            @Override
                            public void resolve(Codigos codigo) {
                                if(codigo.getCodigo()==200){
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
                                    Toast.makeText(ActivityFinalizaTerminar.this, codigo.getMensaje(),
                                            Toast.LENGTH_SHORT).show();
                                    binding.btnGuardar.setEnabled(true);
                                    binding.btnGuardar.setAlpha(1.0f);
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

        binding.btnGuardar.setEnabled(false);
        binding.btnGuardar.setAlpha(0.35f);

        binding.btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnFinalizar.setEnabled(false);
                binding.btnFinalizar.setAlpha(.4f);
                GuardarFinalizar guardarFinalizar = new GuardarFinalizar(
                        mdid,usuario,"2", puntuacion
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
                                    binding.btnFinalizar.setEnabled(true);
                                    binding.btnFinalizar.setAlpha(1);
                                    loadingProgress(progressDialog, 1);

                                }else{
                                    Toast.makeText(ActivityFinalizaTerminar.this, codigo.getMensaje(),
                                            Toast.LENGTH_SHORT).show();

                                    binding.btnGuardar.setEnabled(true);
                                    binding.btnGuardar.setAlpha(1);
                                    binding.btnFinalizar.setEnabled(true);
                                    binding.btnFinalizar.setAlpha(1);
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
    ArrayList<DatosPuntuacion.Factore> faltantes;
    ArrayList<DatosPuntuacion.Factore> faltantesMicro;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    String puntuacion, categoria;
    public void getDatos(){
        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdid = preferences.getString("mdIdterminar", "");
        String usuarioId = preferences.getString("usuario", "");
        loadingProgress(progressDialog, 0);
        ProviderConsultaFinaliza.getInstance(this).obtenerPuntos(mdid, usuarioId, new ProviderConsultaFinaliza.ConsultaPuntos() {
            @Override
            public void resolve(DatosPuntuacion datosPuntuacion) {
                if(datosPuntuacion.getCodigo()==200){

                    factoresMacro = new ArrayList<>();
                    factoresMicro = new ArrayList<>();
                    faltantes = new ArrayList<>();
                    faltantesMicro = new ArrayList<>();

                    binding.btnGuardar.setEnabled(true);
                    binding.btnGuardar.setAlpha(1);
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

                    for(int i = 0;i<datosPuntuacion.getFaltantes().size();i++){
                        if(datosPuntuacion.getFaltantes().get(i).getRangoubica().equals("MACRO UBICACION")){
                            faltantes.add(datosPuntuacion.getFaltantes().get(i));
                        }else{
                            faltantesMicro.add(datosPuntuacion.getFaltantes().get(i));
                        }

                    }

                    if(faltantes.size()<=0){
                        binding.tituloFaltantesMacro.setVisibility(View.GONE);
                    }

                    if(faltantesMicro.size()<=0){
                        binding.tituloFaltantesMicro.setVisibility(View.GONE);
                    }

                    if(faltantesMicro.size()<=0 && faltantes.size()<=0){
                        binding.tituloFaltantes.setVisibility(View.GONE);
                        binding.tituloFaltantesMicro.setVisibility(View.GONE);
                        binding.tituloFaltantesMacro.setVisibility(View.GONE);
                    }

                    if(factoresMicro.size()<=0){
                        binding.tituloMicro.setVisibility(View.GONE);
                    }

                    if(factoresMacro.size()<=0){
                        binding.tituloMacro.setVisibility(View.GONE);
                    }

                    generarDetallesMicro(binding, factoresMicro);
                    generarDetallesMacro(binding, factoresMacro);

                    generarDetallesMicroFaltantes(binding, faltantesMicro);
                    generarDetallesMacroFaltantes(binding, faltantes);

                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("categoria", categoria);
                    editor.putString("puntuacion", puntuacion);
                    editor.apply();
                    loadingProgress(progressDialog, 1);

                }else{
                    Toast.makeText(ActivityFinalizaTerminar.this, "Error al guardar tu MD",
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
            t1v1.setTextColor(resource.getColor(R.color.grisetxt));
            t1v1.setPadding(0, paddingPixel,0,0);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(this);
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");
            t3v1.setTextColor(resource.getColor(R.color.grisetxt));
            t3v1.setGravity(Gravity.LEFT);
            t3v1.setLayoutParams( new TableRow.LayoutParams( 50,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v1);

            TextView t3v2 = new TextView(this);
            t3v2.setTextSize(12);
            t3v2.setText("/"+datosPuntuacion.get(i).getTotalxfactor()+"");
            t3v2.setTextColor(resource.getColor(R.color.grisetxt));
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
            t1v1.setTextColor(resource.getColor(R.color.grisetxt));
            t1v1.setPadding(0, paddingPixel,0,0);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(this);
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");
            t3v1.setTextColor(resource.getColor(R.color.grisetxt));
            t3v1.setGravity(Gravity.LEFT);
            t3v1.setLayoutParams( new TableRow.LayoutParams( 50,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v1);

            TextView t3v2 = new TextView(this);
            t3v2.setTextSize(12);
            t3v2.setText("/"+datosPuntuacion.get(i).getTotalxfactor()+"");
            t3v2.setTextColor(resource.getColor(R.color.grisetxt));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams( new TableRow.LayoutParams( 75,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v2);

            binding.factoresMicro.addView(tbrow);
        }
    }

    public void generarDetallesMacroFaltantes(ActivityFinalizaBinding binding,  ArrayList<DatosPuntuacion.Factore> datosPuntuacion){

        Resources resource = this.getResources();
        binding.faltantesMacro.removeAllViews();
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
            t1v1.setTextColor(resource.getColor(R.color.grisetxt));
            t1v1.setPadding(0, paddingPixel,0,0);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(this);
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");
            t3v1.setTextColor(resource.getColor(R.color.grisetxt));
            t3v1.setGravity(Gravity.LEFT);
            t3v1.setLayoutParams( new TableRow.LayoutParams( 50,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v1);

            TextView t3v2 = new TextView(this);
            t3v2.setTextSize(12);
            t3v2.setText("/"+datosPuntuacion.get(i).getTotalxfactor()+"");
            t3v2.setTextColor(resource.getColor(R.color.grisetxt));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams( new TableRow.LayoutParams( 75,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v2);


            binding.faltantesMacro.addView(tbrow);
        }
    }

    public void generarDetallesMicroFaltantes(ActivityFinalizaBinding binding,  ArrayList<DatosPuntuacion.Factore> datosPuntuacion){

        Resources resource = this.getResources();
        binding.tablaFaltantesMicro.removeAllViews();
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
            t1v1.setTextColor(resource.getColor(R.color.grisetxt));
            t1v1.setPadding(0, paddingPixel,0,0);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(this);
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");
            t3v1.setTextColor(resource.getColor(R.color.grisetxt));
            t3v1.setGravity(Gravity.LEFT);
            t3v1.setLayoutParams( new TableRow.LayoutParams( 50,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v1);

            TextView t3v2 = new TextView(this);
            t3v2.setTextSize(12);
            t3v2.setText("/"+datosPuntuacion.get(i).getTotalxfactor()+"");
            t3v2.setTextColor(resource.getColor(R.color.grisetxt));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams( new TableRow.LayoutParams( 75,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v2);

            binding.tablaFaltantesMicro.addView(tbrow);
        }
    }

}
