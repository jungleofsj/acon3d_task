package CanTuna.acon3dtask.service;

import CanTuna.acon3dtask.domain.Item;
import CanTuna.acon3dtask.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    void createItem() {
        //given
        Item testItem = new Item();

        testItem.setNameKor("item1");
        testItem.setTextKor("textkor1");
        testItem.setCreator("creator1");
        testItem.setPrice(250000.0);

        //when
        Item savedItem = itemService.createItem(testItem);

        Optional<Item> findItem = itemService.findItemById(savedItem.getId());
        Item findItemResult = findItem.get();

        //then
        System.out.println(findItemResult.getId());
        System.out.println(findItemResult.getNameKor());
        System.out.println(findItemResult.getTextKor());
        Assertions.assertThat(testItem.getNameKor()).isEqualTo(findItemResult.getNameKor());


    }

    @Test
    void updateItem() {
        Item testItem = new Item();
        testItem.setNameKor("item1");
        testItem.setTextKor("textkor1");
        testItem.setCreator("creator1");
        testItem.setPrice(250000.0);
        Item savedItem = itemService.createItem(testItem);

        Item testUpdate = new Item();
        testUpdate.setNameEng("nameeng1");
        testUpdate.setNameChn("namechn1");
        testUpdate.setTextEng("texteng1");
        testUpdate.setTextChn("textchn1");
        testUpdate.setCommissionPct(15.0);
        testUpdate.setEditor("editor1");
        testUpdate.setApproved(true);

        Item updatedItem = itemService.updateItem(testItem.getId(),testUpdate).get();

        System.out.println("updated ::" + updatedItem.getNameEng());
        System.out.println("updated ::" + updatedItem.getNameChn());
        System.out.println("updated ::" + updatedItem.getTextEng());
        System.out.println("updated ::" + updatedItem.getTextChn());
        System.out.println("updated ::" + updatedItem.getCommissionPct());
        System.out.println("updated ::" + updatedItem.getEditor());
        System.out.println("updated ::" + updatedItem.getApproved());

    }

    @Test
    void findItemByName() {
    }

    @Test
    void findItemById() {
    }
}