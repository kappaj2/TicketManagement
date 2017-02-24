package za.co.ticket.web.rest;

import com.codahale.metrics.annotation.Timed;
import za.co.ticket.service.LoanEquipmentService;
import za.co.ticket.web.rest.util.HeaderUtil;
import za.co.ticket.web.rest.util.PaginationUtil;
import za.co.ticket.service.dto.LoanEquipmentDTO;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing LoanEquipment.
 */
@RestController
@RequestMapping("/api")
public class LoanEquipmentResource {

    private final Logger log = LoggerFactory.getLogger(LoanEquipmentResource.class);
        
    @Inject
    private LoanEquipmentService loanEquipmentService;

    /**
     * POST  /loan-equipments : Create a new loanEquipment.
     *
     * @param loanEquipmentDTO the loanEquipmentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loanEquipmentDTO, or with status 400 (Bad Request) if the loanEquipment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loan-equipments")
    @Timed
    public ResponseEntity<LoanEquipmentDTO> createLoanEquipment(@Valid @RequestBody LoanEquipmentDTO loanEquipmentDTO) throws URISyntaxException {
        log.debug("REST request to save LoanEquipment : {}", loanEquipmentDTO);
        if (loanEquipmentDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("loanEquipment", "idexists", "A new loanEquipment cannot already have an ID")).body(null);
        }
        LoanEquipmentDTO result = loanEquipmentService.save(loanEquipmentDTO);
        return ResponseEntity.created(new URI("/api/loan-equipments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("loanEquipment", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loan-equipments : Updates an existing loanEquipment.
     *
     * @param loanEquipmentDTO the loanEquipmentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loanEquipmentDTO,
     * or with status 400 (Bad Request) if the loanEquipmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the loanEquipmentDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loan-equipments")
    @Timed
    public ResponseEntity<LoanEquipmentDTO> updateLoanEquipment(@Valid @RequestBody LoanEquipmentDTO loanEquipmentDTO) throws URISyntaxException {
        log.debug("REST request to update LoanEquipment : {}", loanEquipmentDTO);
        if (loanEquipmentDTO.getId() == null) {
            return createLoanEquipment(loanEquipmentDTO);
        }
        LoanEquipmentDTO result = loanEquipmentService.save(loanEquipmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("loanEquipment", loanEquipmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loan-equipments : get all the loanEquipments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loanEquipments in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/loan-equipments")
    @Timed
    public ResponseEntity<List<LoanEquipmentDTO>> getAllLoanEquipments(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of LoanEquipments");
        Page<LoanEquipmentDTO> page = loanEquipmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loan-equipments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /loan-equipments/:id : get the "id" loanEquipment.
     *
     * @param id the id of the loanEquipmentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loanEquipmentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/loan-equipments/{id}")
    @Timed
    public ResponseEntity<LoanEquipmentDTO> getLoanEquipment(@PathVariable String id) {
        log.debug("REST request to get LoanEquipment : {}", id);
        LoanEquipmentDTO loanEquipmentDTO = loanEquipmentService.findOne(id);
        return Optional.ofNullable(loanEquipmentDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /loan-equipments/:id : delete the "id" loanEquipment.
     *
     * @param id the id of the loanEquipmentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loan-equipments/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoanEquipment(@PathVariable String id) {
        log.debug("REST request to delete LoanEquipment : {}", id);
        loanEquipmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("loanEquipment", id.toString())).build();
    }

}
