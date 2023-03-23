package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.SlotsResponse;
import com.oasis.oasisapp.exception.ResourceNotFoundException;
import com.oasis.oasisapp.model.Item;
import com.oasis.oasisapp.model.Market;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.repository.MarketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MarketService {

    private final MarketRepository marketRepository;

    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public void save(User user, Item item, Double price)   {
        marketRepository.save(new Market(user, item, price));
    }

    public Market getMarketById(Long id)   {
        return marketRepository.getMarketById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<SlotsResponse> getEntriesByGameNameAndItemName(String itemName, String gameName)   {
        List<Market> marketList;
        if (itemName.isEmpty())
            marketList = marketRepository.getAllSlots().orElseThrow(ResourceNotFoundException::new);
        else
            marketList = marketRepository.getAllFromMarketByItemNameFilter(itemName).orElseThrow(ResourceNotFoundException::new);

        List<SlotsResponse> slotsResponses = new ArrayList<>();

        if (!gameName.isEmpty()) {
            marketList.forEach(market -> {
                if (market.getItem().getGame().getName().equals(gameName))
                    slotsResponses.add(new SlotsResponse(market.getItem().getName(),
                            market.getItem().getItemUrl(),
                            market.getItem().getGame().getName(),
                            market.getItem().getRarity(),
                            market.getPrice(), market.getId()));
            });
        } else marketList.forEach(market -> {
            slotsResponses.add(new SlotsResponse(market.getItem().getName(),
                    market.getItem().getItemUrl(),
                    market.getItem().getGame().getName(),
                    market.getItem().getRarity(),
                    market.getPrice(), market.getId()));
        });

        slotsResponses.sort(Comparator.comparing(SlotsResponse::getPrice));

        return slotsResponses;

    }

    public void deleteSlot(Long marketId) {
        marketRepository.deleteMarketById(marketId);
    }

}
