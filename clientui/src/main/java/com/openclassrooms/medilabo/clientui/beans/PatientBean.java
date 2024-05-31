package com.openclassrooms.medilabo.clientui.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
        return "PatientBean{" +
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
