/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 *
 * @author Imanol
 */
public class Image {
    private String path;
    private int width;
    private int height;
    private BinaryBitmap bitmap;
    private BitMatrix bitMatrix;
    
    public Image(String path, int width, int height) {
        this.path = path;
        this.width = width;
        this.height = height;
        bitmap = null;
        bitMatrix = null;
    }
    
    public Image(String path) {
        this.path = path;
        width = 0;
        height = 0;
        bitmap = null;
        bitMatrix = null;
    }
    
    public String getPath(){
        return path;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public BinaryBitmap getBitmap(){
        if(bitmap == null)
        {
            if(bitMatrix != null){
                computeBitmapFromBitMatrix();
            } else
                System.out.println("No bitmap loaded in "+path+"");
        }
        return bitmap;
    }
    
    public BitMatrix getBitMatrix(){
        return bitMatrix;
    }
    
    public void setBitmap(BinaryBitmap bm){
        bitmap = bm;
    }
    
    public void setBitMatrix(BitMatrix bm){
        bitMatrix = bm;
    }
    
    public boolean save(){
        try{
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", FileSystems.getDefault().getPath(path));
            return true;
        } catch(Exception e) {
            System.out.println("Exception thrown saving Image "+path);
        }
        return false;
    }
    
    public boolean save(String path)
    {
        this.path = path;
        return save();
    }
    
    public boolean load(){
        try{
            File qrCodeimage = new File(path);
            BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
            height = bufferedImage.getHeight();
            width = bufferedImage.getWidth();
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            bitmap = new BinaryBitmap(new HybridBinarizer(source));
            return true;
        } catch(IOException e)
        {
            System.out.println("Exception thrown loading Image "+path);
        }
        return false;
    }
    
    private void computeBitmapFromBitMatrix(){
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                bi.setRGB(x, y, bitMatrix.get(x,y) ? Color.BLACK.getRGB(): Color.WHITE.getRGB());
            }
        }
        LuminanceSource source = new BufferedImageLuminanceSource(bi);
        bitmap = new BinaryBitmap(new HybridBinarizer(source));
    }
}
