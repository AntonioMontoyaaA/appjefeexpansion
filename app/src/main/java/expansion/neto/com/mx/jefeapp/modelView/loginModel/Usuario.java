package expansion.neto.com.mx.jefeapp.modelView.loginModel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.provider.loginProvider.ProviderLogin;
import expansion.neto.com.mx.jefeapp.ui.dashboard.ActivityMain;
import expansion.neto.com.mx.jefeapp.utils.Util;
import expansion.neto.com.mx.jefeapp.databinding.ActivityLoginBinding;


/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class Usuario {

    String usuario;
    String contra;

    Context context;
    ActivityLoginBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public Usuario(String usuario, String contra, Context context,
                   ActivityLoginBinding binding) {
        this.usuario = usuario;
        this.contra = contra;
        this.context = context;
        this.binding = binding;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }


    /**
     * Método que contiene el evento para el boton entrar, verifica si existe en la BD de tiendas neto y crea una variable de sesión
     * @param usuario
     * @param contra
     */
    public void onClickEntrar(String usuario, String contra) {
        blockUI();
        if(usuario.length() > 0 && contra.length() > 0){
            binding.entrar.setAlpha(0.45f);
            binding.entrar.setEnabled(false);
            Usuario user = new Usuario(usuario, contra, context, binding);
            String imei = Util.getImei(context);
            compruebaUsuario(user, imei);
        }else{
            binding.entrar.setEnabled(true);
            binding.entrar.setAlpha(1f);
            Snackbar snackbar = Snackbar.make(binding.container,
                    Html.fromHtml("<b><font color=\"#254581\">" +
                            context.getString(R.string.sizeContra) +
                            "</font></b>"), Snackbar.LENGTH_SHORT);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(context.getResources().getColor(R.color.snackBar)); // snackbar background color
            snackbar.show();
            unblockUI();
        }
    }
    ArrayList<Permiso> permisos;

    /**
     * Método que regresa el objeto usuario según se encuentre en la BD, también existe condición para saber si el usuario tiene acceso
     * @param usuario
     */
    public void compruebaUsuario(final Usuario usuario, String imei) {
        ProviderLogin.getInstance(context).compruebaLogin(imei, usuario,
                new ProviderLogin.ConsultaUsuario() {
                    @Override
                    public void resolve(UsuarioLogin usuarioLogin) {
                        if(usuarioLogin!=null){
                            if(usuarioLogin.getCodigo()==200){

                                permisos = new ArrayList<>();
                                for(int i=0;i<usuarioLogin.getPerfil().getPerfilesxusuario().get(0).getPermisos().size();i++){
                                    permisos.add(usuarioLogin.getPerfil().getPerfilesxusuario().get(0).getPermisos().get(i));
                                }

                                //perfilId = 1 > para que pueda entrar el gerente en app jefe y puesto 3
                                boolean permiso = false;
                                for(int i=0;i<usuarioLogin.getPerfil().getPerfilesxusuario().size();i++){
                                    if(usuarioLogin.getPerfil().getPerfilesxusuario().get(i).getPerfilid()==1){
                                        permiso = true;
                                    }
                                }

                                if(permiso){
                                    preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    Gson gson = new Gson();
                                    String json = gson.toJson(permisos);
                                    editor.putString("permisos", json);
                                    editor.commit();


                                    Intent main = new Intent(context, ActivityMain.class);
                                    context.startActivity(main);
                                    binding.entrar.setAlpha(1f);
                                    usuarioLogin.getPerfil().setImei(Util.getImei(context));
                                    sharedSave(context, usuario,
                                            usuarioLogin);
                                    unblockUI();

                                }else{

                                    Snackbar snackbar = Snackbar.make(binding.container,
                                            Html.fromHtml("<b><font color=\"#254581\">" +
                                                    "Este perfil no tiene permisos"+
                                                    "</font></b>"), Snackbar.LENGTH_SHORT);

                                    View snackBarView = snackbar.getView();
                                    snackBarView.setBackgroundColor(context.getResources().getColor(R.color.snackBar));
                                    snackbar.show();
                                    binding.entrar.setAlpha(1f);
                                    binding.entrar.setEnabled(true);
                                    unblockUI();

                                }
                            }else if(usuarioLogin.getCodigo()==1){
                                Snackbar snackbar = Snackbar.make(binding.container,
                                        Html.fromHtml("<b><font color=\"#254581\">" +
                                                usuarioLogin.getMensaje() +
                                                "</font></b>"), Snackbar.LENGTH_SHORT);

                                View snackBarView = snackbar.getView();
                                snackBarView.setBackgroundColor(context.getResources().getColor(R.color.snackBar));
                                snackbar.show();
                                binding.entrar.setAlpha(1f);
                                binding.entrar.setEnabled(true);
                                unblockUI();
                            }else{
                                Snackbar snackbar = Snackbar.make(binding.container,
                                        Html.fromHtml("<b><font color=\"#254581\">" +
                                                usuarioLogin.getMensaje() +
                                                "</font></b>"), Snackbar.LENGTH_SHORT);

                                View snackBarView = snackbar.getView();
                                snackBarView.setBackgroundColor(context.getResources().getColor(R.color.snackBar));
                                snackbar.show();
                                binding.entrar.setAlpha(1f);
                                binding.entrar.setEnabled(true);
                                unblockUI();
                            }

                        }else{
                            Snackbar snackbar = Snackbar.make(binding.container,
                                    Html.fromHtml("<b><font color=\"#254581\">" +
                                            context.getString(R.string.usuarioIncorrecto) +
                                            "</font></b>"), Snackbar.LENGTH_SHORT);

                            View snackBarView = snackbar.getView();
                            snackBarView.setBackgroundColor(context.getResources().getColor(R.color.snackBar));
                            snackbar.show();
                            unblockUI();
                        }
                    }
                    @Override
                    public void reject(Exception e) {}
                });
    }

    private void blockUI(){
        Util.addProgressBar(context, binding.container,binding.container.getChildCount()-1 );
    }

    private void unblockUI(){
        binding.container.removeViewAt(binding.container.getChildCount()-2);
    }

    public static void sharedSave(Context context, Usuario usuario, UsuarioLogin usuarioLogin){

        SharedPreferences preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("areaxpuesto", usuarioLogin.getPerfil().getAreasxpuesto().get(0).getAreaId().toString());
        editor.putString("usuario", usuario.getUsuario());
        editor.putString("contra", usuario.getContra());
        editor.putString("imei", usuarioLogin.getPerfil().getImei());
        editor.putString("numero", usuario.getContra());
        editor.putString("nombreCompleto", usuarioLogin.getPerfil().getNombre()+" "+ usuarioLogin.getPerfil().getApellidoP());
        editor.putString("telefono", usuarioLogin.getPerfil().getTelefono());
        editor.putString("correo", usuarioLogin.getPerfil().getCorreo());
        editor.apply();

    }

    public static UsuarioLogin.Perfil sharedGet(Context context){
        SharedPreferences preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        UsuarioLogin.Perfil usuario = new UsuarioLogin.Perfil();
        usuario.setNombre(preferences.getString("nombreCompleto", ""));
        usuario.setUsuario(preferences.getString("usuario", ""));
        usuario.setContra(preferences.getString("contra", ""));
        usuario.setImei(preferences.getString("imei", ""));
        usuario.setTelefono(preferences.getString("numero", ""));
        usuario.setNombre(preferences.getString("nombreCompleto", ""));
        usuario.setCorreo(preferences.getString("correo", ""));
        return usuario;

    }

}
