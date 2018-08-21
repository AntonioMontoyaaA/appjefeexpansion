package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.VectorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutoriza1Binding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutoriza2Binding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutoriza3Binding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutoriza4Binding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutoriza5Binding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutoriza6Binding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutorizaBinding;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosGeneralidades;
import expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar.FragmentDialogCancelarMdTerminar;
import expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar.FragmentTerminar;
import expansion.neto.com.mx.jefeapp.modelView.Ubicacion;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.DatosConstruccions;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.DatosPredial;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Peatonales;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Amortizacion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CompetenciasGeneradores;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CompetenciasGeneradoresV2;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosPropietario;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosSitio;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosSuperficie;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearGeneralidades;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearPeatonal;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearZonificacion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.DatosConstruccion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.FactoresConstruccion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.HorasPeatonales;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.PropietarioBusqueda;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosAmortizacion;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosConstruccion;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosPeatonal;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosPredial;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderBuscarPropietario;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearConstruccion;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearDatosPropietario;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearDatosSitio;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearGeneralidades;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearPeatonal;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearSuperficie;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearZonificacion;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderDatosCompetencias;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderDatosFactoresConstruccion;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderHorasPeatonales;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderObtenerUrl;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.AdapterAutorizaPeatonal;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.AutorizaHolderPeatonal;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter.AdapterListaCompetencia;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter.AdapterListaGeneradores;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter.AdapterListaGeneradoresMercadoPublico;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter.AdapterListaGeneradoresNegocios;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter.AdapterListaGeneradoresNegociosComida;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter.AdapterListaGeneradoresTianguis;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter.AdapterListaGeneradoresTransporte;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter.AdapterListaHoras;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter.AdapterListaPropietarios;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter.AdapterListaTiendaNeto;
import expansion.neto.com.mx.jefeapp.ui.crear.ActivityFinaliza;
import expansion.neto.com.mx.jefeapp.ui.dashboard.ActivityMain;
import expansion.neto.com.mx.jefeapp.utils.CustomTextWatcher;
import expansion.neto.com.mx.jefeapp.utils.PhoneNumberTextWatcher;
import expansion.neto.com.mx.jefeapp.utils.ServicioGPS;
import expansion.neto.com.mx.jefeapp.utils.Util;

import static android.media.MediaRecorder.VideoSource.CAMERA;
import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.VERSION_APP;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentDialogCancelarMd.cleanShared;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosConstruccion.salvarDatosConstruccion;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosPropietario.salvarDatosPropietario;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosSitio.salvarDatosSitio;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosSuperficieCrear.salvarDatosSuperficie;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos.GuardarDatosZonificacion.salvarDatosZonificacion;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar.FragmentTerminar.compressImage;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar.FragmentTerminar.getBitmap;
import static expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar.FragmentTerminar.getStringImage;
import static expansion.neto.com.mx.jefeapp.utils.Util.getFecha;
import static expansion.neto.com.mx.jefeapp.utils.Util.isEmailValid;
import static expansion.neto.com.mx.jefeapp.utils.Util.random;


