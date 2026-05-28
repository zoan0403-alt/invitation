package com.example.invitation.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class InvitationService {

    public BufferedImage generateImage(String nom) throws Exception {
        BufferedImage image = ImageIO.read(
                new ClassPathResource("static/template.png").getInputStream()
        );

        Graphics2D g = image.createGraphics();

        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g.setColor(Color.BLACK);

        Font font = new Font(
                "Serif",
                Font.BOLD,
                40
        );

        g.setFont(font);

        int x = 665;
        int y = 990;

        g.drawString(nom, x, y);

        g.dispose();

        return image;
    }

    public byte[] generatePng(String nom) throws Exception {
        BufferedImage image = generateImage(nom);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }

    public byte[] generatePdf(String nom) throws Exception {
        BufferedImage image = generateImage(nom);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        // Define document page size matching the image dimensions exactly (in PDF points)
        float width = image.getWidth();
        float height = image.getHeight();
        
        Rectangle pageSize = new Rectangle(width, height);
        Document document = new Document(pageSize, 0, 0, 0, 0); // Zero margins
        
        PdfWriter.getInstance(document, baos);
        document.open();
        
        // Convert AWT BufferedImage to OpenPDF Image
        Image pdfImage = Image.getInstance(image, null);
        pdfImage.setAbsolutePosition(0, 0);
        document.add(pdfImage);
        
        document.close();
        return baos.toByteArray();
    }
}