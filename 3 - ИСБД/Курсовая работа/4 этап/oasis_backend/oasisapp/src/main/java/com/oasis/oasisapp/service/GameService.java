package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.GameInfoResponse;
import com.oasis.oasisapp.exception.*;
import com.oasis.oasisapp.model.*;
import com.oasis.oasisapp.repository.GameRepository;
import com.oasis.oasisapp.repository.GenreRepository;
import com.oasis.oasisapp.repository.ShopRepository;
import com.oasis.oasisapp.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GameService {


    private final GameRepository gameRepository;

    private final GenreRepository genreRepository;

    private final UserRepository userRepository;

    private final ShopRepository shopRepository;

    public GameService(GameRepository gameRepository, GenreRepository genreRepository, UserRepository userRepository, ShopRepository shopRepository) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
    }

    public Game saveGame(String name, Set<String> reqGenres, String devLogin, String gameUrl) {

        if (gameRepository.getByNameInAnyRegister(name).isPresent() || gameRepository.getByGameUrlInAnyRegister(gameUrl).isPresent())
            throw new ResourceAlreadyExist("Данная игра уже опубликована!");


        Set<Genre> genres = new HashSet<>();

        reqGenres.forEach(r -> {
            Genre genre = genreRepository
                    .findByName(r)
                    .orElseThrow(ResourceNotFoundException::new);
            genres.add(genre);
        });

        Game game = new Game(name, LocalDate.now());
        game.setGenres(genres);

        User dev = userRepository.findByLogin(devLogin).orElseThrow(ResourceNotFoundException::new);
        game.setUser(dev);
        game.setGameUrl(gameUrl);
        gameRepository.save(game);

        return game;
    }

    public GameInfoResponse getGameInfo(String gameName)   {
        Shop shop = shopRepository.getShopByGameName(gameName).orElseThrow(ResourceNotFoundException::new);
        String game_name_resp = shop.getGame().getName();
        Double price_resp = shop.getPrice();
        List<String> genres_resp = shop.getGame().getGenres().stream()
                .map(Genre::getName).toList();
        String development_date_resp = shop.getGame().getDevelopmentDate().toString();
        String dev_login_resp = shop.getGame().getUser().getLogin();
        String game_description_resp = shop.getDescription();
        String picture_cover_resp = shop.getPictureCover();
        String picture_gameplay_1_resp = shop.getPictureGamePlay1();
        String picture_gameplay_2_resp = shop.getPictureGamePlay2();
        String picture_gameplay_3_resp = shop.getPictureGamePlay3();

        return new GameInfoResponse(game_name_resp, price_resp, genres_resp,
                development_date_resp, dev_login_resp, game_description_resp,
                picture_cover_resp, picture_gameplay_1_resp,
                picture_gameplay_2_resp, picture_gameplay_3_resp);

    }

    public Game getGameByName(String gameName)  {
        return gameRepository.getGameByName(gameName).orElseThrow(ResourceNotFoundException::new);
    }

    public boolean checkGameOnExist(String gameName) {
        return gameRepository.existsByName(gameName);
    }

    public List<Game> getAllGames(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return gameRepository.findAll(pageable).getContent();
    }


}