public class FragmentAutoriza extends Fragment implements
         AutorizaHolderPeatonal.Listener, com.google.android.gms.location.LocationListener {

    TimerTask hourlyTask;
    private View view;
    private static final String ARG_POSITION = "position";
    private int position;
    AdapterListaCompetencia adapter;
    AdapterListaTiendaNeto adapterTiendaNeto;

    AdapterListaGeneradores adapter2;
    AdapterListaGeneradoresNegocios adapterNegocios;
    AdapterListaGeneradoresTransporte adapterTransporte;

    AdapterListaGeneradoresNegociosComida adapterListaGeneradoresNegociosComida;
    AdapterListaGeneradoresMercadoPublico adapterListaGeneradoresMercadoPublico;
    AdapterListaGeneradoresTianguis adapterListaGeneradoresTianguis;

    AutorizaHolderPeatonal.Listener n;
    List<CompetenciasGeneradoresV2.Competencia> listCompetencia = new ArrayList<>();
    List<CompetenciasGeneradoresV2.OtrosGeneradore> listGeneradores = new ArrayList<>();
    List<CompetenciasGeneradoresV2.TiendaNeto> listCompetenciaTiendaNeto = new ArrayList<>();
    List<CompetenciasGeneradoresV2.Negocio> listGeneradoresNegocios = new ArrayList<>();
    List<CompetenciasGeneradoresV2.TransportePublico> listGeneradoresTransporte = new ArrayList<>();
    List<CompetenciasGeneradoresV2.NegociosDeComida> negociosDeComidaArrayList = new ArrayList<>();
    List<CompetenciasGeneradoresV2.MercadoPublico> mercadoPublicoArrayList = new ArrayList<>();
    List<CompetenciasGeneradoresV2.Tiangui> tianguiArrayList = new ArrayList<>();

    AdapterListaHoras adapterHoras;
    AdapterListaPropietarios adapterListaPropietarios;

    CrearZonificacion zonificacion = null;

    int valor;
    String nombreGenerador;
    String nombreCompetencia;

    String nombrePropietario;
    String apellidoPropietario;
    String apellidoMPropietario;
    String telefonoPropietario;
    String emailPropietario;

    private int CAMERA_FRONTAL = 1;
    private int CAMERA_LATERAL_1 = 2;
    private int CAMERA_LATERAL_2 = 3;
    private int CAMERA_PREDIAL = 4;
    String municipio;
    ProgressDialog progressDialog;

    private AdapterListaPropietarios.OnItemClick clickPropietario = new AdapterListaPropietarios.OnItemClick() {
        @Override
        public void onClick(String nombre, String apellido, String apellidoM, String telefono, String email ) {
            nombrePropietario = nombre;
            apellidoPropietario = apellido;
            apellidoMPropietario = apellidoM;
            telefonoPropietario = telefono;
            emailPropietario = email;
            bindingPropietario.nombre.setText(nombrePropietario+"");
            bindingPropietario.apellidoP.setText(apellidoPropietario+"");
            bindingPropietario.apellidoM.setText(apellidoMPropietario+"");
            bindingPropietario.telefono.setText(telefonoPropietario+"");
            bindingPropietario.email.setText(emailPropietario+"");
        }
    };

    int nivel;

    private AdapterListaCompetencia.OnItemClick click = new AdapterListaCompetencia.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreCompetencia = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaTiendaNeto.OnItemClick clickTiendaNeto = new AdapterListaTiendaNeto.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreCompetencia = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradores.OnItemClick clicks = new AdapterListaGeneradores.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradoresNegocios.OnItemClick clickNegocio = new AdapterListaGeneradoresNegocios.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradoresTransporte.OnItemClick clickTransporte = new AdapterListaGeneradoresTransporte.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradoresMercadoPublico.OnItemClick clickMercadoPublico = new AdapterListaGeneradoresMercadoPublico.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradoresNegociosComida.OnItemClick clickNegociosComida = new AdapterListaGeneradoresNegociosComida.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradoresTianguis.OnItemClick clickTiaguis = new AdapterListaGeneradoresTianguis.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    Ubicacion gpsUbica;
    private LatLng mCenterLatLong;

    private OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(final GoogleMap googleMap) {
            mMap = googleMap;
            gpsUbica = gps();
            if(gpsUbica.lat!=0 && gpsUbica.lng!=0){

                mCenterLatLong = new LatLng(gpsUbica.lat, gpsUbica.lng);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(mCenterLatLong));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mCenterLatLong, 15));
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(mCenterLatLong)
                        .zoom(14)
                        .bearing(0)
                        .tilt(0)
                        .build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                googleMap.setMyLocationEnabled(true);

            }

            mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {
                    mCenterLatLong = cameraPosition.target;
                    mMap.clear();
                    try {
                        Location mLocation = new Location("");
                        mLocation.setLatitude(mCenterLatLong.latitude);
                        mLocation.setLongitude(mCenterLatLong.longitude);
                        setDireccion(binding, mCenterLatLong.latitude, mCenterLatLong.longitude);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            });
        }
    };

    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onLocationChanged(Location location) {
        try {
            if (location != null)
                changeMap(location);
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private GoogleMap mMap;

    @SuppressLint("MissingPermission")
    private void changeMap(Location location) {
        if (mMap != null) {
            mMap.getUiSettings().setZoomControlsEnabled(false);
            LatLng latLong;


            latLong = new LatLng(location.getLatitude(), location.getLongitude());

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLong).zoom(19f).tilt(70).build();

            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        } else {
        }

    }

    BitmapDescriptor icon;
    Float mdLat;
    Float mdLot;

    private OnMapReadyCallback onMapReadyCallbackZonificacion = new OnMapReadyCallback() {
        @Override
        public void onMapReady(final GoogleMap googleMap) {

            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            mdLat = preferences.getFloat("latMd", 0);
            mdLot = preferences.getFloat("lotMd", 0);
            final Long mdIdZ = preferences.getLong("mdId", 0);
            //googleMap.clear();

            if(mdLat!=null || mdLot!=null){
                final LatLng mds = new LatLng(mdLat, mdLot);
                icon = getBitmapDescriptor(R.drawable.home);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(mds);
                markerOptions.icon(icon);

                googleMap.animateCamera(CameraUpdateFactory.newLatLng(mds));
                googleMap.addMarker(markerOptions);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(mds));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mds, 15));
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(mds)
                        .zoom(14)
                        .bearing(0)
                        .tilt(0)
                        .build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {

                        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);

                        int zonificacion = preferences.getInt("zonificacion", 0);
                        String usuario = preferences.getString("usuario", "");

                        if(zonificacion==0){
                            if(valor==0){
                                Toast.makeText(getContext(), "Debes seleccionar alguna compentencia y generador",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                colocarMarcador(latLng, googleMap, valor, usuario, mds, String.valueOf(mdIdZ));
                            }
                        }else if(zonificacion==1){
                            if(valor==0){

                                Toast.makeText(getContext(), "Debes seleccionar alguna compentencia y generador",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                colocarMarcador(latLng, googleMap, valor, usuario, mds, String.valueOf(mdIdZ));
                            }
                        }
                    }
                });

                bindingZonificacion.aceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        CameraUpdate center=
                                CameraUpdateFactory.newLatLng(new LatLng(mdLat, mdLot));
                        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
                        googleMap.moveCamera(center);
                        googleMap.animateCamera(zoom);

                    }
                });


                bindingZonificacion.cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getContext().getSharedPreferences("datosZonificacion", 0).edit().clear().apply();

                        for (Marker marker: markers) {
                            marker.remove();
                        }

                        markers.clear();

                        zonificacion = new CrearZonificacion(null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null);

                        zonificacionJson = "";

                        competencia = new ArrayList<>();
                        generadores = new ArrayList<>();
                        detallesC = new ArrayList<>();
                        detallesG = new ArrayList<>();

                        detalleC = new CrearZonificacion.Detalle();
                        detalleG = new CrearZonificacion.Detalle();

                        zonificacionC = new CrearZonificacion.Zonificacion();
                        zonificacionG = new CrearZonificacion.Zonificacion();

                    }
                });

                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

                    @Override
                    public void onMapLongClick(LatLng arg0) {
                        // TODO Auto-generated method stub
                    }
                });

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker m) {
                        LatLng eliminar = m.getPosition();
                        String latitudCompetencia;
                        String latitudCompetenciaMarcador;
                        String latitudGenerador;
                        String latitudGeneradorMarcador;

                        if(zonificacion!=null && !zonificacion.getCompetencia().isEmpty()) {
                            for (int i = 0; i < zonificacion.getCompetencia().get(0).getDetalles().size(); i++) {
                                latitudCompetencia = zonificacion.getCompetencia().get(0).getDetalles().get(i).getLatitud();
                                latitudCompetenciaMarcador = String.valueOf(eliminar.latitude);
                                if (latitudCompetencia.equals(latitudCompetenciaMarcador)) {
                                    zonificacion.getCompetencia().get(0).getDetalles().remove(i);
                                    m.remove();
                                }
                            }
                        }

                        if(zonificacion!=null && !zonificacion.getGeneradores().isEmpty()){
                            for(int i = 0;i<zonificacion.getGeneradores().get(0).getDetalles().size();i++){
                                latitudGenerador = zonificacion.getGeneradores().get(0).getDetalles().get(i).getLatitud();
                                latitudGeneradorMarcador = String.valueOf(eliminar.latitude);
                                if(latitudGenerador.equals(latitudGeneradorMarcador)){
                                    zonificacion.getGeneradores().get(0).getDetalles().remove(i);
                                    m.remove();
                                }
                            }
                        }
                        zonificacionJson = getJsonString(zonificacion);
                        return false;
                    }
                });

            }


        }
    };

    public static FragmentAutoriza newInstance(int position) {
        FragmentAutoriza f = new FragmentAutoriza();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    private SlideUp slideCompetencia;
    private SlideUp slideGenerador;
    CrearDatosSitio crearsitio;
    CrearDatosPropietario creapropietario;
    FragmentAutorizaBinding binding;
    FragmentAutoriza3Binding bindingZonificacion;
    FragmentAutoriza2Binding bindingSuperficie;
    FragmentAutoriza1Binding bindingPropietario;
    TimerTask hourlyTaskSuperficie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getContext());
        if (position == 0) {
            mensaje = "fragment 1";
            binding = DataBindingUtil.inflate(inflater,R.layout.fragment_autoriza,container,false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.datossitio));

            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(onMapReadyCallback);

            final SharedPreferences preferencesSuperficie = getContext().getSharedPreferences("datosSitio", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferencesSuperficie.edit();
            editor.clear();
            editor.commit();

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentDialogCancelarMdTerminar a = new FragmentDialogCancelarMdTerminar();
                    a.show(getChildFragmentManager(),"child");
                }
            });

            getContext().getSharedPreferences("datosSitio", 0).edit().clear().apply();


            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String usuario = preferences.getString("usuario", "");

            SharedPreferences.Editor editor1 = preferences.edit();
            editor1.putString("mdIdterminar", "");

            binding.nombresitio.setText("");

            final String choice = preferences.getString("tipoSitio", "");

            if(!choice.isEmpty()){

            }else{
                FragmentDialogTipoSitio a = new FragmentDialogTipoSitio();
                a.show(getChildFragmentManager(),"child");
            }

            Timer timer = new Timer ();
            hourlyTask = new TimerTask () {
                @Override
                public void run () {
                    String[] latitud = {null};
                    String[] longitud = {null};
                    String nombreSitio = binding.nombresitio.getText().toString();
                    String codigoPostal = binding.codigopostalsitio.getText().toString();
                    String direccion = binding.direccionsitio.getText().toString();
                    String estado = binding.estadositio.getText().toString();
                    String ciudad = binding.ciudadsitio.getText().toString();
                    String pais = binding.pais.getText().toString();
                    String choices = preferences.getString("tipoSitio", "");

                    if(mCenterLatLong!=null){
                        if(mCenterLatLong.latitude!=0){
                            latitud[0] = String.valueOf(mCenterLatLong.latitude);
                            longitud[0] = String.valueOf(mCenterLatLong.longitude);
                        }
                    }

                    if(nombreSitio!=null){
                        if(nombreSitio.length()>0){
                            crearsitio = new CrearDatosSitio(usuario, nombreSitio, codigoPostal,
                                    direccion, estado, municipio, ciudad, latitud[0], longitud[0], choices, "", VERSION_APP, pais, mdId);

                            if(nombreSitio.equals("")){

                            }else{
                                salvarDatosSitio(crearsitio, editor);
                            }
                        }
                    }
                }
            };
            timer.schedule (hourlyTask, 500, 1000);
            //TODO Hacer estos casos para las demás pantallas
            binding.toolbar.guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

                    binding.toolbar.guardar.setEnabled(false);
                    loadingProgress(progressDialog, 0);

                    String[] latitud = {null};
                    String[] longitud = {null};
                    final String nombreSitio = binding.nombresitio.getText().toString();
                    String codigoPostal = binding.codigopostalsitio.getText().toString();
                    String direccion = binding.direccionsitio.getText().toString();
                    String estado = binding.estadositio.getText().toString();
                    String ciudad = binding.ciudadsitio.getText().toString();
                    String pais = binding.pais.getText().toString();

                    final String choices = preferences.getString("tipoSitio", "");

                    if(mCenterLatLong.latitude!=0){
                        latitud[0] = String.valueOf(mCenterLatLong.latitude);
                        longitud[0] = String.valueOf(mCenterLatLong.longitude);
                    }

                    String usuario = preferences.getString("usuario", "");

                    crearsitio = new CrearDatosSitio(usuario, nombreSitio, codigoPostal,
                            direccion, estado, municipio, ciudad, latitud[0], longitud[0], choices, "", VERSION_APP, pais, "");

                    ProviderCrearDatosSitio.getInstance(getContext()).guardarMd(crearsitio,
                            new ProviderCrearDatosSitio.InterfaceCrearDatosSitio() {
                                @Override
                                public void resolve(Codigos codigo) {

                                    if(codigo.getCodigo()==200){

                                        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editorExpansion = preferences.edit();
                                        editorExpansion.putFloat("latMd", (float) mCenterLatLong.latitude);
                                        editorExpansion.putFloat("lotMd", (float) mCenterLatLong.longitude);
                                        editorExpansion.putLong("mdId", codigo.getMdId());
                                        editorExpansion.putString("nombreSitio", nombreSitio);
                                        editorExpansion.apply();
                                        binding.toolbar.guardar.setEnabled(true);
                                        loadingProgress(progressDialog, 1);

                                        FragmentDialogGuardar a = new FragmentDialogGuardar();
                                        a.show(getChildFragmentManager(),"child");
                                    }else{
                                        loadingProgress(progressDialog,  1);
                                        Toast.makeText(getContext(), codigo.getMensaje(),
                                                Toast.LENGTH_SHORT).show();
                                        binding.toolbar.guardar.setEnabled(true);

                                    }

                                }

                                @Override
                                public void reject(Exception e) {

                                }
                            });
                }
            });

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    cleanShared(getContext());
                    Intent main = new Intent(getContext(), ActivityMain.class);
                    getContext().startActivity(main);
                }
            });

        } else if (position == 1) {

            bindingPropietario = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_1,container,false);
            view = bindingPropietario.getRoot();
            getContext().getSharedPreferences("datosPropietario", 0).edit().clear().apply();

            bindingPropietario.toolbar.nombreTitulo.setText(getString(R.string.datospropietario));
            bindingPropietario.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentDialogCancelarMd a = new FragmentDialogCancelarMd();
                    a.show(getChildFragmentManager(),"child");
                }
            });

            bindingPropietario.nombre.setText("");
            bindingPropietario.apellidoP.setText("");
            bindingPropietario.apellidoM.setText("");
            bindingPropietario.telefono.setText("");
            bindingPropietario.email.setText("");

            final Ubicacion ubicacion = gps();
            final String[] latitudDatos = new String[1];
            final String[] longitudDatos = new String[1];

            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String nombreMd = preferences.getString("nombreSitio", "");
            bindingPropietario.md.setText(nombreMd);

            final SharedPreferences preferencesPropietario = getContext().getSharedPreferences("datosPropietario", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferencesPropietario.edit();



            bindingPropietario.telefono.addTextChangedListener(
                    new PhoneNumberTextWatcher(bindingPropietario.telefono));

            Timer timer = new Timer ();
            hourlyTask = new TimerTask () {
                @Override
                public void run () {
                    long mdid = preferences.getLong("mdId", 0);
                    String convertido = String.valueOf(mdid);
                    String usuarioId = preferences.getString("usuario", "");
                    String mdId = convertido;
                    String apaternoPropietario = bindingPropietario.apellidoP.getText().toString();
                    String nombrePropietario = bindingPropietario.nombre.getText().toString();
                    String amaternoPropietario = bindingPropietario.apellidoM.getText().toString();
                    String telefono = bindingPropietario.telefono.getText().toString();
                    String email = bindingPropietario.email.getText().toString();
                    if(bindingPropietario.apellidoM.getText().toString().equals("")){
                        amaternoPropietario = "''";
                    }

                    if(bindingPropietario.email.getText().toString().equals("")){
                        email = "''";
                    }

                    if(ubicacion!=null){
                        latitudDatos[0] = String.valueOf(ubicacion.getLat());
                        longitudDatos[0] = String.valueOf(ubicacion.getLng());
                    }else{
                        latitudDatos[0] = String.valueOf(mdLat);
                        longitudDatos[0] = String.valueOf(mdLot);
                    }

                    creapropietario = new CrearDatosPropietario(usuarioId, mdId, apaternoPropietario, nombrePropietario, amaternoPropietario,
                            telefono, email, latitudDatos[0], longitudDatos[0]);

                    salvarDatosPropietario(getContext(), creapropietario, editor, preferencesPropietario);

                }
            };

            timer.schedule (hourlyTask, 0l, 1000);

            bindingPropietario.toolbar.guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    Long md = preferences.getLong("mdId", 0);
                    String mdId = String.valueOf(md);
                    bindingPropietario.toolbar.guardar.setEnabled(false);

                    loadingProgress(progressDialog, 0);

                    if(mdId.length()==1){
                        mdId = "";
                    }

                    if(!mdId.equals("") || mdId.equals("0")){
                        long mdid = preferences.getLong("mdId", 0);

                        String convertido = String.valueOf(mdid);

                        String usuarioId = preferences.getString("usuario", "");

                        mdId = convertido;
                        String apaternoPropietario = bindingPropietario.apellidoP.getText().toString();
                        String nombrePropietario = bindingPropietario.nombre.getText().toString();
                        String amaternoPropietario = bindingPropietario.apellidoM.getText().toString();
                        String telefono = bindingPropietario.telefono.getText().toString();
                        String email = bindingPropietario.email.getText().toString();

                        if(bindingPropietario.email.getText().toString().equals("")){
                            email = "-";
                        }

                        if(ubicacion!=null){
                            latitudDatos[0] = String.valueOf(ubicacion.getLat());
                            longitudDatos[0] = String.valueOf(ubicacion.getLng());
                        }else{
                            latitudDatos[0] = String.valueOf(mdLat);
                            longitudDatos[0] = String.valueOf(mdLot);
                        }


                        creapropietario = new CrearDatosPropietario(usuarioId, mdId, apaternoPropietario, nombrePropietario, amaternoPropietario,
                                telefono, email, latitudDatos[0], longitudDatos[0]);
                        ProviderCrearDatosPropietario.getInstance(getContext()).guardarPropietario(creapropietario,
                                new ProviderCrearDatosPropietario.InterfaceCrearDatosPropietario() {
                                    @Override
                                    public void resolve(Codigos codigo) {

                                        if(codigo.getCodigo()==200){

                                            FragmentDialogGuardar a = new FragmentDialogGuardar();
                                            a.show(getChildFragmentManager(),"child");
                                            bindingPropietario.toolbar.guardar.setEnabled(true);
                                            loadingProgress(progressDialog, 1);

                                        }else{
                                            loadingProgress(progressDialog, 1);
                                            Toast.makeText(getContext(), codigo.getMensaje(),
                                                    Toast.LENGTH_SHORT).show();
                                            bindingPropietario.toolbar.guardar.setEnabled(true);

                                        }

                                    }

                                    @Override
                                    public void reject(Exception e) {

                                    }
                                });
                    }

                }
            });

            bindingPropietario.nombreBuscadoredt.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        String usuarioId = preferences.getString("usuario", "");
                        InputMethodManager inputManager =
                                (InputMethodManager) getContext().
                                        getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(
                                getActivity().getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                        String nombre = bindingPropietario.nombreBuscadoredt.getText().toString();

                        loadingProgress(progressDialog, 0);

                        ProviderBuscarPropietario.getInstance(getContext()).obtenerPropietario(usuarioId, nombre, new ProviderBuscarPropietario.ConsultaPropietario() {
                            @Override
                            public void resolve(PropietarioBusqueda propietario) {
                                if(propietario.getCodigo()==200){
                                    adapterListaPropietarios = new AdapterListaPropietarios(propietario.getListaPropietarios(), getContext(), clickPropietario);
                                    bindingPropietario.recyclerPropietarios.setLayoutManager(new LinearLayoutManager(getContext()));
                                    bindingPropietario.recyclerPropietarios.setAdapter(adapterListaPropietarios);
                                    bindingPropietario.coincide.setVisibility(View.VISIBLE);
                                    loadingProgress(progressDialog, 1);

                                }else{
                                    Toast.makeText(getContext(), "No se encontraron coincidencias",
                                            Toast.LENGTH_SHORT).show();
                                    loadingProgress(progressDialog, 1);

                                }
                            }

                            @Override
                            public void reject(Exception e) {

                            }
                        });


                        return true;
                    }
                   // Log.e("*****", "no tecleaste");
                    return false;
                }
            });

            bindingPropietario.buscarNombre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        bindingPropietario.co.setVisibility(View.VISIBLE);
                        bindingPropietario.nombreBuscador.setVisibility(View.VISIBLE);
                        bindingPropietario.nombre.setEnabled(false);
                        bindingPropietario.apellidoP.setEnabled(false);
                        bindingPropietario.apellidoM.setEnabled(false);
                        bindingPropietario.telefono.setEnabled(false);
                        bindingPropietario.email.setEnabled(false);
                    }else{
                        bindingPropietario.nombreBuscador.setVisibility(View.GONE);
                        bindingPropietario.co.setVisibility(View.GONE);
                        bindingPropietario.nombre.setEnabled(true);
                        bindingPropietario.apellidoP.setEnabled(true);
                        bindingPropietario.apellidoM.setEnabled(true);
                        bindingPropietario.telefono.setEnabled(true);
                        bindingPropietario.email.setEnabled(true);
                    }
                }
            });



        }else if (position == 2) {

            final float[] area = {0};
            bindingSuperficie = DataBindingUtil.inflate(inflater,R.layout.fragment_autoriza_2,container,false);
            view = bindingSuperficie.getRoot();
            bindingSuperficie.toolbar.nombreTitulo.setText(getString(R.string.datossuperficie));
            final SharedPreferences preferencesExpansion = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final SharedPreferences preferencesSuperficie = getContext().getSharedPreferences("datosSuperficieCrear", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferencesSuperficie.edit();
            editor.clear();
            editor.commit();
            getContext().getSharedPreferences("datosSuperficieCrear", 0).edit().clear().apply();
            mdLat = preferencesExpansion.getFloat("latMd", 0);
            mdLot = preferencesExpansion.getFloat("lotMd", 0);
            long mdid = preferencesExpansion.getLong("mdId", 0);
            final String convertido = String.valueOf(mdid);
            final String usuario = preferencesExpansion.getString("usuario", "");
            bindingSuperficie.frente.setText("");
            bindingSuperficie.profundidad.setText("");

            bindingSuperficie.frente.setFilters(new InputFilter[] {new CustomTextWatcher(4,1)});
            bindingSuperficie.profundidad.setFilters(new InputFilter[] {new CustomTextWatcher(4,1)});
           // bindingSuperficie.areaterreno.setFilters(new InputFilter[] {new CustomTextWatcher(5,1)});

            ProviderDatosPredial.getInstance(getContext()).obtenerDatosPredial(convertido, usuario, new ProviderDatosPredial.ConsultaDatosPredial() {
                @Override
                public void resolve(DatosPredial datosPredial) {
                    if(datosPredial!=null){
                        if(datosPredial.getCodigo().equals("200")){
                            if(datosPredial.getAplicaPredial().equals("1")){
                                bindingSuperficie.predial.setVisibility(View.VISIBLE);
                            }else{
                                bindingSuperficie.predial.setVisibility(View.GONE);
                                urlPredial = " ";
                                fechaPredial = " ";
                            }
                        }
                    }else{
                        bindingSuperficie.predial.setVisibility(View.GONE);
                        urlPredial = " ";
                        fechaPredial = " ";
                    }
                }
                @Override
                public void reject(Exception e) {
                    bindingSuperficie.predial.setVisibility(View.GONE);
                    urlPredial = " ";
                    fechaPredial = " ";
                }
            });

            final String[] tipoEsquina = {"0"};

            bindingSuperficie.escogeEsquina.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        tipoEsquina[0] = "1";
                    }else{
                        tipoEsquina[0] = "0";
                    }
                }
            });

            Timer timer = new Timer ();
            hourlyTaskSuperficie = new TimerTask () {
                @Override
                public void run () {

                    if(urlFrente.equals("") || urlLateral1.equals("") || urlLateral2.equals("")){
                        getContext().getSharedPreferences("datosSuperficieCrear", 0).edit().clear().apply();
                    }else{
                        String frente = bindingSuperficie.frente.getText().toString();
                        String profundidad = bindingSuperficie.profundidad.getText().toString();
                        CrearDatosSuperficie datos = new CrearDatosSuperficie(tipoEsquina[0], usuario, convertido,
                                frente, profundidad, urlLateral2, urlLateral1, urlFrente,
                                String.valueOf(mdLat), String.valueOf(mdLot), "", VERSION_APP, fechaFrente, fechaEntorno1, fechaEntorno2,
                                urlPredial,fechaPredial);
                        salvarDatosSuperficie(getContext(), datos, editor, preferencesSuperficie);
                    }
                }
            };
            timer.schedule (hourlyTaskSuperficie, 0l, 1000);

            bindingSuperficie.frente.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) { }
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    //Log.e("onTextChanged",s.toString());
                    String frente = bindingSuperficie.frente.getText().toString();
                    String profundidad = bindingSuperficie.profundidad.getText().toString();
                    if(frente.equals("") || profundidad.equals("")){
                        bindingSuperficie.areaterreno.setText("0mts");
                    }else{
                        if(!frente.equals(".") && !profundidad.equals(".")) {
                            area[0] = Float.valueOf(frente)*Float.valueOf(profundidad);
                            bindingSuperficie.areaterreno.setText(String.valueOf(area[0])+"");
                        }
                    }
                }
            });

            final int[] banderaCamara = {0};

            bindingSuperficie.profundidad.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) { }
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String frente = bindingSuperficie.frente.getText().toString();
                    String profundidad = bindingSuperficie.profundidad.getText().toString();
                    if(frente.equals("") || profundidad.equals("")){
                        bindingSuperficie.areaterreno.setText("0mts");
                    }else{
                        if(!frente.equals(".") && !profundidad.equals(".")) {
                            area[0] = Float.valueOf(frente)*Float.valueOf(profundidad);
                            bindingSuperficie.areaterreno.setText(String.valueOf(area[0])+"");
                        }
                    }
                }
            });

            ServicioGPS n = new ServicioGPS(getContext());
            final LatLng mds = new LatLng(mdLat, mdLot);
            final LatLng gps = new LatLng(n.getLatitude(), n.getLongitude());
            final Boolean distancia = distanciaSuperficie(mds, gps);



            bindingSuperficie.predial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(distancia){
                        bindingSuperficie.frontal.setAlpha(0.35f);
                        bindingSuperficie.lateral1.setAlpha(0.35f);
                        bindingSuperficie.lateral2.setAlpha(0.35f);
                        bindingSuperficie.predial.setAlpha(1.0f);
                        banderaCamara[0] = 4;
                        if(urlPredial.length()>0){
                            Picasso.get().load(urlPredial).into(bindingSuperficie.imagen);
                            bindingSuperficie.volver.setVisibility(View.VISIBLE);
                        }else{

                            bindingSuperficie.volver.setVisibility(View.GONE);
                            Intent pictureIntent = new Intent(
                                    MediaStore.ACTION_IMAGE_CAPTURE);
                            if(pictureIntent.resolveActivity(getContext().getPackageManager()) != null){
                                //Create a file to store the image
                                File photoFile = null;
                                try {
                                    photoFile = createImageFile(getContext());
                                } catch (IOException ex) {
                                    // Error occurred while creating the File
                                }
                                if (photoFile != null) {
                                    Uri photoURI = FileProvider.getUriForFile(getContext(),
                                            getString(R.string.file_provider_authority), photoFile);
                                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                    startActivityForResult(pictureIntent,
                                            CAMERA_PREDIAL);
                                }
                            }


                        }
                    }else{
                        Toast.makeText(getContext(), R.string.no_estas,
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });

            bindingSuperficie.frontal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(distancia){
                        bindingSuperficie.frontal.setAlpha(1.0f);
                        bindingSuperficie.lateral1.setAlpha(0.35f);
                        bindingSuperficie.lateral2.setAlpha(0.35f);
                        bindingSuperficie.predial.setAlpha(0.35f);
                        banderaCamara[0] = 1;
                        if(urlFrente.length()>0){
                            Picasso.get().load(urlFrente).into(bindingSuperficie.imagen);
                            bindingSuperficie.volver.setVisibility(View.VISIBLE);
                        }else{
                            bindingSuperficie.volver.setVisibility(View.GONE);
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA);
                        }
                    }else{
                        Toast.makeText(getContext(), R.string.no_estas,
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });

            bindingSuperficie.lateral1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(distancia){
                        bindingSuperficie.lateral1.setAlpha(1.0f);
                        bindingSuperficie.frontal.setAlpha(0.35f);
                        bindingSuperficie.lateral2.setAlpha(0.35f);
                        bindingSuperficie.predial.setAlpha(0.35f);

                        banderaCamara[0] = 2;

                        if(urlLateral1.length()>0){
                            Picasso.get().load(urlLateral1).into(bindingSuperficie.imagen);
                            bindingSuperficie.volver.setVisibility(View.VISIBLE);
                        }else{
                            bindingSuperficie.volver.setVisibility(View.GONE);
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_LATERAL_1);
                        }
                    }else{
                        Toast.makeText(getContext(), R.string.no_estas,
                                Toast.LENGTH_SHORT).show();
                    }



                }
            });

            bindingSuperficie.lateral2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(distancia){
                        bindingSuperficie.lateral2.setAlpha(1.0f);
                        bindingSuperficie.frontal.setAlpha(0.35f);
                        bindingSuperficie.lateral1.setAlpha(0.35f);
                        bindingSuperficie.predial.setAlpha(0.35f);

                        banderaCamara[0] = 3;
                        if(urlLateral2.length()>0){
                            Picasso.get().load(urlLateral2).into(bindingSuperficie.imagen);
                            bindingSuperficie.volver.setVisibility(View.VISIBLE);
                        }else{
                            bindingSuperficie.volver.setVisibility(View.GONE);
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_LATERAL_2);
                        }
                    }else{
                        Toast.makeText(getContext(), R.string.no_estas,
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });


            bindingSuperficie.volver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(distancia){
                        if(banderaCamara[0] ==1){
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA);
                        } else if(banderaCamara[0] ==2){
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_LATERAL_1);
                        } else if(banderaCamara[0] ==3){
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_LATERAL_2);
                        } else if(banderaCamara[0] ==4){

                            Intent pictureIntent = new Intent(
                                    MediaStore.ACTION_IMAGE_CAPTURE);
                            if(pictureIntent.resolveActivity(getContext().getPackageManager()) != null){
                                //Create a file to store the image
                                File photoFile = null;
                                try {
                                    photoFile = createImageFile(getContext());
                                } catch (IOException ex) {
                                    // Error occurred while creating the File
                                }
                                if (photoFile != null) {
                                    Uri photoURI = FileProvider.getUriForFile(getContext(),
                                            getString(R.string.file_provider_authority), photoFile);
                                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                    startActivityForResult(pictureIntent,
                                            CAMERA_PREDIAL);
                                }
                            }

                        }
                    }else{
                        Toast.makeText(getContext(), R.string.no_estas,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

            bindingSuperficie.cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentDialogCancelar a = new FragmentDialogCancelar();
                    a.show(getChildFragmentManager(),"child");
                }
            });


            bindingSuperficie.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentDialogCancelarMd a = new FragmentDialogCancelarMd();
                    a.show(getChildFragmentManager(),"child");
                }
            });

            bindingSuperficie.toolbar.guardar.setEnabled(true);

            bindingSuperficie.toolbar.guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bindingSuperficie.toolbar.guardar.setEnabled(false);

                    final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    Long md = preferences.getLong("mdId", 0);
                    String mdId = String.valueOf(md);

                    loadingProgress(progressDialog, 0);

                    if(mdId.length()==1){
                        mdId = "";
                    }

                    if(!mdId.equals("") || mdId.equals("0")){
                        mdLat = preferences.getFloat("latMd", 0);
                        mdLot = preferences.getFloat("lotMd", 0);
                        long mdid = preferences.getLong("mdId", 0);
                        String convertido = String.valueOf(mdid);

                        if(urlFrente.equals("") || urlLateral1.equals("") || urlLateral2.equals("")){
                            Toast.makeText(getContext(), R.string.mandar,
                                    Toast.LENGTH_SHORT).show();
                            bindingSuperficie.toolbar.guardar.setEnabled(true);
                            loadingProgress(progressDialog, 1);

                        }else{
                            String usuario = preferences.getString("usuario", "");

                            String frente = bindingSuperficie.frente.getText().toString();
                            String profundidad = bindingSuperficie.profundidad.getText().toString();

                            CrearDatosSuperficie datos = new CrearDatosSuperficie(tipoEsquina[0], usuario, convertido,
                                    frente, profundidad, urlLateral2, urlLateral1, urlFrente,
                                    String.valueOf(mdLat), String.valueOf(mdLot), "", VERSION_APP, fechaFrente, fechaEntorno1, fechaEntorno2,
                                    urlPredial,fechaPredial);

                            ProviderCrearSuperficie.getInstance(getContext()).guardarSuperficie(datos, new ProviderCrearSuperficie.InterfaceCrearDatosSuperficie() {
                                @Override
                                public void resolve(Codigos codigo) {
                                    if(codigo.getCodigo()==200){
                                        bindingSuperficie.toolbar.guardar.setEnabled(true);
                                        FragmentDialogGuardar a = new FragmentDialogGuardar();
                                        a.show(getChildFragmentManager(),"child");
                                        loadingProgress(progressDialog, 1);
                                    }else{
                                        loadingProgress(progressDialog, 1);
                                        Toast.makeText(getContext(), codigo.getMensaje(),
                                                Toast.LENGTH_SHORT).show();
                                        bindingSuperficie.toolbar.guardar.setEnabled(true);
                                    }
                                }

                                @Override
                                public void reject(Exception e) { }
                            });

                        }
                    }else {
                        loadingProgress(progressDialog, 1);
                        bindingSuperficie.toolbar.guardar.setEnabled(true);
                    }
                }
            });

            String nombreMd = preferencesExpansion.getString("nombreSitio", "");
            bindingSuperficie.robotoTextView2.setText(nombreMd);

        }else if (position == 3) {
            mensaje = "fragment 2";
            bindingZonificacion = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_3,container,false);
            view = bindingZonificacion.getRoot();
            getContext().getSharedPreferences("datosZonificacion", 0).edit().clear().apply();
            SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final long mdid = preferences.getLong("mdId", 0);
            mdLat = preferences.getFloat("latMd", 0);
            mdLot = preferences.getFloat("lotMd", 0);
            String convertido = String.valueOf(mdid);
            mdId = convertido;
            final String usuario = preferences.getString("usuario", "");

            String nombreMd = preferences.getString("nombreSitio", "");
            bindingZonificacion.robotoTextView2.setText(nombreMd);
            bindingZonificacion.toolbar.nombreTitulo.setText(getString(R.string.zonifica));
            slideUX(bindingZonificacion);

            final SlideUp slideUp;
            slideUp = new SlideUpBuilder(bindingZonificacion.slideRuralStart.slideViewRural)
                    .withListeners(new SlideUp.Listener.Events() {
                        @Override
                        public void onSlide(float percent) { }

                        @Override
                        public void onVisibilityChanged(int visibility) {
                            if (visibility == 0){
                                bindingZonificacion.fab.hide();
                            } else if(visibility == 8){
                                bindingZonificacion.fab.show();
                            }
                        }
                    })
                    .withStartGravity(Gravity.TOP)
                    .withLoggingEnabled(true)
                    .withStartState(SlideUp.State.HIDDEN)
                    .build();

            bindingZonificacion.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bindingZonificacion.fab.hide();
                    slideUp.show();
                }
            });

            ProviderDatosCompetencias.getInstance(getContext()).obtenerDatosCompetencias(usuario, mdId, new ProviderDatosCompetencias.ConsultaDatosCompetencia() {
                @Override
                public void resolve(CompetenciasGeneradoresV2 competenciasGeneradores) {
                    if(competenciasGeneradores.getCodigo()==200){
                        if(competenciasGeneradores!=null){
                            listCompetencia = new ArrayList<>();
                            listGeneradores = new ArrayList<>();
                            listCompetenciaTiendaNeto = new ArrayList<>();
                            listGeneradoresNegocios = new ArrayList<>();
                            listGeneradoresTransporte= new ArrayList<>();
                            negociosDeComidaArrayList = new ArrayList<>();
                            mercadoPublicoArrayList = new ArrayList<>();
                            tianguiArrayList = new ArrayList<>();

                            if(competenciasGeneradores!=null){
                                for(int i=0;i<competenciasGeneradores.getCompetencias().getCompetencias().size();i++){
                                    listCompetencia.add(competenciasGeneradores.getCompetencias().getCompetencias().get(i));
                                }

                                for(int i=0;i<competenciasGeneradores.getCompetencias().getTiendaNeto().size();i++){
                                    listCompetenciaTiendaNeto.add(competenciasGeneradores.getCompetencias().getTiendaNeto().get(i));
                                }

                                for(int i=0;i<competenciasGeneradores.getGeneradores().getOtrosGeneradores().size();i++){
                                    listGeneradores.add(competenciasGeneradores.getGeneradores().getOtrosGeneradores().get(i));
                                }

                                for(int i=0;i<competenciasGeneradores.getGeneradores().getNegocios().size();i++){
                                    listGeneradoresNegocios.add(competenciasGeneradores.getGeneradores().getNegocios().get(i));
                                }

                                for(int i=0;i<competenciasGeneradores.getGeneradores().getTransportePublico().size();i++){
                                    listGeneradoresTransporte.add(competenciasGeneradores.getGeneradores().getTransportePublico().get(i));
                                }

                                for(int i=0;i<competenciasGeneradores.getGeneradores().getNegociosDeComida().size();i++){
                                    negociosDeComidaArrayList.add(competenciasGeneradores.getGeneradores().getNegociosDeComida().get(i));
                                }

                                for(int i=0;i<competenciasGeneradores.getGeneradores().getMercadoPublico().size();i++){
                                    mercadoPublicoArrayList.add(competenciasGeneradores.getGeneradores().getMercadoPublico().get(i));
                                }

                                for(int i=0;i<competenciasGeneradores.getGeneradores().getTianguis().size();i++){
                                    tianguiArrayList.add(competenciasGeneradores.getGeneradores().getTianguis().get(i));
                                }
                            }

                            /****** transporte negocio comida *******/

                            adapterListaGeneradoresNegociosComida = new AdapterListaGeneradoresNegociosComida(negociosDeComidaArrayList, getContext(), clickNegociosComida);
                            bindingZonificacion.content2.contentListaComida.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaComida.setAdapter(adapterListaGeneradoresNegociosComida);
                            RecyclerView.LayoutManager mLayoutManagerNegocio = new GridLayoutManager(getContext(), 4);

                            bindingZonificacion.content2.contentListaComida.setLayoutManager(mLayoutManagerNegocio);
                            bindingZonificacion.content2.contentListaComida.addItemDecoration(new FragmentAutoriza.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaComida.setItemAnimator(new DefaultItemAnimator());

                            /****** mercado publico *******/
                            adapterListaGeneradoresMercadoPublico = new AdapterListaGeneradoresMercadoPublico(mercadoPublicoArrayList, getContext(), clickMercadoPublico);
                            bindingZonificacion.content2.contentListaMercado.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaMercado.setAdapter(adapterListaGeneradoresMercadoPublico);
                            RecyclerView.LayoutManager mLayoutManagerMercado = new GridLayoutManager(getContext(), 4);

                            bindingZonificacion.content2.contentListaMercado.setLayoutManager(mLayoutManagerMercado);
                            bindingZonificacion.content2.contentListaMercado.addItemDecoration(new FragmentAutoriza.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaMercado.setItemAnimator(new DefaultItemAnimator());

                            /****** tianguis *******/
                            adapterListaGeneradoresTianguis = new AdapterListaGeneradoresTianguis(tianguiArrayList, getContext(), clickTiaguis);
                            bindingZonificacion.content2.contentListaTianguis.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaTianguis.setAdapter(adapterListaGeneradoresTianguis);


                            RecyclerView.LayoutManager mLayoutManagerTianguis = new GridLayoutManager(getContext(), 4);
                            bindingZonificacion.content2.contentListaTianguis.setLayoutManager(mLayoutManagerTianguis);
                            bindingZonificacion.content2.contentListaTianguis.addItemDecoration(new FragmentAutoriza.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaTianguis.setItemAnimator(new DefaultItemAnimator());


                            /****** transporte publico *******/
                            adapterTransporte = new AdapterListaGeneradoresTransporte(listGeneradoresTransporte, getContext(), clickTransporte);
                            bindingZonificacion.content2.contentListaTransporte.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaTransporte.setAdapter(adapterTransporte);

                            RecyclerView.LayoutManager mLayoutManagerTransporte = new GridLayoutManager(getContext(), 4);
                            bindingZonificacion.content2.contentListaTransporte.setLayoutManager(mLayoutManagerTransporte);
                            bindingZonificacion.content2.contentListaTransporte.addItemDecoration(new FragmentAutoriza.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaTransporte.setItemAnimator(new DefaultItemAnimator());

                            /****** otros generadores *******/
                            adapterNegocios = new AdapterListaGeneradoresNegocios(listGeneradoresNegocios, getContext(), clickNegocio);
                            bindingZonificacion.content2.contentListaNegocios.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaNegocios.setAdapter(adapterNegocios);

                            RecyclerView.LayoutManager mLayoutManagerNegocios = new GridLayoutManager(getContext(), 4);
                            bindingZonificacion.content2.contentListaNegocios.setLayoutManager(mLayoutManagerNegocios);
                            bindingZonificacion.content2.contentListaNegocios.addItemDecoration(new FragmentAutoriza.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaNegocios.setItemAnimator(new DefaultItemAnimator());

                            /****** otros generadores *******/
                            adapter2 = new AdapterListaGeneradores(listGeneradores, getContext(), clicks);
                            bindingZonificacion.content2.contentLista.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentLista.setAdapter(adapter2);

                            RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(getContext(), 4);
                            bindingZonificacion.content2.contentLista.setLayoutManager(mLayoutManager2);
                            bindingZonificacion.content2.contentLista.addItemDecoration(new FragmentAutoriza.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentLista.setItemAnimator(new DefaultItemAnimator());

                            /****** competencias *******/
                            adapter = new AdapterListaCompetencia(listCompetencia, getContext(), click);
                            bindingZonificacion.contenido.contentLista.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.contenido.contentLista.setAdapter(adapter);

                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
                            bindingZonificacion.contenido.contentLista.setLayoutManager(mLayoutManager);
                            bindingZonificacion.contenido.contentLista.addItemDecoration(new FragmentAutoriza.GridSpacingItemDecoration(3, dpToPx(4), true));
                            bindingZonificacion.contenido.contentLista.setItemAnimator(new DefaultItemAnimator());

                            /****** tiendas neto *******/
                            adapterTiendaNeto = new AdapterListaTiendaNeto(listCompetenciaTiendaNeto, getContext(), clickTiendaNeto);
                            bindingZonificacion.contenido.contentListaTienda.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.contenido.contentListaTienda.setAdapter(adapterTiendaNeto);

                            RecyclerView.LayoutManager mLayoutManagerTienda = new GridLayoutManager(getContext(), 3);
                            bindingZonificacion.contenido.contentListaTienda.setLayoutManager(mLayoutManagerTienda);
                            bindingZonificacion.contenido.contentListaTienda.addItemDecoration(new FragmentAutoriza.GridSpacingItemDecoration(3, dpToPx(4), true));
                            bindingZonificacion.contenido.contentListaTienda.setItemAnimator(new DefaultItemAnimator());

                            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                                    .findFragmentById(R.id.map);
                            mapFragment.getMapAsync(onMapReadyCallbackZonificacion);
                        }

                    }


                }

                @Override
                public void reject(Exception e) {

                }
            });


            bindingZonificacion.competencia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideCompetencia.show();
                    SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("zonificacion", 0);
                    editor.apply();
                }
            });

            bindingZonificacion.generador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideGenerador.show();
                    bindingZonificacion.content2.contentSlideUpView.setVisibility(View.VISIBLE);
                    SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("zonificacion", 1);
                    editor.apply();
                }
            });

            bindingZonificacion.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentDialogCancelarMd a = new FragmentDialogCancelarMd();
                    a.show(getChildFragmentManager(),"child");
                }
            });

            bindingZonificacion.contenido.competencias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideCompetencia.hide();
                }
            });

            bindingZonificacion.contenido.neto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideCompetencia.hide();
                    bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                }
            });

            bindingZonificacion.content2.generadores.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideGenerador.hide();
                    bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                }
            });

            bindingZonificacion.content2.negocios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideGenerador.hide();
                    bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                }
            });

            ///////////////////////////////

            bindingZonificacion.content2.transporte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideGenerador.hide();
                    bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                }
            });

            bindingZonificacion.content2.negC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideGenerador.hide();
                    bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                }
            });

            bindingZonificacion.content2.merP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideGenerador.hide();
                    bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                }
            });

            bindingZonificacion.content2.tiand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideGenerador.hide();
                    bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                }
            });




            bindingZonificacion.toolbar.guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bindingZonificacion.toolbar.guardar.setEnabled(false);

                    final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    Long md = preferences.getLong("mdId", 0);
                    String mdId = String.valueOf(md);
                    loadingProgress(progressDialog, 0);

                    if(mdId.length()==1){ mdId = ""; }

                    if(!mdId.equals("")){
                        if(zonificacionJson.equals("")){
//                            detallesG = new ArrayList<>();;
//
//                            detalleG = new CrearZonificacion.Detalle(
//                                    "6"
//                            );
//
//                            detallesG.add(detalleG);
//                            zonificacionG.setDetalles(detallesG);
//
                            generadores = new ArrayList<>();
                           // generadores.add(zonificacionG);
//
//                            detallesC = new ArrayList<>();
//                            detalleC = new CrearZonificacion.Detalle(
//                                    "1"
//                            );
//
//                            detallesC.add(detalleC);
//                            zonificacionC.setDetalles(detallesC);

                            competencia = new ArrayList<>();
                           // competencia.add(zonificacionC);

                            zonificacion = new CrearZonificacion(
                                    usuario,
                                    mdId,
                                    competencia,
                                    generadores,
                                    String.valueOf(mdLat),
                                    String.valueOf(mdLot),
                                    "5555555555",
                                    VERSION_APP
                            );

                            zonificacionJson = getJsonString(zonificacion);

                            ProviderCrearZonificacion.getInstance(getContext()).crearDatosZonificacion(zonificacionJson, new ProviderCrearZonificacion.InterfaceCrearDatosZonificacion() {
                                @Override
                                public void resolve(Codigos codigo) {
                                    if(codigo.getCodigo()==200){
                                        FragmentDialogGuardar a = new FragmentDialogGuardar();
                                        a.show(getChildFragmentManager(),"child");
                                        bindingZonificacion.toolbar.guardar.setEnabled(true);
                                        loadingProgress(progressDialog, 1);

                                    }else {
                                        Toast.makeText(getContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                                        bindingZonificacion.toolbar.guardar.setEnabled(true);
                                        loadingProgress(progressDialog, 1);
                                    }
                                }

                                @Override
                                public void reject(Exception e) { }
                            });
                        }else{
                            zonificacionJson = getJsonString(zonificacion);
                            ProviderCrearZonificacion.getInstance(getContext()).crearDatosZonificacion(zonificacionJson, new ProviderCrearZonificacion.InterfaceCrearDatosZonificacion() {
                                @Override
                                public void resolve(Codigos codigo) {
                                    if(codigo.getCodigo()==200){
                                        FragmentDialogGuardar a = new FragmentDialogGuardar();
                                        a.show(getChildFragmentManager(),"child");
                                        bindingZonificacion.toolbar.guardar.setEnabled(true);
                                        loadingProgress(progressDialog, 1);
                                    }else {
                                        Toast.makeText(getContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                                        bindingZonificacion.toolbar.guardar.setEnabled(true);
                                        loadingProgress(progressDialog, 1);

                                    }
                                }

                                @Override
                                public void reject(Exception e) { }
                            });
                        }
                    }

                }
            });

            final SharedPreferences preferencesZonifica = getContext().getSharedPreferences("datosZonificacion", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = null;

            Timer timer = new Timer ();
            TimerTask hourlyTaskZonificacion = new TimerTask () {
                @Override
                public void run () {
                    if(zonificacionJson.length()>0){
                        salvarDatosZonificacion(getContext(), zonificacionJson, editor, preferencesZonifica);
                    }
                }
            };
            timer.schedule (hourlyTaskZonificacion, 0l, 1000);

        }else if (position == 4) {

            bindingConstruccion = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_4,container,false);
            view = bindingConstruccion.getRoot();

            getContext().getSharedPreferences("datosConstruccion", 0).edit().clear().apply();

            SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final Long mdIdterminar = preferences.getLong("mdId", 0);
            final String usuarioId = preferences.getString("usuario", "");
            final String nombreSitio = preferences.getString("nombreSitio","");
            bindingConstruccion.titulo.setText(nombreSitio+"");

            bindingConstruccion.toolbar.nombreTitulo.setText(getString(R.string.construccion));
            ProviderDatosFactoresConstruccion.getInstance(getContext()).obtenerDatosContruccion(String.valueOf(mdIdterminar),
                    new ProviderDatosFactoresConstruccion.ConsultaFactoresConstruccion() {
                        @Override
                        public void resolve(final FactoresConstruccion factoresConstruccion) {
                            if (factoresConstruccion != null){
                                if (factoresConstruccion.getCodigo() == 200) {
                                    if (factoresConstruccion.getCatalogo() != null) {

                                        ProviderDatosConstruccion.getInstance(getContext())
                                                .obtenerDatosConstruccion(String.valueOf(mdIdterminar), usuarioId, new ProviderDatosConstruccion.ConsultaDatosConstruccion() {
                                                    @Override
                                                    public void resolve(DatosConstruccions datosSitio) {

                                                        getContext().getSharedPreferences("datosConstruccion", 0).edit().clear().apply();

                                                        if (datosSitio != null) {
                                                            if (datosSitio.getCodigo() == 200 && datosSitio.getConstruccion().size() > 0) {

                                                                generarConstruccion(
                                                                        bindingConstruccion,
                                                                        factoresConstruccion,
                                                                        datosSitio);

                                                                generarDetalles(bindingConstruccion,
                                                                        factoresConstruccion,
                                                                        datosSitio);

                                                                generarCondiciones(bindingConstruccion,
                                                                        factoresConstruccion,
                                                                        datosSitio);

                                                                bindingConstruccion.cargar.setVisibility(View.GONE);


                                                            } else {

                                                                generarConstruccion(
                                                                        bindingConstruccion,
                                                                        factoresConstruccion);

                                                                generarDetalles(bindingConstruccion,
                                                                        factoresConstruccion);

                                                                generarCondiciones(bindingConstruccion,
                                                                        factoresConstruccion);
                                                                bindingConstruccion.cargar.setVisibility(View.GONE);

                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void reject(Exception e) {

                                                    }
                                                });
                                    }
                                } else {
                                    Toast.makeText(getContext(), "Error al consultar factores de construcción", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void reject(Exception e) {

                        }
                    });



            bindingConstruccion.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentDialogCancelarMd a = new FragmentDialogCancelarMd();
                    a.show(getChildFragmentManager(),"child");
                }
            });

            bindingConstruccion.aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentDialogAceptar a = new FragmentDialogAceptar();
                    a.show(getChildFragmentManager(),"child");
                }
            });

            bindingConstruccion.cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentDialogCancelar a = new FragmentDialogCancelar();
                    a.show(getChildFragmentManager(),"child");
                }
            });

            bindingConstruccion.toolbar.guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bindingConstruccion.toolbar.guardar.setEnabled(false);

                    final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    Long mdIdterminar = preferences.getLong("mdId", 0);

                    loadingProgress(progressDialog, 0);


                    if(String.valueOf(mdIdterminar).length()==1){
                        mdIdterminar = Long.valueOf("");
                    }

                    if(!mdIdterminar.equals("")){
                        if(nivelId==0 || nivelIdCondicion==0){
                            loadingProgress(progressDialog, 1);
                            bindingConstruccion.toolbar.guardar.setEnabled(true);
                        }else{

                            niveles = new ArrayList<>();

                            if(nivelIdCondicion==5 || nivelIdCondicion == 4 || nivelIdCondicion == 3){

                                detallesCondicion = new ArrayList<>();

                                DatosConstruccion.Nivele detalleCondicion = new DatosConstruccion.Nivele(
                                        nivelIdCondicion, detallesCondicion);

                                niveles.add(detalleCondicion);

                            }

                            if(nivelId==1 || nivelId==2){

                                detallesContruccion = new ArrayList<>();

                                if(nivelId==1){


                                }else if(nivelId==2){
                                    for ( Map.Entry<Integer, String> entry : checks.entrySet()) {
                                        Integer valor = entry.getKey();
                                        String check = entry.getValue();

                                        if(valor==0 && check =="1"){
                                            detalleConstruccion = new DatosConstruccion.Detalle(valor+1);
                                            detallesContruccion.add(detalleConstruccion);
                                        }

                                        if(valor==1 && check =="1"){
                                            detalleConstruccion = new DatosConstruccion.Detalle(valor+1);
                                            detallesContruccion.add(detalleConstruccion);
                                        }

                                        if(valor==2 && check =="1"){
                                            detalleConstruccion = new DatosConstruccion.Detalle(valor+1);
                                            detallesContruccion.add(detalleConstruccion);
                                        }

                                        if(valor==3 && check =="1"){
                                            detalleConstruccion = new DatosConstruccion.Detalle(valor+1);
                                            detallesContruccion.add(detalleConstruccion);
                                        }
                                    }
                                }

                                DatosConstruccion.Nivele detalleConstruccion = new DatosConstruccion.Nivele(
                                        nivelId, detallesContruccion);
                                niveles.add(detalleConstruccion);

                            }

                            datosConstruccion = new DatosConstruccion(
                                    String.valueOf(mdIdterminar),
                                    usuarioId,
                                    RestUrl.FACTOR_ID,
                                    "5540555599",
                                    VERSION_APP,
                                    niveles
                            );

                            datosConstruccionJson = getJsonString(datosConstruccion);

                            ProviderCrearConstruccion.getInstance(getContext()).crearDatosConstruccion(datosConstruccionJson,
                                    new ProviderCrearConstruccion.InterfaceCrearDatosConstruccion() {
                                        @Override
                                        public void resolve(Codigos codigo) {
                                            if(codigo.getCodigo()==200){
                                                FragmentDialogGuardar a = new FragmentDialogGuardar();
                                                a.show(getChildFragmentManager(),"child");
                                                bindingConstruccion.toolbar.guardar.setEnabled(true);
                                                loadingProgress(progressDialog, 1);
                                            }else{
                                                Toast.makeText(getContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                                                bindingConstruccion.toolbar.guardar.setEnabled(true);
                                                loadingProgress(progressDialog, 1);
                                            }
                                        }

                                        @Override
                                        public void reject(Exception e) {

                                        }
                                    });
                        }
                        datosConstruccion = null;
                    }
                }
            });

            final SharedPreferences preferencesConstruccion = getContext().getSharedPreferences("datosConstruccion", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferencesConstruccion.edit();

            Timer timer = new Timer ();
            hourlyTask = new TimerTask () {
                @Override
                public void run () {
                    datosConstruccion(String.valueOf(mdIdterminar), usuarioId);
                    datosConstruccion(String.valueOf(mdIdterminar), usuarioId);

                    if(datosConstruccionJson.length()>0 && !datosConstruccion.equals("")){
                        salvarDatosConstruccion(datosConstruccionJson, editor);
                    }else{
                        editor.putString("json", "");
                        editor.apply();
                    }
                }
            };
            timer.schedule (hourlyTask, 300, 800);

        }else if (position == 5) {
            final FragmentAutoriza5Binding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_5,container,false);
            view = binding.getRoot();

            getContext().getSharedPreferences("datosGeneralidades", 0).edit().clear().apply();

            binding.toolbar.nombreTitulo.setText(getString(R.string.generalidades));

            binding.renta.setText("");
            binding.amortizaciontotal.setText("");

            final CrearGeneralidades[] crearGeneralidades = {null};
            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);

            final String[] disponibilidad = new String[1];
            String nombreMd = preferences.getString("nombreSitio", "");

            binding.robotoTextView2.setText(nombreMd);

            final SharedPreferences preferencesGeneralidades = getContext().getSharedPreferences("datosGeneralidades", Context.MODE_PRIVATE);

            final SharedPreferences.Editor editor = preferencesGeneralidades.edit();

            binding.datepicker.setMinDate(System.currentTimeMillis() - 1000);

            String mdId = String.valueOf(preferences.getLong("mdId", 0));
            String usuarioId = preferences.getString("usuario", "");

            ProviderDatosAmortizacion.getInstance(getContext()).obtenerDatosAmortizacion(mdId, usuarioId, new ProviderDatosAmortizacion.ConsultaDatosAmortizacion() {
                @Override
                public void resolve(Amortizacion datosPredial) {
                    if(datosPredial!=null){

                        ArrayList<String> amortizacion = new ArrayList<>();

                        for(int i = 0;i<datosPredial.getAmortizacion().size();i++){
                            amortizacion.add(datosPredial.getAmortizacion().get(i).getOpcion());
                        }

                        ArrayList<String> gracia = new ArrayList<>();

                        for(int j = 0;j<datosPredial.getGracia().size();j++){
                            gracia.add(datosPredial.getGracia().get(j).getOpcion());
                        }

                        ArrayAdapter<String> amortizacionSpinner = new ArrayAdapter<String>(getContext(),   android.R.layout.simple_spinner_item,
                                amortizacion);
                        amortizacionSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        binding.periodoamotizacion.setAdapter(amortizacionSpinner);

                        ArrayAdapter<String> graciaSpinner = new ArrayAdapter<String>(getContext(),   android.R.layout.simple_spinner_item,
                                gracia);
                        graciaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        binding.periodogracia.setAdapter(graciaSpinner);

                    }
                }

                @Override
                public void reject(Exception e) {

                }
            });

            Timer timer = new Timer ();
            hourlyTask = new TimerTask () {
                @Override
                public void run () {
                    //TODO datos construccion
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    int day = binding.datepicker.getDayOfMonth();
                    int month = binding.datepicker.getMonth();
                    int year =  binding.datepicker.getYear();

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, day);

                    String mdId = String.valueOf(preferences.getLong("mdId", 0));
                    String usuarioId = preferences.getString("usuario", "");
                    String renta = binding.renta.getText().toString();
                    String fechadisponible = dateFormat.format(calendar.getTime());
                    String porcentajeamortiza = binding.amortizaciontotal.getText().toString();

                    String periodoamortizacion = binding.periodoamotizacion.getSelectedItem().toString();
                    String periodogracia = binding.periodogracia.getSelectedItem().toString();;
                    String numtelefono = "5540555599";
                    String versionapp = VERSION_APP;

                    String arr[] = periodoamortizacion.split(" ", 2);
                    periodoamortizacion = arr[0];

                    String arra[] = periodogracia.split(" ", 2);
                    periodogracia = arra[0];

                    if(!renta.equals("") || !porcentajeamortiza.equals("")){
                        if(disponibilidad[0]!=null){

                            if(disponibilidad[0].equals("1")
                                    || disponibilidad[0].equals("2")){

                                crearGeneralidades[0] = new CrearGeneralidades(
                                        mdId,
                                        usuarioId,
                                        renta,
                                        disponibilidad[0],
                                        getFecha(),
                                        porcentajeamortiza,
                                        periodoamortizacion,
                                        periodogracia,
                                        numtelefono,
                                        versionapp
                                );
                            }else{
                                crearGeneralidades[0] = new CrearGeneralidades(
                                        mdId,
                                        usuarioId,
                                        renta,
                                        disponibilidad[0],
                                        fechadisponible,
                                        porcentajeamortiza,
                                        periodoamortizacion,
                                        periodogracia,
                                        numtelefono,
                                        versionapp
                                );
                            }

                            if(!crearGeneralidades[0].getRenta().equals("") &&
                                    !crearGeneralidades[0].getPorcentajeamortiza().equals("")){
                                GuardarDatosGeneralidades.salvarDatosGeneralidades(getContext(), crearGeneralidades[0], editor, preferencesGeneralidades);
                            }
                        }
                    }
                }
            };

            timer.schedule (hourlyTask, 0l, 1000);



            binding.rdgGrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rinmediato:
                            disponibilidad[0] = "1";
                            binding.datepicker.setVisibility(View.GONE);
                            break;
                        case R.id.rocupado:
                            disponibilidad[0] = "2";
                            binding.datepicker.setVisibility(View.GONE);
                            break;
                        case R.id.rapartirde:
                            disponibilidad[0] = "3";
                            binding.datepicker.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            });

            binding.amortizaciontotal.setFilters(new InputFilter[]{new Util.InputFilterMinMax("0", "999999")});

            binding.toolbar.guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.toolbar.guardar.setEnabled(false);

                    final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    Long md = preferences.getLong("mdId", 0);
                    String mdId = String.valueOf(md);
                    loadingProgress(progressDialog, 0);

                    if(mdId.length()==1){
                        mdId = "";
                    }

                    if(!mdId.equals("") || mdId.equals("0")){
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        int day = binding.datepicker.getDayOfMonth();
                        int month = binding.datepicker.getMonth();
                        int year =  binding.datepicker.getYear();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);

                            mdId = String.valueOf(preferences.getLong("mdId", 0));
                        String usuarioId = preferences.getString("usuario", "");
                        String renta = binding.renta.getText().toString();
                        String fechadisponible = dateFormat.format(calendar.getTime());;
                        String porcentajeamortiza = binding.amortizaciontotal.getText().toString();;

                        String periodoamortizacion = binding.periodoamotizacion.getSelectedItem().toString();
                        String periodogracia = binding.periodogracia.getSelectedItem().toString();;
                        String numtelefono = "";
                        String versionapp = VERSION_APP;

                        String arr[] = periodoamortizacion.split(" ", 2);
                        periodoamortizacion = arr[0];

                        String arra[] = periodogracia.split(" ", 2);
                        periodogracia = arra[0];

                        if(disponibilidad[0]!=null && !porcentajeamortiza.equals("")){
                            if(disponibilidad[0].equals("1")
                                    || disponibilidad[0].equals("2")){

                                crearGeneralidades[0] = new CrearGeneralidades(
                                        mdId,
                                        usuarioId,
                                        renta,
                                        disponibilidad[0],
                                        getFecha(),
                                        porcentajeamortiza,
                                        periodoamortizacion,
                                        periodogracia,
                                        numtelefono,
                                        versionapp
                                );
                            }else{
                                crearGeneralidades[0] = new CrearGeneralidades(
                                        mdId,
                                        usuarioId,
                                        renta,
                                        disponibilidad[0],
                                        fechadisponible,
                                        porcentajeamortiza,
                                        periodoamortizacion,
                                        periodogracia,
                                        numtelefono,
                                        versionapp
                                );
                            }
                        }

                        ProviderCrearGeneralidades.getInstance(getContext()).guardarGeneralidades(crearGeneralidades[0], new ProviderCrearGeneralidades.InterfaceCrearDatosGeneralidades() {
                            @Override
                            public void resolve(Codigos codigo) {
                                if(codigo.getCodigo()==200){

                                    FragmentDialogGuardar a = new FragmentDialogGuardar();
                                    a.show(getChildFragmentManager(),"child");
                                    binding.toolbar.guardar.setEnabled(true);
                                    loadingProgress(progressDialog, 1);


                                }else{
                                    Toast.makeText(getContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                                    binding.toolbar.guardar.setEnabled(true);
                                    loadingProgress(progressDialog, 1);


                                }
                            }

                            @Override
                            public void reject(Exception e) {

                            }
                        });
                    }
                }
            });


            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentDialogCancelarMd a = new FragmentDialogCancelarMd();
                    a.show(getChildFragmentManager(),"child");
                }
            });


        }else {

            final FragmentAutoriza6Binding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_6,container,false);
            view = binding.getRoot();

            final ArrayList<String> horarios = new ArrayList<>();
            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String mdId = String.valueOf(preferences.getLong("mdId", 0));
            final String usuarioId = preferences.getString("usuario", "");

            binding.peatonalConteo.presion.setAlpha(1f);
            binding.peatonalConteo.presion.setEnabled(true);

            binding.peatonalConteo.cronometroPlay.setEnabled(false);
            binding.peatonalConteo.cronometroStop.setEnabled(false);

            final int[] conteos = {0};
            final long[] tiempos = {0};

            binding.peatonalConteo.presion.setAlpha(1f);
            binding.peatonalConteo.presion.setEnabled(true);

            binding.toolbar.guardar.setVisibility(View.GONE);
            binding.peatonalConteo.cronometroPlay.setEnabled(false);
            binding.peatonalConteo.cronometroStop.setEnabled(false);
            binding.peatonalConteo.btnGuardar.setEnabled(false);
            binding.peatonalConteo.btnGuardar.setAlpha(.4f);
            final CountDownTimer[] downTimer = new CountDownTimer[1];

            final int[] tiempo = new int[1];
            final boolean[] hora = {false};

            binding.peatonalConteo.presion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (conteos[0]==0) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            binding.peatonalConteo.chronometer1.setCountDown(true);
                        }

                        int sec = 60* tiempo[0];

                        downTimer[0] = new CountDownTimer(1000 * sec, 1000) {

                            public void onTick(long millisUntilFinished) {
                                String v = String.format("%02d", millisUntilFinished / 60000);
                                int va = (int) ((millisUntilFinished % 60000) / 1000);
                                binding.peatonalConteo.contador.setText(v + ":" + String.format("%02d", va));
                            }

                            public void onFinish() {
                                binding.peatonalConteo.contador.setText("00:00");
                                binding.peatonalConteo.btnGuardar.setEnabled(true);
                                binding.peatonalConteo.btnGuardar.setAlpha(1.f);
                                binding.peatonalConteo.presion.setEnabled(false);
                            }
                        };
                        downTimer[0].start();
                        binding.peatonalConteo.cronometroPlay.setEnabled(false);
                        binding.peatonalConteo.cronometroStop.setEnabled(true);
                        binding.peatonalConteo.btnGuardar.setEnabled(false);
                        binding.peatonalConteo.btnGuardar.setAlpha(.4f);
                    }

                    conteos[0]++;
                    if(conteos[0]<=9){
                        binding.peatonalConteo.real.setText(conteos[0]+"");
                    }

                    if (conteos[0]>9) {
                        String real = String.valueOf(conteos[0]).substring(String.valueOf(conteos[0]).length() - 1);
                        char first = String.valueOf(conteos[0]).charAt(0);
                        binding.peatonalConteo.real.setText(real+"");
                        binding.peatonalConteo.diez.setText(first+"");
                    }

                    if (conteos[0]>99) {
                        char primerNumero = String.valueOf(conteos[0]).charAt(0);
                        char segundoNumero = String.valueOf(conteos[0]).charAt(1);
                        char tercerNumero = String.valueOf(conteos[0]).charAt(2);
                        binding.peatonalConteo.cien.setText(primerNumero+"");
                        binding.peatonalConteo.diez.setText(segundoNumero+"");
                        binding.peatonalConteo.real.setText(tercerNumero+"");
                    }

                    if (conteos[0]>999) {

                        char primerNumero = String.valueOf(conteos[0]).charAt(0);
                        char segundoNumero = String.valueOf(conteos[0]).charAt(1);
                        char tercerNumero = String.valueOf(conteos[0]).charAt(2);
                        char cuartoNumero = String.valueOf(conteos[0]).charAt(3);

                        binding.peatonalConteo.mil.setText(primerNumero+"");
                        binding.peatonalConteo.cien.setText(segundoNumero+"");
                        binding.peatonalConteo.diez.setText(tercerNumero+"");
                        binding.peatonalConteo.real.setText(cuartoNumero+"");

                    }

                }
            });

            String nombreMd = preferences.getString("nombreSitio", "");
            binding.robotoTextView2.setText(nombreMd);
            listaPeatonal(binding);
            ProviderHorasPeatonales.getInstance(getContext()).obtenerHoras(mdId, usuarioId,
                    new ProviderHorasPeatonales.InterfaceObtieneHoras() {
                        @Override
                        public void resolve(final HorasPeatonales horasPeatonales) {
                            if(horasPeatonales!=null){
                                if(horasPeatonales.getCodigo()==200) {

                                    tiempo[0] = Integer.parseInt(horasPeatonales.getTiempoConteos());
                                    binding.peatonalConteo.peatonalN.setText(
                                            "Recuerda que el conteo peatonal debe ser de "+tiempo[0]+" min"

                                    );

                                    binding.peatonalConteo.chronometer1.setBase(SystemClock.elapsedRealtime() - (tiempo[0] * 60000 + 0 * 1000));
                                    tiempos[0] = TimeUnit.MINUTES.toMillis(tiempo[0]);
                                    if(horasPeatonales.getDetalle().size()<0){
                                        binding.btnFinalizar.setAlpha(0.35f);
                                        binding.btnFinalizar.setEnabled(false);
                                    }else{
                                        binding.botones.setVisibility(View.VISIBLE);
                                    }

                                    for(int i=0;i<horasPeatonales.getDetalle().size();i++){
                                        horarios.add(horasPeatonales.getDetalle().get(i).getHoraMin()+" - "+
                                                horasPeatonales.getDetalle().get(i).getHoraMax());
                                    }

                                    adapterHoras = new AdapterListaHoras(horasPeatonales.getDetalle(), getContext(), null, binding, "");
                                    binding.recyclerHoras.setLayoutManager(new LinearLayoutManager(getContext()));
                                    binding.recyclerHoras.setAdapter(adapterHoras);

                                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
                                    binding.recyclerHoras.setLayoutManager(mLayoutManager);
                                    binding.recyclerHoras.addItemDecoration(new FragmentAutoriza.GridSpacingItemDecoration(3, dpToPx(4), true));
                                    binding.recyclerHoras.setItemAnimator(new DefaultItemAnimator());
                                    binding.peatonalConteo.spinnerHora.setItems(horarios);
                                    listaPeatonal(binding);

                                    horaInicio = horasPeatonales.getDetalle().get(0).getHoraMin();
                                    horaFinal = horasPeatonales.getDetalle().get(0).getHoraMax();

                                    Calendar c = Calendar.getInstance();
                                    SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
                                    final String strDate = sdf.format(c.getTime());

                                    binding.toolbar.nombreTitulo.setText(getString(R.string.flujopeatonal));
                                    binding.conteo.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            binding.recyclerHoras.setVisibility(View.GONE);
                                            binding.recyclerPeatonal.setVisibility(View.GONE);

                                            binding.peaton.setVisibility(View.VISIBLE);
                                            binding.btnFinalizar.setVisibility(View.GONE);
                                            binding.promedio.setVisibility(View.GONE);
                                            binding.conteo.setAlpha(0.5f);
                                            binding.conteo.setEnabled(false);
                                            binding.btnFinalizar.setVisibility(View.GONE);
                                            binding.linearLayout.setVisibility(View.INVISIBLE);

                                            String hoI = horasPeatonales.getDetalle().get(0).getHoraMin();
                                            String hoF = horasPeatonales.getDetalle().get(0).getHoraMax();

                                            hoI = hoI.substring(0, 5);
                                            hoF = hoF.substring(0, 5);

                                            hora [0] =  isHourInInterval(strDate, hoI, hoF);
                                            if(hora[0]!=false){
                                                //TODO CONDICION QUE TRAE EL RANGO DE HORAS, Y EL RANGO DE MINUTOS EN EL CRONOMETRO
                                                binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                                binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                horaInicio = hoI;
                                                horaFinal = hoF;
                                            }else{
                                                binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                hora[0] = false;
                                            }


                                            listaPeatonal(binding);


                                            binding.toolbar.guardar.setVisibility(View.GONE);
                                            binding.peatonalConteo.fechaHoy.setText(Util.getFechita());


                                            final String finalHoI = hoI;
                                            final String finalHoF = hoF;
                                            binding.peatonalConteo.spinnerHora.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                                                @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                                                    if(position==0){
                                                        hora [0] = isHourInInterval(strDate, finalHoI, finalHoF);
                                                        if(hora [0]!=false){
                                                            binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                                            binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                            horaInicio = finalHoI;
                                                            horaFinal = finalHoF;
                                                        }else{
                                                            binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                            binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                            hora[0] = false;
                                                        }
                                                    }

                                                    if(position==1){

                                                        String hoI = horasPeatonales.getDetalle().get(1).getHoraMin();
                                                        String hoF = horasPeatonales.getDetalle().get(1).getHoraMax();

                                                        hora [0] =  isHourInInterval(strDate, hoI, hoF);
                                                        if(hora [0]!=false){
                                                            binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                                            binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                            horaInicio = hoI;
                                                            horaFinal = hoF;
                                                        }else{
                                                            binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                            binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                            hora[0] = false;
                                                        }
                                                    }

                                                    if(position==2){
                                                        String hoI;
                                                        String hoF;
                                                        if(horarios.size()>1){
                                                            hoI = horasPeatonales.getDetalle().get(2).getHoraMin();
                                                            hoF = horasPeatonales.getDetalle().get(2).getHoraMax();
                                                        }else{
                                                            hoI = "";
                                                            hoF = "";
                                                        }

                                                        hora [0] = isHourInInterval(strDate, hoI, hoF);
                                                        if(hora [0]!=false){
                                                            binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                                            binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                            horaInicio = hoI;
                                                            horaFinal = hoF;
                                                        }else{
                                                            binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                            binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                            hora[0] = false;
                                                        }
                                                    }
                                                }
                                            });

                                            binding.peatonalConteo.cronometroPlay.setAlpha(0.35f);
                                            binding.peatonalConteo.cronometroStop.setAlpha(0.35f);



                                            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    FragmentDialogCancelarMd a = new FragmentDialogCancelarMd();
                                                    a.show(getChildFragmentManager(),"child");
                                                }
                                            });

                                            binding.peatonalConteo.btnGuardar.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                    if(conteos[0]>0 && hora[0]){
                                                        ServicioGPS n = new ServicioGPS(getContext());
                                                        CrearPeatonal crearPeatonal = new CrearPeatonal(
                                                                usuarioId,
                                                                mdId,
                                                                Util.getFechita(),
                                                                horaInicio,
                                                                horaFinal,
                                                                String.valueOf(conteos[0]),
                                                                String.valueOf(n.getLatitude()),
                                                                String.valueOf(n.getLongitude()),
                                                                "1.0.1",
                                                                "5555555555",
                                                                "0"
                                                        );

                                                        ProviderCrearPeatonal.getInstance(getContext()).guardarPeatonal
                                                                (crearPeatonal, new ProviderCrearPeatonal.InterfaceCrearDatosPeatonal() {
                                                            @Override
                                                            public void resolve(Codigos codigo) {
                                                                if(codigo.getCodigo()==200){
                                                                    binding.recyclerHoras.setVisibility(View.VISIBLE);

                                                                    Toast.makeText(getContext(), "Flujo peatonal guardado con éxito", Toast.LENGTH_SHORT).show();
                                                                    binding.peaton.setVisibility(View.GONE);
                                                                    binding.recyclerPeatonal.setVisibility(View.VISIBLE);
                                                                    listaPeatonal(binding);
                                                                    binding.peatonalConteo.etTotal.setText("");
                                                                    binding.btnFinalizar.setVisibility(View.VISIBLE);
                                                                    binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                                    binding.promedio.setVisibility(View.VISIBLE);
                                                                    binding.peatonalConteo.chronometer1.stop();
                                                                    binding.peatonalConteo.cronometroStop.setAlpha(1.0f);
                                                                    binding.peatonalConteo.cronometroPlay.setAlpha(0.35f);
                                                                    binding.linearLayout.setVisibility(View.VISIBLE);
                                                                    binding.peatonalConteo.spinnerHora.setItems(horarios);
                                                                    binding.conteo.setAlpha(1f);
                                                                    binding.conteo.setEnabled(true);
                                                                    binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                                    binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                                    conteos[0] = 0;

                                                                    binding.peatonalConteo.cien.setText("0");
                                                                    binding.peatonalConteo.real.setText("0");
                                                                    binding.peatonalConteo.mil.setText("0");
                                                                    binding.peatonalConteo.diez.setText("0");
                                                                    binding.peatonalConteo.presion.setEnabled(true);

                                                                    if(downTimer[0]!=null){
                                                                        downTimer[0].cancel();
                                                                    }
                                                                }else{
                                                                    Toast.makeText(getContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                                                                    binding.peatonalConteo.btnGuardar.setEnabled(true);

                                                                }
                                                            }

                                                            @Override
                                                            public void reject(Exception e) { }
                                                        });
                                                    } else {
                                                        binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                        Toast.makeText(getContext(), R.string.horarios, Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });

                                            binding.peatonalConteo.regresar.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    binding.peaton.setVisibility(View.GONE);
                                                    binding.btnFinalizar.setVisibility(View.VISIBLE);
                                                    binding.recyclerPeatonal.setVisibility(View.VISIBLE);
                                                    binding.btnFinalizar.setVisibility(View.VISIBLE);
                                                    binding.promedio.setVisibility(View.VISIBLE);
                                                    binding.linearLayout.setVisibility(View.VISIBLE);
                                                    binding.conteo.setAlpha(1f);
                                                    binding.conteo.setEnabled(true);
                                                    binding.recyclerHoras.setVisibility(View.VISIBLE);

                                                    binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                    binding.peatonalConteo.btnGuardar.setAlpha(0.4f);

                                                    conteos[0] = 0;

                                                    binding.peatonalConteo.cien.setText("0");
                                                    binding.peatonalConteo.real.setText("0");
                                                    binding.peatonalConteo.mil.setText("0");
                                                    binding.peatonalConteo.diez.setText("0");
                                                    binding.peatonalConteo.presion.setEnabled(true);
                                                    binding.peatonalConteo.contador.setText("00:00");
                                                    if(downTimer[0]!=null){
                                                        downTimer[0].cancel();
                                                    }
                                                }
                                            });
                                        }
                                    });

                                }
                            }

                        }

                        @Override
                        public void reject(Exception e) {

                        }
                    });



            binding.btnFinalizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getContext(), ActivityFinaliza.class);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentDialogCancelarMdTerminar a = new FragmentDialogCancelarMdTerminar();
                    a.show(getChildFragmentManager(),"child");
                }
            });

            binding.peatonalConteo.reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    binding.peatonalConteo.btnGuardar.setEnabled(false);
                    binding.peatonalConteo.btnGuardar.setAlpha(0.4f);

                    conteos[0] = 0;

                    binding.peatonalConteo.cien.setText("0");
                    binding.peatonalConteo.real.setText("0");
                    binding.peatonalConteo.mil.setText("0");
                    binding.peatonalConteo.diez.setText("0");
                    binding.peatonalConteo.contador.setText("00:00");

                    binding.peatonalConteo.presion.setEnabled(true);

                    if(downTimer[0]!=null){
                        downTimer[0].cancel();
                    }

                }
            });
        }
        return view;
    }

    public static boolean isHourInInterval(String target, String start, String end) {
        return ((target.compareTo(start) >= 0)&& (target.compareTo(end) <= 0));
    }

    public void generarDetalles(FragmentAutoriza4Binding binding, final FactoresConstruccion factoresConstruccion){

        Resources resource = getContext().getResources();
        binding.factores.removeAllViews();
        TableRow rowPlomo = new TableRow(getContext());
        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
        binding.factores.addView(rowPlomo);

        checks = new HashMap<Integer, String>();

        for(int i = 0;i<factoresConstruccion.getCatalogo().size(); i ++) {
            if (factoresConstruccion.getCatalogo().get(i).getNivelid() == 2) {
                for (int j = 0; j < factoresConstruccion.getCatalogo().get(i).getDetalles().size(); j++) {

                    TableRow tbrow = new TableRow(getContext());
                    tbrow.setBackgroundColor(resource.getColor(R.color.blanco));

                    final CheckBox check = new CheckBox(getContext());
                    check.setTextColor(resource.getColor(R.color.azul));
                    check.setGravity(Gravity.CENTER);
                    tbrow.addView(check);

                    final boolean checked = check.isChecked();

                    if (checked) {
                        checks.put(j, "1");
                    } else {
                        checks.put(j, "0");
                    }

                    final int finalI = j;
                    int finalJ = j;

                    check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            if (isChecked) {
                                checks.put(finalI, "1");
                            } else {
                                checks.put(finalI, "0");
                            }

                        }
                    });

                    TextView t1v1 = new TextView(getContext());
                    t1v1.setText(factoresConstruccion.getCatalogo().get(i).getDetalles().get(finalJ).getDescripcion());
                    t1v1.setTextColor(resource.getColor(R.color.azul));
                    t1v1.setGravity(Gravity.CENTER_VERTICAL);
                    tbrow.addView(t1v1);
                    binding.factores.addView(tbrow);
                }

            }
        }
    }

    String fechaFrente;
    String fechaEntorno1;
    String fechaEntorno2;
    String fechaPredial;

    /**
     * método para realizar la respuesta de cada intent que se hace en la actividad (ver pdf, tomar foto)
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        long mdid = preferences.getLong("mdId", 0);

        if (requestCode == CAMERA_FRONTAL && resultCode==-1) {
            if(resultCode==0){

            }else{
                bit = (Bitmap) data.getExtras().get("data");
                base64frente = b64(bit);
                fechaFrente = getFechaHora();
                obtenerUrl(random()+"_frente", base64frente, String.valueOf(mdid));
            }
        }else if(requestCode == CAMERA_LATERAL_1 && resultCode==-1){
            if(resultCode==0){

            }else{
                bit = (Bitmap) data.getExtras().get("data");
                base64Lateral1 = b64(bit);
                fechaEntorno1 = getFechaHora();
                obtenerUrl(random()+"_lateral1", base64Lateral1, String.valueOf(mdid));
            }
        }else if(requestCode == CAMERA_PREDIAL && resultCode==-1){
            if(resultCode==0){

            }else{

                fechaPredial = getFechaHora();
                Bitmap bitfromPath = getBitmap(imageFilePath);
                base64Predial = getStringImage(compressImage(bitfromPath, 650));
                obtenerUrl(random()+"_predial", base64Predial, String.valueOf(mdid));

            }
        }else if(requestCode == CAMERA_LATERAL_2 && resultCode==-1){
            if(resultCode==0){

            }else{
                bit = (Bitmap) data.getExtras().get("data");
                base64Lateral2 = b64(bit);
                fechaEntorno2 = getFechaHora();
                obtenerUrl(random()+"_lateral2", base64Lateral2, String.valueOf(mdid));
            }
        }else if(resultCode == 0){

        }

    }

    String urlFrente = "";
    String urlLateral1 = "";
    String urlLateral2 = "";
    String urlPredial = "";

    public void obtenerUrl(String foto, String b64, String mdId){
        loadingProgress(progressDialog, 0);
        ProviderObtenerUrl.getInstance(getContext()).obtenerUrl(mdId, foto, b64 , new ProviderObtenerUrl.ConsultaUrl() {
            @Override
            public void resolve(Codigos codigo) {
                if(codigo!= null && codigo.getResultado().getSecureUrl()!=null){
                    if(codigo.getResultado().getSecureUrl().contains("frente")){
                        bindingSuperficie.frontal.setEnabled(false);
                        urlFrente = codigo.getResultado().getSecureUrl();
                        Picasso.get().load(urlFrente).into(bindingSuperficie.imagen);
                        bindingSuperficie.frontal.setEnabled(true);
                        hourlyTaskSuperficie.run();
                        hourlyTaskSuperficie.scheduledExecutionTime();
                        loadingProgress(progressDialog, 1);

                    }else if(codigo.getResultado().getSecureUrl().contains("lateral1")){
                        bindingSuperficie.lateral1.setEnabled(false);
                        urlLateral1 = codigo.getResultado().getSecureUrl();
                        Picasso.get().load(urlLateral1).into(bindingSuperficie.imagen);
                        bindingSuperficie.lateral1.setEnabled(true);
                        hourlyTaskSuperficie.run();
                        hourlyTaskSuperficie.scheduledExecutionTime();
                        loadingProgress(progressDialog, 1);

                    }else if(codigo.getResultado().getSecureUrl().contains("predial")){
                        bindingSuperficie.predial.setEnabled(false);
                        urlPredial = codigo.getResultado().getSecureUrl();
                        Picasso.get().load(urlPredial).into(bindingSuperficie.imagen);
                        bindingSuperficie.predial.setEnabled(true);
                        hourlyTaskSuperficie.run();
                        hourlyTaskSuperficie.scheduledExecutionTime();
                        loadingProgress(progressDialog, 1);

                    } else{
                        bindingSuperficie.lateral2.setEnabled(false);
                        urlLateral2 = codigo.getResultado().getSecureUrl();
                        Picasso.get().load(urlLateral2).into(bindingSuperficie.imagen);
                        bindingSuperficie.lateral2.setEnabled(true);
                        hourlyTaskSuperficie.run();
                        hourlyTaskSuperficie.scheduledExecutionTime();
                        loadingProgress(progressDialog, 1);

                    }
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    HashMap<Integer, String> checks;
    String base64frente;
    String base64Lateral1;
    String base64Lateral2;
    String base64Predial;

    private String b64(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_usuario, menu);
        menu.add(0,1,1, Util.menuIcon(getResources().getDrawable(R.drawable.ic_vpn_key_black_24dp),
                getResources().getString(R.string.cambiarContra)));
        menu.add(0, 2, 2, Util.menuIcon(getResources().getDrawable(R.drawable.ic_exit_to_app_black_24dp),
                getResources().getString(R.string.salir)));
    }

    /**
     * Método que tiene la acción del menu posterior derecha
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case 1:
                return true;
            case 2:
                Util.cerrarSesion(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    String mensaje = null;

    public void slideUX(final FragmentAutoriza3Binding binding){
        slideGenerador = new SlideUpBuilder(binding.content2.slideView)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {
                        binding.dim.setAlpha(1 - (percent / 100));
                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        if (visibility == 0){

                        }
                    }
                }).withStartGravity(Gravity.BOTTOM).withLoggingEnabled(false).withGesturesEnabled(false)
                .withStartState(SlideUp.State.HIDDEN).withSlideFromOtherView(binding.rootView)
                .withTouchableAreaPx(100)
                .withTouchableAreaDp(100)
                .build();

        slideCompetencia = new SlideUpBuilder(binding.contenido.slideView)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {
                        binding.dim2.setAlpha(1 - (percent / 100));
                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        if (visibility == View.GONE){
                            if (visibility == 0){

                            }
                        }
                    }
                }).withStartGravity(Gravity.BOTTOM).withLoggingEnabled(false).withGesturesEnabled(false)
                .withStartState(SlideUp.State.HIDDEN).withSlideFromOtherView(binding.rootView2)
                .withTouchableAreaPx(100)
                .withTouchableAreaDp(100)
                .build();

    }

    ArrayList<Peatonal> peatonales;
    public void listaPeatonal(final FragmentAutoriza6Binding binding){
        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        long mdid = preferences.getLong("mdId", 0);
        String usuario = preferences.getString("usuario", "");
        ProviderDatosPeatonal.getInstance(getContext()).obtenerDatosPeatonal(String.valueOf(mdid), usuario,new ProviderDatosPeatonal.ConsultaPeatonal() {
            @Override
            public void resolve(Peatonales peatonal) {
                peatonales = new ArrayList<>();
                if(peatonal!=null && peatonal.getCodigo()==200){
                    if(peatonal.getConteos().size()>0){ // TODO
                        for(int i=0;i<peatonal.getConteos().size();i++){
                            if(peatonal.getConteos().get(i).getDetalle().size()>=3){
                                binding.btnFinalizar.setAlpha(1.0f);
                                binding.btnFinalizar.setEnabled(true);
                            }else{
                                binding.btnFinalizar.setAlpha(0.35f);
                                binding.btnFinalizar.setEnabled(false);
                            }

                            for(int j=0;j<peatonal.getConteos().get(i).getDetalle().size();j++){
                                peatonales.add(new Peatonal(j,
                                        peatonal.getConteos().get(i).getDetalle().get(j).getFecha(),
                                        Integer.valueOf(peatonal.getConteos().get(i).getDetalle().get(j).getValor()),
                                        0.0,0.0, peatonal.getConteos().get(i).getDetalle().get(j).getNombreGenerador()));
                            }
                        }
                        binding.promedio.setText("Promedio peatonal "+ peatonal.getConteos().get(0).getPromedioPeatonal()+"");
                        binding.recyclerPeatonal.setHasFixedSize(true);
                        AdapterAutorizaPeatonal adapter = new AdapterAutorizaPeatonal(getContext(), ALPHABETICAL_COMPARATOR, n);
                        binding.recyclerPeatonal.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.recyclerPeatonal.setAdapter(adapter);
                        adapter.edit().replaceAll(peatonales).commit();
                        adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                    }else{
                        binding.btnFinalizar.setAlpha(0.35f);
                        binding.btnFinalizar.setEnabled(false);
                    }
                }else{
                    binding.btnFinalizar.setAlpha(0.35f);
                    binding.btnFinalizar.setEnabled(false);
                }
            }
            @Override
            public void reject(Exception e) { }
        });
    }

    private static final Comparator<Peatonal> ALPHABETICAL_COMPARATOR = new Comparator<Peatonal>() {
        @Override
        public int compare(Peatonal a, Peatonal b) {
            return a.getNumConteo().compareTo(b.getNumConteo());
        }
    };

    @Override
    public void onAutorizaSelect(Peatonal model) {

    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private BitmapDescriptor getBitmapDescriptor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            VectorDrawable vectorDrawable = (VectorDrawable) getContext().getDrawable(id);

            int h = vectorDrawable.getIntrinsicHeight();
            int w = vectorDrawable.getIntrinsicWidth();

            vectorDrawable.setBounds(0, 0, w, h);

            Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bm);
            vectorDrawable.draw(canvas);

            return BitmapDescriptorFactory.fromBitmap(bm);

        } else {
            return BitmapDescriptorFactory.fromResource(id);
        }
    }

    /******** gps **************/
    ServicioGPS gpsUbicas;

    Double latitude, longitude, latitudeLast = 0.0, longitudeLast = 0.0;

    public Ubicacion gps() {
        Ubicacion ubicacion;
        latitudeLast = latitude;
        longitudeLast = longitude;
        gpsUbicas = new ServicioGPS(getContext());
        if (gpsUbicas.canGetLocation()) {
            latitude = gpsUbicas.getLatitude();
            longitude = gpsUbicas.getLongitude();
            ubicacion = new Ubicacion(latitude, longitude, true);
        } else {
            if(latitudeLast!=null){
                ubicacion = new Ubicacion(latitudeLast, longitudeLast, false);
            }else{
                ubicacion = new Ubicacion(0.0, 0.0, false);
            }
        }
        return ubicacion;
    }

    public void setDireccion(FragmentAutorizaBinding binding, Double lat, Double lng){
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            if(mCenterLatLong!=null){
                addresses = geocoder.getFromLocation(lat, lng, 1);
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                municipio = addresses.get(0).getLocality();
                String postalCode = addresses.get(0).getPostalCode();

                binding.direccionsitio.setText(address);
                binding.ciudadsitio.setText(city);
                binding.estadositio.setText(state);
                binding.municipiositio.setText(municipio);
                binding.pais.setText(country);
                binding.codigopostalsitio.setText(postalCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    FragmentAutoriza4Binding bindingConstruccion;

    Marker market;

    ArrayList<Marker> markers = new ArrayList<>();
    ArrayList<CrearZonificacion.Zonificacion> competencia;
    ArrayList<CrearZonificacion.Zonificacion> generadores;
    ArrayList<CrearZonificacion.Detalle> detallesC = new ArrayList<>();;
    ArrayList<CrearZonificacion.Detalle> detallesG = new ArrayList<>();;

    CrearZonificacion.Detalle detalleC;
    CrearZonificacion.Detalle detalleG;

    CrearZonificacion.Zonificacion zonificacionC = new CrearZonificacion.Zonificacion();
    CrearZonificacion.Zonificacion zonificacionG = new CrearZonificacion.Zonificacion();

    public void colocarMarcador(LatLng latLng, GoogleMap mMap, int valor, String usuario, LatLng mds, String mdIdZ){

        if(valor==1){
            icon = getBitmapDescriptor(R.drawable.bbb);
        }else if(valor==2){
            icon = getBitmapDescriptor(R.drawable.oxxo);
        }else if(valor==3){
            icon = getBitmapDescriptor(R.drawable.bodegaa);
        }else if(valor==4){
            icon = getBitmapDescriptor(R.drawable.abarrotes);
        }else if(valor==5){
            icon = getBitmapDescriptor(R.drawable.g_iglesia);
        }else if(valor==6){
            icon = getBitmapDescriptor(R.drawable.g_mercado);
        }else if(valor==7){
            icon = getBitmapDescriptor(R.drawable.escuela);
        }else if(valor==8){
            icon = getBitmapDescriptor(R.drawable.g_busstop);
        }else if(valor==9){
            icon = getBitmapDescriptor(R.drawable.otros);
        }else if(valor==10){
            icon = getBitmapDescriptor(R.drawable.netos);
        }else if(valor==11){
            icon = getBitmapDescriptor(R.drawable.g_recauderia);
        }else if(valor==12){
            icon = getBitmapDescriptor(R.drawable.g_comida);
        }else if(valor==13){
            icon = getBitmapDescriptor(R.drawable.g_mercado);
        }else if(valor==14){
            icon = getBitmapDescriptor(R.drawable.g_tianguis);
        }else if(valor==15){
            icon = getBitmapDescriptor(R.drawable.g_tortilleria);
        }else if(valor==16){
            icon = getBitmapDescriptor(R.drawable.g_carniceria);
        }else if(valor==17){
            icon = getBitmapDescriptor(R.drawable.metro);
        }

        market = mMap.addMarker(new
                MarkerOptions().
                position(latLng).
                title("").snippet("")
                .icon(icon));
        market.setVisible(true);

        markers.add(market);

        if(valor == 1 || valor == 2 || valor == 3
                || valor == 4 || valor == 10){

            detalleC = new CrearZonificacion.Detalle(
                    String.valueOf(valor),
                    String.valueOf(market.getPosition().latitude),
                    String.valueOf(market.getPosition().longitude),
                    String.valueOf(nivel)
            );

            detallesC.add(detalleC);


            competencia = new ArrayList<>();
            zonificacionC.setDetalles(detallesC);
            competencia.add(zonificacionC);

        }else if(valor == 5 || valor == 6 || valor == 7 ||
                valor == 8 || valor == 9 ||
                valor == 11 || valor == 12 || valor == 13 || valor == 14
                || valor == 15 || valor == 16 || valor == 17){

            detalleG = new CrearZonificacion.Detalle(
                    String.valueOf(valor),
                    String.valueOf(market.getPosition().latitude),
                    String.valueOf(market.getPosition().longitude),
                    String.valueOf(nivel)

            );

            detallesG.add(detalleG);


            generadores = new ArrayList<>();

            zonificacionG.setDetalles(detallesG);
            generadores.add(zonificacionG);


        }

        if(competencia==null){
           // detallesC = new ArrayList<>();;
            //detallesC.add(detalleC);
            //zonificacionC.setDetalles(detallesC);
            competencia = new ArrayList<>();
            //competencia.add(zonificacionC);
        }

        if(generadores==null){
           // detallesG = new ArrayList<>();;
           // detallesG.add(detalleG);
           // zonificacionG.setDetalles(detallesG);
            generadores = new ArrayList<>();
            //generadores.add(zonificacionG);
        }


        zonificacion = new CrearZonificacion(
                usuario,
                mdIdZ,
                competencia,
                generadores,
                String.valueOf(mdLat),
                String.valueOf(mdLot),
                "5555555555",
                VERSION_APP
        );

        zonificacionJson = getJsonString(zonificacion);

    }

    public String distancia(LatLng latLng, LatLng mdId, String tipo){
        Location loc1 = new Location("");
        loc1.setLatitude(latLng.latitude);
        loc1.setLongitude(latLng.longitude);

        Location loc2 = new Location("");
        loc2.setLatitude(mdId.latitude);
        loc2.setLongitude(mdId.longitude);

        float distanciaMetros = loc1.distanceTo(loc2);

        if(tipo.equals("competencia")){
            if(distanciaMetros <= 500){
                return "8";
            }else if(distanciaMetros>=500 && distanciaMetros<=1500){
                return "8";
            }else{
                return "8";
            }
        }else{
            if(distanciaMetros <= 500){
                return "4";
            }else if(distanciaMetros>=500 && distanciaMetros<=1500){
                return "5";
            }else{
                return "6";
            }
        }
    }

    public static boolean distanciaSuperficie(LatLng latLng, LatLng mdId){
        Location loc1 = new Location("");
        loc1.setLatitude(latLng.latitude);
        loc1.setLongitude(latLng.longitude);

        Location loc2 = new Location("");
        loc2.setLatitude(mdId.latitude);
        loc2.setLongitude(mdId.longitude);

        float distanciaMetros = loc1.distanceTo(loc2);
        if(distanciaMetros <= 500){
                return true;
        }
        return true;
    }

    String zonificacionJson = "";

    private String getJsonString(CrearZonificacion zonificacion) {
        Gson gson = new Gson();
        String json = gson.toJson(zonificacion);
        return json;
    }

    String datosConstruccionJson = "";
    private String getJsonString(DatosConstruccion zonificacion) {
        Gson gson = new Gson();
        String json = gson.toJson(zonificacion);
        return json;
    }

    String horaInicio, horaFinal;
    String mdId;
    int nivelId;
    DatosConstruccion datosConstruccion;
    Bitmap bit;

    List<DatosConstruccion.Nivele> niveles;
    List<DatosConstruccion.Detalle> detallesContruccion;
    List<DatosConstruccion.Detalle> detallesCondicion;
    DatosConstruccion.Detalle detalleConstruccion;

    public void generarConstruccion(final FragmentAutoriza4Binding binding, final FactoresConstruccion factoresConstruccion) {
        final RadioButton[] rb = new RadioButton[2];
        RadioGroup rg = new RadioGroup(getContext());
        rg.setOrientation(RadioGroup.VERTICAL);
        for(int i=0; i<factoresConstruccion.getCatalogo().size(); i++){
            if(factoresConstruccion.getCatalogo().get(i).getNivelid()==1
                    || factoresConstruccion.getCatalogo().get(i).getNivelid()==2){
                rb[i]  = new RadioButton(getContext());
                rb[i].setText(" " + factoresConstruccion.getCatalogo().get(i).getDescripcion());
                rb[i].setId(factoresConstruccion.getCatalogo().get(i).getNivelid());
                rg.addView(rb[i]);
            }
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int radioButtonID = group.getCheckedRadioButtonId();
                View radioButton = group.findViewById(radioButtonID);
                nivelId = group.indexOfChild(radioButton);
                if(checkedId==1){
                    nivelId = 1;
                    binding.linearLayout.setVisibility(View.GONE);
                }else if(checkedId==2){
                    nivelId = 2;
                    binding.linearLayout.setVisibility(View.VISIBLE);

                }
            }
        });

        binding.local.addView(rg);
    }

    int nivelIdCondicion;

    public void generarCondiciones(final FragmentAutoriza4Binding binding, final FactoresConstruccion factoresConstruccion) {
        final RadioButton[] rb = new RadioButton[factoresConstruccion.getCatalogo().size()];
        RadioGroup rg = new RadioGroup(getContext());
        rg.setOrientation(RadioGroup.VERTICAL);
        for(int i=0; i<factoresConstruccion.getCatalogo().size(); i++){

            if(factoresConstruccion.getCatalogo().get(i).getNivelid()==3
                    || factoresConstruccion.getCatalogo().get(i).getNivelid()==4
                    || factoresConstruccion.getCatalogo().get(i).getNivelid()==5){

                rb[i]  = new RadioButton(getContext());
                rb[i].setText(" " + factoresConstruccion.getCatalogo().get(i).getDescripcion());
                rb[i].setId(factoresConstruccion.getCatalogo().get(i).getNivelid());
                rg.addView(rb[i]);

            }

        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int radioBttonID = group.getCheckedRadioButtonId();
                View radioButton = group.findViewById(radioBttonID);
                int nivelId = group.indexOfChild(radioButton);
                if(checkedId==3){
                    nivelIdCondicion = 3;
                }else if(checkedId == 4){
                    nivelIdCondicion = 4;
                }else if(checkedId == 5){
                    nivelIdCondicion = 5;
                }
            }
        });

        binding.condiciones.addView(rg);

    }

    public String getFechaHora(){
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        String dateforrow = dateFormat.format(cal1.getTime());
        return dateforrow;
    }


    public void datosConstruccion(String md, String usuarioId){
        if(nivelId==0 || nivelIdCondicion==0){

        }else{
            niveles = new ArrayList<>();
            if(nivelIdCondicion==5 || nivelIdCondicion == 4 || nivelIdCondicion == 3){

                detallesCondicion = new ArrayList<>();

                DatosConstruccion.Nivele detalleCondicion = new DatosConstruccion.Nivele(
                        nivelIdCondicion, detallesCondicion);

                niveles.add(detalleCondicion);

            }

            if(nivelId==1 || nivelId==2){

                detallesContruccion = new ArrayList<>();

                if(nivelId==1){


                }else if(nivelId==2){
                    for ( Map.Entry<Integer, String> entry : checks.entrySet()) {
                        Integer valor = entry.getKey();
                        String check = entry.getValue();

                        if(valor==0 && check =="1"){
                            detalleConstruccion = new DatosConstruccion.Detalle(valor+1);
                            detallesContruccion.add(detalleConstruccion);
                        }

                        if(valor==1 && check =="1"){
                            detalleConstruccion = new DatosConstruccion.Detalle(valor+1);
                            detallesContruccion.add(detalleConstruccion);
                        }

                        if(valor==2 && check =="1"){
                            detalleConstruccion = new DatosConstruccion.Detalle(valor+1);
                            detallesContruccion.add(detalleConstruccion);
                        }

                        if(valor==3 && check =="1"){
                            detalleConstruccion = new DatosConstruccion.Detalle(valor+1);
                            detallesContruccion.add(detalleConstruccion);
                        }
                    }
                }

                DatosConstruccion.Nivele detalleConstruccion = new DatosConstruccion.Nivele(
                        nivelId, detallesContruccion);

                niveles.add(detalleConstruccion);

            }

            datosConstruccion = new DatosConstruccion(
                    md,
                    usuarioId,
                    "5",
                    "5540555599",
                    VERSION_APP,
                    niveles
            );

            datosConstruccionJson = getJsonString(datosConstruccion);

        }
    }

    public void generarConstruccion(final FragmentAutoriza4Binding binding,
                                    final FactoresConstruccion factoresConstruccion,
                                    DatosConstruccions listaSubfactores) {
        if(factoresConstruccion!=null){
            final RadioButton[] rb = new RadioButton[2];
            RadioGroup rg = new RadioGroup(getContext());
            rg.setOrientation(RadioGroup.VERTICAL);
            for(int i=0; i<factoresConstruccion.getCatalogo().size(); i++){
                if(factoresConstruccion.getCatalogo().get(i).getNivelid()==1
                        || factoresConstruccion.getCatalogo().get(i).getNivelid()==2){

                    rb[i]  = new RadioButton(getContext());
                    rb[i].setText(" " + factoresConstruccion.getCatalogo().get(i).getDescripcion());
                    rb[i].setId(factoresConstruccion.getCatalogo().get(i).getNivelid());

                    rg.addView(rb[i]);
                }
            }

            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    int radioButtonID = group.getCheckedRadioButtonId();
                    View radioButton = group.findViewById(radioButtonID);
                    nivelId = group.indexOfChild(radioButton);

                    if(checkedId==1){
                        if(checks!=null){
                            checks.clear();
                        }
                        nivelId = 1;
                        binding.linearLayout.setVisibility(View.GONE);
                    }else if(checkedId==2){
                        nivelId = 2;
                        binding.linearLayout.setVisibility(View.VISIBLE);

                    }
                }
            });

            binding.local.addView(rg);

            if(listaSubfactores.getConstruccion()!=null){
                if(listaSubfactores.getConstruccion().get(0).getNivelid()==1){
                    rb[0].setChecked(true);
                    nivelId = 1;
                }else if(listaSubfactores.getConstruccion().get(0).getNivelid()==2){
                    rb[1].setChecked(true);
                    nivelId = 1;
                }else{
                    binding.linearLayout.setVisibility(View.VISIBLE);
                    nivelId = 2;
                    rb[1].setChecked(true);
                }
            }
        }

    }

    public void generarCondiciones(final FragmentAutoriza4Binding binding,
                                   final FactoresConstruccion factoresConstruccion,
                                   DatosConstruccions datosConstruccion) {

        final RadioButton[] rb = new RadioButton[factoresConstruccion.getCatalogo().size()];
        RadioGroup rg = new RadioGroup(getContext());
        rg.setOrientation(RadioGroup.VERTICAL);
        for(int i=0; i<factoresConstruccion.getCatalogo().size(); i++){
            if(factoresConstruccion.getCatalogo().get(i).getNivelid()==3
                    || factoresConstruccion.getCatalogo().get(i).getNivelid()==4
                    || factoresConstruccion.getCatalogo().get(i).getNivelid()==5){

                rb[i]  = new RadioButton(getContext());
                rb[i].setText(" " + factoresConstruccion.getCatalogo().get(i).getDescripcion());
                int niv = factoresConstruccion.getCatalogo().get(i).getNivelid();
                rb[i].setId(niv);
                rg.addView(rb[i]);

                if(rb[i].getId()==datosConstruccion.getConstruccion().get(1).getNivelid()){
                    nivelIdCondicion = 3;
                    rb[i].setChecked(true);
                }

                if(rb[i].getId()==datosConstruccion.getConstruccion().get(1).getNivelid()){
                    nivelIdCondicion = 4;
                    rb[i].setChecked(true);
                }

                if(rb[i].getId()==datosConstruccion.getConstruccion().get(1).getNivelid()){
                    nivelIdCondicion = 5;
                    rb[i].setChecked(true);
                }
            }
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioBttonID = group.getCheckedRadioButtonId();
                View radioButton = group.findViewById(radioBttonID);
                int nivelId = group.indexOfChild(radioButton);
                if(checkedId==3){
                    nivelIdCondicion = 3;
                }else if(checkedId == 4){
                    nivelIdCondicion = 4;
                }else if(checkedId == 5){
                    nivelIdCondicion = 5;
                }
            }
        });
        binding.condiciones.addView(rg);



    }

    public void generarDetalles(FragmentAutoriza4Binding binding,
                                final FactoresConstruccion factoresConstruccion,
                                DatosConstruccions datosConstruccion){

        Resources resource = getContext().getResources();
        binding.factores.removeAllViews();
        TableRow rowPlomo = new TableRow(getContext());
        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
        binding.factores.addView(rowPlomo);

        checks = new HashMap<Integer, String>();


        for(int i = 0;i<factoresConstruccion.getCatalogo().size(); i ++) {
            if (factoresConstruccion.getCatalogo().get(i).getNivelid() == 2) {
                for (int j = 0; j < factoresConstruccion.getCatalogo().get(i).getDetalles().size(); j++) {

                    TableRow tbrow = new TableRow(getContext());
                    tbrow.setBackgroundColor(resource.getColor(R.color.blanco));

                    final CheckBox check = new CheckBox(getContext());
                    check.setTextColor(resource.getColor(R.color.azul));
                    int niv = factoresConstruccion.getCatalogo().get(1).getDetalles().get(j).getDetalleid();
                    check.setId(niv);
                    check.setGravity(Gravity.CENTER);

                    for(int n = 0;n<datosConstruccion.getConstruccion().size(); n ++) {
                        for (int m = 0; m < datosConstruccion.getConstruccion().get(n).getDetalles().size(); m++) {
                            int nivelId = datosConstruccion.getConstruccion().get(0).getDetalles().get(m).getDetalleid();
                            int factor = check.getId();
                            if(factor==nivelId){
                                check.setChecked(true);
                            }

                        }
                    }
                    tbrow.addView(check);
                    final boolean checked = check.isChecked();
                    if (checked) {
                        checks.put(j, "1");
                    } else {
                        checks.put(j, "0");
                    }

                    final int finalI = j;
                    int finalJ = j;

                    check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            if (isChecked) {
                                checks.put(finalI, "1");
                            } else {
                                checks.put(finalI, "0");
                            }

                        }
                    });

                    TextView t1v1 = new TextView(getContext());
                    t1v1.setText(factoresConstruccion.getCatalogo().get(i).getDetalles().get(finalJ).getDescripcion());
                    t1v1.setTextColor(resource.getColor(R.color.azul));
                    t1v1.setGravity(Gravity.CENTER_VERTICAL);
                    tbrow.addView(t1v1);
                    binding.factores.addView(tbrow);
                }

            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(hourlyTask != null){
            hourlyTask.cancel();
            getContext().getSharedPreferences("datosConstruccion", 0).edit().clear().apply();
            getContext().getSharedPreferences("datosGeneralidades", 0).edit().clear().apply();
            getContext().getSharedPreferences("datosPropietario", 0).edit().clear().apply();
            getContext().getSharedPreferences("datosSitio", 0).edit().clear().apply();
        }

        if(hourlyTaskSuperficie != null){
            hourlyTaskSuperficie.cancel();
        }

    }

    public static void loadingProgress(ProgressDialog progressDialog, int i){
        if(i == 0){
            progressDialog.setTitle("Enviando...");
            progressDialog.setMessage("Espera mientras se carga tu informacion...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }else{
            progressDialog.dismiss();
        }
    }

    String imageFilePath;
    private File createImageFile(Context c) throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = c.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

}
