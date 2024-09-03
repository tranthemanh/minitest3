package codegym.service.appuser;

import codegym.model.AppUser;
import codegym.service.IGenerateService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends IGenerateService<AppUser>, UserDetailsService {
}
