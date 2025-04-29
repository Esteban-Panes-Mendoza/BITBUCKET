package aiss.bitbucketminer.controller;

import aiss.bitbucketminer.model.COMMENT.Comments;
import aiss.bitbucketminer.model.Corrección.GitMinerComment;
import aiss.bitbucketminer.model.Corrección.GitMinerCommit;
import aiss.bitbucketminer.model.COMMIT.Commit;
import aiss.bitbucketminer.model.Corrección.GitMinerIssues;
import aiss.bitbucketminer.model.Corrección.GitMinerProject;
import aiss.bitbucketminer.model.ISSUES.Issues;
import aiss.bitbucketminer.service.*;
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

        GitMinerProject project = projectTransformerService.transform(repoData, new ArrayList<>(), new ArrayList<>());

        String projectName = project.getName();

        Commit commitData = bitbucketService.getCommitsFromBitbucket(workspace, repoSlug, nCommits, maxPages);
        //Transformamos esos datos al formato GitMiner
        List<GitMinerCommit> gitMinerCommits = CommitTransformerService.transform(commitData);

        //Devolvemos los datos transformados
        return ResponseEntity.ok(gitMinerCommits);
    }

    //TODO AÑADIR NCOMMITS Y MAXPAGES

    @GetMapping("/{workspace}/{repoSlug}")
    public ResponseEntity<GitMinerProject> getProjects(
            @PathVariable String workspace,
            @PathVariable String repoSlug,
            @RequestParam(required = false) Integer nCommits,
            @RequestParam(required = false) Integer maxPages) {

        // Obtener datos del proyecto (issues)
        Issues issuesData = bitbucketService.getIssuesFromBitbucket(workspace, repoSlug);

        //Obtener Issues
        List<GitMinerIssues> issues = IssuesTransformerService.transform(issuesData);

        // Obtener datos del proyecto
        Commit_Repository repoData = bitbucketService.getProjectsFromBitbucket(workspace, repoSlug);

        // Obtener Commits
        Commit commitData = bitbucketService.getCommitsFromBitbucket(workspace, repoSlug, null, null);

        List<GitMinerCommit> commits = CommitTransformerService.transform(commitData);

        // Transformar y combinar
        GitMinerProject project = projectTransformerService.transform(repoData, commits, issues);

        return ResponseEntity.ok(project);
    }

    @GetMapping("/{workspace}/{repoSlug}/issues")
    public ResponseEntity<List<GitMinerIssues>> getIssues(
            @PathVariable String workspace,
            @PathVariable String repoSlug,
            @RequestParam(required = false) Integer nCommits,
            @RequestParam(required = false) Integer maxPages) {

        // Obtener datos del proyecto (issues)
        Issues issuesData = bitbucketService.getIssuesFromBitbucket(workspace, repoSlug);

        //Obtener Issues
        List<GitMinerIssues> issues = IssuesTransformerService.transform(issuesData);


        return ResponseEntity.ok(issues);
    }

    @GetMapping("/{workspace}/{repoSlug}/issues/{issuesId}/comments")
    public ResponseEntity<List<GitMinerComment>> getComments(
            @PathVariable String workspace,
            @PathVariable String repoSlug,
            @PathVariable Integer issuesId,
            @RequestParam(required = false) Integer nCommits,
            @RequestParam(required = false) Integer maxPages) {

        // Obtener datos del proyecto (comments)
        Comments commentsdata = bitbucketService.getCommentsFromBitbucket(workspace, repoSlug, issuesId);

        //Transformar comments
        List<GitMinerComment> comments = CommentsTransformService.transform(commentsdata);


        return ResponseEntity.ok(comments);
    }












}
