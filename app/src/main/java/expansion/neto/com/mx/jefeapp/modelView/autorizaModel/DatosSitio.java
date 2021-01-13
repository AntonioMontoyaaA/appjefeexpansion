package expansion.neto.com.mx.jefeapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatosSitio {


    @SerializedName("datossitio")
    @Expose
    private List<datos> datossitio = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public List<datos> getDatossitio() {
        return datossitio;
    }

    public void setDatossitio(List<datos> datossitio) {
        this.datossitio = datossitio;
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


    public class datos{

        @SerializedName("tipoUbicacionMD")
        @Expose
        private String tipoUbicacionMD;
        @SerializedName("mdId")
        @Expose
        private String mdId;
        @SerializedName("latitud")
        @Expose
        private String latitud;
        @SerializedName("nombreSitio")
        @Expose
        private String nombreSitio;
        @SerializedName("puntuacion")
        @Expose
        private Integer puntuacion;
        @SerializedName("ciudad")
        @Expose
        private String ciudad;
        @SerializedName("codigoPostal")
        @Expose
        private String codigoPostal;
        @SerializedName("estado")
        @Expose
        private String estado;
        @SerializedName("municipio")
        @Expose
        private String municipio;
        @SerializedName("direccion")
        @Expose
        private String direccion;
        @SerializedName("longitud")
        @Expose
        private String longitud;
        @SerializedName("localidad")
        @Expose
        private String localidad;
        @SerializedName("detallesValidacion")
        @Expose
        private List<detallesValidacion> detallesValidacion = null;
        @SerializedName("fechaCreacion")
        @Expose
        private String fechaCreacion;
        @SerializedName("puntajeTotal")
        @Expose
        private String puntajeTotal;
        @SerializedName("categoria")
        @Expose
        private String categoria;
        @SerializedName("totalmd")
        @Expose
        private String totalmd;

        public String getTotalmd() {
            return totalmd;
        }

        public void setTotalmd(String totalmd) {
            this.totalmd = totalmd;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public String getPuntajeTotal() {
            return puntajeTotal;
        }

        public void setPuntajeTotal(String puntajeTotal) {
            this.puntajeTotal = puntajeTotal;
        }

        public String getFechaCreacion() {

            if(fechaCreacion!=null){
                fechaCreacion = fechaCreacion.substring(0,10);
            }

            return fechaCreacion;
        }

        public void setFechaCreacion(String fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
        }

        public List<detallesValidacion> getDetallesValidacion() {
            return detallesValidacion;
        }

        public void setDetallesValidacion(List<detallesValidacion> detallesValidacion) {
            this.detallesValidacion = detallesValidacion;
        }

        public String getTipoUbicacionMD() {
            return tipoUbicacionMD;
        }

        public void setTipoUbicacionMD(String tipoUbicacionMD) {
            this.tipoUbicacionMD = tipoUbicacionMD;
        }

        public String getMdId() {
            return mdId;
        }

        public void setMdId(String mdId) {
            this.mdId = mdId;
        }

        public String getLatitud() {
            return latitud;
        }

        public void setLatitud(String latitud) {
            this.latitud = latitud;
        }

        public String getNombreSitio() {
            return nombreSitio;
        }

        public void setNombreSitio(String nombreSitio) {
            this.nombreSitio = nombreSitio;
        }

        public Integer getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(Integer puntuacion) {
            this.puntuacion = puntuacion;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public String getCodigoPostal() {
            return codigoPostal;
        }

        public void setCodigoPostal(String codigoPostal) {
            this.codigoPostal = codigoPostal;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getMunicipio() {
            return municipio;
        }

        public void setMunicipio(String municipio) {
            this.municipio = municipio;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getLongitud() {
            return longitud;
        }

        public void setLongitud(String longitud) {
            this.longitud = longitud;
        }

        public String getLocalidad() {
            return localidad;
        }

        public void setLocalidad(String localidad) {
            this.localidad = localidad;
        }

    }


    public class detallesValidacion {

        @SerializedName("ESTATUSVALIDACION")
        @Expose
        private String ESTATUSVALIDACION;

        public String getESTATUSVALIDACION() {
            return ESTATUSVALIDACION;
        }

        public void setESTATUSVALIDACION(String ESTATUSVALIDACION) {
            this.ESTATUSVALIDACION = ESTATUSVALIDACION;
        }

    }

}
