package org.example.manager;

import org.example.entity.Client;
import org.example.entity.Court;
import org.example.entity.Rent;
import org.example.repository.ClientRepository;
import org.example.repository.CourtRepository;
import org.example.repository.Repository;

import java.time.LocalDateTime;

public class RentManager extends Manager<Rent> {

    private final ClientRepository clientRepository = new ClientRepository();
    private final CourtRepository courtRepository = new CourtRepository();

    public RentManager(Repository<Rent> repository) {
        super(repository);
    }

    @Override
    public boolean add(Rent rent) {
        Client client = clientRepository.find(rent.getClient().getId());
        Court court = courtRepository.find(rent.getCourt().getId());

        if (!client.isHasRent() && !court.isRented()) {
                rent.getClient().setHasRent(true);
                rent.getCourt().setRented(true);
                return super.add(rent);
        } else {
            return false;
        }
    }

    public boolean leave(Rent rent) {
        rent.setEndTime(LocalDateTime.now());
        rent.getClient().setHasRent(false);
        rent.getCourt().setRented(false);
        return super.update(rent);
    }

    @Override
    public void close() throws Exception {
        clientRepository.close();
        courtRepository.close();
        super.close();
    }
}
