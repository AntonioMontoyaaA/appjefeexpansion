package expansion.neto.com.mx.jefeapp.modelView.crearModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PropietarioBusqueda {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("listaPropietarios")
    @Expose
    private List<ListaPropietario> listaPropietarios = null;

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

    public List<ListaPropietario> getListaPropietarios() {
        return listaPropietarios;
    }

    public void setListaPropietarios(List<ListaPropietario> listaPropietarios) {
        this.listaPropietarios = listaPropietarios;
    }


    public class ListaPropietario {

        @SerializedName("propietarioId")
        @Expose
        private Integer propietarioId;
        @SerializedName("nombrePropietario")
        @Expose
        private String nombrePropietario;
        @SerializedName("aPaternoPropietario")
        @Expose
        private String aPaternoPropietario;
        @SerializedName("mail")
        @Expose
        private String mail;
        @SerializedName("telefono")
        @Expose
        private String telefono;
        @SerializedName("aMaternoPropietario")
        @Expose
        private String aMaternoPropietario;

        @SerializedName("yaRenta")
        @Expose
        private String yaRenta;

        public String getYaRenta() {
            return yaRenta;
        }

        public void setYaRenta(String yaRenta) {
            this.yaRenta = yaRenta;
        }

        public Integer getPropietarioId() {
            return propietarioId;
        }

        public void setPropietarioId(Integer propietarioId) {
            this.propietarioId = propietarioId;
        }

        public String getNombrePropietario() {
            return nombrePropietario;
        }

        public void setNombrePropietario(String nombrePropietario) {
            this.nombrePropietario = nombrePropietario;
        }

        public String getAPaternoPropietario() {
            return aPaternoPropietario;
        }

        public void setAPaternoPropietario(String aPaternoPropietario) {
            this.aPaternoPropietario = aPaternoPropietario;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getAMaternoPropietario() {
            return aMaternoPropietario;
        }

        public void setAMaternoPropietario(String aMaternoPropietario) {
            this.aMaternoPropietario = aMaternoPropietario;
        }
    }
}
