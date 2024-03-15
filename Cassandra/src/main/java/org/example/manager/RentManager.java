package org.example.manager;

import org.example.entity.Client;
import org.example.entity.Court;
import org.example.entity.Rent;
import org.example.repository.ClientRepository;
import org.example.repository.CourtRepository;
import org.example.repository.RentRepository;

public class RentManager implements AutoCloseable {

    private final RentRepository rentRepository = new RentRepository();
    private final ClientRepository clientRepository = new ClientRepository();
    private final CourtRepository courtRepository = new CourtRepository();

    public boolean addRent(Rent rent) {
        Client client = clientRepository.find(rent.getClientId());
        Court court = courtRepository.find(rent.getCourtId());

        if (client != null && court != null) {
            if (!client.isHasRent() && !court.isRented()) {
                return rentRepository.add(rent);
            }
        }

        return false;
    }

    public boolean endRent(Rent rent) {
        Client client = clientRepository.find(rent.getClientId());
        Court court = courtRepository.find(rent.getCourtId());
        Rent cRent = rentRepository.find(rent.getId());

        if (client != null && court != null && cRent != null) {
            return rentRepository.update(rent);
        }

        return false;
    }

    @Override
    public void close() throws Exception {
        clientRepository.close();
        courtRepository.close();
        rentRepository.close();
    }
}
