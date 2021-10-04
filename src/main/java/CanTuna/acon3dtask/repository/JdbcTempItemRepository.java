package CanTuna.acon3dtask.repository;

import CanTuna.acon3dtask.domain.Currency;
import CanTuna.acon3dtask.domain.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.*;

public class JdbcTempItemRepository implements ItemRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTempItemRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Item createItem(Item item) {

        /*jdbcTemplate.update("INSERT INTO ITEM (name_kor = ?, text_kor = ?,price = ?,reg_date = ?, updt_date = ?) ", itemRowMapper(),
                                    item.getItemNameKor(), item.getItemTextKor(),item.getItemPrice(), getDate(), getDate());
        List<Item> result = jdbcTemplate.query("select * from ITEM where name_kor = ?", itemRowMapper(), item.getItemNameKor());
        item.setItemId(result.stream().findAny().get().getItemId());
        return item;*/

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("ITEM").usingColumns("name_kor","text_kor","price","creator", "reg_date", "updt_date" ).usingGeneratedKeyColumns("id");

        Map<String, Object> param = new HashMap<>();

        param.put("name_kor", item.getNameKor());
        param.put("text_kor", item.getTextKor());
        param.put("price", item.getPrice());
        param.put("creator", item.getCreator());

        param.put("reg_date", getDate());
        param.put("updt_date", getDate());


        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(param));

        item.setId(key.longValue());

        return item;
    }

    @Override
    public Optional<Item> editItem(Long itemId, Item updateItem) {
        jdbcTemplate.update("update ITEM " +
                        "set name_kor = ?,  name_eng = ?,   name_chn = ?, " +
                        "text_kor = ?,      text_eng = ?,   text_chn = ?, " +
                        "editor = ?,        commission = ?, approved = ?, " +
                        "updt_date = ? " +
                        "where id = ?",
                updateItem.getNameKor(),    updateItem.getNameEng(),        updateItem.getNameChn(),
                updateItem.getTextKor(),    updateItem.getTextEng(),        updateItem.getTextChn(),
                updateItem.getEditor(),     updateItem.getCommissionPct(),  updateItem.getApproved(),
                getDate(),                  itemId);

        List<Item> targetResult = jdbcTemplate.query("select * from ITEM where id = ?", itemRowMapper(), itemId);

        return targetResult.stream().findAny();
    }

    @Override
    public Optional<Item> findItemById(Long itemId) {
        List<Item> result = jdbcTemplate.query("select * from ITEM where id = ?", itemRowMapper(), itemId);
        return result.stream().findAny();
    }

    @Override
    public Optional<Item> findItemByName(String itemName) {
        List<Item> result = jdbcTemplate.query("select * from ITEM where name_kor = ?", itemRowMapper(), itemName);

        Item resultItem  = result.stream().findAny().get();
        System.out.println("findItembyName 결과 ");
        System.out.println("findItembyName id ::: " +  resultItem.getId());
        System.out.println("findItembyName namekor ::: " +  resultItem.getNameKor());
        System.out.println("findItembyName creator ::: " +  resultItem.getCreator() );
        System.out.println("findItembyName approved ::: " +  resultItem.getApproved());
        System.out.println("findItembyName approved ::: " +  resultItem.getTextKor());
        System.out.println("-----------------------");
        return result.stream().findAny();
    }

    @Override
    public List<Item> findItemByCreator(String creatorName) {
        List<Item> result = jdbcTemplate.query("select * from ITEM where creator = ?", itemRowMapper(), creatorName);
        return result;
    }

    @Override
    public List<Item> findNonApprovedItem() {
        List<Item> result = jdbcTemplate.query("select * from ITEM where approved = ?", itemRowMapper(), true);
        return result;
    }

    @Override
    public List<Item> findApprovedItem() {
        List<Item> result = jdbcTemplate.query("select * from ITEM where approved = ?", itemRowMapper(), false);
        return result;
    }

    @Override
    public List<Item> findAllItem() {
        return jdbcTemplate.query("select * from ITEM", itemRowMapper());
    }

    @Override
    public Item purchaseLog(Item item, Long userId, Long quantity, String currencyType) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("PURCHASED_LIST").usingGeneratedKeyColumns("id");

        Map<String, Object> param = new HashMap<>();

        List<Currency> exRate = jdbcTemplate.query("select rate from EXCHANGE_RATE WHERE country = ? ", currencyRowMapper(), currencyType);

        Double rate = exRate.stream().findAny().get().getRate();
        param.put("item_id", item.getId());
        param.put("customer_id", userId);
        param.put("quantity", quantity);
        param.put("price", item.getPrice());
        //수량 및 환율을 통해 가격 계산
        param.put("totalprice", item.getPrice() * quantity * rate);
        param.put("currency", currencyType);

        param.put("reg_date", getDate());
        param.put("updt_date", getDate());

        return item;
    }

    private RowMapper<Item> itemRowMapper() {
        return(rs, row) ->{
            Item item = new Item();
            item.setId(rs.getLong("id"));
            item.setNameKor(rs.getString("name_kor"));
            item.setNameEng(rs.getString("name_eng"));
            item.setNameChn(rs.getString("name_chn"));
            item.setCreator(rs.getString("creator"));
            item.setEditor(rs.getString("editor"));
            item.setPrice(rs.getDouble("price"));
            item.setCommissionPct(rs.getDouble("commission"));
            item.setApproved(rs.getBoolean("approved"));
            item.setTextKor(rs.getString("text_kor"));
            item.setTextEng(rs.getString("text_eng"));
            item.setTextChn(rs.getString("text_chn"));
            //System.out.println(item.getItemNameKor());
            //System.out.println(item.getItemApproved());
            return item;

        };
    }

    private RowMapper<Currency> currencyRowMapper() {
        return(rs, row) ->{
            Currency currency = new Currency();
            currency.setRate(rs.getDouble("rate"));
//            rate.setCountry(rs.getString("country"));
//            System.out.println("IN CurrencyRowMapper");
            return currency;
        };
    }

    //DB 상에 등록일, 수정일을 남기기 위한 getDate함수
    private Long getDate(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        Calendar calender = Calendar.getInstance();
        String strDate = sdfDate.format(calender.getTime());

        return Long.parseLong(strDate);
    }

}
