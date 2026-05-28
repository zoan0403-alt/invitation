package com.example.invitation.repository;

import com.example.invitation.model.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {
    
    // Cette ligne magique permet à Spring de créer automatiquement 
    // la requête SQL pour chercher un invité par son nom !
    Optional<Invite> findByNomComplet(String nomComplet);
}