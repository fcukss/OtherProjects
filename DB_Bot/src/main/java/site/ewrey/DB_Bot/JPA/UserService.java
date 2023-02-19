package site.ewrey.DB_Bot.JPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    public void save(ScopeEntity entity) {
        repo.save(entity);
    }


    public List<ScopeEntity> listOfAll() {
        return repo.findAll();
    }

    public List<ScopeEntity> listOfFreeScopes() {
        List<ScopeEntity> listFree = new ArrayList<>();
        List<ScopeEntity> list = repo.findAll();

        for (ScopeEntity e : list) {
            if (e.getStatus().equals("free"))
                listFree.add(e);
        }
        return listFree;
    }
}
