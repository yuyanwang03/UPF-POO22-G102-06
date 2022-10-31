

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.Result;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
//import com.google.zxing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Imanol
 */
public class QRLib {

    public static BitMatrix generateQRCodeImage(String text, int width, int height){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try{
            bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        }catch(WriterException e){
            System.out.println("Exception thrown generating QR Code Image");
        }
        return bitMatrix;
    }
    
     public static String decodeQRCodeImage(BinaryBitmap bitmap) {
        if(bitmap == null) return "";
        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }

}
