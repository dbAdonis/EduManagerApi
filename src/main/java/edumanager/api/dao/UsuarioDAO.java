package edumanager.api.dao;

import edumanager.api.model.Usuario;

public interface UsuarioDAO {
    Usuario buscarPorUsername(String username);
}
