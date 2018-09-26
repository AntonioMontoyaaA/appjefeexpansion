package expansion.neto.com.mx.jefeapp.sorted.proceso;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.modelView.procesoModel.ChatProceso;

public class SentMessageHolder extends RecyclerView.ViewHolder {
    TextView messageText, timeText;

    private static final int TIPO_COMENTARIO_EVALUACIONES = 1;
    private static final int TIPO_COMENTARIO_GENERAL = 2;

    public SentMessageHolder(View itemView) {
        super(itemView);

        messageText = (TextView) itemView.findViewById(R.id.text_message_body);
        timeText = (TextView) itemView.findViewById(R.id.text_message_time);
    }

    public void bind(ChatProceso.MensajeChat message, Context context, int tipoComentario) {

        if(tipoComentario == TIPO_COMENTARIO_EVALUACIONES) {
            messageText.setText("En " + message.getNombrefactor() + ". " + message.getComentario());
        } else if(tipoComentario == TIPO_COMENTARIO_GENERAL) {
            messageText.setText(message.getComentario());
        }
        messageText.setText(message.getComentario());
        timeText.setText(message.getFecharegistro());

    }
}
