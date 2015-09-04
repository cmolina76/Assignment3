package com.cejv679.web;

import com.cejv679.DBPersistance.models.NewPlayerModel;
import com.cejv679.DBPersistance.repository.impl.PlayerRepositoryImpl;
import com.cejv679.validators.PlayerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Carlos Molina.
 */
@Controller
@RequestMapping("/player")
@Transactional
public class PlayerPageController {

    @Autowired
    PlayerValidator playerValidator;

    @Autowired
    PlayerRepositoryImpl playerRepository;

    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(playerValidator);
    }


    @RequestMapping(value = "/create", method = GET)
    public String showRegistrationForm(Model model) {

        model.addAttribute("playerForm", new PlayerForm());

        return "createPlayer";
    }


    @RequestMapping(value = "/create", method = POST)
    @Transactional
    public String registerPlayer(@Valid PlayerForm playerForm, Errors errors, Model model) {

        //call validator manually if not set in @InitBinder
        //playerValidator.validate(playerForm, errors);

        if (errors.hasErrors()) {
            return "createPlayer";
        }

        NewPlayerModel newPlayer = new NewPlayerModel(
                null,
                playerForm.getFirstName(),
                playerForm.getLastName(),
                Integer.parseInt(playerForm.getAge()),
                playerForm.getCurrency(),
                Double.parseDouble(playerForm.getSalary()),
                playerForm.getCountry(),
                playerForm.getPosition(),
                Integer.parseInt(playerForm.getGoals()),
                Integer.parseInt(playerForm.getBookings()));
        NewPlayerModel saved = playerRepository.save(newPlayer);

        model.addAttribute("playerForm", playerForm);

        return "playerPage";
        //return "redirect:/soccer/player/" + playerForm.getFirstName() + "." + playerForm.getLastName();
    }

    @RequestMapping(value="/lastname/{name}", method=GET)
    public String showPlayerProfile(@PathVariable String name, Model model) {

        NewPlayerModel player = playerRepository.findByLastName(name);

        PlayerForm playerForm = new PlayerForm();

        playerForm.setFirstName(player.getFirstname());
        playerForm.setLastName(player.getLastname());
        playerForm.setAge(Integer.toString(player.getAge()));
        playerForm.setCurrency(player.getCurrency());
        playerForm.setSalary(Double.toString(player.getSalary()));
        playerForm.setCountry(player.getCountry());
        playerForm.setPosition(player.getPosition());
        playerForm.setGoals(Integer.toString(player.getGoals()));
        playerForm.setBookings(Integer.toString(player.getBookings()));

        model.addAttribute("playerForm", playerForm);
        return "player";
    }

}
