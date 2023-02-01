package solo.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solo.board.entity.Address;
import solo.board.entity.Delivery;
import solo.board.repository.DeliveryRepository;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public Delivery createDelivery(Address address){
        Delivery delivery = Delivery.createDelivery(address);
        deliveryRepository.save(delivery);
        return delivery;
    }
}
