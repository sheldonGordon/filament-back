package fr.chatelain.filament.controller;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.BrandFilament;
import fr.chatelain.filament.model.Filament;
import fr.chatelain.filament.model.dto.BrandFilamentDto;
import fr.chatelain.filament.model.dto.FilamentDto;
import fr.chatelain.filament.service.FilamentService;
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
public class FilamentController {

    @Autowired
    private FilamentService filamentService;

    @GetMapping("/filaments")
    public ResponseEntity<List<FilamentDto>> getAllFilament(){
        List<FilamentDto> listFilamentDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try{
            filamentService.findAll().forEach(c -> listFilamentDto.add(modelMapper.map(c, FilamentDto.class)));
            return new ResponseEntity<>(listFilamentDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/filaments/{id}")
    public ResponseEntity<FilamentDto> getFilament(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();

        try{
            Optional<Filament> resultFilament = filamentService.getById(id);
            if(resultFilament.isPresent()){
                return new ResponseEntity<>(modelMapper.map(resultFilament.get(), FilamentDto.class), HttpStatus.OK);
            }else {
                throw new RepositoryExeption("Aucun Filament trouvé pour l'id suivant :" + id);
            }
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new FilamentDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/filament")
    public ResponseEntity<FilamentDto> saveFilament(@RequestBody FilamentDto filament) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Filament entity = modelMapper.map(filament, Filament.class);
            filamentService.save(entity);
            return new ResponseEntity<>(filament, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new FilamentDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/filament")
    public ResponseEntity<FilamentDto> updateFilament(@RequestBody FilamentDto filament) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            filamentService.update(modelMapper.map(filament, Filament.class));
            return new ResponseEntity<>(filament, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new FilamentDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/filaments/{id}")
    public ResponseEntity<String> deleteFilament(@PathVariable(name = "id") String id) {
        try {
            filamentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
