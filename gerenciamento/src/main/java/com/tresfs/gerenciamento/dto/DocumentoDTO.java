package com.tresfs.gerenciamento.dto;

import com.tresfs.gerenciamento.entity.Documento;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String tipo;
    private String numero;
    private Long setorId;
    private Long usuarioCadastroId;

    public DocumentoDTO(Documento d) {
        this.id = d.getId();
        this.titulo = d.getTitulo();
        this.descricao = d.getDescricao();
        this.tipo = d.getTipo();
        this.numero = d.getNumero();
        this.setorId = d.getSetor().getId();
        this.usuarioCadastroId = d.getUsuarioCadastro().getId();
    }
}
