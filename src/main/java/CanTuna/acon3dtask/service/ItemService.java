package CanTuna.acon3dtask.service;

import CanTuna.acon3dtask.domain.Currency;
import CanTuna.acon3dtask.domain.Item;
import CanTuna.acon3dtask.domain.User;
import CanTuna.acon3dtask.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ItemService {
    private final ItemRepository itemRepository;
    //private final CurrencyRepository exRateRepository;


    private final Long USER_TYPE_CREATOR = 0L;
    private final Long USER_TYPE_EDITOR = 1L;
    private final Long USER_TYPE_CUSTOMER = 2L;
/*
    private final Integer CURRENCY_TYPE_KOR = 0;
    private final Integer CURRENCY_TYPE_ENG = 1;
    private final Integer CURRENCY_TYPE_CHN = 2;
*/

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {

        Item result = itemRepository.createItem(item);

        return result;
    }

    public Optional<Item> updateItem(Long targetId, Item updateItem) {
        // 에디터 권한 조회
        //authEditor(editUser);

        Optional<Item> result = itemRepository.editItem(targetId, updateItem);

        return result;
    }

    public  Optional<Item> findItemByName(String itemName) {
        Optional<Item> findItem = itemRepository.findItemByName(itemName);
        Item temp = findItem.get();
        System.out.println("-----------------------");
        System.out.println("Serviceitemfind id ::: " +  temp.getId());
        System.out.println("Serviceitemfind namekor ::: " +  temp.getNameKor());
        System.out.println("Serviceitemfind creator ::: " +  temp.getCreator() );
        System.out.println("Serviceitemfind approved ::: " +  temp.getApproved());
        System.out.println("Serviceitemfind approved ::: " +  temp.getTextKor());
        System.out.println("-----------------------");
        return findItem.stream().findAny();
    }

    public  Optional<Item> findItemById(Long itemId) {
        Optional<Item> findItem = itemRepository.findItemById(itemId);
        return findItem.stream().findAny();
    }

    public List<Item> viewItem() {
        List<Item>itemList = itemRepository.findAllItem();
        return itemList;
    }
    public List<Item> viewApprovedItem() {
        List<Item>itemList = itemRepository.findApprovedItem();
        return itemList;
    }

    public List<Item> viewNonApprovedItem() {
        List<Item>itemList = itemRepository.findNonApprovedItem();
        return itemList;
    }

    public Long buyItem(Item buyItem, Long userId, Long quantity, String currencyType) {
        if (quantity == 0L) {
            return -1L;
        } else {
            itemRepository.purchaseLog(buyItem, userId, quantity, currencyType);
        }

        return buyItem.getId();
    }

    //TEST 용 Authorization 코드
    private void authCreator(User user) {
        if (user.getType() != USER_TYPE_CREATOR)
            throw new IllegalStateException("상품 작성 권한이 없습니다.");
    }
    private void authEditor(User user) {
        if (user.getType() != USER_TYPE_EDITOR)
            throw new IllegalStateException("상품 등록/수정 권한이 없습니다.");
    }

}
