package expansion.neto.com.mx.jefeapp.modelView.crearModel;

public class CrearDatosSuperficie {

    private String usuarioid;
    private String mdId;
    private String frente;
    private String fondo;
    private String imgEntorno2Id;
    private String imgEntorno1Id;
    private String imgFrenteId;
    private String latitud;
    private String longitud;
    private String numTelefono;
    private String versionApp;
    private String fechaFrente;
    private String fechaEntorno1;
    private String fechaEntorno2;
    private String esquina;
    private String imgPredial;
    private String fechaPredial;

    public CrearDatosSuperficie (){}

    public CrearDatosSuperficie(String esquina, String usuarioid, String mdId,
                                String frente, String fondo, String imgEntorno2Id,
                                String imgEntorno1Id, String imgFrenteId, String latitud,
                                String longitud, String numTelefono, String versionApp,
                                String fechaFrente, String fechaEntorno1, String fechaEntorno2,
                                String imgPredial, String fechaPredial) {
        this.esquina = esquina;
        this.usuarioid = usuarioid;
        this.mdId = mdId;
        this.frente = frente;
        this.fondo = fondo;
        this.imgEntorno2Id = imgEntorno2Id;
        this.imgEntorno1Id = imgEntorno1Id;
        this.imgFrenteId = imgFrenteId;
        this.latitud = latitud;
        this.longitud = longitud;
        this.numTelefono = numTelefono;
        this.versionApp = versionApp;
        this.fechaFrente = fechaFrente;
        this.fechaEntorno1 = fechaEntorno1;
        this.fechaEntorno2 = fechaEntorno2;
        this.imgPredial = imgPredial;
        this.fechaPredial = fechaPredial;

    }

    public String getImgPredial() {
        return imgPredial;
    }

    public void setImgPredial(String imgPredial) {
        this.imgPredial = imgPredial;
    }

    public String getFechaPredial() {
        return fechaPredial;
    }

    public void setFechaPredial(String fechaPredial) {
        this.fechaPredial = fechaPredial;
    }

    public String getEsquina() {
        return esquina;
    }

    public void setEsquina(String esquina) {
        this.esquina = esquina;
    }

    public String getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(String usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getMdId() {
        return mdId;
    }

    public void setMdId(String mdId) {
        this.mdId = mdId;
    }

    public String getFrente() {
        return frente;
    }

    public void setFrente(String frente) {
        this.frente = frente;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public String getImgEntorno2Id() {
        return imgEntorno2Id;
    }

    public void setImgEntorno2Id(String imgEntorno2Id) {
        this.imgEntorno2Id = imgEntorno2Id;
    }

    public String getImgEntorno1Id() {
        return imgEntorno1Id;
    }

    public void setImgEntorno1Id(String imgEntorno1Id) {
        this.imgEntorno1Id = imgEntorno1Id;
    }

    public String getImgFrenteId() {
        return imgFrenteId;
    }

    public void setImgFrenteId(String imgFrenteId) {
        this.imgFrenteId = imgFrenteId;
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

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getVersionApp() {
        return versionApp;
    }

    public void setVersionApp(String versionApp) {
        this.versionApp = versionApp;
    }

    public String getFechaFrente() {
        return fechaFrente;
    }

    public void setFechaFrente(String fechaFrente) {
        this.fechaFrente = fechaFrente;
    }

    public String getFechaEntorno1() {
        return fechaEntorno1;
    }

    public void setFechaEntorno1(String fechaEntorno1) {
        this.fechaEntorno1 = fechaEntorno1;
    }

    public String getFechaEntorno2() {
        return fechaEntorno2;
    }

    public void setFechaEntorno2(String fechaEntorno2) {
        this.fechaEntorno2 = fechaEntorno2;
    }
}
