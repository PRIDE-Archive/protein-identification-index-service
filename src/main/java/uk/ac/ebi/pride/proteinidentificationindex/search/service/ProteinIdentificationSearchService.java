package uk.ac.ebi.pride.proteinidentificationindex.search.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.ac.ebi.pride.proteinidentificationindex.search.model.ProteinIdentification;
import uk.ac.ebi.pride.proteinidentificationindex.search.model.ProteinIdentificationSummary;
import uk.ac.ebi.pride.proteinidentificationindex.search.service.repository.SolrProteinIdentificationRepository;

import java.util.*;

/**
 * @author Jose A. Dianes
 * @version $Id$
 *          <p/>
 *          NOTE: protein accessions can contain chars that produce problems in solr queries ([,],:). They are replaced by _ when
 *          using the repository methods
 */
@SuppressWarnings("unused")
@Service
public class ProteinIdentificationSearchService {

    private SolrProteinIdentificationRepository solrProteinIdentificationRepository;

    public ProteinIdentificationSearchService(SolrProteinIdentificationRepository solrProteinIdentificationRepository) {
        this.solrProteinIdentificationRepository = solrProteinIdentificationRepository;
    }

    public void setSolrProteinIdentificationRepository(SolrProteinIdentificationRepository solrProteinIdentificationRepository) {
        this.solrProteinIdentificationRepository = solrProteinIdentificationRepository;
    }

    // find by id methods
    public List<ProteinIdentification> findById(String id) {
        return solrProteinIdentificationRepository.findById(id);
    }

    public List<ProteinIdentification> findById(Collection<String> ids) {
        return solrProteinIdentificationRepository.findByIdIn(ids);
    }

    // Synonym query methods
    public List<ProteinIdentification> findBySynonymAndProjectAccession(String synonym, String projectAccession) {
        return solrProteinIdentificationRepository.findBySynonymAndProjectAccession(synonym, projectAccession);
    }

    // find by accession methods
    public List<ProteinIdentification> findByAccession(String accession) {
        return solrProteinIdentificationRepository.findByAccession(accession);
    }

    public List<ProteinIdentification> findByAccession(Collection<String> accessions) {
        return solrProteinIdentificationRepository.findByAccessionIn(accessions);
    }

    // Project accession query methods
    public List<ProteinIdentification> findByProjectAccession(String projectAccession) {
        return solrProteinIdentificationRepository.findByProjectAccession(projectAccession);
    }
    public Long countByProjectAccession(String projectAccession) {
        return solrProteinIdentificationRepository.countByProjectAccession(projectAccession);
    }

    public List<ProteinIdentification> findByProjectAccession(Collection<String> projectAccessions) {
        return solrProteinIdentificationRepository.findByProjectAccessionIn(projectAccessions);
    }

    public Page<ProteinIdentification> findByProjectAccession(String projectAccession, Pageable pageable) {
        return solrProteinIdentificationRepository.findByProjectAccession(projectAccession, pageable);
    }

    public Page<ProteinIdentification> findByProjectAccession(Collection<String> projectAccessions, Pageable pageable) {
        return solrProteinIdentificationRepository.findByProjectAccessionIn(projectAccessions, pageable);
    }

    // Assay accession query methods
    public List<ProteinIdentification> findByAssayAccession(String assayAccession) {
        return solrProteinIdentificationRepository.findByAssayAccession(assayAccession);
    }
    public Long countByAssayAccession(String assayAccession) {
        return solrProteinIdentificationRepository.countByAssayAccession(assayAccession);
    }

    public List<ProteinIdentification> findByAssayAccession(Collection<String> assayAccessions) {
        return solrProteinIdentificationRepository.findByAssayAccessionIn(assayAccessions);
    }

    public Page<ProteinIdentification> findByAssayAccession(String assayAccession, Pageable pageable) {
        return solrProteinIdentificationRepository.findByAssayAccession(assayAccession, pageable);
    }

    public Page<ProteinIdentification> findByAssayAccession(Collection<String> assayAccessions, Pageable pageable) {
        return solrProteinIdentificationRepository.findByAssayAccessionIn(assayAccessions, pageable);
    }

    // Submitted accession query methods
    public List<ProteinIdentification> findBySubmittedAccessionAndAssayAccession(String submittedAccession, String assayAccession){
        return solrProteinIdentificationRepository.findBySubmittedAccessionAndAssayAccession(submittedAccession, assayAccession);
    }

    public List<ProteinIdentificationSummary> findSummaryByProjectAccession(String projectAccession) {
        return solrProteinIdentificationRepository.findSummaryByProjectAccession(projectAccession);
    }

    public Set<String> getUniqueProteinAccessionsByProjectAccession(String projectAccession) {
        List<ProteinIdentificationSummary> results = findSummaryByProjectAccession(projectAccession);
        Set<String> accessions = new HashSet<String>(results.size());
        Iterator<ProteinIdentificationSummary> iterator = results.iterator();
        while (iterator.hasNext()) {
            // in order to make it work we need to cast to the real bean before accessing data
            ProteinIdentification ident = (ProteinIdentification) iterator.next();
            accessions.add(ident.getAccession());
        }
        return accessions;
    }

    public List<ProteinIdentificationSummary> findSummaryByAssayAccession(String assayAccession) {
        return solrProteinIdentificationRepository.findSummaryByAssayAccession(assayAccession);
    }

    public Set<String> getUniqueProteinAccessionsByAssayAccession(String assayAccession) {
        List<ProteinIdentificationSummary> results = findSummaryByAssayAccession(assayAccession);
        Set<String> accessions = new HashSet<String>(results.size());
        Iterator<ProteinIdentificationSummary> iterator = results.iterator();
        while (iterator.hasNext()) {
            // in order to make it work we need to cast to the real bean before accessing data
            ProteinIdentification ident = (ProteinIdentification) iterator.next();
            accessions.add(ident.getAccession());
        }
        return accessions;
    }
}
