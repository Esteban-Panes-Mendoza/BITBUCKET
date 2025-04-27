package aiss.bitbucketminer.controller;

import aiss.bitbucketminer.model.Corrección.GitMinerCommit;
import aiss.bitbucketminer.model.COMMIT.Commit;
import aiss.bitbucketminer.model.Corrección.GitMinerProject;
import aiss.bitbucketminer.service.CommitTransformerService;
import aiss.bitbucketminer.service.BitbucketService; // Servicio que conecta con Bitbucket
import aiss.bitbucketminer.service.ProjectTransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import aiss.bitbucketminer.model.REPOSITORY.Commit_Repository;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/bitbucket")
public class BitbucketController {

    @Autowired
    private BitbucketService bitbucketService;

    @Autowired
    private ProjectTransformerService projectTransformerService;

    @Autowired
    private CommitTransformerService CommitTransformerService;


    @GetMapping("/{workspace}/{repoSlug}/commits")
    public ResponseEntity<List<GitMinerCommit>> getCommits(
            @PathVariable String workspace,
            @PathVariable String repoSlug,
            @RequestParam(defaultValue = "5") Integer nCommits,
            @RequestParam(defaultValue = "2") Integer maxPages) {

        Commit_Repository repoData = bitbucketService.getProjectsFromBitbucket(workspace, repoSlug);
        GitMinerProject project = projectTransformerService.transform(repoData, new ArrayList<>());

        String projectName = project.getName();

        Commit commitData = bitbucketService.getCommitsFromBitbucket(workspace, repoSlug, nCommits, maxPages);
        //Transformamos esos datos al formato GitMiner
        List<GitMinerCommit> gitMinerCommits = CommitTransformerService.transform(commitData, projectName);

        // Devolvemos los datos transformados
        return ResponseEntity.ok(gitMinerCommits);
    }

    @GetMapping("/{workspace}/{repoSlug}")
    public ResponseEntity<GitMinerProject> getProjectWithCommits(
            @PathVariable String workspace,
            @PathVariable String repoSlug,
            @RequestParam(required = false) Integer nCommits,
            @RequestParam(required = false) Integer maxPages) {

        // Obtener datos del proyecto
        Commit_Repository repoData = bitbucketService.getProjectsFromBitbucket(workspace, repoSlug);

        // Obtener commits
        Commit commitData = bitbucketService.getCommitsFromBitbucket(workspace, repoSlug, null, null);

        List<GitMinerCommit> commits = CommitTransformerService.transform(commitData, repoData.getProject().getName());

        // Transformar y combinar
        GitMinerProject project = projectTransformerService.transform(repoData, commits);

        return ResponseEntity.ok(project);
    }








}
