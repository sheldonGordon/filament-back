package fr.chatelain.filament.controller;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.Picture;
import fr.chatelain.filament.model.dto.PictureDto;
import fr.chatelain.filament.service.PictureService;
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
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("/pictures")
    public ResponseEntity<List<PictureDto>> getAllPicture(){
        List<PictureDto> listPictureDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try{
            pictureService.findAll().forEach(c -> listPictureDto.add(modelMapper.map(c, PictureDto.class)));
            return new ResponseEntity<>(listPictureDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/pictures/{id}")
    public ResponseEntity<PictureDto> getPicture(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();

        try{
            Optional<Picture> resultPicture = pictureService.getById(id);
            if(resultPicture.isPresent()){
                return new ResponseEntity<>(modelMapper.map(resultPicture.get(), PictureDto.class), HttpStatus.OK);
            }else {
                throw new RepositoryExeption("Aucun Picture trouvé pour l'id suivant :" + id);
            }
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new PictureDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/picture")
    public ResponseEntity<PictureDto> savePicture(@RequestBody PictureDto picture) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Picture entity = modelMapper.map(picture, Picture.class);
            pictureService.save(entity);
            return new ResponseEntity<>(picture, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new PictureDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/picture")
    public ResponseEntity<PictureDto> updatePicture(@RequestBody PictureDto picture) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            pictureService.update(modelMapper.map(picture, Picture.class));
            return new ResponseEntity<>(picture, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new PictureDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/pictures/{id}")
    public ResponseEntity<String> deletePicture(@PathVariable(name = "id") String id) {
        try {
            pictureService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
