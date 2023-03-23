package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.LastGamesResponse;
import com.oasis.oasisapp.dto.LibraryDataResponse;
import com.oasis.oasisapp.exception.ResourceNotFoundException;
import com.oasis.oasisapp.model.*;
import com.oasis.oasisapp.repository.LibraryRepository;
import com.oasis.oasisapp.repository.ShopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    private final ShopRepository shopRepository;

    public LibraryService(LibraryRepository libraryRepository, ShopRepository shopRepository) {
        this.libraryRepository = libraryRepository;
        this.shopRepository = shopRepository;
    }


    public void saveGameInUserLibrary(User user, Game game) {
        Library library = new Library(user, game);
        libraryRepository.save(library);
    }


    public List<LibraryDataResponse> getEntriesByGameName(String gameName, String userLogin)   {
        List<Library> allLibraryEntries;
        if (gameName == null)
            allLibraryEntries = libraryRepository.getAllFromLibraryByUserLogin(userLogin).orElseThrow(ResourceNotFoundException::new);
        else
            allLibraryEntries = libraryRepository.getAllFromLibraryByGameNameFilter(gameName, userLogin).orElseThrow(ResourceNotFoundException::new);

        List<LibraryDataResponse> libraryDataResponses = new ArrayList<>();

        allLibraryEntries.forEach(library -> {

            String game_name_to_send = library.getGame().getName();
            String game_picture_to_send = libraryRepository.getGameShopPictureByGameName(library.getGame().getName());
            String last_run_date_to_send;
            if (library.getLastRunDate() == null) last_run_date_to_send = "";
            else last_run_date_to_send = library.getLastRunDate().toString().substring(0,
                    library.getLastRunDate().toString().indexOf('.'));


            libraryDataResponses.add(new LibraryDataResponse(game_name_to_send,
                    game_picture_to_send,
                    last_run_date_to_send, library.getGame().getGameUrl()));

        });

        return libraryDataResponses;
    }


    public void enterTheGame(String login, String gameName)   {
        libraryRepository.enterInGame(login, gameName);
    }

    public Integer getCountOfUserGames(String userLogin) {
        return libraryRepository.getCountOfGames(userLogin);
    }


    public List<LastGamesResponse> getUserLastGames(String userLogin)   {
        List<Library> allLibraryEntries = libraryRepository.getAllFromLibraryByUserLogin(userLogin)
                .orElseThrow(ResourceNotFoundException::new);
        List<LastGamesResponse> lastGamesResponses = new ArrayList<>();
        allLibraryEntries.forEach(library -> {
            if (library.getLastRunDate() != null && lastGamesResponses.size() < 3) {
                lastGamesResponses.add(new LastGamesResponse
                        (shopRepository.getGameShopPictureByGameName(library.getGame().getName()),
                                library.getGame().getName(),
                                library.getLastRunDate().toString().substring(0,
                                        library.getLastRunDate().toString().indexOf('.'))));
            }
        });
        lastGamesResponses.sort(Comparator.comparing(LastGamesResponse::getLastEnterDate));
        Collections.reverse(lastGamesResponses);
        return lastGamesResponses;
    }

    public List<Game> getUserGames(String login, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Library> libraryList;
        Page<Library> libraryData = libraryRepository.findByUser(login, pageable).orElseThrow(ResourceNotFoundException::new);
        List<Game> userGames = new ArrayList<>();
        libraryList = libraryData.getContent();
        libraryList.forEach(library -> {
            userGames.add(library.getGame());
        });
        return userGames;
    }

}
