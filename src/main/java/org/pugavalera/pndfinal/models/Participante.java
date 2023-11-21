package org.pugavalera.pndfinal.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor	
public class Participante {
	private int participanteId;
	private String nmbrs;
	private String apllds;
	private String dni;
	private String correoE;
	private String telf;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fechaRegistro;
	private Cuidador cuidador;
}
