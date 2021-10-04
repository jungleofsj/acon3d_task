package CanTuna.acon3dtask.controller;

import CanTuna.acon3dtask.domain.Item;
import CanTuna.acon3dtask.domain.User;
import CanTuna.acon3dtask.service.ItemService;
import CanTuna.acon3dtask.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

    private final ItemService itemService;
    private final UserService userService;

    public ItemController(ItemService itemService,UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }


    @ResponseBody
    @GetMapping("/items/all")
    public List<Item> list(Model model) {

        List<Item> items = itemService.viewItem();

        return items;
    }
    //test input page
    @GetMapping("/items/new")
    public String createForm() {
        return "/createItemForm";
    }

    @PostMapping("/items/new")
    public ResponseEntity createItem(@RequestHeader("Authorization") Long userType, @RequestBody Item item) {

        if (userType != User.USER_TYPE_CREATOR) {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
        Item resultItem = itemService.createItem(item);

        return new ResponseEntity(resultItem, HttpStatus.OK);

    }
    //test input page
    @GetMapping("/items/update")
    public String updateForm() {
        return "/updateItemForm";
    }

    @PutMapping("/items/{id}")
    public ResponseEntity updateItem(@RequestHeader("Authorization") Long userType, @RequestBody Item item, @PathVariable("id") Long id) {
        if (userType != User.USER_TYPE_EDITOR) {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }

        item.setApproved(true);

        Optional<Item> itemResult = itemService.updateItem(id, item);

        if (itemResult.isEmpty()) {
            return new ResponseEntity("UnExist productID", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(itemResult, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/items/approved")
    public ResponseEntity approvedItem(@RequestHeader("Authorization") Long userType, @RequestParam("isApproved") Boolean isApproved) {

        List<Item> items = null;

        if (isApproved == true) {
            items = itemService.viewApprovedItem();
        } else {
            items = itemService.viewNonApprovedItem();
        }
        if (userType == User.USER_TYPE_CUSTOMER) {
            return new ResponseEntity("열람권한이 없습니다", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(items, HttpStatus.OK);

    }

    @PostMapping("/items/purchase")
    public ResponseEntity purchaseItem(@RequestHeader("Authorization")Long UserType, @RequestBody PurchaseForm form) {

        Optional<Item> item = itemService.findItemById(form.getProductId());

        if(item.isEmpty()) {
            return new ResponseEntity("없는 상품입니다.", HttpStatus.BAD_REQUEST);
        }
        Item resultItem = itemService.findItemById(form.getProductId()).stream().findAny().get();

        itemService.buyItem(resultItem, form.getCustomerId(), form.getQuantity(), form.getCurrency());

        return new ResponseEntity(resultItem.getId(), HttpStatus.OK);
    }

}

