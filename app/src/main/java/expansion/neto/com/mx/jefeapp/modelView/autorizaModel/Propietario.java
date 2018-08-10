package expansion.neto.com.mx.jefeapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Propietario {

    @SerializedName("aPaternoPropietario")
    @Expose
    private String aPaternoPropietario;
    @SerializedName("aMaternoPropietario")
    @Expose
    private String aMaternoPropietario;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("propietarioId")
    @Expose
    private Integer propietarioId;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("nombrePropietario")
    @Expose
    private String nombrePropietario;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("rentaMasLocales")
    @Expose
    private int rentaMasLocales;

    public String getaPaternoPropietario() {
        return aPaternoPropietario;
    }

    public void setaPaternoPropietario(String aPaternoPropietario) {
        this.aPaternoPropietario = aPaternoPropietario;
    }

    public String getaMaternoPropietario() {
        return aMaternoPropietario;
    }

    public void setaMaternoPropietario(String aMaternoPropietario) {
        this.aMaternoPropietario = aMaternoPropietario;
    }

    public int getRentaMasLocales() {
        return rentaMasLocales;
    }

    public void setRentaMasLocales(int rentaMasLocales) {
        this.rentaMasLocales = rentaMasLocales;
    }

    public String getAPaternoPropietario() {
        return aPaternoPropietario;
    }

    public void setAPaternoPropietario(String aPaternoPropietario) {
        this.aPaternoPropietario = aPaternoPropietario;
    }

    public String getAMaternoPropietario() {
        return aMaternoPropietario;
    }

    public void setAMaternoPropietario(String aMaternoPropietario) {
        this.aMaternoPropietario = aMaternoPropietario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(Integer propietarioId) {
        this.propietarioId = propietarioId;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
