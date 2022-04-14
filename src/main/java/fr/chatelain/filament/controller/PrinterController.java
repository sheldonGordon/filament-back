package fr.chatelain.filament.controller;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.Picture;
import fr.chatelain.filament.model.Printer;
import fr.chatelain.filament.model.dto.PictureDto;
import fr.chatelain.filament.model.dto.PrinterDto;
import fr.chatelain.filament.service.PrinterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PrinterController {

    @Autowired
    private PrinterService printerService;

    @GetMapping("/printers")
    public ResponseEntity<List<PrinterDto>> getAllPicture(){
        List<PrinterDto> listPrinterDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try{
            printerService.findAll().forEach(c -> listPrinterDto.add(modelMapper.map(c, PrinterDto.class)));
            return new ResponseEntity<>(listPrinterDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/printers/{id}")
    public ResponseEntity<PrinterDto> getPicture(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();

        try{
            Optional<Printer> resultPicture = printerService.getById(id);
            if(resultPicture.isPresent()){
                return new ResponseEntity<>(modelMapper.map(resultPicture.get(), PrinterDto.class), HttpStatus.OK);
            }else {
                throw new RepositoryExeption("Aucun Picture trouvé pour l'id suivant :" + id);
            }
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new PrinterDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/printer")
    public ResponseEntity<PrinterDto> savePicture(@RequestBody PrinterDto printer) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Printer entity = modelMapper.map(printer, Printer.class);
            printerService.save(entity);
            return new ResponseEntity<>(printer, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new PrinterDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/printer")
    public ResponseEntity<PrinterDto> updatePicture(@RequestBody PrinterDto printer) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            printerService.update(modelMapper.map(printer, Printer.class));
            return new ResponseEntity<>(printer, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new PrinterDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/printers/{id}")
    public ResponseEntity<String> deletePicture(@PathVariable(name = "id") String id) {
        try {
            printerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
