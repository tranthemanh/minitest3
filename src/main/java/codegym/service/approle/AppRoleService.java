package codegym.service.approle;

import codegym.model.AppRole;
import codegym.repository.IAppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppRoleService implements IAppRoleService {
    @Autowired
    private IAppRoleRepository appRoleRepository;



    @Override
    public Iterable<AppRole> findAll() {
        return appRoleRepository.findAll();
    }

    @Override
    public void save(AppRole appRole) {
        appRoleRepository.save(appRole);
    }

    @Override
    public Optional<AppRole> findById(Long id) {
        return appRoleRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        appRoleRepository.deleteById(id);
    }
}
