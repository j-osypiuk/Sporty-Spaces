package org.example.repository;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import org.example.dao.ClientDao;
import org.example.entity.Client;
import org.example.mapper.ClientMapper;
import org.example.mapper.ClientMapperBuilder;

public class ClientRepository extends AbstractCassandraRepository implements Repository<Client> {

    private ClientDao clientDao;

    public ClientRepository() {
        super();
        ClientMapper clientMapper = new ClientMapperBuilder(super.getSession()).build();
        clientDao = clientMapper.clientDao(CqlIdentifier.fromCql("rent_a_court"), "clients");
    }

    public boolean add(Client client) {
        try {
            clientDao.save(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(int id) {
        try {
            clientDao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Client find(int id) {
        try {
            return clientDao.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean update(Client client) {
        try {
            clientDao.save(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
