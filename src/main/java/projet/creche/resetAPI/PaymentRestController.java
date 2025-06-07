package projet.creche.resetAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.configs.enums.PaymentStatus;
import projet.creche.dto.PaymentDto;
import projet.creche.mapper.PaymentMapper;
import projet.creche.model.Parent;
import projet.creche.model.Payment;
import projet.creche.service.PaymentService;
import projet.creche.service.PersonService;
import projet.creche.tools.ApiResponse;

@RestController
@RequestMapping("/Director/payment")
public class PaymentRestController {

    private final PaymentService paymentService;
    private final PersonService personService;

    @Autowired
    public PaymentRestController(PaymentService paymentService, PersonService personService) {
        this.paymentService = paymentService;
        this.personService = personService;
    }

    /**
     * Création d'un paiement par un parent (état = EN_ATTENTE)
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody PaymentDto paymentDto) {
        // Vérifier l'existence du parent
        Parent parent = personService.findParentById(paymentDto.getParentId());
        if (parent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, null, "Aucun parent trouvé avec l'ID fourni."));
        }

        // Mapper et initialiser les valeurs par défaut
        paymentDto.setParent(parent);
        paymentDto.setStatus(PaymentStatus.EN_ATTENTE); // sécurité supplémentaire

        Payment payment = PaymentMapper.mappeDtoToEntity(paymentDto);
        Payment saved = paymentService.save(payment);

        // Réponse avec le DTO mis à jour (si souhaité)
        PaymentDto savedDto = PaymentMapper.mappeEntityToDto(saved);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, savedDto, "Le paiement a été enregistré avec succès."));
    }

    @PutMapping("/validate/{id}")
    public ResponseEntity<ApiResponse> validatePayment(
            @PathVariable Long id,
            @RequestParam(required = false) String comment
    ) {
        // TODO: générer l'URL de la facture ici si tu as un service de génération PDF
        String fakeInvoiceUrl = "/invoices/facture-" + id + ".pdf";

        Payment validated = paymentService.validatePayment(id, comment, fakeInvoiceUrl);
        PaymentDto dto = PaymentMapper.mappeEntityToDto(validated);
        return ResponseEntity.ok(new ApiResponse<>(true, dto, "Paiement validé avec succès."));
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<ApiResponse> rejectPayment(
            @PathVariable Long id,
            @RequestParam(required = false) String comment
    ) {
        Payment rejected = paymentService.rejectPayment(id, comment);
        PaymentDto dto = PaymentMapper.mappeEntityToDto(rejected);
        return ResponseEntity.ok(new ApiResponse<>(true, dto, "Paiement refusé."));
    }

}
