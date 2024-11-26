package com.example.mycatagog;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;

public class CircleTransformImg implements Transformation {

    // usa transform de la interfaz Transformation de Picasso
    @Override
    public Bitmap transform(Bitmap source) {

        // busca el tamaño del lado más pequeño de la img
        int size = Math.min(source.getWidth(), source.getHeight());

        // calcula las coordenadas para recortar la imagen desde el centro
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        // crea un bitmap cuadrado cortando la imagen
        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);

        // si el bitmap recortado no es el mismo que el original, liberar la memoria del bitmap original
        if (squaredBitmap != source) {
            source.recycle();
        }

        // crea bitmap nuevo vacío con el tamaño del que va a ser el círculo
        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        // canvas para dibujar en el bitmap
        Canvas canvas = new Canvas(bitmap);

        // paint para definir las características de dibujo
        Paint paint = new Paint();
        paint.setAntiAlias(true);  // suavizado de bordes
        paint.setFilterBitmap(true);  // mejorar la calidad del bitmap al ser escalado
        paint.setDither(true);  // calidad de los colores

        // rectángulo con el tamaño del círculo
        Rect rect = new Rect(0, 0, size, size);

        // fondo transparente para el canvas
        canvas.drawARGB(0, 0, 0, 0);

        // dibujar un círculo en el canvas
        canvas.drawCircle(size / 2, size / 2, size / 2, paint);

        // configurar el modo de mezcla para que la imagen se dibuje dentro del círculo
        paint.setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));

        // dibujar la imagen recortada dentro del círculo
        canvas.drawBitmap(squaredBitmap, rect, rect, paint);

        // libera memoria
        squaredBitmap.recycle();

        return bitmap;
    }

    // devuelve una clave única para esta transformación para que Picasso sepa cuándo usarla
    @Override
    public String key() {
        return "circle";
    }
}
