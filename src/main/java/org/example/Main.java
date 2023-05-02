package org.example;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;


import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {

        Tesseract tesseract = new Tesseract();


        try {

            tesseract.setDatapath("C:/Users/Tolunay/Desktop/te/tessdata");

            //pdf reading
            PDDocument newDoc = PDDocument.load(new File("src/main/resources/yds.pdf"));
            PDFRenderer pdfRenderer = new PDFRenderer(newDoc);

            int totalPages = newDoc.getNumberOfPages();
            int imageDPI = 300;

            FileWriter file = new FileWriter("output.docx");
            BufferedWriter bufferedWriter = new BufferedWriter(file);
            for(int i = 0; i < totalPages;i++){
                BufferedImage tempPageBimg = pdfRenderer.renderImageWithDPI(i,imageDPI, ImageType.RGB);
                String textPdf
                        = tesseract.doOCR(tempPageBimg);
                bufferedWriter.write(textPdf);
                bufferedWriter.newLine();
                System.out.println("[Start] Page " + (i+1) + " ===========================");
                System.out.println(textPdf);
                System.out.println("[End] Page " + (i+1) + " ===========================");
            }

            bufferedWriter.close();






            // the path of your tess data folder
            // inside the extracted file
//            String text
//                    = tesseract.doOCR(new File("src/main/resources/mage2.jpeg"));
//
//            // path of your image file
//
//            text = text.trim();
//            if(text != null){
//                System.out.print(text);
//            }

        }
        catch (TesseractException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}