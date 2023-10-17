package adam.machima.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CardController {
    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostMapping(path = "/api/v1/card")
    public String saveCard(
            @RequestParam("image") MultipartFile image,
            @RequestParam("lat_str") String lat_str,
            @RequestParam("lon_str") String lon_str
    ) {
        try {
            String contentType = image.getContentType();
            byte[] fileContent = image.getBytes();
            Card card = new Card(
                    contentType,
                    lat_str,
                    lon_str,
                    fileContent
            );
            cardRepository.save(card);

            return "File saved successfully";
        } catch (Exception e) {
            return "File not saved";
        }
    }

    @GetMapping(path = "/api/v1/card")
    public Iterable<Card> getCards() {
        return cardRepository.findAll();
    }
}
