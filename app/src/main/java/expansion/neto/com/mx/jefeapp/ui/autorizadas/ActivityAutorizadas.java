package expansion.neto.com.mx.jefeapp.ui.autorizadas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.ActivityAutorizadasBinding;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentDialogCancelarMd;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.jefeapp.provider.autorizadasProvider.ProviderDatosAutorizadas;
import expansion.neto.com.mx.jefeapp.ui.dashboard.ActivityMain;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class ActivityAutorizadas extends AppCompatActivity {

    private ActivityAutorizadasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        initDataBinding();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getDatos();


        binding.btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getApplicationContext(), ActivityMain.class);
                startActivity(main);
            }
        });

    }

    /**
     * Método que setea la vista con el binding
     */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_autorizadas);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public void getDatos(){

        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", "");
        String mes = preferences.getString("mesTaco","");

        ProviderDatosAutorizadas.getInstance(this).obtenerDatosAutorizadas(mes, usuario, new ProviderDatosAutorizadas.ConsultaDatosSitio() {
            @Override
            public void resolve(Autorizadas datosSitio) {
                if(datosSitio!=null){
                    if(datosSitio.getAutorizadas()!=null){

                        Resources resource = ActivityAutorizadas.this.getResources();
                        TableLayout plomoTable = (TableLayout) findViewById(R.id.plomosTable);
                        plomoTable.removeAllViews();
                        TableRow rowPlomo = new TableRow(ActivityAutorizadas.this);
                        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));

                        TextView nombremd = new TextView(ActivityAutorizadas.this);
                        TextView categoria = new TextView(ActivityAutorizadas.this);
                        TextView puntos = new TextView(ActivityAutorizadas.this);
                        TextView fecha = new TextView(ActivityAutorizadas.this);
                        int paddingDp = 2;

                        float density = getResources().getDisplayMetrics().density;
                        int paddingPixel = (int)(paddingDp * density);

                        nombremd.setText("Nombre MD   ");
                        nombremd.setTextColor(resource.getColor(R.color.azul));
                        nombremd.setGravity(Gravity.CENTER);
                        rowPlomo.addView(nombremd);



                        categoria.setText("Categoría   ");
                        categoria.setTextColor(resource.getColor(R.color.azul));
                        categoria.setGravity(Gravity.CENTER);
                        rowPlomo.addView(categoria);

                        puntos.setText("Puntos");
                        puntos.setTextColor(resource.getColor(R.color.azul));
                        puntos.setGravity(Gravity.CENTER);
                        rowPlomo.addView(puntos);

                        fecha.setText("Fecha");
                        fecha.setTextColor(resource.getColor(R.color.azul));
                        fecha.setGravity(Gravity.CENTER);
                        rowPlomo.addView(fecha);

                        plomoTable.addView(rowPlomo);

                        int a = datosSitio.getAutorizadas().size();


                        for(int i = 0; i < datosSitio.getAutorizadas().size(); i ++){

                            TableRow tbrow = new TableRow(ActivityAutorizadas.this);
                            tbrow.setBackgroundColor(resource.getColor(R.color.blanco));
                            TextView t1v1 = new TextView(ActivityAutorizadas.this);
                            t1v1.setText(datosSitio.getAutorizadas().get(i).getNombresitio()+"");
                            t1v1.setTextColor(resource.getColor(R.color.azul));
                            t1v1.setPadding(0, paddingPixel,0,0);
                            t1v1.setGravity(Gravity.START);

                            t1v1.setLayoutParams( new TableRow.LayoutParams( 0,
                                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );


//                        View v = new View(ActivityAutorizadas.this);
//                        v.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
//



                            tbrow.addView(t1v1);

                            TextView t3v1 = new TextView(ActivityAutorizadas.this);
                            t3v1.setText(datosSitio.getAutorizadas().get(i).getCategoria()+"");
                            t3v1.setTextColor(resource.getColor(R.color.azul));
                            t3v1.setGravity(Gravity.CENTER_HORIZONTAL);
                            t3v1.setLayoutParams( new TableRow.LayoutParams( 0,
                                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
                            tbrow.addView(t3v1);

                            TextView t2v1 = new TextView(ActivityAutorizadas.this);
                            t2v1.setText(datosSitio.getAutorizadas().get(i).getPuntuacion()+"");
                            t2v1.setTextColor(resource.getColor(R.color.azul));
                            t2v1.setGravity(Gravity.CENTER_HORIZONTAL);
                            t2v1.setLayoutParams( new TableRow.LayoutParams( 0,
                                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
                            tbrow.addView(t2v1);

                            TextView t2v5 = new TextView(ActivityAutorizadas.this);
                            t2v5.setText(datosSitio.getAutorizadas().get(i).getFechaatorizacion()+"");
                            t2v5.setTextColor(resource.getColor(R.color.azul));
                            t2v5.setGravity(Gravity.CENTER_HORIZONTAL);
                            tbrow.addView(t2v5);


                            plomoTable.addView(tbrow);
                        }

                    }
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });

//
//        ProviderConsultaFinaliza.getInstance(this).obtenerPuntos(mdid, new ProviderConsultaFinaliza.ConsultaPuntos() {
//            @Override
//            public void resolve(DatosPuntuacion datosPuntuacion) {
//                if(datosPuntuacion.getCodigo()==200){
//
//                    categoria = datosPuntuacion.getNomcategoria();
//
//                    SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
//                    final SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("categoria", categoria);
//
//                    editor.apply();
//
//                    if(datosPuntuacion.getFactores().size()>=7){
//                       // binding.btnFinalizar.setEnabled(true);
//                        binding.btnGuardar.setEnabled(true);
//                    }else{
//                        binding.btnGuardar.setAlpha(0.35f);
//                        binding.btnFinalizar.setAlpha(0.35f);
//                        binding.btnFinalizar.setEnabled(false);
//                        binding.btnGuardar.setEnabled(false);
//                    }
//
//                    for(int i=0;i<datosPuntuacion.getFactores().size();i++){
//                        if(datosPuntuacion.getFactores().get(i).getFactorid()==0) {
//                            binding.puntuacion.setText(datosPuntuacion.getFactores().get(i).getPuntuacion() +"/"+datosPuntuacion.getTotalPuntos()+"");
//                            puntuacion = datosPuntuacion.getFactores().get(i).getPuntuacion().toString();
//                            editor.putString("puntuacion", puntuacion);
//                        }
//
//                        if(datosPuntuacion.getFactores().get(i).getFactorid()==1){
//                            binding.datossitio.setText(datosPuntuacion.getFactores().get(i).getPuntuacion()+"/"+
//                                    datosPuntuacion.getFactores().get(i).getTotalxfactor()+"");
//                        }
//
//                        if(datosPuntuacion.getFactores().get(i).getFactorid()==3){
//                            binding.superficie.setText(datosPuntuacion.getFactores().get(i).getPuntuacion()+"/"+
//                                    datosPuntuacion.getFactores().get(i).getTotalxfactor()+"");
//                        }
//
//                        if(datosPuntuacion.getFactores().get(i).getFactorid()==4){
//                            binding.zonificacion.setText(datosPuntuacion.getFactores().get(i).getPuntuacion()+"/"+
//                                    datosPuntuacion.getFactores().get(i).getTotalxfactor()+"");
//                        }
//
//                        if(datosPuntuacion.getFactores().get(i).getFactorid()==5){
//                            binding.construccion.setText(datosPuntuacion.getFactores().get(i).getPuntuacion()+"/"+
//                                    datosPuntuacion.getFactores().get(i).getTotalxfactor()+"");
//                        }
//
//                        if(datosPuntuacion.getFactores().get(i).getFactorid()==6){
//                            binding.generalidades.setText(datosPuntuacion.getFactores().get(i).getPuntuacion()+"/"+
//                                    datosPuntuacion.getFactores().get(i).getTotalxfactor()+"");
//                        }
//
//                        if(datosPuntuacion.getFactores().get(i).getFactorid()==7){
//                            binding.flujopeatonal.setText(datosPuntuacion.getFactores().get(i).getPuntuacion()+"/"+
//                                    datosPuntuacion.getFactores().get(i).getTotalxfactor()+"");
//                        }
//                    }
//                }else{
//                    Toast.makeText(ActivityAutorizadas.this, "Error al guardar tu MD",
//                            Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void reject(Exception e) {
//
//            }
//        });
    }



}
