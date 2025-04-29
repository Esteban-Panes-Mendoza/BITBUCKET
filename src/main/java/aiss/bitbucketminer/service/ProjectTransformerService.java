package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.Corrección.GitMinerIssues;
import aiss.bitbucketminer.model.Corrección.GitMinerProject;
import aiss.bitbucketminer.model.REPOSITORY.Commit_Repository;
import aiss.bitbucketminer.model.Corrección.GitMinerCommit;
import aiss.bitbucketminer.service.CommitTransformerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectTransformerService {

    public GitMinerProject transform(Commit_Repository bitbucketRepo, List<GitMinerCommit> commits, List<GitMinerIssues> issues) {
        if (bitbucketRepo == null) {
            return null;
        }
        List<GitMinerProject> res = new ArrayList<>();

        GitMinerProject project = new GitMinerProject();

        // Configurar propiedades básicas
        if (bitbucketRepo.getProject() != null) {
            project.setId(bitbucketRepo.getProject().getUuid());
            project.setName(bitbucketRepo.getProject().getName());
            if (bitbucketRepo.getProject().getLinks() != null
                    && bitbucketRepo.getProject().getLinks().getSelf() != null) {
                project.setWeb_url(bitbucketRepo.getProject().getLinks().getSelf().getHref());
            }
        } else {
            project.setId(bitbucketRepo.getUuid());
            project.setName(bitbucketRepo.getName());
            if (bitbucketRepo.getLinks() != null
                    && bitbucketRepo.getLinks().getHtml() != null) {
                project.setWeb_url(bitbucketRepo.getLinks().getHtml().getHref());
            }
        }

        for (GitMinerCommit commit : commits) {
                project.addCommit(commit);
            }
                project.setIssues(issues);


        return project;
    }
}

