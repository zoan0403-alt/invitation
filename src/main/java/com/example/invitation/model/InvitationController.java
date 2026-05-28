package com.example.invitation.model; 

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.invitation.service.InvitationService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InvitationController {

    private final InvitationService invitationService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generate(
            @RequestParam String nom,
            @RequestParam(required = false, defaultValue = "pdf") String format
    ) throws Exception {

        if ("png".equalsIgnoreCase(format)) {
            byte[] image = invitationService.generatePng(nom);
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=invitation.png"
                    )
                    .contentType(MediaType.IMAGE_PNG)
                    .body(image);
        } else {
            byte[] pdf = invitationService.generatePdf(nom);
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=invitation.pdf"
                    )
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        }
    }
}