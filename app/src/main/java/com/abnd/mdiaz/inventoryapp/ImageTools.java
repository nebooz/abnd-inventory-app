package com.abnd.mdiaz.inventoryapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;

import java.io.IOException;

/**
 * Created by neboo on 21-Aug-16.
 */
public final class ImageTools {

    private ImageTools() {

    }

    public static Bitmap imageProcess(String imagePath) {

        String subPath = imagePath.substring(5);

        Bitmap inputBitmap = BitmapFactory.decodeFile(subPath);
        Bitmap processedBitmap;

        ExifInterface ei = null;
        try {
            ei = new ExifInterface(subPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                processedBitmap = rotateImage(inputBitmap, 90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                processedBitmap = rotateImage(inputBitmap, 180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                processedBitmap = rotateImage(inputBitmap, 270);
                break;
            case ExifInterface.ORIENTATION_NORMAL:
            default:
                processedBitmap = inputBitmap;
                break;
        }

        return processedBitmap;

    }

    private static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap tempBitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
        return ThumbnailUtils.extractThumbnail(tempBitmap, 200, 200);
    }
}
