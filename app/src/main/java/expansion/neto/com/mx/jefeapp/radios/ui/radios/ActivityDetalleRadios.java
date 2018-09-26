package expansion.neto.com.mx.jefeapp.radios.ui.radios;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.ActivityDetalleRadiosBinding;
import expansion.neto.com.mx.jefeapp.radios.fragment.radios.FragmentAceptar;
import expansion.neto.com.mx.jefeapp.radios.modelView.radiosModel.SinSitios;
import expansion.neto.com.mx.jefeapp.radios.provider.radiosProvider.ProvaiderDatosRadios;
import expansion.neto.com.mx.jefeapp.ui.autoriza.ActivityAutorizar;

import static android.view.View.GONE;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentAutoriza.loadingProgress;

public class ActivityDetalleRadios extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityDetalleRadiosBinding binding;

    public static final String NOMBRERADIO = "tvNombreRadio";
    public static final String FECHAASIGANDO = "tvFechaAsignadoD";
    public static final String TIPO = "tvTipo";
    public static final String STATUS = "tvStatus";
    public static final String POBLACION = "tvPoblacion";
    public static final String PEA = "tvPEA";
    public static final String VIVIENDAS = "tvViviendas";
    public static final String NSE = "tvNSE";
    public static final String MERCADOS = "tvMercados";
    public static final String ESCUELA = "tvEscuelas";
    public static final String HOSPITALES = "tvHospitales";
    public static final String TEMPLOS = "tvTemplos";
    public static final String CALLE = "tvCalle";
    public static final String CALLE1 = "tvCalle1";
    public static final String CALLE2 = "tvCalle2";
    public static final String LATITUD = "latitud";
    public static final String LONGITUD = "longitud";
    public static final String RADIOID = "radioId";
    ProgressDialog progressDialog;
    String radioID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initDataBinding();


        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(ActivityDetalleRadios.this);
        mapFragment.getView().setVisibility(View.INVISIBLE);

        progressDialog = new ProgressDialog(ActivityDetalleRadios.this);
        TabLayout tabLayout = binding.tabLayout;
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.detalle)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.mapa)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    binding.tvDetalle.setVisibility(View.VISIBLE);
                    binding.lldetalle.setVisibility(View.VISIBLE);
                    mapFragment.getView().setVisibility(View.INVISIBLE);
                }else {
                    binding.tvDetalle.setVisibility(GONE);
                    binding.lldetalle.setVisibility(GONE);
                    mapFragment.getView().setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pintarDatos();

    }

    /* Método que setea la vista con el binding */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalle_radios);
    }

    private void pintarDatos() {
        Bundle bundle = getIntent().getExtras();
        binding.tvNombreRadio.setText(bundle.getString(NOMBRERADIO));
        binding.tvFechaAsignadoD.setText("Asignado el " + bundle.getString(FECHAASIGANDO));
        binding.tvTipo.setText(bundle.getString(TIPO));
        binding.tvStatus.setText(bundle.getString(STATUS));
        binding.tvDetalle.setText("Detalle " + bundle.getString(NOMBRERADIO));
        binding.tvPoblacion.setText(bundle.getString(POBLACION));
        binding.tvPEA.setText(bundle.getString(PEA));
        binding.tvViviendas.setText(bundle.getString(VIVIENDAS));
        binding.tvNSE.setText(bundle.getString(NSE));
        binding.tvMercados.setText(bundle.getString(MERCADOS));
        binding.tvEscuelas.setText(bundle.getString(ESCUELA));
        binding.tvHospitales.setText(bundle.getString(HOSPITALES));
        binding.tvTemplos.setText(bundle.getString(TEMPLOS));
        binding.tvCalle.setText(bundle.getString(CALLE));
        binding.tvCalle1.setText(bundle.getString(CALLE1));
        binding.tvCalle2.setText(bundle.getString(CALLE2));
        radioID = bundle.getString(RADIOID);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Bundle bundle = getIntent().getExtras();
        Double latitud = Double.parseDouble(bundle.getString(LATITUD));
        Double longitud = Double.parseDouble(bundle.getString(LONGITUD));
        Resources resource = ActivityDetalleRadios.this.getResources();
        googleMap.addCircle(new CircleOptions()
        .center(new LatLng(latitud,longitud))
        .radius(500)
                .strokeColor(resource.getColor(R.color.radios))
                .fillColor(resource.getColor(R.color.radios)));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitud,longitud))
                .zoom(15)
                .bearing(0)
                .tilt(0)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    public void CrearMD(View view) {

        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("radio", radioID);
        editor.apply();

        Intent main = new Intent(ActivityDetalleRadios.this, ActivityAutorizar.class);
        main.putExtra(ActivityDetalleRadios.RADIOID, radioID);
        startActivity(main);

    }

    public void SinSitios(final View view) {
        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", "");
        loadingProgress(progressDialog, 0);
        ProvaiderDatosRadios.getInstance(this).sinSitiosDisponibles(usuario, radioID, new ProvaiderDatosRadios.SinSitiosI() {
            @Override
            public void resolve( SinSitios sinSitios ) {
                loadingProgress(progressDialog, 1);
                if(sinSitios.getCodigo()== 200){
                    FragmentAceptar a = new FragmentAceptar();
                    a.setMensaje("sin_radios");
                    a.show(getSupportFragmentManager(),"child");
                }

            }

            @Override
            public void reject(Exception e) {
                loadingProgress(progressDialog, 1);
                Toast.makeText(ActivityDetalleRadios.this, "No se pudo actualizar el estatus", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


