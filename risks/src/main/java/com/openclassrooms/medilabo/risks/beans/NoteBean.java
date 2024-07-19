package com.openclassrooms.medilabo.risks.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoteBean {

    private String id;
    private int patId;
    private String patient;
    private String note;

    @Override
    public String toString()
    {
        return "NoteBean{" +
                "id=" + id +
                ", patId='" + patId + '\'' +
                ", patient='" + patient + '\'' +
                ", note='" + note +
                '}';
    }
}

