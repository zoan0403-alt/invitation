package com.example.invitation.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;

@Service
public class InvitationService {

    public byte[] generate(String nom) throws Exception {

        BufferedImage image = ImageIO.read(
                new ClassPathResource(
                        "static/template.png"
                ).getInputStream()
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

        //FontMetrics metrics = g.getFontMetrics();

        // int x = (
        //         image.getWidth()
        //                 - metrics.stringWidth(nom)+250
        // ) / 2;
        int x=665;
        int y = 990;

        g.drawString(nom, x, y);

        g.dispose();

        ByteArrayOutputStream baos =
                new ByteArrayOutputStream();

        ImageIO.write(image, "png", baos);

        return baos.toByteArray();
    }
}