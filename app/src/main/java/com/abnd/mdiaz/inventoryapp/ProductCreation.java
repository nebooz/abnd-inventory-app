package com.abnd.mdiaz.inventoryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductCreation extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;

    private ImageView productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_creation);

        productImage = (ImageView) findViewById(R.id.add_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            productImage.setImageBitmap(ImageTools.imageProcess(mCurrentPhotoPath));

        }
    }

    public void addProduct(View view) {

        DatabaseHelper dbHelp = new DatabaseHelper(this);

        EditText productNameView = (EditText) findViewById(R.id.edit_pro_name);
        EditText productPriceView = (EditText) findViewById(R.id.edit_pro_price);
        EditText productQuantityView = (EditText) findViewById(R.id.edit_pro_quantity);
        EditText productSupplierView = (EditText) findViewById(R.id.edit_pro_supplier);

        String productName = productNameView.getText().toString();
        float productPrice = Float.valueOf(productPriceView.getText().toString());
        int productQuantity = Integer.valueOf(productQuantityView.getText().toString());
        String productSupplier = productSupplierView.getText().toString();

        dbHelp.addProduct(
                -1,
                productName,
                productPrice,
                productQuantity,
                productSupplier,
                mCurrentPhotoPath);

        this.finish();
    }

    public void addImage(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
