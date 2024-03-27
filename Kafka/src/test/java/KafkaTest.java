import org.example.entityKafka.RentKfk;
import org.example.kafka.Producer;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class KafkaTest {

    @Test
    public void kafkaTest() {

        RentKfk rent = new RentKfk(9, "Lodz_rental", 99,999, new Date(), null);

        try(Producer producer = new Producer()) {


            producer.initProducer();

            producer.createTopic();

            assertDoesNotThrow(() -> producer.sendRentToKafka(rent));
        } catch (Exception e) {

        }

    }


}
