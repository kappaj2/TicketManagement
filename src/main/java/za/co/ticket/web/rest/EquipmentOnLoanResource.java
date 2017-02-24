package za.co.ticket.web.rest;

import com.codahale.metrics.annotation.Timed;
import za.co.ticket.service.EquipmentOnLoanService;
import za.co.ticket.web.rest.util.HeaderUtil;
import za.co.ticket.web.rest.util.PaginationUtil;
import za.co.ticket.service.dto.EquipmentOnLoanDTO;

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
 * REST controller for managing EquipmentOnLoan.
 */
@RestController
@RequestMapping("/api")
public class EquipmentOnLoanResource {

    private final Logger log = LoggerFactory.getLogger(EquipmentOnLoanResource.class);
        
    @Inject
    private EquipmentOnLoanService equipmentOnLoanService;

    /**
     * POST  /equipment-on-loans : Create a new equipmentOnLoan.
     *
     * @param equipmentOnLoanDTO the equipmentOnLoanDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new equipmentOnLoanDTO, or with status 400 (Bad Request) if the equipmentOnLoan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/equipment-on-loans")
    @Timed
    public ResponseEntity<EquipmentOnLoanDTO> createEquipmentOnLoan(@Valid @RequestBody EquipmentOnLoanDTO equipmentOnLoanDTO) throws URISyntaxException {
        log.debug("REST request to save EquipmentOnLoan : {}", equipmentOnLoanDTO);
        if (equipmentOnLoanDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("equipmentOnLoan", "idexists", "A new equipmentOnLoan cannot already have an ID")).body(null);
        }
        EquipmentOnLoanDTO result = equipmentOnLoanService.save(equipmentOnLoanDTO);
        return ResponseEntity.created(new URI("/api/equipment-on-loans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("equipmentOnLoan", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /equipment-on-loans : Updates an existing equipmentOnLoan.
     *
     * @param equipmentOnLoanDTO the equipmentOnLoanDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated equipmentOnLoanDTO,
     * or with status 400 (Bad Request) if the equipmentOnLoanDTO is not valid,
     * or with status 500 (Internal Server Error) if the equipmentOnLoanDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/equipment-on-loans")
    @Timed
    public ResponseEntity<EquipmentOnLoanDTO> updateEquipmentOnLoan(@Valid @RequestBody EquipmentOnLoanDTO equipmentOnLoanDTO) throws URISyntaxException {
        log.debug("REST request to update EquipmentOnLoan : {}", equipmentOnLoanDTO);
        if (equipmentOnLoanDTO.getId() == null) {
            return createEquipmentOnLoan(equipmentOnLoanDTO);
        }
        EquipmentOnLoanDTO result = equipmentOnLoanService.save(equipmentOnLoanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("equipmentOnLoan", equipmentOnLoanDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /equipment-on-loans : get all the equipmentOnLoans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of equipmentOnLoans in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/equipment-on-loans")
    @Timed
    public ResponseEntity<List<EquipmentOnLoanDTO>> getAllEquipmentOnLoans(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of EquipmentOnLoans");
        Page<EquipmentOnLoanDTO> page = equipmentOnLoanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/equipment-on-loans");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /equipment-on-loans/:id : get the "id" equipmentOnLoan.
     *
     * @param id the id of the equipmentOnLoanDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the equipmentOnLoanDTO, or with status 404 (Not Found)
     */
    @GetMapping("/equipment-on-loans/{id}")
    @Timed
    public ResponseEntity<EquipmentOnLoanDTO> getEquipmentOnLoan(@PathVariable String id) {
        log.debug("REST request to get EquipmentOnLoan : {}", id);
        EquipmentOnLoanDTO equipmentOnLoanDTO = equipmentOnLoanService.findOne(id);
        return Optional.ofNullable(equipmentOnLoanDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /equipment-on-loans/:id : delete the "id" equipmentOnLoan.
     *
     * @param id the id of the equipmentOnLoanDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/equipment-on-loans/{id}")
    @Timed
    public ResponseEntity<Void> deleteEquipmentOnLoan(@PathVariable String id) {
        log.debug("REST request to delete EquipmentOnLoan : {}", id);
        equipmentOnLoanService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("equipmentOnLoan", id.toString())).build();
    }

}
