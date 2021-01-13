package expansion.neto.com.mx.jefeapp.modelView.crearModel;

public class GuardarFinalizar {

    String mdId;
    String usuarioId;
    String tipoguardado;
    String totalpuntos;

    public GuardarFinalizar(String mdId, String usuarioId, String tipoguardado, String totalpuntos) {
        this.mdId = mdId;
        this.usuarioId = usuarioId;
        this.tipoguardado = tipoguardado;
        this.totalpuntos = totalpuntos;
    }

    public String getMdId() {
        return mdId;
    }

    public void setMdId(String mdId) {
        this.mdId = mdId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTipoguardado() {
        return tipoguardado;
    }

    public void setTipoguardado(String tipoguardado) {
        this.tipoguardado = tipoguardado;
    }

    public String getTotalpuntos() {
        return totalpuntos;
    }

    public void setTotalpuntos(String totalpuntos) {
        this.totalpuntos = totalpuntos;
    }

}
