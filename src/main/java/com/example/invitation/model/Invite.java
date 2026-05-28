package com.example.invitation.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "invite") // Le nom de la table dans la base de données
@Data // Lombok génère automatiquement les Getters et Setters
@NoArgsConstructor
@AllArgsConstructor
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Numéro unique de l'invité

    @Column(name = "nom_complet", nullable = false)
    private String nomComplet;

    // Statut : "En attente" (4 par défaut), "Confirmé", "Annulé"
    @Column(name = "id_type", nullable = false)
    private int idType = 4; 

    @Column(name = "statut_presence", length = 50)
    private String statutPresence = "En attente";

    @Column(name = "notification_admin", nullable = false)
    private boolean notificationAdmin = false; // Drapeau pour les alertes admin
}