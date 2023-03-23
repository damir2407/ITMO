package com.oasis.oasisapp.service;

import com.oasis.oasisapp.dto.ItemResponse;
import com.oasis.oasisapp.exception.ResourceNotFoundException;
import com.oasis.oasisapp.model.Game;
import com.oasis.oasisapp.model.Inventory;
import com.oasis.oasisapp.model.Item;
import com.oasis.oasisapp.model.User;
import com.oasis.oasisapp.repository.InventoryRepository;
import com.oasis.oasisapp.repository.ItemRepository;
import com.oasis.oasisapp.repository.ShopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    private final ItemRepository itemRepository;

    private final ShopRepository shopRepository;

    public InventoryService(InventoryRepository inventoryRepository, ItemRepository itemRepository, ShopRepository shopRepository) {
        this.inventoryRepository = inventoryRepository;
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
    }

    public int saveInventory(User user, Game game)  {
        List<Item> listOfItems = itemRepository.getAllItemsByGameId(game.getId()).orElseThrow(ResourceNotFoundException::new);
        Item commonItem = null;
        Item rareItem = null;
        Item legendaryItem = null;

        if (listOfItems != null) {
            for (Item item : listOfItems) {
                if (item != null) {
                    switch (item.getRarity()) {
                        case "Обычная" -> commonItem = item;
                        case "Редкая" -> rareItem = item;
                        case "Легендарная" -> legendaryItem = item;
                        default -> {
                        }
                    }
                }
            }
        }


        double rnd = Math.random();
        System.out.println(rnd);

        if (rnd > 0.80) {

            if (rnd > 0.95) {

                if (legendaryItem != null) {
                    inventoryRepository.save(new Inventory(user, legendaryItem, 0));
                    return 1;
                } else if (rareItem != null) {
                    inventoryRepository.save(new Inventory(user, rareItem, 0));
                    return 1;
                } else if (commonItem != null) {
                    inventoryRepository.save(new Inventory(user, commonItem, 0));
                    return 1;
                }

            } else if (rareItem != null) {
                inventoryRepository.save(new Inventory(user, rareItem, 0));
                return 1;
            } else if (commonItem != null) {
                inventoryRepository.save(new Inventory(user, commonItem, 0));
                return 1;
            }
        } else if (commonItem != null) {
            inventoryRepository.save(new Inventory(user, commonItem, 0));
            return 1;
        }
        return 0;
    }

    public List<ItemResponse> getAllItems(String userLogin, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Inventory> allInventory = inventoryRepository.findAllByUser(userLogin, pageable).orElseThrow(ResourceNotFoundException::new);
        List<Inventory> allInventoryData = allInventory.getContent();
        List<ItemResponse> itemResponses = new ArrayList<>();
        allInventoryData.forEach(inventory -> {
            itemResponses.add(new ItemResponse(inventory.getItem().getItemUrl(),
                    inventory.getItem().getName(), inventory.getItem().getGame().getName(),
                    inventory.getItem().getRarity(), inventory.getAmount(),
                    shopRepository.getGameShopPictureByGameName(inventory.getItem().getGame().getName())));
        });
        return itemResponses;
    }

    public void saveItemToCustomer(User user, Item item) {
        inventoryRepository.save(new Inventory(user, item, 0));
    }

}
