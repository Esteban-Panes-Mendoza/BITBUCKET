package aiss.bitbucketminer.controller;

import aiss.bitbucketminer.model.COMMENT.Comments;
import aiss.bitbucketminer.model.Corrección.*;
import aiss.bitbucketminer.model.COMMIT.Commit;
import aiss.bitbucketminer.model.ISSUES.Issues;
import aiss.bitbucketminer.model.ISSUES.Value;
import aiss.bitbucketminer.model.USER.Users;
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

        // Obtener datos del repositorio
        Commit_Repository repoData = bitbucketService.getProjectsFromBitbucket(workspace, repoSlug);

        // Obtener Commits
        Commit commitData = bitbucketService.getCommitsFromBitbucket(workspace, repoSlug, nCommits, maxPages);
        List<GitMinerCommit> commits = CommitTransformerService.transform(commitData);

        // Obtener Issues y comentarios
        Issues issuesData = bitbucketService.getIssuesFromBitbucket(workspace, repoSlug);
        List<GitMinerIssues> issues = new ArrayList<>();
        List<GitMinerUser> allusers = new ArrayList<>();


        // Iterar sobre cada issue
        for (Value issueValue : issuesData.getValues()) {

            // Usuario que reportó la issue
            Users reporterData = bitbucketService.getUserFromBitbucket(issueValue.getReporter().getAccountId());
            GitMinerUser reporter = UserTransformerService.transform(reporterData);
            allusers.add(reporter);

            // Obtener y transformar los comentarios de la issue
            Comments commentsData = bitbucketService.getCommentsFromBitbucket(workspace, repoSlug, issueValue.getId());
            List<GitMinerComment> comments = new ArrayList<>();

            if (commentsData != null && commentsData.getValues() != null) {
                for (aiss.bitbucketminer.model.COMMENT.Value commentValue : commentsData.getValues()) {
                    Users commentUserData = bitbucketService.getUserFromBitbucket(commentValue.getUser().getAccountId());
                    GitMinerUser commentUser = UserTransformerService.transform(commentUserData);

                    // Transformar el comentario
                    GitMinerComment comment = CommentsTransformService.transform(commentValue, issueValue.getId(), commentUser);
                    if (comment != null) {
                        comments.add(comment);
                    }
                }
            }

            // Transformar la issue y asignar los comentarios
            List<GitMinerIssues> transformedIssues = IssuesTransformerService.transform(issuesData, comments, allusers);
            if (transformedIssues != null && !transformedIssues.isEmpty()) {
                issues.addAll(transformedIssues);
            }
        }

        // Construir y devolver el proyecto final
        GitMinerProject project = projectTransformerService.transform(repoData, commits, issues);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/{workspace}/{repoSlug}/issues")
    public ResponseEntity<List<GitMinerIssues>> getIssues(
            @PathVariable String workspace,
            @PathVariable String repoSlug,
            @RequestParam(required = false) Integer nCommits,
            @RequestParam(required = false) Integer maxPages) {

        // Obtener datos de los issues
        Issues issuesData = bitbucketService.getIssuesFromBitbucket(workspace, repoSlug);
        List<GitMinerIssues> resultado = new ArrayList<>();

        // Verificar si se obtuvieron issues
        if (issuesData != null && issuesData.getValues() != null) {
            // Obtener los comentarios y usuarios de todos los issues
            List<GitMinerComment> allComments = new ArrayList<>();
            List<GitMinerUser> allUsers = new ArrayList<>();

            for (Value value : issuesData.getValues()) {
                Users userData = bitbucketService.getUserFromBitbucket(value.getReporter().getAccountId());
                GitMinerUser gitMinerUser = UserTransformerService.transform(userData);
                allUsers.add(gitMinerUser);
                Comments commentsData = bitbucketService.getCommentsFromBitbucket(workspace, repoSlug, value.getId());
                if (commentsData != null && commentsData.getValues() != null) {
                    for (aiss.bitbucketminer.model.COMMENT.Value commentValue : commentsData.getValues()) {
                        Users commentUserData = bitbucketService.getUserFromBitbucket(commentValue.getUser().getAccountId());
                        GitMinerUser commentUser = UserTransformerService.transform(commentUserData);
                        GitMinerComment comment = CommentsTransformService.transform(commentValue, value.getId(), commentUser);
                        if (comment != null) {
                            allComments.add(comment);
                        }
                    }
                }
            }

            // Transformar todos los issues
            resultado = IssuesTransformerService.transform(issuesData, allComments, allUsers);
        }

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{workspace}/{repoSlug}/issues/{issuesId}/comments")
    public ResponseEntity<List<GitMinerComment>> getComments(
            @PathVariable String workspace,
            @PathVariable String repoSlug,
            @PathVariable Integer issuesId,
            @RequestParam(required = false) Integer nCommits,
            @RequestParam(required = false) Integer maxPages) {

        Comments commentsData = bitbucketService.getCommentsFromBitbucket(workspace, repoSlug, issuesId);
        List<GitMinerComment> result = new ArrayList<>();

        for (aiss.bitbucketminer.model.COMMENT.Value value : commentsData.getValues()) {
            Users userData = bitbucketService.getUserFromBitbucket(value.getUser().getAccountId());
            GitMinerUser gitMinerUser = UserTransformerService.transform(userData);

            GitMinerComment comment = CommentsTransformService.transform(value, issuesId, gitMinerUser);
            if (comment != null) {
                result.add(comment);
            }
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{userId}")
        public ResponseEntity<GitMinerUser> getUser(
                @PathVariable String userId,
                @RequestParam(required = false) Integer nCommits,
                @RequestParam(required = false) Integer maxPages) {
        // Obtener datos del usuario
        Users userdata = bitbucketService.getUserFromBitbucket(userId);

        // Transformar datos del usuario
        GitMinerUser gitMinerUser = UserTransformerService.transform(userdata);

        return ResponseEntity.ok(gitMinerUser);

    }















}
