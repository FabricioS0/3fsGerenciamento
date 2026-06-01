package com.tresfs.gerenciamento.dto;

import com.tresfs.gerenciamento.entity.Documento;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String tipo;
    private String numero;
    private String setorNome;
    private String usuarioCadastroNome;
    private LocalDateTime dataCriacao;
    
    public DocumentoResponseDTO(Documento f){
        this.setId(f.getId());
        this.setTitulo(f.getTitulo());
        this.setDescricao(f.getDescricao());
        this.setTipo(f.getTipo());
        this.setNumero(f.getNumero());
        this.setSetorNome(f.getSetor().getNome());
        this.setUsuarioCadastroNome(f.getUsuarioCadastro().getNome());
        this.setDataCriacao(f.getDataCadastro());
    }
}
