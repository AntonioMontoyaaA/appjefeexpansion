package expansion.neto.com.mx.jefeapp.modelView.crearModel;

public class CrearDatosSitio {


    private String usuarioId;
    private String nombreSitio;
    private String codigoPostal;
    private String direccion;
    private String estado;
    private String municipio;
    private String ciudad;
    private String latitud;
    private String longitud;
    private String tipoubicacion;
    private String numtelefono;
    private String versionapp;
    private String pais;
    private String mdId;
    private String radio;

    public CrearDatosSitio(){}

    public CrearDatosSitio(String usuarioId, String nombreSitio, String codigoPostal,
                           String direccion, String estado, String municipio,
                           String ciudad, String latitud, String longitud,
                           String tipoubicacion, String numtelefono, String versionapp,
                           String pais, String mdId, String radio) {
        this.usuarioId = usuarioId;
        this.nombreSitio = nombreSitio;
        this.codigoPostal = codigoPostal;
        this.direccion = direccion;
        this.estado = estado;
        this.municipio = municipio;
        this.ciudad = ciudad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipoubicacion = tipoubicacion;
        this.numtelefono = numtelefono;
        this.versionapp = versionapp;
        this.pais = pais;
        this.radio = radio;
        this.mdId = mdId;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getMdId() {
        return mdId;
    }

    public void setMdId(String mdId) {
        this.mdId = mdId;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTipoubicacion() {
        return tipoubicacion;
    }

    public void setTipoubicacion(String tipoubicacion) {
        this.tipoubicacion = tipoubicacion;
    }

    public String getNumtelefono() {
        return numtelefono;
    }

    public void setNumtelefono(String numtelefono) {
        this.numtelefono = numtelefono;
    }

    public String getVersionapp() {
        return versionapp;
    }

    public void setVersionapp(String versionapp) {
        this.versionapp = versionapp;
    }
}
