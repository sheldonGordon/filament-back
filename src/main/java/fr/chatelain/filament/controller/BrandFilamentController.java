package fr.chatelain.filament.controller;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.Account;
import fr.chatelain.filament.model.BrandFilament;
import fr.chatelain.filament.model.dto.AccountDto;
import fr.chatelain.filament.model.dto.BrandFilamentDto;
import fr.chatelain.filament.service.AccountService;
import fr.chatelain.filament.service.BrandFilamentService;
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
public class BrandFilamentController {

    @Autowired
    private BrandFilamentService brandFilamentService;

    @GetMapping("/brandFilaments")
    public ResponseEntity<List<BrandFilamentDto>> getAllBrandFilament(){
        List<BrandFilamentDto> listBrandFilamentDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try{
            brandFilamentService.findAll().forEach(c -> listBrandFilamentDto.add(modelMapper.map(c, BrandFilamentDto.class)));
            return new ResponseEntity<>(listBrandFilamentDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/brandFilaments/{id}")
    public ResponseEntity<BrandFilamentDto> getBrandFilament(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();

        try{
            Optional<BrandFilament> resultBrandFilament = brandFilamentService.getById(id);
            if(resultBrandFilament.isPresent()){
                return new ResponseEntity<>(modelMapper.map(resultBrandFilament.get(), BrandFilamentDto.class), HttpStatus.OK);
            }else {
                throw new RepositoryExeption("Aucun Brand trouvé pour l'id suivant :" + id);
            }
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new BrandFilamentDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/brandFilament")
    public ResponseEntity<BrandFilamentDto> saveBrandFilament(@RequestBody BrandFilamentDto brandFilament) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            BrandFilament entity = modelMapper.map(brandFilament, BrandFilament.class);
            brandFilamentService.save(entity);
            return new ResponseEntity<>(brandFilament, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new BrandFilamentDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/brandFilament")
    public ResponseEntity<BrandFilamentDto> updateBrandFilament(@RequestBody BrandFilamentDto brandFilament) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            brandFilamentService.update(modelMapper.map(brandFilament, BrandFilament.class));
            return new ResponseEntity<>(brandFilament, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new BrandFilamentDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/brandFilaments/{id}")
    public ResponseEntity<String> deleteBrandFilament(@PathVariable(name = "id") String id) {
        try {
            brandFilamentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
