package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.GuidesResponse;
import com.oasis.oasisapp.exception.ResourceNotFoundException;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.Guide;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.repository.GuideRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class GuideService {
    private final ShopService shopService;

    private final GuideRepository guideRepository;

    public GuideService(GuideRepository guideRepository, ShopService shopService) {
        this.guideRepository = guideRepository;
        this.shopService = shopService;
    }

    public void saveGuide(User user, Game game, String guideText) {
        guideRepository.save(new Guide(user, game, guideText, new Timestamp(System.currentTimeMillis())));
    }

    public List<Guide> getAllGuides(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return guideRepository.findAll(pageable).getContent();
    }

    public List<Guide> getAllGuidesByGameName(String gameName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Guide> guidePage = guideRepository.findByGameName(gameName, pageable).orElseThrow(ResourceNotFoundException::new);
        return guidePage.getContent();
    }


    public List<GuidesResponse> getGuidesByCondition(String selectedGame, int page, int size) {
        List<Guide> guideList;
        if (selectedGame.isEmpty()) guideList = getAllGuides(page, size);
        else guideList = getAllGuidesByGameName(selectedGame, page, size);

        List<GuidesResponse> guidesResponses = new ArrayList<>();
        guideList.forEach(guide -> {
            guidesResponses.add(new GuidesResponse(guide.getUser().getLogin(),
                    guide.getSendDate().toString().substring(0,
                            guide.getSendDate().toString().indexOf('.')),
                    guide.getGuideText(),
                    shopService.getGamePicture(guide.getGame().getName())
                    , guide.getGame().getName()));
        });
        guidesResponses.sort(Comparator.comparing(GuidesResponse::getSendDate));
        Collections.reverse(guidesResponses);
        return guidesResponses;
    }
}
