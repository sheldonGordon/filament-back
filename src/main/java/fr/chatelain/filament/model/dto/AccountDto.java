package fr.chatelain.filament.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDto {
    private String id;
    private String firstName;
    private String lastName;
    private List<PrinterDto> listPrinter;
}
