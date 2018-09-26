package expansion.neto.com.mx.jefeapp.ui.porterminar;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.ActivityAutorizaBinding;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentDialogMostrarTip;
import expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar.FragmentDialogCancelarMdTerminar;
import expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar.FragmentTerminar;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos.FragmentDatosConstruccion;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos.FragmentDatosGeneralidades;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos.FragmentDatosPropietario;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos.FragmentDatosSitio;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos.FragmentDatosSuperficie;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos.FragmentDatosZonificacion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosPropietario;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosSitio;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosSuperficie;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearGeneralidades;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Tips;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderConsultaTips;

import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentDialogCancelarMd.cleanShared;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosConstruccion.obtenerConstruccion;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosGeneralidades.obtenerGeneralidades;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosPropietario.obtenerPropietario;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosSitio.obtenerSitio;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosSuperficie.obtenerSuperficie;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosZonificacion.obtenerZonificacion;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class ActivityPorTerminar extends AppCompatActivity{

    private ActivityAutorizaBinding binding;
    private PageAdapter adapter;
    private int currentItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initDataBinding();
        cleanShared(ActivityPorTerminar.this);
        currentItem = 0;

        binding.anterior.setVisibility(View.INVISIBLE);

        adapter = new PageAdapter(getSupportFragmentManager());
        binding.pager.setAdapter(adapter);
        binding.pager.setCurrentItem(currentItem);
        setNavigator();

        binding.help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTip("1");
            }
        });


        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //TODO Crear condiciones para que se guarde solo cuando se avanza no cuando se regresa
                final SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                String mdId = preferences.getString("mdIdterminar", "");


                if(mdId.length()==0 || mdId.equals("0")){
                    mdId = "";
                }

                if(position==0) {
                    binding.help.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mostrarTip("1");
                        }
                    });
                }


                if(position==1) {
                    binding.help.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mostrarTip("2");
                        }
                    });
                    CrearDatosSitio crearDatosPropietario = obtenerSitio(ActivityPorTerminar.this);
                    if(crearDatosPropietario!=null || !crearDatosPropietario.getNombreSitio().equals("")){
                        FragmentDatosSitio crear = new FragmentDatosSitio(ActivityPorTerminar.this);
                        crear.mandarDatos(crearDatosPropietario);
                    }

                }

                if(position==2){
                    binding.help.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mostrarTip("3");
                        }
                    });
                    if(!mdId.equals("") || mdId.equals("0")){
                        CrearDatosPropietario crearDatosPropietario = obtenerPropietario(ActivityPorTerminar.this);


                        if(!crearDatosPropietario.getMdId().equals("") && crearDatosPropietario!=null){
                            FragmentDatosPropietario crear = new FragmentDatosPropietario(ActivityPorTerminar.this);
                            crear.mandarDatosPropietario(crearDatosPropietario);
                            getSharedPreferences("datosPropietario", 0).edit().clear().apply();

                        }
                    }

                }

                if(position==3){
                    binding.help.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mostrarTip("4");
                        }
                    });
                    if(!mdId.equals("") || mdId.equals("0")){
                        CrearDatosSuperficie crearDatosSuperficie = obtenerSuperficie(ActivityPorTerminar.this);
                        if(!crearDatosSuperficie.getMdId().equals("") && crearDatosSuperficie!=null){
                            crearDatosSuperficie.setMdId(mdId);
                            FragmentDatosSuperficie crear = new FragmentDatosSuperficie(ActivityPorTerminar.this);
                            crear.mandarDatosSuperficie(crearDatosSuperficie);
                            getSharedPreferences("datosSuperficie", 0).edit().clear().apply();
                        }
                    }
                }

                if(position==4){
                    binding.help.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mostrarTip("5");
                        }
                    });
                    if(!mdId.equals("") || mdId.equals("0")) {

//                        String json = obtenerZonificacion(ActivityPorTerminar.this);
//                        if (json.length() > 0 || !json.equals("")) {
//                            FragmentDatosZonificacion crear = new FragmentDatosZonificacion(ActivityPorTerminar.this);
//                            crear.mandarDatosZonificacion(json);
//                            getSharedPreferences("datosZonificacion", 0).edit().clear().apply();
//
//
//                        }
                    }
                }

                if(position==5){
                    binding.help.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mostrarTip("6");
                        }
                    });
                    if(!mdId.equals("") || mdId.equals("0")){
                        String json = obtenerConstruccion(ActivityPorTerminar.this);
                        if(json.length()>0 || !json.equals("")){
                            FragmentDatosConstruccion crear = new FragmentDatosConstruccion(ActivityPorTerminar.this);
                            crear.mandarDatosConstruccion(json);
                            getSharedPreferences("datosConstruccion", 0).edit().clear().apply();

                        }
                    }
                }

                if(position==6){
                    binding.help.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mostrarTip("7");
                        }
                    });
                    if(!mdId.equals("") || mdId.equals("0")){
                        CrearGeneralidades generalidades = obtenerGeneralidades(ActivityPorTerminar.this);
                        if(!generalidades.getMdId().equals("") && generalidades!=null){
                            FragmentDatosGeneralidades crear = new FragmentDatosGeneralidades(ActivityPorTerminar.this);
                            crear.mandarDatosGeneralidades(generalidades);

                            getSharedPreferences("datosGeneralidades", 0).edit().clear().apply();


                        }
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int position) {


                //Log.e("******", "swipe "+position);

                // TODO Auto-generated method stub
                if (binding.pager.getCurrentItem() == 0) {
                    binding.anterior.setVisibility(View.INVISIBLE);

                    //Log.e("******", "INVISIBLE "+position);


                } else {

                    binding.anterior.setVisibility(View.VISIBLE);
                    //Log.e("******", "VISIBLE "+position);


                }

                if (binding.pager.getCurrentItem() == (binding.pager.getAdapter().getCount() - 1)) {

                    //Log.e("******", "FINISH "+position);

                    binding.siguiente.setText("FINISH");
                } else {
                   // Log.e("******", "NEXT "+position);
                    binding.siguiente.setText("NEXT");
                }
                setNavigator();
            }
        });

        binding.anterior.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (binding.pager.getCurrentItem() != 0) {
                    binding.pager.setCurrentItem(binding.pager.getCurrentItem() - 1);
                }
                setNavigator();
            }
        });

        binding.siguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (binding.pager.getCurrentItem() != (binding.pager.getAdapter().getCount() - 1)) {
                    binding.pager.setCurrentItem(binding.pager.getCurrentItem() + 1);
                } else {
                    Toast.makeText(ActivityPorTerminar.this, "Finish",
                            Toast.LENGTH_SHORT).show();
                }
                setNavigator();
            }
        });

    }

    public void setNavigator() {
        String navigation = "";
        for (int i = 0; i < adapter.getCount(); i++) {
            if (i == binding.pager.getCurrentItem()) {
                navigation += getString(R.string.material_icon_point_full)
                        + "      ";
            } else {
                navigation += getString(R.string.material_icon_point_empty)
                        + "      ";
            }
        }
        binding.circuloPosicion.setText(navigation);
    }

    public void setCurrentSlidePosition(int position) {
        this.currentItem = position;
    }

    public int getCurrentSlidePosition() {
        return this.currentItem;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                return FragmentTerminar.newInstance(position);
            } else if (position == 1) {
                return FragmentTerminar.newInstance(position);
            } else {
                return FragmentTerminar.newInstance(position);
             }
        }
    }

    /**
     * método que setea la vista con el binding
     */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_autoriza);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {

            FragmentManager fm = getSupportFragmentManager();
            FragmentDialogCancelarMdTerminar dFragment = new FragmentDialogCancelarMdTerminar();
            dFragment.show(fm, "Dialog Fragment");

        }
    }

    ArrayList<Tips.Tip> tips;
    public void mostrarTip(String pantalla){
        ProviderConsultaTips.getInstance(ActivityPorTerminar.this).obtenerTips(pantalla, new ProviderConsultaTips.ConsultaTips() {
            @Override
            public void resolve(Tips tip) {
                if(tip.getCodigo()==200){
                    tips = new ArrayList<>();

                    if(tip.getTips().size()>0){
                        for(int i=0;i<tip.getTips().size();i++){
                            tips.add(tip.getTips().get(i));
                        }

                        SharedPreferences preferences;
                        preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(tips);
                        editor.putString("tips", json);
                        editor.apply();

                        FragmentManager fm = getSupportFragmentManager();
                        FragmentDialogMostrarTip dFragment = new FragmentDialogMostrarTip();
                        dFragment.show(fm, "Dialog Fragment");

                    }else{
                        Toast.makeText(ActivityPorTerminar.this, "Aún no se agrega tip para esta opción",
                            Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ActivityPorTerminar.this, tip.getMensaje(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

}
