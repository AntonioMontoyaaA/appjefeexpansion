package expansion.neto.com.mx.jefeapp.fragment.fragmentDetalle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.VectorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
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
import java.io.IOException;
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

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.ActivityFinalizaBinding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutoriza3Binding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutoriza4Binding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutoriza6Binding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentDetalleGeneralidadesBinding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentDetallePropietarioBinding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentDetalleSitioBinding;
import expansion.neto.com.mx.jefeapp.databinding.FragmentDetalleSuperficieBinding;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentDialogAceptar;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentDialogCancelar;
import expansion.neto.com.mx.jefeapp.fragment.fragmentRechazadas.FragmentDialogCancelarMdRechazadas;
import expansion.neto.com.mx.jefeapp.modelView.Ubicacion;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.DatosConstruccions;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.DatosPredial;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.DatosSitio;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.GeneralidadesSitio;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Peatonales;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Propietario;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Superficie;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Zonificacion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Amortizacion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CompetenciasGeneradoresV2;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearZonificacion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.DatosConstruccion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.DatosPuntuacion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.FactoresConstruccion;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.HorasPeatonales;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosAmortizacion;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosConstruccion;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosGeneralidadesSitio;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosPeatonal;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosPredial;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosPropietario;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosSitio;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosSuperficie;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosZonificacion;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderConsultaFinaliza;
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
import expansion.neto.com.mx.jefeapp.ui.agenda.ActivityNotificaciones;
import expansion.neto.com.mx.jefeapp.ui.porterminar.ActivityFinalizaTerminar;
import expansion.neto.com.mx.jefeapp.utils.PhoneNumberTextWatcher;
import expansion.neto.com.mx.jefeapp.utils.ServicioGPS;
import expansion.neto.com.mx.jefeapp.utils.Util;

import static android.media.MediaRecorder.VideoSource.CAMERA;
import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.VERSION_APP;
import static expansion.neto.com.mx.jefeapp.utils.Util.random;


