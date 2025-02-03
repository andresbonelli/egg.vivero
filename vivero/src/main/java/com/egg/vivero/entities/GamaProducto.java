package com.egg.vivero.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GamaProducto {
    private int idGama;
    String gama;
    String descripcionTexto;
    String descripcionHtml;
    String imagen;
}
