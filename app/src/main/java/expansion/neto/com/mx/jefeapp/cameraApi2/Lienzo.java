package expansion.neto.com.mx.jefeapp.cameraApi2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import expansion.neto.com.mx.jefeapp.R;

public class Lienzo extends View {

    private static Path drawPath;                              // trazo del que vamos pintando
    private static Paint drawPaint, canvasPaint;        // es como el pincel,
    private static int paintColor = 0xFFFA7E0A;                //color inicial
    private static Canvas drawCanvas;                   //lienzo, fondo
    private static Bitmap canvasBitmap;                 //tipo de archivo par apoder guardarlo

    float touchX;
    float touchY;

    private float iniciotouchX ;
    private float iniciotouchY ;

    int anchoNuevo, altoNuevo;

    //marquesina
    Bitmap marco;
    float xMarco;
    int xMArcoInt;
    Bitmap marco2;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpDrawing();
        marco = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.marquesina);
        marco = Bitmap.createScaledBitmap(marco, 600, 150, true);
    }

    private static void setUpDrawing() {
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(7);
        drawPaint.setStyle(Paint.Style.STROKE); //pintar solo bordes, trazos
        drawPaint.setStrokeJoin(Paint.Join.ROUND); //pintura sera redondeada
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //cambioTamanio(canvasBitmap, w,h,0.9f);
        //super.onSizeChanged(anchoNuevo, altoNuevo, anchoNuevo, altoNuevo);
        //Toast.makeText(getContext(), "w: "+w +" h:"+ h, Toast.LENGTH_SHORT).show();
        super.onSizeChanged(w, h, oldw, oldh);
        //canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        //drawCanvas = new Canvas(canvasBitmap);
        //Toast.makeText(getContext(), "ancho: "+ canvasBitmap.getWidth()+ " alto:"+canvasBitmap.getHeight(), Toast.LENGTH_SHORT).show();
        canvasBitmap = Bitmap.createScaledBitmap(canvasBitmap, 1100, 1100, true);
        Bitmap mutableBitmap = canvasBitmap.copy(Bitmap.Config.ARGB_8888, true);
        drawCanvas = new Canvas(mutableBitmap);
        drawCanvas.setBitmap(mutableBitmap);
    }

    public void cambioTamanio(Bitmap b, int w, int h, float porcentajeABajar){
        float w2, h2;
        anchoNuevo = w;
        altoNuevo = h;
        if(b.getWidth() > w || b.getHeight() > h){
            w2 = b.getWidth() * porcentajeABajar;
            h2 = b.getHeight() * porcentajeABajar;
            if(w2 > w || h2  > h){
                cambioTamanio(b, w,  h, (porcentajeABajar - 0.1f));
            }else{
                anchoNuevo = Math.round(w2);
                altoNuevo = Math.round(h2);
            }
        }else{
            anchoNuevo = w;
            altoNuevo = h;
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = event.getX();
        touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                iniciotouchX  = touchX;
                iniciotouchY = touchY;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                xMarco = touchX - iniciotouchX ;
                xMArcoInt = Math.round(xMarco);
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
        canvas.drawRect(iniciotouchX, iniciotouchY, touchX, touchY,drawPaint);

        xMArcoInt = xMArcoInt <1 ? 1: xMArcoInt;
        marco2 = Bitmap.createScaledBitmap(marco, xMArcoInt, 150, true);
        canvas.drawBitmap(marco2, iniciotouchX, iniciotouchY, drawPaint);
    }

    //actualiza color
    public void setColor(String newColor) {
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    public static void setBitmap( Bitmap bitmap){
        canvasBitmap = bitmap;

        setUpDrawing();
    }
}