public class FragmentDetalleRechazadas extends Fragment implements
         AutorizaHolderPeatonal.Listener, com.google.android.gms.location.LocationListener {

    private View view;
    private static final String ARG_POSITION = "position";
    private int position;
    AdapterListaCompetencia adapter;
    AdapterListaGeneradores adapter2;
    AutorizaHolderPeatonal.Listener n;
    List<CompetenciasGeneradoresV2.Competencia> listCompetencia = new ArrayList<>();
    List<CompetenciasGeneradoresV2.OtrosGeneradore> listGeneradores = new ArrayList<>();
    AdapterListaHoras adapterHoras;
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

    AdapterListaGeneradoresNegocios adapterNegocios;
    AdapterListaGeneradoresTransporte adapterTransporte;
    AdapterListaTiendaNeto adapterTiendaNeto;
    AdapterListaGeneradoresNegociosComida adapterListaGeneradoresNegociosComida;
    AdapterListaGeneradoresMercadoPublico adapterListaGeneradoresMercadoPublico;
    AdapterListaGeneradoresTianguis adapterListaGeneradoresTianguis;


    List<CompetenciasGeneradoresV2.TiendaNeto> listCompetenciaTiendaNeto = new ArrayList<>();
    List<CompetenciasGeneradoresV2.Negocio> listGeneradoresNegocios = new ArrayList<>();
    List<CompetenciasGeneradoresV2.TransportePublico> listGeneradoresTransporte = new ArrayList<>();

    List<CompetenciasGeneradoresV2.NegociosDeComida> negociosDeComidaArrayList = new ArrayList<>();
    List<CompetenciasGeneradoresV2.MercadoPublico> mercadoPublicoArrayList = new ArrayList<>();
    List<CompetenciasGeneradoresV2.Tiangui> tianguiArrayList = new ArrayList<>();
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


    private LatLng mCenterLatLong;

    private OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(final GoogleMap googleMap) {
            mMap = googleMap;
            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            mdLat = preferences.getFloat("latMd", 0);
            mdLot = preferences.getFloat("lotMd", 0);

            mCenterLatLong = new LatLng(mdLat, mdLot);
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

            googleMap.getUiSettings().setScrollGesturesEnabled(false);
            googleMap.getUiSettings().setZoomGesturesEnabled(false);


            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {
                    mCenterLatLong = cameraPosition.target;
                    googleMap.clear();
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
    private GoogleMap mMapZona;

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

    ArrayList<Zonificacion.Detalle> detallesGen;
    ArrayList<Zonificacion.Detalle> detallesCom;

    private OnMapReadyCallback onMapReadyCallbackZonificacion = new OnMapReadyCallback() {
        @Override
        public void onMapReady(final GoogleMap googleMap) {

            mMapZona = googleMap;

            SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            mdLat = preferences.getFloat("latMd", 0);
            mdLot = preferences.getFloat("lotMd", 0);
            final Float mdLat = preferences.getFloat("latMd", 0);
            final Float mdLot = preferences.getFloat("lotMd", 0);

            LatLng mds = new LatLng(mdLat, mdLot);
            icon = getBitmapDescriptor(R.drawable.home);
            googleMap.addMarker(new MarkerOptions().position(mds)
                    .title("")
                    .icon(icon)
            );

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

            if(mdLat!=null || mdLot!=null){
                mds = new LatLng(mdLat, mdLot);
                icon = getBitmapDescriptor(R.drawable.home);
                googleMap.addMarker(new MarkerOptions().position(mds)
                        .title("")
                        .icon(icon)
                );

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(mds));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mds, 15));
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

                cameraPosition = new CameraPosition.Builder()
                        .target(mds)
                        .zoom(14)
                        .bearing(0)
                        .tilt(0)
                        .build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


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


                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

                    @Override
                    public void onMapLongClick(LatLng arg0) {
                        // TODO Auto-generated method stub
                    }
                });
            }

        }
    };


    private static boolean noti = false;
    public static FragmentDetalleRechazadas newInstance(int position, boolean notificacion) {
        FragmentDetalleRechazadas f = new FragmentDetalleRechazadas();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        noti = notificacion;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    private SlideUp slideCompetencia;
    private SlideUp slideGenerador;
    FragmentDetalleSitioBinding binding;
    FragmentAutoriza3Binding bindingZonificacion;
    FragmentDetalleSuperficieBinding bindingSuperficie;
    FragmentDetallePropietarioBinding bindingPropietario;
    Float lat, lot;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (position == 0) {
            mensaje = "fragment 1";
            binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detalle_sitio,container,false);
            view = binding.getRoot();
            binding.toolbar.nombreTitulo.setText(getString(R.string.datossitio));
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(onMapReadyCallback);

            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String usuario = preferences.getString("usuario", "");
            final String mdIdterminar = preferences.getString("mdIdterminar", "");

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdRechazadas a = new FragmentDialogCancelarMdRechazadas();
                        a.show(getChildFragmentManager(),"child");
                    }

                }
            });

            binding.nombresitio.setEnabled(false);
            binding.direccionsitio.setEnabled(false);
            binding.ciudadsitio.setEnabled(false);
            binding.municipiositio.setEnabled(false);
            binding.estadositio.setEnabled(false);
            binding.pais.setEnabled(false);

            binding.toolbar.guardar.setVisibility(View.INVISIBLE);

            binding.nombresitio.setBackgroundResource(android.R.color.transparent);
            binding.direccionsitio.setBackgroundResource(android.R.color.transparent);
            binding.ciudadsitio.setBackgroundResource(android.R.color.transparent);
            binding.municipiositio.setBackgroundResource(android.R.color.transparent);
            binding.estadositio.setBackgroundResource(android.R.color.transparent);
            binding.pais.setBackgroundResource(android.R.color.transparent);
            final SharedPreferences.Editor editorPre = preferences.edit();

            ProviderDatosSitio.getInstance(getContext()).obtenerDatosSitio(mdIdterminar, usuario, new ProviderDatosSitio.ConsultaDatosSitio() {
                @Override
                public void resolve(DatosSitio datosSitio) {
                    if(datosSitio.getDatossitio()!= null && datosSitio.getCodigo()==200){
                        if(datosSitio.getDatossitio().get(0).getDireccion()!=null &&
                                datosSitio.getDatossitio().get(0).getDetallesValidacion()!=null &&
                                datosSitio.getDatossitio().get(0).getLatitud()!=null &&
                                datosSitio.getDatossitio().get(0).getLongitud()!=null &&
                                datosSitio.getDatossitio().get(0).getNombreSitio()!=null){

                            binding.escogeSitio.setEnabled(false);
                            if(datosSitio.getDatossitio().get(0).getTipoUbicacionMD()!=null){
                                if(datosSitio.getDatossitio().get(0).getTipoUbicacionMD().equals("RURAL")){
                                    binding.escogeSitio.setChecked(true);
                                    binding.rural.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.azul));
                                    editorPre.putString("tipoSitio" ,"2");
                                    editorPre.apply();
                                }else{
                                    binding.escogeSitio.setChecked(false);
                                    binding.ciudad.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.azul));
                                    editorPre.putString("tipoSitio" ,"1");
                                    editorPre.apply();
                                }
                            }


                            binding.escogeSitio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if(isChecked){
                                        editorPre.putString("tipoSitio" ,"2");
                                        editorPre.apply();
                                        binding.ciudad.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.grisedt));
                                        binding.rural.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.azul));

                                    }else{
                                        editorPre.putString("tipoSitio" ,"1");
                                        editorPre.apply();
                                        binding.ciudad.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.azul));
                                        binding.rural.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.grisedt));


                                    }
                                }
                            });

                            binding.nombresitio.setEnabled(false);
                            binding.nombresitio.setText(datosSitio.getDatossitio().get(0).getNombreSitio());

                            binding.direccionsitio.setText(datosSitio.getDatossitio().get(0).getDireccion()+"");
                            binding.estadositio.setText(datosSitio.getDatossitio().get(0).getEstado()+"");
                            lat = Float.valueOf(datosSitio.getDatossitio().get(0).getLatitud());
                            lot = Float.valueOf(datosSitio.getDatossitio().get(0).getLongitud());

                            SharedPreferences.Editor editorDatos = preferences.edit();
                            editorDatos.putFloat("latMd", lat);
                            editorDatos.putFloat("lotMd", lot);
                            editorDatos.putString("nombreSitio", datosSitio.getDatossitio().get(0).getNombreSitio().toString());
                            editorDatos.apply();

                            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                                    .findFragmentById(R.id.map);
                            mapFragment.getMapAsync(onMapReadyCallback);
                        }
                    }
                }

                @Override
                public void reject(Exception e) { }
            });

        } else if (position == 1) {

            bindingPropietario = DataBindingUtil.inflate(inflater, R.layout.fragment_detalle_propietario,container,false);
            view = bindingPropietario.getRoot();

           // bindingPropietario.toolbar.back.setVisibility(View.INVISIBLE);

            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String usuario = preferences.getString("usuario", "");
            String md = preferences.getString("mdIdterminar", "");
            bindingPropietario.telefono.addTextChangedListener(
                    new PhoneNumberTextWatcher(bindingPropietario.telefono));
            bindingPropietario.toolbar.guardar.setVisibility(View.INVISIBLE);

            bindingPropietario.nombre.setEnabled(false);
            bindingPropietario.apellidoP.setEnabled(false);
            bindingPropietario.apellidoM.setEnabled(false);
            bindingPropietario.telefono.setEnabled(false);
            bindingPropietario.email.setEnabled(false);

            bindingPropietario.nombre.setBackgroundResource(android.R.color.transparent);
            bindingPropietario.apellidoP.setBackgroundResource(android.R.color.transparent);
            bindingPropietario.apellidoM.setBackgroundResource(android.R.color.transparent);
            bindingPropietario.telefono.setBackgroundResource(android.R.color.transparent);
            bindingPropietario.email.setBackgroundResource(android.R.color.transparent);


            bindingPropietario.renta.setVisibility(View.GONE);
            bindingPropietario.md.setVisibility(View.VISIBLE);

            ProviderDatosPropietario.getInstance(getContext())
                    .obtenerDatosPropietario(md, usuario, new ProviderDatosPropietario.ConsultaDatosPropietario() {
                        @Override
                        public void resolve(Propietario propietario) {
                            if(propietario.getCodigo()==200 && propietario.getAMaternoPropietario()!=null){

                                if(propietario.getRentaMasLocales() > 0) {
                                    bindingPropietario.robotoTextView11.setText("YA RENTA A NETO");
                                } else {
                                    bindingPropietario.robotoTextView11.setText("NO RENTA A NETO");
                                }


                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT);
                                params.weight = 1.0f;
                                params.gravity = Gravity.TOP;

                                if(propietario.getMail().equals("null")){
                                    propietario.setMail("");
                                }

                                bindingPropietario.nombre.setText(propietario.getNombrePropietario()+ " ");
                                bindingPropietario.telefono.setText(propietario.getTelefono());
                                bindingPropietario.email.setText(propietario.getMail());
                                bindingPropietario.apellidoP.setText(propietario.getAPaternoPropietario());
                                bindingPropietario.apellidoM.setText(propietario.getAMaternoPropietario());
                            }
                        }
                        @Override
                        public void reject(Exception e) {
                        }
                    });

            bindingPropietario.toolbar.nombreTitulo.setText(getString(R.string.datospropietario));
            bindingPropietario.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdRechazadas a = new FragmentDialogCancelarMdRechazadas();
                        a.show(getChildFragmentManager(),"child");
                    }
                }
            });


        }else if (position == 2) {

            final int[] area = {0};

            bindingSuperficie = DataBindingUtil.inflate(inflater,R.layout.fragment_detalle_superficie,container,false);
            view = bindingSuperficie.getRoot();

            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String[] usuario = {preferences.getString("usuario", "")};
            String md = preferences.getString("mdIdterminar", "");
            final String nombreSitio = preferences.getString("nombreSitio","");
            bindingSuperficie.robotoTextView2.setText(nombreSitio);

            bindingSuperficie.areaterreno.setEnabled(false);
            bindingSuperficie.frente.setEnabled(false);
            bindingSuperficie.profundidad.setEnabled(false);

            bindingSuperficie.areaterreno.setBackgroundResource(android.R.color.transparent);
            bindingSuperficie.frente.setBackgroundResource(android.R.color.transparent);
            bindingSuperficie.profundidad.setBackgroundResource(android.R.color.transparent);


            bindingSuperficie.toolbar.nombreTitulo.setText(R.string.superficie);

            fechaFrente = getFechaHora();
            fechaEntorno1  = getFechaHora();
            fechaEntorno2  = getFechaHora();
            bindingSuperficie.toolbar.guardar.setVisibility(View.INVISIBLE);

            ProviderDatosPredial.getInstance(getContext()).obtenerDatosPredial(md, usuario[0], new ProviderDatosPredial.ConsultaDatosPredial() {
                @Override
                public void resolve(DatosPredial datosPredial) {
                    if(datosPredial!=null){
                        if(datosPredial.getAplicaPredial().equals("1")){
                            bindingSuperficie.predial.setVisibility(View.VISIBLE);
                        }else{
                            urlPredial = " ";
                            fechaPredial = " ";
                        }
                    }
                }
                @Override
                public void reject(Exception e) { }
            });

            bindingSuperficie.volver.setVisibility(View.INVISIBLE);
            final String[] tipoEsquina = {"0"};

            bindingSuperficie.escogeEsquina.setEnabled(false);
            ProviderDatosSuperficie.getInstance(getContext())
                    .obtenerDatosSuperficie(md, usuario[0], new ProviderDatosSuperficie.ConsultaDatosSuperficie() {
                        @Override
                        public void resolve(final Superficie superficie) {

                            if(superficie.getCodigo()==200){
                                int valorFoto = 0;
                                int valorFrente = 0;
                                int valorFondo = 0;
                                int valorEsquina = 0;
                                for(int i = 0;i<superficie.getNiveles().size();i++){
                                    if(superficie.getNiveles().get(i).getNivel()==4 ||
                                            superficie.getNiveles().get(i).getNivel()==5){
                                        Picasso.get().load(superficie.getNiveles().get(i).getImgFrenteId()).into(bindingSuperficie.imagen);
                                        valorFoto = i;
                                        valorFondo = i;
                                    }

                                    if(superficie.getNiveles().get(i).getNivel()==6 ||
                                            superficie.getNiveles().get(i).getNivel()==7){
                                        valorFrente = i;
                                    }

                                    if(superficie.getNiveles().get(i).getNivel()==8){
                                        valorEsquina = i;
                                    }
                                }

                                Double esquina = superficie.getNiveles().get(valorEsquina).getValorreal();

                                if(esquina==1){
                                    bindingSuperficie.escogeEsquina.setChecked(true);
                                    tipoEsquina[0] = "1";
                                }else{
                                    bindingSuperficie.escogeEsquina.setChecked(false);
                                    tipoEsquina[0] = "0";
                                }

                                getContext().getSharedPreferences("datosSuperficie", 0).edit().clear().apply();
                                String superficieS = String.valueOf(superficie.getNiveles().get(valorFrente).getValorreal());
                                superficieS = superficieS.replace(" ", "");

                                String fondoS = String.valueOf(superficie.getNiveles().get(valorFondo).getFondo());
                                fondoS = fondoS.replace(" ", "");

                                bindingSuperficie.frente.setText("  "+superficieS);
                                bindingSuperficie.profundidad.setText("  "+fondoS);

                                String total = String.valueOf((Double.valueOf(superficieS)
                                        *(Double.valueOf(fondoS))));
                                bindingSuperficie.areaterreno.setText("  "+total+"mts2");

                                bindingSuperficie.frontal.setAlpha(1.0f);
                                bindingSuperficie.lateral1.setAlpha(0.35f);
                                bindingSuperficie.lateral2.setAlpha(0.35f);
                                bindingSuperficie.predial.setAlpha(0.35f);

                                bindingSuperficie.robotoTextView2.setText(nombreSitio);

                                final int finalValorFoto = valorFoto;
                                Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgFrenteId()).into(bindingSuperficie.imagen);

                                bindingSuperficie.frontal.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgFrenteId()).into(bindingSuperficie.imagen);
                                        bindingSuperficie.frontal.setAlpha(1.0f);
                                        bindingSuperficie.lateral1.setAlpha(0.35f);
                                        bindingSuperficie.lateral2.setAlpha(0.35f);
                                        bindingSuperficie.predial.setAlpha(0.35f);
                                        if(superficie.getNiveles().get(finalValorFoto).getImgFrenteId().length()>0){
                                            if(urlFrente.length()>0){
                                                Picasso.get().load(urlFrente).into(bindingSuperficie.imagen);
                                            } else {
                                                Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgFrenteId()).into(bindingSuperficie.imagen);
                                            }

                                        }else{
                                            bindingSuperficie.volver.setVisibility(View.GONE);
                                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                            startActivityForResult(intent, CAMERA);
                                        }
                                    }
                                });

                                bindingSuperficie.lateral1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgLateral1Id()).into(bindingSuperficie.imagen);
                                        bindingSuperficie.lateral1.setAlpha(1.0f);
                                        bindingSuperficie.frontal.setAlpha(0.35f);
                                        bindingSuperficie.lateral2.setAlpha(0.35f);
                                        bindingSuperficie.predial.setAlpha(0.35f);
                                        if(superficie.getNiveles().get(finalValorFoto).getImgLateral1Id().length()>0){
                                            if(urlLateral1.length()>0){
                                                Picasso.get().load(urlLateral1).into(bindingSuperficie.imagen);
                                            } else {
                                                Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgLateral1Id()).into(bindingSuperficie.imagen);
                                            }
                                        }else{
                                            bindingSuperficie.volver.setVisibility(View.GONE);
                                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                            startActivityForResult(intent, CAMERA);
                                        }
                                    }
                                });

                                bindingSuperficie.predial.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(!superficie.getNiveles().get(finalValorFoto).getImgPredial().equals("")){
                                            Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgPredial()).into(bindingSuperficie.imagen);
                                            bindingSuperficie.lateral1.setAlpha(0.35f);
                                            bindingSuperficie.frontal.setAlpha(0.35f);
                                            bindingSuperficie.lateral2.setAlpha(0.35f);
                                            bindingSuperficie.predial.setAlpha(1.0f);

                                            if(superficie.getNiveles().get(finalValorFoto).getImgPredial().length()>0){
                                                if(urlPredial.length()>0){
                                                    Picasso.get().load(urlPredial).into(bindingSuperficie.imagen);
                                                } else {
                                                    Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgPredial()).into(bindingSuperficie.imagen);
                                                }
                                            }else{
                                                bindingSuperficie.volver.setVisibility(View.GONE);
                                                // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                // startActivityForResult(intent, CAMERA);
                                            }
                                        }
                                    }
                                });


                                urlFrente = superficie.getNiveles().get(finalValorFoto).getImgFrenteId();
                                urlLateral1 = superficie.getNiveles().get(finalValorFoto).getImgLateral1Id();
                                urlLateral2 = superficie.getNiveles().get(finalValorFoto).getImgLateral2Id();
                                urlPredial = superficie.getNiveles().get(finalValorFoto).getImgPredial();

                                bindingSuperficie.lateral2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        bindingSuperficie.lateral2.setAlpha(1.0f);
                                        bindingSuperficie.frontal.setAlpha(0.35f);
                                        bindingSuperficie.lateral1.setAlpha(0.35f);
                                        bindingSuperficie.predial.setAlpha(0.35f);
                                        if(superficie.getNiveles().get(finalValorFoto).getImgLateral2Id().length()>0){
                                            if(urlLateral2.length()>0){
                                                Picasso.get().load(urlLateral2).into(bindingSuperficie.imagen);
                                            } else {
                                                Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgLateral2Id()).into(bindingSuperficie.imagen);
                                            }
                                        }else{
                                            bindingSuperficie.volver.setVisibility(View.GONE);
                                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                            startActivityForResult(intent, CAMERA);
                                        }
                                    }
                                });
                            }
                        }
                        @Override
                        public void reject(Exception e) { }
                    });


            bindingSuperficie.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdRechazadas a = new FragmentDialogCancelarMdRechazadas();
                        a.show(getChildFragmentManager(),"child");
                    }

                }
            });



        }else if (position == 3) {

            mensaje = "fragment 2";
            bindingZonificacion = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_3,container,false);
            view = bindingZonificacion.getRoot();



            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);

            mapFragment.getMapAsync(onMapReadyCallbackZonificacion);

            bindingZonificacion.toolbar.guardar.setVisibility(View.INVISIBLE);
            bindingZonificacion.cancelar.setVisibility(View.INVISIBLE);

            final SharedPreferences[] preferences = {getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE)};
            final String mdIdterminar = preferences[0].getString("mdIdterminar", "");
            mdLat = preferences[0].getFloat("latMd", 0);
            mdLot = preferences[0].getFloat("lotMd", 0);
            final String usuario = preferences[0].getString("usuario", "");

            String nombreMd = preferences[0].getString("nombreSitio", "");
            bindingZonificacion.robotoTextView2.setText(nombreMd);
            //bindingZonificacion.toolbar.back.setVisibility(View.INVISIBLE);

            bindingZonificacion.toolbar.nombreTitulo.setText(getString(R.string.zonifica));

            if(true){
                preferences[0] = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                ProviderDatosZonificacion.getInstance(getContext())
                        .obtenerDatosZonificacion(mdIdterminar, usuario, new ProviderDatosZonificacion.ConsultaDatosZonificacion() {
                            @Override
                            public void resolve(Zonificacion creaZonificacion) {
                                if(creaZonificacion.getCodigo()==200){


                                    LatLng mds = new LatLng(mdLat, mdLot);
                                    LatLng mdsNuevos = null;

                                    detallesCom = new ArrayList<>();
                                    detallesGen = new ArrayList<>();

                                    for(int i=0;i<creaZonificacion.getCompetencia().size();i++){
                                        for(int j=0;j<creaZonificacion.getCompetencia().get(i).getDetalle().size();j++){
                                            detallesCom.add(creaZonificacion.getCompetencia().get(i).getDetalle().get(j));
                                        }
                                    }

                                    for(int i=0;i<creaZonificacion.getGeneradores().size();i++){
                                        for(int j=0;j<creaZonificacion.getGeneradores().get(i).getDetalle().size();j++){
                                            detallesGen.add(creaZonificacion.getGeneradores().get(i).getDetalle().get(j));
                                        }
                                    }


                                    if(detallesGen.size()==0){
                                       // mdsNuevos = new LatLng(0.0, 0.0);
                                       // colocarMarcador(mdsNuevos, mMapZona, 1,
                                              //  usuario, mds, String.valueOf(mdIdterminar), detallesGen, detallesCom);
                                    }else{
                                        for(int j=0;j<detallesGen.size();j++){
                                            mdsNuevos = new LatLng(Double.valueOf(detallesGen.get(j).getLatitud()),
                                                    Double.valueOf(detallesGen.get(j).getLongitud()));

                                            colocarMarcador(mdsNuevos, mMapZona, detallesGen.get(j).getGeneradorId(),
                                                    usuario, mds, String.valueOf(mdIdterminar), detallesGen, detallesCom);
                                        }
                                    }

                                    if(detallesCom.size()==0){
                                        mdsNuevos = new LatLng(0.0, 0.0);
                                        colocarMarcador(mdsNuevos, mMapZona, 1,
                                                usuario, mds, String.valueOf(mdIdterminar), detallesGen, detallesCom);
                                    }else{
                                        for(int j=0;j<detallesCom.size();j++){
                                            mdsNuevos = new LatLng(Double.valueOf(detallesCom.get(j).getLatitud()),
                                                    Double.valueOf(detallesCom.get(j).getLongitud()));
                                            colocarMarcador(mdsNuevos, mMapZona, detallesCom.get(j).getGeneradorId(),
                                                    usuario, mds, String.valueOf(mdIdterminar), detallesGen, detallesCom);
                                        }
                                    }

                                    listGeneradores = new ArrayList<>();

                                }else{
                                    Toast.makeText(getContext(), "Error al obtener los datos",
                                            Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void reject(Exception e) { }
                        });


                slideUX(bindingZonificacion);

                ProviderDatosCompetencias.getInstance(getContext()).obtenerDatosCompetencias(usuario, mdIdterminar, new ProviderDatosCompetencias.ConsultaDatosCompetencia() {
                    @Override
                    public void resolve(CompetenciasGeneradoresV2 competenciasGeneradores) {

                        if(competenciasGeneradores!=null && competenciasGeneradores.getCodigo()==200){
                            listCompetencia = new ArrayList<>();
                            listGeneradores = new ArrayList<>();
                            listCompetenciaTiendaNeto = new ArrayList<>();
                            listGeneradoresNegocios = new ArrayList<>();
                            listGeneradoresTransporte= new ArrayList<>();

                            negociosDeComidaArrayList = new ArrayList<>();
                            mercadoPublicoArrayList = new ArrayList<>();
                            tianguiArrayList = new ArrayList<>();

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
                            ///////
                            for(int i=0;i<competenciasGeneradores.getGeneradores().getNegociosDeComida().size();i++){
                                negociosDeComidaArrayList.add(competenciasGeneradores.getGeneradores().getNegociosDeComida().get(i));
                            }

                            for(int i=0;i<competenciasGeneradores.getGeneradores().getMercadoPublico().size();i++){
                                mercadoPublicoArrayList.add(competenciasGeneradores.getGeneradores().getMercadoPublico().get(i));
                            }

                            for(int i=0;i<competenciasGeneradores.getGeneradores().getTianguis().size();i++){
                                tianguiArrayList.add(competenciasGeneradores.getGeneradores().getTianguis().get(i));
                            }


                            /****** transporte negocio comida *******/

                            adapterListaGeneradoresNegociosComida = new AdapterListaGeneradoresNegociosComida(negociosDeComidaArrayList, getContext(), clickNegociosComida);
                            bindingZonificacion.content2.contentListaComida.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaComida.setAdapter(adapterListaGeneradoresNegociosComida);
                            RecyclerView.LayoutManager mLayoutManagerNegociosComida = new GridLayoutManager(getContext(), 4);

                            bindingZonificacion.content2.contentListaComida.setLayoutManager(mLayoutManagerNegociosComida);
                            bindingZonificacion.content2.contentListaComida.addItemDecoration(new FragmentDetalleRechazadas.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaComida.setItemAnimator(new DefaultItemAnimator());

                            /****** mercado publico *******/
                            adapterListaGeneradoresMercadoPublico = new AdapterListaGeneradoresMercadoPublico(mercadoPublicoArrayList, getContext(), clickMercadoPublico);
                            bindingZonificacion.content2.contentListaMercado.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaMercado.setAdapter(adapterListaGeneradoresMercadoPublico);
                            RecyclerView.LayoutManager mLayoutManagerMercado = new GridLayoutManager(getContext(), 4);

                            bindingZonificacion.content2.contentListaMercado.setLayoutManager(mLayoutManagerMercado);
                            bindingZonificacion.content2.contentListaMercado.addItemDecoration(new FragmentDetalleRechazadas.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaMercado.setItemAnimator(new DefaultItemAnimator());

                            /****** tianguis *******/
                            adapterListaGeneradoresTianguis = new AdapterListaGeneradoresTianguis(tianguiArrayList, getContext(), clickTiaguis);
                            bindingZonificacion.content2.contentListaTianguis.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaTianguis.setAdapter(adapterListaGeneradoresTianguis);


                            RecyclerView.LayoutManager mLayoutManagerTianguis = new GridLayoutManager(getContext(), 4);

                            bindingZonificacion.content2.contentListaTianguis.setLayoutManager(mLayoutManagerTianguis);
                            bindingZonificacion.content2.contentListaTianguis.addItemDecoration(new FragmentDetalleRechazadas.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaTianguis.setItemAnimator(new DefaultItemAnimator());


                            /****** transporte publico *******/
                            adapterTransporte = new AdapterListaGeneradoresTransporte(listGeneradoresTransporte, getContext(), clickTransporte);
                            bindingZonificacion.content2.contentListaTransporte.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaTransporte.setAdapter(adapterTransporte);

                            RecyclerView.LayoutManager mLayoutManagerTransporte = new GridLayoutManager(getContext(), 4);

                            bindingZonificacion.content2.contentListaTransporte.setLayoutManager(mLayoutManagerTransporte);
                            bindingZonificacion.content2.contentListaTransporte.addItemDecoration(new FragmentDetalleRechazadas.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaTransporte.setItemAnimator(new DefaultItemAnimator());

                            /****** otros generadores *******/
                            adapterNegocios = new AdapterListaGeneradoresNegocios(listGeneradoresNegocios, getContext(), clickNegocio);
                            bindingZonificacion.content2.contentListaNegocios.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaNegocios.setAdapter(adapterNegocios);

                            RecyclerView.LayoutManager mLayoutManagerNegocios = new GridLayoutManager(getContext(), 4);
                            bindingZonificacion.content2.contentListaNegocios.setLayoutManager(mLayoutManagerNegocios);
                            bindingZonificacion.content2.contentListaNegocios.addItemDecoration(new FragmentDetalleRechazadas.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaNegocios.setItemAnimator(new DefaultItemAnimator());

                            /****** otros generadores *******/
                            adapter2 = new AdapterListaGeneradores(listGeneradores, getContext(), clicks);
                            bindingZonificacion.content2.contentLista.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentLista.setAdapter(adapter2);

                            RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(getContext(), 4);
                            bindingZonificacion.content2.contentLista.setLayoutManager(mLayoutManager2);
                            bindingZonificacion.content2.contentLista.addItemDecoration(new FragmentDetalleRechazadas.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentLista.setItemAnimator(new DefaultItemAnimator());

                            /****** competencias *******/
                            adapter = new AdapterListaCompetencia(listCompetencia, getContext(), click);
                            bindingZonificacion.contenido.contentLista.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.contenido.contentLista.setAdapter(adapter);

                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
                            bindingZonificacion.contenido.contentLista.setLayoutManager(mLayoutManager);
                            bindingZonificacion.contenido.contentLista.addItemDecoration(new FragmentDetalleRechazadas.GridSpacingItemDecoration(3, dpToPx(4), true));
                            bindingZonificacion.contenido.contentLista.setItemAnimator(new DefaultItemAnimator());

                            /****** tiendas neto *******/
                            adapterTiendaNeto = new AdapterListaTiendaNeto(listCompetenciaTiendaNeto, getContext(), clickTiendaNeto);
                            bindingZonificacion.contenido.contentListaTienda.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.contenido.contentListaTienda.setAdapter(adapterTiendaNeto);

                            RecyclerView.LayoutManager mLayoutManagerTienda = new GridLayoutManager(getContext(), 3);
                            bindingZonificacion.contenido.contentListaTienda.setLayoutManager(mLayoutManagerTienda);
                            bindingZonificacion.contenido.contentListaTienda.addItemDecoration(new FragmentDetalleRechazadas.GridSpacingItemDecoration(3, dpToPx(4), true));
                            bindingZonificacion.contenido.contentListaTienda.setItemAnimator(new DefaultItemAnimator());

                        }

                        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                                .findFragmentById(R.id.map);
                        mapFragment.getMapAsync(onMapReadyCallbackZonificacion);
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
                        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("zonificacion", 1);
                        editor.apply();
                    }
                });

                bindingZonificacion.toolbar.back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(noti){
                            Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                            startActivity(main);
                        }else{
                            FragmentDialogCancelarMdRechazadas a = new FragmentDialogCancelarMdRechazadas();
                            a.show(getChildFragmentManager(),"child");
                        }
                    }
                });

                bindingZonificacion.contenido.competencias.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideCompetencia.hide();
                    }
                });

                bindingZonificacion.content2.generadores.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideGenerador.hide();
                    }
                });

                bindingZonificacion.contenido.neto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideCompetencia.hide();
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
            }

            /*************************************** terminar zonificacion **************************************************/

        }else if (position == 4) {

            bindingConstruccion = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_4,container,false);
            view = bindingConstruccion.getRoot();

            bindingConstruccion.toolbar.guardar.setVisibility(View.INVISIBLE);

            SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String mdIdterminar = preferences.getString("mdIdterminar", "");
            final String usuarioId = preferences.getString("usuario", "");
            final String nombreSitio = preferences.getString("nombreSitio","");
            bindingConstruccion.titulo.setText(nombreSitio+"");

            bindingConstruccion.toolbar.nombreTitulo.setText(getString(R.string.construccion));
           // bindingConstruccion.toolbar.back.setVisibility(View.INVISIBLE);

            ProviderDatosFactoresConstruccion.getInstance(getContext()).obtenerDatosContruccion(mdIdterminar,
                    new ProviderDatosFactoresConstruccion.ConsultaFactoresConstruccion() {
                @Override
                public void resolve(final FactoresConstruccion factoresConstruccion) {

                    if(factoresConstruccion.getCodigo()==200){
                        if(factoresConstruccion.getCatalogo()!=null) {

                            ProviderDatosConstruccion.getInstance(getContext())
                                    .obtenerDatosConstruccion(mdIdterminar, usuarioId, new ProviderDatosConstruccion.ConsultaDatosConstruccion() {
                                        @Override
                                        public void resolve(DatosConstruccions datosSitio) {
                                            datosSitios = datosSitio;
                                            if(datosSitio!=null){
                                                if(datosSitio.getCodigo()==200 &&  datosSitio.getConstruccion().size() > 0) {

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

                                                    datosConstruccion(mdIdterminar, usuarioId, datosSitios);

                                                    bindingConstruccion.cargar.setVisibility(View.INVISIBLE);


                                                } else{

                                                    generarConstruccion(
                                                            bindingConstruccion,
                                                            factoresConstruccion);

                                                    generarDetalles(bindingConstruccion,
                                                            factoresConstruccion);

                                                    generarCondiciones(bindingConstruccion,
                                                            factoresConstruccion);
                                                    bindingConstruccion.cargar.setVisibility(View.INVISIBLE);

                                                }
                                            }
                                        }

                                        @Override
                                        public void reject(Exception e) {

                                        }
                                    });
                        }
                    }else{
                        Toast.makeText(getContext(), "Error al consultar factores de construcción", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void reject(Exception e) {

                }
            });



            bindingConstruccion.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdRechazadas a = new FragmentDialogCancelarMdRechazadas();
                        a.show(getChildFragmentManager(),"child");
                    }
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






        }else if (position == 5) {

            final FragmentDetalleGeneralidadesBinding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detalle_generalidades,container,false);
            view = binding.getRoot();

            //binding.toolbar.back.setVisibility(View.INVISIBLE);

            binding.toolbar.nombreTitulo.setText(getString(R.string.generalidades));
            binding.datepicker.setMinDate(System.currentTimeMillis() - 1000);




            binding.renta.setEnabled(false);
            binding.amortizaciontotal.setEnabled(false);

            binding.toolbar.guardar.setVisibility(View.INVISIBLE);

            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);

            final String[] disponibilidad = new String[1];
            String nombreMd = preferences.getString("nombreSitio", "");
            binding.robotoTextView2.setText(nombreMd);

            String usuarioId = preferences.getString("usuario", "");
            final String mdIdterminar = preferences.getString("mdIdterminar", "");
            final int textColor = Color.parseColor("#254581");
            ProviderDatosAmortizacion.getInstance(getContext()).obtenerDatosAmortizacion(mdIdterminar, usuarioId, new ProviderDatosAmortizacion.ConsultaDatosAmortizacion() {
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
            ProviderDatosGeneralidadesSitio.getInstance(getContext())
                    .obtenerDatosGeneralidades(mdIdterminar, usuarioId, new ProviderDatosGeneralidadesSitio.ConsultaGeneralidadesSitio() {
                        @Override
                        public void resolve(GeneralidadesSitio datosSitio) {
                            if(datosSitio!=null && datosSitio.getCodigo()==200) {
                                if(datosSitio.getGeneralidades().size()>0){
                                    String fecha = datosSitio.getGeneralidades().get(0).getFechadisponible();

                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                    Date d = null;
                                    try {
                                        d = formatter.parse(fecha);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    Calendar thatDay = Calendar.getInstance();
                                    thatDay.setTime(d);

                                    int year = thatDay.get(Calendar.YEAR);
                                    int month = thatDay.get(Calendar.MONTH);
                                    int day = thatDay.get(Calendar.DAY_OF_MONTH);

                                    binding.datepicker.updateDate(year, month, day);


                                    for(int i = 0; i < datosSitio.getGeneralidades().size(); i++) {

                                        if(datosSitio.getGeneralidades().get(i).getNivelid() == 7 ||
                                                datosSitio.getGeneralidades().get(i).getNivelid() == 8 ||
                                                datosSitio.getGeneralidades().get(i).getNivelid() == 9){

                                            binding.amortizaciontotal.setText(datosSitio.getGeneralidades().get(i).getValor()+" MXN");

                                            if(datosSitio.getGeneralidades().get(i).getDetalles()
                                                    != null && datosSitio.getGeneralidades().get(i).getDetalles().size() > 0) {
                                                int valor = datosSitio.getGeneralidades().get(i).getDetalles().get(0).getValor();
                                                switch (valor){
                                                    case 0:
                                                        binding.periodoamotizacion.setSelection(0);
                                                        break;
                                                    case 1:
                                                        binding.periodoamotizacion.setSelection(1);
                                                        break;
                                                    case 2:
                                                        binding.periodoamotizacion.setSelection(2);
                                                        break;
                                                    case 3:
                                                        binding.periodoamotizacion.setSelection(3);
                                                        break;
                                                    case 6:
                                                        binding.periodoamotizacion.setSelection(4);
                                                        break;
                                                    case 9:
                                                        binding.periodoamotizacion.setSelection(5);
                                                        break;
                                                    case 12:
                                                        binding.periodoamotizacion.setSelection(6);
                                                        break;
                                                    case 18:
                                                        binding.periodoamotizacion.setSelection(7);
                                                        break;
                                                    case 24:
                                                        binding.periodoamotizacion.setSelection(8);
                                                        break;
                                                    case 30:
                                                        binding.periodoamotizacion.setSelection(9);
                                                        break;
                                                    case 36:
                                                        binding.periodoamotizacion.setSelection(10);
                                                        break;
                                                    case 42:
                                                        binding.periodoamotizacion.setSelection(11);
                                                        break;
                                                    case 48:
                                                        binding.periodoamotizacion.setSelection(12);
                                                        break;
                                                    case 54:
                                                        binding.periodoamotizacion.setSelection(13);
                                                        break;
                                                    case 60:
                                                        binding.periodoamotizacion.setSelection(14);
                                                        break;
                                                }


                                            }

                                        }


                                        if(datosSitio.getGeneralidades().get(i).getNivelid() == 4 || datosSitio.getGeneralidades().get(i).getNivelid() == 5 || datosSitio.getGeneralidades().get(i).getNivelid() == 6){

                                            if(datosSitio.getGeneralidades().get(i).getNivelid() == 4){
                                                binding.rinmediato.setChecked(true);
                                                binding.rinmediato.setTextColor(Color.parseColor("#254581"));
                                                binding.rinmediato.setButtonTintList(ColorStateList.valueOf(textColor));
                                                disponibilidad[0] = "1";
                                                binding.datepicker.setVisibility(View.GONE);
                                            }

                                            if(datosSitio.getGeneralidades().get(i).getNivelid() == 5){
                                                binding.rapartirde.setChecked(true);
                                                binding.rapartirde.setTextColor(Color.parseColor("#254581"));
                                                binding.rapartirde.setButtonTintList(ColorStateList.valueOf(textColor));

                                                disponibilidad[0] = "3";
                                                binding.datepicker.setVisibility(View.VISIBLE);
                                            }

                                            if(datosSitio.getGeneralidades().get(i).getNivelid() == 6){
                                                binding.rocupado.setChecked(true);
                                                binding.rocupado.setTextColor(Color.parseColor("#254581"));
                                                binding.rocupado.setButtonTintList(ColorStateList.valueOf(textColor));
                                                disponibilidad[0] = "2";
                                                binding.datepicker.setVisibility(View.GONE);
                                            }

                                        }

                                        if(datosSitio.getGeneralidades().get(i).getNivelid() == 1 ||
                                                datosSitio.getGeneralidades().get(i).getNivelid() == 2 ||
                                                datosSitio.getGeneralidades().get(i).getNivelid() == 3){

                                            String valor = datosSitio.getGeneralidades().get(i).getValor().toString();
                                            binding.renta.setText(valor);
                                            int valor2 = datosSitio.getGeneralidades().get(i).getDetalles().get(0).getValor();

                                            switch (valor2){
                                                case 0:
                                                    binding.periodogracia.setSelection(0);
                                                    break;
                                                case 1:
                                                    binding.periodogracia.setSelection(1);
                                                    break;
                                                case 2:
                                                    binding.periodogracia.setSelection(2);
                                                    break;
                                                case 3:
                                                    binding.periodogracia.setSelection(3);
                                                    break;
                                                case 4:
                                                    binding.periodogracia.setSelection(4);
                                                    break;
                                                case 5:
                                                    binding.periodogracia.setSelection(5);
                                                    break;
                                                case 6:
                                                    binding.periodogracia.setSelection(6);
                                                    break;
                                                case 7:
                                                    binding.periodogracia.setSelection(7);
                                                    break;
                                                case 8:
                                                    binding.periodogracia.setSelection(8);
                                                    break;
                                                case 9:
                                                    binding.periodogracia.setSelection(9);
                                                    break;
                                                case 10:
                                                    binding.periodogracia.setSelection(10);
                                                    break;
                                                case 11:
                                                    binding.periodogracia.setSelection(11);
                                                    break;
                                                case 12:
                                                    binding.periodogracia.setSelection(12);
                                                    break;
                                            }

                                        }
                                    }
                                }


                            }
                        }
                        @Override
                        public void reject(Exception e) {

                        }
                    });



            binding.datepicker.setEnabled(false);
            binding.rinmediato.setEnabled(false);
            binding.rocupado.setEnabled(false);
            binding.rapartirde.setEnabled(false);

            binding.periodoamotizacion.setEnabled(false);
            binding.periodogracia.setEnabled(false);

            binding.rdgGrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rinmediato:
                            binding.datepicker.setVisibility(View.INVISIBLE);
                            break;
                        case R.id.rocupado:
                            binding.datepicker.setVisibility(View.INVISIBLE);
                            break;
                        case R.id.rapartirde:
                            binding.datepicker.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            });


            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdRechazadas a = new FragmentDialogCancelarMdRechazadas();
                        a.show(getChildFragmentManager(),"child");
                    }
                }
            });

        } else if (position == 6) {
            final FragmentAutoriza6Binding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_6,container,false);
            view = binding.getRoot();
            final ArrayList<String> horarios = new ArrayList<>();

            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String usuarioId = preferences.getString("usuario", "");
            final String mdIdterminar = preferences.getString("mdIdterminar", "");

            binding.conteo.setVisibility(View.INVISIBLE);
            binding.btnFinalizar.setVisibility(View.INVISIBLE);
            binding.toolbar.guardar.setVisibility(View.INVISIBLE);

            //binding.toolbar.back.setVisibility(View.INVISIBLE);

            String nombreMd = preferences.getString("nombreSitio", "");
            binding.robotoTextView2.setText(nombreMd);
            listaPeatonal(binding);
            binding.recyclerHoras.setVisibility(View.INVISIBLE);
            ProviderHorasPeatonales.getInstance(getContext()).obtenerHoras(mdIdterminar, usuarioId,
                    new ProviderHorasPeatonales.InterfaceObtieneHoras() {
                        @Override
                        public void resolve(final HorasPeatonales horasPeatonales) {
                            if(horasPeatonales.getCodigo()==200){

                                if(horasPeatonales.getDetalle().size()<0){
                                    binding.btnFinalizar.setAlpha(0.35f);
                                }else{
                                    binding.botones.setVisibility(View.VISIBLE);
                                    binding.btnFinalizar.setEnabled(true);
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
                                binding.recyclerHoras.addItemDecoration(new FragmentDetalleRechazadas.GridSpacingItemDecoration(3, dpToPx(4), true));
                                binding.recyclerHoras.setItemAnimator(new DefaultItemAnimator());
                                binding.peatonalConteo.spinnerHora.setItems(horarios);

                                horaInicio = horasPeatonales.getDetalle().get(0).getHoraMin();
                                horaFinal = horasPeatonales.getDetalle().get(0).getHoraMax();

                                Calendar c = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
                                final String strDate = sdf.format(c.getTime());

                                binding.toolbar.nombreTitulo.setText(getString(R.string.flujopeatonal));
                                binding.conteo.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        binding.peaton.setVisibility(View.VISIBLE);
                                        binding.recyclerPeatonal.setVisibility(View.INVISIBLE);
                                        binding.btnFinalizar.setVisibility(View.INVISIBLE);
                                        binding.promedio.setVisibility(View.INVISIBLE);

                                        String hoI = horasPeatonales.getDetalle().get(0).getHoraMin();
                                        String hoF = horasPeatonales.getDetalle().get(0).getHoraMax();

                                        hoI = hoI.substring(0, 5);
                                        hoF = hoF.substring(0, 5);

                                        boolean hora =  isHourInInterval(strDate, hoI, hoF);
                                        if(hora!=false){
                                            binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                            binding.peatonalConteo.btnGuardar.setEnabled(true);
                                            horaInicio = hoI;
                                            horaFinal = hoF;
                                        }else{
                                            binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                            Toast.makeText(getContext(), "La hora actual no se encuentra en esta seleccion",
                                                    Toast.LENGTH_SHORT).show();
                                        }


                                        listaPeatonal(binding);



                                        binding.toolbar.guardar.setVisibility(View.INVISIBLE);
                                        binding.peatonalConteo.fechaHoy.setText(Util.getFechita());


                                        final String finalHoI = hoI;
                                        final String finalHoF = hoF;
                                        binding.peatonalConteo.spinnerHora.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                                            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                                                if(position==0){
                                                    boolean hora = isHourInInterval(strDate, finalHoI, finalHoF);
                                                    if(hora!=false){
                                                        binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                                        binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                        horaInicio = finalHoI;
                                                        horaFinal = finalHoF;
                                                    }else{
                                                        binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                        Toast.makeText(getContext(), "La hora actual no se encuentra en esta seleccion", Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                if(position==1){

                                                    String hoI = horasPeatonales.getDetalle().get(1).getHoraMin();
                                                    String hoF = horasPeatonales.getDetalle().get(1).getHoraMax();

                                                    boolean hora =  isHourInInterval(strDate, hoI, hoF);
                                                    if(hora!=false){
                                                        binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                                        binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                        horaInicio = hoI;
                                                        horaFinal = hoF;
                                                    }else{
                                                        binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                        Toast.makeText(getContext(), "La hora actual no se encuentra en esta seleccion",
                                                                Toast.LENGTH_SHORT).show();
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

                                                    boolean hora = isHourInInterval(strDate, hoI, hoF);
                                                    if(hora!=false){
                                                        binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                                        binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                        horaInicio = hoI;
                                                        horaFinal = hoF;
                                                    }else{
                                                        binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                        binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                        Toast.makeText(getContext(), "La hora actual no se encuentra en esta seleccion",
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        });

                                        binding.peatonalConteo.cronometroPlay.setAlpha(0.35f);
                                        binding.peatonalConteo.cronometroStop.setAlpha(0.35f);
                                        binding.peatonalConteo.cronometroPlay.setEnabled(true);
                                        binding.peatonalConteo.cronometroStop.setEnabled(true);

                                        binding.peatonalConteo.cronometroPlay.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                binding.peatonalConteo.cronometroPlay.setAlpha(1.0f);
                                                binding.peatonalConteo.cronometroStop.setAlpha(0.35f);
                                                binding.peatonalConteo.chronometer1.setBase(SystemClock.elapsedRealtime()- tiempo *1000);
                                                binding.peatonalConteo.chronometer1.start();
                                                showElapsedTime(binding);
                                                binding.peatonalConteo.cronometroPlay.setEnabled(false);

                                            }
                                        });

                                        binding.peatonalConteo.cronometroStop.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                binding.peatonalConteo.chronometer1.setBase(SystemClock.elapsedRealtime()- tiempo *1000);
                                                binding.peatonalConteo.cronometroPlay.setEnabled(true);
                                                binding.peatonalConteo.cronometroStop.setAlpha(1.0f);
                                                binding.peatonalConteo.cronometroPlay.setAlpha(0.35f);
                                                binding.peatonalConteo.chronometer1.stop();
                                                showElapsedTime(binding);
                                            }
                                        });


                                        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if(noti){
                                                    Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                                                    startActivity(main);
                                                }else{
                                                    FragmentDialogCancelarMdRechazadas a = new FragmentDialogCancelarMdRechazadas();
                                                    a.show(getChildFragmentManager(),"child");
                                                }
                                            }
                                        });


                                        binding.peatonalConteo.regresar.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                binding.peaton.setVisibility(View.INVISIBLE);
                                                binding.conteo.setEnabled(true);
                                                binding.btnFinalizar.setVisibility(View.VISIBLE);
                                                binding.recyclerPeatonal.setVisibility(View.VISIBLE);
                                                binding.btnFinalizar.setVisibility(View.VISIBLE);
                                                binding.promedio.setVisibility(View.VISIBLE);

                                            }
                                        });
                                    }
                                });


                            }
                        }

                        @Override
                        public void reject(Exception e) {

                        }
                    });

            binding.btnFinalizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getContext(), ActivityFinalizaTerminar.class);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdRechazadas a = new FragmentDialogCancelarMdRechazadas();
                        a.show(getChildFragmentManager(),"child");
                    }
                }
            });


        }else {

            final ActivityFinalizaBinding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.activity_finaliza,container,false);
            view = binding.getRoot();
            getDatos(binding);
            binding.btnFinalizar.setVisibility(View.INVISIBLE);
            binding.btnGuardar.setVisibility(View.INVISIBLE);
            binding.atras.setVisibility(View.INVISIBLE);

            binding.btnAtras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdRechazadas a = new FragmentDialogCancelarMdRechazadas();
                        a.show(getChildFragmentManager(),"child");
                    }

                }
            });

        }
        return view;
    }

    String puntuacion, categoria;
    ArrayList<DatosPuntuacion.Factore> factoresMacro;
    ArrayList<DatosPuntuacion.Factore> factoresMicro;
    public void getDatos(final ActivityFinalizaBinding binding){

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdid = preferences.getString("mdIdterminar", "");
        String usuario = preferences.getString("usuario", "");

        ProviderConsultaFinaliza.getInstance(getContext()).obtenerPuntos(mdid, usuario, new ProviderConsultaFinaliza.ConsultaPuntos() {
            @Override
            public void resolve(DatosPuntuacion datosPuntuacion) {
                if(datosPuntuacion.getCodigo()==200) {

                    factoresMacro = new ArrayList<>();
                    factoresMicro = new ArrayList<>();

                    SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);

                    binding.textoTipo.setText(datosPuntuacion.getUbicacionMD()+"");
                    categoria = datosPuntuacion.getNomcategoria();
                    for (int i = 0; i < datosPuntuacion.getFactores().size(); i++) {
                        if (datosPuntuacion.getFactores().get(i).getNombrenivel().equals("TOTAL")) {
                            puntuacion = datosPuntuacion.getFactores().get(i).getPuntuacion().toString();
                        }
                    }

                    for (int i = 0; i < datosPuntuacion.getFactores().size(); i++) {
                        if(datosPuntuacion.getFactores().get(i).getRangoubica()!=null){
                            if (datosPuntuacion.getFactores().get(i).getRangoubica().equals(getString(R.string.micro_ub))) {
                                factoresMacro.add(datosPuntuacion.getFactores().get(i));
                            } else{
                                factoresMicro.add(datosPuntuacion.getFactores().get(i));
                            }
                        }else{
                            factoresMicro.add(datosPuntuacion.getFactores().get(i));
                        }
                    }

                    if (factoresMicro.size() <= 0) {
                        binding.tituloMicro.setVisibility(View.GONE);
                    }

                    if (factoresMacro.size() <= 0) {
                        binding.tituloMacro.setVisibility(View.GONE);
                    }

                    generarDetallesMicro(binding, factoresMicro);
                    generarDetallesMacro(binding, factoresMacro);

                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("categoria", categoria);
                    editor.putString("puntuacion", puntuacion);
                    editor.apply();

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
        TableRow rowPlomo = new TableRow(getContext());
        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
        int paddingDp = 2;

        float density = getResources().getDisplayMetrics().density;
        int paddingPixel = (int)(paddingDp * density);

        for(int i = 0; i < datosPuntuacion.size(); i ++){

            TableRow tbrow = new TableRow(getContext());
            tbrow.setBackgroundColor(resource.getColor(R.color.blanco));
            TextView t1v1 = new TextView(getContext());
            t1v1.setTextSize(12);
            t1v1.setText(datosPuntuacion.get(i).getNombrenivel()+"");
            t1v1.setTextColor(resource.getColor(R.color.azul));
            t1v1.setPadding(0, paddingPixel,0,0);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(getContext());
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");
            t3v1.setTextColor(resource.getColor(R.color.azul));
            t3v1.setGravity(Gravity.RIGHT);
            t3v1.setLayoutParams( new TableRow.LayoutParams( 50,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v1);


            TextView t3v2 = new TextView(getContext());
            t3v2.setTextSize(12);
            if(datosPuntuacion.get(i).getTotalxfactor()!=null){
                t3v2.setText("/"+datosPuntuacion.get(i).getTotalxfactor()+"");
            }else{
                binding.tituloMacro.setVisibility(View.GONE);
                binding.tituloMicro.setVisibility(View.GONE);
            }
            t3v2.setTextColor(resource.getColor(R.color.azul));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams( new TableRow.LayoutParams( 75,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v2);


            binding.factores.addView(tbrow);
        }
    }

    public void generarDetallesMicro(ActivityFinalizaBinding binding,  ArrayList<DatosPuntuacion.Factore> datosPuntuacion){

        Resources resource = this.getResources();
        binding.factoresMicro.removeAllViews();
        TableRow rowPlomo = new TableRow(getContext());
        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
        int paddingDp = 2;

        float density = getResources().getDisplayMetrics().density;
        int paddingPixel = (int)(paddingDp * density);

        for(int i = 0; i < datosPuntuacion.size(); i ++){

            TableRow tbrow = new TableRow(getContext());
            tbrow.setBackgroundColor(resource.getColor(R.color.blanco));
            TextView t1v1 = new TextView(getContext());
            t1v1.setTextSize(12);
            t1v1.setText(datosPuntuacion.get(i).getNombrenivel()+"");
            t1v1.setTextColor(resource.getColor(R.color.azul));
            t1v1.setPadding(0, paddingPixel,0,0);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(getContext());
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");
            t3v1.setTextColor(resource.getColor(R.color.azul));
            t3v1.setGravity(Gravity.RIGHT);
            t3v1.setLayoutParams( new TableRow.LayoutParams( 50,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v1);

            TextView t3v2 = new TextView(getContext());
            t3v2.setTextSize(12);
            if(datosPuntuacion.get(i).getTotalxfactor()!=null){
                t3v2.setText("/"+datosPuntuacion.get(i).getTotalxfactor()+"");
            }else{
                binding.tituloMacro.setVisibility(View.GONE);
                binding.tituloMicro.setVisibility(View.GONE);
            }
            t3v2.setTextColor(resource.getColor(R.color.azul));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams( new TableRow.LayoutParams( 75,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v2);

            binding.factoresMicro.addView(tbrow);
        }
    }

    long tiempo = 0;

    public static boolean isHourInInterval(String target, String start, String end) {
        return ((target.compareTo(start) >= 0)&& (target.compareTo(end) <= 0));
    }

    private void showElapsedTime(FragmentAutoriza6Binding binding) {
        long elapsedMillis = SystemClock.elapsedRealtime() - binding.peatonalConteo.chronometer1.getBase();
        tiempo = elapsedMillis/1000;
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


                    check.setEnabled(false);

                    if (checked) {
                        checks.put(j, "1");
                    } else {
                        checks.put(j, "0");
                    }

                    final int finalI = j;
                    int finalJ = j;


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
        String mdIdterminar = preferences.getString("mdIdterminar", "");
        if (requestCode == CAMERA_FRONTAL && resultCode==-1) {
            if(resultCode==0){

            }else{
                bit = (Bitmap) data.getExtras().get("data");
                base64frente = b64(bit);
                fechaFrente = getFechaHora();
                obtenerUrl(random()+"_frente", base64frente, mdIdterminar);

            }
        }else if(requestCode == CAMERA_LATERAL_1 && resultCode==-1){
            if(resultCode==0){

            }else{
                bit = (Bitmap) data.getExtras().get("data");
                base64Lateral1 = b64(bit);
                fechaEntorno1 = getFechaHora();
                obtenerUrl(random()+"_lateral1", base64Lateral1, mdIdterminar);

            }
        }else if(requestCode == CAMERA_LATERAL_2 && resultCode==-1){
            if(resultCode==0){

            }else{
                bit = (Bitmap) data.getExtras().get("data");
                base64Lateral2 = b64(bit);
                fechaEntorno2 = getFechaHora();
                obtenerUrl(random()+"_lateral2", base64Lateral2, mdIdterminar);


            }
        }else if(resultCode == 0){


        }

    }

    String urlFrente = "";
    String urlLateral1 = "";
    String urlLateral2 = "";
    String urlPredial = "";

    public void obtenerUrl(String foto, String b64, String mdId){
        ProviderObtenerUrl.getInstance(getContext()).obtenerUrl(mdId, foto, b64 , new ProviderObtenerUrl.ConsultaUrl() {
            @Override
            public void resolve(Codigos codigo) {
                if(codigo!= null && codigo.getResultado().getSecureUrl()!=null){
                    if(codigo.getResultado().getSecureUrl().contains("frente")){
                        bindingSuperficie.frontal.setEnabled(false);
                        urlFrente = codigo.getResultado().getSecureUrl();
                        Picasso.get().load(urlFrente).into(bindingSuperficie.imagen);
                        bindingSuperficie.frontal.setEnabled(true);
                    }else if(codigo.getResultado().getSecureUrl().contains("lateral1")){
                        bindingSuperficie.lateral1.setEnabled(false);
                        urlLateral1 = codigo.getResultado().getSecureUrl();
                        Picasso.get().load(urlLateral1).into(bindingSuperficie.imagen);
                        bindingSuperficie.lateral1.setEnabled(true);
                    }else{
                        bindingSuperficie.lateral2.setEnabled(false);
                        urlLateral2 = codigo.getResultado().getSecureUrl();
                        Picasso.get().load(urlLateral2).into(bindingSuperficie.imagen);
                        bindingSuperficie.lateral2.setEnabled(true);
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
               // Log.e("contra", "contra");

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
        String mdid = preferences.getString("mdIdterminar", "");
        String usuario = preferences.getString("usuario", "");
        ProviderDatosPeatonal.getInstance(getContext()).obtenerDatosPeatonal(mdid, usuario,new ProviderDatosPeatonal.ConsultaPeatonal() {
            @Override
            public void resolve(Peatonales peatonal) {
                peatonales = new ArrayList<>();
                if(peatonal!=null && peatonal.getCodigo()==200){

                    if(peatonal.getConteos().size()>0){
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
                        binding.headersConteo.setVisibility(View.VISIBLE);
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

    public void setDireccion(FragmentDetalleSitioBinding binding, Double lat, Double lng){
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
                String municipio = addresses.get(0).getLocality();
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

    public void colocarMarcador(LatLng latLng, GoogleMap mMap, int valor,
                                String usuario, LatLng mds, String mdIdZ,
                                ArrayList<Zonificacion.Detalle> detallesGene, ArrayList<Zonificacion.Detalle> detallesCompe){

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

        String nivel = "";

        if(mMap!=null){


            market = mMap.addMarker(new
                    MarkerOptions().
                    position(latLng).
                    title("").snippet("")
                    .icon(icon));

            markers.add(market);

            if(valor == 1 || valor == 2 || valor == 3
                    || valor == 4){

                nivel = distancia(latLng, mds, "competencia");

                detalleC = new CrearZonificacion.Detalle(
                        String.valueOf(valor),
                        String.valueOf(market.getPosition().latitude),
                        String.valueOf(market.getPosition().longitude),
                        nivel
                );

                detallesC.add(detalleC);


                zonificacionC.setDetalles(detallesC);
                competencia = new ArrayList<>();
                competencia.add(zonificacionC);

            }else if(valor == 5 || valor == 6 || valor == 7 ||
                    valor == 8 || valor == 9 || valor == 10 ||
                    valor == 11 || valor == 12){

                nivel = distancia(latLng, mds, "generadores");

                detalleG = new CrearZonificacion.Detalle(
                        String.valueOf(valor),
                        String.valueOf(market.getPosition().latitude),
                        String.valueOf(market.getPosition().longitude),
                        nivel
                );


                detallesG.add(detalleG);

                zonificacionG.setDetalles(detallesG);
                generadores = new ArrayList<>();
                generadores.add(zonificacionG);
            }

            if(generadores==null){

            }

            if(competencia==null){

            }

            if(detallesGene!=null){
                if(detallesGene.size()==0){
                    detallesG = new ArrayList<>();;

                    detalleG = new CrearZonificacion.Detalle(
                            "6"
                    );

                    detallesG.add(detalleG);
                    zonificacionG.setDetalles(detallesG);

                    generadores = new ArrayList<>();
                    generadores.add(zonificacionG);
                }
            }

            if(detallesCompe!=null){
                if(detallesCompe.size()==0){
                    detallesC = new ArrayList<>();
                    detalleC = new CrearZonificacion.Detalle(
                            "1"
                    );

                    detallesC.add(detalleC);
                    zonificacionC.setDetalles(detallesC);


                    competencia = new ArrayList<>();
                    competencia.add(zonificacionC);
                }
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

    }

    DatosConstruccions datosSitios;

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
                return "2";
            }else if(distanciaMetros>=500 && distanciaMetros<=1500){
                return "3";
            }else{
                return "1";
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
    int nivelId;

    DatosConstruccion datosConstruccion;
    Bitmap bit;
    List<DatosConstruccion.Nivele> niveles;
    List<DatosConstruccion.Detalle> detallesContruccion;
    List<DatosConstruccion.Detalle> detallesCondicion;
    DatosConstruccion.Detalle detalleConstruccion;

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
                    rb[i].setEnabled(false);
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


            if(listaSubfactores.getConstruccion()!=null){
                if(listaSubfactores.getConstruccion().get(0).getNivelid()==1){
                    rb[0].setChecked(true);
                }else if(listaSubfactores.getConstruccion().get(0).getNivelid()==2){
                    rb[1].setChecked(true);
                }else{
                    binding.linearLayout.setVisibility(View.VISIBLE);
                    rb[1].setChecked(true);
                }
            }
        }

    }

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
                rb[i].setEnabled(false);
                rg.addView(rb[i]);

                if(rb[i].getId()==datosConstruccion.getConstruccion().get(1).getNivelid()){
                    rb[i].setChecked(true);
                }

                if(rb[i].getId()==datosConstruccion.getConstruccion().get(1).getNivelid()){
                    rb[i].setChecked(true);
                }

                if(rb[i].getId()==datosConstruccion.getConstruccion().get(1).getNivelid()){
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

    public String getFechaHora(){
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        String dateforrow = dateFormat.format(cal1.getTime());
        return dateforrow;
    }

    public void datosConstruccion(String md, String usuarioId, DatosConstruccions datosConstruccions){

        if(datosConstruccions!=null){
            if(datosConstruccions.getConstruccion().get(0).getDetalles().size()>0){
                nivelId = 2;
            }else{
                nivelId = 1;
            }

            nivelIdCondicion = datosConstruccions.getConstruccion().get(1).getNivelid();

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
}
