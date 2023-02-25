package com.erich.hibernate.dto;


import com.erich.hibernate.entity.Empresa;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpresaDto {

    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String direccion;

    public static EmpresaDto fromEntity(Empresa empresa){
        if(empresa == null){
            return null;
        }

        return EmpresaDto.builder().id(empresa.getId())
                .nombre(empresa.getNombre())
                .direccion(empresa.getDireccion())
                .build();
    }

    public static Empresa toEntity(EmpresaDto empresaDto){
        if(empresaDto == null){
            return null;
        }

        return Empresa.builder().id(empresaDto.getId())
                .nombre(empresaDto.getNombre())
                .direccion(empresaDto.getDireccion())
                .build();
    }
}
