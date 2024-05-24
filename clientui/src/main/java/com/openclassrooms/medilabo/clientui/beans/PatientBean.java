package com.openclassrooms.medilabo.clientui.beans;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PatientBean {

    public int id;
    public String prenom;
    public String nom;
    public String dateNaissance;
    public String genre;
    public String adressePostale;
    public String numeroTelephone;

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
