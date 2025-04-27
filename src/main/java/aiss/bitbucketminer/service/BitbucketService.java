package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.COMMIT.Commit;
import aiss.bitbucketminer.model.REPOSITORY.Commit_Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class BitbucketService {

    private static final Logger logger = LoggerFactory.getLogger(BitbucketService.class);
    private final RestTemplate restTemplate;
    private static final String BITBUCKET_API_BASE_URL = "https://api.bitbucket.org/2.0";

    public BitbucketService() {
        this.restTemplate = new RestTemplate();
    }

    public Commit getCommitsFromBitbucket(String workspace, String repoSlug, Integer nCommits, Integer maxPages) {
        String url = BITBUCKET_API_BASE_URL
                + "/repositories/" + workspace + "/" + repoSlug + "/commits";
        if (nCommits != null) {
            url += "?pagelen=" + nCommits;
        }
        if (maxPages != null) {
            url += "&page=" + maxPages;
        }

        try {
            // Petición GET simple usando RestTemplate
            Commit response = restTemplate.getForObject(url, Commit.class);

            if (response == null) {
                logger.warn("La respuesta de la API de Bitbucket es nula para la URL: {}", url);
                return null;
            }
            return response;
        } catch (RestClientException e) {
            logger.error("Error al realizar la solicitud a la API de Bitbucket: {}", e.getMessage());
            return null;
        }
    }

    public Commit_Repository getProjectsFromBitbucket(String workspace, String repoSlug) {
        String url = BITBUCKET_API_BASE_URL
                + "/repositories/" + workspace + "/" + repoSlug;

        try {

            Commit_Repository response = restTemplate.getForObject(url, Commit_Repository.class);

            if (response == null) {
                logger.warn("La respuesta de la API de Bitbucket es nula para la URL: {}", url);
                return null;
            }

            return response;
        } catch (RestClientException e) {
            logger.error("Error al realizar la solicitud a la API de Bitbucket: {}", e.getMessage());
            return null;
        }
    }



}
