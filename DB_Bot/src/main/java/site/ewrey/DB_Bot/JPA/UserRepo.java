package site.ewrey.DB_Bot.JPA;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<ScopeEntity, Long> {
//    boolean existsByChatId(Long chatId);
//    ScopeEntity findByChatId(Long chatId);


    List<ScopeEntity> findAll();



}
