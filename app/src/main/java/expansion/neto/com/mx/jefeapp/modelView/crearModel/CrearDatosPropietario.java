package expansion.neto.com.mx.jefeapp.modelView.crearModel;

public class CrearDatosPropietario {

    private String usuarioId;
    private String mdId;
    private String nombrePropietario;
    private String apaternoPropietario;
    private String amaternoPropietario;
    private String telefono;
    private String email;
    private String latitud;
    private String longitud;


    public CrearDatosPropietario(){}

    public CrearDatosPropietario(String usuarioId, String mdId, String apaternoPropietario, String nombrePropietario , String amaternoPropietario, String telefono, String email, String latitud, String longitud) {
        this.usuarioId = usuarioId;
        this.mdId = mdId;
        this.nombrePropietario = nombrePropietario;
        this.apaternoPropietario = apaternoPropietario;
        this.amaternoPropietario = amaternoPropietario;
        this.telefono = telefono;
        this.email = email;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getMdId() {
        return mdId;
    }

    public void setMdId(String mdId) {
        this.mdId = mdId;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getApaternoPropietario() {
        return apaternoPropietario;
    }

    public void setApaternoPropietario(String apaternoPropietario) {
        this.apaternoPropietario = apaternoPropietario;
    }

    public String getAmaternoPropietario() {
        return amaternoPropietario;
    }

    public void setAmaternoPropietario(String amaternoPropietario) {
        this.amaternoPropietario = amaternoPropietario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
