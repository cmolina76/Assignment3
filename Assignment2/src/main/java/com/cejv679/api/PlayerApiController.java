package com.cejv679.api;

/**
 * Created by Carlos Molina.
 */

        import com.cejv679.DBPersistance.models.NewPlayerModel;
        import com.cejv679.DBPersistance.repository.PlayerNotFoundException;
        import com.cejv679.DBPersistance.repository.impl.PlayerRepositoryImpl;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.ResponseBody;
        import org.springframework.web.bind.annotation.ResponseStatus;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.util.UriComponentsBuilder;

        import java.net.URI;
        import java.util.List;

@RestController
@RequestMapping("/playerAPI")
public class PlayerApiController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private PlayerRepositoryImpl playerRepository;

    @Autowired
    public PlayerApiController(PlayerRepositoryImpl playerRepository) {
        this.playerRepository = playerRepository;
    }

    @RequestMapping(method=RequestMethod.GET, produces="application/json")
    public List<NewPlayerModel> players() {
        return playerRepository.findAll();
    }


    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
    public NewPlayerModel playerById(@PathVariable Long id) {
        return playerRepository.findOne(id);
    }

    @RequestMapping(method=RequestMethod.POST, consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NewPlayerModel> savePlayer(@RequestBody NewPlayerModel player, UriComponentsBuilder ucb) {
        NewPlayerModel saved = playerRepository.save(player);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/players/")
                .path(String.valueOf(saved.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        ResponseEntity<NewPlayerModel> responseEntity = new ResponseEntity<NewPlayerModel>(saved, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody Error spittleNotFound(PlayerNotFoundException e) {
        long playerId = e.getId();
        return new Error(4, "Player [" + playerId + "] not found");
    }

}