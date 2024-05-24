package com.openclassrooms.medilabo.clientui.beans;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {

    private int id;
    private String prenom;
    private String nom;
    private String dateNaissance;
    private String genre;
    private String adressePostale;
    private String numeroTelephone;

    @Override
    public String toString()
    {
        return "ProductBean{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", genre='" + genre + '\'' +
                ", adressePostale='" + adressePostale + '\'' +
                ", prix=" + numeroTelephone +
                '}';
    }

}
