package fr.chatelain.filament.controller;

import fr.chatelain.filament.exceptions.RepositoryExeption;
import fr.chatelain.filament.model.Account;
import fr.chatelain.filament.model.dto.AccountDto;
import fr.chatelain.filament.service.AccountService;
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
@ResponseBody
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAllAccount(){
        List<AccountDto> listAccountDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try{
            accountService.findAll().forEach(c -> listAccountDto.add(modelMapper.map(c, AccountDto.class)));
            return new ResponseEntity<>(listAccountDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();

        try{
            Optional<Account> resultAccount = accountService.getById(id);
            if(resultAccount.isPresent()){
                return new ResponseEntity<>(modelMapper.map(resultAccount.get(), AccountDto.class), HttpStatus.OK);
            }else{
                throw new RepositoryExeption("Aucun Account trouvé pour l'id suivant :"+id);
            }
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new AccountDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/account")
    public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto account) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Account entity = modelMapper.map(account, Account.class);
            accountService.save(entity);
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new AccountDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/account")
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto account) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            accountService.update(modelMapper.map(account, Account.class));
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new AccountDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable(name = "id") String id) {
        try {
            accountService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
